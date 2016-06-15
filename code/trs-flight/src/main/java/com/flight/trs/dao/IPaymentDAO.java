package com.flight.trs.dao;

import java.util.List;

import com.flight.trs.model.entity.Customer;
import com.flight.trs.model.entity.Order;
import com.flight.trs.model.entity.Payment;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月19日 下午11:56:17
 * @version V1.0
 */
public interface IPaymentDAO {

	//查找某用户下的所有订单
	List<Payment> findByCustomer(Customer customer);
	
	List<Payment> findByOrder(Order order);
	
}
