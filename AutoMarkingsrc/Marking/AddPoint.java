package Marking;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.DAO;

public class AddPoint {
DAO dao=new DAO();
ResultSet rs;
	/**分数累加*/
	public int Addpoint(String s,String spo,String spa,int s2,int s3) {//分数列  题型分数表 题型卷表 学生id 卷id
		int re = 0,po=0;
		String T1[] = s.split(",");
		dao.setSQL("SELECT * FROM PaperBiao WHERE paId=" + s3);
		rs=dao.Query();
		try {
			rs.next();
			po=(int) rs.getLong(spa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < T1.length; i++) {
			re=re+Integer.parseInt(T1[i])*po;
		}		
		dao.setSQL("UPDATE PointBiao SET "+spo+" = "+re+" WHERE StId= "+s2+" AND PaId="+s3);
		dao.Insert();
		return re;
	}
	public int Addpoint(String spo,String spa,int s2,int s3,String tcnm) {//分数列  题型分数表 题型卷表 学生id 卷id 教师姓名
		int re = 0,po=0;
		String s="";
		dao.setSQL("SELECT * FROM PointBiao WHERE PaId="+s3+" AND StId= "+s2);
		rs=dao.Query();
		try {
			rs.next();
			s=s.concat(rs.getString(spo+"SumP"));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String T1[] = s.split(",");
		dao.setSQL("SELECT * FROM PaperBiao WHERE paId=" + s3);
		rs=dao.Query();
		try {
			rs.next();
			po=(int) rs.getLong(spa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < T1.length; i++) {
			re=re+Integer.parseInt(T1[i])*po;
		}		
		dao.setSQL("UPDATE PointBiao SET "+spo+"All = "+re+" , "+spo+"tc = '"+tcnm+"' WHERE StId= "+s2+" AND PaId="+s3);
		dao.Insert();
		return re;
	}
	public void addall(int stid,int paid){
		int all=0;
		String timu[]={"StId","PoChAll","PoJuAll","PoFiAll","PoExAll","PoSmAll","PoAllSum"};
		dao.setSQL("SELECT * FROM PointBiao WHERE PaId="+paid+" AND StId= "+stid);
		rs=dao.Query();
		try {
			rs.next();
			all=(int) rs.getLong(timu[1])+(int) rs.getLong(timu[2])+(int) rs.getLong(timu[3])+(int) rs.getLong(timu[4])+(int) rs.getLong(timu[5]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.setSQL("UPDATE PointBiao SET "+timu[6]+" = '"+all+"' WHERE StId= "+stid+" AND PaId="+paid);
		dao.Insert();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stubミ

	}

}
