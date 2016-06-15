package com.flight.trs.dao;

import java.util.List;

import com.flight.trs.model.entity.Airport;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月28日 下午9:40:48
 * @version V1.0
 */
public interface IAirportDAO {

	//查看承运人代码为指定值的承运人记录数
	public Long getRowCountWithCode(String code);
	
	//根据城市名查找机场
	public List<Airport> findByCityName(String cityName);
	
}
