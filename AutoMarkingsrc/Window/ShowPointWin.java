package Window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import freemarker.template.TemplateException;
import Marking.ChaperChoice;
import Marking.ShowChaper;
import DAO.DAO;
import DAO.JuanziTogether;

@SuppressWarnings("serial")
public class ShowPointWin extends JFrame implements ActionListener {
	DAO dao = new DAO();
	JButton sure, exit;
	JPanel panel;
	JTable table;
	int[] stid = new int[500];
	int paid;
	Object taObjectH[] = { "学生编号", "学生姓名", "选择题", "判断题", "填空题", "名词解释", "简答题",
			"总分" };
	Object taObject[][];
	String timu[] = { "StId", "", "PoChAll", "PoJuAll", "PoFiAll", "PoExAll",
			"PoSmAll", "PoAllSum" };
	String stuna[] = new String[500];
	ResultSet rsResultSet;

	public ShowPointWin(int[] stId, int paid, String stuna[]) {
		// TODO Auto-generated constructor stub
		setTitle("学生分数");
		setBounds(0, 0, 1400, 800);
		setLayout(new BorderLayout());
		this.paid = paid;
		this.stid = stId;
		this.stuna = stuna;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		sure = new JButton("输出到Word文档");
		sure.addActionListener(this);
		exit = new JButton("退出");
		exit.addActionListener(this);
		int init = 10;
		for (int i = 0; i < stId.length; i++) {
			if (stId[i] == 0) {
				init = i;
				break;
			}
		}
		taObject = new Object[init][8];
		for (int i = 0; i < init; i++) {
			dao.setSQL("SELECT * FROM PointBiao WHERE StId= " + stId[i]);
			rsResultSet = dao.Query();
			try {
				rsResultSet.next();
				for (int j = 0; j < 8; j++) {
					if (j == 1) {
						continue;
					}
					taObject[i][j] = (int) rsResultSet.getLong(timu[j]);
				}
				dao.setSQL("SELECT * FROM studentBiao WHERE StId=" + stId[i]);
				rsResultSet = dao.Query();
				rsResultSet.next();
				taObject[i][1] = rsResultSet.getString("stName");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		table = new JTable(taObject, taObjectH);
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		Box box = Box.createHorizontalBox();
		box.add(exit);
		box.add(sure);
		panel.add(box, BorderLayout.SOUTH);
		add(panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 学生ID 卷ID 学生姓名 学年 其中期末 课程 院别 AB卷
	 * 
	 * @return
	 **/
	public void ShowChaperWin(int[] stid, int paid, String stuna[],String xuenian,String qizm, String kecheng,
			String yuanbie, String juan, String xzf, String xmf, String tzf,
			String tmf, String pzf, String pmf, String mzf, String mmf,
			String jzf, String jmf) {
		DAO dao = new DAO();
		JuanziTogether juanzi = new JuanziTogether();
		ResultSet rsResultSet;
		int init = 10;
		for (int i = 0; i < stid.length; i++) {
			if (stid[i] == 0) {
				init = i;
				break;
			}
		}
		juanzi.setBiaotou(xuenian,qizm, kecheng, yuanbie, juan, xzf, xmf, tzf, tmf,
				pzf, pmf, mzf, mmf, jzf, jmf);
		ChaperChoice chaperChoice=new ChaperChoice(paid, 0);
		String chString=chaperChoice.getChoiceP();
		String juString=chaperChoice.getJudgeP();
		String fiString=chaperChoice.getFillblankP();
		String exString=chaperChoice.getExplainP();
		String smString=chaperChoice.getSimpleP();
//		JProgressBar progressBar=new JProgressBar();
//		progressBar.setStringPainted(true);
	//	JOptionPane.showMessageDialog(this, progressBar, "提示", JOptionPane.INFORMATION_MESSAGE);
//		getContentPane().add(progressBar,BorderLayout.NORTH);
//		panel.add(progressBar,BorderLayout.NORTH);
//		panel.updateUI();
		for (int i = 0; i < init; i++) {
			dao.setSQL("SELECT * FROM PointBiao WHERE StId= " + stid[i]
					+ " AND PaId=" + paid);
			rsResultSet = dao.Query();
			try {
				rsResultSet.next();
				ShowChaper showchaper=new ShowChaper(paid,stid[i]);
				juanzi.setXuezeneirong(showchaper.showchoice(chString));
				juanzi.setPanduanneirong(showchaper.showju(juString));
				juanzi.setTiankongneirong(showchaper.showfi(fiString));
				juanzi.setMingcineirong(showchaper.showex(exString));
				juanzi.setJiandaneirong(showchaper.showsm(smString));
				juanzi.setStudent(stuna[i], Integer.toString(stid[i]));
				juanzi.setteacher(Integer.toString((int) rsResultSet
						.getLong("PoChAll")), "系统", Integer
						.toString((int) rsResultSet.getLong("PoFiAll")),
						rsResultSet.getString("PoFitc"),
						Integer.toString((int) rsResultSet.getLong("PoJuAll")),
						"系统", Integer.toString((int) rsResultSet
								.getLong("PoExAll")), rsResultSet
								.getString("PoExtc"),
						Integer.toString((int) rsResultSet.getLong("PoSmAll")),		
						rsResultSet.getString("PoSmtc"));
//				progressBar.setValue(i);
				try {
					juanzi.CreatPingJuan();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TemplateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(this, "输出完成！", "提示", JOptionPane.INFORMATION_MESSAGE);
	}
	public void setBiaotou(int[] stid,int paid,String stuna[]) {
		String xuenian, kecheng, yuanbie, qizm,juan, xzf, xmf, tzf, tmf, pzf, pmf, mzf,
		mmf, jzf, jmf;
		ResultSet rs;
		// TODO Auto-generated constructor stub
		dao.setSQL("SELECT * FROM paperBiao WHERE paId= "+paid);
		rs=dao.Query();
		try {
			rs.next();
			xuenian=rs.getString("pasemester");
			kecheng=rs.getString("paname");
			yuanbie=rs.getString("pagrade");
			qizm=rs.getString("paQizQim");
			juan=rs.getString("paAB");
			xzf=rs.getString("paChNumzfs");
			xmf=rs.getString("paChNumfs");
			tzf=rs.getString("paFiNumzfs");
			tmf=rs.getString("paFiNumfs");
			pzf=rs.getString("paJuNumzfs");
			pmf=rs.getString("paJuNumfs");
			mzf=rs.getString("paExNumzfs");
			mmf=rs.getString("paExNumfs");
			jzf=rs.getString("paSmNumzfs");
			jmf=rs.getString("paSmNumfs");
			ShowChaperWin(stid, paid,stuna,xuenian,qizm, kecheng, yuanbie, juan, xzf, xmf, tzf, tmf, pzf, pmf, mzf, mmf, jzf, jmf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == sure) {
			setBiaotou(stid, paid, stuna);
		} else if (e.getSource() == exit) {
			int n = JOptionPane.showConfirmDialog(this, "是否退出系统？", "",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				// con.close();
				this.dispose();
				System.gc();
				System.exit(0);
			} else if (n == JOptionPane.NO_OPTION) {
			}
		}

	}
}
