package entity;

import java.util.ArrayList;
import java.util.List;

public class test {

	/**
	 * 测试类
	 */
	public static void main(String[] args) {
		String[] idString={"001","002","003"};
		String[] nameString={"冈部伦太郎","真由理","桥田至"};
		String genderString="男";
		int salary=10;
//		user user=new user(idString[i],nameString[i],genderString,salary);
//		for(int i=0;i<3;i++){
//			user=new user(idString[i],nameString[i],genderString,salary);
//		}
//		user user1=new user(idString[1],nameString[1],genderString,salary);
//		user user2=new user(idString[2],nameString[2],genderString,salary);
//		user user0=new user(idString[0],nameString[0],genderString,salary);
		//元素为user的泛型
		List<User> list=new ArrayList<User>();
		//添加user
//		list.add(user1);
//		list.add(user2);
//		list.add(user0);
		//根据user类toString()进行输出
		System.out.println(list);
		//根据list中数据条书循环
		for(int i=0;i<list.size();i++){
			
		}
//		user.setNameString("dd");
//		System.out.println(user.getNameString());
//		System.out.println(user.toString());

	}

}
