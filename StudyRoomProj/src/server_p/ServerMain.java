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
import java.util.UUID;

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

	ArrayList<PacketClient> clientList = new ArrayList<PacketClient>();

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

					PacketClient pClient = new PacketClient(client, UUID.randomUUID());

					pClient.start();
					clientList.add(pClient);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class PacketClient extends Thread {

	UUID uuid;
	PacketMap pMap = new PacketMap();
	InputStream is;
	ObjectInputStream dis;
	OutputStream os;
	ObjectOutputStream dos;
	Socket socket;

	PacketClient(Socket socket, UUID uuid) {

		this.uuid = uuid;
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

		while (true) {

			try {
				sleep(1000);
				if (is.available() > 0) {
					System.out.println("데이터 들어옴");
					pMap.receivePacket(this, (PacketBase) dis.readObject());
				}
			} catch (Exception e) {
				System.out.println("서버에서 패킷 받는 도중 오류 " + e);
			} finally {
			}
		}
	}

	void sendPacket(PacketBase packet) {
		try {
//			System.out.println(socket.isConnected());
//			System.out.println(socket.isClosed());
			System.out.println("SERVER SEND: " + packet.getClass());
			dos.writeObject(packet);
			dos.flush();
			dos.reset();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}
}
