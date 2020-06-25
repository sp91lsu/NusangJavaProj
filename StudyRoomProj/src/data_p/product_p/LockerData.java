package data_p.product_p;

public class LockerData extends ProductData {

	String pw;

	public LockerData(Integer id, String name, String pw, long price) {
		super(id, name, price);
		this.pw = pw;
	}

}
