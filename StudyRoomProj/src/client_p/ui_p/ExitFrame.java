package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import client_p.ClientNet;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsExitSyn;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScExitAck;

public class ExitFrame extends JFrame implements Receivable {

	public ExitFrame() {
		setBounds(100, 100, 450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("��� �Ͻðڽ��ϱ�???");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(53, 48, 318, 135);
		getContentPane().add(lblNewLabel);

		JButton okButton = new JButton("Ȯ��");
		okButton.setFont(new Font("���ü", Font.PLAIN, 22));
		okButton.setBounds(38, 227, 162, 70);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CsExitSyn packet = new CsExitSyn(BaseFrame.getInstance().getUsingRoom());
				ClientNet.getInstance().sendPacket(packet);
				dispose();
				BaseFrame.getInstance().view("LoginMain");
				System.out.println("���������ͺ�����:" + BaseFrame.getInstance().userData);
				BaseFrame.getInstance().userData = null;
				System.out.println("���������ͺ�����:" + BaseFrame.getInstance().userData);
			}
		});
		getContentPane().add(okButton);

		JButton cancleButton = new JButton("���");
		cancleButton.setFont(new Font("���ü", Font.PLAIN, 22));
		cancleButton.setBounds(228, 227, 162, 70);
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(cancleButton);
		setVisible(true);
	}

	@Override
	public void receive(PacketBase packet) {
		ScExitAck resPacket = (ScExitAck) packet;
		if (resPacket.eResult == EResult.SUCCESS) {
			BaseFrame.getInstance().view("MainLayout");
		} else if (resPacket.eResult == EResult.FAIL) {
			System.out.println("��� ����");
		}
	}
}