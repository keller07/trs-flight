package com.flight.trs.dao.impl;

import java.util.List;

import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.flight.trs.dao.ICustomerDAO;
import com.flight.trs.dao.IDeleteNotPhysicallyDAO;
import com.flight.trs.model.entity.Customer;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月17日 下午6:25:59
 * @version V1.0
 */
@Repository
public class CustomerDAO 
	extends BaseDAO<Customer, Long>
		implements ICustomerDAO, IDeleteNotPhysicallyDAO<Customer, Long> {
	
	@Override
	public Long getRowCountWithPhone(String phone){
		return getRowCount(Property.forName("phone").eq(phone));
	}	
	
	@Override
	public Long getRowCountWithEmail(String email){
		return getRowCount(Property.forName("email").eq(email));
	}
	
	@Override
	public Customer findCstmByPhoneAndPwd(String phone, String password) {
		// TODO
		Customer cstm = null;
		String hql = "from Customer c where c.phone = :phone and c.password = :password ";
		List<?> cstmList;
		try {
			cstmList = findByNamedParams(
					hql
					,new String[]{"phone","password"}
					,new Object[]{phone,password}
					);
			if(cstmList != null && !cstmList.isEmpty())
				cstm = (Customer) cstmList.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cstm;
	}
	
	@Override
	public Customer findCstmByEmailAndPwd(String email, String password) {
		// TODO
		Customer cstm = null;
		String hql = "from Customer c where c.email = :email and c.password = :password ";
		List<?> cstmList;
		try {
			cstmList = findByNamedParams(
					hql
					,new String[]{"email","password"}
					,new Object[]{email,password}
					);
			if(cstmList != null && !cstmList.isEmpty())
				cstm = (Customer) cstmList.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cstm;
	}

	@Override
	public Customer findCstmByPhone(String phone) {
		Customer cstm = null;
		String hql = "from Customer c where c.phone = :phone";
		List<?> cstmList;
		try {
			cstmList = findByNamedParam(hql, "phone", phone);
			if(cstmList != null && !cstmList.isEmpty())
				cstm = (Customer) cstmList.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cstm;
	}

	@Override
	public Customer findCstmByEmail(String email) {
		Customer cstm = null;
		String hql = "from Customer c where c.email = :email";
		List<?> cstmList;
		try {
			cstmList = findByNamedParam(hql, "email", email);
			if(cstmList != null && !cstmList.isEmpty())
				cstm = (Customer) cstmList.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cstm;
	}
	
}
