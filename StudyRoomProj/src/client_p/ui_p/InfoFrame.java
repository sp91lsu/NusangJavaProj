package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class InfoFrame extends JFrame{

	public static void main(String[] args) {
		InfoFrame frame = new InfoFrame();
	}

	public InfoFrame() {
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel mainLabel = new JLabel("<html>��� ���͵��<br>�̿� ����<html>");
		mainLabel.setFont(new Font("���� ����", Font.BOLD, 36));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setBounds(33, 15, 407, 103);
		getContentPane().add(mainLabel);
		
		JLabel contentLabel = new JLabel("<html>�̿��� ID : ryu5986<br><br>���� �̿� �ð� : HH:mm<br><br>"
				+ "���� �̿� �ð� : HH:mm<html>");
		contentLabel.setFont(new Font("���� ����", Font.BOLD, 28));
		contentLabel.setBounds(33, 135, 407, 199);
		getContentPane().add(contentLabel);
		contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton okButton = new JButton("Ȯ��");
		okButton.setFont(new Font("���� ����", Font.BOLD, 22));
		okButton.setBounds(143, 364, 186, 65);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}});
		getContentPane().add(okButton);
		setVisible(true);
	}
}