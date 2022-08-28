package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 *获取数据库连接的工具类
 */
public class DBUtil {
	public static Connection 
					getConnection(){
		try {
			Class.forName
				("com.mysql.jdbc.Driver");
			String url=
			"jdbc:mysql://localhost:3306/lx" +
			"?useUnicode=true" +
			"&characterEncoding=utf8";
			String user ="root";
			String pwd = "1234";
			Connection conn =
				DriverManager.getConnection
				(url,user,pwd);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//测试链接是否获取成功
	public static void main(String[] args) {
		Connection conn= 
			DBUtil.getConnection();
		System.out.println(conn);
	}
	
}
