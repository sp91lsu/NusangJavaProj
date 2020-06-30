package client_p.ui_p;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import data_p.product_p.room_p.RoomProduct;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;

public class ReserInfoPane extends JFrame {

	private JPanel reserIPane;
	private ArrayList<ReserObj> reserList = new ArrayList<ReserObj>();
	HashMap<Object, Object> reserMap = new HashMap<Object, Object>();
	JPanel infoPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReserInfoPane frame = new ReserInfoPane();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ReserInfoPane() {
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 245, 650);
		reserIPane = new JPanel();
		reserIPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(reserIPane);
		reserIPane.setLayout(null);

		JLabel titleL = new JLabel("예약 현황");
		titleL.setFont(new Font("굴림", Font.BOLD, 30));
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		titleL.setBounds(12, 10, 205, 51);
		reserIPane.add(titleL);

		JLabel userDataL = new JLabel("유저 정보 넣을 공간 아이디, 이름정도?");
		userDataL.setBounds(22, 42, 205, 160);
		reserIPane.add(userDataL);

		JScrollPane reserInfoScroll = new JScrollPane();

		reserInfoScroll.setBounds(12, 215, 205, 327);
		reserIPane.add(reserInfoScroll);

		infoPane = new JPanel();
		infoPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		infoPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPane.setAlignmentY(Component.TOP_ALIGNMENT);
		infoPane.setBackground(Color.GREEN);
		reserInfoScroll.setViewportView(infoPane);
		infoPane.setBounds(10, 10, 150, 300);
		GridBagLayout gbl_infoPane = new GridBagLayout();
		gbl_infoPane.columnWidths = new int[] { 0, 0 };
		gbl_infoPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_infoPane.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_infoPane.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		infoPane.setLayout(gbl_infoPane);

	}

	public void OpenPage() {
		setVisible(true);
		infoPane.removeAll();
		ButtonGroup bg = new ButtonGroup();
//		Calendar 
		int gridY = 0;
		for (RoomProduct room : BaseFrame.getInstance().userData.myReservationList) {

			for (Calendar cal : room.calendarList) {
				
				GridBagConstraints gc = new GridBagConstraints();
				JCheckBox checkBox = new JCheckBox(room.name + " " + cal.getTime());
				ReserObj ro = new ReserObj(checkBox, room);
				reserList.add(ro);

				gridY++;
				gc.gridy = gridY;
				bg.add(checkBox);
				infoPane.add(checkBox, gc);
			}
		}
	}

	class ReserObj {
		JCheckBox cBox;
		RoomProduct room;

		ReserObj(JCheckBox cBox, RoomProduct room) {
			this.cBox = cBox;
			this.room = room;
		}
	}
}
