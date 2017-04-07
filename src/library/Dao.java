package library;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Connection;

public class Dao {
	public static Connection conn = null;
	public static ResultSet rs=null;
	public static Statement stmt = null;
	public static Connection getconnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3366/library2","root","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (conn==null){
			System.err.println("数据库连接错误~~~");
		}
		return conn;
	}
	
	public static ResultSet query(String sql){
		conn=getconnection();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);	
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return rs;
	}
	
	public static int update(String sql){
		int a=0;
		conn=getconnection();
		try {
			stmt=conn.createStatement();
			a=stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return a;
	}
	
	public static void close(){
		try {
			if (rs!=null){
				rs.close();
			}
			if (stmt!=null){
				stmt.close();
			}
			if (conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	
	}
}
