package client_p.ui_p;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client_p.ClientNet;
import client_p.PacketMap;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsUpdateRoomSyn;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;
import packetBase_p.ELoginType;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScBuyRoomAck;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.ack_p.ScSignUpAck;
import server_p.packet_p.broadCast.ScChatBroadCast;
import server_p.packet_p.broadCast.ScRoomInfoBroadCast;

public class BaseFrame extends JFrame implements Receivable {

	public ArrayList<JPanel> jPanelArrl = new ArrayList<JPanel>();
	public ArrayList<RoomProduct> roomInfoList = new ArrayList<RoomProduct>();
	public ELoginType loginType = ELoginType.KIOSK;
	private static BaseFrame instance;

	public static BaseFrame getInstance() {
		if (instance == null) {
			instance = new BaseFrame();
		}

		return instance;
	}

	UserData userData = null;
	public Payment payment = new Payment();
	public SignUpMain signUpFrame = new SignUpMain();
	public PaymentPopFrame paymentPop = new PaymentPopFrame();
	public RCalcFrame rcalc = new RCalcFrame();
	public RoomProduct roomProduct;

	public BaseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame();
	}

	void startFrame() {

//		jjj = new JPanel();
//		jjj.setLayout(new BorderLayout(0,0));
//		jjj.setVisible(true);
		payment.setVisible(false);
		addToBaseFrame(new LoginMain());
		addToBaseFrame(new MainLayout());
		addToBaseFrame(new Seating_Arrangement());
		addToBaseFrame(new LockerMain());
		addToBaseFrame(new ReservationMain());
		addToBaseFrame(new ClientChatFrame());
		setBackground(Color.GRAY);
//		setLayout(null);
		setBounds(100, 100, 900, 1000);
		setVisible(true);
		view("LoginMain");
		PacketMap.getInstance().map.put(ScLoginAck.class, (Receivable) jPanelArrl.get(0)); // �α���
		PacketMap.getInstance().map.put(ScSignUpAck.class, (Receivable) signUpFrame); // ȸ������
		PacketMap.getInstance().map.put(ScBuyRoomAck.class, paymentPop);// ����
		PacketMap.getInstance().map.put(ScRoomInfoBroadCast.class, (Receivable) this);
		PacketMap.getInstance().map.put(ScChatConnectAck.class, (Receivable) jPanelArrl.get(1));
		PacketMap.getInstance().map.put(ScChatBroadCast.class, (Receivable) jPanelArrl.get(5));
		PacketMap.getInstance().map.put(ScRoomInfoBroadCast.class, this);

	}

	void addToBaseFrame(JPanel jp) {
		jPanelArrl.add(jp);
		// add(jp);
	}

	public void view(String jPanelName) {
		getContentPane().removeAll();

		for (JPanel jp : jPanelArrl) {
			if ((jp.getClass().getSimpleName()).equals(jPanelName)) {
				getContentPane().add(jp);
			}
		}
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	@Override
	public void receive(PacketBase packet) {
		ScRoomInfoBroadCast roomInfoCast = (ScRoomInfoBroadCast) packet;
		roomInfoList = roomInfoCast.roomList;
		// ����������
		// ���ϰ���������
	}

	public void updateInfo(ArrayList<RoomProduct> roomList) {
		roomInfoList = roomList;
		payment.updateRoomInfo();
	}

	// ���� �� ���� ������ ���� �ð� �ֱ�
	public void setCurrentRoomInfo(ArrayList<Calendar> calendarList) {
		if (roomProduct != null) {
			roomProduct.setDate(userData.uuid, calendarList);
		} else {
			System.out.println("���� �����Ϸ����ϴ� �� ������ null��");
		}
	}

	// ������ �̹� �Ǿ��ִ� int �� ����Ʈ (payment, reservationMain) ���� ����ϴ� �Լ�
	public ArrayList<Integer> getCheckList(int month, int date) {

		ArrayList<Integer> valueList = new ArrayList<Integer>();

		System.out.println("�������� ���� ������ ");
		// �������� ��� ���ŵǴ� ���� ������
		for (RoomProduct roomInfo : roomInfoList) {

			// ���� �����Ϸ��� �� ������ ������
			if (roomInfo.name.equals(roomProduct.name)) {

				// �� �������� Ķ������ ���ؼ�
				for (Calendar cal : roomInfo.calendarList) {

					if (cal.get(Calendar.MONTH) == month && cal.get(Calendar.DATE) == date)
						for (int i = 1; i <= 24; i++) {
							if (!valueList.contains(i) && cal.get(Calendar.HOUR_OF_DAY) == i) {

								System.out.println(i + "��");
								valueList.add(i);
							}
						}
				}
			}
		}
		return valueList;
	}

	public Seating_Arrangement getSeatingArrUI() {
		return (Seating_Arrangement) jPanelArrl.get(2);
	}

	public ClientChatFrame getClientChatFrame() {
		return (ClientChatFrame) jPanelArrl.get(5);
	}

	public MainLayout openMainLayout() {
		BaseFrame.getInstance().view("MainLayout");
		return (MainLayout) jPanelArrl.get(1);
	}

	public ReservationMain getReservationMain() {
		return (ReservationMain) jPanelArrl.get(4);
	}

	// ���� ����ϰ� �ִ� �� ����
	public RoomProduct getUsingRoom() {
		Calendar current = Calendar.getInstance();
		for (RoomProduct product : BaseFrame.getInstance().userData.myReservationList) {
			for (Calendar cal : product.calendarList) {

				System.out.println(cal.get(Calendar.MONTH));
				System.out.println(current.get(Calendar.MONTH));
				System.out.println(cal.get(Calendar.HOUR));
				System.out.println(current.get(Calendar.HOUR));
				if (cal.get(Calendar.MONTH) == current.get(Calendar.MONTH)
						&& cal.get(Calendar.HOUR) == current.get(Calendar.HOUR)) {
					return product;
				}
			}
		}
		return null;
	}
}

class CheckRoomInfo extends Thread {
	@Override
	public void run() {
		try {
			sleep(60000);
			CsUpdateRoomSyn packet = new CsUpdateRoomSyn(BaseFrame.getInstance().roomProduct,
					BaseFrame.getInstance().userData.uuid);
			ClientNet.getInstance().sendPacket(packet);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}