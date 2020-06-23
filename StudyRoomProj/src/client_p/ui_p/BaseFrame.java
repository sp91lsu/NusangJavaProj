package client_p.ui_p;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client_p.PacketMap;
import client_p.Receivable;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;
import packetBase_p.ELoginType;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScBuyRoomAck;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.ack_p.ScSignUpAck;
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

//	Container jjj;
//	JPanel loginMain = new LoginMain();
//	JPanel mainLayout = new MainLayout();
//	JPanel seating_Arrangement = new Seating_Arrangement();

	public BaseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame();
	}

	void startFrame() {
		PacketMap.getInstance().map.put(ScLoginAck.class, (Receivable) jPanelArrl.get(0)); // �α���
		PacketMap.getInstance().map.put(ScSignUpAck.class, (Receivable) signUpFrame); // ȸ������
		PacketMap.getInstance().map.put(ScBuyRoomAck.class, paymentPop);// ����
		PacketMap.getInstance().map.put(ScRoomInfoBroadCast.class, (Receivable) this);
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
	}

	void addToBaseFrame(JPanel jp) {
		jPanelArrl.add(jp);
		add(jp);
	}

	public void view(String jPanelName) {
		getContentPane().removeAll();

		for (JPanel jp : jPanelArrl) {
//			System.out.println(jp.getClass().getSimpleName());
			if ((jp.getClass().getSimpleName()).equals(jPanelName)) {
				getContentPane().add(jp);
			}
		}
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public static void main(String[] args) {

//		for (int i = 0; i < 6; i++) {
//			try {
//				BaseFrame.getInstance().view("LoginMain");
//				Thread.sleep(1000);
//				BaseFrame.getInstance().view("MainLayout");
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	@Override
	public void receive(PacketBase packet) {

		ScRoomInfoBroadCast roomInfoAck = (ScRoomInfoBroadCast) packet;
		updateInfo(roomInfoAck.roomList);
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
							if (!valueList.contains(i) && cal.get(Calendar.HOUR) == i) {

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
}