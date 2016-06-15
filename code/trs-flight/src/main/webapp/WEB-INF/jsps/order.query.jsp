<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<link type="text/css" href="<%=contextPath %>/resources/bootstrapselect/css/bootstrap-select.css" rel="stylesheet" />
    
<style>
</style>

	<div class="row">
		<div class="col-md-5">
			<div class="panel panel-default">
   				<div class="panel-heading">订单查询</div>
      			<div class="panel-body">
      				<form id="form_order" method="get" action="<%=contextPath %>/orderform">
		      			<div class="input-group">
			               <input name="orderNo" type="text" class="form-control" placeholder="输入订单号">
			               <div class="input-group-btn">
			                  <button type="submit" class="btn btn-default ">
			                     <span class="glyphicon glyphicon-search"></span>
			                  </button>
			               </div><!-- /btn-group -->
			            </div><!-- /input-group -->
      				</form>
      			</div>
			</div>
	   </div>
	</div>
	
    </div>
      <!-- row end -->
      
<script type="text/javascript" src="<%=contextPath %>/resources/js/custom/citypicker-1.0.14.js"></script>
<script type="text/javascript" src="<%=contextPath %>/resources/bootstrapselect/js/bootstrap-select.js"></script>


	
<script>
</script>

























<%@ include file="footer.jsp"%>