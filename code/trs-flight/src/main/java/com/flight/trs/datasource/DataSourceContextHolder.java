package com.flight.trs.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月14日 下午8:17:56
 * @version V1.0
 */
@Aspect
public class DataSourceContextHolder{

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	private static Logger logger = LoggerFactory.getLogger("DataSourceContextHolder");
	
	public static final String DEFAULT = null;
	
	public static String get() {
		return contextHolder.get();
	}

	@Before("@annotation(usingDataSource)")
	public static void set(JoinPoint joinpoint, UsingDataSource usingDataSource){
		if(usingDataSource != null){
			contextHolder.set(usingDataSource.dataSourceName());
			logger.info("change datasource......");
		}else{
			contextHolder.set(DEFAULT);
		}
	}
	
	@After("@annotation(usingDataSource)")
	public static void reset(JoinPoint joinpoint, UsingDataSource usingDataSource) {
		contextHolder.set(DEFAULT);
		logger.info("reset datasource......");
	}
	
	public static void clear(){
		contextHolder.remove();
	}
	
}
