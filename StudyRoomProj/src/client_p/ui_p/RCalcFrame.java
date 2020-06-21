package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class RCalcFrame extends JFrame{
	
	public static void main(String[] args) {
		RCalcFrame frame = new RCalcFrame();
	}

	public RCalcFrame(){
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel MainLabel = new JLabel("예약 및 결제 내역");
		MainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MainLabel.setFont(new Font("굴림", Font.BOLD, 28));
		MainLabel.setBounds(27, 29, 412, 81);
		getContentPane().add(MainLabel);
		
		JLabel contentLabel = new JLabel("<html>예약 종류 : 개인석<br>예약 좌석 : 00번<br>"
				+ "예약자 ID : ryu5986<br>예약 일자 : YY/MM/dd<br>예약 시간 : HH:mm<br>휴대폰번호 : 000-0000-0000<br><html>");
		contentLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentLabel.setBounds(37, 125, 409, 220);
		getContentPane().add(contentLabel);
		
		JButton okButton = new JButton("예약 및 결제");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().view("PaymentPopFrame");
			}});
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
}