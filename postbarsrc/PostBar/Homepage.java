package PostBar;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.UIManager;
import dao.BookDAO;
import PostBar.JBUTTON;

@SuppressWarnings("serial")
public class Homepage extends JFrame implements ActionListener, MouseListener,
		ItemListener {
	boolean publish = true;
	int bottm, top, ToolTip, initPostnum = 0, judgeOpenlist = 0,
			maximum = 10000, idBlock, Items, Items01, userid = 170327,
			maxid = 1000000, typechange, maximum2 = 200,
			buttonchoice[] = new int[maximum2];
	String Editfor, time, userID[] = new String[maxid];
	String sqlTopic = "SELECT idTopic,Title,Createtime,user_id,Type,Scan,Reply FROM topic WHERE BlockID ='";
	String sqlTopicTitle = "SELECT Title FROM topic WHERE idTopic ='";
	String sqlTopicFind = "SELECT idTopic,Title,Createtime,user_id,Type,Scan,Reply FROM topic WHERE Title LIKE '%";
	String sqlTopicScan = "UPDATE topic SET Scan=Scan+1 WHERE idTopic ='";
	String sqlTopicReply = "UPDATE topic SET Reply=Reply+1 WHERE idTopic ='";
	String sqlTopicbest = "SELECT MAX(idTopic) FROM topic ";
	String sqlTopicAdd = "INSERT INTO topic(idTopic,Title,Createtime,user_id,BlockID,Type,Scan,Reply) VALUES ";
	String sqlNote = "SELECT note.idNote,note.Content,note.Createtime,note.Rank,note.user_id,t_user.user_name,t_user.user_price FROM note,t_user WHERE t_user.user_id=note.user_id AND idTopic ='";
	String sqlNoteReply = "SELECT idTopic FROM note WHERE idNote='";
	String sqlNotebest0 = "SELECT MAX(idNote) FROM note ";
	String sqlNotebest1 = "SELECT idNote,Rank FROM note WHERE idNote=(SELECT MAX(idNote)) AND idTopic ='";
	String sqlNoteAdd = "INSERT INTO note(idNote,Content,Createtime,Rank,idTopic,user_id) VALUES ";
	String sqlReply = "SELECT reply.idReply,reply.Content,reply.Createtime,t_user.user_name FROM t_user,reply WHERE t_user.user_id=reply.user_id AND idNote ='";
	String sqlReplybest0 = "SELECT MAX(Rank) FROM reply WHERE idNote='";
	String sqlReplybest1 = "SELECT idReply,Rank FROM reply WHERE idReply=(SELECT MAX(idReply)) AND idNote ='";
	String sqlReplybest3 = "SELECT Rank FROM reply WHERE idNote='";
	String sqlReplyAdd = "INSERT INTO reply(idReply,Content,Createtime,Rank,idNote,user_id) VALUES ";
	String sqlTopic00 = "SELECT idTopic,Title,Createtime,user_id,Type,Scan,Reply FROM topic WHERE ",
			sqlTopicType = "", sqlTopicTime = "", sqlTopicReorder = "",warning="";
	private JTextField findfield;
	private JBUTTON find, Button[] = new JBUTTON[maximum], close, closenew,
			newposts, published, reply[][] = new JBUTTON[3][maximum],
			Posting[] = new JBUTTON[maximum],
			comment[][] = new JBUTTON[maximum2][maximum],
			cancelreply[] = new JBUTTON[maximum],
			// letter[] = new JBUTTON[maxid],
			Openlist[][] = new JBUTTON[maximum2][maximum];
	private JButton portrait[] = new JButton[maxid];
	// Barreply[][]=new JBUTTON[maximum2][maximum];
	private JComboBox Listorder[] = new JComboBox[maximum2];
	private JTree tree;
	private JLabel PostBarListtitle[][] = new JLabel[maximum2][maximum];
	JTextField title[] = new JTextField[maximum2];
	private Box box1V1, box1H12, box1V11, postboxH2[] = new Box[maximum2];
	private JTabbedPane textTabbedPane, TabbedPane[] = new JTabbedPane[3];
	private JPanel PANE, pane1, pane11, pane111, pane2, pane3, pane4,
			postpane[] = new JPanel[maximum2],
			postpane01[] = new JPanel[maximum2], NewPost,
			PostBarListCEN[] = new JPanel[maximum2],
			postpaneinit[][] = new JPanel[maximum2][maximum],
			Postreplytpane[] = new JPanel[maximum2],
			PostBarListpane[] = new JPanel[maximum2],
			PostBarList[] = new JPanel[maximum2], postpaneinit01,
			postpaneinit02, BarComment[] = new JPanel[maximum];
	private Font Font1, Font3, Font4;
	BookDAO table = new BookDAO();
	Edit edit = new Edit();
	Edit edit01 = new Edit();
	Edit edit02 = new Edit();
	ResultSet rs;
	File musicWarning=new File("audio/Warning.wav");
	File musicPrompt=new File("audio/Prompt.wav");
	File musicClicked=new File("audio/Clicked.wav");
	URI uriClicked=musicClicked.toURI();
	URI uriWarning=musicWarning.toURI();
	URI uriPrompt=musicPrompt.toURI();
	URL urlWarning,urlPrompt,urlClicked;
	AudioClip clip;
	
	public Homepage(String s) {
		setTitle(s);
		init();
	}

	private void init() {
		Font1 = new Font("微软雅黑", Font.PLAIN, 12);// 字体
		// new Font("微软雅黑",Font.PLAIN,16);
		Font3 = new Font("微软雅黑", Font.PLAIN, 18);
		Font4 = new Font("微软雅黑", Font.PLAIN, 10);
		UIManager.put("Button.font", new java.awt.Font("微软雅黑", Font.PLAIN, 12)); // 默认字体{按钮
		// 标签
		// Tabbed页面
		// 下拉列表}
		UIManager.put("Label.font", new java.awt.Font("微软雅黑", Font.PLAIN, 12));
		UIManager.put("TabbedPane.font", new java.awt.Font("微软雅黑", Font.PLAIN,
				12));
		UIManager.put("ComboBox.font",
				new java.awt.Font("微软雅黑", Font.PLAIN, 12));
		setBounds(200, 100, 1000, 618);
		PANE = new JPanel();
		pane1 = new JPanel();
		pane2 = new JPanel();
		pane3 = new JPanel();
		pane4 = new JPanel();
		PANE.setBounds(200, 100, 1000, 322);
		PANE.setLayout(new BorderLayout(5, 12));
		findfield = new JTextField(10); // 查找文本框
		find = new JBUTTON("查找"); // 查找按钮
		find.setFont(Font1);
		find.setForeground(Color.ORANGE);
		find.setBackground(new Color(100, 100, 150));
		findfield.addActionListener(this);
		find.addActionListener(this);
		// 树
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(
				"      论坛                "); // 根节点
		DefaultMutableTreeNode nodeA = new DefaultMutableTreeNode("书友互动"); // 节点
		DefaultMutableTreeNode nodeB = new DefaultMutableTreeNode("图书交流");
		DefaultMutableTreeNode nodeC = new DefaultMutableTreeNode("共享欣赏");
		DefaultMutableTreeNode nodeD = new DefaultMutableTreeNode("书友交易");
		DefaultMutableTreeNode A1 = // 子节点
		new DefaultMutableTreeNode("书友交流");
		DefaultMutableTreeNode A2 = new DefaultMutableTreeNode("书友推荐");
		DefaultMutableTreeNode A3 = new DefaultMutableTreeNode("读后感");
		DefaultMutableTreeNode A4 = new DefaultMutableTreeNode("电子书");
		DefaultMutableTreeNode A5 = new DefaultMutableTreeNode("写作原创");
		DefaultMutableTreeNode A6 = new DefaultMutableTreeNode("意见反馈");
		DefaultMutableTreeNode B1 = new DefaultMutableTreeNode("童书交流");
		DefaultMutableTreeNode B2 = new DefaultMutableTreeNode("小说交流");
		DefaultMutableTreeNode B3 = new DefaultMutableTreeNode("文学书籍交流");
		DefaultMutableTreeNode B4 = new DefaultMutableTreeNode("历史传记交流");
		DefaultMutableTreeNode B5 = new DefaultMutableTreeNode("心理学交流");
		DefaultMutableTreeNode B6 = new DefaultMutableTreeNode("励志治愈交流");
		DefaultMutableTreeNode C1 = new DefaultMutableTreeNode("情感日志");
		DefaultMutableTreeNode C2 = new DefaultMutableTreeNode("美文欣赏");
		DefaultMutableTreeNode C3 = new DefaultMutableTreeNode("作文大全");
		DefaultMutableTreeNode C4 = new DefaultMutableTreeNode("美图欣赏");
		DefaultMutableTreeNode C5 = new DefaultMutableTreeNode("杂粹杂谈");
		DefaultMutableTreeNode C6 = new DefaultMutableTreeNode("经典语录");
		root.add(nodeA);
		root.add(nodeB);
		root.add(nodeC);
		root.add(nodeD);
		nodeA.add(A1);
		nodeA.add(A2);
		nodeA.add(A3);
		nodeA.add(A4);
		nodeA.add(A5);
		nodeA.add(A6);
		nodeB.add(B1);
		nodeB.add(B2);
		nodeB.add(B3);
		nodeB.add(B4);
		nodeB.add(B5);
		nodeB.add(B6);
		nodeC.add(C1);
		nodeC.add(C2);
		nodeC.add(C3);
		nodeC.add(C4);
		nodeC.add(C5);
		nodeC.add(C6);
		tree = new JTree(root);
		tree.setFont(Font1);// 树字体
		// tree.setForeground(Color.red);
		// tree.setBackground(Color.red);
		// pane1布局
		pane1.setLayout(new BorderLayout());
		pane1.setFont(Font1);
		// pane11布局
		pane11 = new JPanel(); // pane1的CENTER布局
		pane11.setLayout(new BorderLayout());
		pane11.setBackground(new Color(100, 100, 170));
		pane111 = new JPanel(); // 推荐帖子列表
		pane111.setLayout(new BorderLayout());
		// pane111.setLayout(new BoxLayout(pane111, BoxLayout.Y_AXIS));
		Box pane111box = Box.createVerticalBox(); // 垂直盒子{帖子目录}
		for (int i = 0; i < 23; i++) {
			// PostBarListForm(2,i,12);
			// pane111.add(PostBarList[2]); //添加帖子

		}
		table.setTableName("topic");
		sqlTopicType = " Type='推荐' AND ";
		sqlTopicTime = " DATE_SUB(CURDATE(), INTERVAL 7 DAY) <=date(Createtime) AND ";
		for (int i = 11; i <= 16; i++) {
			int i0 = i;
			for (; i0 < maximum2; i0 = i0 + 30) {
				if (buttonchoice[i0] == 0) {
					buttonchoice[i0] = 1;
					break;
				}
			}
			sqlTopic = sqlTopic00.concat(sqlTopicType).concat(sqlTopicTime)
					.concat(" BlockID =").concat(String.valueOf(i)).concat(
							sqlTopicReorder);
			table.setSQL(sqlTopic);
			rs = table.Query();
			try {
				int i1 = 0;
				while (rs.next()) {
					PostBarListForm(i0, i1, rs.getInt(1), rs.getString(2), rs
							.getDate(3), rs.getString(4), rs.getString(5), rs
							.getLong(6), rs.getLong(7));
					pane111box.add(PostBarList[i0]);
					if (i1 >= 20) {
						break;
					}
					i1++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				warning="无该类结果！";
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (int i = 21; i <= 26; i++) {
			int i0 = i;
			for (; i0 < maximum2; i0 = i0 + 30) {
				if (buttonchoice[i0] == 0) {
					buttonchoice[i0] = 1;
					break;
				}
			}
			sqlTopic = sqlTopic00.concat(sqlTopicType).concat(sqlTopicTime)
					.concat(" BlockID =").concat(String.valueOf(i)).concat(
							sqlTopicReorder);
			table.setSQL(sqlTopic);
			rs = table.Query();
			try {
				int i1 = 0;
				while (rs.next()) {
					PostBarListForm(i0, i1, rs.getInt(1), rs.getString(2), rs
							.getDate(3), rs.getString(4), rs.getString(5), rs
							.getLong(6), rs.getLong(7));
					pane111box.add(PostBarList[i0]);
					if (i1 >= 20) {
						break;
					}
					i1++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				warning="无该类结果！";
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (int i = 31; i <= 36; i++) {
			int i0 = i;
			for (; i0 < maximum2; i0 = i0 + 30) {
				if (buttonchoice[i0] == 0) {
					buttonchoice[i0] = 1;
					break;
				}
			}
			sqlTopic = sqlTopic00.concat(sqlTopicType).concat(sqlTopicTime)
					.concat(" BlockID =").concat(String.valueOf(i)).concat(
							sqlTopicReorder);
			table.setSQL(sqlTopic);
			rs = table.Query();
			try {
				int i1 = 0;
				while (rs.next()) {
					PostBarListForm(i0, i1, rs.getInt(1), rs.getString(2), rs
							.getDate(3), rs.getString(4), rs.getString(5), rs
							.getLong(6), rs.getLong(7));
					pane111box.add(PostBarList[i0]);
					if (i1 >= 20) {
						break;
					}
					i1++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				warning="无该类结果！";
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		pane111.add(pane111box, BorderLayout.NORTH);
		pane111.setBackground(new Color(100, 100, 200));
		for (int i = 0; i < 3; i++) {
			TabbedPane[i] = new JTabbedPane(); // 最外层面板底层标签
			TabbedPane[i].setForeground(Color.white);
			TabbedPane[i].setBackground(new Color(100, 100, 200));
			TabbedPane[i].setTabPlacement(JTabbedPane.TOP);
		}
		// pane2布局 书友互动页
		TabbedPane[0].addTab("书友互动", pane2);
		pane2.setLayout(new GridLayout(2, 3));
		// JBUTTON pbutton1=new JBUTTON("书友交流",new
		// ImageIcon("Picture/pane1p.jpg"));
		Button[11] = (new JBUTTON(new ImageIcon("Picture/Button-11.gif")));
		Button[12] = (new JBUTTON(new ImageIcon("Picture/Button-12.gif")));
		Button[13] = (new JBUTTON(new ImageIcon("Picture/Button-13.gif")));
		Button[14] = (new JBUTTON(new ImageIcon("Picture/Button-14.gif")));
		Button[15] = (new JBUTTON(new ImageIcon("Picture/Button-15.gif")));
		Button[16] = (new JBUTTON(new ImageIcon("Picture/Button-16.gif")));
		for (int i = 11; i <= 16; i++) {
			pane2.add(Button[i]);
			Button[i].addActionListener(this);
		}
		// pane3布局 图书交流页
		TabbedPane[1].addTab("图书交流", pane3);
		pane3.setLayout(new GridLayout(2, 3));
		Button[21] = (new JBUTTON(new ImageIcon("Picture/Button-21.gif")));
		Button[22] = (new JBUTTON(new ImageIcon("Picture/Button-22.gif")));
		Button[23] = (new JBUTTON(new ImageIcon("Picture/Button-23.gif")));
		Button[24] = (new JBUTTON(new ImageIcon("Picture/Button-24.gif")));
		Button[25] = (new JBUTTON(new ImageIcon("Picture/Button-25.gif")));
		Button[26] = (new JBUTTON(new ImageIcon("Picture/Button-26.gif")));
		for (int i = 21; i <= 26; i++) {
			pane3.add(Button[i]);
			Button[i].addActionListener(this);
		}
		// pane4布局 共同欣赏页
		TabbedPane[2].addTab("共同欣赏", pane4);
		pane4.setLayout(new GridLayout(2, 3));
		Button[31] = (new JBUTTON(new ImageIcon("Picture/Button-31.gif")));
		Button[32] = (new JBUTTON(new ImageIcon("Picture/Button-32.gif")));
		Button[33] = (new JBUTTON(new ImageIcon("Picture/Button-33.gif")));
		Button[34] = (new JBUTTON(new ImageIcon("Picture/Button-34.gif")));
		Button[35] = (new JBUTTON(new ImageIcon("Picture/Button-35.gif")));
		Button[36] = (new JBUTTON(new ImageIcon("Picture/Button-36.gif")));
		for (int i = 31; i <= 36; i++) {
			pane4.add(Button[i]);
			Button[i].addActionListener(this);
		}
		box1V11 = Box.createVerticalBox();
		box1V11.add(Box.createVerticalStrut(5));
		// JLabel JLab1=new JLabel("贴吧");//标题
		// JLab1.setFont(Font1);
		// box1V11.add(JLab1);
		// box1V11.add(Box.createVerticalStrut(5));
		box1H12 = Box.createHorizontalBox(); // {论坛搜索 查找框 查找按钮}
		box1H12.add(Box.createHorizontalStrut(12));
		box1H12.add(new JLabel("论坛搜索："));
		box1H12.add(Box.createHorizontalStrut(12));
		box1H12.add(findfield);
		box1H12.add(Box.createHorizontalStrut(12));
		box1H12.add(find);
		box1H12.add(Box.createHorizontalStrut(12));
		box1V11.add(box1H12);
		box1V1 = Box.createVerticalBox();
		box1V1.add(box1V11);
		box1V1.add(Box.createVerticalStrut(5));
		pane11.add(box1V1, BorderLayout.NORTH);
		pane11.add(new JScrollPane(pane111), BorderLayout.CENTER);
		pane1.add(tree, BorderLayout.WEST);
		pane1.add(pane11, BorderLayout.CENTER);
		// 底层标签
		textTabbedPane = new JTabbedPane();
		textTabbedPane.setFont(Font1);
		textTabbedPane.setForeground(Color.white);
		textTabbedPane.setBackground(new Color(100, 100, 200));
		textTabbedPane.addTab("推荐搜索", pane1);
		textTabbedPane.addTab("书友互动", TabbedPane[0]);
		textTabbedPane.addTab("图书交流", TabbedPane[1]);
		textTabbedPane.addTab("共同欣赏", TabbedPane[2]);
		textTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		setLayout(new java.awt.BorderLayout(5, 12));
		PANE.add(textTabbedPane, BorderLayout.CENTER);
		PANE.setVisible(true);
		add(PANE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void PostBarListWindow(int pages, int BlockID) {// 贴子目录界面
		sqlTopicType = "";
		sqlTopicTime = "";
		sqlTopicReorder = "";
		PostBarListpane[pages] = new JPanel(); // 整体界面{NORTH置顶面板条 CENTER帖子目录}
		PostBarListpane[pages].setBackground(new Color(100, 100, 200));
		PostBarListpane[pages].setLayout(new BorderLayout());
		JPanel postlistpaneH1 = new JPanel(); // 置顶面版条{发新帖 标题 水平盒子}
		postlistpaneH1.setBackground(new Color(100, 100, 200));
		postlistpaneH1.setLayout(new BorderLayout());
		Box postboxH1 = Box.createHorizontalBox(); // 水平盒子{页数 关闭}
		newposts = new JBUTTON("发新帖"); // 发新帖
		newposts.setPreferredSize(new Dimension(158, 8));// 固定大小
		newposts.addActionListener(this);
		JLabel title = new JLabel("标题"); // 标题
		postlistpaneH1.add(newposts, BorderLayout.WEST);
		postlistpaneH1.add(title, BorderLayout.CENTER);
		buttonchoice[pages] = 1;
		JBUTTON Listconfirm = new JBUTTON("跳转"); // 确定按钮
		Listconfirm.addActionListener(this); // 监听确定按钮
		JBUTTON Refreshfirm = new JBUTTON("刷新"); // 确定按钮
		Refreshfirm.addActionListener(this); // 监听确定按钮
		Listorder[pages] = new JComboBox();
		int items = 0;
		table.setTableName("topic");
		sqlTopic = sqlTopic00.concat(sqlTopicType).concat(sqlTopicTime).concat(
				" BlockID =").concat(String.valueOf(BlockID)).concat(
				sqlTopicReorder);
		// table.setSQL(sqlTopic+BlockID+"'"+sqlTopicReorder);
		table.setSQL(sqlTopic);
		rs = table.Query();
		try {
			rs.last();
			items = rs.getRow();
			rs.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i <= items / 20; i++) {
			String s = "第" + (i + 1) + "页";
			Listorder[pages].addItem(s);
		}
		postboxH1.add(Listorder[pages]);
		postboxH1.add(Box.createHorizontalStrut(12));
		postboxH1.add(Listconfirm);
		postboxH1.add(Box.createHorizontalStrut(12));
		postboxH1.add(Refreshfirm);
		postboxH1.add(Box.createHorizontalStrut(12));
		close = new JBUTTON("关闭");
		close.addActionListener(this);
		postboxH1.add(close);
		postboxH1.add(Box.createHorizontalStrut(5));
		postboxH2[pages] = Box.createHorizontalBox(); // 水平盒子{三个下拉列表}
		postboxH2[pages].add(new JLabel("类型："));
		JComboBox typechoice = new JComboBox(); // 类型下拉列表
		typechoice.addItem("全部类型");
		typechoice.addItem("普通");
		typechoice.addItem("精品");
		typechoice.addItem("推荐");
		typechoice.addItem("热门");
		typechoice.addItemListener(this);
		typechoice.setBackground(new Color(100, 100, 200));
		postboxH2[pages].add(typechoice);
		postboxH2[pages].add(new JLabel("时间："));
		JComboBox timechoice = new JComboBox(); // 时间下拉列表
		timechoice.addItem("全部时间");
		timechoice.addItem("一天");
		timechoice.addItem("两天");
		timechoice.addItem("一周");
		timechoice.addItem("一个月");
		timechoice.addItem("三个月");
		timechoice.addItemListener(this);
		timechoice.setBackground(new Color(100, 100, 200));
		postboxH2[pages].add(timechoice);
		JComboBox sort = new JComboBox(); // 排序下拉列表
		postboxH2[pages].add(new JLabel("排序："));
		sort.addItem("默认排序");
		sort.addItem("最后发表");
		sort.addItem("发帖时间");
		sort.addItem("回复/查看");
		sort.addItemListener(this);
		sort.setBackground(new Color(100, 100, 200));
		postboxH2[pages].add(sort);
		postlistpaneH1.add(postboxH1, BorderLayout.EAST);
		PostBarListpane[pages].add(postlistpaneH1, BorderLayout.NORTH);
		PostBarListCENWindow(pages, 0, BlockID);
		PostBarListpane[pages].add(new JScrollPane(PostBarListCEN[pages]),
				BorderLayout.CENTER);
	}

	// private void getPostBox(Box s){
	//		
	// }
	private void PostBarListCENWindow(int pages, int page, int BlockID) {// 帖子目录中央界面
		PostBarListCEN[pages] = new JPanel(); // 在NORTH中放入垂直盒子
		PostBarListCEN[pages].setBackground(new Color(100, 100, 200));
		PostBarListCEN[pages].setLayout(new BorderLayout());
		// postlistpaneCEN.add(postboxH2,BorderLayout.NORTH);
		Box PostBarListV1 = Box.createVerticalBox(); // 垂直盒子{帖子目录}
		PostBarListV1.add(Box.createVerticalStrut(8));
		PostBarListV1.add(postboxH2[pages]);
		PostBarListV1.add(Box.createVerticalStrut(8));
		table.setTableName("topic");
		sqlTopic = sqlTopic00.concat(sqlTopicType).concat(sqlTopicTime).concat(
				" BlockID =").concat(String.valueOf(BlockID)).concat(
				sqlTopicReorder);
		// table.setSQL(sqlTopic+BlockID+"'"+sqlTopicReorder);
		table.setSQL(sqlTopic);
		rs = table.Query();
		try {
			rs.absolute(20 * page+1);
			try {
				int i = 0;
				do{
					PostBarListForm(pages, i, rs.getInt(1), rs.getString(2), rs
							.getDate(3), rs.getString(4), rs.getString(5), rs
							.getLong(6), rs.getLong(7));
					PostBarListV1.add(PostBarList[pages]);
					if (i >= 20) {
						break;
					}
					i++;
				}while (rs.next()) ;
			} catch (SQLException e) {
				e.printStackTrace();
				warning="无该类结果！";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			warning="无该类结果！";
		}
		// for(int i=20*page;i<20*page+20;i++){ //20个贴一页
		// }
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// PostBarListCEN[pages].add(new
		// JScrollPane(PostBarListV1),BorderLayout.NORTH);
		PostBarListCEN[pages].add(PostBarListV1, BorderLayout.NORTH);
	}

	private void PostBarListForm(int pages, int items, int idTopic,
			String title, Date Createtime, String user_id, String Type,
			long Scan, long Reply) { // 帖子单条目录模板
		// Object type[]={"普通","精品","推荐","热门"}; //标签内容
		PostBarList[pages] = new JPanel(); // 整体界面
		PostBarList[pages].setBackground(new Color(100, 100, 150));
		PostBarList[pages].setLayout(new BorderLayout());
		JLabel typelabel = new JLabel(Type); // 标签
		JLabel username = new JLabel("用户ID" + user_id); // 用户名
		// AccessibleContext acc=new AccessibleContext("d");
		PostBarListtitle[pages][idTopic] = new JLabel(" " + title); // 标题
		JLabel time = new JLabel("" + Createtime); // 日期时间
		JLabel Replyview1 = new JLabel("回复" + Reply); // 回复数
		JLabel Replyview2 = new JLabel("查看" + Scan); // 查看数
		Replyview1.setPreferredSize(new Dimension(80, 8));
		Replyview2.setPreferredSize(new Dimension(80, 8));
		typelabel.setForeground(Color.YELLOW); // 颜色及字体
		PostBarListtitle[pages][idTopic].setFont(Font3);
		PostBarListtitle[pages][idTopic].setForeground(Color.ORANGE);
		PostBarListtitle[pages][idTopic].addMouseListener(this);
		username.setFont(Font4);
		username.setForeground(Color.white);
		time.setFont(Font4);
		time.setForeground(Color.white);
		JPanel PostBarListH1 = new JPanel(); // 面板1{水平盒子1水平盒子2}
		PostBarListH1.setBackground(new Color(100, 100, 220));
		PostBarListH1.setLayout(new BorderLayout());
		JPanel PostBarListH2 = new JPanel(); // 面板2{用户名 日期}
		PostBarListH2.setBackground(new Color(100, 100, 220));
		PostBarListH2.setLayout(new BorderLayout());
		Box PostBarListH01 = Box.createHorizontalBox(); // 水平盒子1{标签标题}
		PostBarListH01.add(typelabel);
		PostBarListH01.add(PostBarListtitle[pages][idTopic]);
		Box PostBarListH02 = Box.createHorizontalBox(); // 水平盒子2{回复查看}
		PostBarListH02.add(Replyview1);
		PostBarListH02.add(Box.createHorizontalStrut(8));
		PostBarListH02.add(Replyview2);
		PostBarListH1.add(PostBarListH01, BorderLayout.WEST);
		PostBarListH1.add(PostBarListH02, BorderLayout.EAST);
		PostBarListH2.add(username, BorderLayout.WEST);
		PostBarListH2.add(time, BorderLayout.EAST);
		Box PostBarListV1 = Box.createVerticalBox(); // 垂直盒子{面板1面板2}
		PostBarListV1.add(Box.createVerticalStrut(8));
		PostBarListV1.add(PostBarListH1);
		PostBarListV1.add(PostBarListH2);
		PostBarList[pages].add(PostBarListV1, BorderLayout.NORTH);
	}

	private void initPostWindow(int pages, int idTopic) { // 论坛帖子页面模板（点击帖子标题）
		int items = 0;
		postpane[pages] = new JPanel(); // 整体界面（主面板）
		postpane[pages].setBackground(new Color(100, 100, 200)); // 背景颜色
		postpane[pages].setLayout(new BorderLayout()); // 布局
		JPanel postpaneH1 = new JPanel(); // 置顶面版条{用户名 标题 水平盒子}
		postpaneH1.setBackground(new Color(100, 100, 200));
		postpaneH1.setLayout(new BorderLayout());
		Box postboxH1 = Box.createHorizontalBox(); // 水平盒子{页数 回帖 关闭}
		JLabel user = new JLabel("用户名"); // 用户名
		user.setPreferredSize(new Dimension(158, 8));
		JLabel title = new JLabel("标题" + pages); // 标题
		Posting[idTopic] = new JBUTTON("回帖"); // 回帖按钮
		Posting[idTopic].addActionListener(this);
		postboxH1.add(Box.createHorizontalStrut(12));
		Listorder[pages] = new JComboBox();
		table.setTableName("note");
		table.setSQL(sqlNote + idTopic + "'");
		rs = table.Query();
		try {
			rs.last();
			items = rs.getRow();
			rs.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i <= items / 30; i++) {
			String s = "第" + (i + 1) + "页";
			Listorder[pages].addItem(s);
		}
		postboxH1.add(Listorder[pages]);
		JBUTTON Listconfirm = new JBUTTON("转到"); // 转到按钮
		Listconfirm.addActionListener(this); // 监听确定按钮
		postboxH1.add(Box.createHorizontalStrut(12));
		postboxH1.add(Listconfirm);
		postboxH1.add(Box.createHorizontalStrut(12));
		close = new JBUTTON("关闭"); // 关闭按钮
		close.addActionListener(this);
		postboxH1.add(Posting[idTopic]);
		postboxH1.add(Box.createHorizontalStrut(12));
		postboxH1.add(close);
		postboxH1.add(Box.createHorizontalStrut(5));
		postpaneH1.add(user, BorderLayout.WEST);
		postpaneH1.add(title, BorderLayout.CENTER);
		postpaneH1.add(postboxH1, BorderLayout.EAST);
		initPostCEN(pages, 0, idTopic);
		postpane[pages].add(postpaneH1, BorderLayout.NORTH); // 置顶面板条放入主面板
		postpane[pages].add(new JScrollPane(postpane01[pages]),
				BorderLayout.CENTER);
		setItems(idTopic);
	}

	private void initPostCEN(int pages, int page, int idTopic) { // 帖子列表
		postpane01[pages] = new JPanel();
		postpane01[pages].setLayout(new BorderLayout());
		postpane01[pages].setBackground(new Color(100, 90, 200));
		Box boxV1 = Box.createVerticalBox(); // 垂直盒子boxV1
		boxV1.add(Box.createVerticalStrut(5));
		table.setTableName("note");
		table.setSQL(sqlNote + idTopic + "'");
		rs = table.Query();
		try {
			rs.absolute(30 * page+1);
			try {
				int i = 0;
				InputStream in = null;
				do{
					if (i >= 30) {
						break;
					}
					i++;
					int idNote = rs.getInt(1);
					in = rs.getBinaryStream(7);
					String icon = "images/userimg" + rs.getString(5) + i
							+ ".jpg";
					ImageUtil.readBlob(in, icon);
					PostitemForm(pages, i, idNote, rs.getString(2), rs
							.getDate(3), rs.getInt(4), rs.getString(5), rs
							.getString(6), icon);
					boxV1.add(postpaneinit[pages][idNote]); // boxV1中放入帖子模板
					boxV1.add(Box.createVerticalStrut(5));
				}while (rs.next()) ;
			} catch (SQLException e) {
				e.printStackTrace();
				warning="无该类结果！";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			warning="无该类结果！";
		}
		// for(int i=0+page*30;i<30+page*30;){ //根据initPost为帖子编号，最多同时为100个帖子编号
		// }
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for(int i=0+items*30;i<30+items*30;i++){
		// //根据initPost为帖子编号，最多同时为100个帖子编号
		// PostitemForm(pages,i);
		// boxV1.add(postpaneinit[pages][i]); //boxV1中放入帖子模板返回initPost*100
		// boxV1.add(Box.createVerticalStrut(5));
		// }
		JPanel postpane02 = new JPanel();
		postpane02.setLayout(new BorderLayout());
		postpane02.setBackground(new Color(100, 90, 200));
		postpane02.add(boxV1, BorderLayout.NORTH);
		postpane01[pages].add(postpane02, BorderLayout.CENTER);
		// postpane01[pages].add(new JScrollPane(boxV1),BorderLayout.CENTER);
	}

	private void PostitemForm(int pages, int items, int idNote, String Content,
			Date Createtime, int Rank, String user_id, String user_name,
			String icon) { // 帖子模板（每一条贴）
		postpaneinit[pages][idNote] = new JPanel(); // 帖子面板（主面板）
		postpaneinit[pages][idNote].setLayout(new BorderLayout());
		postpaneinit01 = new JPanel(); // 放入帖子面板SOUTH用来存放回复信息和清空回复信息
		postpaneinit01.setBackground(new Color(100, 100, 0));
		postpaneinit01.setLayout(new BorderLayout());
		postpaneinit02 = new JPanel(); // 放入帖子面板CENTER用来安置帖子内部布局（内部面板）
		postpaneinit02.setBackground(new Color(100, 100, 0));
		postpaneinit02.setLayout(new BorderLayout());
		// postpaneinit02.setPreferredSize(new Dimension(930, 200));
		JPanel postpaneinitH1 = new JPanel(); // 置顶面版条
		postpaneinitH1.setBackground(new Color(100, 100, 200));
		postpaneinitH1.setLayout(new BorderLayout());
		JPanel postpaneinitH2 = new JPanel(); // 放入内部面板SOUTH{底层按钮}
		postpaneinitH2.setBackground(new Color(100, 100, 160));
		JPanel postpaneinitH3 = new JPanel(); // 放入内部面板WEST{头像}
		postpaneinitH3.setBackground(new Color(100, 100, 160));
		JPanel postpaneinitH4 = new JPanel(); // 放入内部面板CENTER{文本区}
		postpaneinitH4.setBackground(new Color(100, 100, 160));
		JLabel username = new JLabel("用户名:" + user_name); // 用户名
		username.setPreferredSize(new Dimension(130, 8));
		JLabel date1 = new JLabel("日期" + Createtime);
		JLabel postnum = new JLabel("楼层" + Rank + "      ");// 楼层
		int myuserid = 0;
		for (int i = 0; i <= maxid; i++) {
			if (userID[i] == null) {
				userID[i] = "" + user_id;
				myuserid = i;
				break;
			} else if (userID[i].equals(user_id)) {
				myuserid = i;
				break;
			}
		}
		portrait[myuserid] = new JButton("", new ImageIcon(icon));
		// ("", new ImageIcon(
		// "Picture/Button-1.gif"));// 头像
		portrait[myuserid].setPreferredSize(new Dimension(120, 160));
		// letter[myuserid] = new JBUTTON("私信");
		// letter[myuserid].addActionListener(this);
		JLabel area = new JLabel(Content);
		area.setPreferredSize(new Dimension(800, 160));
		JScrollPane scroll = new JScrollPane(area); // 文本区添加滚动条
		// JBUTTON thumbsup = new JBUTTON("点赞");
		comment[pages][idNote] = new JBUTTON("回复");
		comment[pages][idNote].addActionListener(this);
		Openlist[pages][idNote] = new JBUTTON("查看回复");
		Openlist[pages][idNote].addActionListener(this);
		postpaneinitH1.add(username, BorderLayout.WEST);
		postpaneinitH1.add(date1, BorderLayout.CENTER);
		postpaneinitH1.add(postnum, BorderLayout.EAST);
		Box postinitboxH2 = Box.createHorizontalBox(); // 存放底层按钮
		postinitboxH2.add(Box.createHorizontalStrut(640));
		// postinitboxH2.add(thumbsup);
		postinitboxH2.add(Box.createHorizontalStrut(8));
		// postinitboxH2.add(letter[myuserid]);
		// postinitboxH2.add(Box.createHorizontalStrut(8));
		postinitboxH2.add(comment[pages][idNote]);
		postinitboxH2.add(Box.createHorizontalStrut(8));
		postinitboxH2.add(Openlist[pages][idNote]);
		postinitboxH2.add(Box.createHorizontalStrut(8));
		postpaneinitH2.add(postinitboxH2);
		Box postpaneinitV3 = Box.createVerticalBox(); // 存放头像
		postpaneinitV3.add(portrait[myuserid]);
		postpaneinitH3.add(postpaneinitV3);
		postpaneinitH4.add(scroll);
		postpaneinit02.add(postpaneinitH1, BorderLayout.NORTH);
		postpaneinit02.add(postpaneinitH3, BorderLayout.WEST);
		postpaneinit02.add(postpaneinitH4, BorderLayout.CENTER);
		postpaneinit02.add(postpaneinitH2, BorderLayout.SOUTH);
		postpaneinit[pages][idNote].add(postpaneinit02, BorderLayout.CENTER);
	}

	private void BarCommentForm(int pages, int idReply, String Content,
			Date date0, String user_name) { // 回复条模板（点击回复列表）
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String time0 = formatter.format(date0);
		BarComment[pages] = new JPanel(); // 整体界面
		BarComment[pages].setBackground(new Color(100, 100, 200));
		BarComment[pages].setLayout(new BorderLayout());
		BarComment[pages].setSize(10, 80);
		JPanel postpaneinit012 = new JPanel(); // {用户名 内容 回复}
		postpaneinit012.setBackground(new Color(100, 100, 150));
		postpaneinit012.setLayout(new BorderLayout());
		postpaneinit012.setPreferredSize(new Dimension(900, 25));
		JLabel username = new JLabel(user_name + ":"); // 用户名
		username.setPreferredSize(new Dimension(80, 8));
		JLabel title = new JLabel("" + Content); // 内容
		// Barreply[pages][Rank]=new JBUTTON("回复"); //回复
		// Barreply[pages][Rank].addActionListener(this);
		JLabel time1 = new JLabel("" + time0);
		postpaneinit012.add(username, BorderLayout.WEST);
		postpaneinit012.add(title, BorderLayout.CENTER);
		postpaneinit012.add(time1, BorderLayout.EAST);
		BarComment[pages].add(postpaneinit012, BorderLayout.NORTH);
	}

	private void PostreplyForm(int pages, int type, int ID) { // 南回复模板（点击回帖/回复/私信）
		Postreplytpane[pages] = new JPanel();
		Postreplytpane[pages].setBackground(new Color(100, 100, 200));
		Postreplytpane[pages].setLayout(new BorderLayout());
		JPanel PostreplytpaneN = new JPanel();
		PostreplytpaneN.setBackground(new Color(100, 100, 200));
		PostreplytpaneN.setLayout(new BorderLayout());
		// JLabel Forsome = new JLabel("回复“" + "”：");
		reply[type][ID] = new JBUTTON("回复");
		reply[type][ID].addActionListener(this);
		cancelreply[pages] = new JBUTTON("取消");
		cancelreply[pages].addActionListener(this);
		Box PostreplyH1 = Box.createHorizontalBox();
		PostreplyH1.add(reply[type][ID]);
		PostreplyH1.add(Box.createHorizontalStrut(8));
		PostreplyH1.add(cancelreply[pages]);
		// PostreplytpaneN.add(Forsome, BorderLayout.WEST);
		PostreplytpaneN.add(PostreplyH1, BorderLayout.EAST);
		Postreplytpane[pages].add(PostreplytpaneN, BorderLayout.NORTH);
		Postreplytpane[pages].add(edit01.editpanecen, BorderLayout.CENTER);
	}

	private void NewPostForm(int pages, int BlockID) { // 发新帖模板
		NewPost = new JPanel();
		NewPost.setBackground(new Color(100, 100, 200));
		NewPost.setLayout(new BorderLayout());
		JPanel NewPostNorth = new JPanel(); // 北面板
		NewPostNorth.setBackground(new Color(100, 100, 200));
		NewPostNorth.setLayout(new BorderLayout());
		Object type1[] = { "", "", "", "选择主题分类", "选择主题分类", "选择主题分类", "书友交流",
				"童书交流", "情感日志", "书友推荐", "小说交流", "美文欣赏", "读后感", "文学书籍交流",
				"作文大全", "电子书", "历史传记交流", "美图欣赏", "写作原创", "心理学交流", "杂粹杂谈",
				"意见反馈", "励志治愈交流", "经典语录" };// 主题分类
		title[pages] = new JTextField("题目"); // 题目
		published = new JBUTTON("发表"); // 发表按钮
		published.addActionListener(this);
		JComboBox typechoice = new JComboBox(); // 类型下拉列表
		for (int i = (pages - 1) / 10 - 4; i < 24; i = i + 3) {
			typechoice.addItem(type1[i]);
		}
		typechoice.addItemListener(this);
		Box NewPostH1 = Box.createHorizontalBox();
		// Box NewPostH2=Box.createHorizontalBox();
		// NewPostH1.add(new JLabel("主题分类："));
		// NewPostH1.add(typechoice);
		// NewPostH2.add(new JLabel("题目："));
		// NewPostH2.add(title);
		closenew = new JBUTTON("关闭"); // 关闭按钮
		closenew.addActionListener(this); // 监听关闭按钮
		NewPostH1.add(published);
		NewPostH1.add(closenew);
		typechoice.setBackground(new Color(100, 100, 200));
		NewPostNorth.add(typechoice, BorderLayout.WEST);
		NewPostNorth.add(title[pages], BorderLayout.CENTER);
		NewPostNorth.add(NewPostH1, BorderLayout.EAST);
		NewPost.add(NewPostNorth, BorderLayout.NORTH);
		NewPost.add(edit.editpanecen, BorderLayout.CENTER);
		setBlockID(BlockID);
		publish = false;
	}

	private void setBlockID(int s) {
		idBlock = s;
	}

	private void setItems(int s) {
		Items = s;
	}

	private void setItems01(int s) {
		Items01 = s;
	}

	private void Time() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		time = formatter.format(new Date());
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == find || e.getSource() == findfield) {
			table.setTableName("topic");
			String findthis = findfield.getText();
			findthis = sqlTopicFind.concat(findthis).concat("%'");
			Box pane111box = Box.createVerticalBox(); // 垂直盒子{帖子目录}
			for (int i = 11; i <= 16; i++) {
				int i0 = i;
				for (; i0 < maximum2; i0 = i0 + 30) {
					if (buttonchoice[i0] == 0) {
						buttonchoice[i0] = 1;
						break;
					}
				}
				table.setSQL(findthis);
				rs = table.Query();
				try {
					int i1 = 0;
					while (rs.next()) {
						PostBarListForm(i0, i1, rs.getInt(1), rs.getString(2),
								rs.getDate(3), rs.getString(4),
								rs.getString(5), rs.getLong(6), rs.getLong(7));
						pane111box.add(PostBarList[i0]);
						if (i1 >= 20) {
							break;
						}
						i1++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					warning="无该类结果！";
				}
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			for (int i = 21; i <= 26; i++) {
				int i0 = i;
				for (; i0 < maximum2; i0 = i0 + 30) {
					if (buttonchoice[i0] == 0) {
						buttonchoice[i0] = 1;
						break;
					}
				}
				// table.setSQL(sqlTopicFind+findthis+"%'");
				table.setSQL(findthis);
				rs = table.Query();
				try {
					int i1 = 0;
					while (rs.next()) {
						PostBarListForm(i0, i1, rs.getInt(1), rs.getString(2),
								rs.getDate(3), rs.getString(4),
								rs.getString(5), rs.getLong(6), rs.getLong(7));
						pane111box.add(PostBarList[i0]);
						if (i1 >= 20) {
							break;
						}
						i1++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					warning="无该类结果！";
				}
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			for (int i = 31; i <= 36; i++) {
				int i0 = i;
				for (; i0 < maximum2; i0 = i0 + 30) {
					if (buttonchoice[i0] == 0) {
						buttonchoice[i0] = 1;
						break;
					}
				}
				table.setSQL(findthis);
				rs = table.Query();
				try {
					int i1 = 0;
					while (rs.next()) {
						PostBarListForm(i0, i1, rs.getInt(1), rs.getString(2),
								rs.getDate(3), rs.getString(4),
								rs.getString(5), rs.getLong(6), rs.getLong(7));
						pane111box.add(PostBarList[i0]);
						if (i1 >= 20) {
							break;
						}
						i1++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					warning="无该类结果！";
				}
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			System.out.println(findthis);
			pane111.removeAll();
			pane111.add(pane111box, BorderLayout.NORTH);
			pane111.updateUI();
			pane1.updateUI();
		}
		if (((JBUTTON) e.getSource()).getText().equals("发表")) { // 发表新帖
			// JOptionPane.showMessageDialog(this,"您已经打开一个发帖页面啦！","",JOptionPane.WARNING_MESSAGE
			// );
			Pagedetection();
			buttonchoice[ToolTip] = 0;
			if (typechange != 0) {
				table.setTableName("topic");
				table.setSQL(sqlTopicbest);
				rs = table.Query();
				try {
					int best = 0;
					while (rs.next()) {
						best = rs.getInt(1);
					}
					try {
						rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					best = best + 1;
					Time();
					String init00 = "('" + best + "','"
							+ title[ToolTip].getText() + "','" + time + "','"
							+ userid + "','" + typechange + "','普通','" + 0
							+ "','" + 0 + "')";
					table.setSQL(sqlTopicAdd + init00);
					table.Insert();
					table.setTableName("note");
					table.setSQL(sqlNotebest0);
					rs = table.Query();
					int best0 = 0;
					while (rs.next()) {
						best0 = rs.getInt(1);
					}
					try {
						rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					best0 = best0 + 1;
					String init01 = "('" + best0 + "','"
							+ (edit.textPane).getText() + "','" + time + "','"
							+ 1 + "','" + best + "','" + userid + "')";
					table.setSQL(sqlNoteAdd + init01);
					table.Insert();
					table.setTableName("topic");
					table.setSQL(sqlTopicReply + best + "'");
					table.Insert();
					publish = true;
					Pagedetection();
					buttonchoice[ToolTip] = 0;
					TabbedPane[bottm - 1].removeTabAt(top);
					try {
						urlPrompt = uriPrompt.toURL();
						clip=Applet.newAudioClip(urlPrompt);
						clip.play();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(this, "成功发表！", "提示",
							JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (((JBUTTON) e.getSource()).getText().equals("关闭")) { // 关闭当前打开的页面
			try {
				urlClicked = uriClicked.toURL();
				clip=Applet.newAudioClip(urlClicked);
				clip.play();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Pagedetection();
			buttonchoice[ToolTip] = 0;
			TabbedPane[bottm - 1].removeTabAt(top);
			if (e.getSource() == closenew) {
				publish = true;
			}
		}
		if (((JBUTTON) e.getSource()).getText().equals("刷新")) { // 点击刷新刷新页面
			Pagedetection();
			for (int j = 0; j < maximum2; j++) {
				String s = "第" + (j + 1) + "页";
				if (s.equals(Listorder[ToolTip].getSelectedItem().toString())) {
					PostBarListCEN[ToolTip] = null;
					PostBarListpane[ToolTip].remove(1);
					int BlockID = (ToolTip - 11) % 30 + 11;
					PostBarListCENWindow(ToolTip, j, BlockID);
					PostBarListCEN[ToolTip].updateUI();
					PostBarListpane[ToolTip].add(new JScrollPane(
							PostBarListCEN[ToolTip]), BorderLayout.CENTER);
					PostBarListpane[ToolTip].updateUI();
				}
			}
		}
		if (((JBUTTON) e.getSource()).getText().equals("跳转")) { // 点击跳转跳转页面
			Pagedetection();
			for (int j = 0; j < maximum2; j++) {
				String s = "第" + (j + 1) + "页";
				if (s.equals(Listorder[ToolTip].getSelectedItem().toString())) {
					PostBarListCEN[ToolTip] = null;
					PostBarListpane[ToolTip].remove(1);
					int BlockID = (ToolTip - 11) % 30 + 11;
					PostBarListCENWindow(ToolTip, j, BlockID);
					PostBarListCEN[ToolTip].updateUI();
					PostBarListpane[ToolTip].add(new JScrollPane(
							PostBarListCEN[ToolTip]), BorderLayout.CENTER);
					PostBarListpane[ToolTip].updateUI();
				}
			}
		}
		if (((JBUTTON) e.getSource()).getText().equals("转到")) { // 点击转到跳转页面
			Pagedetection();
			for (int j = 0; j < maximum2; j++) {
				String s = "第" + (j + 1) + "页";
				if (s.equals(Listorder[ToolTip].getSelectedItem().toString())) {
					postpane01[ToolTip] = null;
					postpane[ToolTip].remove(1);
					initPostCEN(ToolTip, j, Items);
					postpane[ToolTip].add(new JScrollPane(postpane01[ToolTip]),
							BorderLayout.CENTER);
					postpane[ToolTip].updateUI();
				}
			}
		}
		if (((JBUTTON) e.getSource()).getText().equals("回帖")) {
			for (int i = 0; i < maximum; i++) { // 打开回帖界面（回帖）
				if (e.getSource() == Posting[i]) {
					Pagedetection();
					generalcomment(ToolTip, 0, i);
				}
			}
		}
		if (((JBUTTON) e.getSource()).getText().equals("回复")) {
			for (int i = 0; i < maximum2; i++) {
				for (int j = 0; j < maximum; j++) {
					if (e.getSource() == comment[i][j]) { // 打开回复界面（帖子）
						generalcomment(i, 1, j);
					}
				}
			} 
			for (int i = 0; i < maximum; i++) { // 南回复模板回复按钮
				if (e.getSource() == reply[0][i]) { // 回复帖子
					table.setTableName("topic");
					table.setSQL(sqlTopicReply + i + "'");
					table.Insert();
					Time();
					table.setTableName("note");
					table.setSQL(sqlNotebest1 + i + "'");
					rs = table.Query();
					int best0 = 0, best1 = 0;
					try {
						while (rs.next()) {
							best0 = rs.getInt(1);
							best1 = rs.getInt(2);
						}
						try {
							urlPrompt = uriPrompt.toURL();
							clip=Applet.newAudioClip(urlPrompt);
							clip.play();
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(this, "回帖成功！", "提示",
								JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					best0 = best0 + 1;
					best1 = best1 + 1;
					String init01 = "('" + best0 + "','"
							+ (edit01.textPane).getText() + "','" + time
							+ "','" + best1 + "','" + i + "','" + userid + "')";
					table.setSQL(sqlNoteAdd + init01);
					table.Insert();
					System.out.println(init01);
				} else if (e.getSource() == reply[1][i]) { // 回复bar
					Time();
					table.setTableName("reply");
					table.setSQL(sqlReplybest1 + i + "'");
					rs = table.Query();
					int best0 = 0, best1 = 0;
					try {
						while (rs.next()) {
							best0 = rs.getInt(1);
							best1 = rs.getInt(2);
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					try {
						rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					best0 = best0 + 1;
					best1 = best1 + 1;
					String init01 = "('" + best0 + "','"
							+ (edit01.textPane).getText() + "','" + time
							+ "','" + best1 + "','" + i + "','" + userid + "')";
					table.setSQL(sqlReplyAdd + init01);
					table.Insert();
					table.setTableName("note");
					table.setSQL(sqlNoteReply + i + "'");
					rs = table.Query();
					int idTopic = 0;
					try {
						while (rs.next()) {
							idTopic = rs.getInt(1);
						}
						table.setTableName("topic");
						table.setSQL(sqlTopicReply + idTopic + "'");
						table.Insert();
						try {
							urlPrompt = uriPrompt.toURL();
							clip=Applet.newAudioClip(urlPrompt);
							clip.play();
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(this, "回复成功", "提示",
								JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(init01);
				} else if (e.getSource() == reply[2][i]) { // 回复私信

				}
			}
		}
		// for (int i = 0; i < maxid; i++) {
		// if (e.getSource() == letter[i]) { // 打开回复界面（私信）
		// generalcomment(i, 2, 0);
		// }
		// }
		if (((JBUTTON) e.getSource()).getText().equals("取消")) {			
			for (int i = 0; i < maximum2; i++) {
				if (e.getSource() == cancelreply[i]) { // 取消回复界面
					try {
						urlClicked = uriClicked.toURL();
						clip=Applet.newAudioClip(urlClicked);
						clip.play();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Postreplytpane[i].removeAll();
					postpane[i].remove(2);
					postpane[i].add(Postreplytpane[i], BorderLayout.SOUTH);
					postpane[i].updateUI();
					Postreplytpane[i] = null;
					cancelreply[i] = null;
				}
			}
		}
		if (((JBUTTON) e.getSource()).getText().equals("查看回复")) {
			for (int i = 0; i < maximum2; i++) {
				for (int j = 0; j < maximum; j++) {
					if (e.getSource() == Openlist[i][j]) { // 打开回复列表
						if (judgeOpenlist == 0) { // 控制打开关闭列表
							JPanel postpaneinit02 = new JPanel();
							Box postpaneinit02V2 = Box.createVerticalBox();
							postpaneinit02.setBackground(new Color(100, 100,
									200));
							table.setTableName("reply");
							table.setSQL(sqlReplybest0 + j + "'");
							rs = table.Query();
							int barnum = 0;
							try {
								while (rs.next()) {
									barnum = rs.getInt(1);
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							table.setTableName("reply");
							table.setSQL(sqlReply + j + "'");
							rs = table.Query();
							for (int barnum0 = 0; barnum0 < barnum; barnum0++) { // 根据initPost为帖子编号，最多同时为100个帖子编号
								try {
									while (rs.next()) {
										BarCommentForm(i, rs.getInt(1), rs
												.getString(2), rs.getDate(3),
												rs.getString(4));
										postpaneinit02V2.add(BarComment[i]);
										postpaneinit02V2.add(Box
												.createVerticalStrut(5));
									}
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
							try {
								rs.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							if (barnum != 0) {
								postpaneinit02.add(postpaneinit02V2);
								postpaneinit01.add(postpaneinit02,
										BorderLayout.NORTH);
								postpaneinit[i][j].add(postpaneinit01,
										BorderLayout.SOUTH);
								postpane[i].updateUI(); // 刷新界面
								judgeOpenlist = 1;
							}
						} else if (judgeOpenlist == 1) {
							postpaneinit01.removeAll();
							postpane[i].updateUI();
							judgeOpenlist = 0;
						}
					}
				}
			}
		}
		if (((JBUTTON) e.getSource()).getText().equals("发新帖")) { // 发新帖
			try {
				urlClicked = uriClicked.toURL();
				clip=Applet.newAudioClip(urlClicked);
				clip.play();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (publish == true) {
				Pagedetection();
				for (int i0 = ToolTip; i0 < maximum2; i0 = i0 + 30) {
					if (buttonchoice[i0] == 0) {
						NewPostForm(i0, (ToolTip - 11) % 30 + 11);
						TabbedPane[bottm - 1].insertTab("发新帖", null, NewPost,
								"" + i0, 1);
						TabbedPane[bottm - 1].setSelectedIndex(1);
						buttonchoice[i0] = 1;
						break;
					}
				}
			} else {
				try {
					urlWarning = uriWarning.toURL();
					clip=Applet.newAudioClip(urlWarning);
					clip.play();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "已经打开一个发新帖页面了", "提示",
						JOptionPane.PLAIN_MESSAGE);
			}

		}
		for (int i = 11; i <= 16; i++) {
			if (e.getSource() == Button[i]) {
				panebuttonchoice(0, i);
			}
		}
		for (int i = 21; i <= 26; i++) {
			if (e.getSource() == Button[i]) {
				panebuttonchoice(1, i);
			}
		}
		for (int i = 31; i <= 36; i++) {
			if (e.getSource() == Button[i]) {
				panebuttonchoice(2, i);
			}
		}
	}

	void RefreshPost() {
		Pagedetection();
		for (int j = 0; j < maximum2; j++) {
			String s = "第" + (j + 1) + "页";
			if (s.equals(Listorder[ToolTip].getSelectedItem().toString())) {
				postpane01[ToolTip] = null;
				postpane[ToolTip].remove(1);
				initPostCEN(ToolTip, j, Items);
				postpane[ToolTip].add(new JScrollPane(postpane01[ToolTip]),
						BorderLayout.CENTER);
				postpane[ToolTip].updateUI();
			}
		}
	}

	void panebuttonchoice(int i, int BlockID) { // 次界面选择
		for (int i0 = BlockID; i0 < maximum2; i0 = i0 + 30) {
			if (buttonchoice[i0] == 0) {
				buttonchoice[i0] = 1;
				PostBarListWindow(i0, BlockID); // 返回页码、帖子数目
				String type1[]={"书友交流","书友推荐","读后感","电子书","写作原创","意见反馈"};		//主题分类
				String type2[]={"童书交流","小说交流","文学书籍交流","历史传记交流","心理学交流","励志治愈交流"};
				String type3[]={"情感日志","美文欣赏","作文大全","美图欣赏","杂粹杂谈","经典语录"};	
				for(int i1=11;i1<=16;i1++){
					if ((i0 - i1) % 30 == 0) {
						TabbedPane[i].insertTab(type1[i1-11], null, PostBarListpane[i0],
								"" + i0, 1);
					}
				}
				for(int i1=21;i1<=26;i1++){
					if ((i0 - i1) % 30 == 0) {
						TabbedPane[i].insertTab(type2[i1-21], null, PostBarListpane[i0],
								"" + i0, 1);
					}
				}
				for(int i1=31;i1<=36;i1++){
					if ((i0 - i1) % 30 == 0) {
						TabbedPane[i].insertTab(type3[i1-31], null, PostBarListpane[i0],
								"" + i0, 1);
					}
				}
				break;
			}
		}
		TabbedPane[i].setSelectedIndex(1); // 调到index为1的面板
	}

	void Pagedetection() { // 通用页面检测
		bottm = textTabbedPane.getSelectedIndex(); // 取得外围页数index
		top = TabbedPane[textTabbedPane.getSelectedIndex() - 1]
				.getSelectedIndex(); // 取得当前页数index
		ToolTip = Integer.parseInt(TabbedPane[bottm - 1].getToolTipTextAt(top)); // 返回由鼠标事件位置确定的组件的工具提示文本
		// 页数page
	}

	void generalBarListchoice(int i, int pages, int idTopic) { // 通用贴子主题选择----------------------------------
		for (int i0 = pages; i0 < maximum2; i0 = i0 + 30) {
			// int
			// i0=TabbedPane[textTabbedPane.getSelectedIndex()-1].getSelectedIndex();
			if (buttonchoice[i0] == 0) {
				table.setTableName("topic");
				table.setSQL(sqlTopicScan + idTopic + "'");
				table.Insert();
				table.setSQL(sqlTopicTitle+idTopic+"'");
				rs = table.Query();
				String tptitle="";
				try {
					while(rs.next()){
						tptitle = rs.getString(1);
					}					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				buttonchoice[i0] = 1;
				initPostWindow(i0, idTopic);
				/** 插入页面 标题 图片 组件 标签（页码编号） 位置 */
				TabbedPane[i]
						.insertTab(tptitle, null, postpane[i0], "" + i0, 1);
				break;
			}
		}
		textTabbedPane.setSelectedIndex(i + 1); // 调到index为i的面板（主面板）
		TabbedPane[i].setSelectedIndex(1); // 调到index为1的面板（次面板）
	}

	void generalcomment(int i, int type, int ID) { // 通用回复（回帖 私信 回复）弹出回复框
		if (cancelreply[i] != null) {
			Postreplytpane[i].removeAll();
			postpane[i].remove(2);
			postpane[i].add(Postreplytpane[i], BorderLayout.SOUTH);
			postpane[i].updateUI();
			Postreplytpane[i] = null;
			cancelreply[i] = null;
		} else if (cancelreply[i] == null) {
			PostreplyForm(i, type, ID);
			postpane[i].add(Postreplytpane[i], BorderLayout.SOUTH);
			postpane[i].updateUI();
		}
	}

	public void mouseClicked(MouseEvent e) {
		for (int i = 11; i <= 16; i++) {
			for (int i0 = i; i0 < maximum2; i0 = i0 + 30) { // 1
				for (int j = 0; j < maximum; j++) {
					if (e.getSource() == PostBarListtitle[i0][j]) {
						generalBarListchoice(0, 11, j);
						break;
					}
				}
			}
		}
		for (int i = 21; i <= 26; i++) {
			for (int i0 = i; i0 < maximum2; i0 = i0 + 30) { // 2
				for (int j = 0; j < maximum; j++) {
					if (e.getSource() == PostBarListtitle[i0][j]) {
						generalBarListchoice(1, 21, j);
						break;
					}
				}
			}
		}
		for (int i = 31; i <= 36; i++) {
			for (int i0 = i; i0 < maximum2; i0 = i0 + 30) { // 3
				for (int j = 0; j < maximum; j++) {
					if (e.getSource() == PostBarListtitle[i0][j]) {
						generalBarListchoice(2, 31, j);
						break;
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {

		@SuppressWarnings("unused")
		Homepage Homepage = new Homepage("书友论坛");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		String item = e.getItem().toString();
		String type[] = { "全部类型", "普通", "精品", "推荐", "热门", "全部时间", "一天", "两天",
				"一周", "一个月", "三个月", "默认排序", "最后发表", "发帖时间", "回复/查看", }; // 标签内容
		String type02[] = { "选择主题分类", "选择主题分类", "选择主题分类", "书友交流", "童书交流",
				"情感日志", "书友推荐", "小说交流", "美文欣赏", "读后感", "文学书籍交流", "作文大全", "电子书",
				"历史传记交流", "美图欣赏", "写作原创", "心理学交流", "杂粹杂谈", "意见反馈", "励志治愈交流",
				"经典语录" };
		if (e.getStateChange() == ItemEvent.SELECTED) {
			for (int i = 0; i < 15; i++) {
				if (type[i].equals(item)) {
					if (i == 0) {
						sqlTopicType = "";
					} else if (i == 1) {
						sqlTopicType = " Type='普通' AND ";
					} else if (i == 2) {
						sqlTopicType = " Type='精品' AND ";
					} else if (i == 3) {
						sqlTopicType = " Type='推荐' AND ";
					} else if (i == 4) {
						sqlTopicType = " Type='热门' AND ";
					} else if (i == 5) {
						sqlTopicTime = "";
					} else if (i == 6) {
						sqlTopicTime = " DATE_SUB(CURDATE(), INTERVAL 1 DAY) <=date(Createtime) AND ";
					} else if (i == 7) {
						sqlTopicTime = " DATE_SUB(CURDATE(), INTERVAL 2 DAY) <=date(Createtime) AND ";
					} else if (i == 8) {
						sqlTopicTime = " DATE_SUB(CURDATE(), INTERVAL 7 DAY) <=date(Createtime) AND ";
					} else if (i == 9) {
						sqlTopicTime = " DATE_SUB(CURDATE(), INTERVAL 30 DAY) <=date(Createtime) AND ";
					} else if (i == 10) {
						sqlTopicTime = " DATE_SUB(CURDATE(), INTERVAL 90 DAY) <=date(Createtime) AND ";
					} else if (i == 11) {
						sqlTopicReorder = "";
					} else if (i == 12) {
						sqlTopicReorder = " ORDER BY 3 DESC";
					} else if (i == 13) {
						sqlTopicReorder = " ORDER BY Createtime ASC";
					} else if (i == 14) {
						sqlTopicReorder = " ORDER BY 7 DESC";
					}
					// sqlTopic=sqlTopic00.concat(sqlTopicType).concat(sqlTopicTime).concat(" BlockID ='");
					Pagedetection();
					PostBarListCEN[ToolTip] = null;
					PostBarListpane[ToolTip].remove(1);
					int BlockID = (ToolTip - 11) % 30 + 11;
					PostBarListCENWindow(ToolTip, 0, BlockID);
					PostBarListCEN[ToolTip].updateUI();
					PostBarListpane[ToolTip].add(new JScrollPane(
							PostBarListCEN[ToolTip]), BorderLayout.CENTER);
					PostBarListpane[ToolTip].updateUI();
					if(warning.equals("")){
						
					}else{
						try {
							urlWarning = uriWarning.toURL();
							clip=Applet.newAudioClip(urlWarning);
							clip.play();
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(this, warning, "提示",
								JOptionPane.PLAIN_MESSAGE);
						warning="";
					}
				}
			}
			for (int i = 0; i < 21; i = i + 3) {
				if (type02[i].equals(item)) {
					if (i == 0) {
						typechange = 0;
					} else {
						typechange = i / 3 + 10;
					}
				}
			}
			for (int i = 1; i < 21; i = i + 3) {
				if (type02[i].equals(item)) {
					if (i == 1) {
						typechange = 0;
					} else {
						typechange = i / 3 + 20;
					}
				}
			}
			for (int i = 2; i < 21; i = i + 3) {
				if (type02[i].equals(item)) {
					if (i == 2) {
						typechange = 0;
					} else {
						typechange = i / 3 + 30;
					}
				}
			}
		}
	}
}