package client_p.ui_p;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeatChangePop extends JFrame {

	public SeatChangePop() {
		setBounds(300, 300, 400, 200);
		getContentPane().setLayout(null);
		
		JLabel msg = new JLabel("�̵��� �¼��� Ŭ���� �ּ���");
		msg.setFont(new Font("����", Font.PLAIN, 25));
		msg.setBounds(0, 0, 380, 80);
		getContentPane().add(msg);
		
		JButton okBtn = new JButton("Ȯ��");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		okBtn.setBounds(120, 90, 150, 50);
		getContentPane().add(okBtn);
		setVisible(true);
	}
}