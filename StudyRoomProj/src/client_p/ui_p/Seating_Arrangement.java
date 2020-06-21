package client_p.ui_p;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client_p.ClientNet;
import client_p.packet_p.syn_p.CsSignUpSyn;
import data_p.product_p.DataManager;
import data_p.product_p.TimeData;
import data_p.product_p.room_p.RoomProduct;

public class Seating_Arrangement extends JPanel {
	static JLabel north_west;

	ArrayList<TimeData> timeList = new ArrayList<TimeData>();

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500, 30, 900, 1000);
		frame.add(new Seating_Arrangement());
		frame.setVisible(true);
	}

	public Seating_Arrangement() {

		setLayout(new BorderLayout(0, 0));

		// ��� �г�
		JPanel panel_north = new JPanel();
		add(panel_north, BorderLayout.NORTH);
		panel_north.setLayout(new BorderLayout(0, 0));

		this.north_west = new JLabel("2020-06-18  21:24");// ����,�ð�
		north_west.setFont(new Font("����", Font.PLAIN, 20));
		panel_north.add(north_west, BorderLayout.WEST);

		JButton north_east = new JButton("�ڷΰ���");
		north_east.setFont(new Font("����", Font.PLAIN, 17));
		panel_north.add(north_east, BorderLayout.EAST);
		north_east.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().view("MainLayout");
			}
		});

		JPanel north_center = new JPanel();
		panel_north.add(north_center, BorderLayout.CENTER);
		north_center.setLayout(new BorderLayout(0, 0));

		JPanel north_center_center = new JPanel();
		north_center.add(north_center_center, BorderLayout.CENTER);
//            north_center_center.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		north_center_center.add(panel_5);

		JPanel panel_6 = new JPanel();
		north_center_center.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("�̿밡��");
		panel_6.add(lblNewLabel_1);

		ImageIcon umgIc = new ImageIcon("img/green.png");
		JLabel lblNewLabel_2 = new JLabel(umgIc);
		panel_6.add(lblNewLabel_2);

		JPanel panel_7 = new JPanel();
		north_center_center.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_3 = new JLabel("�����");
		panel_7.add(lblNewLabel_3);

		umgIc = new ImageIcon("img/red.png");
		JLabel lblNewLabel_4 = new JLabel(umgIc);
		panel_7.add(lblNewLabel_4);

		JPanel panel_8 = new JPanel();
		north_center_center.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_5 = new JLabel("���缱��");
		panel_8.add(lblNewLabel_5);

		umgIc = new ImageIcon("img/blue.png");
		JLabel lblNewLabel_6 = new JLabel(umgIc);
		panel_8.add(lblNewLabel_6);

		JPanel north_center_west = new JPanel();
		north_center.add(north_center_west, BorderLayout.WEST);

		JPanel north_center_east = new JPanel();
		north_center.add(north_center_east, BorderLayout.EAST);

		// �߾��г�
		JPanel panel_center = new JPanel();
		add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(null);

		// ���⼭���� �� ��ư
		JButton roomBtn1 = new JButton("������");
		roomBtn1.addActionListener(new BtnAct(roomBtn1));
		roomBtn1.setBounds(0, 0, 300, 180);
		panel_center.add(roomBtn1);

		JButton roomBtn2 = new JButton("8�ν�");
		roomBtn2.addActionListener(new BtnAct(roomBtn2));
		roomBtn2.setBounds(300, 0, 250, 180);
		panel_center.add(roomBtn2);

		JButton roomBtn3 = new JButton("6�ν�-1");
		roomBtn3.addActionListener(new BtnAct(roomBtn3));
		roomBtn3.setBounds(550, 0, 160, 180);
		panel_center.add(roomBtn3);

		JButton roomBtn4 = new JButton("6�ν�-2");
		roomBtn4.addActionListener(new BtnAct(roomBtn4));
		roomBtn4.setBounds(710, 0, 160, 180);
		panel_center.add(roomBtn4);

		JButton roomBtn5 = new JButton("4�ν�-1");
		roomBtn5.addActionListener(new BtnAct(roomBtn5));
		roomBtn5.setBounds(710, 220, 160, 150);
		panel_center.add(roomBtn5);

		JButton roomBtn6 = new JButton("4�ν�-2");
		roomBtn6.addActionListener(new BtnAct(roomBtn6));
		roomBtn6.setBounds(710, 370, 160, 150);
		panel_center.add(roomBtn6);

		JButton roomBtn7 = new JButton("2�ν�-1");
		roomBtn7.addActionListener(new BtnAct(roomBtn7));
		roomBtn7.setBounds(710, 520, 160, 100);
		panel_center.add(roomBtn7);

		JButton roomBtn8 = new JButton("2�ν�-2");
		roomBtn8.addActionListener(new BtnAct(roomBtn8));
		roomBtn8.setBounds(710, 620, 160, 100);
		panel_center.add(roomBtn8);

		JButton roomBtn9 = new JButton("2�ν�-3");
		roomBtn9.addActionListener(new BtnAct(roomBtn9));
		roomBtn9.setBounds(710, 720, 160, 100);
		panel_center.add(roomBtn9);

		JButton roomBtn10 = new JButton("�뷡��");
		roomBtn10.addActionListener(new BtnAct(roomBtn10));
		roomBtn10.setBounds(0, 180, 160, 240);
		panel_center.add(roomBtn10);

		JButton roomBtn11 = new JButton("���");
		roomBtn11.addActionListener(new BtnAct(roomBtn11));
		roomBtn11.setBounds(0, 420, 160, 240);
		panel_center.add(roomBtn11);

		// �ų��� �г�
		JPanel mannerzone_panel = new JPanel();
		mannerzone_panel.setBackground(Color.GREEN);
		mannerzone_panel.setBounds(0, 660, 300, 250);
		panel_center.add(mannerzone_panel);
		mannerzone_panel.setLayout(null);

		// �ų��� ��,��ư
		JLabel label_manner = new JLabel("�ų���");
		label_manner.setFont(new Font("����", Font.PLAIN, 20));
		label_manner.setBounds(120, 115, 70, 20);
		mannerzone_panel.add(label_manner);

		JButton btnM_1 = new JButton("M1");
		btnM_1.addActionListener(new BtnAct(btnM_1));
		btnM_1.setBounds(0, 0, 100, 90);
		mannerzone_panel.add(btnM_1);

		JButton btnM_2 = new JButton("M2");
		btnM_2.addActionListener(new BtnAct(btnM_2));
		btnM_2.setBounds(100, 0, 100, 90);
		mannerzone_panel.add(btnM_2);

		JButton btnM_3 = new JButton("M3");
		btnM_3.addActionListener(new BtnAct(btnM_3));
		btnM_3.setBounds(0, 160, 100, 90);
		mannerzone_panel.add(btnM_3);

		JButton btnM_4 = new JButton("M4");
		btnM_4.addActionListener(new BtnAct(btnM_4));
		btnM_4.setBounds(100, 160, 100, 90);
		mannerzone_panel.add(btnM_4);

		JButton btnM_5 = new JButton("M5");
		btnM_5.addActionListener(new BtnAct(btnM_5));
		btnM_5.setBounds(200, 160, 100, 90);
		mannerzone_panel.add(btnM_5);
		// �Ϲݼ� �г�
		JPanel normalzone_panel = new JPanel();
		normalzone_panel.setBackground(Color.CYAN);
		normalzone_panel.setBounds(300, 300, 300, 250);
		panel_center.add(normalzone_panel);
		normalzone_panel.setLayout(null);
		// �Ϲݽ� ��,��ư
		JLabel label_normal = new JLabel("�Ϲݽ�");
		label_normal.setFont(new Font("����", Font.PLAIN, 20));
		label_normal.setBounds(120, 115, 70, 20);
		normalzone_panel.add(label_normal);

		JButton btnN_1 = new JButton("1");
		btnN_1.addActionListener(new BtnAct(btnN_1));
		btnN_1.setBounds(0, 0, 100, 90);
		normalzone_panel.add(btnN_1);

		JButton btnN_2 = new JButton("2");
		btnN_2.addActionListener(new BtnAct(btnN_2));
		btnN_2.setBounds(100, 0, 100, 90);
		normalzone_panel.add(btnN_2);

		JButton btnN_3 = new JButton("3");
		btnN_3.addActionListener(new BtnAct(btnN_3));
		btnN_3.setBounds(200, 0, 100, 90);
		normalzone_panel.add(btnN_3);

		JButton btnN_4 = new JButton("4");
		btnN_4.addActionListener(new BtnAct(btnN_4));
		btnN_4.setBounds(0, 160, 100, 90);
		normalzone_panel.add(btnN_4);

		JButton btnN_5 = new JButton("5");
		btnN_5.addActionListener(new BtnAct(btnN_5));
		btnN_5.setBounds(100, 160, 100, 90);
		normalzone_panel.add(btnN_5);

		JButton btnN_6 = new JButton("6");
		btnN_6.addActionListener(new BtnAct(btnN_6));
		btnN_6.setBounds(200, 160, 100, 90);
		normalzone_panel.add(btnN_6);

		// �ްԽ�
		JPanel restzone_panel = new JPanel();
		restzone_panel.setBackground(Color.ORANGE);
		restzone_panel.setBounds(300, 710, 260, 200);
		panel_center.add(restzone_panel);
		restzone_panel.setLayout(null);
		// �ްԽ� �ȿ� ������ ���⼭
		JLabel label_rest = new JLabel("�ްԽ�");
		label_rest.setFont(new Font("����", Font.PLAIN, 20));
		label_rest.setBounds(100, 100, 70, 20);
		restzone_panel.add(label_rest);

		JPanel locker = new JPanel();
		locker.setBackground(Color.LIGHT_GRAY);
		locker.setBounds(560, 710, 30, 200);
		panel_center.add(locker);
		locker.setLayout(null);

		JLabel lblNewLabel = new JLabel("<html>��<br>��<br>��<html>");
		lblNewLabel.setBounds(0, 60, 32, 79);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		locker.add(lblNewLabel);

		SetNowTime now_time = new SetNowTime(north_west);
		now_time.start();
		setVisible(true);
	}
}

class BtnAct implements ActionListener {

	JButton bt;

	public BtnAct(JButton bt) {
		this.bt = bt;
	}

	void searchRProd(JButton jb) {
		for (RoomProduct roomData : DataManager.getInstance().roomList) {
			if (roomData.name.equals(jb.getText())) {

				System.out.println("roomData cfcccccccccc");
				BaseFrame.getInstance().paymentKKK.openPage(roomData);
//				BaseFrame.getInstance().roomProductKKK = roomData;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		searchRProd(bt);
		BaseFrame.getInstance().paymentKKK.setVisible(true);

	}

}