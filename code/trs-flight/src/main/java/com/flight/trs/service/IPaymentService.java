package com.flight.trs.service;

import java.math.BigDecimal;
import java.util.List;

import com.flight.trs.model.entity.Payment;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月19日 下午10:05:49
 * @version V1.0
 */
public interface IPaymentService {
	
	Payment pay(BigDecimal orderNo,String paymentMethod,String paymentAccount
			,double amount);

	List<Payment> findWithCustomerId(long customerId);
	
	List<Payment> findByOrderNo(BigDecimal orderNo);
	
	void deletePayment(BigDecimal paymentId);
	
}
