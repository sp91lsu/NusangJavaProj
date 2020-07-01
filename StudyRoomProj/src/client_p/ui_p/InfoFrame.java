package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		String date = "";

		setBounds(100, 100, 550, 650);
		getContentPane().setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 133, 449, 349);
		getContentPane().add(scrollPane_1);

		textArea = new JTextArea();

		ArrayList<RoomProduct> exitList = BaseFrame.getInstance().userData.exitList;
		ArrayList<RoomProduct> reserList = BaseFrame.getInstance().userData.myReservationList;

		String text = "��� ��Ȳ\n";
		for (RoomProduct data : exitList) {
			for (Calendar cal : data.calendarList) {
				date = sdf.format(cal.getTime());
				text += "�̿��� ID : " + id + "\n������ �¼�/�� �� : " + data.name + " / " + "�ݾ� : " + data.price + "��"
						+ "\n������ �ð� : " + date + "\n";
			}
		}

		text += "���� ��Ȳ\n";
		for (RoomProduct data : reserList) {
			for (Calendar cal : data.calendarList) {
				date = sdf.format(cal.getTime());
				text += "�̿��� ID : " + id + "\n������ �¼�/�� �� : " + data.name + " / " + "�ݾ� : " + data.price + "��"
						+ "\n������ �ð� : " + date + "\n";
			}
		}

		textArea.setText(text);
		scrollPane_1.setViewportView(textArea);

		JLabel mainLabel = new JLabel("<html>��� ���͵��<br>�̿� ����<html>");
		mainLabel.setFont(new Font("���� ���", Font.BOLD, 36));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setBounds(17, 15, 494, 103);
		getContentPane().add(mainLabel);

		JButton okButton = new JButton("Ȯ��");
		okButton.setFont(new Font("���� ���", Font.BOLD, 22));
		okButton.setBounds(158, 514, 186, 65);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BaseFrame.getInstance().getMainLayout().is_Info=false;
			}
		});
		getContentPane().add(okButton);
		setUndecorated(true); 
		setVisible(true);

	}
}