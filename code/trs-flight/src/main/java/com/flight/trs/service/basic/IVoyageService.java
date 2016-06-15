package com.flight.trs.service.basic;

import com.flight.trs.model.entity.Voyage;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 下午12:10:08
 * @version V1.0
 */
public interface IVoyageService {
	public Voyage findSingleByExample(Voyage voyage);
	public void add(Voyage voyage);
}
