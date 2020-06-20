package data_p.product_p;

import java.util.Calendar;

import packetBase_p.PacketBase;

public class TimeData extends PacketBase {

	public int id;
	public long price;
	public int value;
	public int date;
	public TimeData(int id, int value, long price) {
		super();
		this.id = id;
		this.value = value;
		this.price = price;
	}

}
