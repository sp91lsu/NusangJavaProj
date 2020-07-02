package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.packet_p.syn_p.CsBuyLockerSyn;
import data_p.product_p.LockerData;
import data_p.user_p.UserData;

public class LockerCalcFrame extends JDialog {

	UserData userData = BaseFrame.getInstance().userData;
	LockerData lockerData;

	public LockerCalcFrame(LockerData lockerData) {
		setModal(true);
		this.lockerData = lockerData;
		setBounds(100, 100, 450, 350);
		getContentPane().setBackground(MyColor.black);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("<html>사물함 대여 내역<br>이용자 ID :" + userData.id + "<br>" + "사물함 번호 : "
				+ lockerData.id + "<br>대여 요금 : 1000원<html>");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(17, 26, 394, 146);
		lblNewLabel.setForeground(Color.white);
		getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("결제");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 22));
		btnNewButton.setBounds(67, 209, 129, 70);
		getContentPane().add(btnNewButton);
		btnNewButton.setBackground(MyColor.w_white);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 결제하면 기능을 추가. 일단은 창을 닫는다
				CsBuyLockerSyn csBuyLockerPacket = new CsBuyLockerSyn(userData.uuid, lockerData);
				ClientNet.getInstance().sendPacket(csBuyLockerPacket);
				dispose();
			}
		});

		JButton button = new JButton("취소");
		button.setFont(new Font("굴림", Font.BOLD, 22));
		button.setBounds(221, 209, 129, 70);
		button.setBackground(MyColor.w_white);
		getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setUndecorated(true);
		setVisible(true);
	}
}