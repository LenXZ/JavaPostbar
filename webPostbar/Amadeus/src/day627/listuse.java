package day627;

import java.util.ArrayList;
import java.util.List;
/**
 * 泛型 规定集合只允许使用一种类型的数据
 **/

public class listuse {
	public static void main(String[] args) {
		//不限制数据类型
		List list=new ArrayList();//向上造型 
		//限制只存放String类型数据
		List<String> listStrings=new ArrayList<String>();
		for(int i=0;i<=10;i++){
			listStrings.add(""+(int)(Math.random()*100));			
		}
		System.out.println(listStrings);
	}
}
