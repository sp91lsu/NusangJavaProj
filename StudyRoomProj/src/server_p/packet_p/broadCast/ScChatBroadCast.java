package server_p.packet_p.broadCast;

import java.util.UUID;

import packetBase_p.EResult;
import packetBase_p.PacketBase;
import packetBase_p.ResultPacketBase;

public class ScChatBroadCast extends ResultPacketBase {

	String text="";
	public boolean isEnd;

	public ScChatBroadCast(EResult eResult, String text, boolean isEnd) {
		super(eResult);
		this.text = text;
		this.isEnd = isEnd;
	}

	public String getText() {

		return text;
	}
}
