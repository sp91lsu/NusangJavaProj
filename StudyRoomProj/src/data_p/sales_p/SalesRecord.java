package data_p.sales_p;

import java.io.Serializable;
import java.util.ArrayList;

public class SalesRecord implements Serializable {

	public String dateStr = "";
	public String room_name = "";
	public int room_price_Tot = 0;
	public String user_name = "";
	public String user_id = "";
	public ArrayList<String> hourList = new ArrayList<String>();
	
	public SalesRecord(String date, String room_name, int room_price, String user_name, String user_id,
			ArrayList<String> hourList) {
		super();
		this.dateStr = date;
		this.room_name = room_name;
		this.user_name = user_name;
		this.user_id = user_id;
		this.hourList = hourList;
		
		room_price_Tot = room_price * hourList.size();
	}
}
