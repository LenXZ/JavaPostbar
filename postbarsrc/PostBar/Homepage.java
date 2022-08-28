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
		Font1 = new Font("΢���ź�", Font.PLAIN, 12);// ����
		// new Font("΢���ź�",Font.PLAIN,16);
		Font3 = new Font("΢���ź�", Font.PLAIN, 18);
		Font4 = new Font("΢���ź�", Font.PLAIN, 10);
		UIManager.put("Button.font", new java.awt.Font("΢���ź�", Font.PLAIN, 12)); // Ĭ������{��ť
		// ��ǩ
		// Tabbedҳ��
		// �����б�}
		UIManager.put("Label.font", new java.awt.Font("΢���ź�", Font.PLAIN, 12));
		UIManager.put("TabbedPane.font", new java.awt.Font("΢���ź�", Font.PLAIN,
				12));
		UIManager.put("ComboBox.font",
				new java.awt.Font("΢���ź�", Font.PLAIN, 12));
		setBounds(200, 100, 1000, 618);
		PANE = new JPanel();
		pane1 = new JPanel();
		pane2 = new JPanel();
		pane3 = new JPanel();
		pane4 = new JPanel();
		PANE.setBounds(200, 100, 1000, 322);
		PANE.setLayout(new BorderLayout(5, 12));
		findfield = new JTextField(10); // �����ı���
		find = new JBUTTON("����"); // ���Ұ�ť
		find.setFont(Font1);
		find.setForeground(Color.ORANGE);
		find.setBackground(new Color(100, 100, 150));
		findfield.addActionListener(this);
		find.addActionListener(this);
		// ��
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(
				"      ��̳                "); // ���ڵ�
		DefaultMutableTreeNode nodeA = new DefaultMutableTreeNode("���ѻ���"); // �ڵ�
		DefaultMutableTreeNode nodeB = new DefaultMutableTreeNode("ͼ�齻��");
		DefaultMutableTreeNode nodeC = new DefaultMutableTreeNode("��������");
		DefaultMutableTreeNode nodeD = new DefaultMutableTreeNode("���ѽ���");
		DefaultMutableTreeNode A1 = // �ӽڵ�
		new DefaultMutableTreeNode("���ѽ���");
		DefaultMutableTreeNode A2 = new DefaultMutableTreeNode("�����Ƽ�");
		DefaultMutableTreeNode A3 = new DefaultMutableTreeNode("�����");
		DefaultMutableTreeNode A4 = new DefaultMutableTreeNode("������");
		DefaultMutableTreeNode A5 = new DefaultMutableTreeNode("д��ԭ��");
		DefaultMutableTreeNode A6 = new DefaultMutableTreeNode("�������");
		DefaultMutableTreeNode B1 = new DefaultMutableTreeNode("ͯ�齻��");
		DefaultMutableTreeNode B2 = new DefaultMutableTreeNode("С˵����");
		DefaultMutableTreeNode B3 = new DefaultMutableTreeNode("��ѧ�鼮����");
		DefaultMutableTreeNode B4 = new DefaultMutableTreeNode("��ʷ���ǽ���");
		DefaultMutableTreeNode B5 = new DefaultMutableTreeNode("����ѧ����");
		DefaultMutableTreeNode B6 = new DefaultMutableTreeNode("��־��������");
		DefaultMutableTreeNode C1 = new DefaultMutableTreeNode("�����־");
		DefaultMutableTreeNode C2 = new DefaultMutableTreeNode("��������");
		DefaultMutableTreeNode C3 = new DefaultMutableTreeNode("���Ĵ�ȫ");
		DefaultMutableTreeNode C4 = new DefaultMutableTreeNode("��ͼ����");
		DefaultMutableTreeNode C5 = new DefaultMutableTreeNode("�Ӵ���̸");
		DefaultMutableTreeNode C6 = new DefaultMutableTreeNode("������¼");
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
		tree.setFont(Font1);// ������
		// tree.setForeground(Color.red);
		// tree.setBackground(Color.red);
		// pane1����
		pane1.setLayout(new BorderLayout());
		pane1.setFont(Font1);
		// pane11����
		pane11 = new JPanel(); // pane1��CENTER����
		pane11.setLayout(new BorderLayout());
		pane11.setBackground(new Color(100, 100, 170));
		pane111 = new JPanel(); // �Ƽ������б�
		pane111.setLayout(new BorderLayout());
		// pane111.setLayout(new BoxLayout(pane111, BoxLayout.Y_AXIS));
		Box pane111box = Box.createVerticalBox(); // ��ֱ����{����Ŀ¼}
		for (int i = 0; i < 23; i++) {
			// PostBarListForm(2,i,12);
			// pane111.add(PostBarList[2]); //�������

		}
		table.setTableName("topic");
		sqlTopicType = " Type='�Ƽ�' AND ";
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
				warning="�޸�������";
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
				warning="�޸�������";
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
				warning="�޸�������";
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
			TabbedPane[i] = new JTabbedPane(); // ��������ײ��ǩ
			TabbedPane[i].setForeground(Color.white);
			TabbedPane[i].setBackground(new Color(100, 100, 200));
			TabbedPane[i].setTabPlacement(JTabbedPane.TOP);
		}
		// pane2���� ���ѻ���ҳ
		TabbedPane[0].addTab("���ѻ���", pane2);
		pane2.setLayout(new GridLayout(2, 3));
		// JBUTTON pbutton1=new JBUTTON("���ѽ���",new
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
		// pane3���� ͼ�齻��ҳ
		TabbedPane[1].addTab("ͼ�齻��", pane3);
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
		// pane4���� ��ͬ����ҳ
		TabbedPane[2].addTab("��ͬ����", pane4);
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
		// JLabel JLab1=new JLabel("����");//����
		// JLab1.setFont(Font1);
		// box1V11.add(JLab1);
		// box1V11.add(Box.createVerticalStrut(5));
		box1H12 = Box.createHorizontalBox(); // {��̳���� ���ҿ� ���Ұ�ť}
		box1H12.add(Box.createHorizontalStrut(12));
		box1H12.add(new JLabel("��̳������"));
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
		// �ײ��ǩ
		textTabbedPane = new JTabbedPane();
		textTabbedPane.setFont(Font1);
		textTabbedPane.setForeground(Color.white);
		textTabbedPane.setBackground(new Color(100, 100, 200));
		textTabbedPane.addTab("�Ƽ�����", pane1);
		textTabbedPane.addTab("���ѻ���", TabbedPane[0]);
		textTabbedPane.addTab("ͼ�齻��", TabbedPane[1]);
		textTabbedPane.addTab("��ͬ����", TabbedPane[2]);
		textTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		setLayout(new java.awt.BorderLayout(5, 12));
		PANE.add(textTabbedPane, BorderLayout.CENTER);
		PANE.setVisible(true);
		add(PANE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void PostBarListWindow(int pages, int BlockID) {// ����Ŀ¼����
		sqlTopicType = "";
		sqlTopicTime = "";
		sqlTopicReorder = "";
		PostBarListpane[pages] = new JPanel(); // �������{NORTH�ö������ CENTER����Ŀ¼}
		PostBarListpane[pages].setBackground(new Color(100, 100, 200));
		PostBarListpane[pages].setLayout(new BorderLayout());
		JPanel postlistpaneH1 = new JPanel(); // �ö������{������ ���� ˮƽ����}
		postlistpaneH1.setBackground(new Color(100, 100, 200));
		postlistpaneH1.setLayout(new BorderLayout());
		Box postboxH1 = Box.createHorizontalBox(); // ˮƽ����{ҳ�� �ر�}
		newposts = new JBUTTON("������"); // ������
		newposts.setPreferredSize(new Dimension(158, 8));// �̶���С
		newposts.addActionListener(this);
		JLabel title = new JLabel("����"); // ����
		postlistpaneH1.add(newposts, BorderLayout.WEST);
		postlistpaneH1.add(title, BorderLayout.CENTER);
		buttonchoice[pages] = 1;
		JBUTTON Listconfirm = new JBUTTON("��ת"); // ȷ����ť
		Listconfirm.addActionListener(this); // ����ȷ����ť
		JBUTTON Refreshfirm = new JBUTTON("ˢ��"); // ȷ����ť
		Refreshfirm.addActionListener(this); // ����ȷ����ť
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
			String s = "��" + (i + 1) + "ҳ";
			Listorder[pages].addItem(s);
		}
		postboxH1.add(Listorder[pages]);
		postboxH1.add(Box.createHorizontalStrut(12));
		postboxH1.add(Listconfirm);
		postboxH1.add(Box.createHorizontalStrut(12));
		postboxH1.add(Refreshfirm);
		postboxH1.add(Box.createHorizontalStrut(12));
		close = new JBUTTON("�ر�");
		close.addActionListener(this);
		postboxH1.add(close);
		postboxH1.add(Box.createHorizontalStrut(5));
		postboxH2[pages] = Box.createHorizontalBox(); // ˮƽ����{���������б�}
		postboxH2[pages].add(new JLabel("���ͣ�"));
		JComboBox typechoice = new JComboBox(); // ���������б�
		typechoice.addItem("ȫ������");
		typechoice.addItem("��ͨ");
		typechoice.addItem("��Ʒ");
		typechoice.addItem("�Ƽ�");
		typechoice.addItem("����");
		typechoice.addItemListener(this);
		typechoice.setBackground(new Color(100, 100, 200));
		postboxH2[pages].add(typechoice);
		postboxH2[pages].add(new JLabel("ʱ�䣺"));
		JComboBox timechoice = new JComboBox(); // ʱ�������б�
		timechoice.addItem("ȫ��ʱ��");
		timechoice.addItem("һ��");
		timechoice.addItem("����");
		timechoice.addItem("һ��");
		timechoice.addItem("һ����");
		timechoice.addItem("������");
		timechoice.addItemListener(this);
		timechoice.setBackground(new Color(100, 100, 200));
		postboxH2[pages].add(timechoice);
		JComboBox sort = new JComboBox(); // ���������б�
		postboxH2[pages].add(new JLabel("����"));
		sort.addItem("Ĭ������");
		sort.addItem("��󷢱�");
		sort.addItem("����ʱ��");
		sort.addItem("�ظ�/�鿴");
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
	private void PostBarListCENWindow(int pages, int page, int BlockID) {// ����Ŀ¼�������
		PostBarListCEN[pages] = new JPanel(); // ��NORTH�з��봹ֱ����
		PostBarListCEN[pages].setBackground(new Color(100, 100, 200));
		PostBarListCEN[pages].setLayout(new BorderLayout());
		// postlistpaneCEN.add(postboxH2,BorderLayout.NORTH);
		Box PostBarListV1 = Box.createVerticalBox(); // ��ֱ����{����Ŀ¼}
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
				warning="�޸�������";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			warning="�޸�������";
		}
		// for(int i=20*page;i<20*page+20;i++){ //20����һҳ
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
			long Scan, long Reply) { // ���ӵ���Ŀ¼ģ��
		// Object type[]={"��ͨ","��Ʒ","�Ƽ�","����"}; //��ǩ����
		PostBarList[pages] = new JPanel(); // �������
		PostBarList[pages].setBackground(new Color(100, 100, 150));
		PostBarList[pages].setLayout(new BorderLayout());
		JLabel typelabel = new JLabel(Type); // ��ǩ
		JLabel username = new JLabel("�û�ID" + user_id); // �û���
		// AccessibleContext acc=new AccessibleContext("d");
		PostBarListtitle[pages][idTopic] = new JLabel(" " + title); // ����
		JLabel time = new JLabel("" + Createtime); // ����ʱ��
		JLabel Replyview1 = new JLabel("�ظ�" + Reply); // �ظ���
		JLabel Replyview2 = new JLabel("�鿴" + Scan); // �鿴��
		Replyview1.setPreferredSize(new Dimension(80, 8));
		Replyview2.setPreferredSize(new Dimension(80, 8));
		typelabel.setForeground(Color.YELLOW); // ��ɫ������
		PostBarListtitle[pages][idTopic].setFont(Font3);
		PostBarListtitle[pages][idTopic].setForeground(Color.ORANGE);
		PostBarListtitle[pages][idTopic].addMouseListener(this);
		username.setFont(Font4);
		username.setForeground(Color.white);
		time.setFont(Font4);
		time.setForeground(Color.white);
		JPanel PostBarListH1 = new JPanel(); // ���1{ˮƽ����1ˮƽ����2}
		PostBarListH1.setBackground(new Color(100, 100, 220));
		PostBarListH1.setLayout(new BorderLayout());
		JPanel PostBarListH2 = new JPanel(); // ���2{�û��� ����}
		PostBarListH2.setBackground(new Color(100, 100, 220));
		PostBarListH2.setLayout(new BorderLayout());
		Box PostBarListH01 = Box.createHorizontalBox(); // ˮƽ����1{��ǩ����}
		PostBarListH01.add(typelabel);
		PostBarListH01.add(PostBarListtitle[pages][idTopic]);
		Box PostBarListH02 = Box.createHorizontalBox(); // ˮƽ����2{�ظ��鿴}
		PostBarListH02.add(Replyview1);
		PostBarListH02.add(Box.createHorizontalStrut(8));
		PostBarListH02.add(Replyview2);
		PostBarListH1.add(PostBarListH01, BorderLayout.WEST);
		PostBarListH1.add(PostBarListH02, BorderLayout.EAST);
		PostBarListH2.add(username, BorderLayout.WEST);
		PostBarListH2.add(time, BorderLayout.EAST);
		Box PostBarListV1 = Box.createVerticalBox(); // ��ֱ����{���1���2}
		PostBarListV1.add(Box.createVerticalStrut(8));
		PostBarListV1.add(PostBarListH1);
		PostBarListV1.add(PostBarListH2);
		PostBarList[pages].add(PostBarListV1, BorderLayout.NORTH);
	}

	private void initPostWindow(int pages, int idTopic) { // ��̳����ҳ��ģ�壨������ӱ��⣩
		int items = 0;
		postpane[pages] = new JPanel(); // ������棨����壩
		postpane[pages].setBackground(new Color(100, 100, 200)); // ������ɫ
		postpane[pages].setLayout(new BorderLayout()); // ����
		JPanel postpaneH1 = new JPanel(); // �ö������{�û��� ���� ˮƽ����}
		postpaneH1.setBackground(new Color(100, 100, 200));
		postpaneH1.setLayout(new BorderLayout());
		Box postboxH1 = Box.createHorizontalBox(); // ˮƽ����{ҳ�� ���� �ر�}
		JLabel user = new JLabel("�û���"); // �û���
		user.setPreferredSize(new Dimension(158, 8));
		JLabel title = new JLabel("����" + pages); // ����
		Posting[idTopic] = new JBUTTON("����"); // ������ť
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
			String s = "��" + (i + 1) + "ҳ";
			Listorder[pages].addItem(s);
		}
		postboxH1.add(Listorder[pages]);
		JBUTTON Listconfirm = new JBUTTON("ת��"); // ת����ť
		Listconfirm.addActionListener(this); // ����ȷ����ť
		postboxH1.add(Box.createHorizontalStrut(12));
		postboxH1.add(Listconfirm);
		postboxH1.add(Box.createHorizontalStrut(12));
		close = new JBUTTON("�ر�"); // �رհ�ť
		close.addActionListener(this);
		postboxH1.add(Posting[idTopic]);
		postboxH1.add(Box.createHorizontalStrut(12));
		postboxH1.add(close);
		postboxH1.add(Box.createHorizontalStrut(5));
		postpaneH1.add(user, BorderLayout.WEST);
		postpaneH1.add(title, BorderLayout.CENTER);
		postpaneH1.add(postboxH1, BorderLayout.EAST);
		initPostCEN(pages, 0, idTopic);
		postpane[pages].add(postpaneH1, BorderLayout.NORTH); // �ö���������������
		postpane[pages].add(new JScrollPane(postpane01[pages]),
				BorderLayout.CENTER);
		setItems(idTopic);
	}

	private void initPostCEN(int pages, int page, int idTopic) { // �����б�
		postpane01[pages] = new JPanel();
		postpane01[pages].setLayout(new BorderLayout());
		postpane01[pages].setBackground(new Color(100, 90, 200));
		Box boxV1 = Box.createVerticalBox(); // ��ֱ����boxV1
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
					boxV1.add(postpaneinit[pages][idNote]); // boxV1�з�������ģ��
					boxV1.add(Box.createVerticalStrut(5));
				}while (rs.next()) ;
			} catch (SQLException e) {
				e.printStackTrace();
				warning="�޸�������";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			warning="�޸�������";
		}
		// for(int i=0+page*30;i<30+page*30;){ //����initPostΪ���ӱ�ţ����ͬʱΪ100�����ӱ��
		// }
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for(int i=0+items*30;i<30+items*30;i++){
		// //����initPostΪ���ӱ�ţ����ͬʱΪ100�����ӱ��
		// PostitemForm(pages,i);
		// boxV1.add(postpaneinit[pages][i]); //boxV1�з�������ģ�巵��initPost*100
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
			String icon) { // ����ģ�壨ÿһ������
		postpaneinit[pages][idNote] = new JPanel(); // ������壨����壩
		postpaneinit[pages][idNote].setLayout(new BorderLayout());
		postpaneinit01 = new JPanel(); // �����������SOUTH������Żظ���Ϣ����ջظ���Ϣ
		postpaneinit01.setBackground(new Color(100, 100, 0));
		postpaneinit01.setLayout(new BorderLayout());
		postpaneinit02 = new JPanel(); // �����������CENTER�������������ڲ����֣��ڲ���壩
		postpaneinit02.setBackground(new Color(100, 100, 0));
		postpaneinit02.setLayout(new BorderLayout());
		// postpaneinit02.setPreferredSize(new Dimension(930, 200));
		JPanel postpaneinitH1 = new JPanel(); // �ö������
		postpaneinitH1.setBackground(new Color(100, 100, 200));
		postpaneinitH1.setLayout(new BorderLayout());
		JPanel postpaneinitH2 = new JPanel(); // �����ڲ����SOUTH{�ײ㰴ť}
		postpaneinitH2.setBackground(new Color(100, 100, 160));
		JPanel postpaneinitH3 = new JPanel(); // �����ڲ����WEST{ͷ��}
		postpaneinitH3.setBackground(new Color(100, 100, 160));
		JPanel postpaneinitH4 = new JPanel(); // �����ڲ����CENTER{�ı���}
		postpaneinitH4.setBackground(new Color(100, 100, 160));
		JLabel username = new JLabel("�û���:" + user_name); // �û���
		username.setPreferredSize(new Dimension(130, 8));
		JLabel date1 = new JLabel("����" + Createtime);
		JLabel postnum = new JLabel("¥��" + Rank + "      ");// ¥��
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
		// "Picture/Button-1.gif"));// ͷ��
		portrait[myuserid].setPreferredSize(new Dimension(120, 160));
		// letter[myuserid] = new JBUTTON("˽��");
		// letter[myuserid].addActionListener(this);
		JLabel area = new JLabel(Content);
		area.setPreferredSize(new Dimension(800, 160));
		JScrollPane scroll = new JScrollPane(area); // �ı�����ӹ�����
		// JBUTTON thumbsup = new JBUTTON("����");
		comment[pages][idNote] = new JBUTTON("�ظ�");
		comment[pages][idNote].addActionListener(this);
		Openlist[pages][idNote] = new JBUTTON("�鿴�ظ�");
		Openlist[pages][idNote].addActionListener(this);
		postpaneinitH1.add(username, BorderLayout.WEST);
		postpaneinitH1.add(date1, BorderLayout.CENTER);
		postpaneinitH1.add(postnum, BorderLayout.EAST);
		Box postinitboxH2 = Box.createHorizontalBox(); // ��ŵײ㰴ť
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
		Box postpaneinitV3 = Box.createVerticalBox(); // ���ͷ��
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
			Date date0, String user_name) { // �ظ���ģ�壨����ظ��б�
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd��");
		String time0 = formatter.format(date0);
		BarComment[pages] = new JPanel(); // �������
		BarComment[pages].setBackground(new Color(100, 100, 200));
		BarComment[pages].setLayout(new BorderLayout());
		BarComment[pages].setSize(10, 80);
		JPanel postpaneinit012 = new JPanel(); // {�û��� ���� �ظ�}
		postpaneinit012.setBackground(new Color(100, 100, 150));
		postpaneinit012.setLayout(new BorderLayout());
		postpaneinit012.setPreferredSize(new Dimension(900, 25));
		JLabel username = new JLabel(user_name + ":"); // �û���
		username.setPreferredSize(new Dimension(80, 8));
		JLabel title = new JLabel("" + Content); // ����
		// Barreply[pages][Rank]=new JBUTTON("�ظ�"); //�ظ�
		// Barreply[pages][Rank].addActionListener(this);
		JLabel time1 = new JLabel("" + time0);
		postpaneinit012.add(username, BorderLayout.WEST);
		postpaneinit012.add(title, BorderLayout.CENTER);
		postpaneinit012.add(time1, BorderLayout.EAST);
		BarComment[pages].add(postpaneinit012, BorderLayout.NORTH);
	}

	private void PostreplyForm(int pages, int type, int ID) { // �ϻظ�ģ�壨�������/�ظ�/˽�ţ�
		Postreplytpane[pages] = new JPanel();
		Postreplytpane[pages].setBackground(new Color(100, 100, 200));
		Postreplytpane[pages].setLayout(new BorderLayout());
		JPanel PostreplytpaneN = new JPanel();
		PostreplytpaneN.setBackground(new Color(100, 100, 200));
		PostreplytpaneN.setLayout(new BorderLayout());
		// JLabel Forsome = new JLabel("�ظ���" + "����");
		reply[type][ID] = new JBUTTON("�ظ�");
		reply[type][ID].addActionListener(this);
		cancelreply[pages] = new JBUTTON("ȡ��");
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

	private void NewPostForm(int pages, int BlockID) { // ������ģ��
		NewPost = new JPanel();
		NewPost.setBackground(new Color(100, 100, 200));
		NewPost.setLayout(new BorderLayout());
		JPanel NewPostNorth = new JPanel(); // �����
		NewPostNorth.setBackground(new Color(100, 100, 200));
		NewPostNorth.setLayout(new BorderLayout());
		Object type1[] = { "", "", "", "ѡ���������", "ѡ���������", "ѡ���������", "���ѽ���",
				"ͯ�齻��", "�����־", "�����Ƽ�", "С˵����", "��������", "�����", "��ѧ�鼮����",
				"���Ĵ�ȫ", "������", "��ʷ���ǽ���", "��ͼ����", "д��ԭ��", "����ѧ����", "�Ӵ���̸",
				"�������", "��־��������", "������¼" };// �������
		title[pages] = new JTextField("��Ŀ"); // ��Ŀ
		published = new JBUTTON("����"); // ����ť
		published.addActionListener(this);
		JComboBox typechoice = new JComboBox(); // ���������б�
		for (int i = (pages - 1) / 10 - 4; i < 24; i = i + 3) {
			typechoice.addItem(type1[i]);
		}
		typechoice.addItemListener(this);
		Box NewPostH1 = Box.createHorizontalBox();
		// Box NewPostH2=Box.createHorizontalBox();
		// NewPostH1.add(new JLabel("������ࣺ"));
		// NewPostH1.add(typechoice);
		// NewPostH2.add(new JLabel("��Ŀ��"));
		// NewPostH2.add(title);
		closenew = new JBUTTON("�ر�"); // �رհ�ť
		closenew.addActionListener(this); // �����رհ�ť
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
			Box pane111box = Box.createVerticalBox(); // ��ֱ����{����Ŀ¼}
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
					warning="�޸�������";
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
					warning="�޸�������";
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
					warning="�޸�������";
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
		if (((JBUTTON) e.getSource()).getText().equals("����")) { // ��������
			// JOptionPane.showMessageDialog(this,"���Ѿ���һ������ҳ������","",JOptionPane.WARNING_MESSAGE
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
							+ userid + "','" + typechange + "','��ͨ','" + 0
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
					JOptionPane.showMessageDialog(this, "�ɹ�����", "��ʾ",
							JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (((JBUTTON) e.getSource()).getText().equals("�ر�")) { // �رյ�ǰ�򿪵�ҳ��
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
		if (((JBUTTON) e.getSource()).getText().equals("ˢ��")) { // ���ˢ��ˢ��ҳ��
			Pagedetection();
			for (int j = 0; j < maximum2; j++) {
				String s = "��" + (j + 1) + "ҳ";
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
		if (((JBUTTON) e.getSource()).getText().equals("��ת")) { // �����ת��תҳ��
			Pagedetection();
			for (int j = 0; j < maximum2; j++) {
				String s = "��" + (j + 1) + "ҳ";
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
		if (((JBUTTON) e.getSource()).getText().equals("ת��")) { // ���ת����תҳ��
			Pagedetection();
			for (int j = 0; j < maximum2; j++) {
				String s = "��" + (j + 1) + "ҳ";
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
		if (((JBUTTON) e.getSource()).getText().equals("����")) {
			for (int i = 0; i < maximum; i++) { // �򿪻������棨������
				if (e.getSource() == Posting[i]) {
					Pagedetection();
					generalcomment(ToolTip, 0, i);
				}
			}
		}
		if (((JBUTTON) e.getSource()).getText().equals("�ظ�")) {
			for (int i = 0; i < maximum2; i++) {
				for (int j = 0; j < maximum; j++) {
					if (e.getSource() == comment[i][j]) { // �򿪻ظ����棨���ӣ�
						generalcomment(i, 1, j);
					}
				}
			} 
			for (int i = 0; i < maximum; i++) { // �ϻظ�ģ��ظ���ť
				if (e.getSource() == reply[0][i]) { // �ظ�����
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
						JOptionPane.showMessageDialog(this, "�����ɹ���", "��ʾ",
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
				} else if (e.getSource() == reply[1][i]) { // �ظ�bar
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
						JOptionPane.showMessageDialog(this, "�ظ��ɹ�", "��ʾ",
								JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(init01);
				} else if (e.getSource() == reply[2][i]) { // �ظ�˽��

				}
			}
		}
		// for (int i = 0; i < maxid; i++) {
		// if (e.getSource() == letter[i]) { // �򿪻ظ����棨˽�ţ�
		// generalcomment(i, 2, 0);
		// }
		// }
		if (((JBUTTON) e.getSource()).getText().equals("ȡ��")) {			
			for (int i = 0; i < maximum2; i++) {
				if (e.getSource() == cancelreply[i]) { // ȡ���ظ�����
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
		if (((JBUTTON) e.getSource()).getText().equals("�鿴�ظ�")) {
			for (int i = 0; i < maximum2; i++) {
				for (int j = 0; j < maximum; j++) {
					if (e.getSource() == Openlist[i][j]) { // �򿪻ظ��б�
						if (judgeOpenlist == 0) { // ���ƴ򿪹ر��б�
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
							for (int barnum0 = 0; barnum0 < barnum; barnum0++) { // ����initPostΪ���ӱ�ţ����ͬʱΪ100�����ӱ��
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
								postpane[i].updateUI(); // ˢ�½���
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
		if (((JBUTTON) e.getSource()).getText().equals("������")) { // ������
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
						TabbedPane[bottm - 1].insertTab("������", null, NewPost,
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
				JOptionPane.showMessageDialog(this, "�Ѿ���һ��������ҳ����", "��ʾ",
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
			String s = "��" + (j + 1) + "ҳ";
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

	void panebuttonchoice(int i, int BlockID) { // �ν���ѡ��
		for (int i0 = BlockID; i0 < maximum2; i0 = i0 + 30) {
			if (buttonchoice[i0] == 0) {
				buttonchoice[i0] = 1;
				PostBarListWindow(i0, BlockID); // ����ҳ�롢������Ŀ
				String type1[]={"���ѽ���","�����Ƽ�","�����","������","д��ԭ��","�������"};		//�������
				String type2[]={"ͯ�齻��","С˵����","��ѧ�鼮����","��ʷ���ǽ���","����ѧ����","��־��������"};
				String type3[]={"�����־","��������","���Ĵ�ȫ","��ͼ����","�Ӵ���̸","������¼"};	
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
		TabbedPane[i].setSelectedIndex(1); // ����indexΪ1�����
	}

	void Pagedetection() { // ͨ��ҳ����
		bottm = textTabbedPane.getSelectedIndex(); // ȡ����Χҳ��index
		top = TabbedPane[textTabbedPane.getSelectedIndex() - 1]
				.getSelectedIndex(); // ȡ�õ�ǰҳ��index
		ToolTip = Integer.parseInt(TabbedPane[bottm - 1].getToolTipTextAt(top)); // ����������¼�λ��ȷ��������Ĺ�����ʾ�ı�
		// ҳ��page
	}

	void generalBarListchoice(int i, int pages, int idTopic) { // ͨ����������ѡ��----------------------------------
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
				/** ����ҳ�� ���� ͼƬ ��� ��ǩ��ҳ���ţ� λ�� */
				TabbedPane[i]
						.insertTab(tptitle, null, postpane[i0], "" + i0, 1);
				break;
			}
		}
		textTabbedPane.setSelectedIndex(i + 1); // ����indexΪi����壨����壩
		TabbedPane[i].setSelectedIndex(1); // ����indexΪ1����壨����壩
	}

	void generalcomment(int i, int type, int ID) { // ͨ�ûظ������� ˽�� �ظ��������ظ���
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
		Homepage Homepage = new Homepage("������̳");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		String item = e.getItem().toString();
		String type[] = { "ȫ������", "��ͨ", "��Ʒ", "�Ƽ�", "����", "ȫ��ʱ��", "һ��", "����",
				"һ��", "һ����", "������", "Ĭ������", "��󷢱�", "����ʱ��", "�ظ�/�鿴", }; // ��ǩ����
		String type02[] = { "ѡ���������", "ѡ���������", "ѡ���������", "���ѽ���", "ͯ�齻��",
				"�����־", "�����Ƽ�", "С˵����", "��������", "�����", "��ѧ�鼮����", "���Ĵ�ȫ", "������",
				"��ʷ���ǽ���", "��ͼ����", "д��ԭ��", "����ѧ����", "�Ӵ���̸", "�������", "��־��������",
				"������¼" };
		if (e.getStateChange() == ItemEvent.SELECTED) {
			for (int i = 0; i < 15; i++) {
				if (type[i].equals(item)) {
					if (i == 0) {
						sqlTopicType = "";
					} else if (i == 1) {
						sqlTopicType = " Type='��ͨ' AND ";
					} else if (i == 2) {
						sqlTopicType = " Type='��Ʒ' AND ";
					} else if (i == 3) {
						sqlTopicType = " Type='�Ƽ�' AND ";
					} else if (i == 4) {
						sqlTopicType = " Type='����' AND ";
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
						JOptionPane.showMessageDialog(this, warning, "��ʾ",
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