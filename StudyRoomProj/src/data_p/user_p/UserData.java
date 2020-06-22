package data_p.user_p;

import java.util.ArrayList;
import java.util.UUID;

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
