package client_p.packet_p.syn_p;

import java.net.InetAddress;

import packetBase_p.PacketBase;

//�����ڿ��� ä�� ��û 
public class CsChatSyn extends PacketBase {

	public String text;
	public InetAddress address;

	CsChatSyn(String text, InetAddress address) {
		this.text = text;
		this.address = address;
	}

}
