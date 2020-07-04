package client_p.ui_p;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import data_p.product_p.room_p.RoomProduct;

public class ReserInfoPane extends JPanel {

	private ArrayList<ReserObj> reserList = new ArrayList<ReserObj>();
	HashMap<Object, Object> reserMap = new HashMap<Object, Object>();
	JPanel infoPane;
	String userName = "";
	String userId = "";
	JLabel userDataL;

	public ReserInfoPane() {

		setBounds(650, 0, 245, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(MyColor.beige);
		setLayout(null);
		setVisible(true);

		JLabel titleL = new JLabel("예약 현황");
		titleL.setBounds(12, 10, 205, 51);
		titleL.setFont(new Font("굴림", Font.BOLD, 30));
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleL);

		JScrollPane reserInfoScroll = new JScrollPane();
		reserInfoScroll.setBounds(5, 215, 220, 327);
		add(reserInfoScroll);

		infoPane = new JPanel();
		infoPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		infoPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPane.setAlignmentY(Component.TOP_ALIGNMENT);
		infoPane.setBackground(MyColor.jinBeige);
		reserInfoScroll.setViewportView(infoPane);
		infoPane.setBounds(10, 10, 150, 300);
		infoPane.setLayout(new GridLayout(0, 1, 0, 5));

		userDataL = new JLabel();
		userDataL.setBounds(12, 71, 210, 90);
		add(userDataL);
	}

	public void OpenPage() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf = new SimpleDateFormat("yyyy-MM-dd (E) HH시");
		
		userName = BaseFrame.getInstance().userData.name;
		userId = BaseFrame.getInstance().userData.id;
		userDataL.setText("<html>" + userId + "(" + userName + ")님" + "<br>환영합니다.");

		infoPane.removeAll();
		ButtonGroup bg = new ButtonGroup();
		int gridY = 0;
		for (RoomProduct room : BaseFrame.getInstance().userData.myReservationList) {

			for (Calendar cal : room.calendarList) {
				GridBagConstraints gc = new GridBagConstraints();
				JCheckBox checkBox = new JCheckBox(room.name + " " + sdf.format(cal.getTime()));
				checkBox.setBackground(MyColor.jinBeige);
				ReserObj ro = new ReserObj(checkBox, room, cal);
				reserList.add(ro);

				gc.gridy = gridY;
				gridY++;
				bg.add(checkBox);
				infoPane.add(checkBox, gc);
			}
		}
	}

	class ReserObj {
		JCheckBox cBox;
		RoomProduct room;
		Calendar cal;

		ReserObj(JCheckBox cBox, RoomProduct room, Calendar cal) {
			this.cBox = cBox;
			this.room = room;
			this.cal = cal;
			cBox.addActionListener(new showReserSeatAct(cal));
		}
	}

	class showReserSeatAct implements ActionListener {

		Calendar cal;

		showReserSeatAct(Calendar cal) {
			this.cal = cal;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			BaseFrame.getInstance().getSeatingArrUI().btn_click=false;
			System.out.println(cal.getTime());
			BaseFrame.getInstance().getSeatingArrUI().setMyTimeShow(cal, cal.get(Calendar.HOUR_OF_DAY));
		}
	}
}
