package data_p.sales_p;

import java.util.ArrayList;

public class SalesRecord {

	String date = "";
	String room_name = "";
	int room_price = 0;
	String user_name = "";
	String user_id = "";
	ArrayList<String> hourList = new ArrayList<String>();
	
	public SalesRecord(String date, String room_name, int room_price, String user_name, String user_id,
			ArrayList<String> hourList) {
		super();
		this.date = date;
		this.room_name = room_name;
		this.room_price = room_price;
		this.user_name = user_name;
		this.user_id = user_id;
		this.hourList = hourList;
	}
}
