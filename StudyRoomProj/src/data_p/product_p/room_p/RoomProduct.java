package data_p.product_p.room_p;

import java.util.ArrayList;
import java.util.Calendar;

import data_p.product_p.ProductData;
import data_p.product_p.TimeData;

//룸 하나의 여러개의 시간 상품 
public class RoomProduct extends ProductData {

	public Integer personNum;
	public Calendar calendar;

	public ArrayList<TimeData> timeList = new ArrayList<TimeData>();

	public RoomProduct(int id, String name, long price, int personNum) {
		super(id, name, price);
	}

	// 날짜 입력
	public void setDate(int month, ArrayList<TimeData> timeList) {
		calendar = Calendar.getInstance();

		System.out.println("현재시간" + calendar.getTime());
		
		System.out.println("넣을값" + month);
		
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, 2020);
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH));
		this.timeList = timeList;
	}

}
