package client_p.ui_p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LogoutPopFrame extends JDialog{

	public LogoutPopFrame() {
		setModal(true);
		setBounds(820, 470, 280, 140);
		getContentPane().setBackground(MyColor.black);
		getContentPane().setLayout(null);

		JLabel jbl = new JLabel("�α׾ƿ� �Ͻðڽ��ϱ�?");
		jbl.setBounds(0, 0, 263, 56);
		jbl.setHorizontalAlignment(SwingConstants.CENTER);
		jbl.setForeground(Color.white);
		getContentPane().add(jbl);
		JButton okBtn = new JButton("Ȯ��");
		okBtn.setBackground(MyColor.w_white);
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
		
		JButton cancelBtn = new JButton("���");
		cancelBtn.setBounds(143, 55, 96, 36);
		getContentPane().add(cancelBtn);
		cancelBtn.setBackground(MyColor.w_white);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().getMainLayout().is_LogOut=false;
				dispose();
			}
		});
		
		setVisible(false);
		setUndecorated(true);
	}
}