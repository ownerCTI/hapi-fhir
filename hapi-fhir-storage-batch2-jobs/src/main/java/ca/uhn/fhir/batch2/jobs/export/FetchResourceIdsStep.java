package ca.uhn.fhir.batch2.jobs.export;

import ca.uhn.fhir.batch2.api.IFirstJobStepWorker;
import ca.uhn.fhir.batch2.api.IJobDataSink;
import ca.uhn.fhir.batch2.api.JobExecutionFailedException;
import ca.uhn.fhir.batch2.api.RunOutcome;
import ca.uhn.fhir.batch2.api.StepExecutionDetails;
import ca.uhn.fhir.batch2.api.VoidModel;
import ca.uhn.fhir.batch2.jobs.export.models.BulkExportIdList;
import ca.uhn.fhir.batch2.jobs.export.models.BulkExportJobParameters;
import ca.uhn.fhir.batch2.jobs.models.Id;
import ca.uhn.fhir.jpa.bulk.export.api.IBulkExportProcessor;
import ca.uhn.fhir.jpa.bulk.export.model.BulkExportJobStatusEnum;
import ca.uhn.fhir.jpa.bulk.export.model.ExportPIDIteratorParameters;
import ca.uhn.fhir.rest.api.server.storage.ResourcePersistentId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FetchResourceIdsStep implements IFirstJobStepWorker<BulkExportJobParameters, BulkExportIdList> {
	private static final Logger ourLog = LoggerFactory.getLogger(FetchResourceIdsStep.class);

	private static final int MAX_IDS_TO_BATCH = 1000;

	@Autowired
	private IBulkExportProcessor myBulkIdProcessor;

	@Nonnull
	@Override
	public RunOutcome run(@Nonnull StepExecutionDetails<BulkExportJobParameters, VoidModel> theStepExecutionDetails,
								 @Nonnull IJobDataSink<BulkExportIdList> theDataSink) throws JobExecutionFailedException {
		BulkExportJobParameters params = theStepExecutionDetails.getParameters();
		ourLog.info("Starting BatchExport job");

		// set job status to building
		myBulkIdProcessor.setJobStatus(params.getJobId(), BulkExportJobStatusEnum.BUILDING);

		ExportPIDIteratorParameters providerParams = new ExportPIDIteratorParameters();
		providerParams.setFilters(params.getFilters());
		providerParams.setStartDate(params.getStartDate());

		int submissionCount = 0;
		for (String resourceType : params.getResourceTypes()) {
			providerParams.setResourceType(resourceType);

			// TODO - implementation - see BulkItemReader
			Iterator<ResourcePersistentId> pidIterator = myBulkIdProcessor.getResourcePidIterator(providerParams);
			List<Id> idsToSubmit = new ArrayList<>();
			while (pidIterator.hasNext()) {
				ResourcePersistentId pid = pidIterator.next();

				idsToSubmit.add(Id.getIdFromPID(pid, resourceType));
				if (idsToSubmit.size() > MAX_IDS_TO_BATCH) {
					submitWorkChunk(idsToSubmit, resourceType, params, theDataSink);
					submissionCount++;
					idsToSubmit.clear(); // clear to refile
				}
			}

			// if we have any other Ids left, submit them now
			if (!idsToSubmit.isEmpty()) {
				submitWorkChunk(idsToSubmit, resourceType, params, theDataSink);
				submissionCount++;
				idsToSubmit.clear();
			}
		}

		ourLog.info("Submitted {} groups of ids for processing", submissionCount);
		return RunOutcome.SUCCESS;
	}

	private void submitWorkChunk(List<Id> theIds,
										  String theResourceType,
										  BulkExportJobParameters theParams,
										  IJobDataSink<BulkExportIdList> theDataSink) {
		BulkExportIdList idList = new BulkExportIdList();

		idList.setIds(theIds);
		idList.setJobId(theParams.getJobId());
		idList.setResourceType(theResourceType);

		theDataSink.accept(idList);
	}
}