package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import data_p.product_p.room_p.RoomProduct;

public class TimeFrame extends JFrame {

	String id = BaseFrame.getInstance().userData.id;
	String phoneNum = "01012341234";
	String seatingName = "»þ¿ö·ë";
	String usingTime = "01:20";
	String remaingTime = "00:40";

	public TimeFrame() {
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("ÀÜ¿© ½Ã°£ È®ÀÎ");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		titleLabel.setBounds(60, 10, 360, 70);
		getContentPane().add(titleLabel);

		JLabel lblNewLabel = new JLabel("<html>ÀÌ¿ëÀÚ ID : " + id + "<br>ÈÞ´ëÆù¹øÈ£ : " + phoneNum + "<br>ÀÌ¿ë ÁßÀÎ ÁÂ¼®/·ë : "
				+ seatingName + "<br>ÀÌ¿ë ½Ã°£ : " + usingTime + "<br>ÀÜ¿© ½Ã°£ : " + remaingTime + "<html>");
		showRemainTime();
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

	void showRemainTime() {

		ArrayList<Calendar> remainList = BaseFrame.getInstance().getTodayRemainTime();

		Calendar end = remainList.get(0);

		for (Calendar calendar : remainList) {
			if (end.getTimeInMillis() < calendar.getTimeInMillis()) {
				end = calendar;
			}
		}
		end.add(Calendar.HOUR, 1);

		long remain = end.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();

		System.out.println("³¡½Ã°£ " + end.getTime());
		System.out.println("³²Àº½Ã°£ ");
		System.out.println(TimeUnit.MILLISECONDS.toMinutes(remain));
	}

}
