import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

class fixok extends JDialog {
	JLabel ok = new JLabel("수정되었습니다.");
	JButton okb = new JButton("닫기");

	public fixok() {

		setLayout(null);
		setLocation(700, 600);
		setTitle("수정완료");
		setSize(200, 140);
		ok.setFont(new Font("고딕", Font.BOLD, 15));
		ok.setBounds(40, 15, 140, 30);
		okb.setBounds(70, 55, 60, 30);

		add(ok);
		add(okb);

		okb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
	}
}

public class Fix extends MFrame implements ActionListener { // j프레임을 상속받는 클래스 생성
	fixok dialog;

	DBMgr mgr = new DBMgr(); // DAO
	ArrayList<MbBean> list;
	MbBean bean; // DTO

	Container cp;
	String[] str = { "ID", "PW", "NEW PW", "HP" };
	JLabel jl = new JLabel();
	JLabel jl1 = new JLabel();

	JTextField jt1 = new JTextField();
	JTextField jt2 = new JTextField();
	JTextField pt1;
	JTextField pt2;

	JButton jb = new JButton("prev");
	JButton jb1 = new JButton("modify");// 계산 버튼 생성

	Fix() {
		list = mgr.login();
		dialog = new fixok(); // 팝업

		ImageIcon bg = new ImageIcon("images/bg.gif");
		JLabel bgs = new JLabel();
		bgs.setBounds(0, 0, 1100, 800);
		bgs.setIcon(bg);

		setTitle("Modify"); // 프레임 생성
		cp = getContentPane();
		cp.setLayout(null);

		jl1 = new JLabel("Modify"); // 회원정보 수정 라벨
		jl1.setSize(500, 200);
		jl1.setLocation(300, 90);
		jl1.setHorizontalAlignment(JLabel.CENTER);
		jl1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 100));
		jl1.setForeground(Color.WHITE);
		cp.add(jl1);

		jl = new JLabel(str[0]);
		jl.setSize(380, 20); // 입력 설명
		jl.setLocation(345, 290);
		jl.setFont(new Font("Berlin Sans FB", Font.PLAIN, 17));
		jl.setForeground(Color.BLACK);
		cp.add(jl);

		jt1 = new JTextField(30);
		jt1.setSize(300, 40); // 입력칸
		jt1.setLocation(460, 280);
		jt1.setText(Login.staticid);
		jt1.setEnabled(false);
		cp.add(jt1);

		jt2 = new JTextField(30);
		jt2.setSize(300, 40); // 입력칸
		jt2.setLocation(460, 460);
		jt2.setText(Login.staticphone);
		cp.add(jt2);

		pt1 = new JPasswordField(30);
		pt1.setSize(300, 40); // 입력칸
		pt1.setLocation(460, 340);
		// jt1.setText();
		cp.add(pt1);

		pt2 = new JPasswordField(30);
		pt2.setSize(300, 40); // 입력칸
		pt2.setLocation(460, 400);
		// jt1.setText();
		cp.add(pt2);

		jb.setSize(120, 60); // 버튼 설정
		jb.setLocation(410, 550);
		jb.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		cp.add(jb);
		jb.addActionListener(this);

		jb1.setSize(120, 60); // 버튼 설정
		jb1.setLocation(570, 550);
		jb1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		cp.add(jb1);
		jb1.addActionListener(this);

		for (int i = 1; i < str.length; i++) { // 라벨이 들어갈 공간을 for문과 배열을 이용하여
												// 위치지정
			jl = new JLabel(str[i]);
			jl.setSize(380, 20);

			jl.setLocation(345, 290 + (i * 60));
			jl.setForeground(Color.BLACK);
			jl.setFont(new Font("Berlin Sans FB", Font.PLAIN, 17));

			cp.add(jl);
		}

		cp.add(bgs);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == jb) { // 이전
			mmenu mb = new mmenu();
			dispose();
		}
		if (e.getSource() == jb1) { // 수정

			String pw1 = pt1.getText();
			String pw2 = pt2.getText();
			if (pw1.equals("") || pw2.equals("") || jt2.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "공백은 허용되지 않습니다.", "공백", JOptionPane.WARNING_MESSAGE);
			} else {

				for (int i = 0; i < list.size(); i++) {
					if (Login.staticid.equals(list.get(i).getMb_id()) && Login.staticpass.equals(pw1)) {
						new mmenu();

						String phone = list.get(i).getMb_phone();
						mgr.modify(pw2, phone);
						dialog.setVisible(true);// 팝업창
						list = mgr.login();
						dispose();
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "암호가 올바르지 않습니다.", "오류", JOptionPane.WARNING_MESSAGE);
			}

		}

	}

}