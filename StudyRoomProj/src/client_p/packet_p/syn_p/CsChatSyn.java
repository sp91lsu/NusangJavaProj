package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

//관리자에게 채팅 요청 
public class CsChatSyn extends PacketBase {

	public String text;
	public String cip;
	public String mip;

	public CsChatSyn(String cip, String mip) {
		super();
		this.cip = cip;
		this.mip = mip;
	}

	public void setText(String text) {
		this.text = text;
	}
}
