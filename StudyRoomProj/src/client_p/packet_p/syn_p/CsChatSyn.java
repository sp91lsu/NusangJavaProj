package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

//�����ڿ��� ä�� ��û 
public class CsChatSyn extends PacketBase {

	public String text="";
	public boolean isEnd=false;

	public CsChatSyn() {
		super();
		isEnd = false;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void end() {
		isEnd = true;
	}
}
