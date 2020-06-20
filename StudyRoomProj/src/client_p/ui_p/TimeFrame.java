package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class TimeFrame extends JFrame{

	String id = "star";
	String phoneNum = "01012341234";
	String seatingName = "»þ¿ö·ë";
	String usingTime = "01:20";
	String remaingTime = "00:40";
	
	public static void main(String[] args) {
		TimeFrame frame = new TimeFrame();
	}

	public TimeFrame() {
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>ÀÌ¿ëÀÚ ID : "+id+"<br>ÈÞ´ëÆù¹øÈ£ : "+phoneNum
				+ "<br>ÀÌ¿ë ÁßÀÎ ÁÂ¼®/·ë : "+seatingName+"<br>ÀÌ¿ë ½Ã°£ : "+usingTime+"<br>ÀÜ¿© ½Ã°£ : "+remaingTime+"<html>");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		lblNewLabel.setBounds(51, 98, 387, 183);
		//lblNewLabel.setOpaque(true);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNewButton = new JButton("È®ÀÎ");
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		btnNewButton.setBounds(110, 340, 243, 71);
		getContentPane().add(btnNewButton);
		
		JLabel titleLabel = new JLabel("ÀÜ¿© ½Ã°£ È®ÀÎ");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		titleLabel.setBounds(57, 10, 363, 71);
		getContentPane().add(titleLabel);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		setVisible(true);
	}
}