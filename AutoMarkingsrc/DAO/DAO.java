package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**mysql数据库连接*/
public class DAO {
	private static Connection con = null;
	private ResultSet rs;
	private PreparedStatement pstm;
	Statement sql;
	String datasourceName = ""; // 数据源名
	String uri = "jdbc:mysql://localhost:3306/aaa";
	String id = "root";
	String password = "9269426944";// 9269426944
	String SQL, message = "", str1, str2;
	String tableName = "";  // 表名
	double sum1 = 0;
	int cate;

	public DAO() {}

	// 变量传导
	public void setDatasourceName(String s) {
		datasourceName = s.trim();
	}

	public void setTableName(String s) {
		tableName = s.trim();
	}

	public void setSQL(String SQL) {
		this.SQL = SQL.trim();
	}

	public void setCate(int Cate) {
		cate = Cate;
	}

	// 打开数据库连接*
	public void Con() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(uri, id, password);
			if (con != null)
				System.out.print("");
		} catch (Exception e) {
			e.printStackTrace();
		}		
//		try {
//			con = DriverManager.getConnection(uri);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	// 关闭数据库连接
	public void Close() {
		try {
			pstm.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// 关闭所有数据库连接
	public void CloseAll() {
		try {
			pstm.close();
			con.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public ResultSet Query() {
		ResultSet rs = null;
		try {
			sql = con.createStatement();
			rs = sql.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void Insert() {
		try {
			sql = con.createStatement();
			sql.execute(SQL);
//			System.out.println("成功插入数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
