package com.flight.trs.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.flight.trs.dao.IDeleteNotPhysicallyDAO;
import com.flight.trs.dao.IVoyageDAO;
import com.flight.trs.model.entity.Customer;
import com.flight.trs.model.entity.Order;
import com.flight.trs.model.entity.OrderVoyage;
import com.flight.trs.model.entity.Voyage;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月20日 上午12:57:36
 * @version V1.0
 */
@Repository
public class VoyageDAO extends BaseDAO<Voyage, BigDecimal> 
	implements IVoyageDAO, IDeleteNotPhysicallyDAO<Voyage, BigDecimal> {

}
