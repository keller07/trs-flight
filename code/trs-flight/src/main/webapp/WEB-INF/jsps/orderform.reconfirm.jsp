<%@page 
	import="com.flight.trs.model.enums.FlightType"
	import="com.flight.trs.model.info.Pax"
	import="com.flight.trs.util.PaxUtil"
	
%>
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
	      			<h3 class="panel-title"><label>机票信息</label></h3>
	   			</div>
	   			<div class="panel-body">
	   				<div id="div_flights" class="flights">
	   					<div class="segmet">
		   					<table style="width:100%;table-layout: fixed;">
		   						<thead>
		   							<tr>
		   							<th>单&nbsp;&nbsp;&nbsp;&nbsp;程</th>
		   							<th>航班信息</th>
		   							<th>起&nbsp;&nbsp;&nbsp;&nbsp;飞</th>
		   							<th>抵&nbsp;&nbsp;&nbsp;&nbsp;达</th>
		   							<th>机票单价</th>
		   							<th>机建燃油</th>
		   							<th>单张总价</th>
		   							</tr>
		   						</thead>
		   						<tbody>
		   							<tr style="font-family: Tahoma, Arial, \5b8b\4f53, sans-serif;">
		   								<td class="meals">
		   									<div><strong>${depCityName}</strong><span  style="color: #999;">——</span><strong>${arrCityName}</strong></div>
		   								</td>
		   								<td class="logo">
											<div>
					   							<div>
					   							<strong>${carrierName}</strong>
					   							<span>${flightNo}</span>
					   							</div>
					   						</div>        
					   						<div style="color: #999;">
					   							<span>${acName }</span>
					   						</div>     
					   						<span style="color: #999;">${meals }餐食</span>
										</td>
		   								<td class="right">
											<div><strong class="time">${depDate}</strong></div>
				   							<div><strong>${depTime}</strong></div>
					   						<div>${depAirportName}T${depTerminal}</div>
										</td>
		   								<td class="right">
		   									<div><strong class="time">${arrDate}</strong></div>
		   									<div><strong class="time">${arrTime}</strong></div>
		   									<div>${arrAirportName}T${arrTerminal}</div>
		   								</td>
		   								
		   								<td class="price">
		   									<div style="color: #999;">
					   							<span>${classOfService } ( ${discount } 折)</span>
					   						</div>   
		   									<div> 
		   										<span class="price2"><dfn>¥</dfn>${ticketFee }</span>
		   									</div>  
		   								</td>
		   								<td>
		   									机建+燃油<br><dfn>¥</dfn>${airportTax } + <dfn>¥</dfn>${baf }
		   								</td>
		   								
		   								<td class="price" style="font-size: 30px; color: #FF6600;">
		   									<span class="price"><dfn>¥</dfn>${price }</span>
		   								</td>
		   							</tr>
		   						</tbody>
		   					</table>
	   					</div>
	   				</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-10">
			<div class="panel panel-default">
				<div class="panel-heading">
	      			<h3 class="panel-title"><label>联系人信息</label></h3>
	   			</div>
	   			<div class="panel-body">
	   				<form class="form-inline" role="form">
					  <div class="form-group form-inline  col-sm-4">
					    <label class="control-label">联&nbsp;系&nbsp;人:</label>
					    <label class="control-label">${contactName }</label>
					  </div>
					  <div class="form-group col-sm-4">
					    <label for="inputPassword" class="control-label">手机号码:</label>
					    <label class="control-label">${contactPhone }</label>
					  </div>
					  <div class="form-group col-sm-10">
					    <label for="inputPassword" class="control-label">备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
					    <label class="control-label">${remark }</label>
					  </div>
					</form>
	   			</div>
	   		</div>
	   	</div>
   </div>
   
   <div class="row">
		<div class="col-md-10">
			<div class="panel panel-default">
				<div class="panel-heading">
	      			<h3 class="panel-title"><label>登机人信息</label></h3>
	   			</div>
	   			<div class="panel-body">
	   				<div class="form-group form-inline  col-sm-12">
					    <label class="control-label">登机人数:</label>
					    <label class="control-label">${paxNumber }人</label>
				  	</div>
			  		
					   <table class="table table-condensed">
					      <thead>
					         <tr>
					         	<th>序号</th>
					            <th>姓名</th>
					            <th>类型</th>
					            <th>证件类型</th>
					            <th>证件编号</th>
					            <th>出生日期</th>
					         </tr>
					      </thead>
					      <tbody>
					      <%
					    	List<Pax> paxes = (List<Pax>)request.getAttribute("paxes");
					    	for (int i=0;i<paxes.size();i++) {
					    		out.println("<tr>");
					    		out.println("<td>"+(i+1)+"</td>");
					    		out.println("<td>"+paxes.get(i).getPaxName()+"</td>");
					    		out.println("<td>"+PaxUtil.getPaxTypeAlia(paxes.get(i).getPaxType())+"</td>");
					    		out.println("<td>"+PaxUtil.getPaxIdTypeAlia(paxes.get(i).getPaxIdType())+"</td>");
					    		out.println("<td>"+paxes.get(i).getPaxIdNo()+"</td>");
					    		out.println("<td>"+paxes.get(i).getPaxBirthday()+"</td>");
								out.println("</tr>");
							}
					  		%>
					      </tbody>
					   </table>
	   			</div>
	   		</div>
	   	</div>
   </div>
	
<div class="row">
		<div class="col-md-10">
			<div class="panel panel-default">
				<div class="panel-heading">
	      			<h3 class="panel-title"><label>订单信息</label></h3>
	   			</div>
	   			<div class="panel-body">
	   				<div class="form-group form-inline  col-sm-8">
				  	</div>
	   				<div class="form-group form-inline  col-sm-2">
					    <label class="control-label">订单价格:</label>
				  	</div>
			  		<div class="col-sm-2" style="border-bottom:2px solid;font-size:20px;text-align:right;">
					    <span class="price2"><dfn>¥</dfn>${price }</span><span><dfn>&nbsp;&nbsp;&#215;</dfn>&nbsp;${paxNumber }</span>
				  	</div>
					    
				 	<div class="form-group form-inline  col-sm-10">
				 	<div class="form-group"  style="font-size: 18px;">
				 		<label class="radio-inline">支付方式：</label>
                        <label class="radio-inline">
                          <input type="radio" name="payment_mode" value="local" checked>本地账户
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="payment_mode" value="alipay">支付宝
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="payment_mode" value="wechat">微信支付
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="payment_mode" value="bank card">网银支付
                        </label>
                      </div>
				  	</div>
			  		<div class="col-sm-2" style="font-size: 30px; color: #FF6600;text-align:right;">
					    <span><dfn>¥</dfn>${totalPrice }</span>
				  	</div>
					 
	   			</div>
	   		</div>
	   	</div>
   </div>

   <div style="text-align:center;">
   <div id="div_date_domestic_onward" style="width:70%;float:left;">
		        		<button id="btn_previous" type="submit" class="btn btn-danger" style="float:right;">
	        			上&nbsp;&nbsp;一&nbsp;&nbsp;步
	        				</button>
		    			</div>
	<div id="div_date_domestic_onward" style="width:25%;float:right;">
		        		<button id="btn_reconfirm"  type="submit" class="btn btn-warning" style="float:left;">
	        			确&nbsp;认&nbsp;提&nbsp;交
	        				</button>
		    			</div>
   </div>

<script type="text/javascript" src="<%=contextPath %>/resources/js/custom/citypicker-1.0.14.js"></script>
<script type="text/javascript" src="<%=contextPath %>/resources/bootstrapselect/js/bootstrap-select.js"></script>
<script>

$("#btn_previous").click(function(){
	history.go(-1);
});

$("#btn_reconfirm").click(function(){
	var paymentMode = $("[name=payment_mode]:checked").val();
	var url = "<%=contextPath %>/orderform/do";
	var params = {
			paymentMode:paymentMode
	};
	$.post(
  			url,
  			params,
  			function(data){
  				if(data.status == "success"){
  					location.href="<%=contextPath %>/orderform/succeed";
  				}
  				else{
  					location.href="<%=contextPath %>/error";
  				}
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


	$(document).ready(function() {
		
	});
</script>


<%@ include file="footer.jsp"%>