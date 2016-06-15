package com.flight.trs.dao.callable;

import java.io.Serializable;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月4日 下午7:01:06
 * @version V1.0
 */
public interface IReflectSetter<E extends Serializable> {
	public void setFieldValue(final E obj, final String fieldName, final Object fieldValue);
}
