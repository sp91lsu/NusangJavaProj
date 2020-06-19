package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TimeFrame extends JFrame{

	public static void main(String[] args) {
		TimeFrame frame = new TimeFrame();
	}

	public TimeFrame() {
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>ÀÌ¿ëÀÚ ID : ryu5986<br>ÈÞ´ëÆù¹øÈ£ : 000-0000-0000<br>"
				+ "ÀÌ¿ë ÁßÀÎ ÁÂ¼®/·ë : 00¼®<br>ÀÌ¿ë ½Ã°£ : HH:mm<br>ÀÜ¿© ½Ã°£ : HH:mm<html>");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		lblNewLabel.setBounds(34, 33, 387, 268);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNewButton = new JButton("È®ÀÎ");
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		btnNewButton.setBounds(110, 340, 243, 71);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}});
		
		setVisible(true);
	}
}