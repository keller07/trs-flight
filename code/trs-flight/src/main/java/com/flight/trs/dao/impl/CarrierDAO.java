package com.flight.trs.dao.impl;

import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.flight.trs.dao.ICarrierDAO;
import com.flight.trs.dao.IDeleteNotPhysicallyDAO;
import com.flight.trs.model.entity.Carrier;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月28日 下午9:26:54
 * @version V1.0
 */
@Repository("carrierDAO")
public class CarrierDAO
	extends BaseDAO<Carrier, String>
	implements ICarrierDAO, IDeleteNotPhysicallyDAO<Carrier, String> {

	@Override
	public Long getRowCountWithCode(String code) {
		// TODO
		return getRowCount(Property.forName("code").eq(code));
	}

	@Override
	public Long getRowCountWithName(String name) {
		// TODO
		return getRowCount(Property.forName("name").eq(name));
	}

}
