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
	// new Color(50, 100, 200);// �뿪ʱ��ɫ

	public JBUTTON() {
		super();
	}

	public JBUTTON(ImageIcon ico) {
		super(ico);
	}

	public JBUTTON(String s) {
		super(s);
		this.addMouseListener(this);
		setContentAreaFilled(false);// �Ƿ���ʾ��Χ�������� ѡ��
	}

	/** ���ð�ťԲ�Ǵ�С **/
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
		// ���Բ�Ǿ������� Ҳ����Ϊ������ͼ��
		g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, round,
				round);
		// ���������� ���򻭲�����
		super.paintComponent(g);
	}

	public void paintBorder(Graphics g) {
		// ���߽�����
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
