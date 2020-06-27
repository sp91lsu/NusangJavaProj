package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;

public class RCalcFrame extends JFrame {

	RoomProduct room;
	UserData userData;
	JLabel contentLabel;

	public static void main(String[] args) {
		// RCalcFrame frame = new RCalcFrame();
	}

	public RCalcFrame(RoomProduct room) {
		userData = BaseFrame.getInstance().userData;
		this.room = room;
		setBounds(710, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel MainLabel = new JLabel("예약 및 결제 내역");
		MainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MainLabel.setFont(new Font("굴림", Font.BOLD, 28));
		MainLabel.setBounds(27, 29, 412, 81);
		getContentPane().add(MainLabel);

		contentLabel = new JLabel();
		contentLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentLabel.setBounds(37, 125, 409, 220);
		getContentPane().add(contentLabel);
		contentLabel.setText(getDateFormat());
		JButton okButton = new JButton("예약 및 결제");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().paymentPop.openPage(room);
				dispose();
			}
		});
		okButton.setBounds(60, 360, 173, 58);
		getContentPane().add(okButton);

		JButton cancleButton = new JButton("취소");
		cancleButton.setBounds(250, 360, 173, 58);
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		getContentPane().add(cancleButton);

		setVisible(true);
	}

	String getDateFormat() {
		String text = "<html>예약 좌석 :" + room.name + "<br>예약자 ID :" + userData.id + "<br>예약 일자 :"
				+ room.calendarList.get(0).get(Calendar.YEAR) + " " + (room.calendarList.get(0).get(Calendar.MONTH) + 1)
				+ " " + room.calendarList.get(0).get(Calendar.DATE) + "<br>예약 시간 :";
		text += room.calendarList.get(0).get(Calendar.HOUR_OF_DAY) + " ~ "
				+ (room.calendarList.get(room.calendarList.size() - 1).get(Calendar.HOUR_OF_DAY) + 1);
		text += "<br>휴대폰번호 :" + userData.phone + "<br><html>";
		return text;
	}

}