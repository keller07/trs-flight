package com.flight.trs.service;

import java.util.List;

import com.flight.trs.model.entity.FreightRate;
import com.flight.trs.model.entity.FreightRateId;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月21日 上午11:58:07
 * @version V1.0
 */
public interface IFreightRateService {

	//查找所有运价缓存
	List<FreightRate> find();
	//根据ID查询运价缓存
	FreightRate find(FreightRateId id);
	//刷新某一个
	void remove(FreightRateId id);
	//刷新缓存
	void flush();
	//添加运价缓存
	//void post(FreightRate FreightRates);
	//添加多个运价缓存
	//void post(Iterable<FreightRate> FreightRates);
	//修改运价缓存
	//void put(FreightRate FreightRates);
	//修改多个运价缓存
	//void put(Iterable<FreightRate> FreightRates);
	//根据ID删除运价缓存
	//void delete(FreightRateId id);
	//删除运价缓存
	//void delete(FreightRate FreightRate);
	//删除多个运价缓存
	//void delete(Iterable<FreightRate> FreightRates);
}
