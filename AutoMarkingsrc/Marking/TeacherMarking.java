package Marking;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.DAO;
import MyListener.MarkingListener;

public class TeacherMarking {

	MarkingListener checklistener=new MarkingListener();
	DAO dao = new DAO();
	int paper, stId,s2,s3;
	ResultSet rs = null;
	String Explain, Fillblank, Simple;// 解释|填空|简答
	String s1;
	/** 试卷选择  */
	public void ChaperChoice(int s, int s2) {
		paper = s;
		stId = s2;
		dao.setSQL("SELECT * FROM PaperBiao WHERE paId = " + s );
		rs = dao.Query();
		try {
			while (rs.next()) {
				Fillblank = rs.getString("paFiSumId");
				Explain = rs.getString("paExSumId");
				Simple = rs.getString("paSmSumId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**插入新条目**//**需改进-----------------------------------------------需改进-------------------需改进-----------------------需改进------------------*/
	public void insertstP(int s1,int s2){//卷ID 学生ID
		boolean chongfu=false;
		dao.setSQL("SELECT * FROM PointBiao WHERE PaId = " + s1 + "");
		rs = dao.Query();
		int PoId=0,poid=0;
		try {
			while(rs.next()){
				if(rs.getLong("StId")==s2){
					chongfu=true;
				}
			}
//			if(rs.getLong("PoId")!=0){
//				PoId=Integer.parseInt(rs.getString("PoId"))+1;	
//				dao.setSQL("INSERT INTO PointBiao VALUES ('"+Integer.toString(PoId)+"','"+s2+"','"+s1+"',"+"'0','0','0','0','0','0')");
//				dao.Insert();			
//			}
			if(chongfu){
				
			}else{
				dao.setSQL("SELECT * FROM PointBiao ");
				rs = dao.Query();
				while(rs.next()){
					poid=(int) (rs.getLong("PoId")+1);
					if(poid>PoId){
						PoId=poid;
					}
				}
				dao.setSQL("INSERT INTO PointBiao VALUES ("+PoId+","+s2+","+s1+","+"'0','0','0','0','0','0','0','0','0','0','0','0','0','0')");
				dao.Insert();				
			}
		} catch (SQLException e) {
			dao.setSQL("SELECT * FROM PointBiao ");
			rs = dao.Query();
			try{
				while(rs.next()){
					PoId=(int) (rs.getLong("PoId")+1);	
				}
				dao.setSQL("INSERT INTO PointBiao VALUES ("+PoId+","+s2+","+s1+","+"'0','0','0','0','0','0','0','0','0','0','0','0','0','0')");
				dao.Insert();				
			}catch(Exception e1){
				
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setID(String s1,int s2,int s3){//题型 学生ID
		this.s1=s1;
		this.s2=s2;
		this.s3=s3;
	}
	/**填空题目显示*/
	public void FiChaperWin(JPanel pane[]){
		String Ti[] = Fillblank.split(",");
		checklistener.reTimuNum();
		for (int i = 0; i < Ti.length; i++) {
			dao.setSQL("SELECT * FROM FillblankBiao WHERE fbId=" + Ti[i] + "");
			rs = dao.Query();
			pane[i].setLayout(new BorderLayout());
			try {
				rs.next();
				pane[i].add(new JLabel(""+rs.getString("fbTopic")),BorderLayout.NORTH);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**解释题目显示*/
	public void ExChaperWin(JPanel pane[]){
		String Ti[] = Explain.split(",");
		checklistener.reTimuNum();
		for (int i = 0; i < Ti.length; i++) {
			dao.setSQL("SELECT * FROM ExplainBiao WHERE exId=" + Ti[i] + "");
			rs = dao.Query();
			pane[i].setLayout(new BorderLayout());
			try {
				rs.next();
				pane[i].add(new JLabel(""+rs.getString("ExTopic")),BorderLayout.NORTH);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**简答题目显示*/
	public void SiChaperWin(JPanel pane[]){
		String Ti[] = Simple.split(",");
		checklistener.reTimuNum();
		for (int i = 0; i < Ti.length; i++) {
			dao.setSQL("SELECT * FROM SimpleBiao WHERE smId=" + Ti[i] + "");
			rs = dao.Query();
			pane[i].setLayout(new BorderLayout());
			try {
				rs.next();
				pane[i].add(new JLabel(""+rs.getString("smTopic")),BorderLayout.NORTH);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**简答题答案显示*/
	public String Answer(String s1,JPanel pane[],JPanel pane2[]){
		String re="";
		String T1[] = s1.split("#");
		checklistener.reTimuNum();
		for (int i = 0; i < T1.length ; i++) {
			String T11[] = T1[i].split("[(（]");
			Box boxV11 = Box.createVerticalBox();
			checklistener.setTimuNum(T11.length);
			for (int i1 = 0; i1 < T11.length; i1++) {
				boxV11.add(new JLabel(""+T11[i1]));
			}
			pane[i].setLayout(new GridLayout(1,2));
			pane2[i].add(boxV11,BorderLayout.CENTER);
			pane[i].add(pane2[i]);
		}
		return re;
	}
	/** 填空题获取标准答案 */
	public String FiAnswer(String s1,JPanel pane[],JPanel pane2[]) {
		String re = "";
		String T1[] = s1.split("#");
		checklistener.reTimuNum();
		for (int i = 0; i < T1.length ; i++) {
			String T11[] = T1[i].split("[,，]");
			Box boxV11 = Box.createVerticalBox();
			checklistener.setTimuNum(T11.length);
			for (int i1 = 0; i1 < T11.length; i1++) {
				boxV11.add(new JLabel(""+T11[i1]));
			}
			pane[i].setLayout(new GridLayout(1,2));
			pane2[i].add(boxV11,BorderLayout.CENTER);
			pane[i].add(pane2[i]);
		}
		return re;
	}
	/** 简答题学生答案显示 */
	public void StAnswer(String s1, JPanel pane[]) {
		JCheckBox jc[]=new JCheckBox[1000];
		int Timu=0;
		String T1[] = s1.split("#");
		checklistener.setID(this.s1, this.s2,this.s3);
		for (int i = 0; i < T1.length; i++) {
			String T11[] = T1[i].split("ミ");
			Box boxV11 = Box.createVerticalBox();
			for (int i1 = 0; i1 < T11.length; i1++) {
				jc[Timu]=new JCheckBox(""+T11[i1]);
				boxV11.add(jc[Timu]);
				checklistener.setJCheckBox(jc);
				jc[Timu].addItemListener(checklistener);
				Timu++;
			}
//			pane[i].setLayout(new BorderLayout());
			pane[i].add(boxV11);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
