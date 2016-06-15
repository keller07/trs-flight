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
	
		<!-- DataTables JavaScript -->
	  	<script src="<%=contextPath %>/resources/js/SB.Admin.2/plugins/dataTables/jquery.dataTables.min.js"></script>
	  	<script src="<%=contextPath %>/resources/js/SB.Admin.2/plugins/dataTables/dataTables.bootstrap.min.js"></script>
	  
	  	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
		<script>
		
		  	$(document).ready(function() {
		    		
		  		var url = "<%=contextPath %>/airport/";
				var params = {};
		  		
		  		$("#a_loadAirports").click(function(event){
		  			event.preventDefault();
		  			$("#div_airport").show();
					loadAllAirport(url,params);
		  		});
		    		
		  		//loadAllAirport(url, params);
		  		
		  		$("#a_loadCarriers").click(function(event){
		  			event.preventDefault();
		  			var url = "<%=contextPath %>/carrier/";
					var params = {};
					alert("a_loadCarriers");
					loadAndShowAll(url, params);
		  		});
		  		
		  		$("#a_loadPassengerGauges").click(function(event){
		  			event.preventDefault();
		  			var url = "<%=contextPath %>/passengerGauge/";
					var params = {};
					alert("a_loadPassengerGauges");
					loadAndShowAll(url, params);
		  		});
		  		
		  		$("#a_loadAircraftTypes").click(function(event){
		  			event.preventDefault();
		  			var url = "<%=contextPath %>/aircraftType/";
					var params = {};
					alert("a_loadAircraftTypes");
					loadAndShowAll(url, params);
		  		});
		  		
		  		$("#a_loadCustomers").click(function(event){
		  			event.preventDefault();
		  			alert("a_loadCustomers");
		  		});
		  		
		  		$("#a_loadSalesmans").click(function(event){
		  			event.preventDefault();
		  			
		  		});
		  		
		  		$("#a_loadOperatingLogs").click(function(event){
		  			event.preventDefault();
		  			
		  		});
		  		
			});
		  	
		  	function loadAllAirport(url, params){
		  		$.get(
		  			url,
		  			params,
		  			function(airports){
		  				$("#div_airports").empty();
		  				$table = $("<table class=\"table table-striped table-bordered table-hover\">"
							+"<thead><tr role=\"row\"><th>三字码</th>	<th>城市名</th><th>首字母</th><th>机场名</th>"
							+"<th>区域</th><th>是否热门</th><th width=\"124px\">操作</th></tr></thead></table>");
		  				$tbody = $("<tbody></tbody>");
				  		$.each(airports,function(idx,airport){
				  			$tr = $("<tr></tr>");
				  			$tdCode = $("<td></td>");
				  			$tdCode.html(airport.code);
				  			$tr.append($tdCode);
				  			$tdCityName = $("<td></td>");
				  			$tdCityName.html(airport.cityName);
				  			$tr.append($tdCityName);
				  			$tdCityNameInitial = $("<td></td>");
				  			$tdCityNameInitial.html(airport.cityNameInitial);
				  			$tr.append($tdCityNameInitial);
				  			$tdAirportName = $("<td></td>");
				  			$tdAirportName.html(airport.airportName);
				  			$tr.append($tdAirportName);
				  			$tdDist = $("<td></td>");
				  			$tdDist.html(airport.dist);
				  			$tr.append($tdDist);
				  			$tdIsHot = $("<td></td>");
				  			$tdIsHot.html(airport.isHot);
				  			$tr.append($tdIsHot);
				  			$tr.append($("<td></td>"));
				  			$tbody.append($tr);
				  		});
				  		$table.append($tbody);
						$("#div_airports").append($table);

				  		$table.DataTable({
			  				"ordering": false,
			  				"columnDefs": [{
			  					// 定义操作列
			  					"targets": 6,
			  					"data": null,
			  					"render": function(data, type, row) {
		  							var html = '<a href="javascript:void(0);" class="update btn btn-default btn-xs"><i class="fa fa-edit"></i> 修改</a>';
			  						html += '<a href="javascript:void(0);" class="delete btn btn-default btn-xs" style="margin-left:5px;"><i class="fa fa-times"></i> 删除</a>';
			  						html += '<a href="javascript:void(0);" class="submit btn btn-default btn-xs" style="display:none;"><i class="fa fa-check"></i> 提交</a>';
			  						html += '<a href="javascript:void(0);" class="cancel btn btn-default btn-xs" style="margin-left:5px;display:none;"><i class="fa fa-times"></i> 取消</a>';
			  						return html;
			  						
			  					}
			  				}],
			  				language: {
			  					"processing": "处理中...",
			  					"lengthMenu": "显示 _MENU_ 项结果",
			  					"zeroRecords": "没有匹配结果",
			  					"info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			  					"infoEmpty": "显示第 0 至 0 项结果，共 0 项",
			  					"infoFiltered": "(由 _MAX_ 项结果过滤)",
			  					"infoPostFix": "",
			  					"search": "搜索:",
			  					"url": "",
			  					"emptyTable": "表中数据为空",
			  					"loadingRecords": "载入中...",
			  					"infoThousands": ",",
			  					"paginate": {
			  						"first": "首页",
			  						"previous": "上页",
			  						"next": "下页",
			  						"last": "末页"
			  					},
			  					"aria": {
			  						"sortAscending": ": 以升序排列此列",
			  						"sortDescending": ": 以降序排列此列"
			  					}
			  				}
				      	});
				  		
				  		// 初始化刪除按钮
				  		$("#div_airports").find("table").eq(0).on('click', 'a.delete', function(e) {
				  			e.preventDefault();
				  			
				  			//撤销先前提出的修改请求
				  			if($(".currentTr").eq(0).length > 0){
				  				var currentTr = $(".currentTr").eq(0);
				  				$.each(currentTr.find("td"),function(idx,td){
				  					if(idx>0&&idx<6){
					  					var oldval = $(td).attr("oldval");
					  					$(td).html(oldval);
					  					$(td).removeAttr("oldval");
					  					$(td).removeAttr("contenteditable");
					  				}
				  				});
				  				currentTr.find("a").toggle();
				  				currentTr.removeClass("currentTr");
				  			}
				  			
				  			var table = $("#div_airports").find("table").eq(0).DataTable();
				  			var page = table.page();
				  			var row = table.row($(this).parents('tr'));
			  				var index = row.index();
			  				var data = table.data();
			  				var code = data[index][0];
				  			if (confirm("确定要删除三字码为"+code+"的机场信息？")) {
				  				$.ajax({
				  					type:"delete"
				  					,url:"<%=contextPath %>/airport/"+code
				  					,data:{
				  					}
				  					,dataType:"json"
				  					,contentType:"application/json;charset=utf-8"
				  					,success:function(data){
				  						if(data.status=="success"){
				  							row.remove().draw();
				  							table.page(page);
							  				//table.row($(this).parents('tr')).remove().draw();
							  				alert("删除成功");
				  						}
				  						else if(data.status=="nonexistent"){
				  							alert("不存在的机场信息");
				  						}
				  						else{
				  							alert("删除失败");
				  						}
				  					}
				  					,error:function(e){
				  						console.log("getCities failed, Error:",e);
				  					}
				  				});
				  			}
				  		});

				  		// 初始化修改按钮
				  		$("#div_airports").find("table").eq(0).on('click', 'a.update', function(e) {
				  			e.preventDefault();
				  			
				  			//撤销先前提出的修改请求
				  			if($(".currentTr").eq(0).length > 0){
				  				var currentTr = $(".currentTr").eq(0);
				  				$.each(currentTr.find("td"),function(idx,td){
				  					if(idx>0&&idx<6){
					  					var oldval = $(td).attr("oldval");
					  					$(td).html(oldval);
					  					$(td).removeAttr("oldval");
					  					$(td).removeAttr("contenteditable");
					  				}
				  				});
				  				currentTr.find("a").toggle();
				  				currentTr.removeClass("currentTr");
				  			}
			  				
				  			$(this).parents('tr').find('a').toggle();
				  			var $tr = $(this).parents('tr').eq(0);
				  			$tr.addClass("currentTr");
				  			$.each($tr.children(),function(idx,td){
				  				if(idx>0&&idx<6){
				  					var oldVal = $(td).html();
				  					$(td).attr("oldVal",oldVal);
				  					$(td).attr("contenteditable",true);
				  				}
				  			});
				  			$tr.children().eq(1).focus();
				  			
				  			//if ((index + 1) < max) {
				  			//	var data = table.data();
				  			//	table.clear();
				  			//	data.splice((index + 1), 0, data.splice(index, 1)[0]);
				  			//	table.rows.add(data).draw(false);
				  			//	table.page('next');
				  			//} else {
				  			//	alert("亲，已经到底了");
				  			//}
				  		});
				  		
				  		// 初始化修改按钮
				  		$("#div_airports").find("table").eq(0).on('click', 'a.submit', function(e) {
				  			e.preventDefault();
				  			
				  			//获取新值
				  			var $tr = $(this).parents('tr').eq(0);
				  			var newData = [];
				  			$.each($tr.children(),function(idx,td){
				  				if(idx<6){
				  					newData[idx] = $(td).html();
				  				}
				  			});
				  			newData[6] = "";
				  			
				  			var table = $("#div_airports").find("table").eq(0).DataTable();
  							var row = table.row($(this).parents('tr'));
  							var index = row.index();
  							var page = table.page();
			  				var data = table.data();
			  				
				  			if (confirm("确定要更新三字码为"+newData[0]+"的机场信息？")) {
				  				var airport ={
				  						code:newData[0]
			  	  					,cityName:newData[1]
			  	  					,cityNameInitial:newData[2]
			  	  					,airportName:newData[3]
			  	  					,dist:newData[4]
			  	  					,isHot:newData[5]
			  					};
				  				$.ajax({
				  					type:"put"
				  					,url:"<%=contextPath %>/airport/"
				  					,data:JSON.stringify(airport)
				  					,dataType:"json"
				  					,contentType:"application/json;charset=utf-8"
				  					,success:function(result){
				  						if(result.status=="success"){
							  				table.clear();
								  			data.splice(index, 1, newData);//替换掉旧的
								  			table.rows.add(data).draw(false);
				  							table.page(page);
							  				alert("更新成功");
							  				//$(this).parents('tr').find('a').toggle();
				  						}
				  						else if(result.status=="nonexistent"){
				  							alert("不存在的机场信息");
				  						}
				  						else{
				  							alert("更新失败失败");
				  						}
				  					}
				  					,error:function(e){
				  						console.log("getCities failed, Error:",e);
				  					}
				  				});
				  			}
				  		});
				  		
				  		// 初始化修改按钮
				  		$("#div_airports").find("table").eq(0).on('click', 'a.cancel', function(e) {
				  			e.preventDefault();
				  			var $tr = $(this).parents('tr').eq(0);
				  			$.each($tr.children(),function(idx,td){
				  				if(idx>0&&idx<6){
				  					var oldVal = $(td).attr("oldVal");
				  					$(td).html(oldVal);
				  					$(td).removeAttr("oldVal");
				  					$(td).removeAttr("contenteditable");
				  				}
				  			});
				  			$(this).parents('tr').find('a').toggle();
				  		});
		  			},
		              'json'
		  		).success(function(result){
		         		console.log("load succeed!");
	         	})
	 			.error(function(e){
	 				console.log("load failed, Error:",e);
	 				alert("加载失败！请稍后再试");
	 			})
	 			.complete(function(e){
	 				console.log("load complete!");
	 			});
		  	}
		  	
		 	// 初始化添加按钮
	  		$("#a_add_airport").on('click', function(e) {
	  			e.preventDefault();
	  			
	  			//撤销先前提出的修改请求
	  			if($(".currentTr").eq(0).length > 0){
	  				var currentTr = $(".currentTr").eq(0);
	  				$.each(currentTr.find("td"),function(idx,td){
	  					if(idx>0&&idx<6){
		  					var oldval = $(td).attr("oldval");
		  					$(td).html(oldval);
		  					$(td).removeAttr("oldval");
		  					$(td).removeAttr("contenteditable");
		  				}
	  				});
	  				currentTr.find("a").toggle();
	  				currentTr.removeClass("currentTr");
	  			}
	  			
	  			var airportCode = $("#ipt_airportCode").val();
	  			var cityName = $("#ipt_cityName").val();
	  			var cityNameInitial = $("#ipt_citiNameInitial").val();
	  			var airportName = $("#ipt_airportName").val();
	  			var dist = $("#sel_dist").val();
	  			var is_hot = $("#sel_is_hot").val();
	  			
	  			var url = "<%=contextPath %>/airport/";
	  			var params = {
  					code:airportCode
  					,cityName:cityName
  					,cityNameInitial:cityNameInitial
  					,airportName:airportName
  					,dist:dist
  					,isHot:is_hot	
	  			};
	  			$.post(
  		  			url,
  		  			params,
  		  			function(data){
  		  				if(data.status=="success"){
  		  					var table = $("#div_airports").find("table").eq(0).DataTable();
  		  					var page = table.page();
  		  					var pageSize = table.page.len();
  		  					var index = (page * pageSize);
  		  					var data = table.rows().data();
  		  					var airport = [];
  		  					airport[0] = airportCode;
	  		  				airport[1] = cityName;
	  		  				airport[2] = cityNameInitial;
	  		  				airport[3] = airportName;
	  		  				airport[4] = dist;
	  		  				airport[5] = is_hot;
  		  					airport[6] ="";
			  				table.clear();
			  				data.splice(index, 0, airport);
			  				table.rows.add(data).draw(false);
  		  					alert("添加成功");
  		  				}
  		  				else if(data.status=="existing"){
  		  					alert("机场已存在");
  		  				}
  		  				else {
  		  					alert("添加失败，请稍后再试。");
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
	  			
	  			//var table = $("#div_airports").find("table").eq(0).DataTable();
	  			//var index = table.row($(this).parents('tr')).index();
	  			//data = table.data();
	  			//table.clear();
	  			//alert(data[0][6]);
	  			//var newRow = ["","","","","",""];
	  			//data.splice((index + 1), 0, newRow);
	  			//alert(index);
	  			//data().splice((index + 1),0,newRow).draw();
	  			//table.rows.add(data).draw(false);
	  			//	var data = table.data();
	  			//	table.clear();
	  			//	data.splice((index - 1), 0, data.splice(index, 1)[0]);
	  			//	table.rows.add(data).draw();

	  		});
		  	
		 	//分页加载所有客户信息
		function loadCustomersWithPaging(fromIndex, recordAmount){
			var url = "<%=contextPath %>/customerManage/loadWithPaging";
			var params = {
				fromIndex:fromIndex
				, recordAmount:recordAmount
			};
			$.get(
		      		url,        			  
		      		params,
		        		function(result){ 		
		      			var temp = "";
		      			$.each(result.data, function(idx, item){
		      				temp += idx + "-" + item.id
		      					+ ", " + item.phone
		      					+ ", " + item.email
		      					+ "\n"
		      			});
		      			alert(temp);
		              },   
		              'json'  				  
		         	)
		         	.success(function(data){
		         		console.log("load customers succeed!");
		         	})
		 			.error(function(e){
		 				console.log("load customers failed,Error:",e);
		 			})
		 			.complete(function(e){
		 				console.log("load customers complete!");
		 			});
		}
		  	
		 	//分页加载所有员工信息
		function loadEmployeesWithPaging(fromIndex, recordAmount){
			var url = "<%=contextPath %>/employeeManage/loadAll";
			var params = {
				fromIndex:fromIndex
				, recordAmount:recordAmount
			};
			$.get(
		      		url,
		      		params,
		        		function(result){
		      			var temp = "";
		      			$.each(result.data, function(idx, item){
		      				temp += idx + "-" + item.no
		      					+ ", " + item.role
		      					+ "\n"
		      			});
		      			alert(temp);
		              },   
		              'json'  				  
		         	)
		         	.success(function(data){
		         		console.log("load employees succeed!");
		         	})
		 			.error(function(e){
		 				console.log("load employees failed,Error:",e);
		 			})
		 			.complete(function(e){
		 				console.log("load employees complete!");
		 			});
		}
		 	
		</script>
		

	</body>
</html>