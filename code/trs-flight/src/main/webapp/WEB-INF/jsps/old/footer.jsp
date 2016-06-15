<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	  
<%	String contextPath = request.getContextPath(); %>


		</div>
		<!-- /#wrapper -->
		
		<!-- jQuery -->
		<script src="<%=contextPath %>/resources/js/SB.Admin.2/jquery.min.js"></script>
		
		<!-- Bootstrap Core JavaScript -->
		<script src="<%=contextPath %>/resources/js/SB.Admin.2/bootstrap.min.js"></script>
		
		<!-- Metis Menu Plugin JavaScript -->
		<script src="<%=contextPath %>/resources/js/SB.Admin.2/metisMenu.min.js"></script>
		
		<!-- Custom Theme JavaScript -->
		<script src="<%=contextPath %>/resources/js/SB.Admin.2/sb-admin-2.js"></script>

		<!-- Page-Level Demo Scripts - Tables - Use for reference -->
		<script>
		$(document).ready(function() {
			 $("#form_flightQueryInfo").submit(function(event){
						event.preventDefault();
						var url = "<%=contextPath %>/queryFlight";
						var depAirportCode = $("#ipt_depAirportCode").val();
						var arrAirportCode = $("#ipt_arrAirportCode").val();
						var depDate = $("#ipt_depDate").val();
						
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
			  					var flight_no = carrier_code + item.flight_no;
			  					var dep_airport_code = item.depAirport.code;
			  					var dep_airport_cityName = item.depAirport.cityName;
			  					var dep_airport_airportName = item.depAirport.airportName;
			  					var dep_terminal = item.depTerminal;
			  					var dep_time = item.depTime.substr(0,2)+":"+item.depTime.substr(2,2);
			  					var aircraftType_code = item.aircraftType.code;
			  					var aircraftType_name = item.aircraftType.name;
			  					var stops = item.stops;
			  					var meal_service = (item.mealService == 'n')?"无餐食":"有餐食";
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
			  					var spare_seats = new Array();
			  					$.each(item.spareSeats,function(idx,spareSeat){
			  						spare_seats[idx] = {cl_code:spareSeat.clCode
			  								,class_of_service:spareSeat.classOfService
			  								,discount:spareSeat.discount
			  								,fee:spareSeat.fee
			  								,count:spareSeat.count};
			  					});
			  					alert(
			  						"id = " + id + "\n"
					  				+ "carrier_code = " +carrier_code + "\n"
					  				+ "carrier_name =" + carrier_name + "\n"
					  				+ "flight_no =" + flight_no + "\n"
					  				+ "dep_airport_code =" + dep_airport_code + "\n"
					  				+ "dep_airport_cityName =" + dep_airport_cityName + "\n"
					  				+ "dep_airport_airportName =" + dep_airport_airportName + "\n"
					  				+ "dep_terminal =" + dep_terminal + "\n"
					  				+ "dep_time =" + dep_time + "\n"
					  				+ "aircraftType_code =" + aircraftType_code + "\n"
					  				+ "aircraftType_name =" + aircraftType_name + "\n"
					  				+ "stops =" + stops + "\n"
					  				+ "meal_service =" + meal_service + "\n"
					  				+ "arr_date_offset =" + arr_date_offset + "\n"
					  				+ "arr_time =" + arr_time + "\n"
					  				+ "arr_airport_code =" + arr_airport_code + "\n"
					  				+ "arr_airport_cityName =" + arr_airport_cityName + "\n"
					  				+ "arr_airport_airportName =" + arr_airport_airportName + "\n"
					  				+ "arr_terminal =" + arr_terminal + "\n"
					  				+ "duration =" + duration + "\n"
					  				+ "flight_short =" + flight_short + "\n"
					  				+ "dep_date =" + dep_date + "\n"
					  				+ "taxes =" + taxes + "\n"
					  				+ "spare_seats =" + spare_seats	
			  					);
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