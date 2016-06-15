package com.flight.trs.service.basic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.flight.trs.dao.impl.VoyageDAO;
import com.flight.trs.model.entity.Voyage;
import com.flight.trs.service.basic.IVoyageService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 下午12:12:43
 * @version V1.0
 */
@Repository
public class VoyageService implements IVoyageService {

	@Resource
	VoyageDAO voyageDAO;

	@Override
	public Voyage findSingleByExample(Voyage voyage) {
		// TODO
		return (Voyage) voyageDAO.findByExample(voyage).get(0);
	}

	@Transactional(
			readOnly=false)
	@Override
	public void add(Voyage voyage) {
		// TODO
		voyageDAO.save(voyage);
	}
	
	public Voyage findByUK(String flightNo, String depDatetime, String depAirportCode, String arrAirportCode){
		Voyage voyage = null;
		String hql = "select v from Voyage v left join v.depAirport dep left join v.arrAirport arr where v.flightNo = :flightNo and v.depDatetime = :depDatetime and dep.code = :depAirportCode and arr.code = :arrAirportCode";
		List<?> voyages;
		try {
			voyages = voyageDAO.findByNamedParams(hql, new String[]{
					"flightNo","depDatetime","depAirportCode","arrAirportCode"
			}, new Object[]{
					flightNo,depDatetime,depAirportCode,arrAirportCode
			});
			if(voyages != null && !voyages.isEmpty())
				voyage = (Voyage) voyages.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return voyage;
	}

}
