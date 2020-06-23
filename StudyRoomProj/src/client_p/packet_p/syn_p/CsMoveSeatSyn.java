package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

public class CsMoveSeatSyn extends PacketBase {

	public String userUUID;
	public String originSeatID;
	public String moveSeatID;

	CsMoveSeatSyn(String userUUID, String originSeatID, String moveSeatID) {
		this.userUUID = userUUID;
		this.originSeatID = originSeatID;
		this.moveSeatID = moveSeatID;
	}
}
