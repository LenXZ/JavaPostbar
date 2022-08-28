package dao;

import java.util.List;

import entity.Emp;

public interface EmpDao {
	//查询所有
	public List<Emp> findAll();
	//根据id查询
	public Emp findById(int id);
	//添加员工
	public void addEmp(Emp emp);
	//删除员工
	public void deleteEmp(int id);
	//修改员工
	public void modifyEmp(Emp e);
}
