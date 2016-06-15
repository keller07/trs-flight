package com.flight.trs.service.basic;

import java.util.List;

import com.flight.trs.model.entity.FreightRate;
import com.flight.trs.model.entity.FreightRateId;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月14日 上午2:43:27
 * @version V1.0
 */
public interface IFreightRateService {
	//查找所有运价缓存
	List<FreightRate> get();
	//根据ID查询运价缓存
	FreightRate get(FreightRateId id);
	//添加运价缓存
	void post(FreightRate FreightRates);
	//添加多个运价缓存
	void post(Iterable<FreightRate> FreightRates);
	//修改运价缓存
	void put(FreightRate FreightRates);
	//修改多个运价缓存
	void put(Iterable<FreightRate> FreightRates);
	//根据ID删除运价缓存
	void delete(FreightRateId id);
	//删除运价缓存
	void delete(FreightRate FreightRate);
	//删除多个运价缓存
	void delete(Iterable<FreightRate> FreightRates);
}
