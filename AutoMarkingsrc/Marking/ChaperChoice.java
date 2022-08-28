package Marking;

import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.DAO;

public class ChaperChoice {
	DAO dao = new DAO();
	int IChoice=2, IExplain=8, IFillblank=10, IJudge=4, ISimple=6;// 选择|解释|填空|判断|简答
	ResultSet rs = null;
	String Choice, Explain, Fillblank, Judge, Simple;// 选择|解释|填空|判断|简答
	int paper, stId;

	/** 试卷选择 */
	public ChaperChoice(int s, int s2) {/** 试卷ID 学生ID*/
		paper = s;
		stId = s2;
		dao.setSQL("SELECT * FROM paperbiao WHERE paId = " + s );
		rs = dao.Query();
		try {
			while (rs.next()) {
				Choice = rs.getString("paChSumId");
				Fillblank = rs.getString("paFiSumId");//4
				Judge = rs.getString("paJuSumId");//6
				Explain = rs.getString("paExSumId");
				Simple = rs.getString("paSmSumId");//10
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 选择题获取题目 */
	public String getChoiceP() {
		String re = "";
		String Ti[] = Choice.split(",");
		for (int i = 0; i < Ti.length; i++) {
			// System.out.println(""+Ti[i]);
			dao.setSQL("SELECT * FROM ChoiceBiao WHERE chId=" + Integer.parseInt(Ti[i]) + "");
			rs = dao.Query();
			try {
				rs.next();
				re = re.concat(rs.getString("chTopic")).concat("ミ");
				re = re.concat(rs.getString("chOptionA")).concat("ミ");
				re = re.concat(rs.getString("chOptionB")).concat("ミ");
				re = re.concat(rs.getString("chOptionC")).concat("ミ");
				re = re.concat(rs.getString("chOptionD")).concat("#");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}

	/** 选择题获取标准答案 */
	public String getChoiceA() {
		String re = "";
		String Ti[] = Choice.split(",");
		for (int i = 0; i < Ti.length; i++) {
			dao.setSQL("SELECT * FROM ChoiceBiao WHERE chId=" +Integer.parseInt(Ti[i]) + "");
			rs = dao.Query();
			try {
				while (rs.next()) {
					re = re.concat(rs.getString("chAnswer"));
					re = re.concat(",");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}

	/** 选择题获取学生答案 */
	public String getChoiceSA() {
		String re = "";
		dao.setSQL("SELECT * FROM AnswerBiao WHERE PaId=" + paper
				+ " AND StId=" + stId + "");
		rs = dao.Query();
		try {
			rs.next();
			re = rs.getString("AnChSumId");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return re;
	}

	/** 判断题获取题目 */
	public String getJudgeP() {
		String re = "";
		String Ti[] = Judge.split(",");
		for (int i = 0; i < Ti.length; i++) {
			// System.out.println(""+Ti[i]);
			dao.setSQL("SELECT * FROM JudgeBiao WHERE juId=" + Integer.parseInt(Ti[i]) + "");
			rs = dao.Query();
			try {
				rs.next();
				re = re.concat(rs.getString("juTopic")).concat("#");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}

	/** 判断题获取标准答案 */
	public String getJudgeA() {
		String re = "";
		String Ti[] = Judge.split(",");
		for (int i = 0; i < Ti.length; i++) {
			dao.setSQL("SELECT * FROM JudgeBiao WHERE juId=" + Integer.parseInt(Ti[i]) + "");
			rs = dao.Query();
			try {
				while (rs.next()) {
					re = re.concat(rs.getString("juAnswer"));
					re = re.concat(",");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}

	/** 判断题获取学生答案 */
	public String getJudgeSA() {
		String re = "";
		dao.setSQL("SELECT * FROM AnswerBiao WHERE paId=" + paper
				+ " AND StId=" + stId + "");
		rs = dao.Query();
		try {
			rs.next();
			re = rs.getString("AnJuSumId");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return re;
	}
	/** 解释题获取题目 */
	public String getExplainP() {
		String re = "";
		String Ti[] = Explain.split(",");
		for (int i = 0; i < Ti.length; i++) {
			// System.out.println(""+Ti[i]);
			dao.setSQL("SELECT * FROM ExplainBiao WHERE exId=" + Integer.parseInt(Ti[i]) + "");
			rs = dao.Query();
			try {
				rs.next();
				re = re.concat(rs.getString("exTopic")).concat("#");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}

	/** 解释题获取标准答案 */
	public String getExplainA() {
		String re = "";
		String Ti[] = Explain.split(",");
		for (int i = 0; i < Ti.length; i++) {
			dao.setSQL("SELECT * FROM ExplainBiao WHERE exId=" +Integer.parseInt(Ti[i]) + "");
			rs = dao.Query();
			try {
				while (rs.next()) {
					re = re.concat(rs.getString("exAnswer"));
					re = re.concat("#");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}

	/** 解释题获取学生答案 */
	public String getExplainSA() {
		String re = "";
		dao.setSQL("SELECT * FROM AnswerBiao WHERE paId=" + paper
				+ " AND StId=" + stId + "");
		rs = dao.Query();
		try {
			rs.next();
			re = rs.getString("AnExSumId");
		} catch (SQLException e1) {
			// TODO Auto-generated catch blockミ
			e1.printStackTrace();
		}
		return re;
	}
	/** 填空题获取题目 */
	public String getFillblankP() {
		String re = "";
		String Ti[] = Fillblank.split(",");
		for (int i = 0; i < Ti.length; i++) {
			// System.out.println(""+Ti[i]);
			dao.setSQL("SELECT * FROM FillblankBiao WHERE fbId=" + Integer.parseInt(Ti[i]) + "");
			rs = dao.Query();
			try {
				rs.next();
				re = re.concat(rs.getString("fbTopic")).concat("#");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}
	/** 填空题获取标准答案 */
	public String getFillblankA() {
		String re = "";
		String Ti[] = Fillblank.split(",");
		for (int i = 0; i < Ti.length; i++) {
			dao
					.setSQL("SELECT * FROM FillblankBiao WHERE fbId=" + Integer.parseInt(Ti[i]));
			rs = dao.Query();
			try {
				while (rs.next()) {
					re = re.concat(rs.getString("fbAnswer"));
					re = re.concat("#");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}

	/** 填空题获取学生答案 */
	public String getFillblankSA() {
		String re = "";
		dao.setSQL("SELECT * FROM AnswerBiao WHERE paId=" + paper
				+ " AND StId=" + stId);
		rs = dao.Query();
		try {
			rs.next();
			re = rs.getString("AnFiSumId");
		} catch (SQLException e1) {
			// TODO Auto-generated catch blockミ
			e1.printStackTrace();
		}
		return re;
	}
	/** 简答题获取题目 */
	public String getSimpleP() {
		String re = "";
		String Ti[] = Simple.split(",");
		for (int i = 0; i < Ti.length; i++) {
			dao.setSQL("SELECT * FROM SimpleBiao WHERE smId=" + Integer.parseInt(Ti[i]) + "");
			rs = dao.Query();
			try {
				rs.next();
				re = re.concat(rs.getString("smTopic")).concat("#");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}
	/** 简答题获取标准答案 */
	public String getSimpleA() {
		String re = "";
		String Ti[] = Simple.split(",");
		for (int i = 0; i < Ti.length; i++) {
			dao
					.setSQL("SELECT * FROM SimpleBiao WHERE smId=" + Integer.parseInt(Ti[i]));
			rs = dao.Query();
			try {
				while (rs.next()) {
					re = re.concat(rs.getString("smAnswer"));
					re = re.concat("#");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}

	/** 简答题获取学生答案 */
	public String getSimpleSA() {
		String re = "";
		dao.setSQL("SELECT * FROM AnswerBiao WHERE paId=" + paper
				+ " AND StId=" + stId);
		rs = dao.Query();
		try {
			rs.next();
			re = rs.getString("AnSmSumId");
		} catch (SQLException e1) {
			// TODO Auto-generated catch blockミ
			e1.printStackTrace();
		}
		return re;
	}
	public int NumExplain(){
		String Ti[] = Explain.split(",");
		return Ti.length;
	}
	public int NumFillblank(){
		String Ti[] = Fillblank.split(",");
		return Ti.length;
	}
	public int NumSimple(){
		String Ti[] = Simple.split(",");
		return Ti.length;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
