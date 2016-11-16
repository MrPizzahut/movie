import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



class cenokk extends JDialog{
	JLabel ok = new JLabel("취소되었습니다.");
	JButton okb = new JButton("닫기");
	
	public cenokk() {
		setLayout(null);
		setLocation(700, 600);
		setTitle("취소완료");
		setSize(200, 140);
		ok.setFont(new Font("고딕",Font.BOLD,15));
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

public class ReservationFrame extends MFrame implements ActionListener {
	cenokk dialog;
	DBCon conn = new DBCon();
	JButton prev = new JButton("이전");
	JButton cancel = new JButton("취소");
	DBMgr mgr = new DBMgr();
	String c[] = { "0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	int intyy;
	int ii;

	public ArrayList<ReservationDTO> ResevedMovies() {

		Connection con = null; // 내 pc의 db에 접속
		Statement stmt = null; // db에 sql을 적을 수 있는 판을 만든다.
		ResultSet rs = null;// sql한 결과를 담는 그릇을 마는다.
		ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
		String sql = "select * from movies where id = '" + Login.staticid + "'";

		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ReservationDTO bean = new ReservationDTO();
				bean.setDate(rs.getString(1));
				bean.setTitle(rs.getString(2));
				bean.setTime(rs.getString(3));
				bean.setTheater(rs.getString(4));
				bean.setSeat(rs.getString(5));
				bean.setNum(rs.getInt(6));
				bean.setId(rs.getString(7));
				list.add(bean);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	
	JTable table;
	JScrollPane scpan;

	String col[] = { "Date", "Title", "Time","Theater", "Seat" };

	ArrayList<ReservationDTO> list;
	ReservationDTO bean;

	public ReservationFrame() {
		dialog = new cenokk();
		this.setTitle("예약관리");
		list = ResevedMovies();
		init();
	}

	public ReservationFrame(ArrayList<ReservationDTO> list) {
		this.setTitle("예약관리");
		this.list = list;
		init();
	}

	private void init() {
		Container con = this.getContentPane();
		
		JLabel logot = new JLabel("Administration"); //로고
		logot.setBounds(70, 30, 500, 110);
		logot.setFont(new Font("Berlin Sans FB",Font.PLAIN,80));
		logot.setForeground(Color.WHITE);
		
		ImageIcon bg = new ImageIcon("images/bg.gif");
		JLabel bgs = new JLabel();
		bgs.setBounds(0, 0, 1100, 800);
		bgs.setIcon(bg);

		String record[][] = new String[list.size()][col.length];
		for (int i = 0; i < list.size(); i++) {
			bean = list.get(i);
			record[i][0] = bean.getDate();
			record[i][1] = bean.getTitle();
			record[i][2] = bean.getTime();
			record[i][3] = bean.getTheater();
			record[i][4] = bean.getSeat();
		//	record[i][5] = bean.getNum();
		}
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		con.setLayout(null);
		DefaultTableModel model = new DefaultTableModel(record, col){
		    public boolean isCellEditable(int row, int col) {
		     return false;
		    }};
		

		table = new JTable(model);
		table.setFont(new Font("고딕체", Font.ITALIC, 20));
		table.setRowHeight(50);
		table.getColumn("Date").setPreferredWidth(-50);
		table.getColumn("Seat").setPreferredWidth(-50);
		table.getColumn("Time").setPreferredWidth(150);
		table.getColumn("Theater").setPreferredWidth(-200);
		table.getColumn("Theater").setCellRenderer(celAlignCenter);
		table.getColumn("Date").setCellRenderer(celAlignCenter);
		table.getColumn("Seat").setCellRenderer(celAlignCenter);
		table.getColumn("Time").setCellRenderer(celAlignCenter);
		table.getColumn("Title").setCellRenderer(celAlignCenter);
		table.getColumn("Title").setPreferredWidth(100);
		scpan = new JScrollPane(table);
		JPanel panline = new JPanel(new BorderLayout());
	//	panline.setBorder(new TitledBorder(new EtchedBorder(), "예약관리"));
		panline.add(scpan, BorderLayout.CENTER);
		panline.setBounds(70, 150, 960, 500);
		con.add(panline);
		
		
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		
		prev.setBounds(750, 670, 130, 65); // 버튼 사이즈
		cancel.setBounds(900, 670, 130, 65);
		prev.addActionListener(this);
		cancel.addActionListener(this);
		con.add(prev);
		con.add(cancel);
		con.add(logot);
		con.add(bgs);

		// this.pack();
		this.repaint();
		this.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==prev) {
			mmenu mm = new mmenu();
			dispose();
		}
		if (e.getSource()==cancel) {
			int result = JOptionPane.showConfirmDialog(null, "취소하시겠습니까?", "취소", JOptionPane.YES_NO_OPTION);
			// DB에서 중복검사필요
			if (result == JOptionPane.CLOSED_OPTION) {
			} else if (result == JOptionPane.YES_OPTION) {
				
				DefaultTableModel model2=(DefaultTableModel)table.getModel();
				int row=table.getSelectedRow();
				if(row<0){return;}
				
				String num=(String)model2.getValueAt(row,4);
				String date=(String)model2.getValueAt(row,0);
				String title=(String)model2.getValueAt(row,1);
				String time=(String)model2.getValueAt(row,2);
				
				System.out.println(num);
				String yy=num.substring(0,1);
				String xx=num.substring(1,2);
				System.out.println(yy);
				System.out.println(xx);
				
				for(ii=1;ii<=10;ii++){
					if(c[ii].equals(yy)){
						
						intyy=ii;
					}
				}
				int intxx=Integer.parseInt(xx);
				
				System.out.println(intyy);
				System.out.println(intxx);
				
				mgr.seatdelete(title,date,time,intyy,intxx);
				
				
				list = mgr.delete(row,model2);
				
	
				JOptionPane.showMessageDialog(null, "                    " + "취소되었습니다.", "취소완료",
						JOptionPane.DEFAULT_OPTION);
				repaint();
		
			}
			
		}
		
	}

	public static void main(String[] args) {
		new ReservationFrame();
	}
}
