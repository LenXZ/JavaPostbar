package util;

public class StringUtils {
	public static String initcap(String str) {
		if (str == null || "".equals(str)) {	// 保证操作的数据有内容
			return str ;
		}
		char ch[] = str.toCharArray();
		ch[0] -= 32;
		return String.valueOf(ch);
	}
}
