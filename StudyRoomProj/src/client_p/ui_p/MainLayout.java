package client_p.ui_p;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sun.net.www.content.image.jpeg;

public class MainLayout extends JPanel {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 1000);
		frame.add(new MainLayout());
		frame.setVisible(true);
	}
	
	public MainLayout() {
		System.out.println("m");
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(82, 168, 725, 430);
		add(panel);
		panel.setLayout(new GridLayout(3,3,5,5));
		
		JButton button_1 = new JButton("���μ� �̿�");
		button_1.setFont(new Font("���� ����", Font.BOLD, 20));
		panel.add(button_1);
		
		JButton button_2 = new JButton("��ü�� �̿�");
		button_2.setFont(new Font("���� ����", Font.BOLD, 20));
		panel.add(button_2);
		
		JButton button_3 = new JButton("�繰�� �뿩");
		button_3.setFont(new Font("���� ����", Font.BOLD, 20));
		panel.add(button_3);
		
		JButton button_4 = new JButton("1:1 ��������");
		button_4.setFont(new Font("���� ����", Font.BOLD, 20));
		panel.add(button_4);
		
		JButton button_5 = new JButton("���μ� �̵�");
		button_5.setFont(new Font("���� ����", Font.BOLD, 20));
		panel.add(button_5);
		
		JButton button_6 = new JButton("�¼� ����");
		button_6.setFont(new Font("���� ����", Font.BOLD, 20));
		panel.add(button_6);
		
		JButton button_7 = new JButton("�ܿ� �ð�");
		button_7.setFont(new Font("���� ����", Font.BOLD, 20));
		panel.add(button_7);
		
		JButton button_8 = new JButton("�� �̿�����");
		button_8.setFont(new Font("���� ����", Font.BOLD, 20));
		panel.add(button_8);
		
		JButton button_9 = new JButton("���");
		button_9.setFont(new Font("���� ����", Font.BOLD, 20));
		panel.add(button_9);
		
		JLabel lblNewLabel = new JLabel("�α��� �� ȭ��");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("���� ����", Font.BOLD, 35));
		lblNewLabel.setBounds(261, 10, 396, 107);
		add(lblNewLabel);
	}

}