package com.flight.trs.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * 
 * @Description: 分页工具工厂类
 * @author le
 * @date 2016年2月7日 下午5:23:12
 * @version V1.0
 */
public class PaginationFactory {

	private PaginationFactory() {
		// TODO 防止被重复实例化
	}
	
	private static PaginationFactory singlePaginationFactory = new PaginationFactory();
	
	private static Map<Class<? extends Serializable>, Pagination<?>> paginationMap 
		= new HashMap<>();
	
	public static PaginationFactory getFactory(){
		return singlePaginationFactory;
	}
	
	public Pagination<?> getPagination(Class<? extends Serializable> entityClazz){
		return paginationMap.get(entityClazz);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Serializable> Pagination<T> createPagination(List<T> records){
		
		Pagination<T> pagination = null;
		try {
			
			Class<?> entityClazz = GenericUtil.getParameterizedTypeOfList(records);
			if (entityClazz.isAssignableFrom(Serializable.class)) {
				if (paginationMap.containsKey(entityClazz)) {
					throw new IllegalArgumentException( entityClazz.getName() + "has been save to the pagination factory...");
				}
				pagination = new Pagination<T>(records);
		        paginationMap.put((Class<? extends Serializable>)entityClazz, pagination);
			}
		} catch (SecurityException e) {
			// TODO
			e.printStackTrace();
		}
		
        return pagination;
	}
	
	public void removePagination(Class<? extends Serializable> entityClazz){
		if (paginationMap.containsKey(entityClazz)) {
			paginationMap.remove(entityClazz);
		}
		else {
			throw new IllegalArgumentException( entityClazz.getName() + "dose not exit in the pagination factory...");
		}
	}
	
	/** 
	 * @ClassName: Pagination 
	 * @Description: 应用层分页工具
	 * @author le
	 * @date 2016年2月8日 下午5:35:30 
	 * 
	 * @param <T> 
	 */
	public class Pagination <T extends Serializable>{
	
		List<T> records;
		
		private Pagination(List<T> records){
			//
			this.records = records;
		}
		
		public List<T> nextPage(int fromIndex, int maxCount){
			
			if (fromIndex < 0) {
				throw new IllegalArgumentException("the firstIndex should not < 0...");
			}
			if (maxCount <= 0) {
				throw new IllegalArgumentException("the maxCount should not <= 0");
			}
			
			List<T> resultList = new ArrayList<T>();
			
			//如果请求的其实索引超出当前索引最大值则直接返回空集
			if (fromIndex > records.size()) {
				return resultList;
			}
			
			//如果可浏览的记录数少于请求的最大记录数，则返回所有可浏览的记录，否则返回正常数目的记录
			if ((fromIndex + maxCount) > records.size()) {
				resultList = records.subList(fromIndex, records.size());
			}
			else { 
				resultList = records.subList(fromIndex, fromIndex + maxCount );
			}
			
			return resultList;
			
		}
	}
}
