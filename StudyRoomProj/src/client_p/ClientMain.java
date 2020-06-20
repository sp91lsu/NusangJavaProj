package client_p;

import client_p.ui_p.BaseFrame;
import data_p.product_p.DataManager;

public class ClientMain {

	public static void main(String[] args) {
		ClientNet.getInstance().start();
		DataManager.getInstance();
		BaseFrame.getInstance();
	}
}
