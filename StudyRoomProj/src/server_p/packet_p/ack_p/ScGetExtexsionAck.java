package server_p.packet_p.ack_p;

import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScGetExtexsionAck extends ResultPacketBase {

	public int value;

	public ScGetExtexsionAck(EResult eResult, int value) {
		super(eResult);

		this.value = value;
		// TODO Auto-generated constructor stub
	}

}
