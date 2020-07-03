package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

//�����ڿ��� ä�� ��û 
public class CsChatSyn extends PacketBase {

	public String text;
	public boolean isEnd;
	public String cip;
	public String mip;

	public CsChatSyn(String cip, String mip) {
		super();
		this.cip = cip;
		this.mip = mip;
		isEnd = false;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void end() {
		isEnd = true;
	}
}
