package client_p.ui_p;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class LockerPWFrame implements ActionListener{

	private JFrame frame;
	private JPasswordField passwordField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LockerPWFrame window = new LockerPWFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LockerPWFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("����", Font.BOLD, 40));
		passwordField.setBounds(77, 53, 320, 74);
		frame.getContentPane().add(passwordField);
		
		ButtonGroup bg = new ButtonGroup();
		
		JButton btnNewButton = new JButton("1");
		btnNewButton.setBounds(39, 142, 129, 55);
		frame.getContentPane().add(btnNewButton);
		bg.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		
		JButton button = new JButton("2");
		button.setBounds(169, 142, 129, 55);
		frame.getContentPane().add(button);
		bg.add(button);
		button.addActionListener(this);
		
		JButton button_1 = new JButton("3");
		button_1.setBounds(300, 142, 129, 55);
		frame.getContentPane().add(button_1);
		bg.add(button_1);
		button_1.addActionListener(this);
		
		JButton button_2 = new JButton("4");
		button_2.setBounds(300, 200, 129, 55);
		frame.getContentPane().add(button_2);
		bg.add(button_2);
		button_2.addActionListener(this);
		
		JButton button_3 = new JButton("5");
		button_3.setBounds(169, 200, 129, 55);
		frame.getContentPane().add(button_3);
		bg.add(button_3);
		button_3.addActionListener(this);
		
		JButton button_4 = new JButton("6");
		button_4.setBounds(39, 200, 129, 55);
		frame.getContentPane().add(button_4);
		bg.add(button_4);
		button_4.addActionListener(this);
		
		JButton button_5 = new JButton("7");
		button_5.setBounds(300, 257, 129, 55);
		frame.getContentPane().add(button_5);
		bg.add(button_5);
		button_5.addActionListener(this);
		
		JButton button_6 = new JButton("8");
		button_6.setBounds(169, 257, 129, 55);
		frame.getContentPane().add(button_6);
		bg.add(button_6);
		button_6.addActionListener(this);
		
		JButton button_7 = new JButton("9");
		button_7.setBounds(39, 257, 129, 55);
		frame.getContentPane().add(button_7);
		bg.add(button_7);
		button_7.addActionListener(this);
		
		JButton button_8 = new JButton("Ȯ��");
		button_8.setBounds(111, 324, 129, 55);
		frame.getContentPane().add(button_8);
		button_8.addActionListener(this);
		
		JButton button_9 = new JButton("���");
		button_9.setBounds(248, 324, 129, 55);
		frame.getContentPane().add(button_9);
		button_9.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("��й�ȣ 4�ڸ��� �Է��ϼ���");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(28, 0, 421, 49);
		frame.getContentPane().add(lblNewLabel);
	}
	String text = "";

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton keyPoint = (JButton) e.getSource();
		if (passwordField.getText().length() < 4) {

			if (keyPoint.getText() != "BackSpace" && keyPoint.getText() != "Ȯ��" 
					&& keyPoint.getText() != "���") {
				text += keyPoint.getText();
			} else if (keyPoint.getText() == "BackSpace")
				textBack();

			passwordField.setText(text);

		}
		
		if(keyPoint.getText().equals("Ȯ��")) {
			
		}
		
		if (keyPoint.getText().equals("���")) {
			frame.setVisible(false);
		}
	}
      
      void textBack()
      {
         if(text.length() > 0)
         text =  text.substring(0,text.length()-1);
      }

		
	
	
}
