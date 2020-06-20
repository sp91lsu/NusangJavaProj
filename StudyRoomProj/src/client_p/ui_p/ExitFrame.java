package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ExitFrame extends JFrame{

	public ExitFrame() {
		setBounds(100, 100, 450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Åð½Ç ÇÏ½Ã°Ú½À´Ï±î???");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(53, 48, 318, 135);
		getContentPane().add(lblNewLabel);
		
		JButton okButton = new JButton("È®ÀÎ");
		okButton.setFont(new Font("°íµñÃ¼", Font.PLAIN, 22));
		okButton.setBounds(38, 227, 162, 70);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}});
		getContentPane().add(okButton);
		
		JButton cancleButton = new JButton("Ãë¼Ò");
		cancleButton.setFont(new Font("°íµñÃ¼", Font.PLAIN, 22));
		cancleButton.setBounds(228, 227, 162, 70);
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}});
		getContentPane().add(cancleButton);
		setVisible(true);
	}
}