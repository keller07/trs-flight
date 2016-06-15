package com.flight.trs.service;

import com.flight.trs.model.entity.Order;
import com.flight.trs.model.info.PaymentInfo;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 上午10:57:36
 * @version V1.0
 */
public interface IBookTicketService {
	String getPNR(Order order);
	PaymentInfo getPaymenInfo(Order order, String paymentMode, int totalPrice);
}
