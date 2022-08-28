package DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JuanziTogether {
	private String xueqi = "";// ���þ��ӵ�ʹ��ѧ��
	private String xuenian = "";// ���þ��ӵ�ʹ��ѧ��
	private String kechengming = "";// ���þ��ӵĿγ�����
	private String yuanbie = "";// ���þ��ӵ�ʹ��Ժ��
	private String zhuanye = "";// ���þ��ӵ�ʹ��רҵ
	private String nianji = "";// ���þ��ӵ�ʹ���꼶
	private String banji = "";// ���þ��ӵ�ʹ�ð༶
	private String teacher = "";// ���ó����ʦ

	private String studentname = "";
	private String studentID = "";

	private String time = "";// ���ó���ʱ��
	private String qizm = "";// ���þ��������л���ĩ
	private String juanab = "";// ���þ�����A���B��
	private String kaosc = "";// ���þ����ǿ��Ի��ǿ���
	private String xzf = "";// ���þ���ѡ�����ܷ�
	private String xmf = "";// ���þ���ѡ����ÿ���
	private String xuanzeneirong = "";// ���þ���ѡ��������xuezeneirong
	private String tzf = "";// ���þ���������ܷ�
	private String tmf = "";// ���þ��������ÿ���
	private String tiankongneirong = "";// ���þ������������
	private String pzf = "";// ���þ����ж����ܷ�
	private String pmf = "";// ���þ����ж���ÿ���
	private String panduanneirong = "";// ���þ����ж�������
	private String mzf = "";// ���þ������ʽ������ܷ�
	private String mmf = "";// ���þ������ʽ�����ÿ���
	private String mingcineirong = "";// ���þ������ʽ���������
	private String jzf = "";// ���þ��Ӽ�����ܷ�
	private String jmf = "";// ���þ��Ӽ����ÿ���
	private String jiandaneirong = "";// ���þ��Ӽ��������
	private String choiceanswer = "";// ѡ�����
	private String fillblankanswer = "";// ������
	private String explainanswer = "";// ѡ�����
	private String judgeanswer = "";// �ж����
	private String simpleanswer = "";// ������
	private String zdf = "";
	private String zpteacher = "";
	private String zongheneirong = "";
	private String zzf = "";
	private String xdf = "";// �÷�
	private String xpteacher = "";// ������
	private String tdf = "";
	private String tpteacher = "";
	private String pdf = "";
	private String ppteacher = "";
	private String mdf = "";
	private String mpteacher = "";
	private String jdf = "";
	private String jpteacher = "";

	// �����Ծ���
	// public CreatJuanzi(String chtopicText,String fbtopicText,String
	// extopicText,String jutopicText,String smtopicText,String
	// snum,LinkedList<String> sxlist) throws IOException, TemplateException {
	public void CreatPingJuan() throws IOException, TemplateException {
		// TODO Auto-generated constructor stub
		// cxlist=sxlist;
		// System.out.println(xuenian);
		Configuration configuration = null;
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		// �����������
		//
		// dataMap.put("xzf",
		// Integer.parseInt(cxlist.get(6))*Integer.parseInt(cxlist.get(7)));

		dataMap.put("xuenianxueqi", xuenian);// xuenian+xueqi
		dataMap.put("kechengming", kechengming);
		dataMap.put("yuanbienianjibenzhuan", yuanbie + nianji);
		dataMap.put("qizm", qizm);
		dataMap.put("juanab", juanab);
		dataMap.put("kaosc", kaosc);

		dataMap.put("xdf", xdf);
		dataMap.put("xpteacher", xpteacher);
		dataMap.put("xzf", xzf);
		dataMap.put("xmf", xmf);
		dataMap.put("xuanzeneirong", xuanzeneirong);
		dataMap.put("tdf", tdf);
		dataMap.put("tpteacher", tpteacher);
		dataMap.put("tzf", tzf);
		dataMap.put("tmf", tmf);
		dataMap.put("tiankongneirong", tiankongneirong);
		dataMap.put("mdf", mdf);
		dataMap.put("mpteacher", mpteacher);
		dataMap.put("mzf", mzf);
		dataMap.put("mmf", mmf);
		dataMap.put("mingcineirong", mingcineirong);
		dataMap.put("pdf", pdf);
		dataMap.put("ppteacher", ppteacher);
		dataMap.put("pzf", pzf);
		dataMap.put("pmf", pmf);
		dataMap.put("panduanneirong",
				"���ж�����˵���Ƿ���ȷ����ȷ������������T�����������������дF��<w:br />" + panduanneirong);
		dataMap.put("jdf", jdf);
		dataMap.put("jpteacher", jpteacher);
		dataMap.put("jzf", jzf);
		dataMap.put("jiandaneirong", jiandaneirong);

		// dataMap.put("jmf",jmf);

		dataMap.put("zdf", zdf);
		dataMap.put("zpteacher", zpteacher);
		dataMap.put("zzf", zzf);
		dataMap.put("zongheneirong", zongheneirong);
		// System.out.print(dataMap);
		// д������
		configuration.setClassForTemplateLoading(this.getClass(), "/ftl"); // FTL�ļ������ڵ�λ��
		Template template = configuration.getTemplate("ccccc.ftl");
		File outFile = new File("D:/temp/" + xuenian + kechengming + kaosc
				+ juanab + studentID + studentname + ".doc");
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outFile), "UTF-8"));
		template.process(dataMap, out);
	}

	// ���ɴ𰸷���
	public void CreatTopAns(JuanziTogether juanzi) throws IOException,
			TemplateException {
		// CreatAnswer cc=new CreatAnswer();
		Configuration configuration = null;
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");

		Map<String, Object> dataMapAnswer = new HashMap<String, Object>();
		// ���������
		dataMapAnswer.put("kechengming", kechengming);
		// �ж���a����b������Ӧ�����ڴ�Ժ�
		if (juanzi.juanab.equals("A")) {
			dataMapAnswer.put("a", "��");
			dataMapAnswer.put("b", "��");
		} else {
			dataMapAnswer.put("a", "��");
			dataMapAnswer.put("b", "��");
		}

		dataMapAnswer.put("zhuanyenianji", zhuanye + nianji);
		dataMapAnswer.put("xueqixuenian", xueqi + xuenian);
		dataMapAnswer.put("yuanxi", yuanbie);
		dataMapAnswer.put("teachername", teacher);
		dataMapAnswer.put("neirong", "һ.ѡ����<w:br />�𰸣�<w:br />" + choiceanswer
				+ "<w:br />" + "" + "��.�����<w:br />�𰸣�<w:br />"
				+ subString(",", fillblankanswer) + "<w:br />" + ""
				+ "��.���ʽ���<w:br />�𰸣�<w:br />" + explainanswer + "<w:br />" + ""
				+ "��.�ж���<w:br />�𰸣�<w:br />" + judgeanswer + "<w:br />"
				+ "��.�����<w:br />�𰸣�<w:br />" + subString("(", simpleanswer)
				+ "<w:br />");

		// д��������
		configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
		Template templateAnswer = configuration.getTemplate("answer.ftl");
		File outFileAnswer = new File("E:/temp/" + "answer" + juanab
				+ studentname + ".doc");
		Writer outAnswer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outFileAnswer), "UTF-8"));
		templateAnswer.process(dataMapAnswer, outAnswer);
	}

	// ��ȡ�ַ�������
	public String subString(String str, String text) {
		String string = "";

		String[] sourceStrArray = text.split(",");
		for (int i = 0; i < sourceStrArray.length; i++) {
			// System.out.println(sourceStrArray[i]);
			String temp = "";
			temp = sourceStrArray[i];
			string = string + temp + "<w:br />";
		}

		return string;
	}

	/*
	 * //�ж���a����b�����÷���ѡ�� public String getFangKuang(String str) {
	 * 
	 * String fangkuangstr=""; if(str.equals("A")) { fangkuangstr= } else {}
	 * 
	 * return fangkuangstr;
	 * 
	 * }
	 */
	// �������Ե�get������set����
	/** ��ͷ������ */
	public void setBiaotou(String xuenian, String qizm, String kecheng,
			String yuanbie, String juan, String xzf, String xmf, String tzf,
			String tmf, String pzf, String pmf, String mzf, String mmf,
			String jzf, String jmf) {
		this.xuenian = xuenian;
		this.qizm = qizm;
		this.kechengming = kecheng;
		this.yuanbie = yuanbie;
		this.juanab = juan;
		this.xzf = xzf;
		this.xmf = xmf;
		this.tzf = tzf;
		this.tmf = tmf;
		this.pzf = pzf;
		this.pmf = pmf;
		this.mzf = mzf;
		this.mmf = mmf;
		this.jzf = jzf;
		this.jmf = jmf;
	}

	/** ��������÷������� */
	public void setteacher(String xdf, String xpteacher, String tdf,
			String tpteacher, String pdf, String ppteacher, String mdf,
			String mpteacher, String jdf, String jpteacher) {
		this.xdf = xdf;
		this.xpteacher = xpteacher;
		this.tdf = tdf;
		this.tpteacher = tpteacher;
		this.pdf = pdf;
		this.ppteacher = ppteacher;
		this.mdf = mdf;
		this.mpteacher = mpteacher;
		this.jdf = jdf;
		this.jpteacher = jpteacher;
	}

	public void setStudent(String studentname, String studentID) {
		this.studentID = studentID;
		this.studentname = studentname;
	}

	public String getXueqi() {
		return xueqi;
	}

	public void setXueqi(String xueqi) {
		this.xueqi = xueqi;
	}

	public String getXuenian() {
		return xuenian;
	}

	public void setXuenian(String xuenian) {
		this.xuenian = xuenian;
	}

	public String getKechengming() {
		return kechengming;
	}

	public void setKechengming(String kechengming) {
		this.kechengming = kechengming;
	}

	public String getNianji() {
		return nianji;
	}

	public void setNianji(String nianji) {
		this.nianji = nianji;
	}

	public String getXzf() {
		return xzf;
	}

	public void setXzf(String xzf) {
		this.xzf = xzf;
	}

	public String getXmf() {
		return xmf;
	}

	public void setXmf(String xmf) {
		this.xmf = xmf;
	}

	public String getXuezeneirong() {
		return xuanzeneirong;
	}

	public void setXuezeneirong(String xuezeneirong) {
		this.xuanzeneirong = xuezeneirong;
	}

	public String getTzf() {
		return tzf;
	}

	public void setTzf(String tzf) {
		this.tzf = tzf;
	}

	public String getTmf() {
		return tmf;
	}

	public void setTmf(String tmf) {
		this.tmf = tmf;
	}

	public String getTiankongneirong() {
		return tiankongneirong;
	}

	public void setTiankongneirong(String tiankongneirong) {
		this.tiankongneirong = tiankongneirong;
	}

	public String getPzf() {
		return pzf;
	}

	public void setPzf(String pzf) {
		this.pzf = pzf;
	}

	public String getPmf() {
		return pmf;
	}

	public void setPmf(String pmf) {
		this.pmf = pmf;
	}

	public String getPanduanneirong() {
		return panduanneirong;
	}

	public void setPanduanneirong(String panduanneirong) {
		this.panduanneirong = panduanneirong;
	}

	public String getMzf() {
		return mzf;
	}

	public void setMzf(String mzf) {
		this.mzf = mzf;
	}

	public String getMmf() {
		return mmf;
	}

	public void setMmf(String mmf) {
		this.mmf = mmf;
	}

	public String getMingcineirong() {
		return mingcineirong;
	}

	public void setMingcineirong(String mingcineirong) {
		this.mingcineirong = mingcineirong;
	}

	public String getJzf() {
		return jzf;
	}

	public void setJzf(String jzf) {
		this.jzf = jzf;
	}

	public String getJmf() {
		return jmf;
	}

	public void setJmf(String jmf) {
		this.jmf = jmf;
	}

	public String getJiandaneirong() {
		return jiandaneirong;
	}

	public void setJiandaneirong(String jiandaneirong) {
		this.jiandaneirong = jiandaneirong;
	}

	public String getQizm() {
		return qizm;
	}

	public void setQizm(String qizm) {
		this.qizm = qizm;
	}

	public String getYuanbie() {
		return yuanbie;
	}

	public void setYuanbie(String yuanbie) {
		this.yuanbie = yuanbie;
	}

	public String getZhuanye() {
		return zhuanye;
	}

	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}

	public String getJuanab() {
		return juanab;
	}

	public void setJuanab(String juanab) {
		this.juanab = juanab;
	}

	public String getKaosc() {
		return kaosc;
	}

	public void setKaosc(String kaosc) {
		this.kaosc = kaosc;
	}

	public String getBanji() {
		return banji;
	}

	public void setBanji(String banji) {
		this.banji = banji;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getChoiceanswer() {
		return choiceanswer;
	}

	public void setChoiceanswer(String choiceanswer) {
		this.choiceanswer = choiceanswer;
	}

	public String getFillblankanswer() {
		return fillblankanswer;
	}

	public void setFillblankanswer(String fillblankanswer) {
		this.fillblankanswer = fillblankanswer;
	}

	public String getExplainanswer() {
		return explainanswer;
	}

	public void setExplainanswer(String explainanswer) {
		this.explainanswer = explainanswer;
	}

	public String getJudgeanswer() {
		return judgeanswer;
	}

	public void setJudgeanswer(String judgeanswer) {
		this.judgeanswer = judgeanswer;
	}

	public String getSimpleanswer() {
		return simpleanswer;
	}

	public void setSimpleanswer(String simpleanswer) {
		this.simpleanswer = simpleanswer;
	}

	/*
	 * public Juanzi() { // TODO Auto-generated constructor stub }
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// JuanziTogether juanzi=new JuanziTogether();
		// try {
		// // juanzi.CreatJuanzi();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (TemplateException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}
