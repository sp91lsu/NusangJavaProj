package data_p.product_p;

import java.util.Calendar;

import packetBase_p.PacketBase;

public class TimeData extends PacketBase {

	public int id;
	public int start;
	public int end;
	public long price;
	
	public TimeData(int id, int start, int end, long price) {
		super();
		this.id = id;
		this.start = start;
		this.price = price;
	}
	
	
}
