package packetBase_p;

public enum EResult {

	SUCCESS(1), // ����
	FAIL(-202), // ����
	NOT_FOUND_DATA(-1000), // ������ ã�� �� ����
	DUPLICATEED_ID(-1001), // �ߺ� �� ���̵�
	NEGATIVE_CHAT(-1002); // �Ŵ��� ���� �ź�

	int value;

	EResult(int value) {
		this.value = value;
	}
}
