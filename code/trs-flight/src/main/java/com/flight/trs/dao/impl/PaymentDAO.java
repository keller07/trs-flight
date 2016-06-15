package com.flight.trs.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.flight.trs.dao.IDeleteNotPhysicallyDAO;
import com.flight.trs.dao.IPaymentDAO;
import com.flight.trs.model.entity.Customer;
import com.flight.trs.model.entity.Order;
import com.flight.trs.model.entity.Payment;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月20日 上午12:04:51
 * @version V1.0
 */
public class PaymentDAO extends BaseDAO<Payment, BigDecimal> 
	implements IPaymentDAO, IDeleteNotPhysicallyDAO<Payment, BigDecimal> {

	@Resource
	OrderDAO orderDAO;

	@Override
	public List<Payment> findByOrder(Order order) {
		// TODO
		return order.getPayments();
	}
	
	@Override
	public List<Payment> findByCustomer(Customer customer) {
		// TODO
		List<Payment> payments = new ArrayList<Payment>();
		List<Order> orders = orderDAO.findByCustomer(customer);
		for (Order order : orders) {
			payments.addAll(findByOrder(order));
		}
		return payments;
	}

}
