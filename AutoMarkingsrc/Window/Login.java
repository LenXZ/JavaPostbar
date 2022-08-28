package Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import DAO.DAO;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {
	JTextField text1;
	JPasswordField text2;
	JButton log, back;
	Box baseBox, BBox, boxV1, boxV2, boxV3, boxV4;
	JPanel jp;
	Connection con = null;
	Statement sql;
	ResultSet rs = null;
	boolean dc = true;
	JTextArea area;
	DAO dao = new DAO();

	public Login(String s) {
		setTitle(s);
		setBounds(300, 300, 400, 300);
		setContentPane(new MyPanel("image/BB.jpg"));
		area = new JTextArea(3, 8);
		text1 = new JTextField(10);
		text2 = new JPasswordField(10);
		log = new JButton("登入");
		back = new JButton("退出");
		text1.addActionListener(this);
		text2.addActionListener(this);
		log.addActionListener(this);
		back.addActionListener(this);

		setLayout(new java.awt.FlowLayout());
		boxV1 = Box.createVerticalBox();
		boxV1.add(new JLabel("用户ID："));
		boxV1.add(Box.createVerticalStrut(12));
		boxV1.add(new JLabel("密码："));
		boxV1.add(Box.createVerticalStrut(12));

		boxV2 = Box.createVerticalBox();
		boxV2.add(text1);
		boxV2.add(Box.createVerticalStrut(4));
		boxV2.add(text2);
		boxV2.add(Box.createVerticalStrut(4));

		boxV3 = Box.createHorizontalBox();
		boxV3.add(log);
		boxV3.add(Box.createHorizontalStrut(30));
		boxV3.add(back);

		baseBox = Box.createHorizontalBox();
		baseBox.add(boxV1);
		baseBox.add(Box.createHorizontalStrut(24));
		baseBox.add(boxV2);

		boxV4 = Box.createHorizontalBox();
		boxV4.add(area);
		BBox = Box.createVerticalBox();
		BBox.add(Box.createVerticalStrut(12));
		BBox.add(baseBox);
		BBox.add(Box.createVerticalStrut(4));
		BBox.add(boxV3);
		BBox.add(Box.createVerticalStrut(12));
		BBox.add(boxV4);

		add(BBox);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		area.append("欢迎使用!");
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == log||e.getSource()==text1||e.getSource()==text2) {
				try {
					dao.Con();
					dao.setTableName("kaoshixitong");
					dao.setSQL("SELECT * FROM AdminBiao ");
					rs = dao.Query();
					int s1;
					String s2, s3;
					while (rs.next()) {
						s1 = (int) rs.getLong(1);
						s2 = rs.getString(2);
						s3 = rs.getString(3);
						if (s1==Integer.parseInt(text1.getText())
								&& s3.equals(text2.getText())) {
							dc = false;
							this.dispose();
							new ChaperChoiceWin(s1,s2);
							break;
						}
					}
					if (dc != false) {
						text1.setText(null);
						text2.setText(null);
						area.setText("");
						area.append("用户名或密码错误！\n请重新输入");

					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == back) {
				int n = JOptionPane.showConfirmDialog(this, "是否退出？", "",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					// con.close();
					System.exit(0);
				} else if (n == JOptionPane.NO_OPTION) {
				}
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Login("登入界面");
	}
}
