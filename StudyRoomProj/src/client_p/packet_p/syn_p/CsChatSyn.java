package client_p.packet_p.syn_p;

import java.net.InetAddress;

import packetBase_p.PacketBase;

//관리자에게 채팅 요청 
public class CsChatSyn extends PacketBase {

	public String text;
	public InetAddress address;

	CsChatSyn(String text, InetAddress address) {
		this.text = text;
		this.address = address;
	}

}
