package data_p.product_p.room_p;

import java.security.Timestamp;
import java.util.Calendar;

import data_p.product_p.ProductData;

public class RoomProduct extends ProductData {

	public boolean isEmpty;
	public Calendar calendar;

	public RoomProduct(String name, long price) {
		super(name, price);
	}
	
	
}
