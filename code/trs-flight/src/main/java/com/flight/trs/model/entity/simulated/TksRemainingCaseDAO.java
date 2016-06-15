package com.flight.trs.model.entity.simulated;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flight.trs.dao.impl.BaseDAO;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月15日 上午3:53:29
 * @version V1.0
 */
@Repository
public class TksRemainingCaseDAO extends BaseDAO<TksRemainingCase, TksRemainingCaseId>
	implements ITksRemainingCaseDAO {

	@Override
	public List<TksRemainingCase> get() {
		// TODO
		return super.loadAll();
	}

	@Override
	public TksRemainingCase get(TksRemainingCaseId id) {
		// TODO
		return super.get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TksRemainingCase> get(String flightNo, String depAirportCode, String arrAirportCode, String depTime,
			String depDate) {
		// TODO
		String hql = "from TksRemainingCase tks where tks.id.flightNo=:flightNo and tks.id.depAirportCode=:depAirportCode and tks.id.arrAirportCode=:arrAirportCode and tks.id.depTime=:depTime and tks.id.depDate=:depDate";
		return (List<TksRemainingCase>) findByNamedParams(hql, new String[]{"flightNo","depAirportCode","arrAirportCode","depTime","depDate"}
		, new Object[]{flightNo, depAirportCode, arrAirportCode, depTime, depDate});
	}

}
