package com.flight.trs.model;

/**   
 * 
 * @Description: 指示实现该接口的实体删除时为非物理删除
 * @author le
 * @date 2016年1月28日 下午10:30:56
 * @version V1.0
 */
public interface IDeleteNotPhysically {
	
	/** 
	 * @Title: delete 
	 * @Description: 非物理删除，指示isDeleted属性为'y'
	 * @param 
	 * @return void
	 * @throws 
	 */
	public void deleteNotPhysically();
	
}
