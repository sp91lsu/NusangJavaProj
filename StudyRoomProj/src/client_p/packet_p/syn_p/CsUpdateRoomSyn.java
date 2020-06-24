package client_p.packet_p.syn_p;

import data_p.product_p.room_p.RoomProduct;
import packetBase_p.PacketBase;
	
public class CsUpdateRoomSyn extends PacketBase {
	
	RoomProduct product;
	String uuid;
	
	 public CsUpdateRoomSyn(RoomProduct product, String uuid) {
		 this.product=product;
		 this.uuid=uuid;
	}
}
