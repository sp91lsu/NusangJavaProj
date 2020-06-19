package client_p;

import client_p.ui_p.BaseFrame;

public class ClientMain {

	public static void main(String[] args) {
		ClientNet.getInstance().start();

		BaseFrame.getInstance();
	}
}
