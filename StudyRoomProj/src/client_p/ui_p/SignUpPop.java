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
		jdd.setBounds(50, 50, 100, 100);
		jdd.setLayout(new GridLayout(2, 1));
		JLabel jll = new JLabel("������ �Է��ϼ���");
		
		JButton jbb = new JButton("Ȯ��");
		jdd.add(jll);
		jdd.add(jbb);
		jbb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jdd.dispose();
				
			}});
		jdd.setVisible(true);
	}
}