package manager_p;

import client_p.ClientNet;
import client_p.ui_p.BaseFrame;

public class ManagerMain {

	public static void main(String[] args) {
		ClientNet.getInstance().start();
		new managerLogin();
	}

}
