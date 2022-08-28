<%@page  contentType="text/html;charset=utf8" pageEncoding="utf8"%>
<%@page import="entity.*"%>
<html>
	<head>
		<title>修改员工信息</title>
		<meta http-equiv="content-type" content="text/html;charset=utf8">
	</head>
	<body>
		<h1>修改员工信息</h1>
		<%
			Emp emp=(Emp)request.getAttribute("emp");
			
		 %>
		<form action="update.do?idString=<%=emp.getIdString() %>" method="post">		
			<table>
				<tr>
					<td align="right">
						ID:
					</td>
					<td align="left">						
						<input type="text" name="idString" value="<%=emp.getIdString() %>"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名:
					</td>
					<td align="left">						
						<input type="text" name="nameString" value="<%=emp.getNameString() %>"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						性别:
					</td>
					<td align="left">						
						<input type="text" name="genderString" value="<%=emp.getGenderString() %>"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						薪水:
					</td>
					<td align="left">						
						<input type="text" name="salary" value="<%=String.valueOf(emp.getSalary()) %>"/>
					</td>
				</tr>
			</table>
			<p>
				<input type="submit" value="修改"/>
			</p>
		</form>
	</body>
</html>