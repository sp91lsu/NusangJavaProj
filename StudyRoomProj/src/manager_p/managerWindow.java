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

public class managerWindow extends JFrame {

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
		tabbedPane.setToolTipText("È¸¿ø");
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("\uD68C\uC6D0\uAD00\uB9AC", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\uC88C\uC11D/\uB8F8 \uAD00\uB9AC", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\uC0AC\uBB3C\uD568 \uAD00\uB9AC", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("\uC608\uC57D \uAD00\uB9AC", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("\uC694\uAE08 \uAD00\uB9AC", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("\uC815\uC0B0", null, panel_5, null);
	}
}
