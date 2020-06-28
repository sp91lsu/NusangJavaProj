package data_p.user_p;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import client_p.ui_p.BaseFrame;
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

	public ArrayList<RoomProduct> exitList = new ArrayList<RoomProduct>();
	public ArrayList<RoomProduct> myReservationList = new ArrayList<RoomProduct>();

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

	public void setReserRoom(ArrayList<RoomProduct> myReservationList) {
		this.myReservationList = myReservationList;
	}

	public void setExitRoom(ArrayList<RoomProduct> exitList) {
		this.exitList = exitList;
	}

	public RoomProduct getTodayRoom() {
		for (RoomProduct room : myReservationList) {
			if (room != null) {// 오늘 총 예약한 리스트
				for (Calendar cal : room.calendarList) {
					if (BaseFrame.getInstance().isSameTime(Calendar.DATE, cal, Calendar.getInstance())) {
						return room;
					}
				}
			}
		}
		return null;
	}
}
