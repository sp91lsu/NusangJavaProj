package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LockerCalcFrame extends JFrame
{
	public LockerCalcFrame() {
		setBounds(100, 100, 450, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>사물함 대여 내역<br>이용자 ID : ryu5986<br>"
				+ "사물함 번호 : 00번<br>대여 요금 : 1000원<html>");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(17, 26, 394, 146);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("결제");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 22));
		btnNewButton.setBounds(67, 209, 129, 70);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//결제하면 기능을  추가. 일단은 창을 닫는다
				dispose();
			}});
		
		JButton button = new JButton("취소");
		button.setFont(new Font("굴림", Font.BOLD, 22));
		button.setBounds(221, 209, 129, 70);
		getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		setVisible(true);
	}
}