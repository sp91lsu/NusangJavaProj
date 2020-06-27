package client_p.ui_p;

import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data_p.product_p.room_p.RoomProduct;

public class AddTimeFrame extends JFrame {

	private JPanel contentPane;

	int startTime;
	int endTime;

	public static void main(String[] args) {
		AddTimeFrame frame = new AddTimeFrame();
	}

	public AddTimeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleL = new JLabel("좌석 연장");
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		titleL.setBounds(94, 10, 273, 54);
		contentPane.add(titleL);

		JLabel timeInfoL = new JLabel("시 까지 연장");
		timeInfoL.setBounds(270, 112, 73, 31);
		contentPane.add(timeInfoL);

		JButton payButton = new JButton("결제");
		payButton.setBounds(91, 188, 110, 43);
		contentPane.add(payButton);

		JButton cancelButton = new JButton("취소");
		cancelButton.setBounds(243, 188, 110, 43);
		contentPane.add(cancelButton);

		Vector<Integer> timeCnt = new Vector<Integer>();
		for (int i = 1; i <= 24; i++) {
			timeCnt.add(i);
		}
		JComboBox timeSelectCom = new JComboBox(timeCnt);
		timeSelectCom.setBounds(185, 112, 73, 31);
		contentPane.add(timeSelectCom);

		setVisible(true);
		 timeChoice() ;
	}

	public void timeChoice() {

		RoomProduct lastRoom = BaseFrame.getInstance().userData.getTodayLast();

		Calendar startCal = lastRoom.calendarList.get(0);

		System.out.println("내가 이용하고 있는 룸 " + lastRoom.name);
		for (RoomProduct comRoom : BaseFrame.getInstance().roomInfoList) {

			if (comRoom.id == lastRoom.id) {
				for (Calendar cal : comRoom.calendarList) {

					if (BaseFrame.getInstance().isSameTime(Calendar.DATE, startCal, cal)
							&& startCal.getTimeInMillis() < cal.getTimeInMillis()) {
						System.out.println("내 마지막 시간 뒤에 누군가가 있따" + cal.getTime());
					}
				}
			}
		}
	}

}
