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

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月17日 下午1:03:19
 * @version V1.0
 */
public class FunctionCall extends HibernateDaoSupport {

	//以注解的方式注入HibernateTemplate
	@Autowired
	protected final void setHibernateTemplateForAutowired(HibernateTemplate hibernateTemplate) {
		super.setHibernateTemplate(hibernateTemplate);
	}
	
	public ResultSet callReturnCURSOR(final String funcName, final Object... parms){
		
		return 
		(ResultSet) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			
			ResultSet returnCursor = null;
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException{
				session.doWork(new Work() {
					
					@Override
					public void execute(Connection connection) throws SQLException {
						
						CallableStatement call = null;
						
						// 拼接sql：{ ? = call funcname ( ?, ?, ? ) }
						StringBuilder sqlSB = new StringBuilder("{ ? = call ");	//	函数必须有返回值
						sqlSB.append(funcName);
						if (parms != null) {
							if (parms.length > 0) {
								//函数的第一个参数对应的占位符 
								sqlSB.append("( ?");
								for (int i = 1; i < parms.length; i++) {
									//函数第二以上个参数对应的占位符
									sqlSB.append(", ?");
								}
								sqlSB.append(" )");
							}
						}
						sqlSB.append(" }");
						String sql = sqlSB.toString();
						call = connection.prepareCall(sql);
						
						// 设置返回值(第一个参数)的类型
						call.registerOutParameter(1, -10);
						
						// 设置函数参数列表的值
						if (parms != null) {
							for (int i = 0; i < parms.length; i++) {
								// sql中第2个参数，对应被调用函数第1个参数，取值parms数组下标0
								call.setObject( i + 2, parms[i]);
							}
						}
						
						call.executeQuery();
						
						// 返回值被赋值于第一个参数，因此第一个参数即返回值， CORSOR 对应 ResultSet
						returnCursor = (ResultSet) call.getObject(1);
					}
				});
				return returnCursor;
			}
		});
		
	}

}
