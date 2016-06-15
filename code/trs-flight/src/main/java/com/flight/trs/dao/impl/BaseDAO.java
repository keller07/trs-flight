package com.flight.trs.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.flight.trs.dao.IBaseDAO;
import com.flight.trs.dao.callable.IReflectSetter;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月22日 下午9:02:12
 * @version V1.0
 * @param <E>
 * @param <PK>
 */
public class BaseDAO<E extends Serializable,PK extends Serializable>
	extends HibernateDaoSupport implements IBaseDAO<E, PK> {
	
	// 实体类类型(由构造方法自动赋值)
	private Class<E> entityClass;
	
    // 构造方法
	@SuppressWarnings("unchecked")
	public BaseDAO() {
		// TODO 
		this.entityClass = null;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
        	//根据实例类自动获取实体类类型
        	this.entityClass =(Class<E>) ((ParameterizedType) type).getActualTypeArguments()[0];
        }
	}

	//以注解的方式注入HibernateTemplate
	@Autowired
	protected final void setHibernateTemplateForAutowired(HibernateTemplate hibernateTemplate) {
		
		super.setHibernateTemplate(hibernateTemplate);
	
	}
	
	//-------------------------------------------------------------------------
	// Convenience methods for loading individual objects
	//-------------------------------------------------------------------------

	@Override
	public E get(final PK id) throws DataAccessException {
		
		return getHibernateTemplate().get(entityClass, id, null);
	
	}
	
	@Override
	public E getWithLock(final PK id, final LockMode lockMode) throws DataAccessException, HibernateException {
		
		E entity = getHibernateTemplate().get(entityClass, id, lockMode);
		if(entity != null){
			flush();	//立即刷新，否则锁不会生效	
		}
		return entity;
	
	}
	
	@Override
	public E load(final PK id) throws DataAccessException {
		
		return getHibernateTemplate().load(entityClass, id, null);
	
	}
	
	@Override
	public E loadWithLock(final PK id, final LockMode lockMode) throws DataAccessException, HibernateException {
		
		E entity = getHibernateTemplate().load(entityClass, id, lockMode);
		if(entity != null){
			flush();	//立即刷新，否则锁不会生效	
		}
		return entity;
	
	}
	
	@Override
	public List<E> loadAll() throws DataAccessException, HibernateException {
		
		return getHibernateTemplate().loadAll(entityClass);
	
	}
	
	//public List<E> loadAllWithLock(final LockMode lockMode) throws DataAccessException;

	@Override
	public void refresh(final E entity) throws DataAccessException {
		
		getHibernateTemplate().refresh(entity, null);
	
	}

	@Override
	public void refreshWithLock(final E entity, final LockMode lockMode) throws DataAccessException, HibernateException {
		
		getHibernateTemplate().refresh(entity, lockMode);
		flush();
	
	}

	@Override
	public boolean contains(final E entity) throws DataAccessException {
		
		return getHibernateTemplate().contains(entity);
	
	}
	
	@Override
	public void evict(final E entity) throws DataAccessException, HibernateException {
		
		getHibernateTemplate().evict(entity);
	
	}
	
	
	/** 
	 * @Title: initialize 
	 * @Description: 初始化实体代理，
	 * 		用于防止延迟加载时 no session or session is closed 的问题
	 * @param @param proxy
	 * @param @throws DataAccessException
	 * @return void
	 * @throws 
	 */
	@Override
	public void initialize(final E proxy) throws DataAccessException {
		
		getHibernateTemplate().initialize(proxy);
	
	}
	
	/** 
	 * @Title: enableFilter 
	 * @Description: 当设置Filter时可以指定过滤器名启动该过滤器，默认不开启
	 * @param @param filterName
	 * @param @return
	 * @param @throws IllegalStateException
	 * @return Filter
	 * @throws 
	 */
	@Override
	public Filter enableFilter(String filterName) throws IllegalStateException {
		
		return getHibernateTemplate().enableFilter(filterName);
	
	}
	
	@Override
	public void disableFilter(String filterName) throws IllegalStateException {
		
		Session session = getSessionFactory().getCurrentSession();
		Filter filter = session.getEnabledFilter(filterName);
		if (filter != null) {
			session.disableFilter(filterName);
		}
	
	}

	
	
	
	//-------------------------------------------------------------------------
	// Convenience methods for storing individual objects
	//-------------------------------------------------------------------------

	@Override
	public void lock(final E entity, final LockMode lockMode) throws DataAccessException, HibernateException {
		
		getHibernateTemplate().lock(entity, lockMode);
	
	}
	
	@Override
	public Serializable save(final E entity) throws DataAccessException, HibernateException {
		
		return getHibernateTemplate().save(entity);
	
	}
	
	@Override
	public void update(final E entity)throws DataAccessException {
		
		getHibernateTemplate().update(entity, null);
	
	}

	@Override
	public void updateWithLock(final E entity, final LockMode lockMode) throws DataAccessException, HibernateException {
		
		getHibernateTemplate().update(entity, lockMode);
		flush();
	
	}
	
	@Override
	public void saveOrUpdate(final E entity) throws DataAccessException, HibernateException {
		
		getHibernateTemplate().saveOrUpdate(entity);
	
	}
	
	@Override
	public void replicate(final E entity, final ReplicationMode replicationMode)
			throws DataAccessException, HibernateException {
		
		getHibernateTemplate().replicate(entity, replicationMode);
	
	}
	
	@Override
	public void persist(final E entity) throws DataAccessException, HibernateException {
		
		getHibernateTemplate().persist(null, entity);
	
	}
	
	@Override
	public E merge(final E entity) throws DataAccessException, HibernateException {
		
		return getHibernateTemplate().merge(null, entity);
	
	}
	
	@Override
	public void delete(final E entity) throws DataAccessException {
		
		getHibernateTemplate().delete(entity, null);
	
	}
	
	@Override
	public void deleteWithLock(final E entity, final LockMode lockMode) throws DataAccessException {
	
		getHibernateTemplate().delete(entity, lockMode);
		flush();
	
	}
	
	@Override
	public void deleteAll(final Collection<E> entities) throws DataAccessException, HibernateException {
	
		getHibernateTemplate().deleteAll(entities);
	
	}
	
	@Override
	public void flush() throws DataAccessException, HibernateException {
	
		getHibernateTemplate().flush();
	
	}
	
	@Override
	public void clear() throws DataAccessException {
	
		getHibernateTemplate().clear();
	
	}
	
	
	
	
	
	
	
	//-------------------------------------------------------------------------
	// Convenience finder methods for HQL strings
	//-------------------------------------------------------------------------

	@Override
	public List<?> find(final String queryString, final Object... values)
			throws DataAccessException ,HibernateException {
	
		return getHibernateTemplate().find(queryString, values);
	
	}
	
	@Override
	public List<?> findByNamedParam(String queryString, String paramName, Object value)
			throws DataAccessException {
	
		return getHibernateTemplate().findByNamedParam(
				queryString, new String[]{ paramName }, new Object[]{ value }
		);
	
	}

	@Override
	public List<?> findByNamedParams(final String queryString, final String[] paramNames, final Object[] values)
			throws DataAccessException, HibernateException {
		
		return getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
	
	}

	@Override
	public List<?> findByNamedParams(final String queryString, final Map<String, Object> paramNameAndValues)
			throws DataAccessException, HibernateException {
	
		String[] paramNames = new String[paramNameAndValues.size()];
		Object[] values = new Object[paramNameAndValues.size()];
		Iterator<String> iterator = paramNameAndValues.keySet().iterator();
		int index = 0;
		while( iterator.hasNext() ){
			String key = iterator.next();
			paramNames[index] = key;
			values[index++] = paramNameAndValues.get(key);
		}
		
		return findByNamedParams(queryString, paramNames, values);
	}
			
	@Override
	public List<?> findByValueBean(final String queryString, final Object valueBean)
			throws DataAccessException, HibernateException {

		return getHibernateTemplate().findByValueBean(queryString, valueBean);
	
	}


	
	
	//-------------------------------------------------------------------------
	// Convenience finder methods for named queries
	//-------------------------------------------------------------------------

	@Override
	public List<?> findByNamedQuery(final String queryName, final Object... values)
			throws DataAccessException, HibernateException {
		
		return getHibernateTemplate().findByNamedQuery(queryName, values);
	
	}

	@Override
	public List<?> findByNamedQueryAndNamedParam(String queryName, String paramName, Object value)
			throws DataAccessException, IllegalArgumentException, HibernateException {
		
		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramName, value);
	
	}
	
	@Override
	public List<?> findByNamedQueryAndNamedParam(
			final String queryName, final String[] paramNames, final Object[] values)
			throws DataAccessException, IllegalArgumentException, HibernateException {
				
		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames, values);
	
	}

	@Override
	public List<?> findByNamedQueryAndValueBean(final String queryName, final Object valueBean)
			throws DataAccessException, HibernateException {
		
		return getHibernateTemplate().findByNamedQueryAndValueBean(queryName, valueBean);
	
	}


	
	
	
	//-------------------------------------------------------------------------
	// Convenience finder methods for detached criteria
	//-------------------------------------------------------------------------

	@Override
	public DetachedCriteria createDetachedCriteria(){
		
		return DetachedCriteria.forClass(this.entityClass);
		
	}
	
	@Override
	public Criteria createCriteria(){
		
		return currentSession().createCriteria(entityClass);
	
	}
	
	@Override
	public Long getRowCount(final Criterion... criterions){
		
		Criteria criteria = createCriteria();
		criteria.setProjection(Projections.rowCount());
		if (criterions != null) {
			for (int i = 0; i < criterions.length; i++) {
				criteria.add(criterions[i]);
			}
		}
		Object rowCount = criteria.uniqueResult();
		return  rowCount != null ? (Long) rowCount : 0;
		
	}
	
	@Override
	public List<?> findByCriteria(DetachedCriteria criteria)
			throws DataAccessException, HibernateException {
				
		return getHibernateTemplate().findByCriteria(criteria, -1, -1);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> findForPaging(final int firstResult, final int maxResults)
			throws DataAccessException, HibernateException{
		DetachedCriteria criteria = createDetachedCriteria();
		return (List<E>) findByCriteriaForPaging(criteria, firstResult, maxResults);
	}
	
	@Override
	public List<?> findByCriteriaForPaging(final DetachedCriteria criteria, final int firstResult, final int maxResults)
			throws DataAccessException, HibernateException {
		return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	
	} 
	
	@Override
	public List<?> findByExample(E exampleEntity) throws DataAccessException, HibernateException {
		
		return getHibernateTemplate().findByExample(exampleEntity, -1, -1);
	
	}

	@Override
	public List<?> findByExample(E exampleEntity, int firstResult, int maxResults)
			throws DataAccessException, HibernateException {
		
		return getHibernateTemplate().findByExample(exampleEntity, firstResult, maxResults);
	
	}
	
	
	
	//-------------------------------------------------------------------------
	// Convenience query methods for iteration and bulk updates/deletes
	//-------------------------------------------------------------------------

	@Override
	public Iterator<?> iterate(final String queryString, final Object... values)
			throws DataAccessException, HibernateException {
				
		return getHibernateTemplate().iterate(queryString, values);
				
	}

	@Override
	public void closeIterator(Iterator<?> it) throws DataAccessException {
		
		getHibernateTemplate().closeIterator(it);
		
	}

	@Override
	public int bulkUpdate(final String queryString, final Object... values)
			throws DataAccessException, HibernateException {
		
		return getHibernateTemplate().bulkUpdate(queryString, values);

	}

	@Override
	public void insertInBatch(Collection<E> entitys) {
		// TODO
		for (E e : entitys) {
			save(e);
		}
	}

	@Override
	public void updateInBatch(Collection<E> entitys) {
		// TODO
		for (E e : entitys) {
			update(e);
		}
	}
	
	@Override
	public void updateByMapInBatch(IReflectSetter<E> reflectSetter, Map<PK, Map<String, Object>> updateDataMap){
		Iterator<PK> iteratorPKs = updateDataMap.keySet().iterator();
		while ( iteratorPKs.hasNext() ) {
			PK pk = iteratorPKs.next();
			E entity = load(pk);
			Map<String, Object> fields = updateDataMap.get(pk);
			Iterator<String> iteratorFields = fields.keySet().iterator();
			while ( iteratorFields.hasNext() ) {
				String fieldName = iteratorFields.next();
				Object fieldValue = fields.get(fieldName);
				reflectSetter.setFieldValue(entity, fieldName, fieldValue);
			}
			update(entity);
		}
	}
	
	@Override
	public void deleteInBatch(Collection<E> entitys) {
		// TODO
		for (E e : entitys) {
			delete(e);
		}
	}
	
	@Override
	public void deleteByID(PK id){
		E entity = load(id);
		delete(entity);
	}

	@Override
	public void deleteByIDInBatch(Collection<PK> ids){
		for (PK pk : ids) {
			E entity = load(pk);
			delete(entity);
		}
	}
	
	
	//-------------------------------------------------------------------------
	// Helper methods used by the operations above
	//-------------------------------------------------------------------------

}
