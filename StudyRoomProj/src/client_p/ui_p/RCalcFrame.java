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
		
		JLabel MainLabel = new JLabel("���� �� ���� ����");
		MainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MainLabel.setFont(new Font("����", Font.BOLD, 28));
		MainLabel.setBounds(27, 29, 412, 81);
		getContentPane().add(MainLabel);
		
		JLabel contentLabel = new JLabel("<html>���� �¼� :"+roomN+
				"<br>������ ID :"+userId+"<br>���� ���� :"+resDate+"<br>���� �ð� :"+
				resTime+"<br>�޴�����ȣ :"+phoneN+"<br><html>");
		contentLabel.setFont(new Font("����", Font.PLAIN, 20));
		contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentLabel.setBounds(37, 125, 409, 220);
		getContentPane().add(contentLabel);
		
		JButton okButton = new JButton("���� �� ����");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().paymentPop.setVisible(true);
				dispose();
			}});
		okButton.setBounds(60, 360, 173, 58);
		getContentPane().add(okButton);
		
		
		JButton cancleButton = new JButton("���");
		cancleButton.setBounds(250, 360, 173, 58);
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}});
		getContentPane().add(cancleButton);
		
		setVisible(true);
	}
	
	//��¥���� ���� �ҷ����� �żҵ�
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