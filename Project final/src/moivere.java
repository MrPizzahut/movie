import java.awt.*;
import java.io.*;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class CalendarDataManager extends JFrame { // 6*7배열에 나타낼 달력 값을 구하는 class
	static final int CAL_WIDTH = 7;
	final static int CAL_HEIGHT = 6;
	int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
	int calYear;
	int calMonth;
	int calDayOfMon;
	final int calLastDateOfMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	int calLastDate;
	Calendar today2 = Calendar.getInstance();
	Calendar cal;
	
	public CalendarDataManager() {
		setToday();
	}

	public void setToday() {
		calYear = today2.get(Calendar.YEAR);
		calMonth = today2.get(Calendar.MONTH);
		calDayOfMon = today2.get(Calendar.DAY_OF_MONTH);
		makeCalData(today2);
	}

	private void makeCalData(Calendar cal) {
		// 1일의 위치와 마지막 날짜를 구함
		int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK) + 7 - (cal.get(Calendar.DAY_OF_MONTH)) % 7) % 7;
		if (calMonth == 1)
			calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
		else
			calLastDate = calLastDateOfMonth[calMonth];
		// 달력 배열 초기화
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				calDates[i][j] = 0;
			}
		}
		// 달력 배열에 값 채워넣기
		for (int i = 0, num = 1, k = 0; i < CAL_HEIGHT; i++) {
			if (i == 0)
				k = calStartingPos;
			else
				k = 0;
			for (int j = k; j < CAL_WIDTH; j++) {
				if (num <= calLastDate)
					calDates[i][j] = num++;
			}
		}
	}

	private int leapCheck(int year) { // 윤년인지 확인하는 함수
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return 1;
		else
			return 0;
	}

	public void moveMonth(int mon) { // 현재달로 부터 n달 전후를 받아 달력 배열을 만드는 함수(1년은 +12,
										// -12달로 이동 가능)
		calMonth += mon;
		if (calMonth > 11)
			while (calMonth > 11) {
				calYear++;
				calMonth -= 12;
			}
		else if (calMonth < 0)
			while (calMonth < 0) {
				calYear--;
				calMonth += 12;
			}
		cal = new GregorianCalendar(calYear, calMonth, calDayOfMon);
		makeCalData(cal);
	}
}

public class moivere extends CalendarDataManager implements MouseListener, ActionListener { 			
	
	static 
	JPanel calOpPanel;
	JButton todayBut;
	JLabel todayLab;
	JButton lYearBut;
	JButton lMonBut;
	JLabel curMMYYYYLab;
	JButton nMonBut;
	JButton nYearBut;
	ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();
 
	JPanel calPanel;
	JButton weekDaysName[];
	JButton dateButs[][] = new JButton[6][7];
	listenForDateButs lForDateButs = new listenForDateButs();
 
	// JPanel infoPanel;
	JLabel infoClock;
 
	JPanel memoPanel;
	JLabel selectedDate;
	JTextArea memoArea;
	JScrollPane memoAreaSP;
	JPanel memoSubPanel;
 
	// 상수, 메세지
	final String WEEK_DAY_NAME[] = { "S", "M", "T", "W", "T", "F", "S" };
 
	JFrame j = new JFrame();
	JPanel p = new JPanel();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JLabel j1 = new JLabel();
	JLabel j2 = new JLabel();
	JLabel j3 = new JLabel();
	JLabel j4 = new JLabel();
	JLabel j6 = new JLabel();
 
	JLabel j8 = new JLabel();
	JLabel j9 = new JLabel();
	JLabel j10 = new JLabel();
	JLabel j11 = new JLabel();
	JLabel j12 = new JLabel();
	JLabel j13 = new JLabel();
	JLabel j14 = new JLabel();
	JLabel j15 = new JLabel();
	JLabel j16 = new JLabel();
	JButton bu = new JButton("달력");
	JButton bu1 = new JButton("좌석선택");
	JButton bu2 = new JButton("이전");
	JScrollPane sp1 = new JScrollPane();
	JScrollPane sp2 = new JScrollPane();
	JScrollPane sp3 = new JScrollPane();
	
	JList list1 = new JList();
	JList list2 = new JList();
	
	ImageIcon poster;
	JPanel info = new JPanel();

	moivemgr mgr = new moivemgr();
	DBMgr mgr1=new DBMgr();
	ArrayList<movieDTO> list;
	ArrayList<rebean> list4;
 
	int selected = 0;
	
	public moivere() { 
		
		Container c = getContentPane();
		c.setLayout(null);
 
		this.setSize(1100, 800);
 
		// 화면 중앙 처리
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2), (int) (di.getHeight() / 2 - di1.getHeight() / 2));
 
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
		
		//달력
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			System.out.println("error");
		}
 
		calOpPanel = new JPanel();
		todayBut = new JButton("Today");
		todayBut.setToolTipText("Today");
		todayBut.addActionListener(lForCalOpButtons);
		todayLab = new JLabel("     " + today2.get(Calendar.YEAR) + " / " + (today2.get(Calendar.MONTH) + 1) + " / "
				+ today2.get(Calendar.DAY_OF_MONTH));
		lYearBut = new JButton("<<");
		lYearBut.setToolTipText("Previous Year");
		lYearBut.addActionListener(lForCalOpButtons);
		lMonBut = new JButton("<");
		lMonBut.setToolTipText("Previous Month");
		lMonBut.addActionListener(lForCalOpButtons);
		curMMYYYYLab = new JLabel("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
				+ (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");
		nMonBut = new JButton(">");
		nMonBut.setToolTipText("Next Month");
		nMonBut.addActionListener(lForCalOpButtons);
		nYearBut = new JButton(">>");
		nYearBut.setToolTipText("Next Year");
		nYearBut.addActionListener(lForCalOpButtons);
		calOpPanel.setLayout(new GridBagLayout());
		GridBagConstraints calOpGC = new GridBagConstraints();
		calOpGC.gridx = 1;
		calOpGC.gridy = 1;
		calOpGC.gridwidth = 2;
		calOpGC.gridheight = 1;
		calOpGC.weightx = 1;
		calOpGC.weighty = 1;
		calOpGC.insets = new Insets(5, 5, 0, 0);
		calOpGC.anchor = GridBagConstraints.WEST;
		calOpGC.fill = GridBagConstraints.NONE;
		calOpPanel.add(todayBut, calOpGC);
		calOpGC.gridwidth = 3;
		calOpGC.gridx = 2;
		calOpGC.gridy = 1;
		calOpPanel.add(todayLab, calOpGC);
		calOpGC.anchor = GridBagConstraints.CENTER;
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 1;
		calOpGC.gridy = 2;
		calOpPanel.add(lYearBut, calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 2;
		calOpGC.gridy = 2;
		calOpPanel.add(lMonBut, calOpGC);
		calOpGC.gridwidth = 2;
		calOpGC.gridx = 3;
		calOpGC.gridy = 2;
		calOpPanel.add(curMMYYYYLab, calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 5;
		calOpGC.gridy = 2;
		calOpPanel.add(nMonBut, calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 6;
		calOpGC.gridy = 2;
		calOpPanel.add(nYearBut, calOpGC);
 
		calPanel = new JPanel();
		weekDaysName = new JButton[7];
		for (int i = 0; i < CAL_WIDTH; i++) {
			weekDaysName[i] = new JButton(WEEK_DAY_NAME[i]);
			weekDaysName[i].setFont(new Font("Aharoni", Font.BOLD, 18));
			weekDaysName[i].setBorderPainted(false);
			weekDaysName[i].setContentAreaFilled(false);
			weekDaysName[i].setForeground(Color.WHITE);
			if (i == 0)
				weekDaysName[i].setBackground(new Color(200, 50, 50));
			else if (i == 6)
				weekDaysName[i].setBackground(new Color(50, 100, 200));
			else
				weekDaysName[i].setBackground(new Color(150, 150, 150));
			weekDaysName[i].setOpaque(true);
			weekDaysName[i].setFocusPainted(false);
			calPanel.add(weekDaysName[i]);
		}
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				dateButs[i][j] = new JButton();
				dateButs[i][j].setBorderPainted(false);
				dateButs[i][j].setContentAreaFilled(false);
				dateButs[i][j].setBackground(Color.WHITE);
				dateButs[i][j].setFont(new Font("Aharoni",Font.BOLD,25));
				dateButs[i][j].setOpaque(true);
				dateButs[i][j].addActionListener(lForDateButs);
				calPanel.add(dateButs[i][j]);
			}
		}
		calPanel.setLayout(new GridLayout(0, 7, 2, 2));
		calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		showCal(); // 달력을 표시
 
		selectedDate = new JLabel(today2.get(Calendar.YEAR) + " / " + (today2.get(Calendar.MONTH) + 1) + " / "
				+ today2.get(Calendar.DAY_OF_MONTH));
		//선택날짜
		//selected = today2.get(Calendar.DATE);
		
		selectedDate.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		selectedDate.setFont(new Font("고딕", Font.BOLD, 20));
		JPanel frameSubPanelWest = new JPanel();
		frameSubPanelWest.setBackground(Color.white);
		
		Dimension calOpPanelSize = calOpPanel.getPreferredSize();
		calOpPanelSize.height = 80;
		calOpPanel.setPreferredSize(calOpPanelSize);
		frameSubPanelWest.setOpaque(false);
		calPanel.setBackground(Color.white);
		calOpPanel.setBackground(Color.white);
		frameSubPanelWest.setLayout(new BorderLayout());
		frameSubPanelWest.add(calOpPanel, BorderLayout.NORTH);
		frameSubPanelWest.add(calPanel, BorderLayout.CENTER);
 
		JPanel frameSubPanelEast = new JPanel();
		frameSubPanelEast.setLayout(new BorderLayout());
		frameSubPanelEast.add(selectedDate);
 
		Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
		frameSubPanelWestSize.width = 510;
		frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);
 
		frameSubPanelWest.setBounds(365, 50, 400, 380);
		frameSubPanelEast.setBounds(810, 470, 200, 100);
		frameSubPanelEast.setOpaque(false);
		
		c.add(frameSubPanelWest);
		c.add(frameSubPanelEast);
 
		
		setTitle("영화예매");
		
 
		ImageIcon bg = new ImageIcon("images/bg.gif");
		JLabel bgs = new JLabel();
		bgs.setBounds(0, 0, 1100, 800);
		bgs.setIcon(bg);
 
		j1.setSize(300, 50);
		j1.setLocation(40, 40);
		j1.setFont(new Font("돋움", Font.BOLD, 27));
		j1.setText("예매하기 :TICKETING");
 
		c.add(j1);
 
		// 첫번째 레이아웃
 
		p.setSize(300, 635);
		p.setLocation(40, 100);
		p.setLayout(null);
		p.setOpaque(false);
 
		// 리스트 db부분
		movieDTO bean;
		rebean bean1;
		list = mgr.movie();
		String col[] = { list.get(0).getName(), list.get(4).getName(), list.get(8).getName(), list.get(12).getName() };
		list1 = new JList<String>(col);
		list1.setFont(new Font("돋움", Font.BOLD, 20));
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list1.addMouseListener(this);
 
		j2.setFont(new Font("굴림", Font.BOLD, 20));
		j2.setText("Select Movie ||");
		j2.setBackground(Color.white);
		j2.setSize(320, 28);
		j2.setLocation(10, 0);
				
		JPanel wb = new JPanel();
		wb.setBounds(40, 95, 300,40);
		wb.setBackground(Color.WHITE);
				
		JScrollPane sp = new JScrollPane(list1);
		sp.setBounds(0, 40, 300, 595);
		sp.setOpaque(false);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		p.add(sp);
		p.add(j2);
		
		c.add(p);
		c.add(wb);
		// 두번쨰 레이아웃(360, 50, 400, 380) 
		sp1.setSize(400, 295);
		sp1.setLocation(365, 440);
		sp1.setOpaque(false);
		sp1.setLayout(null);
 
		p2.setSize(380, 50);
		p2.setLocation(410, 100);
		p2.setOpaque(false);
		p2.setLayout(new BorderLayout());
 
 
		sp1.add(sp3);
		c.add(j3);
		c.add(sp1);
		c.add(p2);
	
 
		// 세번째 레이아웃
		sp2.setSize(270, 384);
		sp2.setLocation(790, 100);
		//sp2.setOpaque(false);
		sp2.setBackground(Color.white);
		sp2.setLayout(null);
		
		j4.setFont(new Font("돋움", Font.BOLD, 27));
		j4.setText("선택 정보 ||");
		j4.setSize(320, 40);
		j4.setLocation(790, 50);
 
		info.setLayout(null);
		info.setBounds(790, 490, 270, 170);
		//info.setOpaque(false);
		info.setBackground(Color.WHITE);
		
		JPanel wb2 = new JPanel();
		wb2.setBounds(790, 490, 270,170);
		wb2.setBackground(Color.WHITE);
		info.add(wb2);
 
		j13.setFont(new Font("고딕", Font.BOLD, 20));//영화이름
		j13.setForeground(Color.black);
		j13.setSize(170, 30);
		j13.setLocation(813, 600);

		j15.setFont(new Font("고딕", Font.BOLD, 20));//영화관
		j15.setSize(150, 30);
		j15.setLocation(815, 570);
 
		j16.setFont(new Font("고딕", Font.BOLD, 20));//영화시간
		j16.setSize(300, 30);
		j16.setLocation(815, 540);
 
		bu2.setBounds(790, 670, 130, 65);
		bu1.setBounds(930, 670, 130, 65);
		bu1.setFont(new Font("고딕", Font.BOLD, 15));
		bu2.setFont(new Font("고딕", Font.BOLD, 15));
 
		bu1.addActionListener(this);
		bu2.addActionListener(this);
 
		c.add(j13);
		c.add(j14);
		c.add(j15);
		c.add(j16);
		c.add(j4);
		c.add(sp2);
		c.add(bu1);
		c.add(bu2);
		c.add(info);
		c.add(bgs);
 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 800);
		setVisible(true);
		setResizable(false);
 
		ImageIcon poster = new ImageIcon("./images/lady.gif");
		j12.setIcon(poster);
 
		focusToday(); // 현재 날짜에 focus를 줌 (mainFrame.setVisible(true) 이후에 배치해야함)
 
	}
 
	private void focusToday() {
		if (today2.get(Calendar.DAY_OF_WEEK) == 1)
			dateButs[today2.get(Calendar.WEEK_OF_MONTH)][today2.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
		else
			dateButs[today2.get(Calendar.WEEK_OF_MONTH) - 1][today2.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JList list3 = (JList) e.getSource();
		JList list4 = (JList) e.getSource();
		if (e.getClickCount() == 1) {
			int index = list3.locationToIndex(e.getPoint());
			int index1 = list4.locationToIndex(e.getPoint());
 
			if (index1 >= 0 && list4.equals(list2)) {
			
				Object o = list3.getModel().getElementAt(index);
				j16.setText((String)o);
				
			} else if (index >= 0 && list3.equals(list1)) {
				Object o = list3.getModel().getElementAt(index);
				j13.setText((String) o);
 
				if (index == 0) {
					sp2.removeAll();
					sp1.removeAll();
					j16.setText("");
					j14.setText("");
					ImageIcon poster = new ImageIcon("./images/lady.gif");
					JLabel j12 = new JLabel(poster);
					j12.setSize(270, 384);
					j12.setLocation(0, 0);
					sp2.add(j12);
					String time[] = { list.get(0).getTime(), list.get(1).getTime(), list.get(2).getTime(),
							list.get(3).getTime() };
					list2 = new JList<String>(time);
					list2.setFont(new Font("돋움", Font.BOLD, 20));
					list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp3 = new JScrollPane(list2);
					sp3.setSize(400, 295);
					sp3.setLocation(0, 0);
					sp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					sp1.add(sp3);
					list2.addMouseListener(this);
					String movienum = list.get(0).getNum();
					j15.setText(movienum);
 
					repaint();
				} else if (index == 1) {
 
					sp2.removeAll();
					sp1.removeAll();
					j16.setText(" ");
					j14.setText("");
					poster = new ImageIcon("./images/le.gif");
					JLabel j12 = new JLabel(poster);
					j12.setSize(270, 384);
					j12.setLocation(0, 0);
					sp2.add(j12);
					String time[] = { list.get(4).getTime(), list.get(5).getTime(), list.get(6).getTime(),
							list.get(7).getTime() };
					list2 = new JList<String>(time);
					list2.setFont(new Font("돋움", Font.BOLD, 20));
					list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp3 = new JScrollPane(list2);
					sp3.setSize(400, 295);
					sp3.setLocation(0, 0);
					sp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					sp1.add(sp3);
					list2.addMouseListener(this);
					String movienum = list.get(4).getNum();
					j15.setText(movienum);
					repaint();
				} else if (index == 2) {
					sp2.removeAll();
					sp1.removeAll();
					j16.setText(" ");
					j14.setText("");
					poster = new ImageIcon("./images/dory.gif");
					JLabel j12 = new JLabel(poster);
					j12.setSize(270, 384);
					j12.setLocation(0, 0);
					sp2.add(j12);
					String time[] = { list.get(8).getTime(), list.get(9).getTime(), list.get(10).getTime(),
							list.get(11).getTime() };
					list2 = new JList<String>(time);
					list2.setFont(new Font("돋움", Font.BOLD, 20));
					list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp3 = new JScrollPane(list2);
					sp3.setSize(400, 295);
					sp3.setLocation(0, 0);
					sp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					sp1.add(sp3);
					list2.addMouseListener(this);
					String movienum = list.get(8).getNum();
					System.out.println(movienum);
					j15.setText(movienum);
					repaint();
				} else if (index == 3) {
					sp2.removeAll();
					sp1.removeAll();
					j16.setText(" ");
					j14.setText("");
					poster = new ImageIcon("./images/day.gif");
					JLabel j12 = new JLabel(poster);
					j12.setSize(270, 384);
					j12.setLocation(0, 0);
					sp2.add(j12);
					String time[] = { list.get(12).getTime(), list.get(13).getTime(), list.get(14).getTime(),
							list.get(15).getTime() };
					list2 = new JList<String>(time);
					list2.setFont(new Font("돋움", Font.BOLD, 20));
					list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp3 = new JScrollPane(list2);
					sp3.setSize(400, 295);
					sp3.setLocation(0, 0);
					sp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					sp1.add(sp3);
					list2.addMouseListener(this);
					String movienum = list.get(12).getNum();
					j15.setText(movienum);
					repaint();
				}
			}
		}
	};
	//달력
	private void showCal() {
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				String fontColor = "black";
				if (j == 0)
					fontColor = "red";
				else if (j == 6)
					fontColor = "blue";
 
				File f = new File("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
						+ (calDates[i][j] < 10 ? "0" : "") + calDates[i][j] + ".txt");
				if (f.exists()) {
					dateButs[i][j]
							.setText("<html><b><font color=" + fontColor + ">" + calDates[i][j] + "</font></b></html>");
				} else
					dateButs[i][j].setText("<html><font color=" + fontColor + ">" + calDates[i][j] + "</font></html>");
 
				JLabel todayMark = new JLabel("<html><font color=green>*</html>");
				dateButs[i][j].removeAll();
				if (calMonth == today2.get(Calendar.MONTH) && calYear == today2.get(Calendar.YEAR)
						&& calDates[i][j] == today2.get(Calendar.DAY_OF_MONTH)) {
					//dateButs[i][j].add(todayMark);
					
					dateButs[i][j].setToolTipText("Today");
				}
 
				if (calDates[i][j] == 0)
					dateButs[i][j].setVisible(false);
				else
					dateButs[i][j].setVisible(true);
			}
		}
	}
 
	private class ListenForCalOpButtons implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == todayBut) {
				setToday();
				lForDateButs.actionPerformed(e);
				focusToday();
			} else if (e.getSource() == lYearBut)
				moveMonth(-12);
			else if (e.getSource() == lMonBut)
				moveMonth(-1);
			else if (e.getSource() == nMonBut)
				moveMonth(1);
			else if (e.getSource() == nYearBut)
				moveMonth(12);
 
			curMMYYYYLab.setText("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
					+ (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");
			showCal();
		}
	}
 
	private class listenForDateButs implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int k = 0, l = 0;
			for (int i = 0; i < CAL_HEIGHT; i++) {
				for (int j = 0; j < CAL_WIDTH; j++) {
					if (e.getSource() == dateButs[i][j]) {
						k = i;
						l = j;
					}
				}
			}
 
			if (!(k == 0 && l == 0))
				calDayOfMon = calDates[k][l]; 
			// today버튼을 눌렀을때도 이 actionPerformed함수가 실행되기 때문에 넣은 부분
 
			cal = new GregorianCalendar(calYear, calMonth, calDayOfMon);
 
			String dDayString = new String();
			int dDay = ((int) ((cal.getTimeInMillis() - today2.getTimeInMillis()) / 1000 / 60 / 60 / 24));
 
			selectedDate.setText(calYear + " / " + (calMonth + 1) + " / " + calDayOfMon+ dDayString);
			
		}
	}
 
	public void mouseEntered(MouseEvent e) {
	}
 
	public void mouseExited(MouseEvent e) {
	}
 
	public void mousePressed(MouseEvent e) {
	}
 
	public void mouseReleased(MouseEvent e) {
	}
 
	
	
	public static void main(String[] args) {
		new moivere();
	
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//int today2 = Calendar.DATE;
		
		
		if (e.getSource() == bu1) {
			String DATE = selectedDate.getText();
			String title = j13.getText();
			String TIME = j16.getText();
			String theater = j15.getText();
			
			if(e.getActionCommand().equals("좌석선택")){
				if(DATE== " " || title == " " || TIME == " " || theater==" " ||
						DATE== "" || title == "" || TIME == "" || theater=="" ){
					JOptionPane.showMessageDialog(null, "예매정보가 올바르지 않습니다.", "예매오류", JOptionPane.ERROR_MESSAGE);
					return;
				} else{
				DATE = selectedDate.getText();
				title = j13.getText();
				TIME = j16.getText();
				theater = j15.getText();
				}
			//	mgr1.inmovie(DATE, title, TIME, theater, seat, num, id);
				int result = JOptionPane.showConfirmDialog(null, "선택한 정보가 맞습니까?","확인",JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.YES_OPTION){
				new Seats(DATE, title, TIME, theater);
				dispose();}
			}
		} 
		else if (e.getSource() == bu2) {
			new mmenu();
			dispose();
		}
 
	
	}
 
}