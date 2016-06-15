package com.flight.trs.service.impl;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.flight.trs.model.entity.Order;
import com.flight.trs.model.info.PaymentInfo;
import com.flight.trs.service.IBookTicketService;
import com.flight.trs.util.PNRGenerator;
import com.flight.trs.util.TransSEQGenerator;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 上午10:58:18
 * @version V1.0
 */
@Repository
public class BookTicketService implements IBookTicketService {

	private static final Logger logger = LoggerFactory.getLogger(BookTicketService.class);
	
	@Override
	public String getPNR(Order order) {
		// TODO
		return PNRGenerator.getPNR();
	}
	
	@Override
	public PaymentInfo getPaymenInfo(Order order, String paymentMode,int totalPrice){
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setOrder(order);
		paymentInfo.setPaymentMode(paymentMode);
		
		//生成随机银行卡号
		Random random = new Random();
        StringBuilder sBPaymentAccount=new StringBuilder();
        for (int i=0;i<19;i++)     
        {     
            int rtmp = random.nextInt(10); 
            sBPaymentAccount.append((char)(rtmp+48));         
        }     
        //银行卡号赋值
        String paymentAccount = sBPaymentAccount.toString();
        paymentInfo.setPaymentAccount(paymentAccount);
        paymentInfo.setAmount(totalPrice);
        String transSeqNo = "0000000000000000000";
		try {
			transSeqNo = TransSEQGenerator.getObject();
		} catch (Exception e) {
			// TODO
			return null;
			//e.printStackTrace();
		}
        paymentInfo.setTransSeqNo(transSeqNo);
        Date paymentTime = new Date();
        paymentInfo.setPaymentTime(paymentTime);
		return paymentInfo;
	}

}
