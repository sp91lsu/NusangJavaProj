package client_p;

import java.util.HashMap;

import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.*;

public class PacketMap {

	HashMap<Class, Receivable> map = new HashMap<Class, Receivable>();

	PacketMap() {

		map.put(ScLoginAck.class, new ReceiveLoginAck()); // �α��� ����
		map.put(ScSignInUpAck.class, new ReceiveSignUpAck()); // ȸ������
		
		map.put(ScChatConnectAck.class, new ReceiveSignUpAck()); // ä�� ���� ��û�� ���� ���� 
	}

	void receivePacket(PacketProccess pClient, PacketBase packet) {

		System.out.println("CLIENT RECEIVE : " + packet.getClass());
		map.get(packet.getClass()).receive(packet);
	}
}
