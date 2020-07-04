package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.packet_p.syn_p.CsMoveSeatSyn;

public class SeatChangeOkPop extends JDialog {

	private JPanel contentPane;

	public SeatChangeOkPop(int moveSeatID, long priceGab) {

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setBackground(MyColor.jinBeige);

		JLabel lblNewLabel = new JLabel();

		if (priceGab > 0) {
			lblNewLabel.setText("<html>�̵��Ͻðڽ��ϱ�? <br>�߰� �����ݾ� : " + priceGab + "��<br><html>");
			lblNewLabel.setBounds(0, 0, 280, 80);
			setBounds(760, 440, 400, 200);
		} else {
			lblNewLabel.setText("�̵��Ͻðڽ��ϱ�?");
			lblNewLabel.setBounds(0, 0, 280, 80);
			setBounds(760, 440, 280, 200);

		}

		lblNewLabel.setFont(new Font("����", Font.PLAIN, 25));

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Ȯ��");
		btnNewButton.setBackground(MyColor.jinBeige2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CsMoveSeatSyn packet = new CsMoveSeatSyn(BaseFrame.getInstance().userData.uuid,
						BaseFrame.getInstance().getUsingRoom(), moveSeatID);
				ClientNet.getInstance().sendPacket(packet);
			}
		});
		btnNewButton.setBounds(7, 80, 130, 50);
		contentPane.add(btnNewButton);

		JButton btnNewButton2 = new JButton("���");
		btnNewButton2.setBackground(MyColor.jinBeige2);
		btnNewButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton2.setBounds(145, 80, 130, 50);
		contentPane.add(btnNewButton2);
		setUndecorated(true);
		setModal(true);
		setVisible(true);
	}
}