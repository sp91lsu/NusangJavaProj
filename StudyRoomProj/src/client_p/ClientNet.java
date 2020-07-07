package client_p;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

import client_p.packet_p.syn_p.CsCloseSyn;
import client_p.packet_p.syn_p.CsGetRoomDataSyn;
import client_p.ui_p.BaseFrame;
import data_p.user_p.UserData;
import packetBase_p.PacketBase;
import packetBase_p.ResultPacketBase;

public class ClientNet extends Thread {

	private static ClientNet instance;

	public static ClientNet getInstance() {
		if (instance == null) {
			instance = new ClientNet();

		}
		return instance;
	}

	public Socket socket;
	PacketProcess packetProcess;

	@Override
	public void run() {

		System.out.println("�������� �õ�");

		try {
//			socket = new Socket("127.0.0.1", 7777);///�ڱ�ip
			socket = new Socket("192.168.0.68", 7777); // ��ȯ��
			packetProcess = new PacketProcess(socket);
			packetProcess.start();


			System.out.println("�������� ����");
			System.out.println("listener Thread Start");

			CsGetRoomDataSyn packet = new CsGetRoomDataSyn();
			sendPacket(packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendPacket(PacketBase packet) {
		packetProcess.sendPacket(packet);
	}
}

class PacketProcess extends Thread {

	UUID uuid;

	Socket socket;
	OutputStream os;
	ObjectOutputStream dos;

	InputStream is;
	ObjectInputStream ois;

	PacketProcess(Socket socket) {
		try {
			this.socket = socket;
			os = socket.getOutputStream();
			dos = new ObjectOutputStream(os);
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Runtime rt = Runtime.getRuntime();

		rt.addShutdownHook(new Thread() {
			public void run() {
				try {
					close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public void run() {
		try {

			while (!socket.isClosed() && socket.isConnected()) {

				if (is.available() > 0) {

					PacketMap.getInstance().receivePacket(this, (ResultPacketBase) ois.readObject());
				}
				sleep(10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		close();
	}

	// Ŭ���̾�Ʈ ��Ŷ ����
	void sendPacket(PacketBase packet) {
		try {
			if (socket.isClosed()) {
				System.out.println("������ ��������");
				return;
			}
			if (!socket.isConnected()) {
				System.out.println("���� ������ ����");
				return;
			}

			System.out.println("CLIENT SEND : " + packet.getClass().getSimpleName());
			dos.writeObject(packet);
			dos.flush();
			dos.reset();
		} catch (IOException e) {
			System.out.println("�˼� ���� ����");
			e.printStackTrace();
		}
	}

	public void close() {

		if (!socket.isClosed()) {

			UserData userData = BaseFrame.getInstance().userData;
			String uuid = null;

			if (userData != null) {
				uuid = userData.uuid;
			}

			CsCloseSyn closeSyn = new CsCloseSyn(uuid);
			sendPacket(closeSyn);
			System.out.println("Ŭ���̾�Ʈ ���� ���� ����");
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
