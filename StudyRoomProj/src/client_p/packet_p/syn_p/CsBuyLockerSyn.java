package client_p.packet_p.syn_p;

import data_p.product_p.LockerData;
import packetBase_p.PacketBase;

public class CsBuyLockerSyn extends PacketBase {

	public String uuid;
	public LockerData locker;

	public CsBuyLockerSyn(String userUUID, LockerData locker) {
		this.uuid = userUUID;
		this.locker = locker;
	}
}
