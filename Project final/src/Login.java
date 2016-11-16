import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Login extends MFrame implements ActionListener {

	JTextField idt = new JTextField();
	JTextField pwt = new JPasswordField();
	JLabel idl = new JLabel("ID : ");
	JLabel pwl = new JLabel("PW : ");
	JButton logb = new JButton("Login");
	JButton resb = new JButton("Reset");
	JButton exit = new JButton("Exit");
	JButton reg = new JButton("Register");
	JButton find = new JButton("Find");
	JPanel login = new JPanel();
	static String staticid;
	static String staticphone;
	static String staticpass;

	DBMgr mgr = new DBMgr(); // DAO
	ArrayList<MbBean> list;
	MbBean bean; // DTO

	public Login() {
		list = mgr.login();

		setTitle("Login");
		// JWindow 사용하면 테두리없어짐

		ImageIcon icon = new ImageIcon("images/login.gif");
		JLabel logo = new JLabel();
		logo.setBounds(0, 0, 1100, 800);
		logo.setIcon(icon);

		login.setLayout(null);

		idl.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		idl.setForeground(Color.BLACK);
		pwl.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		pwl.setForeground(Color.BLACK);

		idl.setBounds(350, 575, 40, 40);
		pwl.setBounds(350, 630, 40, 40);
		idt.setBounds(400, 575, 220, 40);
		pwt.setBounds(400, 630, 220, 40);

		logb.setBounds(650, 575, 100, 40);
		logb.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		resb.setBounds(650, 630, 100, 40);
		resb.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));

		reg.setBounds(950, 580, 100, 40);
		reg.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		find.setBounds(950, 630, 100, 40);
		find.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		exit.setBounds(950, 680, 100, 40);
		exit.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));

		logb.addActionListener(this); // login
		resb.addActionListener(this); // reset

		reg.addActionListener(this); // 회원등록
		find.addActionListener(this); // id/pw 찾기
		exit.addActionListener(this);// 종료

		login.add(idl);
		login.add(pwl);
		login.add(idt);
		login.add(pwt);
		login.add(logb);
		login.add(resb);
		
		login.add(exit);
		login.add(reg);
		login.add(find);

		login.add(logo);
		

		add(login);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();

		if (str.equals("Login")) {
			list = mgr.login();
			String id = idt.getText();
			String pw = pwt.getText();
			for (int i = 0; i < list.size(); i++) {
				if (id.equals(list.get(i).getMb_id()) && pw.equals(list.get(i).getMb_pass())) {
					JOptionPane.showMessageDialog(null, "     로그인에 성공하였습니다.", "로그인완료!", JOptionPane.CLOSED_OPTION);
					new mmenu();
					staticid=id;
					staticphone=list.get(i).getMb_phone();
					staticpass=list.get(i).getMb_pass();
					dispose();
					return;
				}
			}

			JOptionPane.showMessageDialog(null, "ID 혹은 PW를 확인해주세요.", "Error Message", JOptionPane.ERROR_MESSAGE);
			idt.setText("");
			pwt.setText("");
		}

		
		

		if (str.equals("Reset")) {
			idt.setText("");
			pwt.setText("");
		}

		if (e.getSource() == reg) {
			new Regist();
		}

		if (e.getSource() == find) {
			new Find();
		}

		if (e.getSource() == exit) {
			dispose();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}
}