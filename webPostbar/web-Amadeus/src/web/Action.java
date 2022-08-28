package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Action extends HttpServlet {


	/**
	 * 请求资源名hello*/
	//	请求  相应
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求的编码集
		request.setCharacterEncoding("utf8");
		//设置相应的编码集
		response.setContentType("text/html;charset=utf8");
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
		String timeString=simpleDateFormat.format(date);		
		PrintWriter out = response.getWriter();
		out.println(timeString);
		out.print("web-Amadeus");
		out.flush();
		out.close();
	}

}
