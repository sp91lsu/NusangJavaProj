package client_p.packet_p.syn_p;

import java.net.InetAddress;

import packetBase_p.PacketBase;

//�����ڿ��� ä�� ��û 
public class CsChatSyn extends PacketBase {

	public String text;
	public String cip;
	public String mip;

	public CsChatSyn(String text, String cip, String mip) {
		super();
		this.text = text;
		this.cip = cip;
		this.mip = mip;
	}

}
