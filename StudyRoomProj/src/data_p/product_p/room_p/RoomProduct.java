package data_p.product_p.room_p;

import java.util.ArrayList;
import java.util.Calendar;

import data_p.product_p.ProductData;

//1시간짜리 자리 상품 
public class RoomProduct extends ProductData {

	public int personNum;
	public ArrayList<Calendar> reservationList = new ArrayList<Calendar>();

	public RoomProduct(String id, String name, long price, int personNum) {
		super(id, name, price);
	}

	public void setReservationList(ArrayList<Calendar> reservationList) {
		this.reservationList = reservationList;
	}
}
