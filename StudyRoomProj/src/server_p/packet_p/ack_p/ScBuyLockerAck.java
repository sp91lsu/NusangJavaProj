package server_p.packet_p.ack_p;

import java.util.ArrayList;

import data_p.product_p.LockerData;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScBuyLockerAck extends ResultPacketBase {

	public ArrayList<LockerData> lockerList = new ArrayList<LockerData>();
	public LockerData myLocker;

	public ScBuyLockerAck(EResult eResult, ArrayList<LockerData> lockerList, LockerData myLocker) {
		super(eResult);
		// TODO Auto-generated constructor stub
		this.lockerList = lockerList;
	}

}
