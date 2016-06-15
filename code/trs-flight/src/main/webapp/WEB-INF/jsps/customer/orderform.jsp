<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String contextPath = request.getContextPath();
%>
<%@page 
	import="com.flight.trs.model.entity.Order"
	import="java.math.BigDecimal"
	import="java.util.Date"
	import="java.math.BigDecimal"
	import="java.math.BigDecimal"
	
	
%>
<%
	                    Order order = (Order)request.getAttribute("order");
	                    BigDecimal no = order.getNo();
	                	String contactName = order.getContactName();
	                	String contactPhone = order.getContactPhone();
	                	Date orderDate = order.getOrderDate();
	                	int totalCost = order.getTotalCost();
	                	String remark = order.getRemark();
	                	String pnr = order.getPnr();
	                	String status = order.getStatus();
	                	Date lastModified = order.getLastModified();
	                	
	                    //out.println(order.getNo());
	                    //out.println(order.getNo());
	                    //out.println(order.getNo());
	                    //out.println(order.getNo());
	                    //out.println(order.getNo());
	                    //out.println(order.getNo());
	                    //out.println(order.getNo());
	                    %>
<jsp:include page="header.jsp"></jsp:include>

		<div id="page-wrapper" style="min-height: 548px;">
	            <div class="row">
	            <div class="col-lg-5">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bell fa-fw"></i> 订单信息
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:;" class="list-group-item">
                                    <i class="fa fa-comment fa-fw"></i>订单编号：
                                    <span class="pull-right text-muted small"><em><%=no %></em>
                                    </span>
                                </a>
                                <a href="javascript:;" class="list-group-item">
                                    <i class="fa fa-twitter fa-fw"></i>联系人姓名
                                    <span class="pull-right text-muted small"><em><%=contactName %></em>
                                    </span>
                                </a>
                                <a href="javascript:;" class="list-group-item">
                                    <i class="fa fa-envelope fa-fw"></i>联系方式
                                    <span class="pull-right text-muted small"><em><%=contactPhone %></em>
                                    </span>
                                </a>
                                <a href="javascript:;" class="list-group-item">
                                    <i class="fa fa-tasks fa-fw"></i>下单时间
                                    <span class="pull-right text-muted small"><em><%=orderDate %></em>
                                    </span>
                                </a>
                                <a href="javascript:;" class="list-group-item">
                                    <i class="fa fa-upload fa-fw"></i>订单金额
                                    <span class="pull-right text-muted small"><em><%=totalCost %></em>
                                    </span>
                                </a>
                                <a href="javascript:;" class="list-group-item">
                                    <i class="fa fa-bolt fa-fw"></i>订单备注
                                    <span class="pull-right text-muted small"><em><%=remark %></em>
                                    </span>
                                </a>
                                <a href="javascript:;" class="list-group-item">
                                    <i class="fa fa-warning fa-fw"></i>订单状态
                                    <span class="pull-right text-muted small"><em><%=status %></em>
                                    </span>
                                </a>
                                <a href="javascript:;" class="list-group-item">
                                    <i class="fa fa-shopping-cart fa-fw"></i> 订座记录PNR
                                    <span class="pull-right text-muted small"><em><%=pnr %></em>
                                    </span>
                                </a>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
	            </div>
	        </div>
	        <!-- /#page-wrapper -->

<jsp:include page="footer.jsp"></jsp:include>