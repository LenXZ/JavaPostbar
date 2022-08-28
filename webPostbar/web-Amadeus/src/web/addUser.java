package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addUser extends HttpServlet {

	/**
		addUser
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求的编码集
		request.setCharacterEncoding("utf8");
		String nameString=request.getParameter("name");
		byte[] bs=nameString.getBytes("iso8859-1");
		nameString=new String(bs,"utf8");
		//设置相应的编码集
		response.setContentType("text/html;charset=utf8");
		//获取请求资源路径
		String uriString=request.getRequestURI();
		PrintWriter out = response.getWriter();
		System.out.print(uriString);
		out.println(nameString);
		out.println("我辈1");
		out.flush();
		out.close();
	}
	public void getDo(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//设置请求的编码集
		request.setCharacterEncoding("utf8");
		String nameString=request.getParameter("name");
		byte[] bs=nameString.getBytes("iso8859-1");
		nameString=new String(bs,"utf8");
		//设置相应的编码集
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();
		
		out.println(nameString);
		out.println("我辈3");
		out.flush();
		out.close();
	}

}

