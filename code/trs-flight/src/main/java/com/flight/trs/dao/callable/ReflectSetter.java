package com.flight.trs.dao.callable;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flight.trs.model.entity.AircraftType;
import com.flight.trs.model.entity.Airport;
import com.flight.trs.model.entity.Carrier;
import com.flight.trs.model.entity.Customer;
import com.flight.trs.model.entity.Employee;
import com.flight.trs.model.entity.PassengerGauge;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月4日 下午2:09:45
 * @version V1.0
 */
public class ReflectSetter<E extends Serializable> 
	implements IReflectSetter<E>{

	private Class<E> entityClass;
	private Map<Field, Method> setterMap;
	
	@SuppressWarnings("unchecked")
	public ReflectSetter(){
		
		this.entityClass = null;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
        	//根据实例类自动获取实体类类型
        	this.entityClass =(Class<E>) ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        
        setterMap = new HashMap<Field, Method>();
		Field[] fields = entityClass.getDeclaredFields();
		
		for (Field field : fields) {
			String fieldName = field.getName();
			Class<?> fieldType = field.getType();
			try {
				Method setter = entityClass.getMethod("set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1), fieldType);
				if(setter!= null)
					setterMap.put(field, setter);
			} catch (SecurityException se) {
				// TODO
				se.printStackTrace();
			} catch (NoSuchMethodException ignore) {
				// TODO
			}
		}
	}
	
	/** 
	 * @Title: getSetter 
	 * @Description: get the setter for field named @param fieldName
	 * @param fieldName name of field
	 * @return Method setter of field named @param fieldName
	 * @throws NoSuchFieldException if setterMap.KeySet dose not contains field named @param fieldName
	 */
	public Method getSetter(final String fieldName ) throws NoSuchFieldException{
		Iterator<Field> iterator = setterMap.keySet().iterator();
		Field fieldTemp;
		while (iterator.hasNext()) {
			fieldTemp = iterator.next();
			if(fieldName.equals(fieldTemp.getName())){
				return setterMap.get(fieldTemp);
			}
		}
		throw new NoSuchFieldException(fieldName);
	}

	/** 
	 * @Title: setFieldValue 
	 * @Description: set field named @param fieldName val @param fieldValue
	 * @param obj object to set field value
	 * @param fieldName name of field to set value
	 * @param fieldValue value to set
	 * @return void
	 * @throws 
	 */
	public void setFieldValue(final E obj, final String fieldName, final Object fieldValue) {
		Method setter;
		try {
			setter = getSetter(fieldName);
			setter.invoke(obj, fieldValue);
		} catch (IllegalAccessException e) {
			// TODO
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO
			e.printStackTrace();
		}
	}
	
}

@Repository("aircraftTypeSetter")
class AircraftTypeSetter extends ReflectSetter<AircraftType> {}

@Repository("airportSetter")
class AirportSetter extends ReflectSetter<Airport> {}

@Repository("carrierSetter")
class CarrierSetter extends ReflectSetter<Carrier> {}

@Repository("customerSetter")
class CustomerSetter extends ReflectSetter<Customer> {}

@Repository("employeeSetter")
class EmployeeSetter extends ReflectSetter<Employee> {}

@Repository("passengerGaugeSetter")
class PassengerGaugeSetter extends ReflectSetter<PassengerGauge> {}

