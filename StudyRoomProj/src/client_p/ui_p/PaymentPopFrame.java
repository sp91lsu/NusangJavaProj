package client_p.ui_p;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsBuyRoomSyn;
import data_p.product_p.TimeData;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScBuyRoomAck;
import server_p.packet_p.ack_p.ScExitAck;

public class PaymentPopFrame extends JFrame implements Receivable {

	public PaymentPopFrame() {

		setBounds(50, 50, 150, 150);
		setLayout(new GridLayout(2, 1));

		JLabel jbl = new JLabel("���� �Ͻðڽ��ϱ�?");
		jbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(jbl);
		JButton jb = new JButton("Ȯ��");
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CsBuyRoomSyn packet = new CsBuyRoomSyn(BaseFrame.getInstance().roomProduct,
						BaseFrame.getInstance().userData.uuid);
				for (Calendar cal : BaseFrame.getInstance().roomProduct.calendarList) {

					System.out.println(cal.getTime());
				}
				ClientNet.getInstance().sendPacket(packet);
				dispose();
			}
		});
		add(jb);

		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void receive(PacketBase packet) {
		ScBuyRoomAck ack = (ScBuyRoomAck) packet;
		JDialog jd = new JDialog();
		jd.setLayout(new GridLayout(2, 1));
		jd.setBounds(50, 50, 150, 150);
		JLabel jl = new JLabel("");
		JButton jb = new JButton("Ȯ��");

		if (ack.eResult == EResult.SUCCESS) {
			jl.setText("�����Ϸ�");
			BaseFrame.getInstance().view("LoginMain");
			BaseFrame.getInstance().payment.dispose();
			BaseFrame.getInstance().rcalc.dispose();
		} else
			jl.setText("��������");

		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jd.add(jl);
		jd.add(jb);
	}
}