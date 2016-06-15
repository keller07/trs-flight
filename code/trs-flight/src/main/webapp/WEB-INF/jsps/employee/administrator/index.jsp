<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String contextPath = request.getContextPath();
%>
<jsp:include page="header.jsp"></jsp:include>

<div id="page-wrapper" style="min-height: 548px;">
	
	<div class="row">
                <div id="div_airport" class="col-lg-12" style="display:none">
                <form class="form-inline" role="form" style="margin-top:20px;margin-top:5px;">
                    <hr>
                    <input id="ipt_airportCode" type="text" style="width:79px;" placeholder="机场三字码"/>
                    <input id="ipt_cityName" type="text" style="width:176px;" placeholder="城市名"/>
                    <input id="ipt_citiNameInitial" type="text" style="width:80px;" placeholder="拼音首字母"/>
                    <input id="ipt_airportName" type="text" style="width:349px;" placeholder="机场名称"/>
                    <select id="sel_dist" style="width:100px;height:27px;">
                    	<option value="">国内</option>
                    	<option value="Asia">亚洲</option>
                    	<option value="America">美洲</option>
                    	<option value="Europe">欧洲</option>
                    	<option value="Africa">非洲</option>
                    	<option value="Oceania">大洋州</option>
                    </select>
                    <select id="sel_is_hot" style="width:105px;height:27px;">
                    	<option value="y">是</option>
                    	<option value="n">否</option>
                    </select>
                    <a id="a_add_airport" href="javascript:void(0);" class="create btn btn-default"><i class="fa fa-plus"></i> 添加</a>
                	<hr>
                </form>
                </div>
                <!-- /.col-lg-12 -->
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div id="div_airports" class="dataTable_wrapper">
           	</div>
         </div>
        <!-- /.col-lg-12 -->
   	</div>
  	<!-- /.row -->
</div>
<!-- /#page-wrapper -->

<jsp:include page="footer.jsp"></jsp:include>