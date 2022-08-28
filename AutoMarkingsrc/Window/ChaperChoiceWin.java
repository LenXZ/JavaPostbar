package Window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import DAO.DAO;

@SuppressWarnings("serial")
public class ChaperChoiceWin extends JFrame implements ActionListener,
		ItemListener {
	JButton sure;
	JPanel Panel, PNorth, PCenter;
	JTable table;
	DAO dao = new DAO();
	int item=1,s1;
	String s2;
	Object TiV[][] = new Object[5][3], TiH[] = { " ", "题号", "分值" }, TiO[] = {
			"选择题", "判断题", "填空题", "名词解释", "简答题" };
	ResultSet rs = null;

	public ChaperChoiceWin(int s1,String s2) {//教师ID 姓名
		setTitle("试卷选择");
		setBounds(0, 0, 1400, 800);
		setLayout(new BorderLayout());
		this.s1=s1;this.s2=s2;
		Panel = new JPanel();
		Panel.setLayout(new BorderLayout());
		PNorth = new JPanel();
		PNorth.setLayout(new BorderLayout());
		PCenter = new JPanel();
		PCenter.setLayout(new BorderLayout());
		sure = new JButton("确定");
		sure.addActionListener(this);
		dao.Con();
		dao.setSQL("SELECT * FROM PaperBiao");
		rs = dao.Query();
		String Ti = "";
		try {
			while (rs.next()) {
				Ti = Ti.concat(Integer.toString((int) rs.getLong("paId")))
						.concat("#");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String T1[] = Ti.split("#");
		item=Integer.parseInt(T1[0]);
		JComboBox comBox = new JComboBox();
		for (int i = 0; i < T1.length; i++) {
			comBox.addItem("" + T1[i]);
		}
		comBox.addItemListener(this);
		Box BoxN1 = Box.createHorizontalBox();
		BoxN1.add(new JLabel("请选择卷号："));
		BoxN1.add(comBox);
		BoxN1.add(sure);
		PNorth.add(BoxN1, BorderLayout.CENTER);
		for (int i = 0; i < 5; i++) {
			TiV[i][0] = TiO[i];
		}
		getTable(1);
		table = new JTable(TiV, TiH);
		PCenter.add(new JScrollPane(table));
		Panel.add(PNorth, BorderLayout.NORTH);
		Panel.add(PCenter, BorderLayout.CENTER);
		add(Panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void getTable(int id) {
		dao.setSQL("SELECT * FROM PaperBiao WHERE paId=" + id);
		rs = dao.Query();
		try {
			rs.next();
			TiV[0][1] = rs.getString("paChSumId");
			TiV[1][1] = rs.getString("paJuSumId");
			TiV[2][1] = rs.getString("paFiSumId");
			TiV[3][1] = rs.getString("paExSumId");
			TiV[4][1] = rs.getString("paSmSumId");
			TiV[0][2] = rs.getLong("paChNumfs");
			TiV[1][2] = rs.getLong("paJuNumfs");
			TiV[2][2] = rs.getLong("paFiNumfs");
			TiV[3][2] = rs.getLong("paExNumfs");
			TiV[4][2] = rs.getLong("paSmNumfs");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// AutoMarking automarking = new AutoMarking();
		// AddPoint addpoint = new AddPoint();
		new ChaperChoiceWin(1,"yuki");
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == sure) {
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.gc();
			this.dispose();
			new StudentChoiceWin(item,s1,s2);
		}
	}

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		item =Integer.parseInt(e.getItem().toString());
		if (e.getStateChange() == ItemEvent.SELECTED) {
			getTable(item);
//			table.repaint();
//			Panel.updateUI();
		}
	}
}
