package com.flight.trs.dao.impl;

import java.util.List;


import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.flight.trs.dao.IAirportDAO;
import com.flight.trs.dao.IDeleteNotPhysicallyDAO;
import com.flight.trs.model.entity.Airport;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月28日 下午9:50:15
 * @version V1.0
 */
@Repository("airportDAO")
public class AirportDAO 
	extends BaseDAO<Airport, String> 
	implements IAirportDAO, IDeleteNotPhysicallyDAO<Airport, String> {

	@Override
	public Long getRowCountWithCode(String code) {
		// TODO
		return getRowCount(Property.forName("code").eq(code));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Airport> findByCityName(String cityName) {
		// TODO
		String hql = "from Airport a where a.cityName:=cityName";
		return (List<Airport>) findByNamedParam(hql, "cityName", cityName);
	}
	
}
