package data_p.user_p;

import java.util.ArrayList;
import java.util.UUID;

import data_p.PacketData;
import data_p.product_p.room_p.RoomProduct;

public class UserData extends PacketData {

	public String uuid;
	public String name; // 이름
	public String id; // 아이디
	public String pw; // 비번
	public String phone; // 폰
	public String birth; // 생일
	public String cType; // 로그인 타입 (일반,관리자(데이터베이스에 있는 키값을 보내야 함))

	public ArrayList<RoomProduct> myReservationList;

	//
	public UserData(String uuid, String name, String id, String pw, String phone, String birth, String cType) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.birth = birth;
		this.cType = cType;
	}

	public UserData(String uuid, String name, String id, String phone, String birth) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.id = id;
		this.phone = phone;
		this.birth = birth;
	}

	public void setMyRoom(ArrayList<RoomProduct> myReservationList) {
		this.myReservationList = myReservationList;
	}
}
