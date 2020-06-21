package data_p.product_p.room_p;

import java.util.ArrayList;
import java.util.Calendar;

import data_p.product_p.ProductData;
import data_p.product_p.TimeData;

//�� �ϳ��� �������� �ð� ��ǰ 
public class RoomProduct extends ProductData {

	public Integer personNum;
	public Calendar calendar;

	public ArrayList<TimeData> timeList;

	public RoomProduct(int id, String name, long price, int personNum) {
		super(id, name, price);
	}

	// ��¥ �Է�
	public void setDate(int month, ArrayList<TimeData> timeList) {
		calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, month);

		this.timeList = timeList;
	}

	public RoomProduct clone() {
		try {
			return (RoomProduct) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
