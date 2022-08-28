package PostBar;

import java.applet.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JBUTTON extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int round = 20;
	private int temp = 1;
	private Color quit = new Color(200, 200, 200);
	File musicClicked=new File("audio/Clicked.wav");
	File musicEntered=new File("audio/Entered.wav");
	URI uriClicked=musicClicked.toURI();
	URI uriEntered=musicEntered.toURI();
	URL urlClicked,urlEntered;
	AudioClip clip;
	// new Color(50, 100, 200);// 离开时颜色

	public JBUTTON() {
		super();
	}

	public JBUTTON(ImageIcon ico) {
		super(ico);
	}

	public JBUTTON(String s) {
		super(s);
		this.addMouseListener(this);
		setContentAreaFilled(false);// 是否显示外围矩形区域 选否
	}

	/** 设置按钮圆角大小 **/
	public void setRound(int round) {
		this.round = round;
	}

	@Override
	public void setBackground(Color bg) {
		this.quit = bg;
		super.setBackground(bg);
	}

	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setColor(quit);
		// 填充圆角矩形区域 也可以为其它的图形
		g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, round,
				round);
		// 必须放在最后 否则画不出来
		super.paintComponent(g);
	}

	public void paintBorder(Graphics g) {
		// 画边界区域
		g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, round,
				round);
		super.paintComponent(g);
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			urlClicked = uriClicked.toURL();
			clip=Applet.newAudioClip(urlClicked);
			clip.play();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setBackground(new Color(50, 200, 130));
		try {
			urlEntered=uriEntered.toURL();
			clip=Applet.newAudioClip(urlEntered);
			clip.play();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setBackground(new Color(200, 200, 200));
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setBackground(new Color(50, 200, 200));
		this.repaint();

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setBackground(new Color(200, 200, 200));
		this.repaint();
	}
}
