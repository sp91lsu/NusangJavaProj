package server_p.packet_p.broadCast;

import java.util.ArrayList;

import data_p.product_p.LockerData;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScBuyLockerCast extends ResultPacketBase {

	public ArrayList<LockerData> lockerList;

	public ScBuyLockerCast(EResult eResult, ArrayList<LockerData> lockerDList) {
		super(eResult);

		this.lockerList = lockerDList;
	}

}
