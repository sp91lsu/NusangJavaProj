package server_p.packet_p.broadCast;

import java.util.UUID;

import packetBase_p.EResult;
import packetBase_p.PacketBase;
import packetBase_p.ResultPacketBase;

public class ChatBroadCast extends PacketBase {

	String text;

	public ChatBroadCast(String text) {
		this.text = text;
	}

}
