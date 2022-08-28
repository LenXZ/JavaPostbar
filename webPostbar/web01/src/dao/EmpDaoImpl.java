package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import util.DBUtil;
import entity.Emp;

public class EmpDaoImpl implements EmpDao{

	public void addEmp(Emp emp) {
		String sql = 
		"insert into emp values(?,?,?,?)";
		try {
			Connection conn = 
				DBUtil.getConnection();
			PreparedStatement prep = 
				conn.prepareStatement(sql);
			prep.setInt(1, emp.getId());
			prep.setString(2, emp.getName());
			prep.setString(3, emp.getGender());
			prep.setInt(4,emp.getSalary());
			
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//删除员工
	public void deleteEmp(int id) {
		String sql = 
			"delete from emp where id=?";
		try {
			Connection conn = 
				DBUtil.getConnection();
			PreparedStatement prep = 
				conn.prepareStatement(sql);
			prep.setInt(1, id);
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查询所有员工
	public List<Emp> findAll() {
		String sql="select * from emp";
		List<Emp> list = 
			new ArrayList<Emp>();
		try {
			Connection conn = 
				DBUtil.getConnection();
			PreparedStatement prep = 
				conn.prepareStatement(sql);
			ResultSet rs = 
				prep.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = 
					rs.getString("name");
				String gender = 
					rs.getString("gender");
				int salary = 
					rs.getInt("salary");
				Emp e = new Emp
				(id,name,gender,salary);
				list.add(e);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//根据id查询
	public Emp findById(int id) {
		String sql = 
			"select * from emp where id=?";
		Emp emp = new Emp();
		try {
			Connection conn= 
				DBUtil.getConnection();
			PreparedStatement prep = 
				conn.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = 
				prep.executeQuery();
			while(rs.next()){
				String name = 
					rs.getString("name");
				String gender = 
					rs.getString("gender");
				int salary = 
					rs.getInt("salary");
				emp = new Emp
				(id,name,gender,salary);
			}
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//修改员工信息
	public void modifyEmp(Emp e) {
		String sql = 
			"update emp set name=?,gender=?," +
			"salary=? where id =?";
		try {
			Connection conn=
				DBUtil.getConnection();
			PreparedStatement prep =
				conn.prepareStatement(sql);
			prep.setString(1, e.getName());
			prep.setString(2, e.getGender());
			prep.setInt(3, e.getSalary());
			prep.setInt(4, e.getId());
			
			prep.executeUpdate();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
