package Window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Window.TeacherMarkingWin;

import DAO.DAO;
import Marking.ChaperChoice;

@SuppressWarnings("serial")
public class StudentChoiceWin extends JFrame implements ActionListener,ItemListener{
	DAO dao = new DAO();
	int stId[]=new int[500],stid[]=new int[500];
	int stNum,paId,s1,tixing=0;
	JButton sure,all,fan,allclear;
	JCheckBox jc[]=new JCheckBox[1000];
	JPanel Panel, PNorth, PCenter;	
	Object tixingObject[]={"填空题","名词解释","简答题"};
	ResultSet rs = null;
	String s2,stuna[]=new String[500] ;
	public StudentChoiceWin(int paId,int s1,String s2) {
		this.paId=paId;this.s1=s1;this.s2=s2;
		setTitle("学生选择");
		setBounds(0, 0, 1400, 800);
		setLayout(new BorderLayout());
		Panel = new JPanel();
		Panel.setLayout(new BorderLayout());
		PNorth = new JPanel();
		PNorth.setLayout(new BorderLayout());
		PCenter = new JPanel();
		PCenter.setLayout(new BorderLayout());
		sure = new JButton("确定");
		all = new JButton("全选");
		fan=new JButton("反选");
		allclear=new JButton("全部取消");
		sure.addActionListener(this);
		all.addActionListener(this);
		fan.addActionListener(this);
		allclear.addActionListener(this);
		dao.setSQL("SELECT * FROM AnswerBiao WHERE PaId="+ paId);
		rs = dao.Query();
		String Ti = "";
		try {
			while (rs.next()) {
				Ti = Ti.concat(Integer.toString((int) rs.getLong("stId")))
						.concat("#");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String T1[] = Ti.split("#");
		Box boxV11 = Box.createVerticalBox();
		stNum=T1.length;
		for(int i=0;i<T1.length;i++){
			stId[i]=Integer.parseInt(T1[i]);
			dao.setSQL("SELECT * FROM StudentBiao WHERE stId="+ stId[i]);
			rs = dao.Query();
			try {
				rs.next();
				stuna[i]=rs.getString("stName");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jc[i]=new JCheckBox(" 学生  "+T1[i]+"号  "+stuna[i]);
			jc[i].setSelected(true);
			boxV11.add(jc[i]);
			jc[i].addItemListener(this);			
		}
		Box boxH1=Box.createHorizontalBox();
		boxH1.add(all);
		boxH1.add(fan);
		boxH1.add(allclear);
		boxH1.add(sure);
		JComboBox comBox = new JComboBox();
		comBox.addItem(tixingObject[0]);
		comBox.addItem(tixingObject[1]);
		comBox.addItem(tixingObject[2]);
		comBox.addItemListener(this);
		PCenter.add(comBox,BorderLayout.NORTH);
		PCenter.add(new JScrollPane(boxV11), BorderLayout.CENTER);
		PCenter.add(boxH1, BorderLayout.SOUTH);
		Panel.add(PNorth, BorderLayout.NORTH);
		Panel.add(PCenter, BorderLayout.CENTER);
		add(Panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		if(e.getSource()==sure){
			boolean next=false;
			for(int i=0,j=0;i<stNum;i++){
				if(jc[i].isSelected()){
					next=true;
					stid[j]=stId[i];
					j++;
				}
			}
			System.gc();
			this.dispose();
			if(next){
				ChaperChoice chaperchoice = new ChaperChoice(this.paId, stid[0]);
				new TeacherMarkingWin(chaperchoice.getFillblankA(), chaperchoice
						.getFillblankSA(), chaperchoice.getExplainA(), chaperchoice
						.getExplainSA(), chaperchoice.getSimpleA(), chaperchoice
						.getSimpleSA(), chaperchoice.NumFillblank(), chaperchoice
						.NumExplain(), chaperchoice.NumSimple(),stid,stuna,0,paId,s1,s2,tixing);				
			}
		}else if (e.getSource()==all) {
			for(int i=0;i<stNum;i++){
				jc[i].setSelected(true);
			}
		}else if (e.getSource()==allclear) {
			for(int i=0;i<stNum;i++){
				jc[i].setSelected(false);
			}
		}else if (e.getSource()==fan) {
			for(int i=0;i<stNum;i++){
				if(jc[i].isSelected()){
					jc[i].setSelected(false);					
				}else{
					jc[i].setSelected(true);
				}
			}
		}
		
	}
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		String tiString=e.getItem().toString();
		if (e.getStateChange() == ItemEvent.SELECTED) {
			for(int i=0;i<3;i++){
				if(tiString.equals(tixingObject[i])){
					tixing=i;
				}				
			}
		}
	}

}
