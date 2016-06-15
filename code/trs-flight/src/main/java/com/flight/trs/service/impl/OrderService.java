package com.flight.trs.service.impl;

import java.math.BigDecimal;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.flight.trs.dao.impl.OrderDAO;
import com.flight.trs.model.entity.Order;
import com.flight.trs.service.IOrderService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 下午2:16:57
 * @version V1.0
 */
@Repository
public class OrderService implements IOrderService {

	@Resource
	OrderDAO orderDAO;

	@Transactional
	@Override
	public void save(Order order) {
		// TODO
		orderDAO.save(order);
	}

	@Override
	public Order findByOrderNo(BigDecimal orderNo) {
		// TODO
		return orderDAO.get(orderNo);
	}

	@Transactional
	@Override
	public void updateOrder(Order order) {
		// TODO
		orderDAO.update(order);
	}

	@Transactional
	@Override
	public void deleteByOrderNo(BigDecimal orderNo) {
		// TODO
		orderDAO.deleteByIDNotPhysically(orderNo);
	}

}
