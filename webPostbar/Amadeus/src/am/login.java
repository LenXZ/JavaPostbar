package am;

import java.sql.*;
import java.util.Scanner;

import day627.stringuse;

public class login {


	public static void main(String[] args) throws Exception{
		//加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取链接
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/amadeus","root","root");
		//建立操作指令
//		Statement sta =conn.createStatement();		
		Scanner scanner=new Scanner(System.in);//new 调用类的构造器 类的构造器用于初始化属性 属性既是类变量
//		scanner.nextLine();//获取当前控制台中一行的字符串
		System.out.println("请输入ID或用户名");
		//将控制台中的一行字符串转换为userString
		String userString=scanner.nextLine();
		System.out.println("请输入密码");
		//将控制台中的一行字符串转换为pwdString
		String pwdString=scanner.nextLine();
		String sql="select * from user where id=? or user=?";
		//预编译操作指令 批处理速度快
		PreparedStatement preparedStatement=conn.prepareStatement(sql);
		//在第一个问号处插入userString
		preparedStatement.setString(1, userString);
		preparedStatement.setString(2, userString);
		//获取结果集
		ResultSet rs=preparedStatement.executeQuery();
		if (rs.next()) {
			//判断密码是否符合
			if(rs.getString("pwd").equals(pwdString)){
				String gender="";
				//根据sex中的数据确定性别 0为女1为男
				if(rs.getInt("sex")==0){
					gender="女士";
				}else {
					gender="先生";
				}
				System.out.println("欢迎"+rs.getString("user")+gender+"登陆");
			}else{
				System.out.println("密码错误");
			}
						
		}else{
			System.out.println("无此用户");
		}
		
	}

}
