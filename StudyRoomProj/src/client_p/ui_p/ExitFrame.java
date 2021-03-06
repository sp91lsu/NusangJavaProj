
package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import client_p.ClientNet;
import client_p.packet_p.syn_p.CsExitSyn;
import data_p.product_p.room_p.RoomProduct;

public class ExitFrame extends JDialog {

	String title = "";

	public ExitFrame(String title, boolean isCancel) {
		setModal(true);
		this.title = title;
		setBounds(735, 340, 450, 400);
		getContentPane().setBackground(MyColor.black);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel(title + " �Ͻðڽ��ϱ�???");

		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 28));
		lblNewLabel.setBounds(50, 50, 400, 135);
		getContentPane().add(lblNewLabel);

		JButton okButton = new JButton("Ȯ��");
		okButton.setFont(new Font("����ü", Font.BOLD, 22));
		okButton.setBounds(38, 227, 162, 70);
		okButton.setBackground(MyColor.w_white);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomProduct room = BaseFrame.getInstance().checkMyReserRoom(null, Calendar.DATE);
				room.isExit = true;
				CsExitSyn packet = new CsExitSyn(room, isCancel);
				ClientNet.getInstance().sendPacket(packet);
				BaseFrame.getInstance().getLoginMain().logSet();
				dispose();
				BaseFrame.getInstance().getMainLayout().is_Exit = false;
			}
		});
		getContentPane().add(okButton);

		JButton cancleButton = new JButton("���");
		cancleButton.setFont(new Font("����ü", Font.BOLD, 22));
		cancleButton.setBounds(228, 227, 162, 70);
		cancleButton.setBackground(MyColor.w_white);
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BaseFrame.getInstance().getMainLayout().is_Exit = false;
			}
		});
		getContentPane().add(cancleButton);
		setUndecorated(true);
		setVisible(true);
	}
}