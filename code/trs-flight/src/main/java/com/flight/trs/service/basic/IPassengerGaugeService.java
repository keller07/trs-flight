package com.flight.trs.service.basic;

import java.util.List;

import com.flight.trs.model.entity.PassengerGauge;
import com.flight.trs.model.entity.PassengerGaugeId;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月10日 下午5:36:43
 * @version V1.0
 */
public interface IPassengerGaugeService {
	
	//添加客规信息
	public PassengerGauge add(String carrierCode, char clCode, String classOfService, float discount,
			String refund, String rescheduling, String endorsement, String invalidating, String upGrade,
			String exchange);

	//查找
	public PassengerGauge get(PassengerGaugeId id);
	
	//根据ID删除客规信息
	public void deleteByID(PassengerGaugeId id);
	
	//根据ID批量删除客规信息
	public void deleteByIDInBatch(Iterable<PassengerGaugeId> ids);
	
	//统计所有客规数量
	Long countAll();
	
	//加载所有客规信息 
	List<PassengerGauge> findAll();
	
	//根据分页属性获取客规集合
	List<PassengerGauge> findWithPaging(int firstIndex, int recordAmount);

}
