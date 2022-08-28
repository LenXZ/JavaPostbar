package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sun.net.www.content.text.plain;
import util.DBUtil;

import entity.Emp;

public class dao {

	public List<Emp> findAllUsers(){
		String sqlString="select * from emp";
		List<Emp> list=new ArrayList<Emp>();
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				String idString=resultSet.getString("ID");
				String nameString=resultSet.getString("user");
				String genderString=resultSet.getString("gender");
				int salary=resultSet.getInt("salary");
				Emp user=new Emp(idString, nameString, genderString, salary);
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	public void addEmp(Emp emp){
		String sqlString="insert into emp values (?,?,?,?)";
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, emp.getIdString());
			preparedStatement.setString(2, emp.getNameString());
			preparedStatement.setString(3, emp.getGenderString());
			preparedStatement.setInt(4, emp.getSalary());
			preparedStatement.executeUpdate();
			System.out.println("插入成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteEmp(String idString){
		String sqlString="delete from emp where id=?";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sqlString);	
			preparedStatement.setString(1, idString);
			System.out.println("删除成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void modifyEmp(Emp emp){
		
	}
}
