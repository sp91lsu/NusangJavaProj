package data_p.sales_p;

import java.io.Serializable;
import java.util.ArrayList;

public class SalesRecord implements Serializable {

	public String dateStr = "";
	public String room_name = "";
	public String room_price_Tot = "";
	public String user_name = "";
	public String user_id = "";
	public String hourListStr = "";
	
	public SalesRecord(String date, String room_name, String room_price_Tot, String user_name, String user_id,
			String hourListStr) {
		super();
		this.dateStr = date;
		this.room_name = room_name;
		this.room_price_Tot = room_price_Tot;
		this.user_name = user_name;
		this.user_id = user_id;
		this.hourListStr = hourListStr;
		
	}
	
}
