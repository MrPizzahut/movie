import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.*;
import javax.swing.*;


public class mmenu extends JFrame implements ActionListener {

	JButton resv = new JButton("Rervation");
	JButton now = new JButton("Information");
	JButton resm = new JButton("Administration");
	JButton fixb = new JButton("Modify");
	JButton logout = new JButton("Logout");
	JButton exit = new JButton("Exit");

	public mmenu() {

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1100,800);
	   
	      // 화면 중앙 처리
	      Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
	      Dimension di1 = this.getSize();
	      setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
	            (int) (di.getHeight() / 2 - di1.getHeight() / 2));
	      

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1100, 800);
		layeredPane.setLayout(null);
		setTitle("MAIN MENU");
		
		JPanel myPanel = new MyPanel();
		myPanel.setLayout(null);
		myPanel.setBounds(0, 0, 1100, 800);
		myPanel.setOpaque(false);

		Imagechange imgpanel = new Imagechange();
		imgpanel.setLayout(null);
		imgpanel.setBounds(580, 50, 480, 683);
		imgpanel.setOpaque(false);
		new Thread(imgpanel).start();
		
		Time ap = new Time();
		ap.setBounds(1020,750, 100, 100);
		ap.setOpaque(false);
        new Thread(ap).start();

		
		resv.setBounds(120, 395, 160, 80);
		resv.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		resv.addActionListener(this);
		// 관리
		resm.setBounds(310, 395, 160, 80);
		resm.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		resm.addActionListener(this);
		// 상영정보
		now.setBounds(120, 495, 160, 80);
		now.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		now.addActionListener(this);
		// 회원정보 수정
		fixb.setBounds(310, 495, 160, 80);
		fixb.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		fixb.addActionListener(this);

		logout.setBounds(120, 595, 160, 80);
		logout.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		logout.addActionListener(this);

		exit.setBounds(310, 595, 160, 80);
		exit.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		exit.addActionListener(this);
		
		layeredPane.add(resv);
		layeredPane.add(resm);
		layeredPane.add(now);
		layeredPane.add(fixb);
		layeredPane.add(logout);
		layeredPane.add(exit);
		
		layeredPane.add(ap);
		layeredPane.add(imgpanel);
		layeredPane.add(myPanel);
		
		add(layeredPane);
		
		repaint();

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == resv) {// 예매
			new moivere();
			dispose();
		}

		if (e.getSource() == now) {// 상영정보
			new information();
			dispose();
		}

		if (e.getSource() == resm) { // 예매관리
			new ReservationFrame();
			dispose();
		}

		if (e.getSource() == fixb) {
			new Fix();
			dispose();
		}
		if (e.getSource() == logout) {
			new Login();
			dispose();
		}
		if (e.getSource() == exit) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}

	}
	
	
	public static void main(String[] args) {
		new mmenu();
	}

	
	
	class MyPanel extends JPanel {
		Image image;

		public MyPanel() {
			image = Toolkit.getDefaultToolkit().createImage("images/main.gif");
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(image, 0, 0, this);
		}
	}

	class Imagechange extends JPanel implements Runnable {
		Image[] movie = new Image[5];
		int i = 2;
		
		public Imagechange() {
			this.setLayout(null);
			movie[1] = Toolkit.getDefaultToolkit().createImage("images/2.jpg");
			movie[2] = Toolkit.getDefaultToolkit().createImage("images/3.jpg");
			movie[3] = Toolkit.getDefaultToolkit().createImage("images/4.jpg");
			movie[4] = Toolkit.getDefaultToolkit().createImage("images/1.jpg");
			movie[0] = movie[1];
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(movie[0],0, 0,this);
		}

		public void run() {
			while (true) {
				try {
					Thread.sleep(2000);
					switch (i) {
					case 1:
						movie[0] = movie[i];
						i++;
						repaint();						
						break;
					case 2:
						movie[0] = movie[i];
						i++;
						repaint();
						break;
					case 3:
						movie[0] = movie[i];
						i++;
						repaint();
						break;
					case 4:
						movie[0] = movie[i];
						i=1;
						repaint();
						break;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class Time extends JPanel implements Runnable{
		int i = Calendar.getInstance().get(Calendar.AM_PM);
		String[] ampm = {"AM", "PM"};
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String time = sdf.format(new Date());
		JLabel timeLabel, ampmLabel;
		
		public Time() {
			this.setLayout(null);
			 
            timeLabel = new JLabel(time);
            timeLabel.setBounds(30, 0, 100, 30);
            timeLabel.setForeground(new Color(255,255,255));
            timeLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
 
            ampmLabel = new JLabel(ampm[i]);
            ampmLabel.setBounds(0, 0, 100, 30);
            ampmLabel.setForeground(new Color(255, 255, 255));
            ampmLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
 
            add(ampmLabel);
            add(timeLabel);
		}
		
		public void run() {
			  do {
	                try {
	                    Thread.sleep(1000);
	 
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                timeLabel.setText(sdf.format(new Date()));
	            } while (true);
		}
		
	}
	
}