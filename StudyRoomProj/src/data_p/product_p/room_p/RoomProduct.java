package data_p.product_p.room_p;

import java.util.ArrayList;
import java.util.Calendar;

import data_p.product_p.ProductData;
import data_p.product_p.TimeData;

//�� �ϳ��� �������� �ð� ��ǰ 
public class RoomProduct extends ProductData {

	public Integer personNum;
	public String userUUID;
	public ArrayList<Calendar> calendarList = new ArrayList<Calendar>();;

	public RoomProduct(int id, String name, long price, int personNum) {
		super(id, name, price);
		this.personNum = personNum;
	}

	// ��¥ �Է�
	public void setDate(String uuid, ArrayList<Calendar> calendarList) {

		userUUID = uuid;
		for (Calendar cal : calendarList) {
			System.out.println(cal.getTime());
		}
		this.calendarList = calendarList;
	}

}
