package com.flight.trs.service.basic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.flight.trs.dao.IFreightRateDAO;
import com.flight.trs.model.entity.FreightRate;
import com.flight.trs.model.entity.FreightRateId;
import com.flight.trs.service.basic.IFreightRateService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月14日 上午2:44:16
 * @version V1.0
 */
@Repository
public class FreightRateService implements IFreightRateService {

	@Resource
	IFreightRateDAO freightRateDAO;

	@Override
	public List<FreightRate> get() {
		// TODO
		return freightRateDAO.get();
	}

	@Override
	public FreightRate get(FreightRateId id) {
		// TODO
		return freightRateDAO.get(id);
	}

	@Override
	public void post(FreightRate FreightRates) {
		// TODO
		freightRateDAO.post(FreightRates);
	}

	@Override
	public void post(Iterable<FreightRate> FreightRates) {
		// TODO
		freightRateDAO.post(FreightRates);
	}

	@Override
	public void put(FreightRate FreightRate) {
		// TODO
		freightRateDAO.put(FreightRate);
	}

	@Override
	public void put(Iterable<FreightRate> FreightRates) {
		// TODO
		freightRateDAO.put(FreightRates);
	}

	@Override
	public void delete(FreightRateId id) {
		// TODO
		freightRateDAO.delete(id);
	}

	@Override
	public void delete(FreightRate FreightRate) {
		// TODO
		freightRateDAO.delete(FreightRate);
	}

	@Override
	public void delete(Iterable<FreightRate> FreightRates) {
		// TODO
		freightRateDAO.delete(FreightRates);
	}
	
}
