package data_p.product_p.room_p;

import java.util.ArrayList;
import java.util.Calendar;

import data_p.product_p.ProductData;
import data_p.product_p.TimeData;

//�� �ϳ��� �������� �ð� ��ǰ 
public class RoomProduct extends ProductData {

	public int personNum;
	public Calendar calendar;
	
	public ArrayList<TimeData> timeList;

	public RoomProduct(String id, String name, long price, int personNum) {
		super(id, name, price);
	}

	// ��¥ �Է�
	public void setDate(int year, int month, int date, ArrayList<TimeData> timeList) {
		calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);

		this.timeList = timeList;
	}
}
