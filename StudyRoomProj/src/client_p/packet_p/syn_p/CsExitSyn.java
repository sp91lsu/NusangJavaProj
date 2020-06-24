package client_p.packet_p.syn_p;

import data_p.product_p.room_p.RoomProduct;
import packetBase_p.PacketBase;

public class CsExitSyn extends PacketBase {

	public RoomProduct room;

	public CsExitSyn(RoomProduct room) {
		this.room = room;
	}
}
