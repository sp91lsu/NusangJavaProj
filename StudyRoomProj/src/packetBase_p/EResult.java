package packetBase_p;

public enum EResult {

	SUCCESS(1), // ����
	FAIL(-202), // ����
	NOT_FOUND_DATA(-1000), // ������ ã�� �� ����
	DUPLICATEED_ID(-1001), // �ߺ� �� ���̵�
	NEGATIVE_CHAT(-1002), // �Ŵ��� ���� �ź�
	ALEADY_EXIST_DATA(-1003), // �̹� ���� �ڸ�
	DISCONNECT_CHAT(-1004), // ä�ÿ��� ����
	ALREADY_OTHER_MANAGER_CONNECT(-1005); //�̹� �ٸ� �����ڿ� ����
	int value;

	EResult(int value) {
		this.value = value;
	}
}
