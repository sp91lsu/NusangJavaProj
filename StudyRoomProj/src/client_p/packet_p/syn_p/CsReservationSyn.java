package client_p.packet_p.syn_p;

import java.util.ArrayList;
import java.util.Calendar;

import data_p.product_p.ProductData;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.PacketBase;

//¿¹¾à
public class CsReservationSyn extends PacketBase {

	public ArrayList<RoomProduct> roomList;

	public String uuid;

	public CsReservationSyn(String uuid, ArrayList<RoomProduct> roomList) {

		this.uuid = uuid;
		this.roomList = roomList;
	}
}
