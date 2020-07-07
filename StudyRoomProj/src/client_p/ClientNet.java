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

		System.out.println("서버접속 시도");

		try {
//			socket = new Socket("127.0.0.1", 7777);///자기ip
			socket = new Socket("192.168.0.68", 7777); // 승환이
			packetProcess = new PacketProcess(socket);
			packetProcess.start();


			System.out.println("서버접속 성공");
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

			System.out.println("CLIENT SEND : " + packet.getClass().getSimpleName());
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

			UserData userData = BaseFrame.getInstance().userData;
			String uuid = null;

			if (userData != null) {
				uuid = userData.uuid;
			}

			CsCloseSyn closeSyn = new CsCloseSyn(uuid);
			sendPacket(closeSyn);
			System.out.println("클라이언트 소켓 연결 종료");
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
