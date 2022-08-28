package PostBar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Edit3 extends JFrame implements ActionListener, ItemListener,
		MouseListener {
	private File file; // ��ǰ�ļ�
	private JTextArea textarea; // �ı���
	private JButton button_color; // ��ɫ
	private JScrollPane scroll; // Ϊ�ı��༭���ṩ������
	private JDialog dialog1, dialog2, dialog3;
	private JLabel label_dialog, label_dialog2, label_dialog3;
	private Boolean via = false;
	private JPopupMenu popupmenu;
	private JCheckBoxMenuItem checkbox_bold, checkbox_italic; // ���塢б�帴ѡ��
	private String fileName = null;
	private JTextField text_size; // �ֺ��ı���

	public Edit3() // ���ļ��Ĺ��췽��
	{
		super("�ı��༭��");
		this.setSize(700, 500);
		this.setLocation(140, 140);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		textarea = new JTextArea("");
		textarea.addMouseListener(this);
		this.add(textarea);
		this.addMenu(); // �����Զ��巽������Ӳ˵�
		this.setVisible(true);
		this.file = null; // ���ļ�����
	}

	public Edit3(String filename) // ָ���ļ����Ĺ��췽��
	{
		this();
		if (filename != null) {
			this.file = new File(filename);
			this.setTitle(filename); // ���ļ�������ڴ��ڱ�������
			this.textarea.setText(this.readFromFile()); // ʹ������ȡָ���ļ��е��ַ���������ʾ���ı�����
		}
	}

	public Edit3(File file) // ָ���ļ�����Ĺ��췽��
	{
		this();
		if (file != null) {
			this.file = file;
			this.setTitle(this.file.getName());
			this.textarea.setText(this.readFromFile());
		}
	}

	private void addMenu() // ������˵�
	{
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		// -----------------------------------------�ļ�----------------------------------------------
		JMenu menu_file = new JMenu("�ļ�");
		menubar.add(menu_file);
		JMenuItem menuitem_open = new JMenuItem("��(O)");
		menuitem_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		menu_file.add(menuitem_open);
		menuitem_open.addActionListener(this);
		JMenuItem menuitem_save = new JMenuItem("����(S)");
		menuitem_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		menu_file.add(menuitem_save);
		menuitem_save.addActionListener(this);
		JMenuItem menuitem_saveas = new JMenuItem("���Ϊ(F12)");
		menuitem_saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12,
				InputEvent.CTRL_MASK));
		menu_file.add(menuitem_saveas);
		menuitem_saveas.addActionListener(this);
		menu_file.addSeparator();
		JMenuItem menuitem_exit = new JMenuItem("�˳�(X)");
		menuitem_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.ALT_MASK));
		menu_file.add(menuitem_exit);
		menuitem_exit.addActionListener(this);
		// -----------------------------------------�༭---------------------------------------------
		JMenu menu_edit = new JMenu("�༭");
		menubar.add(menu_edit);
		JMenuItem menuitem_cut = new JMenuItem("����(T)");
		menuitem_cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_cut);
		menuitem_cut.addActionListener(this);
		JMenuItem menuitem_copy = new JMenuItem("����(C)");
		menuitem_copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_copy);
		menuitem_copy.addActionListener(this);
		JMenuItem menuitem_paste = new JMenuItem("ճ��(P)");
		menuitem_paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_paste);
		menuitem_paste.addActionListener(this);
		JMenuItem menuitem_insert = new JMenuItem("����(I)");
		menuitem_insert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_insert);
		menuitem_insert.addActionListener(this);
		JMenuItem menuitem_delete = new JMenuItem("ɾ��(L)");
		menuitem_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_delete);
		menuitem_delete.addActionListener(this);
		menu_edit.addSeparator();
		JMenuItem menuitem_find = new JMenuItem("����(F)");
		menuitem_find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_find);
		menuitem_find.addActionListener(this);
		JMenuItem menuitem_replace = new JMenuItem("�滻(R)");
		menuitem_replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				InputEvent.CTRL_MASK));
		menu_edit.add(menuitem_replace);
		menuitem_replace.addActionListener(this);
		// -----------------------------------------����----------------------------------------------
		JMenu menu_tool = new JMenu("����");
		menubar.add(menu_tool);
		JMenuItem menuitem_auto = new JMenuItem("�Զ�����(W)");
		menu_tool.add(menuitem_auto);
		menuitem_auto.addActionListener(this);
		JMenu menu_style = new JMenu("����");
		checkbox_bold = new JCheckBoxMenuItem("����");
		checkbox_italic = new JCheckBoxMenuItem("б��");
		menu_style.add(checkbox_bold); // ��ѡ�˵���
		menu_style.add(checkbox_italic);
		menu_tool.add(menu_style); // �˵����뵽�˵��г�Ϊ�����˵�
		checkbox_bold.addItemListener(this);
		checkbox_italic.addItemListener(this);
		JMenuItem menu_size = new JMenuItem("�ֺ�");
		menu_tool.add(menu_size);
		menu_size.addActionListener(this);
		JMenuItem menu_color = new JMenuItem("��ɫ");
		menu_tool.add(menu_color);
		menu_color.addActionListener(this);
		// -----------------------------------------����----------------------------------------------
		JMenu menu_help = new JMenu("����");
		menubar.add(menu_help);
		JMenuItem menu_look = new JMenuItem("�鿴����(L)");
		menu_help.add(menu_look);
		menu_look.addActionListener(this);
		JMenuItem menu_about = new JMenuItem("�����ı��༭��(A)");
		menu_help.add(menu_about);
		menu_about.addActionListener(this);
		dialog1 = new JDialog(this, "�����ı��༭��");
		dialog1.setSize(480, 150);
		label_dialog = new JLabel("-----------ɵ�����������---------", JLabel.CENTER);
		dialog1.add(label_dialog);
		dialog1.setDefaultCloseOperation(HIDE_ON_CLOSE);
		// ----------------------------------------------����ʽ�˵�����--------------------------------------------------------
		popupmenu = new JPopupMenu();
		JMenuItem final_cut = new JMenuItem("����(T)");
		final_cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));// ���ÿ�ݼ�Ctrl+X
		popupmenu.add(final_cut); // ������в˵���
		final_cut.addActionListener(this);
		JMenuItem final_copy = new JMenuItem("����(C)");
		final_copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));// ���ÿ�ݼ�Ctrl+C
		popupmenu.add(final_copy);
		final_copy.addActionListener(this);
		JMenuItem final_paste = new JMenuItem("ճ��(P)");
		final_paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_MASK));// ���ÿ�ݼ�Ctrl+V
		popupmenu.add(final_paste);
		final_paste.addActionListener(this);
		JMenuItem final_insert = new JMenuItem("����(I)");
		final_insert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK));// ���ÿ�ݼ�Ctrl+V
		popupmenu.add(final_insert);
		final_insert.addActionListener(this);
		JMenuItem final_delete = new JMenuItem("ɾ��(L)");
		final_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				InputEvent.CTRL_MASK));// ���ÿ�ݼ�Ctrl+L
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

	public String readFromFile() // ʹ������ָ���ı��ļ��ж�ȡ�ַ���
	{
		try {
			FileReader fin = new FileReader(this.file);
			BufferedReader bin = new BufferedReader(fin);
			String aline = "", lines = "";
			do {
				aline = bin.readLine(); // ��ȡһ���ַ���������������ʱ����null
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

	public boolean openDialog() // ִ�д��ļ��Ի���
	{ // �ڴ��ļ��Ի����У��������򿪡���ťʱ����true��������ȡ������ťʱ����false
		FileDialog filedialog = new FileDialog(this, "��", FileDialog.LOAD); // �������ļ��Ի���
		filedialog.setVisible(true); // ��ʾ���ļ��Ի���
		if ((filedialog.getDirectory() != null)
				&& (filedialog.getFile() != null)) // �������򿪡���ťʱ
		{
			this.file = new File(filedialog.getDirectory(), filedialog
					.getFile());
			return true;
		} else
			return false;
		// ������ȡ������ťʱ
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
						JOptionPane.showMessageDialog(this, "�����ļ�ʱ����", "����",
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
		textarea.setForeground(JColorChooser.showDialog(textarea, "ѡ����ɫ",
				bcolor));
	}

	public void actionPerformed(ActionEvent e) // �����¼��������
	{
		if (e.getActionCommand() == "��(O)") // �����˵���ʱ
		{
			if (this.openDialog()) // ���ļ��Ի��򲢵������򿪡���ťʱִ�У�������ȡ������ťʱ��ִ��
			{
				this.setTitle(this.file.getName());
				this.textarea.setText(this.readFromFile());
			}
		}
		if (e.getActionCommand() == "����(S)" && this.file != null) // �ǵ�1�α���ʱ��ֻ���治��Ҫ�򿪱����ļ��Ի���
		{
			this.fileSave();
		}
		if (e.getActionCommand() == "����(S)" && this.file == null
				|| e.getActionCommand() == "���Ϊ(F12)") { // ��1�α����ִ��"���Ϊ"�˵�ʱ����Ҫ�򿪱����ļ��Ի���
			this.fileSaveAs();
		}
		if (e.getActionCommand() == "�˳�(X)") // ������switch(int)���
			System.exit(0); // �����˵���ʱ��������
		if (e.getActionCommand() == "����(T)")
			textarea.cut();
		// ��ѡ���ı�������ϵͳ������
		if (e.getActionCommand() == "����(C)")
			textarea.copy();
		if (e.getActionCommand() == "ճ��(P)")
			textarea.paste();
		if (e.getActionCommand() == "����(I)") {
			JPanel insertPanel = new JPanel();
			JLabel contentLabel = new JLabel("Ҫ���������");
			JTextField contentText = new JTextField(10);
			JLabel locationLabel = new JLabel("����λ�ã�");
			JTextField locationText = new JTextField(10);
			insertPanel.add(contentLabel);
			insertPanel.add(contentText);
			insertPanel.add(locationLabel);
			insertPanel.add(locationText);
			JOptionPane.showMessageDialog(null, insertPanel);
			int i = Integer.parseInt(locationText.getText());
			textarea.insert(contentText.getText(), i);
		}
		if (e.getActionCommand() == "ɾ��(L)") {
			textarea.replaceRange("", textarea.getSelectionStart(), textarea
					.getSelectionEnd());
			via = false;
		}
		if (e.getActionCommand() == "����(F)") {
			JPanel findPanel = new JPanel();
			JLabel findLabel = new JLabel("Ҫ���ҵ�����");
			JTextField inputText = new JTextField(10);
			findPanel.add(findLabel);
			findPanel.add(inputText);
			Font font = textarea.getFont();
			int style = font.getStyle();
			style = style ^ 1;
			findPanel.setFont(new Font(font.getName(), style, font.getSize()));
			JOptionPane.showMessageDialog(null, findPanel);
		}
		if (e.getActionCommand() == "�滻(R)") {
			JPanel replacePanel = new JPanel();
			JLabel lookupLabel = new JLabel("Ҫ�滻������");
			JTextField inputText = new JTextField(10);
			JLabel replaceLabel = new JLabel("�滻Ϊ��");
			JTextField changetoText = new JTextField(10);
			replacePanel.add(lookupLabel);
			replacePanel.add(inputText);
			replacePanel.add(replaceLabel);
			replacePanel.add(changetoText);
			JOptionPane.showMessageDialog(null, replacePanel);
			String text = textarea.getText(); // ��������ı�����
			String changeText = text.replace(inputText.getText(), changetoText
					.getText());// ����滻�������
			textarea.setText(changeText);
		}
		if (e.getActionCommand() == "�Զ�����(W)") {
			if (via)
				via = false;
			else
				via = true;
			textarea.setLineWrap(via);
		}
		if (e.getActionCommand() == "�ֺ�") {
			JPanel sizepanel = new JPanel();
			JLabel setsize = new JLabel("�������ֺ�");
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
				dialog2 = new JDialog(this, "��ʾ");
				label_dialog2 = new JLabel("\"" + text_size.getText()
						+ "\" ����ת��������������������!");
				dialog2.add(label_dialog2);
				dialog2.setLocation(this.getX() + 100, this.getY() + 100);
				dialog2.setSize(250, 80);
				dialog2.setLayout(new java.awt.FlowLayout(FlowLayout.CENTER));
				dialog2.setVisible(true);
				dialog2.setDefaultCloseOperation(HIDE_ON_CLOSE);
			} catch (Exception ex) {
				if (ex.getMessage() == "SizeException") // �����Լ��׳����쳣����
				{
					dialog3 = new JDialog(this, "��ʾ");
					label_dialog3 = new JLabel(size + " �ֺŲ����ʣ�����������!");
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
		if (e.getActionCommand() == "��ɫ") {
			chooseColor();
		}
		if (e.getActionCommand() == "�鿴����(L)") {
			Edit3 h = new Edit3("�����ĵ�.txt");
			h.setSize(400, 350);
		}
		if (e.getActionCommand() == "�����ı��༭��(A)") {
			dialog1.setLocation(250, 330);
			dialog1.setVisible(true);
		}
	}

	public void itemStateChanged(ItemEvent e) // ��ѡ��ѡ���¼��������
	{ // ʵ��ItemListener�ӿ��еķ���
		Font font = textarea.getFont();
		int style = font.getStyle();
		if (e.getSource() == checkbox_bold)
			style = style ^ 1; // ������λ���㣬���^
		if (e.getSource() == checkbox_italic)
			style = style ^ 2;
		textarea.setFont(new Font(font.getName(), style, font.getSize()));
	}

	public void mouseClicked(MouseEvent mec) // �������ʱ����
	{ // ʵ��MouseListener�ӿ��еķ���
		if (mec.getModifiers() == mec.BUTTON3_MASK) // ������������Ҽ�
			popupmenu.show(textarea, mec.getX(), mec.getY()); // ����굥������ʾ��ݲ˵�
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