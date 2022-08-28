<%@page pageEncoding="utf8"
		contentType="text/html;charset=utf8" %><!-- 设置编码集  设置客户端编码集-->
<%@page import="java.text.SimpleDateFormat,java.util.Date"%>
<!--<%@page import="web.date"%>  -->
<html>
	<head></head>
	<body style="font-size:24px">
		
		<div style="font-size:20px; border:1px solid red" >
		<%
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
			String timeString=simpleDateFormat.format(date);
			try {
				//沉睡1000毫秒 （1秒）
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			out.println("当前的系统时间："+timeString+"<br>");
			
		%>
		</div>
		 <hr>
		<%
			for (int i = 0; i < 20; i++) {
				out.println("松" + i + "<br>");
			}
			for (int i = 0; i < 20; i++) {
		%>
		<%="NO." + (i + 1) + "Amadeus"%><br>
		 	<%
		 		}
		 	%>
	</body>
</html>