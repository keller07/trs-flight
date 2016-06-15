package com.flight.trs.service.basic;

import java.util.Collection;
import java.util.List;

import com.flight.trs.model.AirportsJson;
import com.flight.trs.model.entity.Airport;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月10日 下午2:06:47
 * @version V1.0
 */
public interface IAirportService {
	
	//添加机场信息
	public Airport add(String code, String cityName, String airportName);
	
	//添加机场信息
	public void post(Airport airport);

	//删除机场信息
	public void delete(String code);
	
	//删除机场信息
	public void delete(Airport airport);
	
	//根据ID批量删除机场信息
	public void deleteInBatch(Collection<String> codes);
	
	//更新机场信息
	public void update(Airport airport);
	
	//根据数据映射批量修改机场信息
	public void updateInBatch(Collection<Airport> airports);
	
	//统计所有机场数量
	public Long countAll();
	
	//根据ID获取
	public Airport get(String code);
	
	//加载所有机场信息 
	public List<Airport> findAll();
	
	//对所有机场进行排序分组并返回
	public AirportsJson sortAirports();
	
	//根据分页属性获取机场集合
	public List<Airport> findWithPaging(int firstIndex, int recordAmount);
	
}
