package com.flight.trs.dao.callable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.flight.trs.datasource.UsingDataSource;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月17日 下午2:40:18
 * @version V1.0
 */
@Repository
public class FlightQueryCall extends FunctionCall {
	
//	@UsingDataSource(dataSourceName = UsingDataSource.DS2PDBGDS)
//	public List<FlightBasic> queryFlightIgnoreCarrier(String depAirportCode
//			, String arrAirportCode, String depDate){
//		return queryFlight("--", depAirportCode, arrAirportCode, depDate);
//	}
	
//	@UsingDataSource(dataSourceName = UsingDataSource.DS2PDBGDS)
//	public List<FlightBasic> queryFlight(String carrierCode, String depAirportCode
//			, String arrAirportCode, String depDate){
//		
//		String funcName = "fuc_query_flight";
//		
//		ResultSet rs = callReturnCURSOR(funcName, carrierCode, depAirportCode
//				, arrAirportCode, depDate);
//		
//		List<FlightBasic> flightList = null;
//		
//		// 遍历结果集并进行转化
//		try {
//			
//			flightList = new ArrayList<FlightBasic>();
//			
//			while(rs.next()){
//				
//				FlightBasic flightBasic = new FlightBasic();
//				flightBasic.setId( rs.getInt(1) );
//				flightBasic.setCarrier_code( rs.getString(2) );
//				flightBasic.setFlight_no( rs.getString(3) );
//				flightBasic.setDep_airport_code( rs.getString(4) );
//				flightBasic.setDep_terminal( rs.getString(5).charAt(0) );
//				flightBasic.setDep_time( rs.getString(6));
//				flightBasic.setAc_code( rs.getString(7) );
//				flightBasic.setStops( rs.getString(8) );
//				flightBasic.setMeal_service( rs.getString(9).charAt(0) );
//				flightBasic.setArr_date_offset( rs.getString(10).charAt(0) );
//				flightBasic.setArr_time( rs.getString(11) );
//				flightBasic.setArr_airport_code( rs.getString(12) );
//				flightBasic.setArr_terminal( rs.getString(13).charAt(0) );
//				flightBasic.setDuration( rs.getString(14) );
//				flightBasic.setFlight_short( rs.getFloat(15) );
//				flightBasic.setDep_date( rs.getString(16) );
//				flightBasic.setTicket_fee( rs.getShort(17) );
//				flightBasic.setAirport_tax( rs.getFloat(18));
//				flightBasic.setBaf( rs.getFloat(19) );
//				flightBasic.setCl_code( rs.getString(20).charAt(0) );
//				flightBasic.setCount_of_spare_seats( rs.getInt(21) );
//				
//				flightList.add(flightBasic);
//				
//			}
//			
//		} catch (SQLException e) {
//			// TODO
//			e.printStackTrace();
//		}
//		
//		return flightList;
//	}
	
}
