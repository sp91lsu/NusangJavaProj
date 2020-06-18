package packetBase_p;


public enum EResult {

	SUCCESS(1), NOT_FOUND_DATA(-202);

	int value;

	EResult(int value) {
		this.value = value;
	}
}
