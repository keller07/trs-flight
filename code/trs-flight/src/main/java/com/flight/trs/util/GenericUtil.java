package com.flight.trs.util;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;



/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月9日 下午1:17:32
 * @version V1.0
 */
public class GenericUtil {

	public GenericUtil() {
		// TODO 
	}

	public static Class<?> getParameterizedTypeOfList(List<?> list){
		Class<?> returnType = null;
		try {
			@SuppressWarnings("unused")
			Method getMethod = list.getClass().getMethod("get",int.class);
			Class<?> clazz = list.getClass().getSuperclass().getSuperclass();
			System.err.println(clazz.getName());
			Type superClazz = clazz.getGenericSuperclass();
			System.out.println(superClazz.getTypeName());
			if ((Type)clazz instanceof ParameterizedType) {
				Type[] types = ((ParameterizedType)superClazz).getActualTypeArguments();
				for (Type type : types) {
					System.out.println(type.getTypeName());
				}
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO
			e.printStackTrace();
		}
		return returnType;
	}
	
}
