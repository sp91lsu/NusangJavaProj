package client_p;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

import client_p.packet_p.syn_p.CsSignUpSyn;
import packetBase_p.PacketBase;

public class ClientMain {

	public static void main(String[] args) {
		MyServer.getInstance().start();
	}
}

class MyServer extends Thread {

	private static MyServer instance;

	public static MyServer getInstance() {
		if (instance == null) {
			instance = new MyServer();
		}
		return instance;
	}

	Socket socket;
	PacketProccess packetProccess;

	@Override
	public void run() {

		System.out.println("서버접속 시도");

		try {
			socket = new Socket("192.168.100.36", 7777);
			packetProccess = new PacketProccess(socket);
			packetProccess.start();

			sleep(3000);

			new TestThread().start();
			System.out.println("서버접속 성공");
			System.out.println("listener Thread Start");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void sendPacket(PacketBase packet) {
		packetProccess.sendPacket(packet);
	}
}

class TestThread extends Thread {
	@Override
	public void run() {
		try {
			sleep(1000);
			CsSignUpSyn packet = new CsSignUpSyn("이승환", "tmdghks", "4521", "940928", "010-2495-7784", "tmdghks123");
			MyServer.getInstance().sendPacket(packet);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class PacketProccess extends Thread {

	UUID uuid;

	Socket socket;
	OutputStream os;
	ObjectOutputStream dos;

	InputStream is;
	ObjectInputStream ois;
	PacketMap pMap = new PacketMap();

	PacketProccess(Socket socket) {
		try {
			this.socket = socket;
			os = socket.getOutputStream();
			dos = new ObjectOutputStream(os);
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {

			while (!socket.isClosed() && socket.isConnected()) {

				sleep(1000);
				if (is.available() > 0) {
					pMap.receivePacket(this, (PacketBase) ois.readObject());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 클라이언트 패킷 전송
	void sendPacket(PacketBase packet) {
		try {
			if (socket.isClosed()) {
				System.out.println("소켓이 닫혀있음");
				return;
			}
			if (!socket.isConnected()) {
				System.out.println("소켓 연결이 끊김");
				return;
			}

			System.out.println("CLIENT SEND : " + packet.getClass());
			dos.writeObject(packet);
			dos.flush();
			dos.reset();
		} catch (IOException e) {
			System.out.println("알수 없는 에러");
			e.printStackTrace();
		}
	}
}
