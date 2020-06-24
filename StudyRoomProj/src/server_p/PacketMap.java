package server_p;

import java.util.HashMap;

import client_p.packet_p.ack_p.MsChatConnectAck;
import client_p.packet_p.syn_p.*;
import packetBase_p.*;

public class PacketMap {

	HashMap<Class, ServerPacketMethod> map = new HashMap<Class, ServerPacketMethod>();

	SocketClient client;

	PacketMap() {
		map.put(CsLoginSyn.class, new MethLoginSyn()); // �α���
		map.put(CsSignUpSyn.class, new MethSignUpSyn()); // ȸ������
		map.put(CsChatConnectSyn.class, new MethChatConnectSyn()); // ä�ÿ��� ��û

		map.put(MsChatConnectAck.class, new MethMSChatConnectAck());
		map.put(CsChatSyn.class, new MethCsChatSyn());
		map.put(CsBuyRoomSyn.class, new MethBuyRoomSyn()); // ����
		map.put(CsMoveSeatSyn.class, new MethMoveSeatSyn()); // �ڸ��̵�
		map.put(CsCloseSyn.class, new MethCloseSyn()); // ���� ����
		
		map.put(CsUpdateRoomSyn.class, new MethUpdateRoomSyn()); // �� ���� ������Ʈ

	}

	void receivePacket(SocketClient pClient, PacketBase packet) {

		System.out.println("SERVER RECEIVE :" + packet.getClass());
		map.get(packet.getClass()).receive(pClient, packet);
	}

}
