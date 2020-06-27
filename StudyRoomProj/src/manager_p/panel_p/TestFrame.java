package manager_p.panel_p;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TestFrame extends JFrame {

	JTabbedPane tabbedPane = new JTabbedPane();
	LockerRoom pnl_Locker = new LockerRoom();
	SetPrice pnl_SetPrice = new SetPrice();
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestFrame() {
		System.out.println("TestFrame 실행");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(40, 100, 1000, 800);
		getContentPane().add(tabbedPane);
		tabbedPane.add("사물함", pnl_Locker);
		tabbedPane.add("요금 관리", pnl_SetPrice);
	
//		JPanel contentPane = new JPanel(new BorderLayout());;
//		setContentPane(contentPane);
//		JTabbedPane tabbedPane = new JTabbedPane();
//		contentPane.add(tabbedPane);
		
//		tabbedPane.addChangeListener(new ChangeListener() {
//		public void stateChanged(ChangeEvent evt) {
//	        JTabbedPane pane = (JTabbedPane) evt.getSource();
//	        int sel = pane.getSelectedIndex();
//	        if(sel==3) {
//	        }
//			}
//	    });
	}

}
