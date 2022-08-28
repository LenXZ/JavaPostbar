package am;
import java.sql.*;
import java.sql.DriverManager;;

public class Amadeus {

	public static void main(String[] args)throws Exception {//抛出异常
		
		//加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
		//请求资源路径
		String url="jdbc:mysql://localhost:3306/Amadeus";
		//用户名
		String user="root";
		//密码
		String pwd="root";
		//获取链接
		Connection conn=DriverManager.getConnection(url,user,pwd);
		System.out.println(conn);
		//建立操作指令
		Statement sta=conn.createStatement();
		//获取结果集
		ResultSet rs=sta.executeQuery("select * from lab ");
		for(int id=1;rs.next();){//询问是否还有下一个元素
			String name=rs.getString("name");
			int age=rs.getInt("age");
			System.out.println(id+++"\t"+name+"\t"+age);
		}
		//与查询无关的用update，返回值是整数，表示影响了几条数据
//		sta.executeUpdate("insert into lab(name,age) values ('moe',22)");
		sta.executeUpdate("update lab set name='rintaro' where id=1");
		//execute&executeUpdate都有效
		int influence=sta.executeUpdate("alter table lab modify sex int;");
		System.out.println(influence);
	}

}
