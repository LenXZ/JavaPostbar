package Amadeus.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.EmptyBorder;

import com.sun.org.apache.regexp.internal.recompile;

import Amadeus.util.DBUtil;
import entity.User;

/**
 * 数据库操作*/
public class DAO {
	public DAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	//查询所有用户的信息
	public List<User> findAllUsers(){
		String sqlString="select * from user";
		List<User> list=new ArrayList<User>();
		try {
			//连接数据库
			Connection connection=DBUtil.getConnection();
			//使用预处理进行查询
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			ResultSet rSet=preparedStatement.executeQuery();
			while (rSet.next()) {
				String idString=rSet.getString("id");
				String nameString=rSet.getString("user");//用户名
				String pwdString=rSet.getString("pwd");
				pwdString="****";
				int gender=rSet.getInt("gender");//性别
				int salary=rSet.getInt("salary");//工资
				User user=new User(idString, nameString,pwdString, gender, salary);
				//向list中添加用户
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			//打印堆栈信息
			e.printStackTrace();
		}
		return null;
	}	
	//增加用户
	public void addUser(User user){
		String sqlString="insert into user values (?,?,?,?,?)";
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user.getIdString());
			preparedStatement.setString(2, user.getNameString());
			preparedStatement.setString(3, user.getPwdString());
			preparedStatement.setInt(4, user.getGender());
			preparedStatement.setInt(5, user.getSalary());
			preparedStatement.executeUpdate();
			System.out.println("插入成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//删除用户
	public void deleteUser(String idString){
		String sqlString="delete from user where id=?";
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, idString);
			preparedStatement.executeUpdate();
			System.out.println("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//根据id查找用户
	public User findById(String idString){
		String sqlString="select * from user where id=?";
		User user = null;
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, idString);
			ResultSet rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				String nameString=rSet.getString("user");
				int gender=rSet.getInt("gender");
				int salary=rSet.getInt("salary");
				user=new User(idString, nameString, "****", gender, salary);
				
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	//修改用户信息
	public void modifyUser(User user){
		String sqlString="update user set user=?,gender=?,salary=? where id=?";
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user.getNameString());
			preparedStatement.setInt(2, user.getGender());
			preparedStatement.setInt(3, user.getSalary());
			preparedStatement.setString(4, user.getIdString());
			preparedStatement.executeUpdate();
			System.out.println("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DAO dao=new DAO();
		User user=new User("000", "Lab","000", 1, 13000);
//		dao.addUser(user);
//		dao.deleteUser("000");
//		System.out.println(dao.findById("002"));
//		System.out.println(dao.findAl());
		dao.modifyUser(user);
	}
}
