package Window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;   
import java.awt.Toolkit;
import javax.swing.JPanel;
/**
 * 功能： 背景填充工具类
 * 
 * **/

@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	String tupian;
	public MyPanel(String str){//图片地址
		tupian=str;
	}
	public void paintComponent(Graphics g) {
	 	Graphics2D g2 = (Graphics2D)g;
	 	super.paintComponent(g);
		Image img = Toolkit.getDefaultToolkit().getImage(tupian);
	 	g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);	
	}
 }
