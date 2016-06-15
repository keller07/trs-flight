package com.flight.trs.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.flight.trs.dao.IDeleteNotPhysicallyDAO;
import com.flight.trs.dao.IOrderDAO;
import com.flight.trs.model.entity.Customer;
import com.flight.trs.model.entity.Order;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月19日 下午11:42:33
 * @version V1.0
 */
@Repository
public class OrderDAO extends BaseDAO<Order, BigDecimal> 
	implements IOrderDAO, IDeleteNotPhysicallyDAO<Order, BigDecimal> {

	@Override
	public List<Order> findByCustomer(Customer customer) {
		// TODO
		return customer.getOrders();
	}
	
}
