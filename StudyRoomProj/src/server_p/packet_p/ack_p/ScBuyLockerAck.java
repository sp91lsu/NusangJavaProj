package server_p.packet_p.ack_p;

import java.util.ArrayList;

import data_p.product_p.LockerData;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScBuyLockerAck extends ResultPacketBase {

	ArrayList<LockerData> lockerData = new ArrayList<LockerData>();

	public ScBuyLockerAck(EResult eResult, ArrayList<LockerData> lockerData) {
		super(eResult);
		// TODO Auto-generated constructor stub
		this.lockerData = lockerData;
	}

}
