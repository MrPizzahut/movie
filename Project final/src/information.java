import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class information extends MFrame implements ActionListener {
	
	JPanel bg = new JPanel();
	
	JPanel post1 = new JPanel();
	JPanel post2 = new JPanel();
	JPanel post3 = new JPanel();
	JPanel post4 = new JPanel();
	JTextArea t1 = new JTextArea();
	JTextArea t2 = new JTextArea();
	JTextArea t3 = new JTextArea();
	JTextArea t4 = new JTextArea();
	
	JLabel label = new JLabel("SCREENING");
	JButton bt = new JButton("prev");
	
	public information() {
		setTitle("Information");
		Container c = getContentPane();
		
		ImageIcon icon = new ImageIcon("images/bg.gif");
		JLabel logo = new JLabel();
		logo.setBounds(0, 0, 1100, 800);
		logo.setIcon(icon);
		
		bg.setLayout(null);
		bg.add(logo);
		bg.setOpaque(false);

		post1.setLayout(new BorderLayout());
		post2.setLayout(new BorderLayout());
		post3.setLayout(new BorderLayout());
		post4.setLayout(new BorderLayout());
		
				
		JPanel mv1=new JPanel();
		JLabel label1 = new JLabel();
		ImageIcon ic1 = new ImageIcon("images/1.jpg");
		Image img1 = ic1.getImage();  //ImageIcon을 Image로 변환.
		Image ming1 = img1.getScaledInstance(210, 300, java.awt.Image.SCALE_SMOOTH);
		ImageIcon mic1 = new ImageIcon(ming1); //Image로 ImageIcon 생성
		label1.setIcon(mic1);
		
		JPanel mv2=new JPanel();
		JLabel label2 = new JLabel();
		ImageIcon ic2 = new ImageIcon("images/2.jpg");
		Image img2 = ic2.getImage();  //ImageIcon을 Image로 변환.
		Image ming2 = img2.getScaledInstance(210, 300, java.awt.Image.SCALE_SMOOTH);
		ImageIcon mic2 = new ImageIcon(ming2); //Image로 ImageIcon 생성
		label2.setIcon(mic2);
		
		JPanel mv3=new JPanel();
		JLabel label3 = new JLabel();
		ImageIcon ic3 = new ImageIcon("images/3.jpg");
		Image img3 = ic3.getImage();  //ImageIcon을 Image로 변환.
		Image ming3 = img3.getScaledInstance(210, 300, java.awt.Image.SCALE_SMOOTH);
		ImageIcon mic3 = new ImageIcon(ming3); //Image로 ImageIcon 생성
		label3.setIcon(mic3);
		
		JPanel mv4=new JPanel();
		JLabel label4 = new JLabel();
		ImageIcon ic4 = new ImageIcon("images/4.jpg");
		Image img4 = ic4.getImage();  //ImageIcon을 Image로 변환.
		Image ming4 = img4.getScaledInstance(210, 300, java.awt.Image.SCALE_SMOOTH);
		ImageIcon mic4 = new ImageIcon(ming4); //Image로 ImageIcon 생성
		label4.setIcon(mic4);

		post1.setBounds(60, 85, 480, 300);
		post2.setBounds(550, 85, 480, 300);
		post3.setBounds(60, 390, 480, 300);
		post4.setBounds(550, 390, 480, 300);
		
		post1.add(label1, BorderLayout.WEST);
		post2.add(label2, BorderLayout.WEST);
		post3.add(label3, BorderLayout.WEST);
		post4.add(label4, BorderLayout.WEST);
		
	
		t1.setEditable(false);
		t1.setFont(new Font("Mincho",Font.PLAIN,12));
		t1.setText("\n  ● 영화 제목 : 도리를 찾아서\n\n"
				+ "  ● 개봉일 :  2016.07.06\n\n"
				+ "  ● 주요정보 : “내가 누구라고? 도리? 도리!”\n\n"
				+ "     무엇을 상상하든 그 이상을 까먹는 ‘도리’의\n "
				+ "     어드벤쳐가 시작된다! 니모를 함께 찾으면서\n "
				+ "     베스트 프렌드가 된 도리와 말린은 우여곡절\n "
				+ "     끝에 다시 고향으로 돌아가 평화로운 일상을\n"
				+ "     보내고 있다. 모태 건망증 도리가 ‘기억’이라는\n"
				+ "     것을 하기 전까지! 도리는 깊은 기억 속에 숨어\n "
				+ "     있던 가족의 존재를 떠올리고 니모와 말린과 \n"
				+ "     함께 가족을 찾아 대책 없는 어드벤쳐를 떠나게 \n"
				+ "     되는데… \n"
				+ "     깊은 바다도 막을 수 없는 스펙터클한 \n"
				+ "     어드벤쳐가 펼쳐진다!");
		post1.add(t1, BorderLayout.CENTER);
		
		t2.setEditable(false);
		t2.setText("\n  ●영화 제목 : 레전드 오브 타잔 \n\n"
				+ "  ●개봉일 : 2016.06.29.\n\n"
				+ "  ●주요정보 : 밀림의 전설, 타잔이 돌아왔다! \n\n"
				+ "      8년 전, 아프리카 밀림을 떠나 이제는 런던 \n"
				+ "      도심에서 사랑하는 제인과 함께 문명 사회에 \n"
				+ "      완벽하게 적응한 타잔. 하지만 탐욕에 휩싸인 \n"
				+ "      인간들은 그를 다시 밀림으로 불러들이는 데… \n"
				+ "      사랑하는 아내 제인과 밀림을 지키기 위해\n"
				+ "      타잔, 그가 이제 인간에게 맞선다!");
		t2.setFont(new Font("Mincho",Font.PLAIN,12));
		post2.add(t2, BorderLayout.CENTER);
		
		t3.setEditable(false);
		t3.setText("\n  ● 영화 제목 : 아가씨\n\n"
				+ "  ● 개봉일 :  2016.06.01\n\n"
				+ "  ● 주요정보 : 어릴 적 부모를 잃고 후견인 \n"
				+ "  이모부의 엄격한 보호 아래 살아가는 귀족 아가씨. \n\n"
				+ "  매일 이모부의 서재에서 책을 읽는 것이 일상의 \n"
				+ "  전부인 외로운 아가씨는 순박해 보이는 하녀에게 \n"
				+ "  조금씩 의지하기 시작한다. 하지만 하녀의 정체는\n"
				+ "  유명한 여도둑의 딸로, 장물아비 손에서 자란 \n"
				+ "  소매치기 고아 소녀 숙희. 막대한 재산을 상속받게 \n"
				+ "  될 아가씨를 유혹하여 돈을 가로채겠다는 \n"
				+ "  사기꾼 백작의 제안을 받고 아가씨가 \n"
				+ "  백작을 사랑하게 만들기 위해 하녀가 된 것. \n"
				+ "  돈과 마음을 뺏기 위해 서로 속고 속이는\n"
				+ "  매혹적인 그들의 이야기가 시작된다!");
		t3.setFont(new Font("Mincho",Font.PLAIN,12));
		post3.add(t3, BorderLayout.CENTER);
		
		t4.setEditable(false);
		t4.setFont(new Font("Mincho",Font.PLAIN,12));
		t4.setText("\n  ● 영화 제목 : 인디펜던스 데이 리씨전스\n\n"
				+ "  ● 개봉일 : 2016.06.22.\n\n"
				+ "  ● 주요정보 :다시, 그들이 온다!\n\n"
				+ "     20년 전 최악의 우주 전쟁을 치른 지구.\n"
				+ "     재건을 위해 힘쓴 전세계는 다시 한번 있을 \n"
				+ "     외계의 침공에 대비한다. 반드시 살아남아야 \n"
				+ "     한다.\n"
				+ "     마침내 돌아온 그날!  상상을 초월하는 그들의\n"
				+ "     공격에 앞에 인류 최후의 전쟁이 시작된다.");
	
		post4.add(t4, BorderLayout.CENTER);
		
		bt.setBounds(900, 700, 130, 55);
		bt.setFont(new Font("Berlin Sans FB",Font.PLAIN,20));
		bt.addActionListener(this);
		label.setBounds(60, 30, 500, 50);
		label.setFont(new Font("Berlin Sans FB",Font.PLAIN,50));
		label.setForeground(Color.WHITE);
		
		c.add(label);
		c.add(post1);
		c.add(post2);
		c.add(post3);
		c.add(post4);
		c.add(bt);
		c.add(bg);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bt){
			new mmenu();
			dispose();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new information();

	}

}
