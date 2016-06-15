package com.flight.trs.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月14日 下午8:26:21
 * @version V1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO
		return DataSourceContextHolder.get();
	}

}
