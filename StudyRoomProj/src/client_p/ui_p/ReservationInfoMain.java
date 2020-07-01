package client_p.ui_p;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import client_p.EEnter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Color;

public class ReservationInfoMain extends JFrame {

	public ReservationInfoMain() {
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);

		String info = "  ��� ���͵���� �̿��� �ּż� �����մϴ�.\n\t    ���� �� ���� ����\n\n1.������ 1�ð� ������ �����մϴ�.\n"
				+ "2.���� �� ���� ������ �Ұ��մϴ�.\n3.���� �� ��¥�� �ð��� �� Ȯ���Ͻñ� �ٶ��ϴ�.\n"+
				"4.������ ����� ��¥���� �ߺ� ������ �Ұ��մϴ�.";
		String headName = "     ��� ���͵��\n     �����̿� �ȳ�";

		JTextArea frontName = new JTextArea();
		frontName.setBackground(Color.YELLOW);
		frontName.setFont(new Font("����", Font.BOLD, 34));
		frontName.setBounds(55, 29, 370, 92);
		frontName.setText(headName);
		getContentPane().add(frontName);

		JTextArea rInfo = new JTextArea();
		rInfo.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rInfo.setBounds(40, 149, 405, 211);
		rInfo.setText(info);
		getContentPane().add(rInfo);

		JButton btnNewButton = new JButton("�����Ϸ� ����");
		btnNewButton.setFont(new Font("����", Font.BOLD, 16));
		btnNewButton.setBounds(68, 375, 151, 54);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				BaseFrame.getInstance().openSeatingArrUI(EEnter.MOBILE);
				dispose();
			}
		});
		getContentPane().add(btnNewButton);

		JButton button = new JButton("�α׾ƿ�");
		button.setFont(new Font("����", Font.BOLD, 16));
		button.setBounds(274, 375, 151, 54);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().getLoginMain().logSet();
				dispose();
			}
		});
		getContentPane().add(button);

		setVisible(true);
	}

}
