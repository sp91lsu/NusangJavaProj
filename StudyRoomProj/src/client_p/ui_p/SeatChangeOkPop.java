package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeatChangeOkPop extends JFrame {

	private JPanel contentPane;

	public SeatChangeOkPop() {
		setBounds(300, 300, 300, 200);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�̵��Ͻðڽ��ϱ�?");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 25));
		lblNewLabel.setBounds(0, 0, 280, 80);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Ȯ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		btnNewButton.setBounds(59, 80, 105, 27);
		contentPane.add(btnNewButton);
		setVisible(true);
	}
}