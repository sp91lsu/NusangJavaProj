package client_p;

import java.util.HashMap;

import packetBase_p.ResultPacketBase;
import server_p.packet_p.ack_p.*;

public class PacketMap {

	private static PacketMap instance;

	public static PacketMap getInstance() {
		if (instance == null) {
			instance = new PacketMap();
		}

		return instance;
	}

	public HashMap<Class, Receivable> map = new HashMap<Class, Receivable>();

	PacketMap() {

	//	map.put(ScLoginAck.class, new ReceiveLoginAck()); // �α��� ����
		map.put(ScSignInUpAck.class, new ReceiveSignUpAck()); // ȸ������
		map.put(ScBuyRoomAck.class, new ReceiveVerifyAck()); // ȸ������
		map.put(ScChatConnectAck.class, new ReceiveChatConnectAck()); // ä�� ���� ��û�� ���� ����

	}

	void receivePacket(PacketProccess pClient, ResultPacketBase packet) {

		System.out.println("CLIENT RECEIVE : " + packet.getClass());
		System.out.println("RESULT : " + packet.eResult + "\n");
		map.get(packet.getClass()).receive(packet);
	}

}
