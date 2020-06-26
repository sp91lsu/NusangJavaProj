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

public class TimeFrame extends JFrame {

	String id = BaseFrame.getInstance().userData.id;
	String phoneNum = BaseFrame.getInstance().userData.phone;
	String seatingName = BaseFrame.getInstance().getUsingRoom().name;

	public TimeFrame() {

		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("ÀÜ¿© ½Ã°£ È®ÀÎ");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		titleLabel.setBounds(60, 10, 360, 70);
		getContentPane().add(titleLabel);

		String remaingTime = showRemainTime();
		JLabel lblNewLabel = new JLabel("<html>ÀÌ¿ëÀÚ ID : " + id + "<br>ÈÞ´ëÆù¹øÈ£ : " + phoneNum + "<br>ÀÌ¿ë ÁßÀÎ ÁÂ¼®/·ë : "
				+ seatingName + "<br>ÀÜ¿© ½Ã°£ : " + remaingTime + "<html>");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		lblNewLabel.setBounds(51, 98, 387, 183);
		// lblNewLabel.setOpaque(true);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnNewButton = new JButton("È®ÀÎ");
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		btnNewButton.setBounds(110, 340, 243, 71);
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

		return remainHour + "½Ã°£" + remainMinute + "ºÐ";
	}
}