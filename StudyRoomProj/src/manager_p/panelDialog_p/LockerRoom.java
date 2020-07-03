package manager_p.panelDialog_p;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import client_p.ui_p.LockerMain;
import java.awt.BorderLayout;

public class LockerRoom extends JPanel {
	TestFrame tfram;
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		JTabbedPane tabbedPane = new JTabbedPane();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(40, 100, 1000, 800);
		f.getContentPane().add(tabbedPane);
		tabbedPane.add("ªÁπ∞«‘", new LockerRoom());
		f.setVisible(true);
	}
	
	public LockerRoom() {
		setLayout(new BorderLayout(0, 0));
//	public LockerRoom(TestFrame tfram) {
//		this.tfram = tfram;
//		this.tfram.tabbedPane.addTab("ªÁπ∞«‘ ∞¸∏Æ", this);

		JPanel panel_1_1 = new JPanel();
		add(panel_1_1);
		GridBagLayout gbl_panel_1_1 = new GridBagLayout();
		gbl_panel_1_1.columnWidths = new int[] { 337, 618, 0 };
		gbl_panel_1_1.rowHeights = new int[] { 717, 0 };
		gbl_panel_1_1.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1_1.setLayout(gbl_panel_1_1);
		
		JPanel panel_7_1_1 = new JPanel();
		GridBagConstraints gbc_panel_7_1_1 = new GridBagConstraints();
		gbc_panel_7_1_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_7_1_1.fill = GridBagConstraints.BOTH;
		gbc_panel_7_1_1.gridx = 0;
		gbc_panel_7_1_1.gridy = 0;
		panel_1_1.add(panel_7_1_1, gbc_panel_7_1_1);
		GridBagLayout gbl_panel_7_1_1 = new GridBagLayout();
		gbl_panel_7_1_1.columnWidths = new int[]{112, 0};
		gbl_panel_7_1_1.rowHeights = new int[]{88, 0, 30, 0, 30, 0, 30, 0, 30, 0, 87, 0, 0, 0};
		gbl_panel_7_1_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_7_1_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_7_1_1.setLayout(gbl_panel_7_1_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("\uC0AC\uBB3C\uD568 \uC815\uBCF4");
		lblNewLabel_6_1.setFont(new Font("»ﬁ∏’ø¢Ω∫∆˜", Font.PLAIN, 35));
		GridBagConstraints gbc_lblNewLabel_6_1 = new GridBagConstraints();
		gbc_lblNewLabel_6_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_6_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6_1.gridx = 0;
		gbc_lblNewLabel_6_1.gridy = 1;
		panel_7_1_1.add(lblNewLabel_6_1, gbc_lblNewLabel_6_1);
		
		JPanel panel_23 = new JPanel();
		GridBagConstraints gbc_panel_23 = new GridBagConstraints();
		gbc_panel_23.insets = new Insets(0, 0, 5, 0);
		gbc_panel_23.fill = GridBagConstraints.BOTH;
		gbc_panel_23.gridx = 0;
		gbc_panel_23.gridy = 3;
		panel_7_1_1.add(panel_23, gbc_panel_23);
		
		JLabel lblNewLabel_7_2_1 = new JLabel(" \uC0AC\uBB3C\uD568 ");
		lblNewLabel_7_2_1.setFont(new Font("ªı±º∏≤", Font.BOLD, 20));
		panel_23.add(lblNewLabel_7_2_1);
		
		JLabel lblNewLabel_7_2_2 = new JLabel("1\uBC88");
		lblNewLabel_7_2_2.setFont(new Font("ªı±º∏≤", Font.BOLD, 20));
		panel_23.add(lblNewLabel_7_2_2);
		
		JPanel panel_16_2 = new JPanel();
		GridBagConstraints gbc_panel_16_2 = new GridBagConstraints();
		gbc_panel_16_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_16_2.fill = GridBagConstraints.BOTH;
		gbc_panel_16_2.gridx = 0;
		gbc_panel_16_2.gridy = 5;
		panel_7_1_1.add(panel_16_2, gbc_panel_16_2);
		GridBagLayout gbl_panel_16_2 = new GridBagLayout();
		gbl_panel_16_2.columnWidths = new int[]{210, 0, 0};
		gbl_panel_16_2.rowHeights = new int[]{0, 0};
		gbl_panel_16_2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_16_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_16_2.setLayout(gbl_panel_16_2);
		
		JLabel lblNewLabel_7_2 = new JLabel(" \uC0AC\uBB3C\uD568 \uC0AC\uC6A9\uC790 \uC774\uB984:");
		lblNewLabel_7_2.setFont(new Font("ªı±º∏≤", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_7_2 = new GridBagConstraints();
		gbc_lblNewLabel_7_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7_2.gridx = 0;
		gbc_lblNewLabel_7_2.gridy = 0;
		panel_16_2.add(lblNewLabel_7_2, gbc_lblNewLabel_7_2);
		
		JLabel lbLockUser = new JLabel("");
		GridBagConstraints gbc_lbLockUser = new GridBagConstraints();
		gbc_lbLockUser.anchor = GridBagConstraints.WEST;
		gbc_lbLockUser.gridx = 1;
		gbc_lbLockUser.gridy = 0;
		panel_16_2.add(lbLockUser, gbc_lbLockUser);
		
		JPanel panel_16 = new JPanel();
		GridBagConstraints gbc_panel_16 = new GridBagConstraints();
		gbc_panel_16.fill = GridBagConstraints.BOTH;
		gbc_panel_16.insets = new Insets(0, 0, 5, 0);
		gbc_panel_16.gridx = 0;
		gbc_panel_16.gridy = 7;
		panel_7_1_1.add(panel_16, gbc_panel_16);
		GridBagLayout gbl_panel_16 = new GridBagLayout();
		gbl_panel_16.columnWidths = new int[]{210, 0, 0};
		gbl_panel_16.rowHeights = new int[]{0, 0};
		gbl_panel_16.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_16.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_16.setLayout(gbl_panel_16);
		
		JLabel lblNewLabel_7 = new JLabel("\uC0AC\uBB3C\uD568 \uC774\uC6A9\uC5EC\uBD80:");
		lblNewLabel_7.setFont(new Font("ªı±º∏≤", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 0;
		panel_16.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JLabel lbLockIsUsing = new JLabel("");
		GridBagConstraints gbc_lbLockIsUsing = new GridBagConstraints();
		gbc_lbLockIsUsing.anchor = GridBagConstraints.WEST;
		gbc_lbLockIsUsing.gridx = 1;
		gbc_lbLockIsUsing.gridy = 0;
		panel_16.add(lbLockIsUsing, gbc_lbLockIsUsing);
		
		JPanel panel_16_1 = new JPanel();
		GridBagConstraints gbc_panel_16_1 = new GridBagConstraints();
		gbc_panel_16_1.fill = GridBagConstraints.BOTH;
		gbc_panel_16_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_16_1.gridx = 0;
		gbc_panel_16_1.gridy = 9;
		panel_7_1_1.add(panel_16_1, gbc_panel_16_1);
		GridBagLayout gbl_panel_16_1 = new GridBagLayout();
		gbl_panel_16_1.columnWidths = new int[]{210, 0, 0};
		gbl_panel_16_1.rowHeights = new int[]{0, 0};
		gbl_panel_16_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_16_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_16_1.setLayout(gbl_panel_16_1);
		
		JLabel lblNewLabel_7_1 = new JLabel("\uC0AC\uBB3C\uD568 \uBE44\uBC00\uBC88\uD638:");
		lblNewLabel_7_1.setFont(new Font("ªı±º∏≤", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_7_1 = new GridBagConstraints();
		gbc_lblNewLabel_7_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7_1.gridx = 0;
		gbc_lblNewLabel_7_1.gridy = 0;
		panel_16_1.add(lblNewLabel_7_1, gbc_lblNewLabel_7_1);
		
		JLabel lbLockPw = new JLabel("");
		GridBagConstraints gbc_lbLockPw = new GridBagConstraints();
		gbc_lbLockPw.anchor = GridBagConstraints.WEST;
		gbc_lbLockPw.gridx = 1;
		gbc_lbLockPw.gridy = 0;
		panel_16_1.add(lbLockPw, gbc_lbLockPw);

		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.VERTICAL;
		gbc_panel_11.gridx = 1;
		gbc_panel_11.gridy = 0;
		panel_1_1.add(panel_11, gbc_panel_11);
		GridBagLayout gbl_panel_11 = new GridBagLayout();
		gbl_panel_11.columnWidths = new int[] { 39, 173, 136, 145, 15, 0 };
		gbl_panel_11.rowHeights = new int[] { 150, 0, 0, 0, 0 };
		gbl_panel_11.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_11.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_11.setLayout(gbl_panel_11);

		JButton btnNewButton_4_1_1 = new JButton("\uC0AC\uBB3C\uD568 1\uBC88");
		btnNewButton_4_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4_1_1.setPreferredSize(new Dimension(170, 126));
		btnNewButton_4_1_1.setFont(new Font("ªı±º∏≤", Font.BOLD, 25));
		GridBagConstraints gbc_btnNewButton_4_1_1 = new GridBagConstraints();
		gbc_btnNewButton_4_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4_1_1.gridx = 1;
		gbc_btnNewButton_4_1_1.gridy = 1;
		panel_11.add(btnNewButton_4_1_1, gbc_btnNewButton_4_1_1);

		JButton btnNewButton_4_1 = new JButton("\uC0AC\uBB3C\uD568 2\uBC88");
		btnNewButton_4_1.setPreferredSize(new Dimension(170, 126));
		btnNewButton_4_1.setFont(new Font("ªı±º∏≤", Font.BOLD, 26));
		GridBagConstraints gbc_btnNewButton_4_1 = new GridBagConstraints();
		gbc_btnNewButton_4_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4_1.gridx = 2;
		gbc_btnNewButton_4_1.gridy = 1;
		panel_11.add(btnNewButton_4_1, gbc_btnNewButton_4_1);

		JButton btnNewButton_4_2 = new JButton("\uC0AC\uBB3C\uD568 3\uBC88");
		btnNewButton_4_2.setPreferredSize(new Dimension(170, 126));
		btnNewButton_4_2.setFont(new Font("ªı±º∏≤", Font.BOLD, 26));
		GridBagConstraints gbc_btnNewButton_4_2 = new GridBagConstraints();
		gbc_btnNewButton_4_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4_2.gridx = 3;
		gbc_btnNewButton_4_2.gridy = 1;
		panel_11.add(btnNewButton_4_2, gbc_btnNewButton_4_2);

		JButton btnNewButton_4_1_2 = new JButton("\uC0AC\uBB3C\uD568 4\uBC88");
		btnNewButton_4_1_2.setPreferredSize(new Dimension(170, 126));
		btnNewButton_4_1_2.setFont(new Font("ªı±º∏≤", Font.BOLD, 26));
		GridBagConstraints gbc_btnNewButton_4_1_2 = new GridBagConstraints();
		gbc_btnNewButton_4_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4_1_2.gridx = 1;
		gbc_btnNewButton_4_1_2.gridy = 2;
		panel_11.add(btnNewButton_4_1_2, gbc_btnNewButton_4_1_2);

		JButton btnNewButton_4_3 = new JButton("\uC0AC\uBB3C\uD568 5\uBC88");
		btnNewButton_4_3.setPreferredSize(new Dimension(170, 126));
		btnNewButton_4_3.setFont(new Font("ªı±º∏≤", Font.BOLD, 26));
		GridBagConstraints gbc_btnNewButton_4_3 = new GridBagConstraints();
		gbc_btnNewButton_4_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4_3.gridx = 2;
		gbc_btnNewButton_4_3.gridy = 2;
		panel_11.add(btnNewButton_4_3, gbc_btnNewButton_4_3);

		JButton btnNewButton_4_1_3 = new JButton("\uC0AC\uBB3C\uD568 6\uBC88");
		btnNewButton_4_1_3.setPreferredSize(new Dimension(170, 126));
		btnNewButton_4_1_3.setFont(new Font("ªı±º∏≤", Font.BOLD, 26));
		GridBagConstraints gbc_btnNewButton_4_1_3 = new GridBagConstraints();
		gbc_btnNewButton_4_1_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4_1_3.gridx = 3;
		gbc_btnNewButton_4_1_3.gridy = 2;
		panel_11.add(btnNewButton_4_1_3, gbc_btnNewButton_4_1_3);

		JButton btnNewButton_4_1_4 = new JButton("\uC0AC\uBB3C\uD568 7\uBC88");
		btnNewButton_4_1_4.setPreferredSize(new Dimension(170, 126));
		btnNewButton_4_1_4.setFont(new Font("ªı±º∏≤", Font.BOLD, 26));
		GridBagConstraints gbc_btnNewButton_4_1_4 = new GridBagConstraints();
		gbc_btnNewButton_4_1_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4_1_4.gridx = 1;
		gbc_btnNewButton_4_1_4.gridy = 3;
		panel_11.add(btnNewButton_4_1_4, gbc_btnNewButton_4_1_4);

		JButton btnNewButton_4_1_5 = new JButton("\uC0AC\uBB3C\uD568 8\uBC88");
		btnNewButton_4_1_5.setPreferredSize(new Dimension(170, 126));
		btnNewButton_4_1_5.setFont(new Font("ªı±º∏≤", Font.BOLD, 26));
		GridBagConstraints gbc_btnNewButton_4_1_5 = new GridBagConstraints();
		gbc_btnNewButton_4_1_5.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4_1_5.gridx = 2;
		gbc_btnNewButton_4_1_5.gridy = 3;
		panel_11.add(btnNewButton_4_1_5, gbc_btnNewButton_4_1_5);

		JButton btnNewButton_4_1_6 = new JButton("\uC0AC\uBB3C\uD568 9\uBC88");
		btnNewButton_4_1_6.setPreferredSize(new Dimension(170, 126));
		btnNewButton_4_1_6.setFont(new Font("ªı±º∏≤", Font.BOLD, 26));
		GridBagConstraints gbc_btnNewButton_4_1_6 = new GridBagConstraints();
		gbc_btnNewButton_4_1_6.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4_1_6.gridx = 3;
		gbc_btnNewButton_4_1_6.gridy = 3;
		panel_11.add(btnNewButton_4_1_6, gbc_btnNewButton_4_1_6);

		Dimension size1 = new Dimension();// ªÁ¿Ã¡Ó∏¶ ¡ˆ¡§«œ±‚ ¿ß«— ∞¥√º ª˝º∫
		size1.setSize(900, 1000);// ∞¥√º¿« ªÁ¿Ã¡Ó∏¶ ¡ˆ¡§
		LockerMain lockerMain = new LockerMain();
		lockerMain.setPreferredSize(size1);
	}

}
