package client_p;

import data_p.product_p.DataManager;
import packetBase_p.ELoginType;
import server_p.packet_p.ack_p.ScGetRoomDataAck;

public class MobileMain {
	public static void main(String[] args) {
		DataManager.getInstance().setLoginType(ELoginType.MOBILE);
		PacketMap.getInstance().map.put(ScGetRoomDataAck.class, DataManager.getInstance());
		ClientNet.getInstance().start();
	}
}