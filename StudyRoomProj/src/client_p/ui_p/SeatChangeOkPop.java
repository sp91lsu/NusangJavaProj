package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client_p.ClientNet;
import client_p.packet_p.syn_p.CsMoveSeatSyn;

public class SeatChangeOkPop extends JFrame {

	private JPanel contentPane;

	public SeatChangeOkPop(int moveSeatID) {
		setBounds(300, 300, 300, 200);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("이동하시겠습니까?");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(0, 0, 280, 80);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CsMoveSeatSyn packet = new CsMoveSeatSyn(BaseFrame.getInstance().userData.uuid,
						BaseFrame.getInstance().getUsingRoom(),
						moveSeatID);
				ClientNet.getInstance().sendPacket(packet);
			}});
		btnNewButton.setBounds(7, 80, 130, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton2 = new JButton("취소");
		btnNewButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton2.setBounds(145, 80, 130, 50);
		contentPane.add(btnNewButton2);
		setVisible(true);
	}
}