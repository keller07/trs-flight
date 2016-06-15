<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	String contextPath = request.getContextPath();

 	int empNo = 0;
	String role = "";
	if(session.getAttribute("empNo") != null && session.getAttribute("role") != null){
		empNo = (int)session.getAttribute("empNo");
		role = (String)session.getAttribute("role");
	} 
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
	
	    <div id="wrapper">
	
	        <!-- Navigation -->
	        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	            <div class="navbar-header">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	                    <span class="sr-only">Toggle navigation</span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
	                <a class="navbar-brand" href="#">业务代表中心</a>
	            </div>
	            <!-- /.navbar-header -->
	
	            <ul class="nav navbar-top-links navbar-right">
	                <li class="dropdown">
	                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
	                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
	                    </a>
	                    <ul class="dropdown-menu dropdown-alerts">
	                        <li>
	                            <a href="#">
	                                <div>
	                                    <i class="fa fa-comment fa-fw"></i> New Comment
	                                    <span class="pull-right text-muted small">4 minutes ago</span>
	                                </div>
	                            </a>
	                        </li>
	                        <li class="divider"></li>
	                        <li>
	                            <a class="text-center" href="#">
	                                <strong>See All Alerts</strong>
	                                <i class="fa fa-angle-right"></i>
	                            </a>
	                        </li>
	                    </ul>
	                    <!-- /.dropdown-alerts -->
	                </li>
	                <!-- /.dropdown -->
	                <li class="dropdown">
	                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
	                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
	                    </a>
	                    <ul class="dropdown-menu dropdown-user">
	                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
	                        </li>
	                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
	                        </li>
	                        <li class="divider"></li>
	                        <li><a href="<%=contextPath %>/inner/logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
	                        </li>
	                    </ul>
	                    <!-- /.dropdown-user -->
	                </li>
	                <!-- /.dropdown -->
	            </ul>
	            <!-- /.navbar-top-links -->
	
	            <div class="navbar-default sidebar" role="navigation">
	                <div class="sidebar-nav navbar-collapse">
	                    <ul class="nav" id="side-menu">
	                    	<li class="sidebar-search">
                      			您好！<%=empNo %>
                    		</li>
	                        <li>
	                            <a href="#"><i class="fa fa-dashboard fa-fw"></i> 航班查询</a>
	                        </li>
	                        <li>
	                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 订单维护<span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level collapse">
	                                <li>
	                                    <a href="#"> 退票</a>
	                                </li>
	                                <li>
	                                    <a href="#"> 改期</a>
	                                </li>
	                                <li>
	                                    <a href="#"> 签转</a>
	                                </li>
	                                <li>
	                                    <a href="#"> 废票</a>
	                                </li>
	                                <li>
	                                    <a href="#"> 升舱</a>
	                                </li>
	                                <li>
	                                    <a href="#"> 换开</a>
	                                </li>
	                            </ul>
	                            <!-- /.nav-second-level -->
	                        </li>
	                        <li>
	                            <a href="#"><i class="fa fa-table fa-fw"></i> 订单补录</a>
	                        </li>
	                    </ul>
	                </div>
	                <!-- /.sidebar-collapse -->
	            </div>
	            <!-- /.navbar-static-side -->
	        </nav>
	
	        
	
	    
