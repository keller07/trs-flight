package com.flight.trs.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月14日 下午10:34:28
 * @version V1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface UsingDataSource {
	
	String dataSourceName() default DS2PDBTRS;
	
	String DS2PDBTRS = "dataSource2pdbtrs";
	
	String DS2PDBGDS = "dataSource2pdbgds";
	
}
//enum dataSourceName{DS2PDBTRS,DS2PDBGDS}