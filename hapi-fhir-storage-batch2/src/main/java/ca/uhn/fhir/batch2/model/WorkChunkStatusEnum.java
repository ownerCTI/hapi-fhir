package ca.uhn.fhir.batch2.model;

public enum WorkChunkStatusEnum {
	QUEUED, IN_PROGRESS, ERRORED, FAILED, COMPLETED;

	public boolean isIncomplete() {
		return switch (this) {
			case COMPLETED -> false;
			default -> true;
		};
	}
}