package client_p.ui_p;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SignUpPop extends JFrame {

	public SignUpPop() {
		
		JDialog jdd = new JDialog();
		jdd.setBounds(830, 405, 300, 200);
		jdd.setLayout(new GridLayout(2, 1));
		
		JLabel jll = new JLabel("��Ȯ�� ������  �Է��ϼ���");
		jdd.add(jll);
		
		JButton jbb = new JButton("Ȯ��");
		jdd.add(jbb);
		
		jbb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdd.dispose();
			}});
		jdd.setUndecorated(true);
		jdd.setVisible(true);
	}
}