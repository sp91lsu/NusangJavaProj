package manager_p;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class managerWindow extends JFrame {
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JScrollPane scrollPane_3;
	private JScrollPane scrollPane_4;
	private JPanel panel_6;
	private CardLayout cl_panel_6;
	private JScrollPane scrollPane_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managerWindow frame = new managerWindow();
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
	public managerWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1280, 900);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("회원");
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("\uD68C\uC6D0\uAD00\uB9AC2", null, panel_5, null);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{162, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_7.anchor = GridBagConstraints.NORTH;
		gbc_panel_7.insets = new Insets(0, 0, 0, 5);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 1;
		panel_5.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 0};
		gbl_panel_7.rowHeights = new int[]{88, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_7.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JLabel lblNewLabel = new JLabel("\uBA54\uB274");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel_7.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnNewButton = new JButton("\uD604\uC7AC \uC774\uC6A9\uC911 \uACE0\uAC1D");
		btnNewButton.addActionListener(new CardSwitch(panel_6, cl_panel_6));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		panel_7.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uC804\uCCB4 \uACE0\uAC1D");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 5;
		panel_7.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\uD68C\uC6D0\uAC80\uC0C9");
		btnNewButton_2.addActionListener(new CardSwitch(panel_6, cl_panel_6));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 7;
		panel_7.add(btnNewButton_2, gbc_btnNewButton_2);
		
		panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 1;
		panel_5.add(panel_6, gbc_panel_6);
		cl_panel_6 = new CardLayout(0, 0);
		panel_6.setLayout(cl_panel_6);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_6.add("scrollPane_3",scrollPane_3);
		
		table_1 = new JTable();
		table_1.setEnabled(false);
		table_1.setFillsViewportHeight(true);
		table_1.setBackground(Color.RED);
		scrollPane_3.setViewportView(table_1);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_6.add("scrollPane_4",scrollPane_4);
		
		table_2 = new JTable();
		table_2.setBackground(Color.ORANGE);
		table_2.setFillsViewportHeight(true);
		scrollPane_4.setViewportView(table_2);
		
		scrollPane_5 = new JScrollPane();
		panel_6.add("scrollPane_5",scrollPane_5);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("\uD68C\uC6D0\uAD00\uB9AC", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{25, 1167, 25, 0};
		gbl_panel.rowHeights = new int[]{0, 240, 15, 240, 0, 240, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 3;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 5;
		panel.add(scrollPane_2, gbc_scrollPane_2);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\uC88C\uC11D/\uB8F8 \uAD00\uB9AC", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\uC0AC\uBB3C\uD568 \uAD00\uB9AC", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("\uC608\uC57D \uAD00\uB9AC", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("\uC694\uAE08 \uAD00\uB9AC", null, panel_4, null);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		
		
		
	}
	
	protected JPanel getPanel_6() {
		return panel_6;
	}
}

class CardSwitch implements ActionListener{
	
	CardLayout card;
	JPanel jp;
	
	
	
	public CardSwitch(JPanel parentPanel,CardLayout cardLay) {
		this.card = cardLay;
		this.jp = parentPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		switch (str) {
		case "현재 이용중 고객":
			card.show(jp, "scrollPane_3");
			break;
		case "전체 고객":
			card.show(jp, "scrollPane_4");
			break;
		case "회원 검색":
			card.show(jp, "scrollPane_5");
			break;

		default:
			break;
		}
	}
	
}
