<%@page import="com.flight.trs.model.enums.FlightType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<link type="text/css" href="<%=contextPath %>/resources/bootstrapselect/css/bootstrap-select.css" rel="stylesheet" />
    
<style>

#div_daypicker{
	float:left;
	margin:0 5px 0 5px;
}

#div_daypicker li{
	background:#D4D4D4;
	float:left;
	width:90px;
	text-align:center;
	border:1px solid #000;
	padding:2px;
	cursor:pointer;
}

#div_daypicker li:hover{
	color:#FFFFFF;
	background:#436EEE;
}

#div_daypicker li.current{
	color:#FFFFFF;
	background:#63B8FF;
}

.last_next_week{
	background:#EDEDED;
	float:left;
	height:46px;
	width:40px;
  	line-height:46px;   
  	text-align:center;
  	overflow:hidden;  
  	cursor:pointer;
  	border:1px solid #000;
}

.last_next_week:hover{
	background:#436EEE;
	color:#FFFFFF;
}

.filter{
	width:100%;
	height:45px;
	padding-left:15px;
}

.filter select{
	height:30px;
	width:100px;
}

.flights{
	width:100%;
}
.segmet{
	float:left;
}
table {
    border-collapse: collapse;
    display: table;
    border-spacing: 2px;
    border-color: grey;
}
th{
	height:40px;
	font-family:Microsoft Yahei;
	background-color: #F5F5F5;
	text-align: center;
	font-style:normal;
	font-size:16px;
}
td, th {
    display: table-cell;
    vertical-align: inherit;
    text-align: center;
}

.logo{
width:130px;
height:57px;
}
.left{
width:120px;
height:57px;
text-align: right;
}
.center{
width:140px;
height:57px;
text-align: center;
}
.right{
width:120px;
height:57px;
}
.meals{
width:78px;
height:57px;
}
.tax{
width:119px;
height:57px;
}
.price{
height:57px;
}

/*.price{
	font-family: tahoma;
    font-size: 30px;
    color: #FF6600;
    line-height: 100%;
}*/

dfn{
	font-style:normal;
	font-size:14px;
	color: #333;
	font-family: Arial,Simsun;
    margin-right: 2px;
}

.price dfn{
    vertical-align: top;
}

.price2{
	font-family: tahoma;
	font-size: 18px;
    color: #BB0000;
    vertical-align: middle;
}


.spare{
	float:left;
	width:100%;
}

.seat{
	height:50%;
	margin-left:auto;
	margin-left:auto;
}
.seat > div{
	float:left;
	width:120px;
}
</style>
	
	<div class="row">
		<div class="col-md-10">
			<div class="panel panel-default">
				<div class="panel-heading">
	      			<h3><label>&nbsp;机票预订成功</label></h3>
	   			</div>
	   			<div class="panel-body" style="height:320px;font-size:16px;">
	   				<p>
	   					<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;机票预订成功。请留意稍后下发的短信，以免耽搁您的行程。
	   				</p>
	   				<br>
	   				<br>
	   				<br>
	   				<br>
	   				<br>
	   				<br>
	   				<div id="div_date_domestic_onward" style="width:100%;float:right;font-size:20px;padding:20px;">
		        		订单编号：&nbsp;&nbsp;${orderNo } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        		<a href="<%=contextPath %>/orderform?orderNo=${orderNo }" class="btn btn-success">查&nbsp;看&nbsp;详&nbsp;情</a>
	        			
		    			<br>
		        		交易金额：&nbsp;&nbsp;<span><dfn>¥</dfn>&nbsp;${businessTransaction }</span><br>
		        		支付流水号：&nbsp;&nbsp;${transSeqNo }
		        	</div>
	   			</div>
	   		</div>
	   	</div>
   </div>

<script type="text/javascript" src="<%=contextPath %>/resources/js/custom/citypicker-1.0.14.js"></script>
<script type="text/javascript" src="<%=contextPath %>/resources/bootstrapselect/js/bootstrap-select.js"></script>
<script>
	$(document).ready(function() {
		//航班查询
		$("#form_domesticFlightQuery").submit(function(event){
			 
			event.preventDefault();
			
			//$('div').css({
			//	"position":"", 
			//	"top":123,
			//	"left":123,
			//	"width":123,
			//	"height":123
			//	}).show(300).delay(3000).hide(300);
			
			var url = "<%=contextPath %>/flight/query";
			var depAirportCode = $("#ipt_depAirportCode").attr("data-code");
			var arrAirportCode = $("#ipt_arrAirportCode").attr("data-code");
			var _depDate = $("#ipt_depDate").val();
			var depDate = _depDate.substr(0,4)+_depDate.substr(5,2)+_depDate.substr(8,2);
			var params = {
					depAirportCode:depAirportCode
					,arrAirportCode:arrAirportCode
					,depDate:depDate
			};
			alert(depAirportCode);
			alert(arrAirportCode);
			alert(depDate);
			$.get(
		  			url,
		  			params,
		  			function(data){
		  				alert(data);
					},
					'json'
		  	).success(function(result){
		  		console.log("getCities succeed!");
		 	})
			.error(function(e){
				console.log("getCities failed, Error:",e);
			})
			.complete(function(e){
				console.log("getCities complete!");
			});
			
		});
	});
</script>


<%@ include file="footer.jsp"%>