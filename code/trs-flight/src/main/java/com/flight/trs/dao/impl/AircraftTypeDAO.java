package com.flight.trs.dao.impl;

import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.flight.trs.dao.IAircraftTypeDAO;
import com.flight.trs.dao.IDeleteNotPhysicallyDAO;
import com.flight.trs.model.entity.AircraftType;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月28日 下午10:10:14
 * @version V1.0
 */
@Repository("aircraftTypeDAO")
public class AircraftTypeDAO 
	extends BaseDAO<AircraftType, String> 
	implements IAircraftTypeDAO, IDeleteNotPhysicallyDAO<AircraftType, String> {
	
	@Override
	public Long getRowCountWithCode(String code) {
		// TODO
		return getRowCount(Property.forName("code").eq(code));
	}
	
}
