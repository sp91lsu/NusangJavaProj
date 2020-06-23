package client_p;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

import client_p.packet_p.syn_p.CsLoginSyn;
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
	PacketProccess packetProccess;

	@Override
	public void run() {

		System.out.println("서버접속 시도");

		try {
			socket = new Socket("127.0.0.1", 7777);
			packetProccess = new PacketProccess(socket);
			packetProccess.start();

			System.out.println("서버접속 성공");
			System.out.println("listener Thread Start");
//			UserData userdata = new UserData("871d5ded-3306-4a9c-bf61-d98ce1aa73d7", "이승환", "tmdghks", "4521", "940928",
//					"010-2495-7784", "fdjifel");
//
//			
//			CsSignUpSyn signupSyn = new CsSignUpSyn("이승환", "tmdghks", "4521", "010-2495-7784", "940928", "rb4rt6u6gujh8a6f1e564as84se6vsdafflad4g68as8ah64se86g4h86adf4gf8se6t4g86dvs4r86eg48af6ser48we6gf8s6e4f86asd4g86as");

			// CsLoginSyn loginSyn = new CsLoginSyn("tmdghks", "4521", true);

			// sendPacket(loginSyn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendPacket(PacketBase packet) {
		packetProccess.sendPacket(packet);
	}
}

class PacketProccess extends Thread {

	UUID uuid;

	Socket socket;
	OutputStream os;
	ObjectOutputStream dos;

	InputStream is;
	ObjectInputStream ois;

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

				sleep(10);
				if (is.available() > 0) {

					PacketMap.getInstance().receivePacket(this, (ResultPacketBase) ois.readObject());
				}
			}

			close();
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
