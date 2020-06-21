package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client_p.Receivable;
import packetBase_p.EResult;
import packetBase_p.PacketBase;

public class RCalcPopFrame extends JFrame{

	public RCalcPopFrame() {
		setBounds(100, 100, 300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp = new JPanel();
		jp.setLayout(null);
		add(jp);
		
		JLabel jlb = new JLabel("<html>예약 및 결제 <br>완료<html>");
		jlb.setFont(new Font("고딕체", Font.BOLD, 20));
		jlb.setHorizontalAlignment(SwingConstants.CENTER);
		jlb.setBounds(50, 0, 200, 200);
		jp.add(jlb);
		
		JButton jb= new JButton("확인");
		jb.setBounds(90, 170, 100, 70);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		jp.add(jlb);
		jp.add(jb);
		
		setVisible(true);
	}

//	@Override
//	public void receive(PacketBase packet) {
//		ScResCalcAck resPacket = (ScResCalcAck) packet;
//		if(resPacket.eResult == EResult.SUCCESS) {
//			BaseFrame.getInstance().view("MainLayout");
//		}else if(resPacket.eResult == EResult.FAIL) {
//			System.out.println("예약 및 결제 실패");
//		}
//		
//	}
}