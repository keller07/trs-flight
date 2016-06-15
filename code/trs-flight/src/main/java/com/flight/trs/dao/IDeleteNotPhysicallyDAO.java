package com.flight.trs.dao;

import java.io.Serializable;
import java.util.Collection;

import com.flight.trs.model.IDeleteNotPhysically;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月28日 下午11:19:03
 * @version V1.0
 */
public interface IDeleteNotPhysicallyDAO <E extends IDeleteNotPhysically & Serializable, PK extends Serializable> 
	extends IBaseDAO<E, PK>{

	default public void deleteNotPhysically(E entity){
		if ( entity!=null ) {
			entity.deleteNotPhysically();
			update(entity);
		}
		else {
			//ignore
		}
	}
	
	default public void deleteNotPhysicallyInBatch(Collection<E> entitys) {
		// TODO
		for (E e : entitys) {
			if (e != null) {
				e.deleteNotPhysically();
			}
			else {
				//ignore
			}
		}
		updateInBatch(entitys);
	}
	
	default public void deleteByIDNotPhysically(PK id){
		E entity = load(id);
		deleteNotPhysically(entity);
	}
	
	default public void deleteByIDNotPhysicallyInBatch(Collection<PK> ids){
		for (PK id : ids) {
			E entity = load(id);
			deleteNotPhysically(entity);
		}
	}
	
}
