package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RCalcPopFrame extends JFrame{

	public RCalcPopFrame() {
		setBounds(100, 100, 300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp = new JPanel();
		jp.setLayout(null);
		add(jp);
		
		JLabel jlb = new JLabel("<html>���� �� ���� <br>�Ϸ�<html>");
		jlb.setFont(new Font("���ü", Font.BOLD, 20));
		jlb.setHorizontalAlignment(SwingConstants.CENTER);
		jlb.setBounds(50, 0, 200, 200);
		jp.add(jlb);
		
		JButton jb= new JButton("Ȯ��");
		jb.setBounds(90, 170, 100, 70);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}});
		
		jp.add(jlb);
		jp.add(jb);
		
		setVisible(true);
	}
}