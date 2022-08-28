<%@page contentType="text/html;charset=utf8" pageEncoding="utf8"%>
<%@page import="java.util.*"%>
<%@page import="entity.*"%>
<html>
	<head>
		<title>员工列表</title>
		<meta http-equiv="content-type" content="text/html;charset=utf8">
	</head>
	<body>
		<table border="1" callpadding="0" cellspacing="0" width="60%">
			<caption>员工信息列表</caption>
			<tr>
				<td>ID</td>
				<td>姓名</td>
				<td>性别</td>
				<td>薪水</td>
				<td>操作</td>
			</tr>	
		<%
			List<Emp> emps=(List<Emp>)request.getAttribute("list");
			for(Emp e:emps){
		 %>
		 <tr>
		 	<td><%=e.getIdString() %></td>
		 	<td><%=e.getNameString() %></td>
		 	<td><%=e.getGenderString() %></td>
		 	<td><%=e.getSalary() %></td>
		 	<td>
		 	<a href="delete.do?idString=<%=e.getIdString() %>" 
		 	onclick="return confirm('是否删除<%=e.getIdString() %>信息')">删除</a>		 	
			<a href="load.do?idString=<%=e.getIdString() %>" 
		 	onclick="return confirm('是否修改<%=e.getIdString() %>信息')">修改</a>
		 	</td>
		 </tr>
		<%}
		 %>
		 </table>
		 <p>
		 	<input type="button" value="增加员工" onclick="location='add.jsp'"/>
		 </p>
	</body>
</html>