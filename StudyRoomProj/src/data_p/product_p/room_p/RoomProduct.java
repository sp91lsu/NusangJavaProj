package data_p.product_p.room_p;

import java.util.ArrayList;
import java.util.Calendar;

import data_p.product_p.ProductData;
import data_p.product_p.TimeData;

//룸 하나의 여러개의 시간 상품 
public class RoomProduct extends ProductData {

	public Integer personNum;
	public ArrayList<Calendar> calendarList;

	public RoomProduct(int id, String name, long price, int personNum) {
		super(id, name, price);
	}

	// 날짜 입력
	public void setDate(ArrayList<Calendar> calendar) {

//		System.out.println("현재시간" + calendar.getTime());
//		
//		System.out.println("넣을값" + month);
		for (Calendar cal : calendar) {
			System.out.println(cal.getTime());
		}
	}

}
