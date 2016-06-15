package com.flight.trs.dao.impl;

import org.springframework.stereotype.Repository;

import com.flight.trs.dao.IDeleteNotPhysicallyDAO;
import com.flight.trs.dao.IPassengerGaugeDAO;
import com.flight.trs.model.entity.PassengerGauge;
import com.flight.trs.model.entity.PassengerGaugeId;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月10日 下午6:13:11
 * @version V1.0
 */
@Repository("passengerGaugeDAO")
public class PassengerGaugeDAO 
	extends BaseDAO<PassengerGauge, PassengerGaugeId> 
	implements IPassengerGaugeDAO, IDeleteNotPhysicallyDAO<PassengerGauge, PassengerGaugeId>{

	
}
