
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

import dao.UserDao;
import entity.User;
/**
 * UserDao的实现类
 * **/
public class UserDaoImpl implements UserDao{
	public List<User> findAll() {
	
		String sqlString="select * from user";
		List<User> list=new ArrayList<User>();
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				String idString=resultSet.getString("ID");
				String nameString=resultSet.getString("user");
				String pwdString=resultSet.getString("pwd");
				String genderString=resultSet.getString("gender");
				String authority=resultSet.getString("authority");
				User user=new User(idString, nameString, pwdString, genderString, authority);
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//添加用户
	public void addUser(User user) {
		String sqlString="insert into user values (?,?,?,?,?)";
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user.getIdString());
			preparedStatement.setString(2, user.getNameString());
			preparedStatement.setString(3, user.getPwdString());
			preparedStatement.setString(4, user.getGenderString());
			preparedStatement.setString(5, user.getAuthority());
			preparedStatement.executeUpdate();
			System.out.println("插入成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//删除用户
	public void deleteUser(String idString) {
		String sqlString="delete from user where id=?";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sqlString);	
			preparedStatement.setString(1, idString);
			preparedStatement.executeUpdate();
			System.out.println("删除成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//根据ID查询用户
	public User findById(String idString) {
		String sqlString="select * from user where id=?";
		User user = null;
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, idString);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				String nameString=resultSet.getString("user");
				String pwdString=resultSet.getString("pwd");
				String genderString=resultSet.getString("gender");
				String authority=resultSet.getString("authority");
				user=new User(idString, nameString, pwdString, genderString, authority);
				
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	//修改用户
	public void modifyUser(User user) {
		String sqlString="update user set user=?,pwd=?,gender=?,authority=? where id=?";
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user.getNameString());
			preparedStatement.setString(2, user.getPwdString());
			preparedStatement.setString(3, user.getGenderString());
			preparedStatement.setString(4, user.getAuthority());
			preparedStatement.setString(5, user.getIdString());
//			preparedStatement.setString(6, user.getIdString());
			preparedStatement.executeUpdate();
			System.out.println("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDaoImpl userDaoImpl=new UserDaoImpl();
//		userDaoImpl.modifyUser(new User("001","116","女",100));
		System.out.println(userDaoImpl.findById("001"));
	}
	
}
