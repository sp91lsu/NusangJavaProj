package client_p.ui_p;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.sun.org.apache.bcel.internal.generic.SIPUSH;

import client_p.Receivable;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.PacketBase;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class InfoFrame extends JFrame {

	JTextArea textArea;
	String id = BaseFrame.getInstance().userData.id;
	
	public static void main(String[] args) {
		InfoFrame frame = new InfoFrame();
	}

	public InfoFrame() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		String date = "";
		
		setBounds(100, 100, 550, 650);
		getContentPane().setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 133, 449, 349);
		getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();

		for (RoomProduct data : BaseFrame.getInstance().userData.myReservationList) {

			for (Calendar cal : data.calendarList) {
				date = sdf.format(cal.getTime());
				textArea.setText("ÀÌ¿ëÀÚ ID : " + id + "\n±¸¸ÅÇÑ ÁÂ¼®/·ë Á¤º¸ : " + "ÁÂ¼®/·ë ¸í : "+data.name + " / " + 
						"±Ý¾× : " + data.price + "¿ø" + "\n±¸¸ÅÇÑ ½Ã°£ : " + date + "\n");
			}
		}

		scrollPane_1.setViewportView(textArea);
		
		JLabel mainLabel = new JLabel("<html>Àâ¾Æ ½ºÅÍµð·ë<br>ÀÌ¿ë ³»¿ª<html>");
		mainLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 36));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setBounds(17, 15, 494, 103);
		getContentPane().add(mainLabel);

		JButton okButton = new JButton("È®ÀÎ");
		okButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		okButton.setBounds(158, 514, 186, 65);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(okButton);
		setVisible(true);
		

	}
}