package com.flight.trs.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.flight.trs.dao.IDeleteNotPhysicallyDAO;
import com.flight.trs.dao.ITicketDAO;
import com.flight.trs.model.entity.Customer;
import com.flight.trs.model.entity.Order;
import com.flight.trs.model.entity.Ticket;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月20日 上午12:50:13
 * @version V1.0
 */
public class TicketDAO extends BaseDAO<Ticket, BigDecimal> 
	implements ITicketDAO, IDeleteNotPhysicallyDAO<Ticket, BigDecimal> {

	@Resource
	OrderDAO orderDAO;
	
	@Resource
	VoyageDAO voyageDAO;
	
	@Override
	public List<Ticket> findByCustomer(Customer customer) {
		// TODO
		List<Ticket> tickets = new ArrayList<Ticket>();
		List<Order> orders = orderDAO.findByCustomer(customer);
		for (Order order : orders) {
			tickets.addAll(findByOrder(order));
		}
		return tickets;
	}

	@Override
	public List<Ticket> findByOrder(Order order) {
		// TODO
		return order.getTickets();
	}

}
