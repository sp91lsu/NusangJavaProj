package data_p.product_p.room_p;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.sun.org.apache.xpath.internal.operations.Bool;

import data_p.product_p.ProductData;
import data_p.product_p.TimeData;

//�� �ϳ��� �������� �ð� ��ǰ 
public class RoomProduct extends ProductData {

	public boolean isExit;
	public Integer personNum;
	public String userUUID;
	public String pUid; //��ǰ ������� ���ִ� �� 
	public ArrayList<Calendar> calendarList = new ArrayList<Calendar>();
	public HashMap<Calendar, Bool> exitMap = new HashMap<Calendar, Bool>();

	public RoomProduct(int id, String name, long price, int personNum) {
		super(id, name, price);
		this.personNum = personNum;
		isExit = false;
	}

	// ��¥ �Է�
	public void setInfo(String uuid, ArrayList<Calendar> calendarList) {

		userUUID = uuid;
		for (Calendar cal : calendarList) {
			System.out.println(cal.getTime());
		}
		this.calendarList = calendarList;
	}

	public void setExit() {
		isExit = true;
	}

	public RoomProduct getClone() {
		RoomProduct clone = new RoomProduct(this.id, this.name, this.price, this.personNum);
		clone.userUUID = userUUID;
		clone.isExit = isExit;
		clone.pUid = pUid;
		return clone;
	}

	public boolean compareID(int id) {
		return this.id.equals(id);
	}
}
