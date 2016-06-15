package com.flight.trs.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.flight.trs.dao.IFreightRateDAO;
import com.flight.trs.model.entity.FreightRate;
import com.flight.trs.model.entity.FreightRateId;
import com.flight.trs.service.IFreightRateService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月21日 下午12:21:10
 * @version V1.0
 */
@Repository("realFreightRateService")
public class FreightRateService implements IFreightRateService {

	private static final Logger logger = LoggerFactory.getLogger(FreightRateService.class);
	
	@Resource
	IFreightRateDAO freightRateDAO;
	
	@CachePut(value="freightRateCache")
	@Override
	public List<FreightRate> find() {
		// TODO
		List<FreightRate> freightRates = freightRateDAO.get();
		for (FreightRate freightRate : freightRates) {
			freightRate.setUpdateTime(new Date());
		}
		//logger.info("cache freightRates,size:"+ freightRates.size());
		return freightRates;
	}

	@Cacheable(value="freightRateCache",key="#id")
	@Override
	public FreightRate find(FreightRateId id) {
		// TODO
		//logger.info("can not element[id="+id.toString()+"] find in cache,query from datasource and cache it");
		return freightRateDAO.get(id);
		//return null;
	}
	
	@CacheEvict(value="freightRateCache",key="#id")
	@Override
	public void remove(FreightRateId id) {
		// TODO
		//logger.info("evict cache element["+id.toString()+"] from freightRateCache...");
	}
	
	@CacheEvict(value="freightRateCache", allEntries=true)
	@Override
	public void flush() {
		// TODO
		logger.info("flush freightRateCache...");
	}
}
