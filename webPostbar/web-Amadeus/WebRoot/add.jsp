<%@page contentType="text/html;charset=utf8" pageEncoding="utf8"%>
<html>
	<head>
		<title>增加员工</title>
		<meta http-equiv="content-type" content="text/html;charset=utf8">
	</head>
	<body>
		<h1>
			增加员工
		</h1>
		<form action="add.do" method="post">
			<table>
				<tr>
					<td align="right">
						ID:
					</td>
					<td align="left">
						<input type="text" name="idString" />
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名:
					</td>
					<td align="left">
						<input type="text" name="nameString" />
					</td>
				</tr>
				<tr>
					<td align="right">
						性别:
					</td>
					<td align="left">
						<input type="text" name="genderString" />
					</td>
				</tr>
				<tr>
					<td align="right">
						薪水:
					</td>
					<td align="left">
						<input type="text" name="salary" />
					</td>
				</tr>
			</table>
			<p>
				<input type="submit" value="提交">
			</p>
		</form>
	</body>
</html>