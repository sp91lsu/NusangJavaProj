package client_p.ui_p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import client_p.ClientNet;
import client_p.packet_p.syn_p.CsCloseSyn;

public class LogoutPopFrame extends JDialog{

	public LogoutPopFrame() {
		setBounds(700, 450, 279, 143);
		getContentPane().setLayout(null);

		JLabel jbl = new JLabel("로그아웃 하시겠습니까?");
		jbl.setBounds(0, 0, 263, 56);
		jbl.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jbl);
		JButton okBtn = new JButton("확인");
		okBtn.setBounds(26, 55, 96, 36);
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().getMainLayout().is_LogOut=false;
				BaseFrame.getInstance().view("LoginMain");
				BaseFrame.getInstance().getLoginMain().logSet();
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
				BaseFrame.getInstance().getMainLayout().is_LogOut=false;
				dispose();
			}
		});
		
		setVisible(false);
	}
}