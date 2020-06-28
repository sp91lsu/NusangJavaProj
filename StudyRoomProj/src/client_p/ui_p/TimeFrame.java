package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import data_p.product_p.LockerData;
import data_p.product_p.room_p.RoomProduct;

public class TimeFrame extends JFrame {

	String id = BaseFrame.getInstance().userData.id;
	String phoneNum = BaseFrame.getInstance().userData.phone;
	LockerData lockerInfo = BaseFrame.getInstance().userData.locker;
	String locker;
	String seatingName;

	public TimeFrame() {

		RoomProduct usingRoom = BaseFrame.getInstance().getUsingRoom();
		if (usingRoom != null) {
			seatingName = usingRoom.name;
		}
		setBounds(100, 100, 500, 600);
		getContentPane().setLayout(null);
		
		if(lockerInfo == null) {
			locker = "�̿� ���� ����";
		}else {
			locker = "<html><br>" + lockerInfo.name +"<br>�ݾ� : "+ lockerInfo.price +
					"<br>��й�ȣ : "+ lockerInfo.pw +"<html>";
		}

		JLabel titleLabel = new JLabel("���� ���� ����");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("���� ���", Font.BOLD, 30));
		titleLabel.setBounds(60, 10, 360, 70);
		getContentPane().add(titleLabel);

		String remaingTime = showRemainTime();
		JLabel lblNewLabel = new JLabel("<html>�̿��� ID : " + id + "<br>�޴�����ȣ : " + 
				phoneNum + "<br>�̿� ���� �¼�/�� : " + seatingName + "<br>���� �̿� �ð� : " + 
				remaingTime + "<br>�繰�� �̿� ���� : " + locker +  "<html>");
		
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 24));
		lblNewLabel.setBounds(51, 70, 387, 400);
		// lblNewLabel.setOpaque(true);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnNewButton = new JButton("Ȯ��");
		btnNewButton.setFont(new Font("���� ���", Font.BOLD, 24));
		btnNewButton.setBounds(110, 450, 243, 71);
		getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setVisible(true);
	}

	public String showRemainTime() {

		long remain = BaseFrame.getInstance().getTodayRemainTime();
		long remainHour = TimeUnit.MILLISECONDS.toHours(remain);
		long remainMinute = TimeUnit.MILLISECONDS.toMinutes(remain) % 60;

		return remainHour + "�ð�" + remainMinute + "��";
	}
}