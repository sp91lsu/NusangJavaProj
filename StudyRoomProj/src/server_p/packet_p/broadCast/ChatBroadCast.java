package server_p.packet_p.broadCast;

import java.util.UUID;

import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ChatBroadCast extends ResultPacketBase {

	public ChatBroadCast(UUID clientUUID, EResult eResult) {
		super(clientUUID, eResult);
	}

}
