package client_p.ui_p;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.Receivable;
import client_p.packet_p.ack_p.CsChatConnectAck;
import client_p.packet_p.syn_p.CsChatConnectSyn;
import client_p.packet_p.syn_p.CsChatSyn;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScChatConnectAck;

public class MainLayout extends JPanel implements Receivable {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 1000);
		frame.getContentPane().add(new MainLayout());
		frame.setVisible(true);
	}

	public MainLayout() {

		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(82, 168, 725, 430);
		add(panel);
		panel.setLayout(new GridLayout(3, 3, 5, 5));

		JButton button_1 = new JButton("°³ÀÎ¼® ÀÌ¿ë");
		button_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().view("Seating_Arrangement");
				BaseFrame.getInstance().getSeatingArrUI().group_state(false);
			}
		});

		JButton button_2 = new JButton("´ÜÃ¼·ë ÀÌ¿ë");
		button_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().view("Seating_Arrangement");
				BaseFrame.getInstance().getSeatingArrUI().solo_state(false);
			}
		});

		JButton button_3 = new JButton("»ç¹°ÇÔ ´ë¿©");
		button_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().view("LockerMain");
			}
		});

		JButton button_4 = new JButton("1:1 °í°´¹®ÀÇ");
		button_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_4);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().view("ClientChatFrame");
				CsChatConnectSyn packet = new CsChatConnectSyn(BaseFrame.getInstance().userData);
				ClientNet.getInstance().sendPacket(packet);
				System.out.println("ÆÐÅ¶ÀÌ ´Ù½Ã ¿Ã¶§±îÁö ±â´Ù·Á¾ß ÇÔ");// ´ÙÀÌ¾ó·Î±× Ã¢ ¼³Á¤ÇÏ±â
			}
		});

		JButton button_5 = new JButton("°³ÀÎ¼® ÀÌµ¿");
		button_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_5);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().getSeatingArrUI().group_state(false);
				BaseFrame.getInstance().view("Seating_Arrangement");
			}
		});

		JButton button_6 = new JButton("ÁÂ¼® ¿¬Àå");
		button_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_6);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().payment.openPage();
			}
		});

		JButton button_7 = new JButton("ÀÜ¿© ½Ã°£");
		button_7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_7);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeFrame time = new TimeFrame();
			}
		});

		JButton button_8 = new JButton("³» ÀÌ¿ëÁ¤º¸");
		button_8.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_8);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoFrame info = new InfoFrame();
			}
		});

		JButton button_9 = new JButton("Åð½Ç");
		button_9.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_9);
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExitFrame exitframe = new ExitFrame();
			}
		});

		JLabel lblNewLabel = new JLabel("·Î±×ÀÎ ÈÄ È­¸é");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 35));
		lblNewLabel.setBounds(261, 10, 396, 107);
		add(lblNewLabel);
	}

	@Override
	public void receive(PacketBase packet) {
		ScChatConnectAck ack = (ScChatConnectAck) packet;
		if (ack.eResult == EResult.SUCCESS) {

			BaseFrame.getInstance().getClientChatFrame().setChatPacket(new CsChatSyn(ack.cip, ack.mip));
			BaseFrame.getInstance().view("ClientChatFrame");

		} else {
			System.out.println("°ÅÀý´çÇÔ");
		}
	}
}