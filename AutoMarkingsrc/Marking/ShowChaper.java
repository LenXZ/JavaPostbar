package Marking;

import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.DAO;

/** 向模板输出卷面内容，包括原题、学生答案、教师批阅痕迹 **/
public class ShowChaper {
	ChaperChoice chaper;
	DAO dao = new DAO();
	int paid, stid, tinum = 300;
	Object biaojiObject[] = { "", "A. ", "B. ", "C. ", "D. " };
	ResultSet rsResultSet;
	String yesString = "<w:rPr><w:color w:val=\"FF0000\"/><w:sz w:val=\"48\"/></w:rPr><w:t>√</w:t>",// <w:proofErr
																									// w:type=\"spellEnd\"/>
			noString = "<w:rPr><w:color w:val=\"FF0000\"/><w:sz w:val=\"72\"/></w:rPr><w:t>×</w:t>";

	public ShowChaper(int paid, int stid) {
		this.stid = stid;
		this.paid = paid;
		dao.Con();
		chaper = new ChaperChoice(paid, stid);
		// TODO Auto-generated constructor stub（ ）
	}

	/** 选择题 **/
	public String showchoice(String timuString) {
		String reString = "<w:p></w:p>";
		String T1[] = timuString.split("[ミ#]");
		String stuA1[] = chaper.getChoiceSA().split(",");
		String stuA[] = new String[tinum];
		for (int i = 0; i < stuA.length; i++) {
			try {
				stuA[i] = stuA1[i];
			} catch (Exception e) {
				stuA[i] = "";
				// TODO: handle exception
			}
		}
		dao.setSQL("SELECT * FROM PointBiao WHERE StId= " + stid + " AND PaId="
				+ paid);
		rsResultSet = dao.Query();
		try {
			rsResultSet.next();
			String tanString[] = rsResultSet.getString("PoChSumP").split(",");
			int tihao = 1;
			int j = 0;
			for (int i = 0; i < T1.length; i++) {
				if (i % 5 == 0 || i == 0) {
					reString = reString.concat("" + tihao++ + " > ").concat(
							T1[i].replace("（  ）", "（ " + stuA[j] + " ）"));// <w:br/>
					try {
						if (tanString[j].equals("1")) {
							reString = reString.concat(yesString).concat(
									"<w:p></w:p>");
						} else {// <w:proofErr w:type=\"spellStart\"/>
							reString = reString.concat(noString).concat(
									"<w:p></w:p>");
						}
					} catch (Exception e) {
						// TODO: handle exception
						reString = reString.concat(noString).concat(
								"<w:p></w:p>");
					}
					j++;
				} else {
					reString = reString.concat("		" + biaojiObject[i % 5])
							.concat(T1[i]).concat("<w:br/>");
				}
				// reString=reString.concat(""+tihao++).concat(T1[i]).concat("<w:br/>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reString;
	}

	/** 填空题 **/
	public String showfi(String timuString) {
		String reString = "";
		String T1[] = timuString.split("[#]");
		String stuA1[] = chaper.getFillblankSA().split("#");
		String stuA[] = new String[tinum];
		for (int i = 0; i < stuA.length; i++) {
			try {
				stuA[i] = stuA1[i];
			} catch (Exception e) {
				stuA[i] = "";
				// TODO: handle exception
			}
		}
		dao.setSQL("SELECT * FROM PointBiao WHERE StId= " + stid + " AND PaId="
				+ paid);
		rsResultSet = dao.Query();
		try {
			rsResultSet.next();
			String tanString[] = rsResultSet.getString("PoFiSumP").split(",");
			int tihao = 1;
			int j = 0;
			for (int i = 0; i < T1.length; i++) {
				reString = reString.concat("" + tihao++ + " > ");
				String stua1[] = stuA[i].split("ミ");
				String tianString = "";
				tianString = T1[i];
				for (int st1 = 0; st1 < stua1.length; st1++) {
					try {
						if (tanString[j].equals("1")) {
							tianString = tianString.replaceFirst("_________",
									"√___" + stua1[st1] + "_");
						} else {// <w:proofErr w:type=\"spellStart\"/>
							tianString = tianString.replaceFirst("_________",
									"×___" + stua1[st1] + "_");
						}
						System.out.print(tanString[j]);
						j++;
					} catch (Exception e) {
						tianString = tianString.replaceFirst("_________",
								"×________");
					}
				}
				reString = reString.concat(tianString).concat("<w:br/>");// <w:br/>
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reString;
	}

	/** 判断题 **/
	public String showju(String timuString) {
		String reString = "<w:p></w:p>";
		String T1[] = timuString.split("[#]");
		String stuA1[] = chaper.getJudgeSA().split(",");
		String stuA[] = new String[tinum];
		for (int i = 0; i < stuA.length; i++) {
			try {
				stuA[i] = stuA1[i];
			} catch (Exception e) {
				stuA[i] = "";
				// TODO: handle exception
			}
		}
		dao.setSQL("SELECT * FROM PointBiao WHERE StId= " + stid + " AND PaId="
				+ paid);
		rsResultSet = dao.Query();
		try {
			rsResultSet.next();
			String tanString[] = rsResultSet.getString("PoJuSumP").split(",");
			int tihao = 1;
			for (int i = 0; i < T1.length; i++) {
				// if (tanString[i].equals("1")) {
				// reString = reString.concat("<w:p></w:p>").concat("	√");
				// //
				// "<w:rPr><w:color w:val=\"FF0000\"/><w:sz w:val=\"88\"/></w:rPr><w:t>√</w:t><w:proofErr w:type=\"spellEnd\"/>");
				// } else {// <w:proofErr w:type=\"spellStart\"/>
				// reString = reString.concat("<w:p></w:p>").concat("	×");
				// }
				reString = reString.concat("" + tihao++ + " > ").concat(
						T1[i].replace("（  ）", "（ " + stuA[i] + " ）"));// <w:br/>
				try {
					if (tanString[i].equals("1")) {
						reString = reString.concat(yesString).concat(
								"<w:p></w:p>");
					} else {// <w:proofErr w:type=\"spellStart\"/>
						reString = reString.concat(noString).concat(
								"<w:p></w:p>");
					}
				} catch (Exception e) {
					// TODO: handle exception
					reString = reString.concat(noString).concat("<w:p></w:p>");
				}
				// reString=reString.concat(""+tihao++).concat(T1[i]).concat("<w:br/>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reString;
	}

	/** 名词解释题 **/
	public String showex(String timuString) {
		String reString = "<w:p></w:p>";
		String T1[] = timuString.split("[#]");
		String stuA[] = new String[tinum];
		String stuA1[] = new String[tinum];
		stuA1 = chaper.getExplainSA().split("#");
		for (int i = 0; i < stuA.length; i++) {
			try {
				stuA[i] = stuA1[i];
			} catch (Exception e) {
				stuA[i] = "";
				// TODO: handle exception
			}
		}
		dao.setSQL("SELECT * FROM PointBiao WHERE StId= " + stid + " AND PaId="
				+ paid);
		rsResultSet = dao.Query();
		try {
			rsResultSet.next();
			String tanString[] = rsResultSet.getString("PoExSumP").split(",");
			int tihao = 1;
			for (int i = 0; i < T1.length; i++) {
				// if (tanString[i].equals("1")) {
				// reString = reString.concat("<w:p></w:p>").concat("	√");
				// //
				// "<w:rPr><w:color w:val=\"FF0000\"/><w:sz w:val=\"88\"/></w:rPr><w:t>√</w:t><w:proofErr w:type=\"spellEnd\"/>");
				// } else {// <w:proofErr w:type=\"spellStart\"/>
				// reString = reString.concat("<w:p></w:p>").concat("	×");
				// }
				reString = reString.concat("" + tihao++ + " > ").concat(T1[i])
						.concat("<w:br/>").concat("		" + stuA[i]);// <w:br/>
				try {
					if (tanString[i].equals("1")) {
						reString = reString.concat(yesString).concat(
								"<w:p></w:p>");
					} else {
						reString = reString.concat(noString).concat(
								"<w:p></w:p>");
					}
				} catch (Exception e) {
					// TODO: handle exception
					reString = reString.concat(noString).concat("<w:p></w:p>");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reString;
	}

	/** 简答题 **/
	public String showsm(String timuString) {
		String reString = "<w:p></w:p>";
		String T1[] = timuString.split("[ミ#]");
		String stuA1[] = chaper.getSimpleSA().split("#");
		String stuA[] = new String[tinum];
		for (int i = 0; i < stuA.length; i++) {
			try {
				stuA[i] = stuA1[i];
			} catch (Exception e) {
				// TODO: handle exception
				stuA[i] = "";
			}
		}
		dao.setSQL("SELECT * FROM PointBiao WHERE StId= " + stid + " AND PaId="
				+ paid);
		rsResultSet = dao.Query();
		try {
			rsResultSet.next();
			String tanString[] = rsResultSet.getString("PoSmSumP").split(",");
			int tihao = 1;
			int tanstring = 0;
			for (int i = 0; i < T1.length; i++) {
				reString = reString.concat("<w:br/>").concat(
						"" + tihao++ + " > ").concat(T1[i]).concat("<w:br/>");// <w:br/>
				String stua1[] = stuA[i].split("ミ");
				for (int i1 = 0; i1 < stua1.length; i1++) {
					// if (tanString[tanstring].equals("1")) {
					// reString = reString.concat("<w:p></w:p>").concat("	√");
					// } else {// <w:proofErr w:type=\"spellStart\"/>
					// reString = reString.concat("<w:p></w:p>").concat("	×");
					// }
					tanstring++;
					try {
						reString = reString.concat("		" + stua1[i1]);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						if (tanString[tanstring].equals("1")) {
							reString = reString.concat(yesString).concat(
									"<w:p></w:p>");
						} else {// <w:proofErr w:type=\"spellStart\"/>
							reString = reString.concat(noString).concat(
									"<w:p></w:p>");
						}
					} catch (Exception e) {
						// TODO: handle exception
						reString = reString.concat(noString).concat(
								"<w:p></w:p>");
					}
				}
				// reString=reString.concat(""+tihao++).concat(T1[i]).concat("<w:br/>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reString;
	}
}
