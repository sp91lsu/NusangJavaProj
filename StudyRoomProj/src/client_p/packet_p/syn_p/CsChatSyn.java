package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

//�����ڿ��� ä�� ��û 
public class CsChatSyn extends PacketBase {

	String text;

	CsChatSyn(String text) {
		this.text = text;
	}

}
