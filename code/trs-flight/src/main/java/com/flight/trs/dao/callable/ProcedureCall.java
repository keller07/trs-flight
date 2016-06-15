package com.flight.trs.dao.callable;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.flight.trs.datasource.UsingDataSource;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月12日 下午4:47:52
 * @version V1.0
 */
@Repository("procedureCall")
public class ProcedureCall extends HibernateDaoSupport{
	
	public ProcedureCall() {
		// TODO 
	}
	
	//以注解的方式注入HibernateTemplate
	@Autowired
	public final void setHibernateTemplateForAutowired(HibernateTemplate hibernateTemplate) {
		super.setHibernateTemplate(hibernateTemplate);
	}

//	String funcName, int returnSqlType, Map<String, Object> parms
	@UsingDataSource(dataSourceName=UsingDataSource.DS2PDBGDS)
	public void callFunc(){
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException{
				session.doWork(new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						// TODO
						CallableStatement call = connection.prepareCall("{ ? = call func_query_flight(?,?,?,?,?,?) }" );
						
						//-10 表示sqlType OracleTypes.CURSOR,不想导入ojdbc的jar包可以用-10代替
						call.registerOutParameter(1, -10);
						
//						call.setString(2, "--");
						call.setObject(2, "--");
//						call.setString(3, "SHA");
						call.setObject(3, "SHA");
//						call.setString(4, "PEK");
						call.setObject(4, "PEK");
//						call.setString(5, "20160812");
						call.setObject(5, "20160812");
//						call.setInt(6, 0);
						call.setObject(6, 0);
//						call.setInt(7, 20);
						call.setObject(7, 20);
						call.executeQuery();
						ResultSet rs = (ResultSet) call.getObject(1);
						while(rs.next()){
							System.out.println(rs.getString(2));
						}
					}
				});
				return null;
			}
		});
		
	}
	
//	query.setParameter("p_carrier_code", "--");
//	query.setParameter("p_dep_airport_code", "SHA");
//	query.setParameter("p_arr_airport_code", "PEK");
//	query.setParameter("p_dep_dete", "20160812");
//	query.setParameter("p_from_index", 0);
//	query.setParameter("p_record_amount", 20);
//	List resultList = query.list();
//	for (Object object : resultList) {
//		System.out.println(object.toString());
//	}
	
	
//	ProcedureCall procedureCall =  session.createStoredProcedureCall("proc_query_flight");
//	
//	procedureCall.registerParameter("crsr_flight_info", void.class, ParameterMode.REF_CURSOR);
//	procedureCall.registerParameter("p_carrier_code", String.class, ParameterMode.IN);
//	ParameterRegistration<String> prmRegistrationDAC =  procedureCall.registerParameter("p_dep_airport_code", String.class, ParameterMode.IN);
//	prmRegistrationDAC.bindValue("SHA");
//	ParameterRegistration<String> prmRegistrationAAC =  procedureCall.registerParameter("p_arr_airport_code", String.class, ParameterMode.IN);
//	prmRegistrationAAC.bindValue("PEK");
//	ParameterRegistration<String> prmRegistrationDD =  procedureCall.registerParameter("p_dep_dete", String.class, ParameterMode.IN);
//	prmRegistrationDD.bindValue("20160812");
//	procedureCall.registerParameter("p_from_index", Integer.class, ParameterMode.IN);
//	procedureCall.registerParameter("p_record_amount", Integer.class, ParameterMode.IN);
//	ProcedureOutputs outputs = procedureCall.getOutputs();
//	Output output = outputs.getCurrent();
//	if( output!= null && output.isResultSet()){
//		ResultSetOutput rSetOutput = (ResultSetOutput)output;
//		List list = rSetOutput.getResultList();
//		System.out.println("..........结果集长度："+list.size());
//		for (Object object : list) {
//			System.out.println(object.toString());
//		}
//	}
//	while(outputs.goToNext()){
//		output = outputs.getCurrent();
//		if( output!= null && output.isResultSet()){
//			ResultSetOutput rSetOutput = (ResultSetOutput)output;
//			List list = rSetOutput.getResultList();
//			System.out.println("..........结果集长度："+list.size());
//			for (Object object : list) {
//				System.out.println(object.toString());
//			}
//		}
//	}
	
}
