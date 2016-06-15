package com.flight.trs.service.basic;

import java.util.Collection;
import java.util.List;

import com.flight.trs.model.entity.AircraftType;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月10日 下午5:34:12
 * @version V1.0
 */
public interface IAircraftTypeService {
	
	//添加机型信息
	public AircraftType add(String code, String name, String spell);

	//根据ID删除机型信息
	public void deleteByCode(String code);
	
	//根据ID批量删除机型信息
	public void deleteByCodeInBatch(Collection<String> codes);
	
	//统计所有机型数量
	Long countAll();
	
	//加载所有机型信息 
	List<AircraftType> findAll();
	
	//根据分页属性获取机型集合
	List<AircraftType> findWithPaging(int firstIndex, int recordAmount);

}
