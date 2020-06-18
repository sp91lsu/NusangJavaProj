package data_p.product_p;

import java.io.Serializable;

import data_p.PacketData;

public class ProductData extends PacketData{

	public String id;
	public String name;
	public long price;

	public ProductData(String id, String name, long price) {
		super();
		this.name = name;
		this.price = price;
	}

}
