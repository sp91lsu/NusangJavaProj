package client_p;

import java.util.HashMap;

import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.ack_p.ScSignInUpAck;

public class PacketMap {

	HashMap<Class, Receivable> map = new HashMap<Class, Receivable>();

	PacketMap() {

		map.put(ScLoginAck.class, new ReceiveLoginAck());
		map.put(ScSignInUpAck.class, new ReceiveSignUpAck());
	}

	void receivePacket(PacketProccess pClient, PacketBase packet) {

		System.out.println("CLIENT RECEIVE : " + packet.getClass());
		map.get(packet.getClass()).receive(packet);
	}
}
