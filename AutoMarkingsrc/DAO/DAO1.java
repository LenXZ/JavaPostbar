package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**JDBC���ݿ�����*/
public class DAO1 {
	private static Connection con = null;
	private ResultSet rs;
	private PreparedStatement pstm;
	Statement sql;
	String datasourceName = ""; // ����Դ��
	String uri = "jdbc:odbc:aaa";
	String id = "root";
	String password = "";
	String SQL, message = "", str1, str2;
	String tableName = ""; // ����
	double sum1 = 0;
	int cate;
	// String
	// ID,Name1,Date,Price1,Price2,Number,Numberall,Category,Pay,Prize,Password,Tel,Address;
	public DAO1() {		
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
	public void Con() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection(
					"jdbc:odbc:aaa", "",
					"");
//			if (con != null)
//				System.out.print("");
			// pstm = con.prepareStatement(sql);
			// rs=pstm.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			con = DriverManager.getConnection(uri);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
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
//			System.out.println("�ɹ���������");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	public String Query() {
//		Connection con;
//		ResultSet rs;
//		String message1 = "";
//		try {
//			con = DriverManager.getConnection(uri, id, password);
//			DatabaseMetaData metadata = con.getMetaData();
//			ResultSet rs1 = metadata.getColumns(null, null, tableName, null);
//			int �ֶθ��� = 0, fafa = 0;
//			while (rs1.next()) {
//				�ֶθ���++;
//			}
//			sql = con.createStatement();
//			rs = sql.executeQuery(SQL);
//			while (rs.next()) {
//				for (int k = 1; k <= �ֶθ���; k++) {
//					message1 = message1.concat(rs.getString(k));
//					message1 = message1.concat(" ");
//					fafa = 1;
//				}
//				message1 = message1.concat("\n");
//			}
//			if (fafa == 0) {
//				message1 = "��Ŀ������";
//			} else
//				con.close();
//		} catch (Exception e) {
//			System.out.println("��������ȷ�ı���" + e);
//		}
//		return message1;
//	}

	// ����
	public String inputQueryResult() {
		Connection con;
		Statement sql;
		ResultSet rs;
		String message1 = "";
		double d1, d2;
		String s1, s2;
		sum1 = 0;
		try {
			String uri = "jdbc:odbc:" + datasourceName;
			String id = "";
			String password = "";
			con = DriverManager.getConnection(uri, id, password);
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = sql.executeQuery(SQL);
			rs.last();
			rs.afterLast();
			if (cate == 0 || cate == 3) {// ����
				d1 = 0;
				message1 = "";
				while (rs.previous()) {
					String name = rs.getString(2);
					double n1 = rs.getDouble(4);
					double n2 = rs.getDouble(5);
					d2 = n1 + n2;
					d1 = n1 + n2 + d1;
					s1 = String.valueOf(n1);
					s2 = String.valueOf(n2);
					str1 = String.valueOf(d1);
					message1 = message1.concat(name + " " + s1 + " " + s2 + " "
							+ d2 + " ");
				}
				message1 = message1.concat("*" + " " + "*" + " " + "*" + " "
						+ str1 + " ");
				sum1 = d1;
			}
			if (cate == 1 || cate == 4) {// ֧��
				d1 = 0;
				message1 = "";
				while (rs.previous()) {
					String name = rs.getString(2);
					double n1 = rs.getDouble(4);
					double n2 = rs.getDouble(6);
					d2 = n1 * n2;
					d1 = n1 * n2 + d1;
					s1 = String.valueOf(n1);
					s2 = String.valueOf((int) n2);
					str1 = String.valueOf(d1);
					message1 = message1.concat(name + " " + s1 + " " + s2 + " "
							+ d2 + " ");
				}
				message1 = message1.concat("*" + " " + "*" + " " + "*" + " "
						+ str1 + " ");
				sum1 = d1;
			}
			if (cate == 2 || cate == 5) {// ����
				d1 = 0;
				message1 = "";
				while (rs.previous()) {
					String name = rs.getString(2);
					double n1 = rs.getDouble(5);
					double n2 = rs.getDouble(7);
					d2 = n1 * n2;
					d1 = n1 * n2 + d1;
					s1 = String.valueOf(n1);
					s2 = String.valueOf((int) n2);
					str1 = String.valueOf(d1);
					message1 = message1.concat(name + " " + s1 + " " + s2 + " "
							+ d2 + " ");
				}
				message1 = message1.concat("*" + " " + "*" + " " + "*" + " "
						+ str1 + " ");
				sum1 = d1;
			}
			if (cate == 3 || cate == 4 || cate == 5) {// ����
				message1 = "";
				message1 = String.valueOf(sum1);
			}

			con.close();
		} catch (Exception e) {
			System.out.println("��������ȷ�ı���" + e);
		}
		return message1;
	}
	// public static void main(String[] args) {
	// new BookDAO().getBookimg("SELECT book_print FROM bookdatabase.t_books");
	// }
}
