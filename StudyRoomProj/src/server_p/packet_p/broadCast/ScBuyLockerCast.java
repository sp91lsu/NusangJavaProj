package server_p.packet_p.broadCast;

import java.util.ArrayList;

import data_p.product_p.LockerData;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScBuyLockerCast extends ResultPacketBase {

	public ArrayList<Integer> idList;

	public ScBuyLockerCast(EResult eResult, ArrayList<Integer> idList) {
		super(eResult);

		this.idList = idList;
	}

}
