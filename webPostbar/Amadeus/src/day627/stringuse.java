package day627;

public class stringuse {

	public stringuse() {
		String s1="AB";
		String s2="A"+"B";
		String s3=s1+s2;
		String s4="A";
		String s5="B";
		String s6=s4+s5;
		String s7=s4+"B";
		System.out.println(s1==s2);//1
		System.out.println(s1==s3);//1
		System.out.println(s1==s6);//0
		System.out.println(s1==s7);//0
	}
	public static void main(String[] args) {
//		new stringuse();
		String idString="A10000100003";
		int id=Integer.parseInt(idString.substring(6));
		id++;
		idString=idString.substring(0,6).concat(String.valueOf(id));
		System.out.println(idString);
	}

}
