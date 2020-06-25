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

public class RCalcFrame extends JFrame{
	
	String roomN = BaseFrame.getInstance().roomProduct.name;
	String userId = BaseFrame.getInstance().userData.id;
	String resDate;
	String resTime="";
	String phoneN = BaseFrame.getInstance().userData.phone;
	
	public static void main(String[] args) {
		RCalcFrame frame = new RCalcFrame();
	}

	public RCalcFrame(){
		setBounds(710, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		dateCal();
		
		JLabel MainLabel = new JLabel("예약 및 결제 내역");
		MainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MainLabel.setFont(new Font("굴림", Font.BOLD, 28));
		MainLabel.setBounds(27, 29, 412, 81);
		getContentPane().add(MainLabel);
		
		JLabel contentLabel = new JLabel("<html>예약 좌석 :"+roomN+
				"<br>예약자 ID :"+userId+"<br>예약 일자 :"+resDate+"<br>예약 시간 :"+
				resTime+"<br>휴대폰번호 :"+phoneN+"<br><html>");
		contentLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentLabel.setBounds(37, 125, 409, 220);
		getContentPane().add(contentLabel);
		
		JButton okButton = new JButton("예약 및 결제");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().paymentPop.setVisible(true);
				dispose();
			}});
		okButton.setBounds(60, 360, 173, 58);
		getContentPane().add(okButton);
		
		
		JButton cancleButton = new JButton("취소");
		cancleButton.setBounds(250, 360, 173, 58);
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}});
		getContentPane().add(cancleButton);
		
		setVisible(true);
	}
	
	//날짜관련 정보 불러오는 매소드
	void dateCal() {
		SimpleDateFormat date = new SimpleDateFormat("YYYY-MM-dd");
		SimpleDateFormat date2 = new SimpleDateFormat("HH:00");
		for (Calendar roomCal : BaseFrame.getInstance().roomProduct.calendarList) {
			if (roomCal != null ) {
				resDate = date.format(roomCal.getTime());
			}
		}
		int cnt=0;
		for ( Calendar aa : BaseFrame.getInstance().roomProduct.calendarList) {
			cnt++;
			resTime+= date2.format(aa.getTime())+" ";
			if(cnt%3==0)
			resTime+="<br>";
		}
	}
}