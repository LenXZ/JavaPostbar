package web;

import java.text.SimpleDateFormat;
import java.util.Date;

public class date {

	/**
	 * 日期
	 */
	public date() {
		while(true){	
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm:ss");
			String timeString=simpleDateFormat.format(date);
			try {
				//沉睡1000毫秒 （1秒）
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(timeString);
		}
	}
	public static void main(String[] args) {
/**		Date date=new Date();		
		System.out.println(date);
		//定义日期格式
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss E");
		String timeString=simpleDateFormat.format(date);		
		System.out.println(timeString);
*/
		while(true){	
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm:ss");
			String timeString=simpleDateFormat.format(date);
			try {
				//沉睡1000毫秒 （1秒）
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(timeString);
		}
	}

}
