package MyListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import DAO.DAO;

public class MarkingListener implements ActionListener,ItemListener {
	JCheckBox JC[] = new JCheckBox[1000];
	String re = "", T1[] = new String[100],s1;
	int Timu = 0,Point[]=new int[1000],s2,s3;
	/**复选框传导*/
	public void setJCheckBox(JCheckBox s[]) {
		JC = s;
	}
	/**分值题数归零*/
	public void reTimuNum(){
		Timu=0;
		Point=new int[1000];
	}
	/**题数累加*/
	public void setTimuNum(int s) {
		Timu = Timu + s;
	}
	public void setID(String s1,int s2,int s3){
		this.s1=s1;
		this.s2=s2;
		this.s3=s3;
	}
	/**分值写入数据库*/
	public String setPoint(String s1,int s2,int s3){//题型 学生ID 卷ID
		re="";
		for(int i=0;i<=Timu;i++){
			re=re.concat(Integer.toString(Point[i]).concat(","));
		}
		DAO table=new DAO();
		table.setSQL("UPDATE PointBiao SET "+s1+" = '"+re+"' WHERE StId= "+s2+" AND PaId="+s3);
//		table.setSQL("INSERT INTO PointBiao('"+s+"') VALUES ('"+re+"')");
		table.Insert();
		return re;
	}
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		// if(e.getItemSelectable()!=null){
		// System.out.println("JC"+e.getItem()+e.getItemSelectable());
		// };
		for (int i = 0; i < 1000; i++) {
			if (e.getSource() == JC[i]) {
				if(Point[i]==1){
					Point[i]=0;
				}else{
					Point[i]=1;
				}
//				for(int j=0;j<Timu;j++){
//					System.out.print(""+Point[j]);
//				}
//				System.out.println(" "+ Timu);		
				setPoint(s1,s2,s3);
			}
		}

	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
