package com.flight.trs.service.basic.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flight.trs.dao.impl.CarrierDAO;
import com.flight.trs.model.entity.Carrier;
import com.flight.trs.service.basic.ICarrierService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月10日 下午1:43:20
 * @version V1.0
 */
@Repository("carrierService")
public class CarrierService 
	implements ICarrierService {

	@Resource(name="carrierDAO")
	CarrierDAO carrierDAO;
	
	public CarrierService() {
		// TODO 
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public Carrier add(String code, String name, String spell, String ticketCode) {
		// TODO
		Carrier carrier = new Carrier(code, name, spell, ticketCode);
		carrierDAO.save(carrier);
		return null;
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public void deleteByCode(String code) {
		// TODO
		Carrier carrier = carrierDAO.load(code);
		carrierDAO.delete(carrier);
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public void deleteByCodeInBatch(Collection<String> codes) {
		// TODO
		carrierDAO.deleteByIDInBatch(codes);
	}

	@Override
	public Long countAll() {
		// TODO
		return carrierDAO.getRowCount();
	}

	@Override
	public List<Carrier> findAll() {
		// TODO
		return carrierDAO.loadAll();
	}
	
	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public List<Carrier> findWithPaging(int firstIndex, int recordAmount) {
		// TODO
		List<Carrier> carrierList = null;
		carrierList = (List<Carrier>) carrierDAO.findForPaging(firstIndex, firstIndex);
		return carrierList;
	}

	@Override
	public Carrier findByCode(String code) {
		// TODO
		return carrierDAO.get(code);
	}

}
