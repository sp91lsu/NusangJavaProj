package client_p.packet_p.syn_p;

import data_p.product_p.ProductData;
import data_p.user_p.UserData;
import packetBase_p.PacketBase;

//���� : ��ǰ�� ��Ƽ� ������û 
public class CsBuySyn extends PacketBase {

	public ProductData product;
	public String uuid;

	public CsBuySyn(ProductData product, String uuid) {
		super();
		this.product = product;
		this.uuid = uuid;
	}
}
