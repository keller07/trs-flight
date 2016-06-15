<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="com.flight.trs.model.entity.Carrier"%>
<%@ include file="header.jsp"%>

<link type="text/css" href="<%=contextPath %>/resources/bootstrapselect/css/bootstrap-select.css" rel="stylesheet" />
    
<style>
</style>

<div class="row">
	<div style="width:460px;">
  		<ul class="nav nav-tabs">
        	<li class="active"><a href="#tab_domestic_flight" data-toggle="tab">国内航班</a></li>
        	<li><a href="#tab_international_flight" data-toggle="tab">国际航班</a></li>
        </ul>
          <div class="tabbable">
            <div class="tab-content">
              <div class="tab-pane active" id="tab_domestic_flight">
                <div class="panel panel-default" style="border-top-width: 0px;">
                  <div class="panel-body">
                    <form id="form_domesticFlightQuery" name="domesticFlightQueryInfo" action="<%=contextPath %>/domesticFlight.query" method="get" role="form">
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
                      <div style="width:458px;">
                      	  <div id="div_city_domestic_onward" style="width:180px;float:left;margin-right:15px;">
                      	  	<p>出发城市:<input id="ipt_depAirportCode" name="depAirportCode" type="text" class="citypicker" style="width:120px;height:30px;" /></p>
                      	  	<p id="p_traAirportCode" style="display:none;">中转城市:<input id="ipt_traAirportCode" name="arrAirportCode" type="text" class="citypicker" style="width:120px;height:30px;" /></p>
                      	  	<p>到达城市:<input id="ipt_arrAirportCode" name="arrAirportCode" type="text" class="citypicker" style="width:120px;height:30px;" /></p>
                      	  </div>
                          <div id="div_date_domestic_onward" class="div_datepicker" style="width:248px;float:left;">
                              <p>出发日期:<input id="ipt_depDate" name="depDate" type="date" style="width:140px;height:30px;margin-right:5px;"/><label>周*</label></p>
                              <p id="p_traDate" style="display:none;">中转日期:<input id="ipt_traDate" name="traDate" type="date" style="width:140px;height:30px;margin-right:5px;"/><label>周*</label></p>
                              <p id="p_retDate" style="display:none;">返回日期:<input id="ipt_retDate" name="ipt_retDate" type="date" style="width:140px;height:30px;margin-right:5px;"/><label>周*</label></p>
                          </div>
                          <div style="width:100%;height:30px;float:left;">
                          		<a id="a_advanced_query" href="javascript:;" style="text-decoration: none;">高级查询<b class="caret"></b></a>
                       	  </div>
                          <div id="div_advanced_query" style="width:428px;height:70px;float:left;">
                          	
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
                       			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       			出发时间:
                       			<select id="sel_dep_time_range">
									<option value="" selected>不限</option>
									<option value="00001000">00:00-10:00</option>
									<option value="10001400">10:00-14:00</option>
									<option value="14001900">14:00-19:00</option>
									<option value="19002400">19:00-24:00</option>
                       			</select>
                       			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       			<label class="checkbox-inline">
							      <input id="ckb_nonstop" type="checkbox" value=""> 直达
							   </label>
                       		</div>
                       		<div style="width:100%;height:30px;float:">
                       			航空公司:
                       			<select id="sel_carrier_code">
                       				<option value="" selected>不限</option>
                       			</select>
                       			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       			舱位等级:<select id="sel_class_of_service">
									<option value="" selected>不限</option>
									<option value="first,business">头等/商务舱</option>
									<option value="economic">经济舱</option>
                       			</select>
                       		</div>
                          </div>
                       	  <div style="width:428px;height:30px;float:left;">
                   			<button type="submit" class="btn btn-warning btn-md" style="float:right;">
          				    	<span class="glyphicon glyphicon-search"></span>&nbsp;立即查询
        				  	</button>
                          </div>
                       </div>
                          <div id="div_domestic_return" style="display:none">
                          </div>
                          <div id="div_domestic_transit" style="display:none">
                          </div>
                    </form>
                  </div>
                </div>
              </div>
							
              <div class="tab-pane" id="tab_international_flight">
                <div class="panel panel-default" style="border-top-width: 0px;">
                  <div class="panel-body">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- row end -->
      
<script type="text/javascript" src="<%=contextPath %>/resources/js/custom/citypicker-1.0.14.js"></script>
<script type="text/javascript" src="<%=contextPath %>/resources/bootstrapselect/js/bootstrap-select.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
  <script>
  
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
 	
	//日期控件值改变时后面的星期几也跟着改变
	$(".div_datepicker input").change(function(){
		var newDateStr = $(this).val();
		var newDate = new Date(newDateStr);
		$(this).next("label").html(week[newDate.getDay()]);
	});
	
	//在国内航班中选择不同的航班类型时界面的切换
  	$("[name = 'domestic_flight_type']").change(function(){
  		var value_selected = $(this).val();
  		if ( value_selected == "single_trip" ) {
  			$("#p_retDate").hide();
  			$("#p_traAirportCode").hide();
  			$("#p_traDate").hide();
  		}
  		else if ( value_selected == "round_trip" ) {
  			$("#p_retDate").show();
  			$("#p_traAirportCode").hide();
  			$("#p_traDate").hide();
  		}
  		else {
  			$("#p_retDate").hide();
  			$("#p_traAirportCode").show();
  			$("#p_traDate").show();
  		}
  	});
    //在国际航班中选择不同的航班类型时界面的切换
  	/*$("[name = 'international_flight_type']").change(function(){
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
    */
	$(document).ready(function() {
		
		initCityPickers();
		initCarrierSel();
		$("#div_advanced_query").hide();
		$("#a_advanced_query").click(function(){
			$("#div_advanced_query").toggle();
		});
		
		//默认显示后天的日期
	 	document.getElementById('ipt_depDate').value = theDayAfterTomorrow.format("yyyy-MM-dd");
	 	document.getElementById('ipt_retDate').value = threeDaysFromNow.format("yyyy-MM-dd");
	 	document.getElementById('ipt_traDate').value = threeDaysFromNow.format("yyyy-MM-dd");
	  	$("#ipt_depDate").next("label").html(week[theDayAfterTomorrow.getDay()]);
	  	$("#ipt_retDate").next("label").html(week[threeDaysFromNow.getDay()]);
	  	$("#ipt_traDate").next("label").html(week[threeDaysFromNow.getDay()]);
	  	
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
			
			var url = "<%=contextPath %>/flight.query";
			
			//在国内航班中选择不同的航班类型时界面的切换
	  		var flight_type = $("[name = 'domestic_flight_type']:checked").val();
			var paramsStr = "?domesticFlightType=";
			
			var depAirportCode = $("#ipt_depAirportCode").attr("data-code");
			var arrAirportCode = $("#ipt_arrAirportCode").attr("data-code");
			
			if(typeof(depAirportCode) == "undefined"){
				alert("请选择出发城市");
				$("#ipt_depAirportCode").focus();
				return false;
			}
			if(typeof(arrAirportCode) == "undefined"){
				alert("请选择出发城市");
				$("#ipt_arrAirportCode").focus();
				return false;
			}
			
			var _depDate = $("#ipt_depDate").val();
			var depDate = _depDate.substr(0,4)+_depDate.substr(5,2)+_depDate.substr(8,2);
	  		if ( flight_type == "single_trip" ) {
	  			paramsStr += "single_trip";
	  			paramsStr += "&depAirportCode=" + depAirportCode
	  				+ "&arrAirportCode=" + arrAirportCode
	  				+ "&depDate="+ depDate ;
	  		}
	  		else if ( flight_type == "round_trip" ) {
	  			paramsStr += "round_trip";
	  			var _retDate = $("#ipt_retDate").val();
				var retDate = _retDate.substr(0,4)+_retDate.substr(5,2)+_retDate.substr(8,2);
	  			paramsStr += "&depAirportCode=" + depAirportCode
  				+ "&arrAirportCode=" + arrAirportCode
				+ "&depDate=" + depDate
				+ "&retDate=" + retDate;
	  		}
	  		else {
	  			paramsStr += "connecting_flight";
	  			var traAirportCode = $("#ipt_traAirportCode").attr("data-code");
	  			var _traDate = $("#ipt_traDate").val();
				var traDate = _traDate.substr(0,4)+_traDate.substr(5,2)+_traDate.substr(8,2);
	  			paramsStr += "&depAirportCode=" + depAirportCode
				+"&arrAirportCode=" + arrAirportCode
				+"&traAirportCode=" + traAirportCode
				+"&depDate=" + depDate
				+"&traDate=" + traDate;
	  		}
	  		//高级查询参数合并
	  		var mix_count = $("#sel_mix_count").val();
	  		if( mix_count != 0){
	  			paramsStr += "&mixCountOfSpareSeats=" + mix_count;
	  		}
	  		
	  		var dep_time_range = $("#sel_dep_time_range").val();
	  		if( dep_time_range != ""){
	  			paramsStr += "&depTimeRange=" + dep_time_range;
	  		}
	  		
	  		if($("#ckb_nonstop").is(':checked')){
	  			paramsStr += "&nonStop=" + "y";
	  		}
	  		
	  		var carrier_code = $("#sel_carrier_code").val();
	  		if( carrier_code != ""){
	  			paramsStr += "&carrierCode=" + carrier_code;
	  		}
	  		
	  		var class_of_service = $("#sel_class_of_service").val();
	  		if( class_of_service != ""){
	  			paramsStr += "&classOfServices=" + class_of_service;
	  		}
	  		window.location =url+paramsStr;
		});
	});
	
	

</script>

























<%@ include file="footer.jsp"%>