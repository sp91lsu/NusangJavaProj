package client_p.ui_p;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsBuyRoomSyn;
import data_p.product_p.ProductData;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.ELoginType;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScBuyRoomAck;

public class PaymentPopFrame extends JDialog implements Receivable {
	RoomProduct room;

	public PaymentPopFrame() {

		setBounds(820, 470, 280, 140);
		getContentPane().setLayout(null);

		JLabel jbl = new JLabel("결제 하시겠습니까?");
		jbl.setBounds(0, 0, 263, 52);
		jbl.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jbl);
		JButton okBtn = new JButton("확인");
		okBtn.setBounds(26, 55, 96, 36);
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				for (Calendar cal : BaseFrame.getInstance().roomProduct.calendarList) {
//
//					//System.out.println(cal.getTime());
//				}
				CsBuyRoomSyn packet = new CsBuyRoomSyn(room, BaseFrame.getInstance().userData.uuid);
				ClientNet.getInstance().sendPacket(packet);
				//BaseFrame.getInstance().getReservationMain().resetResInfo();
				dispose();
			}
		});
		getContentPane().add(okBtn);
		
		JButton cancelBtn = new JButton("취소");
		cancelBtn.setBounds(143, 55, 96, 36);
		getContentPane().add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});

		setVisible(false);
	}

	public void openPage(RoomProduct room) {
		this.room = room;
		setModal(true);
		setVisible(true);
	}

	@Override
	public void receive(PacketBase packet) {
		ScBuyRoomAck ack = (ScBuyRoomAck) packet;
		JDialog jd = new JDialog();
		jd.getContentPane().setLayout(new GridLayout(2, 1));
		jd.setBounds(50, 50, 150, 150);
		JLabel jl = new JLabel("");
		JButton jb = new JButton("확인");

		if (ack.eResult == EResult.SUCCESS) {
			jl.setText("결제완료");
			BaseFrame.getInstance().openMainLayout(ack.roomList, ack.myReserList, ack.exitList, null);
			if (BaseFrame.getInstance().loginType == ELoginType.MOBILE) {
				BaseFrame.getInstance().view("LoginMain");
			}
		//	BaseFrame.getInstance().payment.dispose();
//			BaseFrame.getInstance().roomProduct.calendarList.clear();
		//	BaseFrame.getInstance().payment.timeList.clear();
		} else
			jl.setText("결제실패");

		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jd.getContentPane().add(jl);
		jd.getContentPane().add(jb);
	}
}