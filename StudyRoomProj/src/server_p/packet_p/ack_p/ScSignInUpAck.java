package server_p.packet_p.ack_p;

import java.util.UUID;

import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScSignInUpAck extends ResultPacketBase {

	public ScSignInUpAck(UUID clientUUID, EResult eResult) {
		super(clientUUID, eResult);
	}

	
	
}
