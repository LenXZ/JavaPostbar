package Amadeus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 工具类 用于获取数据库连接
 *  
 */
public class DBUtil {
	
	//public static类名即可调用的方法
	public static Connection getConnection(){
		
		try {
			//加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//打印堆栈信息
			e.printStackTrace();
		}
		//请求资源路径
		String url="jdbc:mysql://localhost:3306/Amadeus?useUnicode=true&characterEncoding=utf8";
		//用户名
		String user="root";
		//密码
		String pwd="root";	
		try {
			//获取链接
			Connection conn=DriverManager.getConnection(url,user,pwd);
			//返回连接
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//若出错,则不返回任何值
		return null;
	}
/**
	public static void main(String[] args) {
		Connection connection=DBUtil.getConnection();
		System.out.print(connection);
	}*/
}
