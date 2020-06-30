package packetBase_p;

public enum EResult {

	SUCCESS(1), // 성공
	FAIL(-202), // 실패
	NOT_FOUND_DATA(-1000), // 데이터 찾을 수 없음
	DUPLICATEED_ID(-1001), // 중복 된 아이디
	NEGATIVE_CHAT(-1002); // 매니저 연결 거부

	int value;

	EResult(int value) {
		this.value = value;
	}
}
