package client_p;

import java.util.HashMap;

import client_p.ui_p.BaseFrame;
import data_p.product_p.DataManager;
import manager_p.ManagerWindow;
import packetBase_p.ResultPacketBase;
import server_p.packet_p.ack_p.*;
import server_p.packet_p.broadCast.ScGetRoomDataCast;
import server_p.packet_p.broadCast.ScRoomInfoBroadCast;

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
		map.put(ScGetRoomDataCast.class, DataManager.getInstance());
	}

	void receivePacket(PacketProccess pClient, ResultPacketBase packet) {
		System.out.println("CLIENT RECEIVE : " + packet.getClass().getSimpleName());
		System.out.println("RESULT : " + packet.eResult + "\n");
		if (map.get(packet.getClass()) != null) {
			map.get(packet.getClass()).receive(packet);
		} else {
			System.out.println("등록되지 않은 패킷 : " + packet.getClass().getSimpleName());
		}

	}
}