
/*
 * import java.sql.Connection;
 * import java.sql.DriverManager;
*/
//DB 드라이버 설치 접속
import java.sql.*;
public class DBCon {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost/movieproject";
	String id = "root";
	String pw="1234";
	Connection con = null;
	public DBCon(){
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		return con;
	}
}
