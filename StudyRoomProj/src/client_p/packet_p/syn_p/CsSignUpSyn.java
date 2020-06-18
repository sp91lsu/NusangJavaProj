package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

public class CsSignUpSyn extends PacketBase {

	public String name; // 이름
	public String id; // 아이디
	public String pw; // 비번
	public String phone; // 폰
	public String birth; // 생일
	public String cType; // 로그인 타입 (일반,관리자(데이터베이스에 있는 키값을 보내야 함))

	public CsSignUpSyn(String name, String id, String pw, String phone, String birth, String cType) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.birth = birth;
		this.cType = cType;
	}

}
