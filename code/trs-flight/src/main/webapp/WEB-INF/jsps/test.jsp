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
	<style>
	    *{ margin:0; padding:0;list-style: none;}
		#outer {
			width:367px;
			margin:auto;
			margin:0; padding:0;
		}
		#tab {
			overflow:hidden;
			zoom:1;
			font:12px/1.5 Tahoma;
			list-style: none;
			background:#5CACEE;
			border:1px solid #000;
			border-bottom-width: 0px;
			margin:auto;
			
		}
		#tab > li {
			float:left;
			color:#FFFFFF;
			width:73px;
			height:30px;	
			cursor:pointer;	
			line-height:30px;
			padding:0 10px;
			border:1px solid #000;
			border-top-width:0;
			border-right-width:0;
		}
		#tab > li:first-child {
			border-left-width:0;
		}
		#tab > li.current {
			color:#000;
			background:#FFFFFF;
			border-bottom-width:0;
		}
		#content {
			font:15px/1.5 "Microsoft YaHei";
			list-style: none;
			border:1px solid #000;
			border-top-width:0;
		}
		#content > li {
			line-height:25px;
			display:none;	
			margin:0px;
			padding:0px;
		}
		
		#content > li dl{
			overflow:hidden;
			zoom:1;
			margin-bottom:4px;
		}
		
		#content > li dl dt {
			width:15px;
			color:#FF0000;
			margin-left:8px;
			cursor:pointer;
		}
		#content > li dl dd {
			margin:-21px 0px 0px 28px;
		}
		
		#content > li dl dd li {
			width:84px;
			padding-left:5px;
			float:left;
			cursor:pointer;
		}
		#content > li dl dd li:hover {
			background:#ccc;
		}
		

	</style>
	
  </head>
  <body>
  <div class="container">  
        <div class="row">  
            <div class="col-md-12">  
                <table id="demo" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">  
                    <thead>  
                        <tr>  
                            <th>姓名</th>  
                            <th>职位</th>  
                            <th>办公室</th>  
                            <th>薪水</th>  
                            <th>操作</th>  
                        </tr>  
                    </thead>  
                    <tbody>  
                        <tr>  
                            <td>张三</td>  
                            <td>System Architect</td>  
                            <td>Edinburgh</td>  
                            <td>$320,800</td>  
                            <td></td>  
                        </tr>  
                        <tr>  
                            <td>李四</td>  
                            <td>Accountant</td>  
                            <td>Tokyo</td>  
                            <td>$170,750</td>  
                            <td></td>  
                        </tr>  
                        <tr>  
                            <td>王五</td>  
                            <td>Junior Technical Author</td>  
                            <td>San Francisco</td>  
                            <td>$86,000</td>  
                            <td></td>  
                        </tr>  
                        <tr>  
                            <td>赵六</td>  
                            <td>Senior Javascript Developer</td>  
                            <td>Edinburgh</td>  
                            <td>$433,060</td>  
                            <td></td>  
                        </tr>  
                        <tr>  
                            <td>小明</td>  
                            <td>Accountant</td>  
                            <td>Tokyo</td>  
                            <td>$162,700</td>  
                            <td></td>  
                        </tr>  
                        <tr>  
                            <td>小丽</td>  
                            <td>Integration Specialist</td>  
                            <td>New York</td>  
                            <td>$372,000</td>  
                            <td></td>  
                        </tr>  
                        <tr>  
                            <td>小春春</td>  
                            <td>Sales Assistant</td>  
                            <td>San Francisco</td>  
                            <td>$137,500</td>  
                            <td></td>  
                        </tr>  
  
                    </tbody>  
                </table>  
            </div>  
        </div>  
    </div>  
  	
  <script type="text/javascript" src="<%=contextPath %>/resources/datetimepicker/js/jquery-2.1.1.min.js"></script>

  <!-- Page-Level Demo Scripts - Tables - Use for reference -->
  <script>
  $(document).ready(function() {  
	  
	    $('#demo').DataTable({  
	        "paging": false,  
	        "ordering": false,  
	        "info": false,  
	        "searching": false,  
	        "columnDefs": [{  
	            // 定义操作列  
	            "targets": 4,  
	            "data": null,  
	            "render": function(data, type, row) {  
	                var html = '<a href="javascript:void(0);" class="delete btn btn-default btn-xs"><i class="fa fa-times"></i> 删除</a>'  
	                html += ' <a href="javascript:void(0);" class="up btn btn-default btn-xs"><i class="fa fa-arrow-up"></i> 上升</a>'  
	                html += ' <a href="javascript:void(0);" class="down btn btn-default btn-xs"><i class="fa fa-arrow-down"></i> 下降</a>'  
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
	    $('#demo tbody').on('click', 'a.delete', function(e) {  
	        e.preventDefault();  
	  
	        if (confirm("确定要删除该属性？")) {  
	            var table = $('#demo').DataTable();  
	            table.row($(this).parents('tr')).remove().draw();  
	        }  
	  
	    });  
	  
	    // 初始化上升按钮  
	    $('#demo tbody').on('click', 'a.up', function(e) {  
	        e.preventDefault();  
	        var table = $('#demo').DataTable();  
	        var index = table.row($(this).parents('tr')).index();  
	        if ((index - 1) >= 0) {  
	            var data = table.data();  
	            table.clear();  
	            data.splice((index - 1), 0, data.splice(index, 1)[0]);  
	            table.rows.add(data).draw();  
	        } else {  
	            alert("亲，已经到顶了");  
	        }  
	  
	    });  
	  
	    // 初始化下降按钮  
	    $('#demo tbody').on('click', 'a.down', function(e) {  
	        e.preventDefault();  
	  
	        var table = $('#demo').DataTable();  
	        var index = table.row($(this).parents('tr')).index();  
	        var max = table.rows().data().length;  
	        if ((index + 1) < max) {  
	            var data = table.data();  
	            table.clear();  
	            data.splice((index + 1), 0, data.splice(index, 1)[0]);  
	            table.rows.add(data).draw();  
	        } else {  
	            alert("亲，已经到底了");  
	        }  
	    });  
	});  
  </script>
  </body>
</html>