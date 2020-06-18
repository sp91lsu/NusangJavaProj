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

public class MainLayout extends JFrame {

	private JPanel mainPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLayout frame = new MainLayout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 1000);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(82, 168, 725, 430);
		mainPane.add(panel);
		panel.setLayout(new GridLayout(3,3,5,5));
		
		JButton button_1 = new JButton("°³ÀÎ¼® ÀÌ¿ë");
		button_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_1);
		
		JButton button_2 = new JButton("´ÜÃ¼·ë ÀÌ¿ë");
		button_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_2);
		
		JButton button_3 = new JButton("»ç¹°ÇÔ ´ë¿©");
		button_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_3);
		
		JButton button_4 = new JButton("1:1 °í°´¹®ÀÇ");
		button_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_4);
		
		JButton button_5 = new JButton("°³ÀÎ¼® ÀÌµ¿");
		button_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_5);
		
		JButton button_6 = new JButton("ÁÂ¼® ¿¬Àå");
		button_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_6);
		
		JButton button_7 = new JButton("ÀÜ¿© ½Ã°£");
		button_7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_7);
		
		JButton button_8 = new JButton("³» ÀÌ¿ëÁ¤º¸");
		button_8.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_8);
		
		JButton button_9 = new JButton("Åð½Ç");
		button_9.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(button_9);
		
		JLabel lblNewLabel = new JLabel("·Î±×ÀÎ ÈÄ È­¸é");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 35));
		lblNewLabel.setBounds(261, 10, 396, 107);
		mainPane.add(lblNewLabel);
	}

}
