package Window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Marking.AddPoint;
import Marking.AutoMarking;
import Marking.ChaperChoice;
import Marking.TeacherMarking;

@SuppressWarnings("serial")
public class TeacherMarkingWin extends JFrame implements ActionListener {
	AddPoint addPoint = new AddPoint();
	AutoMarking automarking = new AutoMarking();
	boolean Fibo = false, Exbo = false, Sibo = false;
	ChaperChoice chaperChoice;
	int Finum, Exnum, Sinum, choice = 1, st = 2, stId[] = new int[500], stIdn,
			paId, tcid, tixing;
	JButton add, previous, next, sure;
	JPanel PAN[] = new JPanel[4], PANE, PSouth;
	String s11, s12, s21, s22, s31, s32, tcnm, TiX = "PoFisumP",stuna[]=new String[500];
	TeacherMarking teachermarking = new TeacherMarking();

	public TeacherMarkingWin(String s11, String s12, String s21, String s22,
			String s31, String s32, int FiNum, int ExNum, int SiNum,
			int[] stId,String stuna[], int stIdn, int paId, int tcid, String tcnm, int tixing) {
		/** 填空|解释|简答|学生ID列表|学生列表位置|卷ID */
		setTitle("批阅界面");
		setBounds(0, 0, 1400, 800);
		setLayout(new BorderLayout());
		this.stId = stId;
		this.paId = paId;
		this.tcid = tcid;
		this.tcnm = tcnm;
		this.tixing = tixing;
		this.stuna=stuna;
		st = stId[stIdn];
		stIdn++;
		this.stIdn = stIdn;
		Finum = FiNum;
		Exnum = ExNum;
		Sinum = SiNum;
		this.s11 = s11;
		this.s12 = s12;
		this.s21 = s21;
		this.s22 = s22;
		this.s31 = s31;
		this.s32 = s32;
		add = new JButton("合计分数");
		previous = new JButton("上一页");
		next = new JButton("下一页");
		sure = new JButton("结束阅卷");
		add.addActionListener(this);
		previous.addActionListener(this);
		next.addActionListener(this);
		sure.addActionListener(this);
		for (int i = 0; i < 4; i++) {
			PAN[i] = new JPanel();
			PAN[i].setLayout(new BorderLayout());
		}
		PANE = new JPanel();
		PANE.setLayout(new BorderLayout());
		PSouth = new JPanel();
		PSouth.setLayout(new BorderLayout());
		teachermarking.setID(TiX, st, paId);
		teachermarking.insertstP(paId, st);
		teachermarking.ChaperChoice(1, 1);
		chaperChoice = new ChaperChoice(paId, st);
		addPoint.Addpoint(automarking.ChoiceMarking(chaperChoice.getChoiceA(),
				chaperChoice.getChoiceSA(), st, paId), "PoChAll", "paChNumfs",
				st, paId);
		addPoint.Addpoint(automarking.JudgeMarking(chaperChoice.getJudgeA(),
				chaperChoice.getJudgeSA(), st, paId), "PoJuAll", "paJuNumfs",
				st, paId);
		if (tixing == 0) {
			FiWin(s11, s12, Finum);
		} else if (tixing == 1) {
			ExWin(s21, s22, Exnum);
		} else {
			SiWin(s31, s32, Sinum);
		}
		Box boxHdown = Box.createHorizontalBox();
		boxHdown.add(previous);
		boxHdown.add(next);
		boxHdown.add(sure);
		PSouth.add(boxHdown, BorderLayout.WEST);
		PSouth.add(new JLabel(" 学生：" + st+"号 "+stuna[stIdn]+"       "), BorderLayout.EAST);
		add(PSouth, BorderLayout.SOUTH);
		add(PANE, BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void FiWin(String s1, String s2, int Num) {
		Fibo = true;
		try {
			PANE.removeAll();
		} catch (Exception e) {

		}
		TiX = "PoFisumP";
		teachermarking.setID(TiX, st, paId);
		JPanel panFiNum[] = new JPanel[Num];
		JPanel panFiInNum[] = new JPanel[Num];
		for (int i = 0; i < Num; i++) {
			panFiNum[i] = new JPanel();
		}
		for (int i = 0; i < Num; i++) {
			panFiInNum[i] = new JPanel();
		}
		teachermarking.FiChaperWin(panFiInNum);
		teachermarking.FiAnswer(s1, panFiNum, panFiInNum);
		teachermarking.StAnswer(s2, panFiNum);
		Box boxVSi = Box.createVerticalBox();
		for (int i = 0; i < Num; i++) {
			boxVSi.add(new JScrollPane(panFiNum[i]));
		}
		PAN[0].add(new JLabel("填空题"), BorderLayout.NORTH);
		PAN[0].add(new JScrollPane(boxVSi), BorderLayout.CENTER);
		// Box boxHdown=Box.createHorizontalBox();
		// boxHdown.add(next);
		// PAN[0].add(boxHdown,BorderLayout.SOUTH);
		PANE.add(PAN[0], BorderLayout.CENTER);
		PANE.updateUI();
	}

	private void ExWin(String s1, String s2, int Num) {
		Exbo = true;
		try {
			PANE.removeAll();
		} catch (Exception e) {

		}
		TiX = "PoExsumP";
		teachermarking.setID(TiX, st, paId);
		JPanel panExNum[] = new JPanel[Num];
		JPanel panExInNum[] = new JPanel[Num];
		for (int i = 0; i < Num; i++) {
			panExNum[i] = new JPanel();
		}
		for (int i = 0; i < Num; i++) {
			panExInNum[i] = new JPanel();
		}
		teachermarking.ExChaperWin(panExInNum);
		teachermarking.Answer(s1, panExNum, panExInNum);
		teachermarking.StAnswer(s2, panExNum);
		Box boxVSi = Box.createVerticalBox();
		for (int i = 0; i < Num; i++) {
			boxVSi.add(new JScrollPane(panExNum[i]));
		}
		PAN[1].add(new JLabel("名词解释"), BorderLayout.NORTH);
		PAN[1].add(new JScrollPane(boxVSi), BorderLayout.CENTER);
		// Box boxHdown=Box.createHorizontalBox();
		// boxHdown.add(next);
		// PAN[1].add(boxHdown,BorderLayout.SOUTH);
		PANE.add(PAN[1], BorderLayout.CENTER);
		PANE.updateUI();
	}

	private void SiWin(String s1, String s2, int Num) {
		Sibo = true;
		try {
			PANE.removeAll();
		} catch (Exception e) {

		}
		TiX = "PoSmsumP";
		teachermarking.setID(TiX, st, paId);
		JPanel panSiNum[] = new JPanel[Num];
		JPanel panSiInNum[] = new JPanel[Num];
		for (int i = 0; i < Num; i++) {
			panSiNum[i] = new JPanel();
		}
		for (int i = 0; i < Num; i++) {
			panSiInNum[i] = new JPanel();
		}
		teachermarking.SiChaperWin(panSiInNum);
		teachermarking.Answer(s1, panSiNum, panSiInNum);
		teachermarking.StAnswer(s2, panSiNum);
		Box boxVSi = Box.createVerticalBox();
		for (int i = 0; i < Num; i++) {
			boxVSi.add(new JScrollPane(panSiNum[i]));
		}
		PAN[2].add(new JLabel("简答题"), BorderLayout.NORTH);
		PAN[2].add(new JScrollPane(boxVSi), BorderLayout.CENTER);
		// Box boxHdown=Box.createHorizontalBox();
		// boxHdown.add(next);
		// PAN[2].add(boxHdown,BorderLayout.SOUTH);
		PANE.add(PAN[2], BorderLayout.CENTER);
		PANE.updateUI();
	}

	private void addf() {
		if (Exbo) {
			addPoint.Addpoint("PoEx", "paExNumfs", st, paId, tcnm);
		}
		if (Fibo) {
			addPoint.Addpoint("PoFi", "paFiNumfs", st, paId, tcnm);
		}
		if (Sibo) {
			addPoint.Addpoint("PoSm", "paSmNumfs", st, paId, tcnm);
		}
		addPoint.addall(st, paId);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == add) {

		} else if (e.getSource() == previous) {
			switch (choice) {
			case 1:
				break;
			case 2:
				FiWin(s11, s12, Finum);
				choice--;
				break;
			case 3:
				ExWin(s21, s22, Exnum);
				choice--;
				break;
			default:
			}
		} else if (e.getSource() == next) {
			switch (choice) {
			case 1:
				ExWin(s21, s22, Exnum);
				choice++;
				break;
			case 2:
				SiWin(s31, s32, Sinum);
				choice++;
				break;
			case 3:
				break;
			default:
			}
		} else if (e.getSource() == sure) {
			if (stId[stIdn] != 0) {
				// System.exit(0);
				addf();
				System.gc();// 垃圾回收
				this.dispose();
				ChaperChoice chaperchoice = new ChaperChoice(paId, stIdn);
				new TeacherMarkingWin(s11, chaperchoice.getFillblankSA(),
						s21, chaperchoice.getExplainSA(), s31, chaperchoice
								.getSimpleSA(), Finum, Exnum, Sinum, stId,stuna,
						stIdn, paId, tcid, tcnm, tixing);
				// new TeacherMarkingWin(s11, s12, s21, s22, s31, s32, Finum,
				// Exnum, Sinum, stId, stIdn);
			} else {
				addf();
				this.dispose();
				new ShowPointWin(stId,paId,stuna);
			}
		}
	}

}
