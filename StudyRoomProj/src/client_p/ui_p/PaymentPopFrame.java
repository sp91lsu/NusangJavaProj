package client_p.ui_p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.packet_p.syn_p.CsBuyRoomSyn;
import data_p.product_p.room_p.RoomProduct;

public class PaymentPopFrame extends JDialog implements ActionListener {
	RoomProduct room;

	public PaymentPopFrame() {

		setBounds(820, 470, 280, 140);
		getContentPane().setLayout(null);
		getContentPane().setBackground(MyColor.jinBeige);
		JLabel jbl = new JLabel("결제 하시겠습니까?");
		jbl.setBounds(0, 0, 263, 52);
		jbl.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jbl);
		JButton okBtn = new JButton("확인");
		okBtn.setBounds(26, 55, 96, 36);
		okBtn.setBackground(MyColor.jinBeige2);
		okBtn.addActionListener(this);
		getContentPane().add(okBtn);

		JButton cancelBtn = new JButton("취소");
		cancelBtn.setBounds(143, 55, 96, 36);
		getContentPane().add(cancelBtn);
		cancelBtn.setBackground(MyColor.jinBeige2);
		cancelBtn.addActionListener(this);
		setVisible(false);
		setUndecorated(true);
	}

	public void openPage(RoomProduct room) {
		this.room = room;
		setModal(true);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.getText().equals("확인")) {
			CsBuyRoomSyn packet = new CsBuyRoomSyn(room, BaseFrame.getInstance().userData.uuid, room.price);
			ClientNet.getInstance().sendPacket(packet);
			dispose();
		} else if (btn.getText().equals("취소")) {
			dispose();
		}
	}
}