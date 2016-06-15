<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Keywords" content="" />
		<meta name="Description" content="" />
		<title>Insert title here</title>
		<meta name="author" content="Codrops" />
		
   	    <!-- Bootstrap Core CSS -->
	    <link href="<%=contextPath %>/resources/css/SB.Admin.2/bootstrap.min.css" rel="stylesheet">
	
	    <!-- MetisMenu CSS -->
	    <link href="<%=contextPath %>/resources/css/SB.Admin.2/metisMenu.min.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="<%=contextPath %>/resources/css/SB.Admin.2/sb-admin-2.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="<%=contextPath %>/resources/css/SB.Admin.2/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
		
	</head>
	
	<body>
		
		<div class="container" status="${status}">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form id="login_form" name="logininfo" action="<%=contextPath %>/inner/login.do" method="post" role="form">
                            <fieldset>
                                <div class="form-group">
                                    <input id="empNo" name="empNo" type="text" class="form-control" placeholder="EmployeeNo" autofocus="">
                                </div>
                                <div class="form-group">
                                    <input id="password" name="password" type="password" class="form-control" placeholder="Password" value="">
                                </div>
                                <input id="btn_login" type="submit" class="btn btn-lg btn-success btn-block" value="Login" />
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<!-- jQuery -->
	<script src="<%=contextPath %>/resources/js/SB.Admin.2/jquery.min.js"></script>
	<script src="<%=contextPath %>/resources/js/common/jquery.md5-1.2.1.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="<%=contextPath %>/resources/js/SB.Admin.2/bootstrap.min.js"></script>
	
	<!-- Metis Menu Plugin JavaScript -->
	<script src="<%=contextPath %>/resources/js/SB.Admin.2/metisMenu.min.js"></script>
	
	<!-- Custom Theme JavaScript -->
	<script src="<%=contextPath %>/resources/js/SB.Admin.2/sb-admin-2.js"></script>

	<script type="text/javascript">
	
		$(document).ready(function(){
			
			var status = $(".container").eq(0).attr("status");
			if(status == "failed"){
				alert("登录名或密码不正确！");
				$("#empNo").val("");
				$("#empNo").focus();
			}
			
			$("#login_form").submit(function(){
				var empNo = $("#empNo").val();
				if(empNo.length > 6){
					alert("登录名为不超过6位的数字");
					$("#empNo").val("");
					$("#empNo").focus();
					return false;
				}
				var password = $.md5($("#password").val());
				$("#password").val(password);
			});
			
		});
		
	</script>
	</body>
</html>
