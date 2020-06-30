package manager_p;

import client_p.ClientNet;

public class ManagerMain {

	public static void main(String[] args) {
		ClientNet.getInstance().start();
		
	}

}
