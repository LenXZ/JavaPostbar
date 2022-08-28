package web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import dao.*;
import entity.*;
public class Action01 extends HttpServlet {

	/**
		方法名一定是service 		
		http://localhost:8080/PostBar/login.jsp
	 */
	User my;
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
		
		//用户管理
		if(action.equals("list")){
			System.out.println(action);
			UserDao dao=new UserDaoImpl();
			List<User> list=dao.findAll();
			System.out.println(list);
			//绑定数据到request上 绑定名 绑定数据
			request.setAttribute("list", list);
			//转发
			request.getRequestDispatcher("list.jsp").forward(request, response);
			
		}else if (action.equals("adminadd")) {
			String idString=request.getParameter("idString");
			String nameString=request.getParameter("nameString");
			String pwdString=request.getParameter("pwdString");
			String genderString=request.getParameter("genderString");
			String authority=request.getParameter("authority");
			User user=new User(idString, nameString, pwdString, genderString, authority);
			UserDao dao=new UserDaoImpl();
			dao.addUser(user);
//			request.getRequestDispatcher("list.jsp").forward(request, response);
			//重定向
			response.sendRedirect("list.do");
		
		}else if (action.equals("register")) {
			String idString=request.getParameter("idString");
			String nameString=request.getParameter("nameString");
			String pwdString=request.getParameter("pwdString");
			String genderString=request.getParameter("genderString");
			String authority="B";
			User user=new User(idString, nameString, pwdString, genderString, authority);
			UserDao dao=new UserDaoImpl();
			try {
				dao.addUser(user);
				request.setAttribute("login","注册成功");
			} catch (Exception e) {
				// TODO: handle exception
				request.setAttribute("login", "注册失败");
			}
//			request.getRequestDispatcher("list.jsp").forward(request, response);
			//重定向			
			request.getRequestDispatcher("login.jsp").forward(request, response);
		
		}else if(action.equals("adminload")){
			//获取id 
			String idString=request.getParameter("idString");
			//创建dao对象
			UserDao dao=new	UserDaoImpl();
			User user=dao.findById(idString);
			request.setAttribute("user", user);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			//重定向
//			response.sendRedirect("list.do");
		}else if(action.equals("update")){
			//获取id Integer.parseInt()
			String idString=request.getParameter("idString");
			String nameString=request.getParameter("nameString");
			String pwdString=request.getParameter("pwdString");
			String genderString=request.getParameter("genderString");
			String authority=request.getParameter("authority");
			//创建dao对象
			UserDao dao=new UserDaoImpl();
			User user=new User(idString, nameString, pwdString, genderString, authority);
			System.out.println(user);
			//执行sql删除元素
			dao.modifyUser(user);
			dao.findById(idString);
			//重定向
			response.sendRedirect("list.do");
		}else if(action.equals("admindelete")){
			//获取id Integer.parseInt()
			String idString=request.getParameter("idString");
			//创建dao对象
			UserDao dao=new UserDaoImpl();
			//执行sql删除元素
			dao.deleteUser(idString);
			//重定向
			response.sendRedirect("list.do");
		}
		//登录
		else if (action.equals("login")) {			
			String myid=request.getParameter("idString");
			String pwdString=request.getParameter("pwdString");
			UserDao dao=new	UserDaoImpl();
			my=dao.findById(myid);
			if(my==null){
//				System.out.println("A3"+myid+pwdString);
				
			}else if (pwdString.equals(my.getPwdString())) {
				//转到
				response.sendRedirect("PostBar.jsp");
			}else{
//				System.out.println("A0");				
			}
		}
		//帖子管理
		//
		else if(action.equals("post")){
			//根据题目显示帖子
			//获取id 
			String titleString=request.getParameter("idString");
//			System.out.println(titleString);
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findPost(titleString);
//			System.out.println(list);
			//绑定数据到request上 绑定名 绑定数据
			request.setAttribute("post", list);
			//转发
			request.getRequestDispatcher("post.jsp").forward(request, response);
			
		}
		//显示所有帖子
		else if(action.equals("postAll")){
			//显示所有帖子
//			System.out.println(action);
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findAll();
//			System.out.println(list);
			//绑定数据到request上 绑定名 绑定数据
			request.setAttribute("postAll", list);
			//转发
			request.getRequestDispatcher("postAll.jsp").forward(request, response);
			
		}
		//添加帖子**
		else if (action.equals("postaddtrans")) {
			String titleString=request.getParameter("titleString");
			//创建dao对象
			request.setAttribute("titleString", titleString);
			request.getRequestDispatcher("postadd.jsp").forward(request, response);
		}
		else if (action.equals("postadd")) {
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd. hh:mm:ss");
			String dateString=simpleDateFormat.format(date);		
			String idString=request.getParameter("idString");
//			System.out.print("oo"+idString);
			//新帖子id尾数+1
			int id=Integer.parseInt(idString.substring(6));
			id++;
			idString=idString.substring(0,6).concat(String.valueOf(id));
			System.out.print(idString);
			String textString=request.getParameter("text");
			PostDao dao=new PostDaoImpl();
			Post post=new Post(idString, my.getIdString(), dateString, textString);
			dao.addPost(post);
			request.setAttribute("idString", idString);
			request.getRequestDispatcher("post.do").forward(request, response);
//			request.getRequestDispatcher("list.jsp").forward(request, response);
			//重定向
//			response.sendRedirect("post.do");
		}else if (action.equals("postdelete")) {
			String idString=request.getParameter("idString");
			//创建dao对象
			PostDao dao=new PostDaoImpl();
			//执行sql删除元素
			dao.deletePost(idString);
			request.setAttribute("idString", idString);
			request.getRequestDispatcher("post.do").forward(request, response);
		}else if (action.equals("postupdate")) {			
			//获取id Integer.parseInt()
			String idString=request.getParameter("idString");
			PostDao dao=new PostDaoImpl();
			Post post=dao.findById(idString);
			String textString=request.getParameter("text");
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd. hh:mm:ss");
			String dateString=simpleDateFormat.format(date);	
			dao.modifyPost(new Post(post.getIdString(), post.getUserString(), dateString, textString));
			request.setAttribute("idString", idString);
			request.getRequestDispatcher("post.do").forward(request, response);
		}else if (action.equals("titleupdate")) {			
			//获取id Integer.parseInt()
			String idString=request.getParameter("idString");
			PostDao dao=new PostDaoImpl();
			Post post=dao.findById(idString);
			String textString=request.getParameter("text");
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd. hh:mm:ss");
			String dateString=simpleDateFormat.format(date);	
			dao.modifyPost(new Post(post.getIdString(), post.getUserString(), dateString, textString));
			//重定向
			String blockString=idString.substring(0,1);
			if(blockString.equals("A")){
				response.sendRedirect("block1.do");				
			}else if(blockString.equals("B")){
				response.sendRedirect("block2.do");				
			}else if(blockString.equals("C")){
				response.sendRedirect("block3.do");				
			}else if(blockString.equals("D")){
				response.sendRedirect("block4.do");				
			}else if(blockString.equals("E")){
				response.sendRedirect("block5.do");				
			}
		}
		//根据id显示一条帖子 用于编辑
		else if (action.equals("postload")||action.equals("titleload")) {	
			//获取id 
			String idString=request.getParameter("idString");
			//创建dao对象
			PostDao dao=new	PostDaoImpl();
			Post post=dao.findById(idString);
			request.setAttribute("post", post);
			request.getRequestDispatcher("postupdate.jsp").forward(request, response);
		}
		//题目管理
		else if (action.equals("postaddtitle")) {		
			String titleString=request.getParameter("titleString");
			//创建dao对象
			request.setAttribute("titleString", titleString);
			request.getRequestDispatcher("titleadd.jsp").forward(request, response);
		}else if (action.equals("titleadd")) {
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd. hh:mm:ss");
			String dateString=simpleDateFormat.format(date);		
			String idString=request.getParameter("titleString");
			String blockString=idString.substring(0,1);
			//新帖子id尾数+1
			int id=Integer.parseInt(idString.substring(1));
			id++;
			idString=blockString.concat(String.valueOf(id)).concat("100000");
			String textString=request.getParameter("text");
			PostDao dao=new PostDaoImpl();
			Post post=new Post(idString, my.getIdString(), dateString, textString);
			dao.addPost(post);
//			request.getRequestDispatcher("list.jsp").forward(request, response);
			//重定向
			if(blockString.equals("A")){
				response.sendRedirect("block1.do");				
			}else if(blockString.equals("B")){
				response.sendRedirect("block2.do");				
			}else if(blockString.equals("C")){
				response.sendRedirect("block3.do");				
			}else if(blockString.equals("D")){
				response.sendRedirect("block4.do");				
			}else if(blockString.equals("E")){
				response.sendRedirect("block5.do");				
			}
		}else if (action.equals("titledelete")) {
			String idString=request.getParameter("idString");
			idString=idString.substring(0,6);
			String blockString=idString.substring(0,1);
			PostDao dao=new PostDaoImpl();
			dao.deleteTitle(idString);
			//重定向
			if(blockString.equals("A")){
				response.sendRedirect("block1.do");				
			}else if(blockString.equals("B")){
				response.sendRedirect("block2.do");				
			}else if(blockString.equals("C")){
				response.sendRedirect("block3.do");				
			}else if(blockString.equals("D")){
				response.sendRedirect("block4.do");				
			}else if(blockString.equals("E")){
				response.sendRedirect("block5.do");				
			}
		}
		else if(action.equals("findTitle")){
			String idString=request.getParameter("titleString");
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(idString);
			try {
				request.setAttribute("block", list);
				//转发
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		//转到各个版块
		else if (action.equals("block1")) {	
			//获取id 
			String titleString="A";
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(titleString);
			try {
				request.setAttribute("block", list);
				//转发
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		else if (action.equals("block2")) {	
			//获取id 
			String titleString="B";
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(titleString);
			try {
				request.setAttribute("block", list);
				//转发
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		else if (action.equals("block3")) {	
			//获取id 
			String titleString="C";
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(titleString);
			try {
				request.setAttribute("block", list);
				//转发
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		else if (action.equals("block4")) {	
			//获取id 
			String titleString="D";
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(titleString);
			try {
				request.setAttribute("block", list);
				//转发
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}else if (action.equals("block5")) {	
			//获取id 
			String titleString="E";
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(titleString);
			System.out.println(list);
			//绑定数据到request上 绑定名 绑定数据
			try {
				request.setAttribute("block", list);
				//转发
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
			
		}
		
	}

}
