package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import data_p.product_p.room_p.RoomProduct;

public class InfoFrame extends JDialog {

	JTextArea textArea;
	String id = BaseFrame.getInstance().userData.id;

	public InfoFrame() {
		setModal(true);

		setBounds(735, 215, 550, 650);
		getContentPane().setBackground(MyColor.black);
		getContentPane().setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 133, 449, 349);
		getContentPane().add(scrollPane_1);

		textArea = new JTextArea();

		ArrayList<RoomProduct> exitList = BaseFrame.getInstance().userData.exitList;
		ArrayList<RoomProduct> reserList = BaseFrame.getInstance().userData.myReservationList;

		String text = " - Åð½Ç ÇöÈ² - \n";
		for (RoomProduct data : exitList) {
			text += "\nÁÂ¼®/·ë ¸í : " + data.name + " \n " + "±Ý¾× : " + (data.price*data.calendarList.size()) + "¿ø" + "\n½Ã°£ : "
					+ startToEnd(data.calendarList) + "\n";
		}

		text += "\n - ¿¹¾à ÇöÈ² - \n";
		for (RoomProduct data : reserList) {
			text += "\nÁÂ¼®/·ë ¸í : " + data.name + " \n " + "±Ý¾× : " + (data.price*data.calendarList.size()) + "¿ø" + "\n½Ã°£ : "
					+ startToEnd(data.calendarList) + "\n";
		}

		textArea.setText(text);
		scrollPane_1.setViewportView(textArea);

		JLabel mainLabel = new JLabel("<html>Àâ¾Æ ½ºÅÍµð·ë<br>ÀÌ¿ë ³»¿ª<html>");
		mainLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 36));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setForeground(Color.white);
		mainLabel.setBounds(17, 15, 494, 103);
		getContentPane().add(mainLabel);

		JButton okButton = new JButton("È®ÀÎ");
		okButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		okButton.setBounds(158, 514, 186, 65);
		okButton.setBackground(MyColor.w_white);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BaseFrame.getInstance().getMainLayout().is_Info = false;
			}
		});
		getContentPane().add(okButton);
		setUndecorated(true);
		setVisible(true);

	}

	String startToEnd(ArrayList<Calendar> list) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd");
		String text = sdf.format(list.get(0).getTime()) + " > ";
		text += list.get(0).get(Calendar.HOUR_OF_DAY) + "½Ã ~ "
				+ (list.get(list.size() - 1).get(Calendar.HOUR_OF_DAY) + 1) + "½Ã";
		return text;
	}
}