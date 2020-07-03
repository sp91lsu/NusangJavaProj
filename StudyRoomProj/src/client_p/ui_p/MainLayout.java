package client_p.ui_p;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.EEnter;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsChatConnectSyn;
import client_p.packet_p.syn_p.CsChatSyn;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.ack_p.ScExitAck;

public class MainLayout extends JPanel implements Receivable, ActionListener {

	private JButton button_1;
	private JButton button_2;
	private JButton button_6;
	private JButton button_9;
	private JButton button_5;
	private JButton button_3;
	private JButton button_7;
	long todayRemainTime;

	boolean is_Info, is_useData, is_addTime, is_Exit, is_LogOut;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ClientNet.getInstance().start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 800);
		frame.getContentPane().add(new MainLayout());
		frame.setVisible(true);
	}

	public MainLayout() {

		setLayout(null);
		setBackground(MyColor.black);
		JPanel panel = new JPanel();
		panel.setBounds(83, 127, 691, 486);
		panel.setBackground(MyColor.black);
		add(panel);

		JLabel lblNewLabel = new JLabel("�ڹ� ���͵��");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 35));
		lblNewLabel.setForeground(MyColor.white);
		lblNewLabel.setBounds(261, 10, 396, 107);
		add(lblNewLabel);
		panel.setLayout(new GridLayout(0, 3, 10, 10));

		button_1 = new JButton("���μ� �̿�");
		button_1.setFont(new Font("���� ���", Font.BOLD, 20));
		button_1.setBackground(MyColor.w_white);
		panel.add(button_1);
		button_1.addActionListener(this);

		button_2 = new JButton("��ü�� �̿�");
		button_2.setFont(new Font("���� ���", Font.BOLD, 20));
		button_2.setBackground(MyColor.w_white);
		panel.add(button_2);
		button_2.addActionListener(this);

		button_3 = new JButton("�繰�� �뿩");
		button_3.setFont(new Font("���� ���", Font.BOLD, 20));
		button_3.setBackground(MyColor.w_white);
		panel.add(button_3);
		button_3.addActionListener(this);

		JButton button_4 = new JButton("1:1 ������");
		button_4.setFont(new Font("���� ���", Font.BOLD, 20));
		button_4.setBackground(MyColor.w_white);
		panel.add(button_4);
		button_4.addActionListener(this);

		button_5 = new JButton("���μ� �̵�");
		button_5.setFont(new Font("���� ���", Font.BOLD, 20));
		button_5.setBackground(MyColor.w_white);
		panel.add(button_5);
		button_5.addActionListener(this);

		button_6 = new JButton("�¼� ����");
		button_6.setFont(new Font("���� ���", Font.BOLD, 20));
		button_6.setBackground(MyColor.w_white);
		panel.add(button_6);
		button_6.addActionListener(this);

		button_7 = new JButton("���� ���� ����");
		button_7.setFont(new Font("���� ���", Font.BOLD, 20));
		button_7.setBackground(MyColor.w_white);
		panel.add(button_7);
		button_7.addActionListener(this);

		JButton button_8 = new JButton("�̿� ���� ��ȸ");
		button_8.setFont(new Font("���� ���", Font.BOLD, 20));
		button_8.setBackground(MyColor.w_white);
		panel.add(button_8);
		button_8.addActionListener(this);

		button_9 = new JButton("���");
		button_9.setFont(new Font("���� ���", Font.BOLD, 20));
		button_9.setBackground(MyColor.w_white);
		panel.add(button_9);
		button_9.addActionListener(this);

		JButton button_10 = new JButton("�α׾ƿ�");
		button_10.setFont(new Font("���� ���", Font.BOLD, 20));
		button_10.setBackground(MyColor.w_white);
		button_10.setBounds(325, 648, 238, 68);
		button_10.addActionListener(this);
		add(button_10);
	}

	public void updatePage() {
		todayRemainTime = BaseFrame.getInstance().getTodayRemainTime();
		RoomProduct reserRoom = BaseFrame.getInstance().checkMyReserRoom(null, Calendar.DATE); // ���� �̿��� �� ����Ʈ
		RoomProduct usingRoom = BaseFrame.getInstance().getUsingRoom(); // ���� �̿����� ��
		if (usingRoom != null) {

			button_9.setText("���");
			button_3.setEnabled(true);
			button_7.setEnabled(true);
			button_5.setEnabled(usingRoom.personNum == 1);
			button_6.setEnabled(true);// �¼� ���� ��ư Ȱ��ȭ

		} else if (reserRoom != null) {
			button_5.setEnabled(false);
			button_3.setEnabled(false);
			button_7.setEnabled(true);
			button_9.setText("���� ���");
			button_6.setEnabled(false);// �¼� ���� ��ư ��Ȱ��ȭ
		} else {
			button_9.setText("���");
			button_3.setEnabled(false);
			button_5.setEnabled(false);
			button_7.setEnabled(false);
			button_9.setEnabled(false);
			button_6.setEnabled(false);// �¼� ���� ��ư ��Ȱ��ȭ
		}
		System.out.println("���� ���� �ð�" + todayRemainTime);

		System.out.println("�����ð�");
		if (todayRemainTime > 0)// ����������
		{
			button_1.setEnabled(false);// ���η� �̿� ��ư ��Ȱ��ȭ
			button_2.setEnabled(false);// ��ü�� �̿��ư ��Ȱ��ȭ
			button_9.setEnabled(true);
		} else {
			button_1.setEnabled(true);// ���η� �̿� ��ư Ȱ��ȭ
			button_2.setEnabled(true);// ��ü�� �̿� ��ư Ȱ��ȭ
			button_9.setEnabled(false);
		}

		if (BaseFrame.getInstance().userData.locker != null) {
			button_3.setEnabled(false);
		}
	}

	public void is_reservation() {
		button_1.setEnabled(false);// ���η� �̿� ��ư ��Ȱ��ȭ
		button_2.setEnabled(false);// ��ü�� �̿��ư ��Ȱ��ȭ
	}

	@Override
	public void receive(PacketBase packet) {
		if (packet.getClass() == ScChatConnectAck.class) {
			ScChatConnectAck ack = (ScChatConnectAck) packet;
			if (ack.eResult == EResult.SUCCESS) {

				BaseFrame.getInstance().getClientChatFrame().setChatPacket(new CsChatSyn());
				BaseFrame.getInstance().openClientChatFrame();

			} else if (ack.eResult == EResult.NEGATIVE_CHAT) {
				BaseFrame.getInstance().openClientChatFrame().chatNegative();
			}
		} else if (packet.getClass() == ScExitAck.class) {
			ScExitAck resPacket = (ScExitAck) packet;
			if (resPacket.eResult == EResult.SUCCESS) {
				BaseFrame.getInstance().openMainLayout(resPacket.reserListAll, resPacket.myReserList,
						resPacket.myExitList, resPacket.lockerList);
				BaseFrame.getInstance().userData.locker = null;
				updatePage();
			} else if (resPacket.eResult == EResult.FAIL) {
				System.out.println("��� ����");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		if (btn.getText().equals("���μ� �̿�")) {
			System.out.println("���μ��̿�");
			BaseFrame.getInstance().openSeatingArrUI(EEnter.PRIVROOM);
			BaseFrame.getInstance().getSeatingArrUI().startTime_state();
		} else if (btn.getText().equals("��ü�� �̿�")) {
			BaseFrame.getInstance().openSeatingArrUI(EEnter.GROUPROOM);
			BaseFrame.getInstance().getSeatingArrUI().startTime_state();
		} else if (btn.getText().equals("�繰�� �뿩")) {
			BaseFrame.getInstance().openLockerMain();
		} else if (btn.getText().equals("1:1 ������")) {
			BaseFrame.getInstance().openClientChatFrame();
			CsChatConnectSyn packet = new CsChatConnectSyn(BaseFrame.getInstance().userData);
			ClientNet.getInstance().sendPacket(packet);
		} else if (btn.getText().equals("���μ� �̵�")) {
			BaseFrame.getInstance().openSeatingArrUI(EEnter.SEATCHANGE);
			BaseFrame.getInstance().view("Seating_Arrangement");
			SeatChangePop frame = new SeatChangePop();
		} else if (btn.getText().equals("�¼� ����")) {
			if (!is_addTime) {
				is_addTime = true;
				AddTimeDialog frame = new AddTimeDialog();
			}
		} else if (btn.getText().equals("���� ���� ����")) {
			if (BaseFrame.getInstance().getTodayRemainTime() > 0) {
				if (!is_useData) {
					is_useData = true;
					TimeFrame time = new TimeFrame();
				}
			} else {
				System.out.println("���� �̿����� ���� ����.");
			}
		} else if (btn.getText().equals("�̿� ���� ��ȸ")) {
			if (!is_Info) {
				is_Info = true;
				InfoFrame info = new InfoFrame();
			}
		} else if (btn.getText().equals("���")) {
			if (!is_Exit) {
				is_Exit = true;
				ExitFrame exitframe = new ExitFrame(button_9.getText());
			}
		} else if (btn.getText().equals("�α׾ƿ�")) {
			if (!is_LogOut) {
				is_LogOut = true;
				LogoutPopFrame logout = new LogoutPopFrame();
				logout.setVisible(true);
			}
		}
	}
}