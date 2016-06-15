package com.flight.trs.service.basic;

import java.util.Collection;
import java.util.List;

import com.flight.trs.model.entity.Carrier;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月9日 下午11:48:58
 * @version V1.0
 */
public interface ICarrierService {
	
	//添加承运人
	public Carrier add(String code, String name, String spell, String ticketCode);

	//根据ID删除承运人
	public void deleteByCode(String code);
	
	//根据ID批量删除机场信息
	public void deleteByCodeInBatch(Collection<String> codes);
	
	//统计所有承运人数量
	Long countAll();
	
	//加载所有承运人信息
	List<Carrier> findAll();
	
	//根据分页属性获取承运人集合
	List<Carrier> findWithPaging(int firstIndex, int recordAmount);

	//根据代号查找承运人
	Carrier findByCode(String code);
}
