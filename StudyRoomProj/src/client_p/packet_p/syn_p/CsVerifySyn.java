package client_p.packet_p.syn_p;

import data_p.product_p.ProductData;
import data_p.user_p.UserData;
import packetBase_p.PacketBase;

//���� : ��ǰ�� ��Ƽ� ������û 
public class CsVerifySyn extends PacketBase {

	public ProductData product;
	public String uuid;

	public CsVerifySyn(ProductData product, String uuid) {
		super();
		this.product = product;
		this.uuid = uuid;
	}
}
