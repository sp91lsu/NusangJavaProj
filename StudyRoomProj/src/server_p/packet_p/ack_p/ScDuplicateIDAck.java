package server_p.packet_p.ack_p;

import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScDuplicateIDAck extends ResultPacketBase {

	public boolean is_hp;
	
	public ScDuplicateIDAck(EResult eResult, boolean is_hp) {
		super(eResult);
		this.is_hp=is_hp;
	}
	
}
