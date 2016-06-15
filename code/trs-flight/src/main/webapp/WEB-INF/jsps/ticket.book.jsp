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
					  <div class="form-group form-inline  col-sm-6">
					    <label class="control-label ">联&nbsp;系&nbsp;人:&nbsp;&nbsp;&nbsp;&nbsp;</label>
					    <input id="ipt_contact_name" type="text" class="form-control"  placeholder="请输入联系人姓名" required/>
					  </div>
					  <div class="form-group form-inline col-sm-6">
					    <label for="inputPassword" class="control-label ">手机号码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					    <input id="ipt_contact_phone" type="text" class="form-control" required
					         placeholder="请输入联系人手机号码">
					  </div>
					    <label for="remark" class="col-sm-1 control-label">备注</label>
					    <div class="col-sm-11">
					      <textarea id="remark" class="form-control" rows="3" placeholder="如有特别需要备注的请在200字内简单描述"></textarea>
					    </div>
	   			</div>
	   		</div>
	   	</div>
   </div>
   
   <div class="row">
		<div class="col-md-10">
			<div id="div_paxes" class="panel panel-default">
				<div class="panel-heading">
	      			<h3 class="panel-title"><label>登机人信息</label></h3>
	   			</div>
	   			
	   			<div id="div_to_clone" class="panel-body div_pax" style="display:none;">
   					 <div class="form-group form-inline  col-sm-4">
					    <label class="control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
					    <input type="text" name="pax_name" class="form-control" placeholder="请输入联系人姓名"/>
					  </div>
					  <div class="form-group form-inline  col-sm-3">
					    <label for="sel_pax_id_type" class="control-label">证件类型:</label>
					    <select name="pax_id_type" class="form-control" id="sel_pax_id_type">
					    	<option value="ID card" >身份证</option>
					    	<option value="passport" >护照</option>
					    	<option value="other" >其它</option>
					    </select>
					  </div>
					  <div class="form-group form-inline  col-sm-3">
					    <label for="sel_pax_type" class="control-label">证件类型:</label>
					    <select name="pax_type" class="form-control">
					    	<option value="adult" >成人</option>
					    	<option value="child" >儿童</option>
					    	<option value="infant" >婴儿</option>
					    </select>
					  </div>
					  <div class="form-group form-inline  col-sm-4">
					    <label for="inputPassword" class="control-label">证件号码:</label>
					    <input type="text" name="pax_id_no" class="form-control" id="inputPassword" 
					         placeholder="请输入证件号码">
					  </div>
					  <div class="form-group form-inline  col-sm-4">
					    <label for="inputPassword" class="control-label">出生日期:</label>
					    <input name="pax_birthday" type="date" class="form-control" id="inputPassword" 
					         placeholder="若乘机人为婴儿则必填">
					  </div>
					  <div class="div_add_minus_pax_btn" id="div_date_domestic_onward" style="width:120px;float:left;">
		        		<button class="btn btn-primary btn_add_pax" style="float:right;">
	        				<span class="glyphicon glyphicon-plus"></span>&nbsp;添加乘机人
        				</button>
	    			  </div>
	   			</div>
	   			
	   			<div class="panel-body div_pax" style="display:none;">
   					 <div class="form-group form-inline  col-sm-4">
					    <label class="control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
					    <input type="text" name="pax_name" class="form-control" placeholder="请输入联系人姓名"/>
					  </div>
					  <div class="form-group form-inline  col-sm-3">
					    <label for="sel_pax_id_type" class="control-label">证件类型:</label>
					    <select name="pax_id_type" class="form-control" id="sel_pax_id_type">
					    	<option value="ID card" >身份证</option>
					    	<option value="passport" >护照</option>
					    	<option value="other" >其它</option>
					    </select>
					  </div>
					  <div class="form-group form-inline  col-sm-3">
					    <label for="sel_pax_type" class="control-label">证件类型:</label>
					    <select name="pax_type" class="form-control">
					    	<option value="adult" >成人</option>
					    	<option value="child" >儿童</option>
					    	<option value="infant" >婴儿</option>
					    </select>
					  </div>
					  <div class="form-group form-inline  col-sm-4">
					    <label for="inputPassword" class="control-label">证件号码:</label>
					    <input type="text" name="pax_id_no" class="form-control" id="inputPassword" 
					         placeholder="请输入证件号码">
					  </div>
					  <div class="form-group form-inline  col-sm-4">
					    <label for="inputPassword" class="control-label">出生日期:</label>
					    <input name="pax_birthday" type="date" class="form-control" id="inputPassword" 
					         placeholder="若乘机人为婴儿则必填">
					  </div>
					  <div class="div_add_minus_pax_btn" id="div_date_domestic_onward" style="width:120px;float:left;">
		        		<button id="btn_to_clone" class="btn btn-warning btn_minus_pax" style="float:right;">
	        				<span class="glyphicon glyphicon-minus"></span>&nbsp;删去乘机人
        				</button>
	    			  </div>
	   			</div>
	   			
	   			<div class="panel-body div_pax">
   					 <div class="form-group form-inline  col-sm-4">
					    <label class="control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
					    <input type="text" name="pax_name" class="form-control" placeholder="请输入联系人姓名"/>
					  </div>
					  <div class="form-group form-inline  col-sm-3">
					    <label for="sel_pax_id_type" class="control-label">证件类型:</label>
					    <select name="pax_id_type" class="form-control" id="sel_pax_id_type">
					    	<option value="ID card" >身份证</option>
					    	<option value="passport" >护照</option>
					    	<option value="other" >其它</option>
					    </select>
					  </div>
					  <div class="form-group form-inline  col-sm-3">
					    <label for="sel_pax_type" class="control-label">证件类型:</label>
					    <select name="pax_type" class="form-control">
					    	<option value="adult" >成人</option>
					    	<option value="child" >儿童</option>
					    	<option value="infant" >婴儿</option>
					    </select>
					  </div>
					  <div class="form-group form-inline  col-sm-4">
					    <label for="inputPassword" class="control-label">证件号码:</label>
					    <input type="text" name="pax_id_no" class="form-control" id="inputPassword" 
					         placeholder="请输入证件号码">
					  </div>
					  <div class="form-group form-inline  col-sm-4">
					    <label for="inputPassword" class="control-label">出生日期:</label>
					    <input name="pax_birthday" type="date" class="form-control" id="inputPassword" 
					         placeholder="若乘机人为婴儿则必填">
					  </div>
					  <div class="div_add_minus_pax_btn" id="div_date_domestic_onward" style="width:120px;float:left;">
		        		<button class="btn btn-primary btn_add_pax" style="float:right;">
	        				<span class="glyphicon glyphicon-plus"></span>&nbsp;添加乘机人
        				</button>
	    			  </div>
	   			</div>
	   			
	   		</div>
	   	</div>
   </div>
  
   <div style="text-align:center;">
   <div style="width:40%;float:left;">
		        		<button id="btn_previous" type="submit" class="btn btn-danger" style="float:right;">
	        			上&nbsp;&nbsp;一&nbsp;&nbsp;步
	        				</button>
		    			</div>
	<div style="width:55%;float:right;">
		        		<button  id="btn_next" type="submit" class="btn btn-warning" style="float:left;">
	        			下&nbsp;&nbsp;一&nbsp;&nbsp;步
	        				</button>
		    			</div>
   </div>
   



<script type="text/javascript" src="<%=contextPath %>/resources/js/custom/citypicker-1.0.14.js"></script>
<script type="text/javascript" src="<%=contextPath %>/resources/bootstrapselect/js/bootstrap-select.js"></script>
<script>
	var strDivPax = "<div class=\"panel-body div_pax\"><div class=\"form-group form-inline  col-sm-4\"><label class=\"control-label\">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label><input type=\"text\" name=\"pax_name\" class=\"form-control\" placeholder=\"请输入联系人姓名\"/></div>"
		+"<div class=\"form-group form-inline  col-sm-3\"><label for=\"sel_pax_type\" class=\"control-label\">证件类型:</label><select name=\"pax_type\" class=\"form-control\"><option value=\"adult\" >成人</option><option value=\"child\" >儿童</option><option value=\"infant\" >婴儿</option>"
		+"</select></div><div class=\"form-group form-inline  col-sm-3\"><label for=\"sel_pax_id_type\" class=\"control-label\">证件类型:</label><select name=\"pax_id_type\" class=\"form-control\" id=\"sel_pax_id_type\"><option value=\"ID card\" >身份证</option><option value=\"passport\" >护照</option>"
		+"<option value=\"other\" >其它</option></select></div><div class=\"form-group form-inline  col-sm-4\"><label for=\"inputPassword\" class=\"control-label\">证件号码:</label><input type=\"text\" name=\"pax_id_no\" class=\"form-control\" id=\"inputPassword\" placeholder=\"请输入证件号码\">"
		+"</div><div class=\"form-group form-inline  col-sm-4\"><label for=\"inputPassword\" class=\"control-label\">出生日期:</label><input name=\"pax_birthday\" type=\"date\" class=\"form-control\" id=\"inputPassword\" placeholder=\"若乘机人为婴儿则必填\"></div><button class=\"btn btn-primary btn_minus_pax\" style=\"float:right;\"><span class=\"glyphicon glyphicon-minus\"></span>&nbsp;删去乘机人</button></div>";
	var strAddBtn = "<button class=\"btn btn-primary btn_add_pax\" style=\"float:right;\"><span class=\"glyphicon glyphicon-plus\"></span>&nbsp;添加乘机人</button>";
	
	var strMinusBtn = "<button class=\"btn btn-warning btn_minus_pax\" style=\"float:right;\"><span class=\"glyphicon glyphicon-minus\"></span>&nbsp;删去乘机人</button>";

	$(document).ready(function() {
		
		$(".btn_minus_pax").on("click",function(){
			$thisPax = $(this).closest(".div_pax").remove(); 
		});
		
		$(".btn_add_pax").on("click",function(){
			var $divClone = $("#div_to_clone").clone(true);
			$divClone.removeAttr("id");
			$divClone.appendTo($("#div_paxes"));
			$divClone.show();
			
			var $minusBtn = $("#btn_to_clone").clone(true);
			$(this).replaceWith($minusBtn);
		});
		
		$("#btn_previous").click(function(){
			history.go(-1);
		});
		
		$("#btn_next").click(function(){
			var orderInfo = {};
			var contactName = $("#ipt_contact_name").val();
			orderInfo.contactName = contactName;
			if(contactName==""){
				alert("联系人姓名不能为空");
				$("#ipt_contact_name").focus();
				return;
			}
			var contactPhone = $("#ipt_contact_phone").val();
			if(contactPhone==""){
				alert("联系方式不能为空");
				$("#ipt_contact_phone").focus();
				return;
			}
			orderInfo.contactPhone = contactPhone;
			var remark = $("#remark").val();
			orderInfo.remark = remark;
			var paxes = [];
			var flag = false;
			$.each($(".div_pax:visible"),function(idx,item){
				var $iptPaxName = $(item).find("[name=pax_name]").eq(0);
				var paxName = $iptPaxName.val();
				if(paxName==""){
					alert("乘机人姓名不能为空！");
					$iptPaxName.focus();
					flag = true;
					return false;
				}
				var $iptPaxType = $(item).find("[name=pax_type]").eq(0);
				var paxType = $iptPaxType.val();
				var $iptPaxIdType = $(item).find("[name=pax_id_type]").eq(0);
				var paxIdType = $iptPaxIdType.val();
				var $iptPaxIdNo = $(item).find("[name=pax_id_no]").eq(0);
				var paxIdNo = $iptPaxIdNo.val();
				if(paxIdNo==""){
					alert("乘机人证件号不能为空！");
					$iptPaxIdNo.focus();
					flag = true;
					return false;
				}
				var $iptPaxBirthday = $(item).find("[name=pax_birthday]").eq(0);
				var paxBirthday = $iptPaxBirthday.val();
				if(paxType=="infant"&&paxBirthday==""){
					alert("婴儿必须提供出生日期！");
					$iptPaxBirthday.focus();
					flag = true;
					return false;
				}
				paxes[idx]={};
				paxes[idx].paxName = paxName;
				paxes[idx].paxType = paxType;
				paxes[idx].paxIdType = paxIdType;
				paxes[idx].paxIdNo = paxIdNo;
				paxes[idx].paxBirthday = paxBirthday;
			});
			if(flag){
				return;
			}
			if(paxes.length==0){
				alert("至少一名乘机人");
				return;
			}
			orderInfo.paxes = paxes;
			var url="<%=contextPath %>/orderform/order";
			
			$.ajax({
				type:"post"
				,url:"<%=contextPath %>/orderform/order"
				,data:JSON.stringify(orderInfo)
				,dataType:"json"
				,contentType:"application/json;charset=utf-8"
				,success:function(data){
					if(data.status=="success"){
						location.href="<%=contextPath %>/orderform/reconfirm";
					}
					else{
						alert("提交失败，请重新选择航班");
					}
				}
				,error:function(e){
					console.log("getCities failed, Error:",e);
				}
			});
			
			//post(url,JSON.stringify("orderInfo="+orderInfo));
		});
		
	});

  	function post(URL, PARAMS) {        
	    var temp = document.createElement("form");        
	    temp.action = URL;        
	    temp.method = "post";        
	    temp.style.display = "none";        
	    for (var x in PARAMS) {        
	        var opt = document.createElement("textarea");        
	        opt.name = x;        
	        opt.value = PARAMS[x];        
	        // alert(opt.name)        
	        temp.appendChild(opt);        
	    }        
	    document.body.appendChild(temp);        
	    temp.submit();        
	    return temp;        
	}  
</script>


<%@ include file="footer.jsp"%>