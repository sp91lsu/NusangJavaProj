package client_p.ui_p;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LogoutPopFrame extends JFrame{

	public LogoutPopFrame() {
		setBounds(50, 50, 150, 150);
		setLayout(new GridLayout(2, 1));

		JLabel jbl = new JLabel("로그아웃 하시겠습니까?");
		jbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(jbl);
		JButton jb = new JButton("확인");
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().view("LoginMain");
				BaseFrame.getInstance().getLoginMain().logSet();
				dispose();
			}
		});
		add(jb);

		setVisible(false);
	}
}