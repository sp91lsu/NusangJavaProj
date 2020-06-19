package client_p.ui_p;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Seating_Arrangement extends JPanel {
	private JPanel contentPane;
	static JLabel north_west;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500, 30, 900, 1000);
		frame.add(new Seating_Arrangement());
		frame.setVisible(true);
	}

	public Seating_Arrangement() {
		setLayout(new BorderLayout(0, 0));

		// 상단 패널
		JPanel panel_north = new JPanel();
		add(panel_north, BorderLayout.NORTH);
		panel_north.setLayout(new BorderLayout(0, 0));

		this.north_west = new JLabel("2020-06-18  21:24");// 날자,시간
		north_west.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_north.add(north_west, BorderLayout.WEST);

		JButton north_east = new JButton("뒤로가기");
		north_east.setFont(new Font("굴림", Font.PLAIN, 17));
		panel_north.add(north_east, BorderLayout.EAST);
		north_east.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 뒤로가기 이벤트
			}
		});

		JPanel north_center = new JPanel();
		panel_north.add(north_center, BorderLayout.CENTER);
		north_center.setLayout(new BorderLayout(0, 0));

		JPanel north_center_center = new JPanel();
		north_center.add(north_center_center, BorderLayout.CENTER);
//		      north_center_center.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		north_center_center.add(panel_5);

		JPanel panel_6 = new JPanel();
		north_center_center.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("이용가능");
		panel_6.add(lblNewLabel_1);

		ImageIcon umgIc = new ImageIcon("img/green.png");
		JLabel lblNewLabel_2 = new JLabel(umgIc);
		panel_6.add(lblNewLabel_2);

		JPanel panel_7 = new JPanel();
		north_center_center.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_3 = new JLabel("사용중");
		panel_7.add(lblNewLabel_3);

		umgIc = new ImageIcon("img/red.png");
		JLabel lblNewLabel_4 = new JLabel(umgIc);
		panel_7.add(lblNewLabel_4);

		JPanel panel_8 = new JPanel();
		north_center_center.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_5 = new JLabel("현재선택");
		panel_8.add(lblNewLabel_5);

		umgIc = new ImageIcon("img/blue.png");
		JLabel lblNewLabel_6 = new JLabel(umgIc);
		panel_8.add(lblNewLabel_6);

		JPanel north_center_west = new JPanel();
		north_center.add(north_center_west, BorderLayout.WEST);

		JPanel north_center_east = new JPanel();
		north_center.add(north_center_east, BorderLayout.EAST);

		// 중앙패널
		JPanel panel_center = new JPanel();
		add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(null);

		// 여기서부터 룸 버튼
		JButton roomBtn1 = new JButton("샤워실");
		roomBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Payment pm = new Payment(roomBtn1.getText());
			}
		});
		roomBtn1.setBounds(0, 0, 300, 180);
		panel_center.add(roomBtn1);

		JButton roomBtn2 = new JButton("8인실");
		roomBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment(roomBtn2.getText());
			}
		});
		roomBtn2.setBounds(300, 0, 250, 180);
		panel_center.add(roomBtn2);

		JButton roomBtn3 = new JButton("6인실-1");
		roomBtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment(roomBtn3.getText());
			}
		});
		roomBtn3.setBounds(550, 0, 160, 180);
		panel_center.add(roomBtn3);

		JButton roomBtn4 = new JButton("6인실-2");
		roomBtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment(roomBtn4.getText());
			}
		});
		roomBtn4.setBounds(710, 0, 160, 180);
		panel_center.add(roomBtn4);

		JButton roomBtn5 = new JButton("4인실-1");
		roomBtn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment(roomBtn5.getText());
			}
		});
		roomBtn5.setBounds(710, 220, 160, 150);
		panel_center.add(roomBtn5);

		JButton roomBtn6 = new JButton("4인실-2");
		roomBtn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment(roomBtn6.getText());
			}
		});
		roomBtn6.setBounds(710, 370, 160, 150);
		panel_center.add(roomBtn6);

		JButton roomBtn7 = new JButton("2인실-1");
		roomBtn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment(roomBtn7.getText());
			}
		});
		roomBtn7.setBounds(710, 520, 160, 100);
		panel_center.add(roomBtn7);

		JButton roomBtn8 = new JButton("2인실-2");
		roomBtn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment(roomBtn8.getText());
			}
		});
		roomBtn8.setBounds(710, 620, 160, 100);
		panel_center.add(roomBtn8);

		JButton roomBtn9 = new JButton("2인실-3");
		roomBtn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment(roomBtn9.getText());
			}
		});
		roomBtn9.setBounds(710, 720, 160, 100);
		panel_center.add(roomBtn9);

		JButton roomBtn10 = new JButton("노래방");
		roomBtn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment(roomBtn10.getText());
			}
		});
		roomBtn10.setBounds(0, 180, 160, 240);
		panel_center.add(roomBtn10);

		JButton roomBtn11 = new JButton("취식");
		roomBtn11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment(roomBtn11.getText());
			}
		});
		roomBtn11.setBounds(0, 420, 160, 240);
		panel_center.add(roomBtn11);

		// 매너존 패널
		JPanel mannerzone_panel = new JPanel();
		mannerzone_panel.setBackground(Color.GREEN);
		mannerzone_panel.setBounds(0, 660, 300, 250);
		panel_center.add(mannerzone_panel);
		mannerzone_panel.setLayout(null);

		// 매너존 라벨,버튼
		JLabel label_manner = new JLabel("매너존");
		label_manner.setFont(new Font("굴림", Font.PLAIN, 20));
		label_manner.setBounds(120, 115, 70, 20);
		mannerzone_panel.add(label_manner);

		JButton btnM_1 = new JButton("M1");
		btnM_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment("매너존 " + btnM_1.getText());
			}
		});
		btnM_1.setBounds(0, 0, 100, 90);
		mannerzone_panel.add(btnM_1);

		JButton btnM_2 = new JButton("M2");
		btnM_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment("매너존 " + btnM_2.getText());
			}
		});
		btnM_2.setBounds(100, 0, 100, 90);
		mannerzone_panel.add(btnM_2);

		JButton btnM_3 = new JButton("M3");
		btnM_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment("매너존 " + btnM_3.getText());
			}
		});
		btnM_3.setBounds(0, 160, 100, 90);
		mannerzone_panel.add(btnM_3);

		JButton btnM_4 = new JButton("M4");
		btnM_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment("매너존 " + btnM_4.getText());
			}
		});
		btnM_4.setBounds(100, 160, 100, 90);
		mannerzone_panel.add(btnM_4);

		JButton btnM_5 = new JButton("M5");
		btnM_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment("매너존 " + btnM_5.getText());
			}
		});
		btnM_5.setBounds(200, 160, 100, 90);
		mannerzone_panel.add(btnM_5);
		// 일반석 패널
		JPanel normalzone_panel = new JPanel();
		normalzone_panel.setBackground(Color.CYAN);
		normalzone_panel.setBounds(300, 300, 300, 250);
		panel_center.add(normalzone_panel);
		normalzone_panel.setLayout(null);
		// 일반실 라벨,버튼
		JLabel label_normal = new JLabel("일반실");
		label_normal.setFont(new Font("굴림", Font.PLAIN, 20));
		label_normal.setBounds(120, 115, 70, 20);
		normalzone_panel.add(label_normal);

		JButton btnN_1 = new JButton("1");
		btnN_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment("일반실 " + btnN_1.getText());
			}
		});
		btnN_1.setBounds(0, 0, 100, 90);
		normalzone_panel.add(btnN_1);

		JButton btnN_2 = new JButton("2");
		btnN_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment("일반실 " + btnN_2.getText());
			}
		});
		btnN_2.setBounds(100, 0, 100, 90);
		normalzone_panel.add(btnN_2);

		JButton btnN_3 = new JButton("3");
		btnN_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment("일반실 " + btnN_3.getText());
			}
		});
		btnN_3.setBounds(200, 0, 100, 90);
		normalzone_panel.add(btnN_3);

		JButton btnN_4 = new JButton("4");
		btnN_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment("일반실 " + btnN_4.getText());
			}
		});
		btnN_4.setBounds(0, 160, 100, 90);
		normalzone_panel.add(btnN_4);

		JButton btnN_5 = new JButton("5");
		btnN_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment("일반실 " + btnN_5.getText());
			}
		});
		btnN_5.setBounds(100, 160, 100, 90);
		normalzone_panel.add(btnN_5);

		JButton btnN_6 = new JButton("6");
		btnN_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pm = new Payment("일반실 " + btnN_6.getText());
			}
		});
		btnN_6.setBounds(200, 160, 100, 90);
		normalzone_panel.add(btnN_6);

		// 휴게실
		JPanel restzone_panel = new JPanel();
		restzone_panel.setBackground(Color.ORANGE);
		restzone_panel.setBounds(300, 710, 260, 200);
		panel_center.add(restzone_panel);
		restzone_panel.setLayout(null);
		// 휴게실 안에 들어갈것은 여기서
		JLabel label_rest = new JLabel("휴게실");
		label_rest.setFont(new Font("굴림", Font.PLAIN, 20));
		label_rest.setBounds(100, 100, 70, 20);
		restzone_panel.add(label_rest);

		JPanel locker = new JPanel();
		locker.setBackground(Color.LIGHT_GRAY);
		locker.setBounds(560, 710, 30, 200);
		panel_center.add(locker);
		locker.setLayout(null);

		JLabel lblNewLabel = new JLabel("<html>사<br>물<br>함<html>");
		lblNewLabel.setBounds(0, 60, 32, 79);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		locker.add(lblNewLabel);
		
		SetNowTime now_time = new SetNowTime(north_west);
		now_time.start();
		setVisible(true);
	}
}
