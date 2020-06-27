package data_p.user_p;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import client_p.ui_p.BaseFrame;
import data_p.PacketData;
import data_p.product_p.room_p.RoomProduct;

public class UserData extends PacketData {

	public String uuid;
	public String name; // �̸�
	public String id; // ���̵�
	public String pw; // ���
	public String phone; // ��
	public String birth; // ����
	public String cType; // �α��� Ÿ�� (�Ϲ�,������(�����ͺ��̽��� �ִ� Ű���� ������ ��))
	public boolean isExit;

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

	public UserData(String uuid, String name, String id, String phone, String birth, boolean isExit) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.id = id;
		this.phone = phone;
		this.birth = birth;
		this.isExit = isExit;
	}

	public void setMyRoom(ArrayList<RoomProduct> myReservationList) {
		this.myReservationList = myReservationList;
	}

	public RoomProduct getTodayRoom() {
		for (RoomProduct room : myReservationList) {
			if (room != null && !isExit) {// ���� �� ������ ����Ʈ
				for (Calendar cal : room.calendarList) {
					if (BaseFrame.getInstance().isSameTime(Calendar.DATE, cal, Calendar.getInstance())) {
						return room;
					}
				}
			}
		}
		return null;
	}

	public RoomProduct getTodayLast() {
		Calendar current = Calendar.getInstance();
		Calendar last = null;
		RoomProduct room = getTodayRoom().getClone();
		if (room != null) {
			for (Calendar cal : room.calendarList) {
				if (BaseFrame.getInstance().isSameTime(Calendar.DATE, cal, current)) {
					if (last == null) {
						last = cal;
					}
					if (last.getTimeInMillis() < cal.getTimeInMillis()) {
						last = cal;
					}
				}
			}
		}

		room.calendarList = new ArrayList<Calendar>();
		room.calendarList.add(last);
		return room;
	}
}
