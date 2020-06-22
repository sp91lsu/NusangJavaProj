package data_p.product_p.room_p;

import java.util.ArrayList;
import java.util.Calendar;

import data_p.product_p.ProductData;
import data_p.product_p.TimeData;

//�� �ϳ��� �������� �ð� ��ǰ 
public class RoomProduct extends ProductData {

	public Integer personNum;
	public ArrayList<Calendar> calendarList;

	public RoomProduct(int id, String name, long price, int personNum) {
		super(id, name, price);
	}

	// ��¥ �Է�
	public void setDate(ArrayList<Calendar> calendar) {

//		System.out.println("����ð�" + calendar.getTime());
//		
//		System.out.println("������" + month);
		for (Calendar cal : calendar) {
			System.out.println(cal.getTime());
		}
	}

}
