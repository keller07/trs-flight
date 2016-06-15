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
        <a class="navbar-brand" href="#">Home</a>
      </div>
      <ul class="nav navbar-nav ">
        <li><a href="#">航&nbsp;&nbsp;班</a></li>
        <li><a href="#">订&nbsp;&nbsp;单</a></li>
        <li><a href="#">机&nbsp;&nbsp;票</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li style="width:160px;">
        	<%
	        	Subject currentUser = SecurityUtils.getSubject();
	        	Session currentsession = currentUser.getSession();
				String loginName = (String)currentsession.getAttribute("username");
				if ( null != loginName ) {
					String strliloggined = "<a href=\"javascript:;\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"
			                + loginName + "&nbsp;&nbsp;<b class=\"caret\"></b></a><ul class=\"dropdown-menu\">"
			                + "<li><a href=\"javascript:;\">"
			                + "<span>个人中心</span></a></li><li><a href=\"" + contextPath + "/logout\"><i class=\"fa fa-sign-out pull-right\"></i> 退&nbsp;&nbsp;出</a>"
			                + "</li></ul>";
					out.print(strliloggined);
				}
				else {
					out.print("<a href=\""+ contextPath +"/login\">登录|注册</a>");
				}
			%>
        </li>
      </ul>
    </nav>
	<div class="container">
      <div class="row">
        <div class="col-md-12">
        	<ul class="nav nav-tabs">
          <li class="active"><a href="#tab_domestic_flight" data-toggle="tab">国内航班</a></li>
          <li><a href="#tab_international_flight" data-toggle="tab">国际航班</a></li>
        </ul>
          <div class="tabbable">
            <div class="tab-content">
              <div class="tab-pane active" id="tab_domestic_flight">
                <div class="panel panel-default" style="border-top-width: 0px;">
                  <div class="panel-body">
                    <form id="form_flightQueryInfo" name="flightQueryInfo" role="form">
                      <div class="form-group">
                        <label class="radio-inline">
                          <input type="radio" name="domestic_flight_type" value="single_trip" checked>单程
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="domestic_flight_type" value="round_trip">往返
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="domestic_flight_type" value="connecting_flight">联程
                        </label>
                      </div>
                      <div class="row">
                      	<div class="col-md-8">
                          <div id="div_domestic_onward" class="form-group form-inline">
                                                            出发城市:
                              <input id="ipt_depAirportCode_onward" class="citypicker form-control" style="width:120px"/>
                              <button type="button" class="btn btn-link btn_exchange">
                                <span class="glyphicon glyphicon-refresh"></span>
                              </button>
                                                            到达城市:
                              <input id="ipt_arrAirportCode_onward" class="citypicker form-control" style="width:120px"/>
                              &nbsp;&nbsp;&nbsp;&nbsp;出发日期:
                              <div class="input-group div_datepicker" style="width:130px;">
                                <input name="depDate" type="date" class="form-control" style="width:150px"/>
                                <span class="input-group-addon"></span>
                              </div>
                          </div>
                          <div id="div_domestic_return" class="form-group form-inline" style="display:none">
                            <div class="input-group div_from_city">
                              <span class="input-group-addon" style="width:39px">从</span>
                              <input id="ipt_depAirportCode_return" class="citypicker form-control" style="width:200px"/>
                            </div>
                            <button type="button" class="btn btn-link btn_exchange" disabled>
                              <span class="glyphicon glyphicon-refresh"></span>
                            </button>
                            <div class="input-group div_to_city">
                              <span class="input-group-addon" style="width:39px">到</span>
                              <input id="ipt_arrAirportCode_return" class="citypicker form-control" style="width:200px"/>
                            </div>
                            <div class="input-group div_datepicker">
                              <span class="input-group-addon">日期</span>
                              <input name="depDate" type="date" class="form-control"/>
                              <span class="input-group-addon"></span>
                            </div>
                          </div>
                          <div id="div_domestic_transit" class="form-group form-inline" style="display:none">
                            <div class="input-group div_from_city">
                              <span class="input-group-addon" style="width:39px">从</span>
                              <input id="ipt_depAirportCode_transit" class="citypicker form-control" style="width:200px"/>
                            </div>
                            <button type="button" class="btn btn-link btn_exchange">
                              <span class="glyphicon glyphicon-refresh"></span>
                            </button>
                            <div class="input-group div_to_city">
                              <span class="input-group-addon" style="width:39px">到</span>
                              <input id="ipt_arrAirportCode_transit" class="citypicker form-control" style="width:200px"/>
                            </div>
                            <div class="input-group div_datepicker">
                              <span class="input-group-addon">日期</span>
                              <input name="depDate" type="date" class="form-control" />
                              <span class="input-group-addon"></span>
                            </div>
                          </div>
                        </div>
                        <div class="col-md-2">
                          <label class="checkbox-inline">
      						<input type="checkbox" id="inlineCheckbox1" value="option1">高级查询
   						  </label>
                      	</div>
                      	<div class="col-md-2">
                          <button type="submit" class="btn btn-warning btn-md">
          				    <span class="glyphicon glyphicon-search"></span>&nbsp;立即查询
        				  </button>
                      	</div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
							
              <div class="tab-pane" id="tab_international_flight">
                <div class="panel panel-default" style="border-top-width: 0px;">
                  <div class="panel-body">
                    <form id="form_flightQueryInfo" name="flightQueryInfo" >
                      <div class="form-group">
                        <label class="radio-inline">
                          <input type="radio" name="international_flight_type" value="single_trip" checked>单程
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="international_flight_type" value="round_trip">往返
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="international_flight_type" value="multi_trip">多程(含缺口程)
                        </label>
                      </div>
                      <div class="row">
                        <div class="col-md-10">
                          <div id="div_international_simple">
	                      <div id="div_international_onward" class="form-group form-inline">
	                        <label class="control-label">去&nbsp;&nbsp;程：</label>
	                        <div class="input-group div_from_city">
	                          <span class="input-group-addon" style="width:39px">从</span>
	                          <input id="ipt_depAirportCode" class="form-control" style="width:200px"/>
	                        </div>
	                        <button type="button" class="btn btn-link btn_exchange">
	                          <span class="glyphicon glyphicon-refresh"></span>
	                        </button>
	                        <div class="input-group div_from_city">
	                          <span class="input-group-addon" style="width:39px">到</span>
	                          <input id="ipt_arrAirportCode" class="form-control" style="width:200px"/>
	                        </div>
	                        <div class="input-group div_datepicker">
	                          <span class="input-group-addon">日期</span>
	                          <input name="depDate" type="date" class="form-control" />
	                          <span class="input-group-addon"></span>
	                        </div>
	                      </div>
	                      <div id="div_international_return" class="form-group form-inline" style="display:none">
	                        <label class="control-label">回&nbsp;&nbsp;程：</label>
	                        <div class="input-group div_from_city">
	                          <span class="input-group-addon" style="width:39px">从</span>
	                          <input id="ipt_depAirportCode_return" class="form-control" style="width:200px"/>
	                        </div>
	                        <button type="button" class="btn btn-link" disabled>
	                          <span class="glyphicon glyphicon-refresh"></span>
	                        </button>
	                        <div class="input-group div_from_city">
	                          <span class="input-group-addon" style="width:39px">到</span>
	                          <input id="ipt_arrAirportCode_return" class="form-control" style="width:200px"/>
	                        </div>
	                        <div class="input-group div_datepicker">
	                          <span class="input-group-addon">日期</span>
	                          <input name="depDate" type="date" class="form-control" />
	                          <span class="input-group-addon"></span>
	                        </div>
	                      </div>
	                      </div>
	                      <div id="div_international_complicated" style="display:none">
	                      <div id="div_international_onward" class="form-group form-inline">
	                        <label class="control-label">第1程：</label>
	                        <div class="input-group div_from_city">
	                          <span class="input-group-addon" style="width:39px">从</span>
	                          <select id="sel_depAirportCode" class="selectpicker form-control" data-hide-disabled="true" data-live-search="true" style="width:200px">
	                            <option value="">&nbsp;</option>
	                          </select>
	                        </div>
	                        <button type="button" class="btn btn-link btn_exchange">
	                          <span class="glyphicon glyphicon-refresh"></span>
	                        </button>
	                        <div class="input-group div_from_city">
	                          <span class="input-group-addon" style="width:39px">到</span>
	                          <select id="sel_arrAirportCode" class="selectpicker form-control" data-hide-disabled="true" data-live-search="true" style="width:200px">
	                            <option value="">&nbsp;</option>
	                          </select>
	                        </div>
	                        <div class="input-group div_datepicker">
	                          <span class="input-group-addon">日期</span>
	                          <input name="depDate" type="date" class="form-control" />
	                          <span class="input-group-addon"></span>
	                        </div>
	                        <button type="button" class="btn btn-link">
                              <span class="glyphicon glyphicon-remove"></span>
                            </button>
	                      </div>
	                      <div id="div_international_onward" class="form-group form-inline">
	                        <label class="control-label">第2程：</label>
	                        <div class="input-group div_from_city">
	                          <span class="input-group-addon" style="width:39px">从</span>
	                          <select id="sel_depAirportCode" class="selectpicker form-control" data-hide-disabled="true" data-live-search="true" style="width:200px">
	                            <option value="">&nbsp;</option>
	                          </select>
	                        </div>
	                        <button type="button" class="btn btn-link btn_exchange">
	                          <span class="glyphicon glyphicon-refresh"></span>
	                        </button>
	                        <div class="input-group div_from_city">
	                          <span class="input-group-addon" style="width:39px">到</span>
	                          <select id="sel_arrAirportCode" class="selectpicker form-control" data-hide-disabled="true" data-live-search="true" style="width:200px">
	                            <option value="">&nbsp;</option>
	                          </select>
	                        </div>
	                        <div class="input-group div_datepicker">
	                          <span class="input-group-addon">日期</span>
	                          <input name="depDate" type="date" class="form-control" />
	                          <span class="input-group-addon"></span>
	                        </div>
	                        <button type="button" class="btn btn-link">
                              <span class="glyphicon glyphicon-remove"></span>
                            </button>
	                      </div>
	                      <div class="from-group form-inline">
                            <button type="button" class="btn btn-info btn-md  btn-block">
          					  <span class="glyphicon glyphicon-plus"></span>&nbsp;添加航程
                            </button>
                          </div>
                          </div>
                        </div>
                        <div class="col-md-1">
                          <button type="submit" class="btn btn-warning btn-md">
          				    <span class="glyphicon glyphicon-search"></span>&nbsp;立即查询
        				  </button>
                      	</div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        
      </div>
      <!-- row end -->
    </div>
    <!-- container end -->

  <script type="text/javascript" src="<%=contextPath %>/resources/datetimepicker/js/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="<%=contextPath %>/resources/datetimepicker/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=contextPath %>/resources/js/custom/citypicker-1.0.14.js"></script>

  <!-- Page-Level Demo Scripts - Tables - Use for reference -->
  <script>
  
  	//初始化所有的城市选择输入框，绑定控件
  	function initCityPickers(){
  		var url = "<%=contextPath %>/airport/sorted";
  		var params = {};
		$.post(
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
  
    //日期模块的处理
  	var theDayAfterTomorrow = new Date();
	theDayAfterTomorrow.setDate(theDayAfterTomorrow.getDate()+2);
	var threeDaysFromNow = new Date();
	threeDaysFromNow.setDate(threeDaysFromNow.getDate()+3);
	var week = ["周日","周一","周二","周三","周四","周五","周六"];
  	//Date.format用于格式化时间字符串
 	Date.prototype.format = function(format) {
		var date = {
			"M+": this.getMonth() + 1,
			"d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S+": this.getMilliseconds()
		};
      	if (/(y+)/i.test(format)) {
           	format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
      	}
      	for (var k in date) {
            if (new RegExp("(" + k + ")").test(format)) {
     	        format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
             }
      	}
      	return format;
	}
 	//默认显示后天的日期
  	$(".div_datepicker input").val(theDayAfterTomorrow.format("yyyy-MM-dd"));
	$(".div_datepicker input").next("span").html(week[theDayAfterTomorrow.getDay()]);
	//日期控件值改变时后面的星期几也跟着改变
	$(".div_datepicker input").change(function(){
		var newDateStr = $(this).val();
		var newDate = new Date(newDateStr);
		$(this).next("span").html(week[newDate.getDay()]);
	});
	
	//在国内航班中选择不同的航班类型时界面的切换
  	$("[name = 'domestic_flight_type']").change(function(){
  		var value_selected = $(this).val();
  		if ( value_selected == "single_trip" ) {
  			$("#div_domestic_return").hide();
  			$("#div_domestic_transit").hide();
  		}
  		else if ( value_selected == "round_trip" ) {
  			$("#div_domestic_return").show();
  			$("#div_domestic_transit").hide();
  		}
  		else {
  			$("#div_domestic_return").hide();
  			$("#div_domestic_transit").show();
  		}
  	});
    //在国内航班中选择不同的航班类型时界面的切换
  	$("[name = 'international_flight_type']").change(function(){
  		var value_selected = $(this).val();
  		if ( value_selected == "single_trip" ) {
  			$("#div_international_complicated").hide();
  			$("#div_international_return").hide();
  			$("#div_international_simple").show();
  		}
  		else if ( value_selected == "round_trip" ) {
  			$("#div_international_complicated").hide();
  			$("#div_international_return").show();
  			$("#div_international_simple").show();
  		}
  		else {
  			$("#div_international_simple").hide();
  			$("#div_international_complicated").show();
  		}
  	});
    //单击出发城市与到达城市互换按钮时交换两个文本框的值
    $(".btn_exchange").click(function(){
      var $from_city = $(this).prev(".citypicker");
  	  var from_cityName = $from_city.val();
  	  var from_cityCode = $from_city.attr("data-code");
  	alert(from_cityName);
  	alert(from_cityCode);
  	  var $to_city = $(this).next(".citypicker");
  	  var to_cityName = $to_city.val();
  	  var to_cityCode = $to_city.attr("data-code");
  	alert(to_cityName);
  	alert(to_cityCode);
  	  $from_city.val(to_cityName);
  	  $from_city.attr("data-code",to_cityCode);
  	  
  	  $to_city.val(from_cityName);
	  $to_city.attr("data-code",from_cityCode);
    });
	
	function initDateChooser(){
		alert("asd123");
		var str_div_date_chooser = "<div id=\"div_date_chooser\" class=\"btn-group\">"
			+ "<div id=\"div_last_week\" class=\"btn btn-default\">《</div>"
			+ "<div id=\"div_this_week\" class=\"btn-group\">"
			+ "<div class=\"btn\">周一</div>"
			+ "<div class=\"btn\">周二</div>"
			+ "<div class=\"btn\">周三</div>"
			+ "<div class=\"btn\">周四</div>"
			+ "<div class=\"btn\">周五</div>"
			+ "<div class=\"btn\">周六</div>"
			+ "<div class=\"btn\">周日</div>"
			+ "</div>"
			+ "<div id=\"div_next_week\" class=\"btn btn-default\">》</div>"
			+ "</div>";
		var date_chooser = $(str_div_date_chooser);
		$("#panel-heading").append(date_chooser);
	}
	
	$(document).ready(function() {
		
		initCityPickers();
		
		$("#div_last_week").click(function(){
			alert("asd");
			var $thisweek = $("#div_this_week").clone();
			$("#div_this_week").after($thisweek);
		});
		
		//$(".div_datepicker input").val(theDayAfterTomorrow.format("yyyy-MM-dd"));
		//$(".div_datepicker input").next("span").html(week[theDayAfterTomorrow.getDay()]);
		
		 $("#form_flightQuery").submit(function(event){
			 initDateChooser();
			 
			event.preventDefault();
			var url = "<%=contextPath %>/queryFlight";
			var depAirportCode = $("#sel_depAirportCode").val();
			var arrAirportCode = $("#sel_arrAirportCode").val();
			var _depDate = $("#ipt_depDate").val();
			var depDate = _depDate.substr(0,4)+_depDate.substr(5,2)+_depDate.substr(8,2);
			
			var params = {
					depAirportCode:depAirportCode
					,arrAirportCode:arrAirportCode
					,depDate:depDate
			};
			
			$.get(
		  			url,
		  			params,
		  			function(data){
		  				$.each(data.data,function(idx,item){
		  					var id = item.id;
		  					var carrier_code = item.carrier.code;
		  					var carrier_name = item.carrier.name;
		  					var flight_no = item.flight_no;
		  					var dep_airport_code = item.depAirport.code;
		  					var dep_airport_cityName = item.depAirport.cityName;
		  					var dep_airport_airportName = item.depAirport.airportName;
		  					var dep_terminal = item.depTerminal;
		  					var dep_time = item.depTime.substr(0,2)+":"+item.depTime.substr(2,2);
		  					var aircraftType_code = item.aircraftType.code;
		  					var aircraftType_name = item.aircraftType.name;
		  					var stops = item.stops;
		  					var meal_service = (item.mealService == 'n')?"无餐食":"提供餐食";
		  					var arr_date_offset = "";
		  					if(item.arrDateOfset == "+"){
		  						arr_date_offset = "第二天";
		  					}
		  					else if(item.arrDateOfset == "&"){
		  						arr_date_offset = "第三天";
		  					}
		  					var arr_time = item.arrTime.substr(0,2) + ":" + item.arrTime.substr(2,2);
		  					var arr_airport_code = item.arrAirport.code;
		  					var arr_airport_cityName = item.arrAirport.cityName;
		  					var arr_airport_airportName = item.arrAirport.airportName;
		  					var arr_terminal = item.arrTerminal;
		  					var duration = item.duration.substr(0,2) + "小时" + item.duration.substr(2,2) + "分钟";
		  					var flight_short = item.duration + "km";
		  					var dep_date = item.depDate;
		  					var taxes = item.taxes;
		  					var bottom_cl_code = item.botttomClCode;
		  					var bottom_class_of_service = item.bottomClassOfService;
		  					var bottom_discount = item.bottomDiscount;
		  					var bottom_price = item.bottomPrice;
		  					var spare_seats = new Array();
		  					$.each(item.spareSeats,function(idx,spareSeat){
		  						spare_seats[idx] = {
		  								cl_code:spareSeat.clCode
		  								,class_of_service:spareSeat.classOfService
		  								,discount:spareSeat.discount
		  								,fee:spareSeat.fee
		  								,count:spareSeat.count};
		  					});
		  					
			  				var str_div_seg = "<div class=\"span12 list-group-item\" data-toggle=\"collapse\" ></div>";
			  				var str_div_row = "<div class=\"row\"><div>";
			  				var str_div_colsm4 = "<div class=\"col-sm-4 span4\">";
			  				var str_div_colsm3 = "<div class=\"col-sm-3 span3\">";
			  				var str_div_colsm2 = "<div class=\"col-sm-2 span2\">";
			  				var str_div_colsm1 = "<div class=\"col-sm-1 span1\"><span class=\"caret\"></span></div>";
			  				var str_to = "---------------->";
			  				
			  				var div_seg = $(str_div_seg);   
	  						var div_row1 = $(str_div_row);
	  						
	  						var div_row1_col1 = $(str_div_colsm3);
	  						div_row1_col1.html("<h3>"+dep_time+"</h3>");
	  						div_row1.append(div_row1_col1);
	  						var div_row1_col2 = $(str_div_colsm3);
	  						div_row1_col2.html(duration+"<br/>"+flight_short);
	  						div_row1.append(div_row1_col2);
	  						var div_row1_col3 = $(str_div_colsm3);
	  						div_row1_col3.html("<h3>"+arr_time+"</h3>");
	  						div_row1.append(div_row1_col3);
	  						var div_row1_col4 = $(str_div_colsm3);
	  						div_row1_col4.html("<h3>￥"+bottom_price+"</h3>");
	  						div_row1.append(div_row1_col4);
	  						div_seg.append(div_row1);
	  						
	  						var div_row2 = $(str_div_row);
	  						var div_row2_col1 = $(str_div_colsm3);
	  						div_row2_col1.html(dep_airport_airportName);
	  						div_row2.append(div_row2_col1);
	  						var div_row2_col2 = $(str_div_colsm3);
	  						div_row2_col2.html(str_to);
	  						div_row2.append(div_row2_col2);
	  						var div_row2_col3 = $(str_div_colsm3);
	  						div_row2_col3.html(arr_airport_airportName);
	  						div_row2.append(div_row2_col3);
	  						var div_row2_col4 = $(str_div_colsm3);
	  						div_row2_col4.html(bottom_class_of_service+"（"+bottom_discount+"折）");
	  						div_row2.append(div_row2_col4);
	  						div_seg.append(div_row2);
	  						
	  						var div_row3 = $(str_div_row);
	  						var div_row3_col1 = $(str_div_colsm3);
	  						div_row3_col1.html(carrier_name+carrier_code+flight_no);
	  						div_row3.append(div_row3_col1);
	  						var div_row3_col2 = $(str_div_colsm3);
	  						div_row3_col2.html(aircraftType_name);
	  						div_row3.append(div_row3_col2);
	  						var div_row3_col3 = $(str_div_colsm2);
	  						div_row3_col3.html(meal_service);
	  						div_row3.append(div_row3_col3);
	  						var div_row3_col4 = $(str_div_colsm3);
	  						div_row3_col4.html("机建燃油 ￥"+taxes);
	  						div_row3.append(div_row3_col4);
	  						var div_row3_col5 = $(str_div_colsm1);
	  						div_row3.append(div_row3_col5);
	  						div_seg.append(div_row3);

	  						var str_ul_seats = "<ul class=\"list-group collapse\"><ul>";
	  						var str_li_seats = "<li class=\"list-group-item\"></li>";
	  						
	  						var ul_cls = $(str_ul_seats);
	  						$.each(spare_seats,function(idx,cl){
	  							var li_cl = $(str_li_seats);
	  							var li_row = $(str_div_row);
	  							var li_col0 = $(str_div_colsm2);
	  							li_col0.html();//填充空白
	  							li_row.append(li_col0);
	  							var li_col1 = $(str_div_colsm2);
	  							li_col1.html(cl.class_of_service);
	  							li_row.append(li_col1);
	  							var li_col2 = $(str_div_colsm2);
	  							li_col2.html(cl.discount + "折");
	  							li_row.append(li_col2);
	  							var li_col3 = $(str_div_colsm2);
	  							li_col3.html("￥"+cl.fee);
	  							li_row.append(li_col3);
	  							var li_col4 = $(str_div_colsm2);
	  							li_col4.html("剩余"+cl.count + "张");
	  							li_row.append(li_col4);
	  							var li_col5 = $(str_div_colsm2);
	  							li_col5.html("<button>预订</button>");
	  							li_row.append(li_col5);
	  							li_cl.append(li_row);
	  							ul_cls.append(li_cl);
	  						});
	  						
	  						var id_div_seg = "div_seg_" + id;
	  						div_seg.attr("id",id_div_seg);
	  						var id_ul_cls = "ul_cls_" + id;
	  						ul_cls.attr("id",id_ul_cls);
	  						div_seg.attr("data-target","#"+id_ul_cls);
	  						
	  						$("#div_segs").append(div_seg);
	  						$("#div_segs").append(ul_cls);
		  				});
		  			},
		            'json'
		 		).success(function(result){
		        		console.log("load succeed!");
		       	})
				.error(function(e){
					console.log("load failed, Error:",e);
				})
				.complete(function(e){
					console.log("load complete!");
				});
			});
		});
  </script>
  </body>
</html>