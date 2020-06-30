package server_p.packet_p.ack_p;

import java.util.ArrayList;

import data_p.product_p.room_p.RoomTimeData;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class SmResvRoomAck  extends ResultPacketBase{
	public ArrayList<RoomTimeData> rtd;

	public SmResvRoomAck(EResult eResult, ArrayList<RoomTimeData> rtd) {
		super(eResult);
		this.rtd = rtd;
	}
}
