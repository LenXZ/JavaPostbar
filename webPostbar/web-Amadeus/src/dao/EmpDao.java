package dao;

import java.util.List;
import entity.Emp;
/**
 * ½Ó¿Ú
 * */
public interface EmpDao {

	public List<Emp> findAll();
	public void addEmp(Emp emp);
	public Emp findById(String idString);
	public void deleteEmp(String idString);
	public void modifyEmp(Emp emp);

}
