package Marking;

import java.sql.ResultSet;
import DAO.DAO;

public class AutoMarking {
	DAO dao = new DAO();
	ResultSet rs = null;
	String Choice, Explain, Fillblank, Judge, Simple;// 选择|解释|填空|判断|简答
	int tinum=100;
	/** 选择题判断 */
	public String ChoiceMarking(String s1, String s2,int s4,int s5) {//标准答案|学生答案| 学生ID 卷ID
		String re = "";
		String T1[] = s1.split(",");
		String T3[] = s2.split(",");
		String T2[]=new String[tinum];
		for (int i = 0; i < T2.length; i++) {
			try {
				T2[i] = T3[i];
			} catch (Exception e) {
				T2[i] = " ";
				// TODO: handle exception
			}
		}
		for (int i = 0; i < T1.length && i < T2.length; i++) {
			if (T1[i].equals(T2[i])) {
				re = re.concat("1").concat(",");
			} else {
				re = re.concat("0").concat(",");
			}
		}
		DAO table=new DAO();
		table.setSQL("UPDATE PointBiao SET PoChSumP = '"+re+"' WHERE StId= "+s4+" AND PaId="+s5);
		table.Insert();
		return re;
	}

	/** 判断题判断 */
	public String JudgeMarking(String s1, String s2,int s4,int s5) {
		String re = "";
		String T1[] = s1.split(",");
		String T3[] = s2.split(",");
		String T2[]=new String[tinum];
		for (int i = 0; i < T2.length; i++) {
			try {
				T2[i] = T3[i];
			} catch (Exception e) {
				T2[i] = " ";
				// TODO: handle exception
			}
		}
		for (int i = 0; i < T1.length && i < T2.length; i++) {
			if (T1[i].equals(T2[i])) {
				re = re.concat("1").concat(",");
			} else {
				re = re.concat("0").concat(",");
			}
		}	
		DAO table=new DAO();
		table.setSQL("UPDATE PointBiao SET PoJuSumP = '"+re+"' WHERE StId= "+s4+" AND PaId="+s5);
		table.Insert();
		return re;
	}

	/** 填空题判断 */
	public String FillblankMarking(String s1, String s2) {
		String re = "";
		String T1[] = s1.split("#");
		String T2[] = s2.split("#");
		for (int i = 0; i < T1.length && i < T2.length; i++) {
			String T11[] = T1[i].split("[，,]");
			String T21[] = T2[i].split("ミ");
			for (int i1 = 0; i1 < T11.length; i1++) {
				for (int i2 = 0; i2 < T21.length; i2++) {
//					System.out.println(""+T11[i1]+" "+T21[i2]);
					if (T11[i1].equals(T21[i2])) {
						re = re.concat("1").concat(",");
						break;
					}
					if (i2 == T21.length - 1) {
						re = re.concat("0").concat(",");
					}
				}
			}
//			int i3 = T11.length - T21.length;
//			while (i3 > 0) {
//				re = re.concat("0").concat(",");
//				i3--;
//			}
		}
		return re;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
