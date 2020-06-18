package data_p.product_p;

import java.util.ArrayList;

import data_p.product_p.room_p.PrivateRoomProduct;
import data_p.product_p.room_p.PublicRoomProduct;
import data_p.product_p.room_p.RoomProduct;

public class ProductMain {

	ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();

	ProductMain() {

	}

	void RoomSetting() {
		for (int i = 0; i < 20; i++) {
			roomList.add(new PrivateRoomProduct());
		}
		for (int i = 0; i < 20; i++) {
			roomList.add(new PublicRoomProduct());
		}
		
		
	}
}
