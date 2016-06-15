<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page 
	import="java.util.Map"
	import="java.util.HashMap"
	import="java.util.List"
	import="java.util.Iterator"
	import="java.util.ArrayList"
	import="org.apache.shiro.session.Session"
	import="org.apache.shiro.subject.Subject"
	import="org.apache.shiro.SecurityUtils"
	import="com.flight.trs.model.entity.Airport"
	import="com.flight.trs.model.entity.Customer"
%>

<%	
	String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="cn">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title>机票预订系统首页</title>
	
    <!-- Bootstrap Core CSS -->
    <link href="<%=contextPath %>/resources/datetimepicker/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="<%=contextPath %>/resources/bootstrapselect/css/bootstrap-select.css" rel="stylesheet" type="text/css" />
    <link type="text/css" href="<%=contextPath %>/resources/js/custom/citypicker-1.0.1.css" rel="stylesheet" />
    <script type="text/javascript" src="<%=contextPath %>/resources/datetimepicker/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/resources/datetimepicker/js/bootstrap.min.js"></script>
	<style>
	  *{
	  	list-style: none;
	  }
	  .div_datepicker{
	    width:274px;
	  }
	  .div_from_city, .div_to_city{
	  	width:240px;
	  }
	</style>
	
  </head>
  <body>
    <nav class="navbar navbar-default" role="navigation">
      <div class="navbar-header">
        <a class="navbar-brand" href="<%=contextPath %>/">Home</a>
      </div>
      <ul class="nav navbar-nav ">
        <li><a href="javascript:;" class="alive">航&nbsp;&nbsp;班</a></li>
        <li><a href="<%=contextPath %>/orderform.query">订&nbsp;&nbsp;单</a></li>
        <li><a href="<%=contextPath %>/ticket.query">机&nbsp;&nbsp;票</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li style="width:160px;">
        	<%
	        	Subject currentUser = SecurityUtils.getSubject();
	        	Session currentsession = currentUser.getSession();
				String loginName = (String)currentsession.getAttribute("username");
				if ( null != loginName ) {
					String strliloggined = "<a href=\"javascript:;\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><span class=\"glyphicon glyphicon-user\"></span>&nbsp;&nbsp;"
			                + loginName + "&nbsp;&nbsp;<b class=\"caret\"></b></a><ul class=\"dropdown-menu\">"
			                + "<li><a href=\"javascript:;\">"
			                + "<span>个人中心</span></a></li><li><a href=\"" + contextPath + "/logout\"><i class=\"fa fa-sign-out pull-right\"></i> 退&nbsp;&nbsp;出</a>"
			                + "</li></ul>";
					out.print(strliloggined);
				}
				else {
					out.print("<a href=\""+ contextPath +"/login\"><span class=\"glyphicon glyphicon-user\"></span>&nbsp;&nbsp;登录|注册</a>");
				}
			%>
        </li>
      </ul>
    </nav>
	<div class="container">