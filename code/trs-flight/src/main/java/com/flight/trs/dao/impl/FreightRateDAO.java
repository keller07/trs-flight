package com.flight.trs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flight.trs.dao.IFreightRateDAO;
import com.flight.trs.model.entity.FreightRate;
import com.flight.trs.model.entity.FreightRateId;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月14日 上午1:40:15
 * @version V1.0
 */
@Repository
public class FreightRateDAO extends BaseDAO<FreightRate, FreightRateId> implements IFreightRateDAO {

	@Override
	public List<FreightRate> get() {
		return super.loadAll();
	}

	@Override
	public void post(FreightRate FreightRate) {
		super.save(FreightRate);
	}

	@Override
	public void post(Iterable<FreightRate> freightRates) {
		for (FreightRate freightRate : freightRates) {
			post(freightRate);
		}
	}

	@Override
	public void put(FreightRate freightRate) {
		super.update(freightRate);
		
	}

	@Override
	public void put(Iterable<FreightRate> freightRates) {
		for (FreightRate freightRate : freightRates) {
			put(freightRate);
		}
	}

	@Override
	public void delete(FreightRateId id) {
		super.deleteByID(id);
	}

	@Override
	public void delete(FreightRate FreightRate) {
		super.delete(FreightRate);
	}

	@Override
	public void delete(Iterable<FreightRate> freightRates) {
		for (FreightRate freightRate : freightRates) {
			delete(freightRate);
		}
	}

}
