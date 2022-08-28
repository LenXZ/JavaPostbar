package PostBar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Edit3 extends JFrame implements ActionListener, ItemListener,
		MouseListener {
	private File file; // 当前文件
	private JTextArea textarea; // 文本区
	private JButton button_color; // 颜色
	private JScrollPane scroll; // 为文本编辑区提供滚动条
	private JDialog dialog1, dialog2, dialog3;
	private JLabel label_dialog, label_dialog2, label_dialog3;
	private Boolean via = false;
	private JPopupMenu popupmenu;
	private JCheckBoxMenuItem checkbox_bold, checkbox_italic; // 粗体、斜体复选框
	private String fileName = null;
	private JTextField text_size; // 字号文本行

	public Edit3() // 空文件的构造方法
	{
		super("文本编辑器");
		this.setSize(700, 500);
		this.setLocation(140, 140);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		textarea = new JTextArea("");
		textarea.addMouseListener(this);
		this.add(textarea);
		this.addMenu(); // 调用自定义方法，添加菜单
		this.setVisible(true);
		this.file = null; // 空文件对象
	}

	public Edit3(String filename) // 指定文件名的构造方法
	{
		this();
		if (filename != null) {
			this.file = new File(filename);
			this.setTitle(filename); // 将文件名添加在窗口标题栏上
			this.textarea.setText(this.readFromFile()); // 使用流读取指定文件中的字符串，并显示在文本区中
		}
	}

	public Edit3(File file) // 指定文件对象的构造方法
	{
		this();
		if (file != null) {
			this.file = file;
			this.setTitle(this.file.getName());
			this.textarea.setText(this.readFromFile());
		}
	}

	private void addMenu() // 添加主菜单
	{
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		// -----------------------------------------文件----------------------------------------------
		JMenu menu_file = new JMenu("文件");
		menubar.add(menu_file);
		JMenuItem menuitem_open = new JMenuItem("打开(O)");
		menuitem_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		menu_file.add(menuitem_open);
		menuitem_open.addActionListener(this);
		JMenuItem menuitem_save = new JMenuItem("保存(S)");
		menuitem_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		menu_file.add(menuitem_save);
		menuitem_save.addActionListener(this);
		JMenuItem menuitem_saveas = new JMenuItem("另存为(F12)");
		menuitem_saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12,
				InputEvent.CTRL_MASK));
		menu_file.add(menuitem_saveas);
		menuitem_saveas.addActionListener(this);
		menu_file.addSeparator();
		JMenuItem menuitem_exit = new JMenuItem("退出(X)");
		menuitem_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.ALT_MASK));
		menu_file.add(menuitem_exit);
		menuitem_exit.addActionListener(this);
		// -----------------------------------------编辑---------------------------------------------
		JMenu menu_edit = new JMenu("编辑");
		menubar.add(menu_edit);
		JMenuItem menuitem_cut = new JMenuItem("剪切(T)");
		menuitem_cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_cut);
		menuitem_cut.addActionListener(this);
		JMenuItem menuitem_copy = new JMenuItem("复制(C)");
		menuitem_copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_copy);
		menuitem_copy.addActionListener(this);
		JMenuItem menuitem_paste = new JMenuItem("粘贴(P)");
		menuitem_paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_paste);
		menuitem_paste.addActionListener(this);
		JMenuItem menuitem_insert = new JMenuItem("插入(I)");
		menuitem_insert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_insert);
		menuitem_insert.addActionListener(this);
		JMenuItem menuitem_delete = new JMenuItem("删除(L)");
		menuitem_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_delete);
		menuitem_delete.addActionListener(this);
		menu_edit.addSeparator();
		JMenuItem menuitem_find = new JMenuItem("查找(F)");
		menuitem_find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_find);
		menuitem_find.addActionListener(this);
		JMenuItem menuitem_replace = new JMenuItem("替换(R)");
		menuitem_replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_replace);
		menuitem_replace.addActionListener(this);
		// -----------------------------------------工具----------------------------------------------
		JMenu menu_tool = new JMenu("工具");
		menubar.add(menu_tool);
		JMenuItem menuitem_auto = new JMenuItem("自动换行(W)");
		menu_tool.add(menuitem_auto);
		menuitem_auto.addActionListener(this);
		JMenu menu_style = new JMenu("字形");
		checkbox_bold = new JCheckBoxMenuItem("粗体");
		checkbox_italic = new JCheckBoxMenuItem("斜体");
		menu_style.add(checkbox_bold); // 复选菜单项
		menu_style.add(checkbox_italic);
		menu_tool.add(menu_style); // 菜单加入到菜单中成为二级菜单
		checkbox_bold.addItemListener(this);
		checkbox_italic.addItemListener(this);
		JMenuItem menu_size = new JMenuItem("字号");
		menu_tool.add(menu_size);
		menu_size.addActionListener(this);
		JMenuItem menu_color = new JMenuItem("颜色");
		menu_tool.add(menu_color);
		menu_color.addActionListener(this);
		// -----------------------------------------帮助----------------------------------------------
		JMenu menu_help = new JMenu("帮助");
		menubar.add(menu_help);
		JMenuItem menu_look = new JMenuItem("查看帮助(L)");
		menu_help.add(menu_look);
		menu_look.addActionListener(this);
		JMenuItem menu_about = new JMenuItem("关于文本编辑器(A)");
		menu_help.add(menu_about);
		menu_about.addActionListener(this);
		dialog1 = new JDialog(this, "关于文本编辑器");
		dialog1.setSize(480, 150);
		label_dialog = new JLabel("-----------傻逼杨工作室制作---------", JLabel.CENTER);
		dialog1.add(label_dialog);
		dialog1.setDefaultCloseOperation(HIDE_ON_CLOSE);
		// ----------------------------------------------弹出式菜单对象--------------------------------------------------------
		popupmenu = new JPopupMenu();
		JMenuItem final_cut = new JMenuItem("剪切(T)");
		final_cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));// 设置快捷键Ctrl+X
		popupmenu.add(final_cut); // 加入剪切菜单项
		final_cut.addActionListener(this);
		JMenuItem final_copy = new JMenuItem("复制(C)");
		final_copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));// 设置快捷键Ctrl+C
		popupmenu.add(final_copy);
		final_copy.addActionListener(this);
		JMenuItem final_paste = new JMenuItem("粘贴(P)");
		final_paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_MASK));// 设置快捷键Ctrl+V
		popupmenu.add(final_paste);
		final_paste.addActionListener(this);
		JMenuItem final_insert = new JMenuItem("插入(I)");
		final_insert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK));// 设置快捷键Ctrl+V
		popupmenu.add(final_insert);
		final_insert.addActionListener(this);
		JMenuItem final_delete = new JMenuItem("删除(L)");
		final_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				InputEvent.CTRL_MASK));// 设置快捷键Ctrl+L
		popupmenu.add(final_delete);
		final_delete.addActionListener(this);
		textarea.add(popupmenu);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		scroll = new JScrollPane(textarea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		container.add(scroll, BorderLayout.CENTER);
	}

	public String readFromFile() // 使用流从指定文本文件中读取字符串
	{
		try {
			FileReader fin = new FileReader(this.file);
			BufferedReader bin = new BufferedReader(fin);
			String aline = "", lines = "";
			do {
				aline = bin.readLine(); // 读取一行字符串，输入流结束时返回null
				if (aline != null)
					lines += aline + "\r\n";
			} while (aline != null);
			bin.close();
			fin.close();
			return lines;
		} catch (IOException ioex) {
			return null;
		}
	}

	public boolean openDialog() // 执行打开文件对话框
	{ // 在打开文件对话框中，单击【打开】按钮时返回true，单击【取消】按钮时返回false
		FileDialog filedialog = new FileDialog(this, "打开", FileDialog.LOAD); // 创建打开文件对话框
		filedialog.setVisible(true); // 显示打开文件对话框
		if ((filedialog.getDirectory() != null)
				&& (filedialog.getFile() != null)) // 单击【打开】按钮时
		{
			this.file = new File(filedialog.getDirectory(), filedialog
					.getFile());
			return true;
		} else
			return false;
		// 单击【取消】按钮时
	}

	public void fileSaveAs() {
		JFileChooser jFileChooser = new JFileChooser();
		if (JFileChooser.APPROVE_OPTION == jFileChooser.showSaveDialog(this)) {
			fileName = (jFileChooser.getSelectedFile().getPath() + ".txt");
			fileSave();
		}
	}

	public void fileSave() {
		if (fileName == null) {
			fileSaveAs();
		} else {
			if (!via) {
				if (fileName.length() != 0) {
					try {
						File saveFile = new File(fileName);
						FileWriter fw = new FileWriter(saveFile);
						fw.write(textarea.getText());
						fw.close();
						via = true;
						this.setTitle(fileName.substring(fileName
								.lastIndexOf("\\") + 1));
						this.repaint();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, "保存文件时出错", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					fileSaveAs();
				}
			}
		}
	}

	public void chooseColor() {
		Color bcolor = textarea.getForeground();
		JColorChooser jColor = new JColorChooser();
		jColor.setColor(bcolor);
		textarea.setForeground(JColorChooser.showDialog(textarea, "选择颜色",
				bcolor));
	}

	public void actionPerformed(ActionEvent e) // 单击事件处理程序
	{
		if (e.getActionCommand() == "打开(O)") // 单击菜单项时
		{
			if (this.openDialog()) // 打开文件对话框并单击【打开】按钮时执行，单击【取消】按钮时不执行
			{
				this.setTitle(this.file.getName());
				this.textarea.setText(this.readFromFile());
			}
		}
		if (e.getActionCommand() == "保存(S)" && this.file != null) // 非第1次保存时，只保存不需要打开保存文件对话框
		{
			this.fileSave();
		}
		if (e.getActionCommand() == "保存(S)" && this.file == null
				|| e.getActionCommand() == "另存为(F12)") { // 第1次保存或执行"另存为"菜单时，需要打开保存文件对话框
			this.fileSaveAs();
		}
		if (e.getActionCommand() == "退出(X)") // 不能用switch(int)语句
			System.exit(0); // 单击菜单项时结束程序
		if (e.getActionCommand() == "剪切(T)")
			textarea.cut();
		// 将选中文本剪切送系统剪贴板
		if (e.getActionCommand() == "复制(C)")
			textarea.copy();
		if (e.getActionCommand() == "粘贴(P)")
			textarea.paste();
		if (e.getActionCommand() == "插入(I)") {
			JPanel insertPanel = new JPanel();
			JLabel contentLabel = new JLabel("要插入的内容");
			JTextField contentText = new JTextField(10);
			JLabel locationLabel = new JLabel("插入位置：");
			JTextField locationText = new JTextField(10);
			insertPanel.add(contentLabel);
			insertPanel.add(contentText);
			insertPanel.add(locationLabel);
			insertPanel.add(locationText);
			JOptionPane.showMessageDialog(null, insertPanel);
			int i = Integer.parseInt(locationText.getText());
			textarea.insert(contentText.getText(), i);
		}
		if (e.getActionCommand() == "删除(L)") {
			textarea.replaceRange("", textarea.getSelectionStart(), textarea
					.getSelectionEnd());
			via = false;
		}
		if (e.getActionCommand() == "查找(F)") {
			JPanel findPanel = new JPanel();
			JLabel findLabel = new JLabel("要查找的内容");
			JTextField inputText = new JTextField(10);
			findPanel.add(findLabel);
			findPanel.add(inputText);
			Font font = textarea.getFont();
			int style = font.getStyle();
			style = style ^ 1;
			findPanel.setFont(new Font(font.getName(), style, font.getSize()));
			JOptionPane.showMessageDialog(null, findPanel);
		}
		if (e.getActionCommand() == "替换(R)") {
			JPanel replacePanel = new JPanel();
			JLabel lookupLabel = new JLabel("要替换的内容");
			JTextField inputText = new JTextField(10);
			JLabel replaceLabel = new JLabel("替换为：");
			JTextField changetoText = new JTextField(10);
			replacePanel.add(lookupLabel);
			replacePanel.add(inputText);
			replacePanel.add(replaceLabel);
			replacePanel.add(changetoText);
			JOptionPane.showMessageDialog(null, replacePanel);
			String text = textarea.getText(); // 获得整个文本内容
			String changeText = text.replace(inputText.getText(), changetoText
					.getText());// 获得替换后的内容
			textarea.setText(changeText);
		}
		if (e.getActionCommand() == "自动换行(W)") {
			if (via)
				via = false;
			else
				via = true;
			textarea.setLineWrap(via);
		}
		if (e.getActionCommand() == "字号") {
			JPanel sizepanel = new JPanel();
			JLabel setsize = new JLabel("请输入字号");
			sizepanel.add(setsize);
			text_size = new JTextField("20", 10);
			sizepanel.add(text_size);
			text_size.addActionListener(this);
			JOptionPane.showMessageDialog(null, sizepanel);
			int size = 0;
			try {
				size = Integer.parseInt(text_size.getText());
				if (size <= 0 || size > 96)
					throw new Exception("SizeException");
				java.awt.Font font = textarea.getFont();
				textarea
						.setFont(new Font(font.getName(), font.getStyle(), size));
			} catch (NumberFormatException nfe) {
				dialog2 = new JDialog(this, "提示");
				label_dialog2 = new JLabel("\"" + text_size.getText()
						+ "\" 不能转换成整数，请重新输入!");
				dialog2.add(label_dialog2);
				dialog2.setLocation(this.getX() + 100, this.getY() + 100);
				dialog2.setSize(250, 80);
				dialog2.setLayout(new java.awt.FlowLayout(FlowLayout.CENTER));
				dialog2.setVisible(true);
				dialog2.setDefaultCloseOperation(HIDE_ON_CLOSE);
			} catch (Exception ex) {
				if (ex.getMessage() == "SizeException") // 捕获自己抛出的异常对象
				{
					dialog3 = new JDialog(this, "提示");
					label_dialog3 = new JLabel(size + " 字号不合适，请重新输入!");
					dialog3.add(label_dialog3);
					dialog3.setLocation(this.getX() + 100, this.getY() + 100);
					dialog3.setSize(250, 80);
					dialog3
							.setLayout(new java.awt.FlowLayout(
									FlowLayout.CENTER));
					dialog3.setVisible(true);
					dialog3.setDefaultCloseOperation(HIDE_ON_CLOSE);
				}
			} finally {
			}
		}
		if (e.getActionCommand() == "颜色") {
			chooseColor();
		}
		if (e.getActionCommand() == "查看帮助(L)") {
			Edit3 h = new Edit3("帮助文档.txt");
			h.setSize(400, 350);
		}
		if (e.getActionCommand() == "关于文本编辑器(A)") {
			dialog1.setLocation(250, 330);
			dialog1.setVisible(true);
		}
	}

	public void itemStateChanged(ItemEvent e) // 复选框选择事件处理程序
	{ // 实现ItemListener接口中的方法
		Font font = textarea.getFont();
		int style = font.getStyle();
		if (e.getSource() == checkbox_bold)
			style = style ^ 1; // 整数的位运算，异或^
		if (e.getSource() == checkbox_italic)
			style = style ^ 2;
		textarea.setFont(new Font(font.getName(), style, font.getSize()));
	}

	public void mouseClicked(MouseEvent mec) // 单击鼠标时触发
	{ // 实现MouseListener接口中的方法
		if (mec.getModifiers() == mec.BUTTON3_MASK) // 单击的是鼠标右键
			popupmenu.show(textarea, mec.getX(), mec.getY()); // 在鼠标单击处显示快捷菜单
	}

	public void mousePressed(MouseEvent mep) {
	}

	public void mouseReleased(MouseEvent mer) {
	}

	public void mouseEntered(MouseEvent mee) {
	}

	public void mouseExited(MouseEvent mex) {
	}

	public void mouseDragged(MouseEvent med) {
	}

	public static void main(String arg[]) {
		new Edit3();
	}
}