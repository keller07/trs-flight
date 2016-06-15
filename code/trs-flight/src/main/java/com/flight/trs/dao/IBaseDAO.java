package com.flight.trs.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.ReplicationMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import com.flight.trs.dao.callable.IReflectSetter;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月22日 下午5:21:15
 * @version V1.0
 */
public interface IBaseDAO  <E extends Serializable,PK extends Serializable> {

	//-------------------------------------------------------------------------
	// Convenience methods for loading individual objects
	//-------------------------------------------------------------------------

	public E get(final PK id) throws DataAccessException;
	
	public E getWithLock(final PK id, final LockMode lockMode) throws DataAccessException, HibernateException;
	
	public E load(final PK id) throws DataAccessException;
	
	public E loadWithLock(final PK id, final LockMode lockMode) throws DataAccessException, HibernateException;
	
	public List<E> loadAll() throws DataAccessException, HibernateException;
	
	//public List<E> loadAllWithLock(final LockMode lockMode) throws DataAccessException;

	public void refresh(final E entity) throws DataAccessException;

	public void refreshWithLock(final E entity, final LockMode lockMode) throws DataAccessException, HibernateException;

	public boolean contains(final E entity) throws DataAccessException;
	
	public void evict(final E entity) throws DataAccessException, HibernateException;
	
	
	/** 
	 * @Title: initialize 
	 * @Description: 初始化实体代理，
	 * 		用于防止延迟加载时 no session or session is closed 的问题
	 * @param @param entity
	 * @param @throws DataAccessException
	 * @return void
	 * @throws 
	 */
	public void initialize(final E entity) throws DataAccessException;
	
	
	/** 
	 * @Title: enableFilter 
	 * @Description: 当设置Filter时可以指定过滤器名启动该过滤器，默认不开启
	 * @param @param filterName
	 * @param @return
	 * @param @throws IllegalStateException
	 * @return Filter
	 * @throws 
	 */
	public Filter enableFilter(String filterName) throws IllegalStateException;
	
	public void disableFilter(String filterName) throws IllegalStateException;

	
	
	
	
	
	
	
	//-------------------------------------------------------------------------
	// Convenience methods for storing individual objects
	//-------------------------------------------------------------------------

	public void lock(final E entity, final LockMode lockMode) throws DataAccessException, HibernateException;
	
	public Serializable save(final E entity) throws DataAccessException, HibernateException; 

	public void update(final E entity)throws DataAccessException;

	public void updateWithLock(final E entity, final LockMode lockMode) throws DataAccessException, HibernateException;
	
	public void saveOrUpdate(final E entity) throws DataAccessException, HibernateException;
	
	public void replicate(final E entity, final ReplicationMode replicationMode)
			throws DataAccessException, HibernateException;
	
	public void persist(final E entity) throws DataAccessException, HibernateException;
	
	public E merge(final E entity) throws DataAccessException, HibernateException;
	
	public void delete(final E entity) throws DataAccessException;
	
	public void deleteWithLock(final E entity, final LockMode lockMode) throws DataAccessException;
	
	public void deleteAll(final Collection<E> entities) throws DataAccessException, HibernateException;
	
	public void flush() throws DataAccessException, HibernateException;
	
	public void clear() throws DataAccessException;
	
	
	
	
	
	
	
	//-------------------------------------------------------------------------
	// Convenience finder methods for HQL strings
	//-------------------------------------------------------------------------

	public List<?> find(final String queryString, final Object... values)
			throws DataAccessException ,HibernateException; 

	public List<?> findByNamedParam(String queryString, String paramName, Object value)
			throws DataAccessException;

	public List<?> findByNamedParams(final String queryString, final String[] paramNames, final Object[] values)
			throws DataAccessException, HibernateException;

	public List<?> findByNamedParams(final String queryString, final Map<String, Object> paramNameAndValues)
			throws DataAccessException, HibernateException;
			
	public List<?> findByValueBean(final String queryString, final Object valueBean)
			throws DataAccessException, HibernateException;


	
	
	//-------------------------------------------------------------------------
	// Convenience finder methods for named queries
	//-------------------------------------------------------------------------

	public List<?> findByNamedQuery(final String queryName, final Object... values)
			throws DataAccessException, HibernateException;

	
	public List<?> findByNamedQueryAndNamedParam(String queryName, String paramName, Object value)
			throws DataAccessException, IllegalArgumentException, HibernateException;

	
	public List<?> findByNamedQueryAndNamedParam(
			final String queryName, final String[] paramNames, final Object[] values)
			throws DataAccessException, IllegalArgumentException, HibernateException;

	public List<?> findByNamedQueryAndValueBean(final String queryName, final Object valueBean)
			throws DataAccessException, HibernateException;


	
	
	
	//-------------------------------------------------------------------------
	// Convenience finder methods for detached criteria
	//-------------------------------------------------------------------------

	public DetachedCriteria createDetachedCriteria();
	
	public Criteria createCriteria();
	
	public Long getRowCount(final Criterion... criterions);
	
	public List<?> findByCriteria(DetachedCriteria criteria)
			throws DataAccessException, HibernateException;
	
	public List<E> findForPaging(final int firstResult, final int maxResults)
			throws DataAccessException, HibernateException;
	
	public List<?> findByCriteriaForPaging(final DetachedCriteria criteria, final int firstResult, final int maxResults)
			throws DataAccessException, HibernateException; 
	
	public List<?> findByExample(E exampleEntity) throws DataAccessException, HibernateException;

	public List<?> findByExample(E exampleEntity, int firstResult, int maxResults)
			throws DataAccessException, HibernateException;

	
	
	
	
	//-------------------------------------------------------------------------
	// Convenience query methods for iteration and bulk updates/deletes
	//-------------------------------------------------------------------------

	public Iterator<?> iterate(final String queryString, final Object... values)
			throws DataAccessException, HibernateException;

	public void closeIterator(Iterator<?> it) throws DataAccessException;

	public int bulkUpdate(final String queryString, final Object... values)
			throws DataAccessException, HibernateException;

	public void insertInBatch(Collection<E> entitys);
	
	public void updateInBatch(Collection<E> entitys);
	
	void updateByMapInBatch(IReflectSetter<E> reflectSetter, Map<PK, Map<String, Object>> updateDataMap);
	
	public void deleteInBatch(Collection<E> entitys);

	void deleteByID(PK id);
	
	void deleteByIDInBatch(Collection<PK> ids);

	

	
	//-------------------------------------------------------------------------
	// Helper methods used by the operations above
	//-------------------------------------------------------------------------
	
}
