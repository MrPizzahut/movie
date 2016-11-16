import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

class idfind extends JDialog {
	// HP로 아이디 확인
	JLabel hp = new JLabel("HP : ");
	JTextField hpt = new JTextField(30);
	JButton ok = new JButton("확인");
	JButton exb = new JButton("닫기");
	JLabel idl = new JLabel("ID는 root입니다.");

	DBMgr mgr = new DBMgr(); // DAO
	ArrayList<MbBean> list;
	MbBean bean; // DTO

	public idfind() {
		list = mgr.login();
		setLayout(null);
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));

		setTitle("등록하신 HP를 입력하세요");
		setSize(300, 100);
		hp.setBounds(30, 20, 30, 30);
		hpt.setBounds(60, 20, 120, 30);
		ok.setBounds(200, 20, 70, 30);

		add(hp);
		add(hpt);
		add(ok);
		/*
		 * idl.setFont(new Font("Berlin Sans FB",Font.PLAIN,15));
		 * idl.setBounds(30, 15, 220,70); exb.setBounds(100, 85, 80, 40);
		 * 
		 * add(idl);
		 */
		// add(exb);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hpo = hpt.getText();

				for (int i = 0; i < list.size(); i++) {
					if (hpo.equals(list.get(i).getMb_phone())) {
						String id = list.get(i).getMb_id();
						JOptionPane.showMessageDialog(null, "등록하신 ID는 : " + id + " 입니다.", "ID",
								JOptionPane.PLAIN_MESSAGE);

						return;
					}
				}
				if (hpo.equals("")) {
					System.out.println("null");
					JOptionPane.showMessageDialog(null, "등록하신 핸드폰번호를 입력해주세요.", "공백", JOptionPane.WARNING_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(null, "미가입회원입니다.", "NO ID", JOptionPane.OK_OPTION);
				}

			}
		});
	}
}

/*
 * class resok extends JDialog{ JLabel ok = new JLabel("비밀번호가 초기화되었습니다.");
 * JButton okb = new JButton("닫기");
 * 
 * public resok() { setLayout(null);
 * 
 * Dimension di = Toolkit.getDefaultToolkit().getScreenSize(); Dimension di1 =
 * this.getSize(); this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() /
 * 2), (int) (di.getHeight() / 2 - di1.getHeight() / 2));
 * 
 * JOptionPane.showMessageDialog(null, "비밀번호가 초기화되었습니다."
 * ,"초기화완료",JOptionPane.CLOSED_OPTION);
 * 
 * setTitle("초기화완료"); setSize(280, 140); ok.setFont(new
 * Font("고딕",Font.BOLD,15)); ok.setBounds(30, 0, 220,50); okb.setBounds(90, 55,
 * 80, 30);
 * 
 * add(ok); add(okb);
 * 
 * okb.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent e) { setVisible(false); } }); } }
 */

public class Find extends JFrame implements ActionListener {
	// resok dialog;
	idfind idlog;

	JLabel title = new JLabel("ID/PW 초기화");
	JLabel id = new JLabel("ID : ");
	JLabel pw = new JLabel("PW : ");

	JTextField idt = new JTextField();
	JTextField pwt = new JTextField("비밀번호가 1234로 초기화됩니다.");

	JButton chk = new JButton("ID 찾기");
	JButton cen = new JButton("취소");
	JButton pwr = new JButton("PW 초기화");
	JButton reset = new JButton("리셋");

	DBMgr mgr = new DBMgr(); // DAO
	ArrayList<MbBean> list;
//	ArrayList<MbBean> list2;
	MbBean bean; // DTO

	public Find() {
		list = mgr.login();
		// dialog = new resok();
		idlog = new idfind();
		this.setTitle("ID/PW 찾기");
		this.setResizable(false);
		this.setLocation(600, 400);
		this.setSize(500, 250);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(null);

		title.setBounds(160, 10, 300, 50);
		title.setFont(new Font("고딕", Font.BOLD, 40));

		id.setBounds(70, 80, 70, 30);
		pw.setBounds(70, 130, 70, 30);

		idt.setBounds(120, 80, 220, 30);
		pwt.setBounds(120, 130, 220, 30);
		pwt.setEnabled(false);

		chk.setBounds(360, 80, 100, 30); // 아이디 찾기
		reset.setBounds(360, 130, 100, 30); // 리셋
		cen.setBounds(150, 180, 70, 30); // 취소
		pwr.setBounds(240, 180, 100, 30); // 비밀번호 초기화

		chk.addActionListener(this);
		reset.addActionListener(this);
		cen.addActionListener(this);
		pwr.addActionListener(this);

		c.add(title);
		c.add(id);
		c.add(pw);

		c.add(idt);
		c.add(pwt);

		c.add(chk);
		c.add(reset);
		c.add(cen);
		c.add(pwr);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == chk) {
			idlog.setVisible(true);

		}
		if (e.getSource() == cen)
			dispose();
		if (e.getSource() == pwr) {

			String id = idt.getText();
			for (int i = 0; i < list.size(); i++) {
				if (id.equals(list.get(i).getMb_id())) {
					String pass = "1234";
					mgr.updatepw(pass, id);
					JOptionPane.showMessageDialog(null, "          " + "비밀번호가 초기화되었습니다.", "초기화완료",
							JOptionPane.CLOSED_OPTION);
					list = mgr.login();
					dispose();
					return;
				}
			}
			if (id.equals("")) {
				System.out.println("null");
				JOptionPane.showMessageDialog(null, "등록하신 아이디를 입력해주세요.", "공백", JOptionPane.WARNING_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null, "미가입회원입니다.", "NO ID", JOptionPane.OK_OPTION);
			}

		}
		if (e.getSource() == reset) {
			idt.setText("");

		}
	}

}
