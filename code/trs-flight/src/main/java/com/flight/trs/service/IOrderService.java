package com.flight.trs.service;

import java.math.BigDecimal;

import com.flight.trs.model.entity.Order;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月19日 下午10:05:06
 * @version V1.0
 */
public interface IOrderService {
	
	void save(Order order);
	
	Order findByOrderNo(BigDecimal orderNo);
	
	void updateOrder(Order order);
	
	void deleteByOrderNo(BigDecimal orderNo);
	
}
