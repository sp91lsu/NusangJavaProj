package manager_p;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

public class managerLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managerLogin frame = new managerLogin();
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
	public managerLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1280, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{5.0, 1.0, 1.0, 1.0, 10.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		contentPane.add(panel_4, gbc_panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("\uAD00\uB9AC\uC790 \uB85C\uADF8\uC778");
		lblNewLabel_1.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 40));
		panel_4.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{445, 390, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPw_1 = new JLabel("ID");
		lblPw_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw_1.setFont(new Font("HY°ß°íµñ", Font.BOLD, 30));
		GridBagConstraints gbc_lblPw_1 = new GridBagConstraints();
		gbc_lblPw_1.anchor = GridBagConstraints.EAST;
		gbc_lblPw_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblPw_1.gridx = 0;
		gbc_lblPw_1.gridy = 0;
		panel.add(lblPw_1, gbc_lblPw_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.TRAILING);
		textField_1.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 0;
		panel.add(textField_1, gbc_textField_1);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {445, 390, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblPw = new JLabel("PW");
		GridBagConstraints gbc_lblPw = new GridBagConstraints();
		gbc_lblPw.anchor = GridBagConstraints.EAST;
		gbc_lblPw.insets = new Insets(0, 0, 0, 5);
		gbc_lblPw.gridx = 0;
		gbc_lblPw.gridy = 0;
		panel_1.add(lblPw, gbc_lblPw);
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw.setFont(new Font("HY°ß°íµñ", Font.BOLD, 30));
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 0;
		panel_1.add(passwordField, gbc_passwordField);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		contentPane.add(panel_2, gbc_panel_2);
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC778");
		btnNewButton.setFont(new Font("±¼¸²", Font.BOLD, 30));
		panel_2.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 4;
		contentPane.add(panel_3, gbc_panel_3);
	}
}
