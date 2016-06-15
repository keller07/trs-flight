package com.flight.trs.service.basic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.flight.trs.dao.impl.CarrierDAO;
import com.flight.trs.dao.impl.PassengerGaugeDAO;
import com.flight.trs.model.entity.Carrier;
import com.flight.trs.model.entity.PassengerGauge;
import com.flight.trs.model.entity.PassengerGaugeId;
import com.flight.trs.service.basic.IPassengerGaugeService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月10日 下午7:26:06
 * @version V1.0
 */
@Repository
public class PassengerGaugeService 
	implements IPassengerGaugeService {

	@Resource
	CarrierDAO carrierDAO;
	
	@Resource
	PassengerGaugeDAO passengerGaugeDAO;
	
	public PassengerGaugeService() {
		// TODO 
	}

	@Override
	public PassengerGauge add(String carrierCode, char clCode, String classOfService, float discount,
			String refund, String rescheduling, String endorsement, String invalidating, String upGrade,
			String exchange) {
		// TODO
		PassengerGaugeId id = new PassengerGaugeId(carrierCode, clCode);
		Carrier carrier = carrierDAO.get(carrierCode);
		PassengerGauge passengerGauge = new PassengerGauge(id, classOfService, discount, 
				refund, rescheduling, endorsement, invalidating, upGrade, exchange);
		passengerGauge.setCarrier(carrier);
		carrier.getPassengerGauges().add(passengerGauge);
		carrierDAO.save(carrier);
		return passengerGauge;
	}

	@Override
	public void deleteByID(PassengerGaugeId id) {
		// TODO
		PassengerGauge passengerGauge = passengerGaugeDAO.load(id);
		Carrier carrier = passengerGauge.getCarrier();
		carrier.getPassengerGauges().remove(carrier);
		passengerGaugeDAO.deleteNotPhysically(passengerGauge);
		carrierDAO.save(carrier);
	}

	@Override
	public void deleteByIDInBatch(Iterable<PassengerGaugeId> ids) {
		// TODO
		for (PassengerGaugeId passengerGaugeId : ids) {
			deleteByID(passengerGaugeId);
		}
	}

	@Override
	public Long countAll() {
		// TODO
		return passengerGaugeDAO.getRowCount();
	}

	@Override
	public List<PassengerGauge> findAll() {
		// TODO
		return passengerGaugeDAO.loadAll();
	}

	@Override
	public List<PassengerGauge> findWithPaging(int fromIndex, int recordAmount) {
		// TODO
		return passengerGaugeDAO.findForPaging(fromIndex, recordAmount);
	}

	@Override
	public PassengerGauge get(PassengerGaugeId id) {
		// TODO
		return passengerGaugeDAO.get(id);
	}

}
