package com.flight.trs.dao;

import java.util.List;

import com.flight.trs.model.entity.Customer;
import com.flight.trs.model.entity.Order;
import com.flight.trs.model.entity.Ticket;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月20日 上午12:41:57
 * @version V1.0
 */
public interface ITicketDAO {

	List<Ticket> findByCustomer(Customer customer);
	
	List<Ticket> findByOrder(Order order);
	
}
