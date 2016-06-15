<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Keywords" content="" />
		<meta name="Description" content="" />
		<title>Insert title here</title>
	</head>
	
	<body>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//判断session中是否有值，没有则拒绝访问
		String cstmID = "";
		String phone = "";
		try{
		    cstmID = session.getAttribute("cstmID").toString();
		    phone = session.getAttribute("phone").toString();
		}catch(NullPointerException e){
			//e.printStackTrace();
		    /* String home=basePath+"index.jsp"; //定义一个主页链接
		    System.out.println(home);
		    response.setHeader("refresh","0;url="+home); */
		}
	%>
		你好！<%=cstmID %>,<%=phone %>
	</body>
</html>