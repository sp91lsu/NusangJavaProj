package data_p.product_p.room_p;


import data_p.product_p.ProductData;

public class RoomProduct extends ProductData {

	public boolean isEmpty;

	public RoomProduct(String id, String name, long price) {
		super(id, name, price);
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
}
