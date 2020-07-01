package server_p;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import packetBase_p.*;

public class ServerMain {

	public static void main(String[] args) {
		MyServer.getInstance().startThread();
	}
}

class MyServer {

	private static MyServer instance;

	public static MyServer getInstance() {

		if (instance == null) {
			instance = new MyServer();
		}
		return instance;
	}

	// Ŭ���̾�Ʈ ����Ʈ
	ArrayList<SocketClient> clientList = new ArrayList<SocketClient>();

	// Ŭ���̾�Ʈ ����Ʈ ������ ��Ŷ �޾Ƽ� ó���ϴ� Ŭ����
	// Ŭ���̾�Ʈ ���� ���� �޴� Ŭ����
	SocketListener listener = new SocketListener();

	void startThread() {
		listener.start();
		Collections.synchronizedList(clientList);
	}

	class SocketListener extends Thread {
		ServerSocket server = null;

		@Override
		public void run() {
			try {
				server = new ServerSocket(7777);

				while (true) {

					System.out.println("Ŭ���̾�Ʈ ���� ���");
					Socket client = server.accept(); // Ŭ���̾�Ʈ ����
					System.out.println("���� Ŭ���̾�Ʈ list ����" + MyServer.getInstance().clientList.size());
					// duplicateClientChk(client);
					System.out.println(client.getInetAddress() + "����");

					SocketClient pClient = new SocketClient(client);

					pClient.start();
					clientList.add(pClient);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	synchronized void duplicateClientChk(Socket client) {
		for (SocketClient socketClient : clientList) {
			if (socketClient.socket.getInetAddress().toString().equals(client.getInetAddress().toString())) {
				socketClient.close();
			}
		}
	}

	public synchronized SocketClient findClient(String address) {
		for (SocketClient packetClient : clientList) {

			System.out.println("������ ������ ã��");
			System.out.println(packetClient.socket.getInetAddress().toString());
			if (packetClient.socket.getInetAddress().toString().equals(address)) {
				System.out.println("ã�Ѵ�!");

				return packetClient;
			}
		}
		return null;
	}

	// ��ü�˸�
	public synchronized void broadCast(SocketClient client, PacketBase packet) {
		for (SocketClient socketClient : clientList) {
			if (socketClient != client) {
				socketClient.sendPacket(packet);
			}
		}
	}

	// p2p �˸� (ä�� ���)
	public synchronized void broadCastP2P(SocketClient client1, SocketClient client2, PacketBase packet) {
		if (client1 != null) {
			client1.sendPacket(packet);
		}
		if (client1 != null) {
			client2.sendPacket(packet);
		}
	}
}

class SocketClient extends Thread {

	PacketMap pMap = new PacketMap();
	InputStream is;
	ObjectInputStream dis;
	OutputStream os;
	ObjectOutputStream dos;
	Socket socket;

	boolean isChat = false;

	SocketClient(Socket socket) {

		this.socket = socket;
		try {
			is = socket.getInputStream();
			dis = new ObjectInputStream(is);
			os = socket.getOutputStream();
			dos = new ObjectOutputStream(os);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Ŭ���̾�Ʈ ����Ʈ ������ �Լ� ����
	public void run() {
		while (socket.isConnected() && !socket.isClosed()) {

			try {
				if (is.available() > 0) {

					System.out.println("������ ����");
					pMap.receivePacket(this, (PacketBase) dis.readObject());
				}
				sleep(10);
			} catch (Exception e) {
				System.out.println("Ŭ���̾�Ʈ���� ��Ŷ �޴� ���� ����");
				close();
				e.printStackTrace();
				return;
			}
		}
		close();
	}

	void sendPacket(PacketBase packet) {
		try {
			System.out.println("SERVER SEND: " + packet.getClass());
			dos.writeObject(packet);
			dos.flush();
			dos.reset();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	public void close() {

		if (MyServer.getInstance().clientList.contains(this)) {
			MyServer.getInstance().clientList.remove(this);
		}
		if (!socket.isClosed()) {
			System.out.println(socket.getInetAddress() + "����");
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
