package ca.uhn.fhir.jpa.batch2;

import ca.uhn.fhir.batch2.api.IJobMaintenanceService;
import ca.uhn.fhir.batch2.api.IJobPersistence;
import ca.uhn.fhir.batch2.api.IJobStepWorker;
import ca.uhn.fhir.batch2.api.RunOutcome;
import ca.uhn.fhir.batch2.api.VoidModel;
import ca.uhn.fhir.batch2.coordinator.JobDefinitionRegistry;
import ca.uhn.fhir.batch2.maintenance.JobMaintenanceServiceImpl;
import ca.uhn.fhir.batch2.model.JobDefinition;
import ca.uhn.fhir.batch2.model.JobInstance;
import ca.uhn.fhir.batch2.model.JobWorkNotificationJsonMessage;
import ca.uhn.fhir.batch2.model.StatusEnum;
import ca.uhn.fhir.batch2.model.WorkChunk;
import ca.uhn.fhir.batch2.model.WorkChunkStatusEnum;
import ca.uhn.fhir.jpa.dao.data.IBatch2JobInstanceRepository;
import ca.uhn.fhir.jpa.dao.data.IBatch2WorkChunkRepository;
import ca.uhn.fhir.jpa.entity.Batch2JobInstanceEntity;
import ca.uhn.fhir.jpa.entity.Batch2WorkChunkEntity;
import ca.uhn.fhir.jpa.subscription.channel.api.ChannelConsumerSettings;
import ca.uhn.fhir.jpa.subscription.channel.api.IChannelFactory;
import ca.uhn.fhir.jpa.subscription.channel.impl.LinkedBlockingChannel;
import ca.uhn.fhir.jpa.test.BaseJpaR4Test;
import ca.uhn.fhir.jpa.test.Batch2JobHelper;
import ca.uhn.fhir.model.api.IModelJson;
import ca.uhn.fhir.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static ca.uhn.fhir.batch2.config.BaseBatch2Config.CHANNEL_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Batch2JobMaintenanceDatabaseIT extends BaseJpaR4Test {
	private static final Logger ourLog = LoggerFactory.getLogger(Batch2JobMaintenanceDatabaseIT.class);

	public static final int TEST_JOB_VERSION = 1;
	public static final String FIRST_STEP_ID = "first-step";
	public static final String SECOND_STEP_ID = "second-step";
	public static final String LAST_STEP_ID = "last-step";
	private static final String JOB_DEF_ID = "test-job-definition";
	private static final JobDefinition<? extends IModelJson> ourJobDef = buildJobDefinition();
	private static final String TEST_INSTANCE_ID = "test-instance-id";

	@Autowired
	JobDefinitionRegistry myJobDefinitionRegistry;
	@Autowired
	IJobMaintenanceService myJobMaintenanceService;
	@Autowired
	Batch2JobHelper myBatch2JobHelper;
	@Autowired
	private IChannelFactory myChannelFactory;

	@Autowired
	IJobPersistence myJobPersistence;
	@Autowired
	IBatch2JobInstanceRepository myJobInstanceRepository;
	@Autowired
	IBatch2WorkChunkRepository myWorkChunkRepository;

	private LinkedBlockingChannel myWorkChannel;
	private final List<StackTraceElement[]> myStackTraceElements = new ArrayList<>();
	private static final AtomicInteger ourCounter = new AtomicInteger(0);
	private TransactionTemplate myTxTemplate;

	@BeforeEach
	public void before() {
		myJobDefinitionRegistry.addJobDefinition(ourJobDef);
		myWorkChannel = (LinkedBlockingChannel) myChannelFactory.getOrCreateReceiver(CHANNEL_NAME, JobWorkNotificationJsonMessage.class, new ChannelConsumerSettings());
		JobMaintenanceServiceImpl jobMaintenanceService = (JobMaintenanceServiceImpl) myJobMaintenanceService;
		jobMaintenanceService.setMaintenanceJobStartedCallback(() -> {
			ourLog.info("Batch maintenance job started");
			myStackTraceElements.add(Thread.currentThread().getStackTrace());
		});

		myTxTemplate = new TransactionTemplate(myTxManager);
		storeNewInstance(ourJobDef);
	}

	@AfterEach
	public void after() {
		myWorkChannel.clearInterceptorsForUnitTest();
		JobMaintenanceServiceImpl jobMaintenanceService = (JobMaintenanceServiceImpl) myJobMaintenanceService;
		jobMaintenanceService.setMaintenanceJobStartedCallback(() -> {
		});
		myWorkChunkRepository.deleteAll();
		myJobInstanceRepository.deleteAll();
	}

	@Test
	public void testCreateInstance() {
		assertInstanceCount(1);
		myJobMaintenanceService.runMaintenancePass();
		assertInstanceCount(1);
	}

	@Test
	public void testSingleQueuedChunk() {

		assertWorkChunkCount(0);

		storeNewWorkChunk(FIRST_STEP_ID, WorkChunkStatusEnum.QUEUED);

		assertWorkChunkCount(FIRST_STEP_ID, 1, WorkChunkStatusEnum.QUEUED);
		myJobMaintenanceService.runMaintenancePass();
		assertWorkChunkCount(1);
		assertWorkChunkCount(FIRST_STEP_ID, 1, WorkChunkStatusEnum.QUEUED);
	}

	@Test
	public void testSingleInProgressChunk() {
		assertWorkChunkCount(0);

		storeNewWorkChunk(FIRST_STEP_ID, WorkChunkStatusEnum.IN_PROGRESS);

		assertWorkChunkCount(FIRST_STEP_ID, 1, WorkChunkStatusEnum.IN_PROGRESS);
		myJobMaintenanceService.runMaintenancePass();
		assertWorkChunkCount(1);
		assertWorkChunkCount(FIRST_STEP_ID, 1, WorkChunkStatusEnum.IN_PROGRESS);
	}

	@Test
	public void testSingleCompleteChunk() {
		assertCurrentGatedStep(FIRST_STEP_ID);

		assertWorkChunkCount(0);

		storeNewWorkChunk(FIRST_STEP_ID, WorkChunkStatusEnum.COMPLETED);
		storeNewWorkChunk(SECOND_STEP_ID, WorkChunkStatusEnum.QUEUED);

		assertWorkChunkCount(FIRST_STEP_ID, 1, WorkChunkStatusEnum.COMPLETED);
		assertWorkChunkCount(SECOND_STEP_ID, 1, WorkChunkStatusEnum.QUEUED);

		myJobMaintenanceService.runMaintenancePass();

		assertWorkChunkCount(2);
		assertWorkChunkCount(FIRST_STEP_ID, 1, WorkChunkStatusEnum.COMPLETED);
		assertWorkChunkCount(SECOND_STEP_ID, 1, WorkChunkStatusEnum.IN_PROGRESS);
		assertCurrentGatedStep(SECOND_STEP_ID);
	}

	private void assertCurrentGatedStep(String theNextStepId) {
		Optional<JobInstance> instance = myJobPersistence.fetchInstance(TEST_INSTANCE_ID);
		assertTrue(instance.isPresent());
		assertEquals(theNextStepId, instance.get().getCurrentGatedStepId());
	}

	private void assertWorkChunkCount(String theStepId, int theExpectedCount, WorkChunkStatusEnum theExpectedStatus) {
		List<WorkChunk> workChunks = new ArrayList<>();
		List<String> allWorkChunks = new ArrayList<>();
		myTxTemplate.executeWithoutResult(t -> {
			myJobPersistence.fetchAllWorkChunksForStepStream(TEST_INSTANCE_ID, theStepId)
				.filter(w -> w.getStatus() == theExpectedStatus)
				.forEach(workChunks::add);

			myJobPersistence.fetchAllWorkChunksIterator(TEST_INSTANCE_ID, false)
				.forEachRemaining(w -> allWorkChunks.add(w.toString()));
		});
		assertThat("Expected " + theExpectedCount + " chunks with step " + theStepId + " and status " + theExpectedStatus + ".  All Work Chunks:\n" + StringUtils.join(allWorkChunks, "\n"), workChunks, hasSize(theExpectedCount));
	}

	@NotNull
	private void storeNewWorkChunk(String theStepId, WorkChunkStatusEnum theStatus) {
		Batch2WorkChunkEntity workChunk = new Batch2WorkChunkEntity();
		workChunk.setId("chunk" + ourCounter.getAndIncrement());
		workChunk.setJobDefinitionId(JOB_DEF_ID);
		workChunk.setStatus(theStatus);
		workChunk.setJobDefinitionVersion(TEST_JOB_VERSION);
		workChunk.setCreateTime(new Date());
		workChunk.setInstanceId(TEST_INSTANCE_ID);
		workChunk.setTargetStepId(theStepId);
		myWorkChunkRepository.save(workChunk);
	}

	private void assertWorkChunkCount(int theCount) {
		assertThat(myWorkChunkRepository.findAll(), hasSize(theCount));
	}

	@NotNull
	private static JobDefinition<? extends IModelJson> buildJobDefinition() {
		IJobStepWorker<TestJobParameters, VoidModel, FirstStepOutput> firstStep = (step, sink) -> {
			ourLog.info("First step for chunk {}", step.getChunkId());
			return RunOutcome.SUCCESS;
		};
		IJobStepWorker<TestJobParameters, FirstStepOutput, SecondStepOutput> secondStep = (step, sink) -> {
			ourLog.info("Second step for chunk {}", step.getChunkId());
			return RunOutcome.SUCCESS;
		};
		IJobStepWorker<TestJobParameters, SecondStepOutput, VoidModel> lastStep = (step, sink) -> {
			ourLog.info("Last step for chunk {}", step.getChunkId());
			return RunOutcome.SUCCESS;
		};

		JobDefinition<? extends IModelJson> definition = buildGatedJobDefinition(firstStep, secondStep, lastStep);
		return definition;
	}

	private void storeNewInstance(JobDefinition<? extends IModelJson> definition) {
		Batch2JobInstanceEntity entity = new Batch2JobInstanceEntity();
		entity.setId(TEST_INSTANCE_ID);
		entity.setStatus(StatusEnum.IN_PROGRESS);
		entity.setDefinitionId(JOB_DEF_ID);
		entity.setDefinitionVersion(TEST_JOB_VERSION);
		entity.setParams(JsonUtil.serializeOrInvalidRequest(new TestJobParameters()));
		entity.setCurrentGatedStepId(FIRST_STEP_ID);
		entity.setCreateTime(new Date());

		myTxTemplate.executeWithoutResult(t -> {
			myJobInstanceRepository.save(entity);
		});
	}

	private void assertInstanceCount(int size) {
		assertThat(myJobPersistence.fetchInstancesByJobDefinitionId(JOB_DEF_ID, 100, 0), hasSize(size));
	}

	@Nonnull
	private static JobDefinition<? extends IModelJson> buildGatedJobDefinition(IJobStepWorker<TestJobParameters, VoidModel, FirstStepOutput> theFirstStep, IJobStepWorker<TestJobParameters, FirstStepOutput, SecondStepOutput> theSecondStep, IJobStepWorker<TestJobParameters, SecondStepOutput, VoidModel> theLastStep) {
		return JobDefinition.newBuilder()
			.setJobDefinitionId(JOB_DEF_ID)
			.setJobDescription("test job")
			.setJobDefinitionVersion(TEST_JOB_VERSION)
			.setParametersType(TestJobParameters.class)
			.gatedExecution()
			.addFirstStep(
				FIRST_STEP_ID,
				"Test first step",
				FirstStepOutput.class,
				theFirstStep
			)
			.addIntermediateStep(
				SECOND_STEP_ID,
				"Test second step",
				SecondStepOutput.class,
				theSecondStep
			)
			.addLastStep(
				LAST_STEP_ID,
				"Test last step",
				theLastStep
			)
			.completionHandler(details -> {
			})
			.build();
	}

	static class TestJobParameters implements IModelJson {
		TestJobParameters() {
		}
	}

	static class FirstStepOutput implements IModelJson {
		FirstStepOutput() {
		}
	}

	static class SecondStepOutput implements IModelJson {
		SecondStepOutput() {
		}
	}
}