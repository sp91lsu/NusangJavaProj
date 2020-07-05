package packetBase_p;

public enum EResult {

	SUCCESS(1), // 성공
	FAIL(-202), // 실패
	NOT_FOUND_DATA(-1000), // 데이터 찾을 수 없음
	DUPLICATEED_ID(-1001), // 중복 된 아이디
	NEGATIVE_CHAT(-1002), // 매니저 연결 거부
	ALEADY_EXIST_DATA(-1003), // 이미 앉은 자리
	DISCONNECT_CHAT(-1004), // 채팅연결 끊김
	ALREADY_OTHER_MANAGER_CONNECT(-1005); //이미 다른 관리자와 연결
	int value;

	EResult(int value) {
		this.value = value;
	}
}
