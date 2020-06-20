package client_p.ui_p;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;

public class BaseFrame extends JFrame {

	public ArrayList<JPanel> jPanelArrl = new ArrayList<JPanel>();

	private static BaseFrame instance;

	public static BaseFrame getInstance() {
		if (instance == null) {
			instance = new BaseFrame();
		}

		return instance;
	}

	UserData userDataKKK = null;
	public ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();
	public Payment paymentKKK = new Payment("kk");
	public SignUpMain signUpFrame = new SignUpMain();
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
		paymentKKK.setVisible(false);
		addToBaseFrame(new LoginMain());
		addToBaseFrame(new MainLayout());
		addToBaseFrame(new Seating_Arrangement());
		addToBaseFrame(new LockerMain());

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

}
