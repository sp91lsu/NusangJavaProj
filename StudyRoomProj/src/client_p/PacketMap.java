package client_p;

import java.util.HashMap;

import client_p.ui_p.BaseFrame;
import packetBase_p.ResultPacketBase;
import server_p.packet_p.ack_p.*;

public class PacketMap {

	private static PacketMap instance;
	public HashMap<Class, Receivable> map;

	public static PacketMap getInstance() {
		if (instance == null) {

			instance = new PacketMap();

		}

		return instance;
	}

	PacketMap() {
		map = new HashMap<Class, Receivable>();
		map.put(ScLoginAck.class, (Receivable)BaseFrame.getInstance().jPanelArrl.get(0)); // �α��� ����
		map.put(ScSignInUpAck.class, (Receivable)BaseFrame.getInstance().paymentKKK); // ȸ������
		map.put(ScBuyRoomAck.class, new ReceiveVerifyAck()); // ȸ������
		map.put(ScChatConnectAck.class, new ReceiveChatConnectAck()); // ä�� ���� ��û�� ���� ����

	}

	void receivePacket(PacketProccess pClient, ResultPacketBase packet) {

		System.out.println("CLIENT RECEIVE : " + packet.getClass());
		System.out.println("RESULT : " + packet.eResult + "\n");
		System.out.println(BaseFrame.getInstance().paymentKKK);
		map.get(packet.getClass()).receive(packet);
	}

}
