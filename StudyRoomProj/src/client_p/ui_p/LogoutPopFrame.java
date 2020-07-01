package client_p.ui_p;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.packet_p.syn_p.CsCloseSyn;

public class LogoutPopFrame extends JFrame{

	public LogoutPopFrame() {
		setBounds(50, 50, 150, 150);
		setLayout(new GridLayout(2, 1));

		JLabel jbl = new JLabel("�α׾ƿ� �Ͻðڽ��ϱ�?");
		jbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(jbl);
		JButton jb = new JButton("Ȯ��");
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().getMainLayout().is_LogOut=false;
				BaseFrame.getInstance().view("LoginMain");
				BaseFrame.getInstance().getLoginMain().logSet();
				CsCloseSyn packet = new CsCloseSyn();
				ClientNet.getInstance().sendPacket(packet);
				dispose();
			}
		});
		add(jb);

		setVisible(false);
	}
}