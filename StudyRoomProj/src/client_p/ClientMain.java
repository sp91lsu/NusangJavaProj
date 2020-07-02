package client_p;

import data_p.product_p.DataManager;
import server_p.packet_p.ack_p.ScGetRoomDataAck;

public class ClientMain {
	public static void main(String[] args) {
		DataManager.getInstance();
		PacketMap.getInstance().map.put(ScGetRoomDataAck.class, DataManager.getInstance());
		ClientNet.getInstance().start();
	}
}