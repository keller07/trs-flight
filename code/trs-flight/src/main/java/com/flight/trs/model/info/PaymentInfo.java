package com.flight.trs.model.info;

import java.util.Date;

import com.flight.trs.model.entity.Order;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 上午10:17:08
 * @version V1.0
 */
public class PaymentInfo {

	private Order order;
	private String paymentMode;
	private String paymentAccount;
	private double amount;
	private String transSeqNo;
	private Date paymentTime;
	
	public PaymentInfo() {
		// TODO 
	}

	
	
	public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
		this.order = order;
	}



	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentAccount() {
		return paymentAccount;
	}

	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransSeqNo() {
		return transSeqNo;
	}

	public void setTransSeqNo(String transSeqNo) {
		this.transSeqNo = transSeqNo;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((paymentAccount == null) ? 0 : paymentAccount.hashCode());
		result = prime * result + ((paymentMode == null) ? 0 : paymentMode.hashCode());
		result = prime * result + ((paymentTime == null) ? 0 : paymentTime.hashCode());
		result = prime * result + ((transSeqNo == null) ? 0 : transSeqNo.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentInfo other = (PaymentInfo) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (paymentAccount == null) {
			if (other.paymentAccount != null)
				return false;
		} else if (!paymentAccount.equals(other.paymentAccount))
			return false;
		if (paymentMode == null) {
			if (other.paymentMode != null)
				return false;
		} else if (!paymentMode.equals(other.paymentMode))
			return false;
		if (paymentTime == null) {
			if (other.paymentTime != null)
				return false;
		} else if (!paymentTime.equals(other.paymentTime))
			return false;
		if (transSeqNo == null) {
			if (other.transSeqNo != null)
				return false;
		} else if (!transSeqNo.equals(other.transSeqNo))
			return false;
		return true;
	}

	

}
