<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page 
	import="com.flight.trs.security.token.IncorrectCaptchaException"%>
<%	String contextPath = request.getContextPath(); %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Keywords" content="" />
		<meta name="Description" content="" />
		<title>Insert title here</title>
		<meta name="author" content="Codrops" />
        <link rel="stylesheet" type="text/css" href="<%=contextPath %>/resources/css/login/demo.css" />
        <link rel="stylesheet" type="text/css" href="<%=contextPath %>/resources/css/login/style.css" />
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/resources/css/login/animate-custom.css" />
	</head>
	
	<body>
		
		<div class="container">
			<div id="div_msg"
			<%
			Class<? extends Exception> eClass = (Class)request.getAttribute("shiroLoginFailure");
			if(null != eClass){
				out.println("status=\"error\" message=\"error\"");
			}
		//}catch (IncorrectCaptchaException e){
//			// 验证码不正确
//			result.put("status", "failed");
//			result.put("message", "incorrectCaptcha");
//			return result;
//		}catch (UnknownAccountException e) {
//			// 找不到该账户信息
//			result.put("status", "failed");
//			result.put("message", "incorrectUsernameOrPassword");
//			return result;
//		}catch (IncorrectCredentialsException e) {
//			// 找到账户但密码不必配
//			result.put("status", "failed");
//			result.put("message", "incorrectUsernameOrPassword");
//			return result;
//		}catch (LockedAccountException e) {
//			// 找到账户但该账户处于锁定状态
//			result.put("status", "failed");
//			result.put("message", "lockedAccount");
//			return result;
//		}catch (DisabledAccountException e) {
//			// 找到账户但该账户处于被删除状态
//			result.put("status", "failed");
//			result.put("message", "disabledAccount");
//			return result;
//		}
			//String status = (String)request.getAttribute("status");
			//String message = (String)request.getAttribute("message");
			%>
			></div>
            <!-- Codrops top bar -->
            <div class="codrops-top">
            </div><!--/ Codrops top bar -->
            <header>
            </header>
            <section>				
                <div id="container_demo" >
                    <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div id="login" class="animate form">
                            <form id="login_form" action="<%=contextPath %>/login.do" method="post" autocomplete="off" > 
                                <h1>登&nbsp;&nbsp;&nbsp;&nbsp;录</h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" >&nbsp;</label>
                                    <input id="username" name="username" type="text" required="required" placeholder="登录名：手机号码/邮箱地址"/>
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd" data-icon="p">&nbsp;</label>
                                    <input id="password" name="password" type="password" required="required" placeholder="请输入您的登录密码" /> 
                                </p>
                                <p>
                                	<input id="captchaToLogin" name="captcha" type="text" style="width:50%" required="required" placeholder="请输入验证码" />
                                	<img id="captchaImgToLogin" style="margin-bottom:-13.5px;padding-top:21.5px;" title="点击刷新" width="130px" height="37px" onclick="refreshCaptcha($(this));" src="<%=contextPath %>/login.getCaptchaImage"/>
                                </p>
                                <p class="keeplogin"> 
                                	<br>
									<input  id="keepLoggedIn"  name="keepLoggedIn" type="checkbox" checked /> 
									<label for="keepLoggedIn">7天内记住我</label>
								</p>
                                <p class="login button"> 
                                    <input type="submit" value="登  录" /> 
								</p>
                                <p class="change_link">
									还未注册账号？
									<a href="#toregister" class="to_register">马上注册</a>
								</p>
                            </form>
                        </div>

                        <div id="register" class="animate form">
                            <form id="register_form" action="<%=contextPath %>/register.do" method="post" autocomplete="off"> 
                                <h1>注&nbsp;&nbsp;&nbsp;&nbsp;册</h1> 
                                <p> 
                                    <label for="phoneToRegister" class="uname" data-icon="u">&nbsp;</label>
                                    <input id="phoneToRegister" name="phoneToRegister" required="required" type="text" placeholder="请输入您的手机号码" />
                                </p>
                                <p> 
                                    <label for="emailToRegister" class="uname" data-icon="e">&nbsp;</label>
                                    <input id="emailToRegister" name="emailToRegister" required="required" type="email" placeholder="请输入您常用的电子邮箱" />
                                </p>
                                <p> 
                                    <label for="passwordToRegister" class="youpasswd" data-icon="p">&nbsp;</label>
                                    <input id="passwordToRegister" name="passwordToRegister" required="required" type="password" placeholder="请输入您的登录密码"/>
                                </p>
                                <p> 
                                    <label for="passwordToRegister_confirm" class="youpasswd" data-icon="p">&nbsp;</label>
                                    <input id="passwordToRegister_confirm" required="required" type="password" placeholder="请重复输入您的登录密码"/>
                                </p>
                                	<input id="captchaToRegister" name="captcha" type="text" style="width:50%" required="required" placeholder="请输入验证码" />
                                	<img id="captchaImgToRegister" style="margin-bottom:-13.5px;padding-top:21.5px;" title="点击更换" width="130px" height="37px" onclick="refreshCaptcha($(this));" src="<%=contextPath %>/login.getCaptchaImage"/>
                                <p class="signin button"> 
									<input type="submit" value="注         册"/> 
								</p>
                                <p class="change_link">  
									已注册账号 ?
									<a href="#tologin" class="to_register">马上登录</a>
								</p>
                            </form>
                        </div>
						
                    </div>
                </div>  
            </section>
        </div>
	
	</body>
	<script type="text/javascript" src="<%=contextPath %>/resources/js/common/jquery-1.12.0.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/resources/js/common/jquery.md5-1.2.1.js"></script>
	<script type="text/javascript">
		
		function refreshCaptcha($img){
			//单击验证码图片时刷新验证码，防止浏览器的缓存问题
			$img.attr("src","<%=contextPath %>/login.getCaptchaImage?t=" + Math.random());
		}
	
		function post(URL, PARAMS) {        
		    var temp = document.createElement("form");        
		    temp.action = URL;        
		    temp.method = "post";        
		    temp.style.display = "none";        
		    for (var x in PARAMS) {        
		        var opt = document.createElement("textarea");        
		        opt.name = x;        
		        opt.value = PARAMS[x];
		        temp.appendChild(opt);        
		    }        
		    document.body.appendChild(temp);        
		    temp.submit();        
		    return temp;        
		}  
		
		$(document).ready(function(){
			var status = $("#div_msg").attr("status");
			var message = $("#div_msg").attr("message");
			if(typeof(status)!="undefined"&&typeof(message)!="undefined"){
				alert(status+""+message);
			}
			
			$("#login_form").submit(function(e){
				e.preventDefault();		//撤销登录表单的默认提交事件
				var username = $("#username").val();
				var urlToGetSalts = "<%=contextPath %>/login.getSalts";
				var paramsToGetSalts = {
					username: username
				};
				//获取根据用户名获取随机盐
				$.post(
					urlToGetSalts,        			  
					paramsToGetSalts,
	          		function(data){ 	
						if(data.status == "error"){
							alert(data.message);
							return;
						}
						if(data.status == "failed"){
							alert(data.message);
							return;
						}
						if(data.status == "succeed"){
							var staticSalt = data.staticSalt;
	        				var dynamicSalt = data.dynamicSalt;
	        				//对密码根据混入的随机盐进行运算
	        				var password = $.md5(dynamicSalt+$.md5(staticSalt+$.md5($("#password").val())));
		        			var captcha = $("#captchaToLogin").val();
		        			var keepLoggedIn = $("#keepLoggedIn").is(':checked') ;
		        			var urlToDoLogin = "<%=contextPath %>/login";
		        			var paramsToDoLogin = {
		        				username : username
		        				, password : password
		        				, captcha : captcha
		        				, keepLoggedIn : keepLoggedIn
		        			};
		        			
		        			post(urlToDoLogin,paramsToDoLogin);
		        			
						}
	                },   
	                'json'  				  
	           	)
	           	.success(function(data){
	           		console.log("get salts succeed!");
	           	})
	   			.error(function(e){
	   				console.log("get salts failed,Error:",e);
	   			})
	   			.complete(function(e){
	   				console.log("get salts complete!");
	   			});
			});
			
			$("#register_form").submit(function(e){
				e.preventDefault();
				var phoneToRegister = $("#phoneToRegister").val();
				var emailToRegister = $("#emailToRegister").val();
				var passwordToRegister = $.md5($("#passwordToRegister").val());
				var captcha = $("#captchaToRegister").val();
				var urlToRegister = "<%=contextPath %>/register.do";
				var paramsToRegister = {
					phoneToRegister : phoneToRegister
					,emailToRegister : emailToRegister
					,passwordToRegister : passwordToRegister
					,captcha : captcha
				};
				//将混入随机盐的密码与其他登录信息一起提交后台验证
				$.post(
						urlToRegister,        			  
						paramsToRegister,
		          		function(data){ 	
							if(data.status == "succeed"){
								var urlHome = "<%=contextPath %>/";
								location.href = urlHome;	//跳转到首页
							}
							if(data.status == "failed"){
								if(data.message = "incorrectCaptcha"){
									alert("验证码错误");
								}
							}
		                },   
		                'json'  				  
		        )
		        .success(function(data){
		        	console.log("register succeed!");
		        })
		   		.error(function(e){
		   			console.log("register failed,Error:",e);
		   		})
		   		.complete(function(e){
		   			console.log("register complete!");
		   		});
			});
		});
		
	</script>
</html>