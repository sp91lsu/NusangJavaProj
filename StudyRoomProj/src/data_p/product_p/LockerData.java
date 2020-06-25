package data_p.product_p;

public class LockerData extends ProductData {

	String pw;

	public LockerData(Integer id, String name, long price) {
		super(id, name, price);

	}

	public void setPW(String pw) {
		this.pw = pw;
	}
}
