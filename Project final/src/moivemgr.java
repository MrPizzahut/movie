import java.sql.*;
import java.util.*;

public class moivemgr {
	DBCon conn;
	public moivemgr(){
		conn =new DBCon();
	}
	public ArrayList<movieDTO> movie(){
		Connection con =null;
		Statement stmt =null;
		ResultSet rs=null;
		ArrayList<movieDTO> list =new ArrayList<movieDTO>();
		String sql ="select *from movie";
		
		movieDTO bean;
		try{
			con =conn.getConnection();
			stmt =con.createStatement();
			rs= stmt.executeQuery(sql);
			while(rs.next()){
				bean =new movieDTO();
				bean.setKey(rs.getInt(1));
				bean.setNum(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setPoster(rs.getString(4));
				bean.setTime(rs.getString(5));
				list.add(bean);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
		
	}


}

