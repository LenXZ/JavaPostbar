package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import dao.EmpDaoImpl;
import entity.*;
public class Action01 extends HttpServlet {

	/**
		方法名一定是service 
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求的编码集
		request.setCharacterEncoding("utf8");
		//设置相应的编码集
		response.setContentType("text/html;charset=utf8");
		String uriString=request.getRequestURI();	
		//截取路径最后一个"/"和"."之间的字符串
		String action=uriString.substring(uriString.lastIndexOf("/")+1,uriString.indexOf("."));
//		System.out.println(action);
		if(action.equals("list")){
			EmpDao dao=new EmpDaoImpl();
			List<Emp> list=dao.findAll();
			//绑定数据到request上 绑定名 绑定数据
			request.setAttribute("list", list);
			//转发
			request.getRequestDispatcher("list.jsp").forward(request, response);
			
		}else if (action.equals("add")) {
			String idString=request.getParameter("idString");
			String nameString=request.getParameter("nameString");
			String genderString=request.getParameter("genderString");
			int salary=Integer.parseInt(request.getParameter("salary"));
			Emp emp=new Emp(idString, nameString, genderString, salary);
			EmpDao dao=new EmpDaoImpl();
			dao.addEmp(emp);
//			request.getRequestDispatcher("list.jsp").forward(request, response);
			//重定向
			response.sendRedirect("list.do");
		
		}else if(action.equals("load")){
			//获取id 
			String idString=request.getParameter("idString");
			//创建dao对象
			EmpDao dao=new	EmpDaoImpl();
			Emp emp=dao.findById(idString);
			request.setAttribute("emp", emp);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			//重定向
//			response.sendRedirect("list.do");
		}else if(action.equals("update")){
			//获取id Integer.parseInt()
			String idString=request.getParameter("idString");
			String nameString=request.getParameter("nameString");
			String genderString=request.getParameter("genderString");
			int salary=Integer.parseInt(request.getParameter("salary"));
			//创建dao对象
			EmpDao dao=new EmpDaoImpl();
			Emp emp=new Emp(idString, nameString, genderString, salary);
			System.out.println(emp);
			//执行sql删除元素
			dao.modifyEmp(emp);
			dao.findById(idString);
			//重定向
			response.sendRedirect("list.do");
		}else if(action.equals("delete")){
			//获取id Integer.parseInt()
			String idString=request.getParameter("idString");
			//创建dao对象
			EmpDao dao=new EmpDaoImpl();
			//执行sql删除元素
			dao.deleteEmp(idString);
			//重定向
			response.sendRedirect("list.do");
		}
		
	}

}
