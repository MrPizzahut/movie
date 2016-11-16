import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//DB데이터 조작, 값 받아오는 메소드
public class DBMgr extends Frame {
	
	Connection ccc = null; // 내 pc의 db에 접속
	String url="jdbc:mysql://localhost:3306/movieproject";
	String ii="root";
	String ss="1234";
	

	public DBMgr() {
	
		
		try{
			ccc=DriverManager.getConnection(url,ii,ss);
		}catch(SQLException e){
			System.err.println("sql error="+e);
			System.exit(1);
		}
		
	}

	public ArrayList<MbBean> insertMb(String id, String pass, String phone) {
		
		Statement stmt = null; // db에 sql을 적을 수 있는 판을 만든다.
		
		ArrayList<MbBean> list = new ArrayList<MbBean>();
		String sql = "insert into mv_member values ('" + id + "','" + pass + "','" + phone + "')";

		try {
			stmt = ccc.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "중복된 아이디가 있습니다.");
		} finally {
			try {

				stmt.close();
			//	ccc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch
		return list;
	}

	public ArrayList<MbBean> searchMb(String num) {
		
		Statement stmt = null; // db에 sql을 적을 수 있는 판을 만든다.
		ResultSet rs = null;// sql한 결과를 담는 그릇을 마는다.
		ArrayList<MbBean> list = new ArrayList<MbBean>();
		String sql = "select * from mv_member where Mb_id = '" + num + "'";

		try {
			stmt = ccc.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MbBean bean = new MbBean();
				bean.setMb_id(rs.getString(1));
				bean.setMb_pass(rs.getString(2));
				bean.setMb_phone(rs.getString(3));
				list.add(bean);
			} // whie
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			//	ccc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch
		return list;
	}

	// Admin 이라는 클래스의 아이디와 패스워드를 받아온다
	public ArrayList<MbBean> login() {
		
		Statement stmt = null; // db에게 sql를 적을 수 있는 판 생성.
		ResultSet rs = null;// sql의 결과값을 저장
		ArrayList<MbBean> list = new ArrayList<MbBean>();
		String sql = "select * from mv_member";
		MbBean bean;
		try {
			
			stmt = ccc.createStatement();
			rs = stmt.executeQuery(sql); // sql의 결과에 대한 테이블
			while (rs.next()) {
				bean = new MbBean();
				bean.setMb_id(rs.getString(1));
				bean.setMb_pass(rs.getString(2));
				bean.setMb_phone(rs.getString(3));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			//	ccc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

//	
//	public ArrayList<MbBean> updateMb2(String name, String num, String phone,
//			String addr) {
//		Connection con = null; // 내 pc의 db에 접속
//		Statement stmt = null; // db에 sql을 적을 수 있는 판을 만든다.
//		
//		
//		System.out.println(name + num + phone + addr);
//		ArrayList<MbBean> list = new ArrayList<MbBean>();
//		String sql = "update member set Mb_name ='" + name + "',Mb_num ='"
//				+ num + "',Mb_phone ='" + phone + "',Mb_addr ='" + addr
//				+ "' where Mb_num ='" + num + "'";
//
//		try {
//			con = conn.getConnection();
//			stmt = con.createStatement();
//			stmt.executeUpdate(sql);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				stmt.close();
//				// con.close();
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}// try catch
//		return list;
//
//	}

	public ArrayList<MbBean> updatepw(String pass, String id) {
	
		PreparedStatement pstmt=null; // db에 sql을 적을 수 있는 판을 만든다.
	
		ArrayList<MbBean> list = new ArrayList<MbBean>();
		String sql = "update mv_member set Mb_pass= ? where Mb_id=?";

		try {
			
			pstmt=ccc.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			//	ccc.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}// try catch
		return list;

	}
	
	
	public ArrayList<ReservationDTO> delete(int row, DefaultTableModel model2) {
		
		PreparedStatement pstmt=null; // db에 sql을 적을 수 있는 판을 만든다.
	
		ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
		String sql = "delete from movies where date = ? and title= ? and time= ? and seat= ?";

		try {
			
			pstmt=ccc.prepareStatement(sql);
			pstmt.setString(1, (String)model2.getValueAt(row,0));
			pstmt.setString(2, (String)model2.getValueAt(row,1));
			pstmt.setString(3, (String)model2.getValueAt(row,2));
			pstmt.setString(4, (String)model2.getValueAt(row,4));
			pstmt.executeUpdate();
		
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			//	ccc.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}// try catch
		model2.removeRow(row);
		return list;

	}
	
	
public ArrayList<SeatBean> seatdelete(String title,String date,String time,int yy,int xx) {
		
		PreparedStatement pstmt=null; // db에 sql을 적을 수 있는 판을 만든다.
	
		ArrayList<SeatBean> list = new ArrayList<SeatBean>();
		String sql = "delete from seat where title_s = ? and date_s= ? and time_s= ? and seaty= ? and seatx= ? ";

		try {
			
			pstmt=ccc.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, date);
			pstmt.setString(3, time);
			pstmt.setInt(4, yy);
			pstmt.setInt(5, xx);
			pstmt.executeUpdate();
		
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			//	ccc.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}// try catch
		return list;

	}
	
	
	public ArrayList<MbBean> modify(String pw2, String phone) {
		
		PreparedStatement pstmt=null; // db에 sql을 적을 수 있는 판을 만든다.
	
		ArrayList<MbBean> list = new ArrayList<MbBean>();
		String sql = "update mv_member set Mb_pass= ?,Mb_phone=? where Mb_id=?";

		try {
			
			pstmt=ccc.prepareStatement(sql);
			pstmt.setString(1, pw2);
			pstmt.setString(2, phone);
			pstmt.setString(3, Login.staticid);
			pstmt.executeUpdate();
		
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			//	ccc.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}// try catch
		return list;

	}
	
	public ArrayList<rebean> inmovie(String DATE, String title,String TIME,String theater, String seat, int num, String id) 
	{
		Statement stmt = null;
		ArrayList<rebean> list =new ArrayList<rebean>();
		String sql ="insert into movies values ('" +DATE+"','"+title+"','"+TIME+"','"+theater+"','"+seat+"','"+num+"','"+id+"')";
		try{
			stmt = ccc.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				stmt.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}

//	
//	public ArrayList<SeatBean> seatupdate (String date, String title, String time,String seatal,int seatnum){
//		PreparedStatement pstmt = null; // db에 sql을 적을 수 있는 판을 만든다.
//
//		ArrayList<SeatBean> list = new ArrayList<SeatBean>();
//		String sql=null;
//		switch (seatnum) {
//		case 0: sql = "update seat set seat0 = "+1+" where num_s= ? and time_s= ? and seat_n= ?";break;
//		case 1: sql = "update seat set seat1 = "+1+" where num_s= ? and time_s= ? and seat_n= ?";break;
//		case 2: sql = "update seat set seat2 = "+1+" where num_s= ? and time_s= ? and seat_n= ?";break;
//		case 3: sql = "update seat set seat3 = "+1+" where num_s= ? and time_s= ? and seat_n= ?";break;
//		case 4: sql = "update seat set seat4 = "+1+" where num_s= ? and time_s= ? and seat_n= ?";break;
//		case 5: sql = "update seat set seat5 = "+1+" where num_s= ? and time_s= ? and seat_n= ?";break;
//		case 6: sql = "update seat set seat6 = "+1+" where num_s= ? and time_s= ? and seat_n= ?";break;
//		case 7: sql = "update seat set seat7 = "+1+" where num_s= ? and time_s= ? and seat_n= ?";break;
//		case 8: sql = "update seat set seat8 = "+1+" where num_s= ? and time_s= ? and seat_n= ?";break;
//		case 9: sql = "update seat set seat9 = "+1+" where num_s= ? and time_s= ? and seat_n= ?";break;
//
//		default:break;
//		}
//	//	String sql = "update seat set ? = "+1+" where num_s= ? and time_s= ? and seat_n= ?";
//
//		try {
//			pstmt = ccc.prepareStatement(sql);
//			pstmt.setString(1, title);
//			pstmt.setString(2, time);
//			pstmt.setString(3, seatal);
//			
//		
//			pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				pstmt.close();
//				// ccc.close();
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} // try catch
//		return list;
//
//	}
	
	
	public ArrayList<SeatBean> seatinsert (String title, String date, String time,int seaty,int seatx){
		PreparedStatement pstmt = null; // db에 sql을 적을 수 있는 판을 만든다.
		
		ArrayList<SeatBean> list = new ArrayList<SeatBean>();
		
		String sql = "insert into seat values(? , ? , ?, ? , ? )";

		try {
			
			pstmt=ccc.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, date);
			pstmt.setString(3, time);
			pstmt.setInt(4, seaty);
			pstmt.setInt(5, seatx);
			
			pstmt.executeUpdate();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// ccc.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch
		return list;

	}
	
	
	
	public ArrayList<SeatBean> seatload (String title, String date, String time){
		PreparedStatement pstmt = null; // db에 sql을 적을 수 있는 판을 만든다.
		ResultSet rs = null;
		ArrayList<SeatBean> list = new ArrayList<SeatBean>();
		
		SeatBean bean;
		
		String sql = "select * from seat where title_s= ? and date_s= ? and time_s= ? ";

		try {
			
			pstmt = ccc.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, date);
			pstmt.setString(3, time);
		//	pstmt.setInt(4, seaty);
		//	pstmt.setInt(5, seatx);
			
			
			rs = pstmt.executeQuery(); // sql의 결과에 대한 테이블
			while (rs.next()) {
				bean=new SeatBean();
				bean.setTitle_s(rs.getString(1));
				bean.setDate_s(rs.getString(2));
				bean.setTime_s(rs.getString(3));
				bean.setSeaty(rs.getInt(4));
				bean.setSeatx(rs.getInt(5));
			
			
				list.add(bean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// ccc.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch
		return list;

	}

}
