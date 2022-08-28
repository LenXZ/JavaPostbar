package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDAO {
	private static Connection con = null;
	private ResultSet rs;
	private PreparedStatement pstm;
	Statement sql;
	String datasourceName = ""; // ����Դ��
	String uri = "jdbc:mysql://127.0.0.1:3306/bookdatabase";
	String id = "root";
	String password = "9269426944";
	String SQL, message = "", str1, str2;
	String tableName = ""; // ����
	double sum1 = 0;
	int cate;
	public BookDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/bookdatabase", "root",
					"9269426944");
			if (con != null)
				System.out.println("���ӳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��������
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

	// �����ݿ�����*
	void getConnection() {
		try {
			con = DriverManager.getConnection(uri);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �ر����ݿ�����
	public void Close() {
		try {
			pstm.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// �ر��������ݿ�����
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
	public void Insert(){
		try {
			sql = con.createStatement();
			sql.execute(SQL);
			System.out.println("�ɹ���������");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
