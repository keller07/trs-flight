<%@page import="com.flight.trs.model.enums.FlightType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<link type="text/css" href="<%=contextPath %>/resources/bootstrapselect/css/bootstrap-select.css" rel="stylesheet" />
    
<style>
body {padding: 0; margin: 0;}
body,html{height: 100%;}
#div_daypicker{
	float:left;
	margin:0px;
}

#div_daypicker li{
	background:#F5FFFA;
	float:left;
	width:100px;
	text-align:center;
	border:1px solid #BDBDBD;
	border-bottom:1px solid #3A5FCD;
	padding:2px;
	cursor:pointer;
}

#div_daypicker li:hover{
	color:#FFFFFF;
	background:#436EEE;
}

#div_daypicker li.current{
	color:#63B8FF;
	background:#FFFFFF;
	border-left:1px solid #3A5FCD;
	border-right:1px solid #3A5FCD;
	border-top:3px solid #3A5FCD;
	border-bottom:0px;
}

.last_next_week{
	background:#FFFFFF;
	float:left;
	height:46px;
	width:40px;
	font-size:20px;
  	line-height:46px;   
  	text-align:center;
  	overflow:hidden;  
  	cursor:pointer;
  	border:1px solid #BDBDBD;
}

.last_next_week:hover{
	color:#436EEE;
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
.flight{
	float:left;
	border-top: 1px solid;
	border-bottom: 1px solid;
}
.segment{
	 width:100%;
	 table-layout: fixed;
}
table {
    border-collapse: collapse;
    display: table;
    border-spacing: 2px;
    border-color: grey;
    background-color: #F5F5F5;
}
tr{
	font-family: Tahoma, Arial, \5b8b\4f53, sans-serif;
}
td, th {
    display: table-cell;
    vertical-align: inherit;
}

.logo{
	width:140px;
	height:57px;
}
.craft{
	color: #999;
}
.left{
	width:120px;
	height:57px;
	text-align: right;
}
.deparrtime{
	font-size:18px;
}
.center{
	width:140px;
	height:57px;
	text-align: center;
}
.tra_or_stops{
	color:#919191;
	font-size:14px;
	padding-top:8px;
}

.arrows{
	background:url(<%=contextPath %>/resources/imgs/arrows.png) no-repeat 20px center;
}
.tra_or_stops_signal{
	margin-left:62px;
	margin-top:-1px;
	text-align:center;
	height:17px;
	width:17px;
	background:#CCCCCC;
	color:#1C86EE;
	font-size:12px;
}
.tra_or_stops_signal:hover{
	background:#1C86EE;
	color:#FFFFFF;
}

.duration_and_short{
	color:#919191;
	font-size:12px;
}

.right{
	width:120px;
	height:57px;
}
.meals{
	width:78px;
	height:57px;
}
.meals > div{
text-align:center;
}

.tax{
width:119px;
height:57px;
}

.price{
	height:57px;
	font-family: tahoma;
    font-size: 30px;
    color: #FF6600;
    line-height: 100%;
}

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

.cl_service{
	font-size:12px;
	font-style: normal;
    color: #999;
}

.cl_service_selected{
	font-size:12px;
	font-style: normal;
}


.price2{
	font-family: tahoma;
	font-size: 14px;
    color: #B5B5B5;
    vertical-align: middle;
}

.price2_selected{
	font-family: tahoma;
    font-size: 18px;
    color: #BB0000;
    vertical-align: middle;
    line-height: 100%;
}

.spare{
	float:left;
	width:100%;
	height:46px;
	display:table;
	/*border-bottom: 1px dashed #111;*/
}
.spare > div{
	float:left;
	display:table-cell;
	vertical-align:middle;
	height:100%;
}

.spare:hover{
	background:#E0FFFF;
}

.proxy{
	width:16.5%;
}

.proxy{
	width:16.5%;
}

.insure{
	width:33.5%;
}

.paxguide{
	 width:10%; 
	 text-align:center;
	 padding:auto;
	 margin:auto;
}
.back_service{
	 background:#FFFFFF;
	 border:1px solid #333399;
	 padding:auto;
	 margin-top:12px;
	 font-size: 14px;
}
.seats{
	width:22%;
}
.seat{
	widht:100;
	float:left;
	margin-left:auto;
}
.seat_single{
	margin-top:12px;
}
.seat > div{
	float:left;
	width:100px;
}
.book{
	width:10%;
}

.btn-book{
 	width:80px;
 	margin-top:5px;
}

.remaining_count{
	width:8%;
}

.remaining_count > div{
	color:#EE3B3B;
	margin-top:12px;
	margin-left:16px;
}

a:link{
	text-decoration:none;
}
a:visited{
	text-decoration:none;
}
a:hover{
	text-decoration:none;
}
a:active{
	text-decoration:none;
}

.gray2{
	color: #999;
}

</style>

	<div class="row">
		<div class="col-md-10">
			<div class="panel panel-default">
				<div class="panel-body">
					<form id="form_domesticFlightQuery" name="domesticFlightQuery" action="<%=contextPath %>/flight/query" method="get">
						<div style="width:70px;float:left;">
							<select id="sel_flight_type" style="height:30px;">
							<%
								String domesticFlightType = (String)request.getAttribute("domesticFlightType");
								String flightType = "";
								if ( null != domesticFlightType ) {
									if ( FlightType.SINGLE_TRIP.equals(domesticFlightType) ) {
										flightType = "单程";
										out.println("<option value=\"single_trip\" selected>单程</option>");
										out.println("<option value=\"round_trip\">往返</option>");
										out.println("<option value=\"connecting_flight\">联程</option>");
									} 
									else if (FlightType.ROUND_TRIP.equals(domesticFlightType)){
										flightType = "往返";
										out.println("<option value=\"single_trip\">单程</option>");
										out.println("<option value=\"round_trip\" selected>往返</option>");
										out.println("<option value=\"connecting_flight\">联程</option>");
									} 
									else{
										flightType = "联程";
										out.println("<option value=\"single_trip\">单程</option>");
										out.println("<option value=\"round_trip\">往返</option>");
										out.println("<option value=\"connecting_flight\" selected>联程</option>");
									}
								}
							%>
							</select>
						</div>
						<div style="width:180px;float:left;margin-right:15px;">
					  	  	<p>出发城市:<input id="ipt_depAirportCode" type="text" class="citypicker" value=${depCityName} data-code=${depAirportCode} style="width:120px;height:30px;"/></p>
				  	    </div>
					  	<div style="width:180px;float:left;margin-right:15px;">
					  	  	<p>到达城市:<input id="ipt_arrAirportCode" type="text" class="citypicker" value=${arrCityName} data-code=${arrAirportCode} style="width:120px;height:30px;"/></p>
					  	</div>
					    <div style="width:248px;float:left;">
					    	<%
					    		String depDate = (String)request.getAttribute("depDate");
					    		String depDateFormated = "";
					    		String depDateLocal = "";
					    		if (null != depDate){
					    			depDateFormated = depDate.substring(0, 4)+"-"+depDate.substring(4, 6)+"-"+depDate.substring(6, 8);
					    			depDateLocal = depDate.substring(0, 4)+"年"+Integer.valueOf(depDate.substring(4, 6))+"月"+depDate.substring(6, 8)+"日";
					    		}
					    	%>
					        <p>出发日期:<input id="ipt_depDate" name="ipt_depDate" type="date" value=<%=depDateFormated %> style="width:140px;height:30px;margin-right:5px;"/></p>
					    </div>
					    <div style="width:70px;float:left;">
					        <a id="a_advanced_query" href="javascript:;">高级查询</a><b class="caret"></b>
					    </div>
					    <div style="width:120px;float:left;">
					        <button id="btn_requery" type="submit" class="btn btn-primary" style="float:right;">
				        		<span class="glyphicon glyphicon-search"></span>&nbsp;重新查询
				        	</button>
					    </div>
					    <div id="div_advanced_query" style="width:100%;float:left;display:none;">
					    	<div style="width:100%;height:30px;float:">
                       			出行人数:
                       			<select id="sel_mix_count">
                       				<option value="0" selected>&nbsp;不限</option>
                       				<option value="1">&nbsp;1</option>
                       				<option value="2">&nbsp;2</option>
                       				<option value="3">&nbsp;3</option>
                       				<option value="4">&nbsp;4</option>
                       				<option value="5">&nbsp;5</option>
                       				<option value="6">&nbsp;6</option>
                       				<option value="7">&nbsp;7</option>
                       				<option value="8">&nbsp;8</option>
                       				<option value="9">&nbsp;9</option>
                       				<option value="10">&nbsp;10</option>
                       			</select>
                       			&nbsp;&nbsp;&nbsp;
                       			出发时间:
                       			<select id="sel_dep_time_range">
									<option value="" selected>不限</option>
									<option value="00001000">00:00-10:00</option>
									<option value="10001400">10:00-14:00</option>
									<option value="14001900">14:00-19:00</option>
									<option value="19002400">19:00-24:00</option>
                       			</select>
                       			&nbsp;&nbsp;&nbsp;
                       			
							   航空公司:
                       			<select id="sel_carrier_code">
                       				<option value="" selected>不限</option>
                       			</select>
                       			&nbsp;&nbsp;&nbsp;
                       			舱位等级:<select id="sel_class_of_service">
									<option value="" selected>不限</option>
									<option value="first,business">头等/商务舱</option>
									<option value="economic">经济舱</option>
                       			</select>
                       			&nbsp;&nbsp;&nbsp;
                       			<label class="checkbox-inline">
							      <input id="ckb_nonstop" type="checkbox" value=""> 直达
							   </label>
                       		</div>
					    </div>
				    </form>
			    </div>
		    </div>
	    </div>
	</div>
	<div class="row">
		<div class="col-md-10">
			<div class="panel panel-default">
	   			<div class="panel-heading" data-depAirportCode=${depAirportCode} data-arrAirportCode=${arrAirportCode}>
	      			<h3 class="panel-title"><label><%=flightType %></label>：<label>${depCityName}</label>——<label>${arrCityName}</label>&nbsp;&nbsp;&nbsp;&nbsp;<label>出发日期:</label><label><%=depDateLocal %></label></h3>
	   			</div>
	   			<div class="panel-body">
	   				<div id="div_last_week" class="last_next_week">
	   					<span class="glyphicon glyphicon-chevron-left"></span>
	   				</div>
	   				<div id="div_daypicker">
	   					<ul id="ul_daypicker">
			      			<li><div>周一</div><div>06-13</div></li>
			      			<li><div>周二</div><div>06-14</div></li>
			      			<li class="current"><div>周三</div><div>06-15</div></li>
			      			<li><div>周四</div><div>06-16</div></li>
			      			<li><div>周五</div><div>06-17</div></li>
			      			<li><div>周六</div><div>06-18</div></li>
			      			<li><div>周日</div><div>06-19</div></li>
	      				</ul>
	   				</div>
	   				<div id="div_next_week" class="last_next_week">
	   					<span class="glyphicon glyphicon-chevron-right"></span>
	   				</div>
	   			</div>
	   			<div id="div_filters" class="filter">
   					排序方式：
   					<label class="radio-inline">
                         <input type="radio" name="domestic_flight_type" value="single_trip" checked>
                         	<span class="glyphicon glyphicon-arrow-up"></span>起飞时间
                       </label>
                       <label class="radio-inline">
                         <input type="radio" name="domestic_flight_type" value="round_trip">
                         <span class="glyphicon glyphicon-arrow-down"></span>舱位全价
                       </label>
                       &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;筛选：
   					<select>
   						<option value="">起飞时段</option>
   					</select>
   					<select>
   						<option value="">航空公司</option>
   					</select>
   					<select>
   						<option value="">计划机型</option>
   					</select>
   					<select>
   						<option value="">舱位等级</option>
   					</select>
   				</div>
	   				
   				<div id="div_flights" class="flights">
   				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="<%=contextPath %>/resources/js/custom/citypicker-1.0.14.js"></script>
<script type="text/javascript" src="<%=contextPath %>/resources/bootstrapselect/js/bootstrap-select.js"></script>
<script>
	//航班查询
	$("#form_domesticFlightQuery").submit(function(event){
		event.preventDefault();
		do_query_flight_normal();
	});
	
	$("#a_advanced_query").click(function(){
		$("#div_advanced_query").toggle();
	});
	
	$("#ul_daypicker li:not(.current)").click(function(){
	});
	
	//初始化所有航空公司输入框
  	function initCarrierSel(){
  		var url = "<%=contextPath %>/carrier";
  		var params = {};
		$.get(
	  			url,
	  			params,
	  			function(data){
	  				//绑定城市选择输入框
	  				$.each(data,function(idx,carrier){
	  					$carrier = $("<option></option>");
	  					$carrier.attr("value",carrier.code);
	  					$carrier.html(carrier.name);
	  					$("#sel_carrier_code").append($carrier);
	  				});
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
	}
	
	$(document).ready(function() {
		initCarrierSel();
		<%
			String carrierCode = (String)request.getAttribute("carrierCode");
			Character nonStop = (Character)request.getAttribute("nonStop");
			String depTimeRange = (String)request.getAttribute("depTimeRange");
			String classOfServices = (String)request.getAttribute("classOfServices");
			Integer mixCountOfSpareSeats = (Integer)request.getAttribute("mixCountOfSpareSeats");
			boolean ifShowAdvancedQuery = false;
			if(null!=carrierCode){
				out.println("$(\"#sel_carrier_code\").val(\""+carrierCode+"\")");
				ifShowAdvancedQuery = true;
			}
			if(null!=nonStop){
				out.println("$(\"#ckb_nonstop\").attr(\"checked\",true)");
				ifShowAdvancedQuery = true;
			}
			if(null!=depTimeRange){
				out.println("$(\"#sel_dep_time_range\").val(\""+depTimeRange+"\")");
				ifShowAdvancedQuery = true;
			}
			if(null!=classOfServices){
				out.println("$(\"#sel_class_of_service\").val(\""+classOfServices+"\")");
				ifShowAdvancedQuery = true;
			}
			if(null!=mixCountOfSpareSeats){
				out.println("$(\"#sel_mix_count\").val("+mixCountOfSpareSeats+")");
				ifShowAdvancedQuery = true;
			}
			if(ifShowAdvancedQuery){
				out.println("$(\"#div_advanced_query\").show();");
			}
		%>
		initCityPickers();
		do_query_flight_normal();
	});
	
	function do_query_flight_normal(){
		var params = {};
		var depAirportCode = $("#ipt_depAirportCode").attr("data-code");
		var arrAirportCode = $("#ipt_arrAirportCode").attr("data-code");
		var _depDate = $("#ipt_depDate").val();
		var depDate = _depDate.substr(0,4)+_depDate.substr(5,2)+_depDate.substr(8,2);
		params.depAirportCode = depAirportCode;
		params.arrAirportCode = arrAirportCode;
		params.depDate = depDate;
		var carrierCode = $("#sel_carrier_code").val();
		if(carrierCode!=""){
			params.carrierCode = carrierCode;
		}
		if($("#ckb_nonstop").is(':checked')){
			params.nonStop = 'y';
  		}
		var depTimeRange = $("#sel_dep_time_range").val();
		if(depTimeRange!=""){
			params.depTimeRange = depTimeRange;
		}
		var classOfServices = $("#sel_class_of_service").val();
		if(classOfServices!=""){
			params.classOfServices = classOfServices;
		}
		var mixCountOfSpareSeats = $("#sel_mix_count").val();
		if(mixCountOfSpareSeats!=0){
			params.mixCountOfSpareSeats = mixCountOfSpareSeats;
		}
		do_query_flight(params);
	}
	
	function do_query_flight(params){
		var url = "<%=contextPath %>/flight/query";
		$.get(
	  			url,
	  			params,
	  			function(data){
	  				init_flights(data);		
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
	}
	
	function init_flights(flights){
		$("#div_flights").empty();
		//查不到匹配的航班
		if(flights.length == 0){
			alert("查找不到匹配的航班");
			return;
		}
		$.each(flights,function(idx,flight){
			var $flight = $("<div class=\"flight\"></div>");
			//segment的构造
			var $segment = $("<table class=\"segment\"></table>");
			var $tbody = $("<tbody></tbody>");
			var $trow = $("<tr></tr>");
			var carrierCode = flight.carrierCode;
			$segment.attr("carrierCode",carrierCode);
			var carrierName = flight.carrierName;
			$segment.attr("carrierName",carrierName);
			var totalFlightNo = flight.totalFlightNo;
			$segment.attr("totalFlightNo",totalFlightNo);
			var depAirportCode = flight.depAirportCode;
			$segment.attr("depAirportCode",depAirportCode);
			var depAirportName = flight.depAirportName;
			var depTerminal = flight.depTerminal;
			var depTime = flight.depTime;
			$segment.attr("depTime",depTime);
			var arrAirportCode = flight.arrAirportCode;
			$segment.attr("arrAirportCode",arrAirportCode);
			var arrAirportName = flight.arrAirportName;
			var arrTerminal = flight.arrTerminal;
			var arrTime = flight.arrTime;
			var depDate = flight.depDate;
			$segment.attr("depDate",depDate);
			var acCode = flight.acCode;
			$segment.attr("acCode",acCode);
			var acName = flight.acName;
			$segment.attr("acName",acName);
			var stopCodes = flight.stopCodes;
			var stopCityNames = flight.stopCityNames;
			var mealService = flight.mealService;
			var ticketFee = flight.ticketFee;
			var airportTax = flight.airportTax;
			var baf = flight.baf;
			var duration = flight.duration;
			var flightShort = flight.flightShort;
			var traFlightNo = flight.traFlightNo;
			var traAirportCode = flight.traAirportCode;
			var traCityName = flight.traCityName;
			var traAirportName = flight.traAirportName;
			var traArrTime = flight.traArrTime;
			var traArrTerminal = flight.traArrTerminal;
			var traDepTime = flight.traDepTime;
			var traDepTerminal = flight.traDepTerminal;
			var traAcCode = flight.traAcCode;
			var traAcName = flight.traAcName;
			
			//logo
			var $logo = $("<td class=\"logo\"></td>");
			var $carrier = $("<div class=\"carrier\"><strong>"+carrierName+"</strong><span>"+totalFlightNo+"<span></div>");
			var $craft = $("<div class=\"craft\"><span>"+acName+"</span></div>");
			$logo.append($carrier);
			$logo.append($craft);
			$trow.append($logo);
			//left
			var $left = $("<td class=\"left\"></td>");
			var $deptime = $("<div class=\"deparrtime\"><strong>"+depTime.substr(0,2)+":"+depTime.substr(2,2)+"</strong></div>");
			var $depairport = $("<div>"+depAirportName+"T"+depTerminal+"</div>");
			$left.append($deptime);
			$left.append($depairport);
			$trow.append($left);
			//center
			var $center = $("<td class=\"center\"></td>");
			var $tra_or_stops;
			var $arrows;
			if(traAirportCode==null){
				if(stopCodes.substr(0,1)=="0"){
					$tra_or_stops = $("<div class=\"tra_or_stops\"><i>&nbsp;</i></div>");
					$arrows = $("<div class=\"arrows\">&nbsp;</div>");
					
				}else{
					$tra_or_stops = $("<div class=\"tra_or_stops\"><i>"+stopCityNames+"</i></div>");
					$arrows = $("<div class=\"arrows\"><div class=\"tra_or_stops_signal\">停</div></div>");
				}
			}
			else{
				$tra_or_stops = $("<div class=\"tra_or_stops\"><i>"+traCityName+"</i></div>");
				$arrows = $("<div class=\"arrows\"><div class=\"tra_or_stops_signal\">转</div></div>");
			}
			$center.append($tra_or_stops);
			$center.append($arrows);
			var hour_of_duration = parseInt(duration.substr(0,2));
			var flight_short = parseInt(flightShort);
			var $duration_and_short = $("<div class=\"duration_and_short\"><i><span>"+hour_of_duration+"</span>h,<span>"+flight_short+"</span>km</i></div>");
			$center.append($duration_and_short);
			$trow.append($center);
			//right
			var $right = $("<td class=\"right\"></td>");
			var $arrtime = $("<div class=\"deparrtime\"><strong>"+arrTime.substr(1,2)+":"+arrTime.substr(3,2)+"</strong></div>");
			var $arrairport = $("<div>"+arrAirportName+"T"+arrTerminal+"</div>");
			$right.append($arrtime);
			$right.append($arrairport);
			$trow.append($right);
			//meals
			var offer_meal = mealService == 'n'?"无":"有";
			var $meals = $("<td class=\"meals\"><div><strong>餐食</strong></div><div class=\"gray2\">"+offer_meal+"</div></td>");
			$trow.append($meals);
			//tax
			var $tax = $("<td class=\"tax\"><div><strong>机建+燃油</strong></div></td>");
			var $_tax = $("<div><span class=\"gray2\"><dfn>¥</dfn>"+parseInt(airportTax)+"&nbsp;+&nbsp;<dfn>¥</dfn>"+parseInt(baf)+"</span></div>");
			$tax.append($_tax);
			$trow.append($tax);
			//price
			var $price = $("<td class=\"price\"><span class=\"price\"><dfn>¥</dfn>"+parseInt(ticketFee)+"</span></td>");
			$trow.append($price);
			$tbody.append($trow);
			$segment.append($tbody);
			$flight.append($segment);
			
			var $spare = $("<div class=\"spare\"></div>");
			
			var $proxy = $("<div class=\"proxy\">&nbsp;</div>");
			$spare.append($proxy);
			
			var $paxguide = $("<div class=\"paxguide\"><div class=\"back_service tooltip-toggle\" data-toggle=\"tooltip\" data-placement=\"bottom\" data-trigger=\"manual\" title=\" \" >退改签</div>	</div>");
			$spare.append($paxguide);
			
			var $insure = $("<div class=\"insure\">&nbsp;</div>");
			$spare.append($insure);
			
			//可选择的舱位类型数目
			var clsCount = flight.cls.length;
			var $seats = $("<div class=\"seats\"></div>");
			if(clsCount == 1){
				var $seat = $("<div class=\"seat seat_single\"></div>");
				var clCode = flight.cls[0].clCode;
				$seat.attr("clCode",clCode);
				var classOfService = flight.cls[0].classOfService;
				var discount = flight.cls[0].discount;
				var ticketFee = flight.cls[0].ticketFee;
				var countOfSpareSeats = flight.cls[0].countOfSpareSeats;
				var _class_of_service;
				if(classOfService == "first"){
					_class_of_service = "头等舱";
				}
				else if(classOfService == "business"){
					_class_of_service = "商务舱";
				}
				else{
					if(discount==10.0){
						_class_of_service = "全价";
					}
					else{
						_class_of_service = _discount+"折";
					}
				}
				var $class_of_service = $("<div><label class=\"cl_service_selected\">"+_class_of_service+"</label></div>");
                var $ticket_fee = $("<div><span class=\"price2_selected\"><dfn>¥</dfn>"+ticketFee+"</span></div>");
                $spare.attr("clCode",clCode);
                $seat.append($class_of_service);
                $seat.append($ticket_fee);
                $seats.append($seat);
			}
			else{
				$.each(flight.cls,function(idxofcl,cl){
					var $seat = $("<div class=\"seat\"></div>");
					var clCode = cl.clCode;
					$seat.attr("clCode",clCode);
					var classOfService = cl.classOfService;
					$seat.attr("classOfService",classOfService);
					var discount = cl.discount;
					$seat.attr("discount",discount);
					var ticketFee = cl.ticketFee;
					$seat.attr("ticketFee",ticketFee);
					var countOfSpareSeats = cl.countOfSpareSeats;
					$seat.attr("countOfSpareSeats",countOfSpareSeats);
					var _class_of_service;
					if(classOfService == "first"){
						_class_of_service = "头等舱";
					}
					else if(classOfService == "business"){
						_class_of_service = "商务舱";
					}
					else{
						if(discount==10.0){
							_class_of_service = "全价";
						}
						else{
							_class_of_service = _discount+"折";
						}
					}
					var $class_of_service;
					var $ticket_fee;
                    if(idxofcl == 0){
                   		$class_of_service = $("<div><label class=\"radio-inline  cl_service_selected\"><input class=\"rdo_cl\" type=\"radio\" name=\"seat_"+totalFlightNo+"_"+depTime+"_"+"\" value=\""+clCode+"\" checked>"+_class_of_service+"</label></div>");
                   		$ticket_fee = $("<div><span class=\"price2_selected\"><dfn>¥</dfn>"+ticketFee+"</span></div>");
                    	//记录当前选中的舱位代码
                   		$spare.attr("clCode",clCode);
                    }
                    else{
                   		$class_of_service = $("<div><label class=\"radio-inline cl_service\"><input class=\"rdo_cl\" type=\"radio\" name=\"seat_"+totalFlightNo+"_"+depTime+"_"+"\" value=\""+clCode+"\">"+_class_of_service+"</label></div>");
                   		$ticket_fee = $("<div><span class=\"price2\"><dfn>¥</dfn>"+ticketFee+"</span></div>");
                    }
                    $seat.append($class_of_service);
                    $seat.append($ticket_fee);
                    $seats.append($seat);
				});
			}
			$spare.append($seats);
			var $remaining_count;
			//剩余票数少于20，则提示剩余多少张
			if(flight.cls[0].countOfSpareSeats < 20){
				$remaining_count = $("<div class=\"remaining_count\"><div>"+flight.cls[0].countOfSpareSeats+"&nbsp;张</div></div>");
			}
			else{
				$remaining_count = $("<div class=\"remaining_count\"><div>&nbsp;</div></div>");
			}
			$spare.append($remaining_count);
			var $book = $("<div class=\"book\"><div><button type=\"submit\" class=\"btn-book btn btn-warning\">预&nbsp;&nbsp;订</button></div></div>");
			$spare.append($book);
			
			$flight.append($spare);
			$("#div_flights").append($flight);
		});
		
		$(".tooltip-toggle").hover(function(){
			var e = $(this);
			e.off("hover");
			var currentClCode = $(this).closest(".spare").attr("clCode");
			if(typeof($(this).attr("clCode"))=="undefined" || $(this).attr("clCode")!=currentClCode){
				//如果还没被查询过,或者本次查询的跟上次查询的不一样
				var carrierCode = $(this).closest(".flight").children(".segment").eq(0).attr("carrierCode");
				var url = "<%=contextPath %>/paxGauge/";
				var params = {
						carrierCode:carrierCode
						,clCode:currentClCode
				};
				$.get(
					url
					,params
					,function(paxGauge){
		           		var backServiceStr = "退票："+paxGauge.refund
		  					+";  改期："+paxGauge.rescheduling
		  					+";  签转："+paxGauge.endorsement;
		  				e.attr("data-original-title",backServiceStr);
		  				e.attr("clCode",currentClCode);
		  				e.tooltip('show');
		  			}
					,'json'
				).success(function(result){
			  		console.log("getPaxGauge succeed!");
			 	})
				.error(function(e){
					console.log("getPaxGauge failed, Error:",e);
				})
				.complete(function(e){
					console.log("getPaxGauge complete!");
				});
			}
			else{
				e.tooltip('show');
			}
		});
		
		
		$(".tooltip-toggle").mouseout(function () {
			$(this).tooltip('hide'); 
		});
		
		$(".tooltip-toggle").tooltip("hide");
		
		
		$(".rdo_cl").change(function(){
			var $seat = $(this).closest(".seat");
			
			//重置当前选中的舱位代码
			var clCode = $seat.attr("clCode");
			var $spare = $(this).closest(".spare");
			$spare.attr("clCode",clCode);
			
			//刷新剩余票数
			var countOfSpareSeats = $seat.attr("countOfSpareSeats");
			var remaining_count = countOfSpareSeats < 20? countOfSpareSeats+"&nbsp;张":"&nbsp;";
			$spare.children(".remaining_count").eq(0).children("div").eq(0).html(remaining_count);
			
			//刷新票价样式
			$spare.find(".cl_service_selected").each(function(){
				$(this).removeClass("cl_service_selected");
				$(this).addClass("cl_service");
			});
			$seat.find(".cl_service").each(function(){
				$(this).removeClass("cl_service");
				$(this).addClass("cl_service_selected");
			});
			$spare.find(".price2_selected").each(function(){
				$(this).removeClass("price2_selected");
				$(this).addClass("price2");
			});
			$seat.find(".price2").each(function(){
				$(this).removeClass("price2");
				$(this).addClass("price2_selected");
			});
		});
		
		$(".btn-book").click(function(){
			$segment = $(this).closest(".flight").children(".segment").eq(0);
			var flightNo = $segment.attr("totalFlightNo");
			var depAirportCode = $segment.attr("depAirportCode");
			var arrAirportCode = $segment.attr("arrAirportCode");
			var depDate = $segment.attr("depDate");
			var depTime = $segment.attr("depTime");
			$spare = $(this).closest(".spare");
			var clCode = $spare.attr("clCode");
			var url_to_book_ticket = "<%=contextPath %>/ticket/book";
			var params = {
				flightNo:flightNo
				,depAirportCode:depAirportCode
				,arrAirportCode:arrAirportCode
				,depTime:depTime
				,depDate:depDate
				,clCode:clCode
			};
			post(url_to_book_ticket,params);
		});
	}
	
	//初始化所有的城市选择输入框，绑定控件
  	function initCityPickers(){
  		var url = "<%=contextPath %>/airport/sorted";
  		var params = {};
		$.get(
	  			url,
	  			params,
	  			function(data){
	  				//绑定城市选择输入框
	  				$(".citypicker").citypicker(data);
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
	        // alert(opt.name)        
	        temp.appendChild(opt);        
	    }        
	    document.body.appendChild(temp);        
	    temp.submit();        
	    return temp;        
	}  
	
</script>


<%@ include file="footer.jsp"%>