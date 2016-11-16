import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Seats extends JFrame implements ActionListener {

	JButton resv = new JButton("예약");
	JButton prev = new JButton("이전");
	JButton reset = new JButton("다시선택");
	JButton home = new JButton("홈으로");
	JPanel center = new JPanel();
	JPanel center2 = new JPanel();

	int num = 0;
	String c[] = {"0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
//	String cc[] = { "seat0", "seat1", "seat2", "seat3", "seat4", "seat5", "seat6", "seat7", "seat8", "seat9" };

	String date;
	String title;
	String time;
	String theater;
	String id = Login.staticid;
	String seat;
	String seatal;
	String seatnum;

	JCheckBox[][] seats = new JCheckBox[11][11];

	String[] str = { "A01", "A02", "A03", "A04", "A05", "A06", "A07", "A08", "A09", "A10", "B01", "B02", "B03", "B04",
			"B05", "B06", "B07", "B08", "B09", "B10", "C01", "C02", "C03", "C04", "C05", "C06", "C07", "C08", "C09",
			"C10", "D01", "D02", "D03", "D04", "D05", "D06", "D07", "D08", "D09", "D10", "E01", "E02", "E03", "E04",
			"E05", "E06", "E07", "E08", "E09", "E10", "F01", "F02", "F03", "F04", "F05", "F06", "F07", "F08", "F09",
			"F10", "G01", "G02", "G03", "G04", "G05", "G06", "G07", "G08", "G09", "G10", "H01", "H02", "H03", "H04",
			"H05", "H06", "H07", "H08", "H09", "H10", "I01", "I02", "I03", "I04", "I05", "I06", "I07", "I08", "I09",
			"I10", "J01", "J02", "J03", "J04", "J05", "J06", "J07", "J08", "J09", "J10" };

	JLabel cen = new JLabel();

	DBMgr mgr = new DBMgr(); // DAO
	ArrayList<SeatBean> list;
	MbBean bean; // DTO

	public Seats(String DATE, String title, String TIME, String theater) {
		// TODO Auto-generated constructor stub

		this.date = DATE;
		this.title = title;
		this.time = TIME;
		this.theater = theater;

		setTitle("좌석선택");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setSize(1100, 800);

		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2), (int) (di.getHeight() / 2 - di1.getHeight() / 2));
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1100, 800);
		layeredPane.setLayout(null);

		JPanel myPanel = new MyPanel();
		myPanel.setLayout(null);
		myPanel.setBounds(0, 0, 1100, 800);
		myPanel.setOpaque(false);

		// 버튼
		resv.setBounds(960, 430, 95, 50);
		reset.setBounds(960, 490, 95, 50);
		prev.setBounds(960, 550, 95, 50);
		home.setBounds(960, 610, 95, 50);

		resv.addActionListener(this);
		prev.addActionListener(this);
		reset.addActionListener(this);
		home.addActionListener(this);

		resv.setFont(new Font("고딕", Font.BOLD, 15));
		prev.setFont(new Font("고딕", Font.BOLD, 15));
		reset.setFont(new Font("고딕", Font.BOLD, 15));
		home.setFont(new Font("고딕", Font.BOLD, 15));
		
		JLabel logo = new JLabel("좌석선택");
		logo.setBounds(60, 30, 400, 100);
		logo.setFont(new Font("고딕", Font.BOLD, 70));
		layeredPane.add(logo);

		ImageIcon on = new ImageIcon("images/seaton.gif");
		ImageIcon off = new ImageIcon("images/seatoff.gif");

		ImageIcon i1 = new ImageIcon("images/i1.gif");
		ImageIcon i2 = new ImageIcon("images/i2.gif");
		ImageIcon i3 = new ImageIcon("images/i3.gif");

		JLabel s1 = new JLabel();
		s1.setBounds(960, 210, 95, 50);
		s1.setIcon(i1);
		JLabel s2 = new JLabel();
		s2.setBounds(960, 270, 95, 50);
		s2.setIcon(i2);
		JLabel s3 = new JLabel();
		s3.setBounds(960, 330, 95, 50);
		s3.setIcon(i3);

		center.setBounds(178, 200, 762, 500);
		center.setLayout(null);
		center.setOpaque(false);

		cen.setBounds(0, 0, 762, 500);
		cen.setLayout(null);
		cen.setOpaque(false);

		int x = 0, y = 0;
		for (int i = 0; i < str.length; i++) {
			cen = new JLabel(str[i]);
			cen.setOpaque(false);

			if (i % 10 == 0 && i != 0) {
				x = 0;
				y += 50;
			}
			cen.setBounds(x + 12, y, 75, 45);
			x += 77;
			center.add(cen);
		}

		int posXpanSeat = 0, posYpanSeat = 0;
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				seats[i][j] = new JCheckBox(null, on);
				seats[i][j].setOpaque(false);
				seats[i][j].setBounds(posXpanSeat, posYpanSeat, 70, 45);
				posXpanSeat += 77;
				seats[i][j].setSelectedIcon(off);
				// seat[i].setEnabled(false);
				center.add(seats[i][j]);
			}
			posXpanSeat = 0;
			posYpanSeat += 50;

		}

		layeredPane.add(s1);
		layeredPane.add(s2);
		layeredPane.add(s3);

		layeredPane.add(center2);
		layeredPane.add(center);
		layeredPane.add(reset);
		layeredPane.add(resv);
		layeredPane.add(prev);
		layeredPane.add(home);
		layeredPane.add(myPanel);
		add(layeredPane);

		list = mgr.seatload(title, date, time);
		
		for (int t = 0; t < list.size(); t++) {
			int yy=list.get(t).getSeaty();
			int xx=list.get(t).getSeatx();
			seats[yy][xx].setEnabled(false);
			
		}

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == prev) {
			new moivere();// 예약창으로 돌아가게
			dispose();
		}
		if (e.getSource() == home) {
			new mmenu();// 
			dispose();
		}

		if (e.getSource() == reset) {
			for (int i = 1; i <= 10; i++) {
				for (int j = 1; j <= 10; j++) {
					seats[i][j].setSelected(true);
					seats[i][j].setSelected(false);
				}
			}
		}

		if (e.getSource() == resv) {
			/*
			 * 체크여부 확인 필요
			 * boolean checked = false;
			for (int i = 1; i <= 10; i++) {
				for (int j = 1; j <= 10; j++) {
					seats[i][j].setSelected(checked);
				}
			}
			if(checked){
				System.out.println("a");
			} else System.out.println("b");*/
			
			int result = JOptionPane.showConfirmDialog(null, "예약하시겠습니까?", "예약", JOptionPane.YES_NO_OPTION);
			// DB에서 중복검사필요
			if (result == JOptionPane.CLOSED_OPTION) {
			} else if (result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "예약되었습니다.", "예약완료", JOptionPane.INFORMATION_MESSAGE);
				for (int i = 1; i <= 10; i++) {
					for (int j = 1; j <= 10; j++) {
						if (seats[i][j].isSelected()) {
							seats[i][j].setEnabled(false);
							seat = c[i] + Integer.toString(j);
						

							mgr.seatinsert(title, date, time, i, j);
							mgr.inmovie(date, title, time, theater, seat, num, id);
						}

					}
				}
				// 예약이 완료되면 seat[i]를 setEnable(false)처리

			} else if (result == JOptionPane.NO_OPTION) {
				// 만약 다른사람이 예약한경우(동시일경우), 없어도 됨
				JOptionPane.showMessageDialog(null, "예약이 불가능합니다.", "좌석선택안함", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

	class MyPanel extends JPanel {
		Image image;

		public MyPanel() {
			image = Toolkit.getDefaultToolkit().createImage("images/seat.gif");
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(image, 0, 0, this);
		}
	}


}
