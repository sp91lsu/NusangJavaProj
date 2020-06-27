package server_p.packet_p.ack_p;

import java.util.ArrayList;

import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScExitAck extends ResultPacketBase {

	public ArrayList<RoomProduct> reserListAll;
	public ArrayList<RoomProduct> myReserList;

	public ScExitAck(EResult eResult, ArrayList<RoomProduct> reserListAll, ArrayList<RoomProduct> myReserList) {
		super(eResult);
		this.reserListAll = reserListAll;
		this.myReserList = myReserList;
	}

}
