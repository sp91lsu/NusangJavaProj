package server_p.packet_p.ack_p;

import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScCloseAck extends ResultPacketBase {

	public ScCloseAck(EResult eResult) {
		super(eResult);
	}
}
