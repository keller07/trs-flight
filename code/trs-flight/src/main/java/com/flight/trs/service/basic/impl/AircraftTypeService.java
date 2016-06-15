package com.flight.trs.service.basic.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flight.trs.dao.impl.AircraftTypeDAO;
import com.flight.trs.model.entity.AircraftType;
import com.flight.trs.service.basic.IAircraftTypeService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月10日 下午6:44:54
 * @version V1.0
 */
@Repository("aircraftTypeService")
public class AircraftTypeService 
	implements IAircraftTypeService{

	@Resource(name="aircraftTypeDAO")
	AircraftTypeDAO aircraftTypeDAO;
	
	public AircraftTypeService() {
		// TODO 
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public AircraftType add(String code, String name, String spell) {
		// TODO
		AircraftType aircraftType = new AircraftType(code, name, spell);
		aircraftTypeDAO.save(aircraftType);
		return aircraftType;
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public void deleteByCode(String code) {
		// TODO
		aircraftTypeDAO.deleteByIDNotPhysically(code);
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public void deleteByCodeInBatch(Collection<String> codes) {
		// TODO
		aircraftTypeDAO.deleteByIDNotPhysicallyInBatch(codes);
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public Long countAll() {
		// TODO
		return aircraftTypeDAO.getRowCount();
	}

	@Transactional
	@Override
	public List<AircraftType> findAll() {
		// TODO
		return aircraftTypeDAO.loadAll();
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public List<AircraftType> findWithPaging(int firstIndex, int recordAmount) {
		// TODO
		return aircraftTypeDAO.findForPaging(firstIndex, recordAmount);
	}

}
