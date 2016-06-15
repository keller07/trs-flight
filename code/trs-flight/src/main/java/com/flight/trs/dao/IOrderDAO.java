package com.flight.trs.dao;

import java.util.List;

import com.flight.trs.model.entity.Customer;
import com.flight.trs.model.entity.Order;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月19日 下午11:25:23
 * @version V1.0
 */
public interface IOrderDAO {
	
	//查找某用户下的所有订单
	List<Order> findByCustomer(Customer customer);
	
}
