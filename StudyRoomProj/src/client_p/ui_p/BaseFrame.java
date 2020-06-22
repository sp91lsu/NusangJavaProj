package client_p.ui_p;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import client_p.Receivable;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;
import packetBase_p.ELoginType;
import packetBase_p.PacketBase;
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
//		jjj = new JPanel();
//		jjj.setLayout(new BorderLayout(0,0));
//		jjj.setVisible(true);
		payment.setVisible(false);
		addToBaseFrame(new LoginMain());
		addToBaseFrame(new MainLayout());
		addToBaseFrame(new Seating_Arrangement());
		addToBaseFrame(new LockerMain());
		addToBaseFrame(new ReservationMain());
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
				System.out.println("v");
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
		// 예약페이지
		// 당일결제페이지
	}

	public void updateInfo(ArrayList<RoomProduct> roomList) {

		roomInfoList = roomList;
		payment.updateRoomInfo();
	}

}
