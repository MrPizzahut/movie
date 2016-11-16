import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Regist extends JFrame implements ActionListener {

	JLabel title = new JLabel("회원등록");
	JLabel id = new JLabel("ID : ");
	JLabel pw = new JLabel("PW : ");
	JLabel hp = new JLabel("HP : ");

	JTextField idt = new JTextField();
	JTextField pwt = new JTextField();
	JTextField hpt = new JTextField();

	JButton chk = new JButton("중복체크");
	JButton cen = new JButton("취소");
	JButton reg = new JButton("등록");
	JButton reset = new JButton("리셋");

	DBMgr mgr = new DBMgr(); // DAO
	ArrayList<MbBean> list;
	MbBean bean; // DTO

	public Regist() {
		list = mgr.login();

		this.setTitle("회원등록");
		this.setResizable(false);
		this.setLocation(600, 400);
		this.setSize(500, 400);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(null);

		title.setBounds(135, 40, 300, 100);
		title.setFont(new Font("고딕", Font.BOLD, 55));

		id.setBounds(70, 150, 70, 30);
		pw.setBounds(70, 200, 70, 30);
		hp.setBounds(70, 250, 70, 30);

		idt.setBounds(120, 150, 220, 30);
		pwt.setBounds(120, 200, 220, 30);
		hpt.setBounds(120, 250, 220, 30);

		chk.setBounds(360, 150, 100, 30); // 중복체크
		reset.setBounds(360, 200, 100, 30); // 리셋
		cen.setBounds(180, 300, 70, 30); // 취소
		reg.setBounds(270, 300, 70, 30); // 등록

		chk.addActionListener(this);
		reset.addActionListener(this);
		cen.addActionListener(this);
		reg.addActionListener(this);

		c.add(title);
		c.add(id);
		c.add(pw);
		c.add(hp);
		c.add(idt);
		c.add(pwt);
		c.add(hpt);

		c.add(chk);
		c.add(reset);
		c.add(cen);
		c.add(reg);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == chk) {

			String id = idt.getText();
			for (int i = 0; i < list.size(); i++) {
				if (id.equals(list.get(i).getMb_id())) {
					System.out.println("no");
					JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.", "ID중복", JOptionPane.OK_OPTION);

					return;
				}
			}
			if (id.equals("")) {
				System.out.println("null");
				JOptionPane.showMessageDialog(null, "ID를 입력해주세요.", "공백", JOptionPane.WARNING_MESSAGE);
			} else {
				System.out.println("ok");
				JOptionPane.showMessageDialog(null, "사용해도 좋은 ID입니다.", "허용가능한 ID", JOptionPane.INFORMATION_MESSAGE);
			}

		}

		if (e.getSource() == cen)
			dispose();

		if (e.getSource() == reg) {
			int result = JOptionPane.showConfirmDialog(null, "등록하시겠습니까?", "등록", JOptionPane.YES_NO_OPTION);
			// DB에서 중복검사필요
			if (result == JOptionPane.CLOSED_OPTION) {

			} else if (result == JOptionPane.YES_OPTION) {

				String id = idt.getText();
				String pass = pwt.getText();
				String phone = hpt.getText();
				for (int i = 0; i < list.size(); i++) {
					if (id.equals(list.get(i).getMb_id())) {
						JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.", "ID중복", JOptionPane.OK_OPTION);
						return;
					}
				}
				if (id.equals("") || pass.equals("") || phone.equals("")) {
					JOptionPane.showMessageDialog(null, "공백은 허용되지 않습니다.", "공백", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "등록되었습니다.", "등록완료", JOptionPane.INFORMATION_MESSAGE);
					mgr.insertMb(id, pass, phone);
					list = mgr.login();
					revalidate();
					dispose();
				}

			} else if (result == JOptionPane.NO_OPTION) {

			}
		}

		if (e.getSource() == reset) {
			idt.setText("");
			pwt.setText("");
			hpt.setText("");
		}
	}

}
