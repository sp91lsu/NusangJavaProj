package client_p.ui_p;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import client_p.EEnter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Color;

public class ReservationInfoMain extends JFrame {

	public ReservationInfoMain() {
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);

		String info = "  잡아 스터디룸을 이용해 주셔서 감사합니다.\n\t    예약 시 주의 사항\n\n1.예약은 1시간 단위로 가능합니다.\n"
				+ "2.예약 시 당일 예약은 불가합니다.\n3.예약 시 날짜와 시간을 꼭 확인하시기 바랍니다.\n"+
				"4.기존의 예약된 날짜에는 중복 예약이 불가합니다.";
		String headName = "     잡아 스터디룸\n     예약이용 안내";

		JTextArea frontName = new JTextArea();
		frontName.setBackground(Color.YELLOW);
		frontName.setFont(new Font("굴림", Font.BOLD, 34));
		frontName.setBounds(55, 29, 370, 92);
		frontName.setText(headName);
		getContentPane().add(frontName);

		JTextArea rInfo = new JTextArea();
		rInfo.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rInfo.setBounds(40, 149, 405, 211);
		rInfo.setText(info);
		getContentPane().add(rInfo);

		JButton btnNewButton = new JButton("예약하러 가기");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 16));
		btnNewButton.setBounds(68, 375, 151, 54);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				BaseFrame.getInstance().openSeatingArrUI(EEnter.MOBILE);
				dispose();
			}
		});
		getContentPane().add(btnNewButton);

		JButton button = new JButton("로그아웃");
		button.setFont(new Font("굴림", Font.BOLD, 16));
		button.setBounds(274, 375, 151, 54);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().getLoginMain().logSet();
				dispose();
			}
		});
		getContentPane().add(button);

		setVisible(true);
	}

}
