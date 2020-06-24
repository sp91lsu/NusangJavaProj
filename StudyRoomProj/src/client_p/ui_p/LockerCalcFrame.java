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
	String id = BaseFrame.getInstance().userData.id;
	String lockerNum;
	
	public LockerCalcFrame(String lockerNum) {
		this.lockerNum=lockerNum;
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>�繰�� �뿩 ����<br>�̿��� ID :"+id+"<br>"
				+ "�繰�� ��ȣ : "+lockerNum+"<br>�뿩 ��� : 1000��<html>");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(17, 26, 394, 146);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.setFont(new Font("����", Font.BOLD, 22));
		btnNewButton.setBounds(67, 209, 129, 70);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�����ϸ� �����  �߰�. �ϴ��� â�� �ݴ´�
				dispose();
				BaseFrame.getInstance().view("LoginMain");
			}});
		
		JButton button = new JButton("���");
		button.setFont(new Font("����", Font.BOLD, 22));
		button.setBounds(221, 209, 129, 70);
		getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		setVisible(true);
	}
}