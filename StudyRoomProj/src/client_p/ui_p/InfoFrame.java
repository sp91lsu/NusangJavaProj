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

public class InfoFrame extends JFrame {

	String id = BaseFrame.getInstance().userData.id;
	JLabel contentLabel;

	public static void main(String[] args) {
		InfoFrame frame = new InfoFrame();
	}

	public InfoFrame() {

		long totUseTimeMinute = BaseFrame.getInstance().totTodayUseTime();
		long hour = TimeUnit.MILLISECONDS.toHours(totUseTimeMinute);
		long minute = TimeUnit.MILLISECONDS.toMinutes(totUseTimeMinute) % 60;
		System.out.println(totUseTimeMinute);
		System.out.println(minute);
		contentLabel = new JLabel("이용자 정보");
		contentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		contentLabel.setBounds(33, 135, 407, 199);

		contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RoomProduct roomProduct = BaseFrame.getInstance().getUsingRoom();
		SimpleDateFormat date = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		if (roomProduct != null && roomProduct.calendarList != null) {
			String dateList = date.format(roomProduct.calendarList.get(0).getTime());

			contentLabel.setText("<html>이용자 ID : " + id + "<br>현재 이용 중인 내역<br>이용중인 좌석/룸 : " + roomProduct.name
					+ "<br>선택한 첫 시간 : " + dateList + "<br>누적 이용 시간 : " + hour + "시간" + minute + "분" + "<html>");
			setVisible(true);
		} else {

			JDialog jd = new JDialog();
			jd.setBounds(100, 100, 200, 200);
			jd.getContentPane().setLayout(new GridLayout(2, 1));

			JLabel jl = new JLabel("현재 이용중인 내역이 없습니다.");
			jd.getContentPane().add(jl);

			JButton jb = new JButton("확인");
			jb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					jd.dispose();

				}
			});
			jd.getContentPane().add(jb);
			jd.setVisible(true);
		}
		getContentPane().add(contentLabel);
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);

		JLabel mainLabel = new JLabel("<html>잡아 스터디룸<br>이용 내역<html>");
		mainLabel.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setBounds(33, 15, 407, 103);
		getContentPane().add(mainLabel);

		JButton okButton = new JButton("확인");
		okButton.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		okButton.setBounds(143, 364, 186, 65);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(okButton);

	}

}