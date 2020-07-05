package client_p.packet_p.syn_p;

import data_p.product_p.DataManager;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.PacketBase;

//���� : ��ǰ�� ��Ƽ� ������û 
public class CsBuyRoomSyn extends PacketBase {

	public RoomProduct RoomProduct;
	public String uuid;

	public CsBuyRoomSyn(RoomProduct product, String uuid, long price) {
		super();

		product.price = price;
		this.RoomProduct = product;
		this.uuid = uuid;
	}
}
