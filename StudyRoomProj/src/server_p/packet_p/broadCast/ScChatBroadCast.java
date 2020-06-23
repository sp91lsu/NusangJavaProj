package server_p.packet_p.broadCast;

import java.util.UUID;

import packetBase_p.EResult;
import packetBase_p.PacketBase;
import packetBase_p.ResultPacketBase;

public class ScChatBroadCast extends ResultPacketBase {

	String text;

	public ScChatBroadCast(EResult eResult, String text) {
		super(eResult);
		this.text = text;
		// TODO Auto-generated constructor stub
	}

	public String getText() {

		return text;
	}

}
