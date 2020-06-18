package client_p.packet_p.syn_p;

import data_p.product_p.ProductData;
import data_p.user_p.UserData;
import packetBase_p.PacketBase;

//결제 : 상품을 담아서 결제요청 
public class CsVerifySyn extends PacketBase {

	public ProductData product;
	public UserData userData;

	public CsVerifySyn(ProductData product, UserData userData) {
		super();
		this.product = product;
		this.userData = userData;
	}
}
