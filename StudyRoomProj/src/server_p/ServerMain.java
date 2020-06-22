package server_p;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
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

	// myserver 는 싱글톤
	private static MyServer instance;

	public static MyServer getInstance() {

		if (instance == null) {
			instance = new MyServer();
		}
		return instance;
	}

	// 클라이언트 리스트

	ArrayList<SocketClient> clientList = new ArrayList<SocketClient>();

	// 클라이언트 리스트 돌려서 패킷 받아서 처리하는 클래스
	// 클라이언트 접속 연결 받는 클래스
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

					System.out.println("클라이언트 접속 대기");

					Socket client = server.accept(); // 클라이언트 접속

					System.out.println(client.getInetAddress() + "접속");

					SocketClient pClient = new SocketClient(client);

					pClient.start();
					clientList.add(pClient);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized SocketClient findClient(String address) {
		for (SocketClient packetClient : clientList) {

			System.out.println("관리자 아이피 찾기");
			System.out.println(packetClient.socket.getInetAddress().toString());
			if (packetClient.socket.getInetAddress().toString().equals(address)) {
				System.out.println("찾앗다!");
 
				return packetClient;
			}
		}
		return null;
	}

	// 전체알림
	public synchronized void broadCast(PacketBase packet) {
		for (SocketClient socketClient : clientList) {
			socketClient.sendPacket(packet);
		}
	}

	// p2p 알림 (채팅 기능)
	public synchronized void broadCastP2P(SocketClient client1, SocketClient client2, PacketBase packet) {
		client1.sendPacket(packet);
		client2.sendPacket(packet);
	}
}

class SocketClient extends Thread {

	PacketMap pMap = new PacketMap();
	InputStream is;
	ObjectInputStream dis;
	OutputStream os;
	ObjectOutputStream dos;
	Socket socket;

	public boolean doChatting = false;

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

	// 클라이언트 리스트 돌려서 함수 실행
	public void run() {

		while (socket.isConnected() && !socket.isClosed()) {

			try {
				if (is.available() > 0) {
					System.out.println("데이터 들어옴");
					pMap.receivePacket(this, (PacketBase) dis.readObject());
				}
				sleep(10);
			} catch (Exception e) {
				System.out.println("클라이언트에서 패킷 받는 도중 오류 ");
				e.printStackTrace();
			}
		}

		System.out.println(socket.getInetAddress() + "종료");
		MyServer.getInstance().clientList.remove(this);
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
		if (!socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
