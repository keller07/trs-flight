<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<!-- saved from url=(0077)http://blackrockdigital.github.io/startbootstrap-sb-admin-2/pages/blank.html# -->
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>SB Admin 2 - Bootstrap Admin Theme</title>
	
	    <!-- Bootstrap Core CSS -->
	    <link href="<%=contextPath %>/resources/css/SB.Admin.2/bootstrap.min.css" rel="stylesheet">
	
		<!-- MetisMenu CSS -->
	    <link href="<%=contextPath %>/resources/css/SB.Admin.2/metisMenu.min.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="<%=contextPath %>/resources/css/SB.Admin.2/sb-admin-2.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="<%=contextPath %>/resources/css/SB.Admin.2/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
		
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	
	</head>
	
	<body>
		<div class="htmleaf-container">
	        <header class="htmleaf-header">
	            <div class="htmleaf-links">
	                <a class="htmleaf-icon " href="http://www.htmleaf.com/" title="jQuery之家" target="_blank"><i class="fa fa-home"></i><span> jQuery之家</span></a>
	            	<%
							 	long cstmid = 0;
								String loginName = "";
								if(session.getAttribute("cstmID") != null && session.getAttribute("loginName") != null){
									cstmid = (long)session.getAttribute("cstmID");
									loginName = (String)session.getAttribute("loginName");
									out.print("您好！ " + loginName);
								} 
								else {
									out.print("<a href=\""+ contextPath +"/login\">登录/注册</a>");
								}
		            		%>
		            	
		                        <a href="<%=contextPath %>/inner/logout"><i class="fa fa-sign-out fa-fw"></i> 退&nbsp;出</a>
		                      
	            </div>
	        </header>
        </div>
   		<div class="container">
			<div class="row">
				<form id="form_flightQueryInfo" name="flightQueryInfo" class="nav">
					<div class="form-group">
						<label for="depAirportCode">出发城市:</label> <input
							id="ipt_depAirportCode" name="depAirportCode" class="form-control" />
					</div>
					<div class="form-group">
						<label for="arrAirportCode" data-icon="e">到达城市</label> <input
							id="ipt_arrAirportCode" name="arrAirportCode" class="form-control" />
					</div>
					<div class="form-group">
						<label for="depDate">出发日期</label>
						<input id="ipt_depDate" name="depDate" />
					</div>
					<div class="form-group">
						<input type="submit" value="查询" />
					</div>
				</form>
			</div>
			<div class="row">
				<div class='col-sm-2'>
					<input type='text' class="form-control" id='datetimepicker4' />
				</div>
				<script type="text/javascript">
				            $(function () {
				                $('#ipt_depDate').datetimepicker();
				            });
				</script>
			</div>
		</div>
