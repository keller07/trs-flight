package com.flight.trs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flight.trs.dao.ISegmentDAO;
import com.flight.trs.model.entity.Segment;
import com.flight.trs.model.entity.SegmentId;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月13日 下午10:27:18
 * @version V1.0
 */
@Repository
public class SegmentDAO extends BaseDAO<Segment, SegmentId> implements ISegmentDAO {

	private static final String queryParamNames[] = new String[]{
			"depAirportCode"
			,"arrAirportCode"
			,"depDate"
			,"carrierCode"
			,"nonStop"
			,"earliest"
			,"latest"
	};
	//private static final String querySql = "FROM Segment s WHERE s.id.depAirportCode = :depAirportCode AND s.id.arrAirportCode = :arrAirportCode AND (TO_NUMBER(:depDate, '99999999') BETWEEN TO_NUMBER(SUBSTR(s.validity, 1, 8), '99999999') AND TO_NUMBER(SUBSTR(s.validity, 9, 8), '99999999') AND (INSTR(s.daysOfOperation, TO_Character(TO_DATE(:depDate, 'yyyymmdd'), 'd'), 1, 1) != 0)) AND (:carrierCode = '--' OR s.id.carrierCode = :carrierCode) )";
	//private static final String querySql = "FROM Segment s WHERE s.id.depAirportCode = ? AND s.id.arrAirportCode = ? AND (TO_NUMBER(?, '99999999') BETWEEN TO_NUMBER(SUBSTR(s.validity, 1, 8), '99999999') AND TO_NUMBER(SUBSTR(s.validity, 9, 8), '99999999') AND (INSTR(s.daysOfOperation, TO_Character(TO_DATE(?, 'yyyymmdd'), 'd'), 1, 1) != 0)) AND (? = '--' OR s.id.carrierCode = ?) AND ( ? = 'n' OR ( ? = 'y' and s.traAirport is null)) AND (? = '----' OR (TO_NUMBER(s.id.depTime, '9999') BETWEEN TO_NUMBER(?, '9999') AND TO_NUMBER(?, '9999')))";
	//private static final String querySql = "FROM Segment s WHERE s.id.depAirportCode = ? AND s.id.arrAirportCode = ? AND (to_number(?, '99999999') BETWEEN to_number(substr(s.validity, 1, 8), '99999999') AND to_number(substr(s.validity, 9, 8), '99999999') AND (instr(s.daysOfOperation, to_char(to_date(?, 'yyyymmdd'), 'd'), 1, 1) != 0)) AND (? = '--' OR s.id.carrierCode = ?) AND ( ? = 'n' OR ( ? = 'y' and s.traAirport is null)) AND (? = '----' OR (to_number(s.id.depTime, '9999') BETWEEN to_number(?, '9999') AND to_number(?, '9999')))";
	//private static final String querySql = "FROM Segment s WHERE s.id.depAirportCode = ? AND s.id.arrAirportCode = ? AND ((to_number(?, '99999999') BETWEEN to_number(substr(s.validity, 1, 8), '99999999') AND to_number(substr(s.validity, 9, 8), '99999999')) AND (instr(s.daysOfOperation, to_char(to_date(?, 'yyyymmdd'), 'd'), 1, 1) != 0)) AND ('/-/-' = ? OR s.id.carrierCode = ? ) ";
	
	private static final String querySql = "FROM Segment s WHERE s.id.depAirportCode = :depAirportCode AND s.id.arrAirportCode = :arrAirportCode AND ((TO_NUMBER(:depDate, '99999999') BETWEEN TO_NUMBER(SUBSTR(s.validity, 1, 8), '99999999') AND TO_NUMBER(SUBSTR(s.validity, 9, 8), '99999999')) AND (INSTR(s.daysOfOperation, TO_CHAR(TO_DATE(:depDate, 'yyyymmdd'), 'd'), 1, 1) != 0)) AND (:carrierCode = '/-/-' OR s.id.carrierCode = :carrierCode) AND ( :nonStop = 'n' OR ( :nonStop = 'y' and s.traAirport is null)) AND (:earliest = '/-/-/-/-' OR (TO_NUMBER(s.id.depTime, '9999') BETWEEN TO_NUMBER(:earliest, '9999') AND TO_NUMBER(:latest, '9999')))";
	//private static final String querySql = "SELECT * FROM trs_flight_segment WHERE dep_airport_code = :depAirportCode AND arr_airport_code = :arrAirportCode AND (TO_NUMBER(:depDate, '99999999') BETWEEN TO_NUMBER(SUBSTR(validity, 1, 8), '99999999') AND TO_NUMBER(SUBSTR(validity, 9, 8), '99999999') AND (INSTR(days_of_operation, TO_Character(TO_DATE(:depDate, 'yyyymmdd'), 'd'), 1, 1) != 0)) AND (:carrierCode = '--' OR carrier_code = :carrierCode) AND ((:nonStop = 'y' and tra_airport_code is null) OR :nonStop = 'n') AND (:earliest = '----' OR (TO_NUMBER(dep_time, '9999') BETWEEN TO_NUMBER(:earliest, '9999') AND TO_NUMBER(:latest, '9999')))";
	//private static final String querySql = "SELECT * FROM trs_flight_segment WHERE dep_airport_code = ? AND arr_airport_code = ? AND (TO_NUMBER(?, '99999999') BETWEEN TO_NUMBER(SUBSTR(validity, 1, 8), '99999999') AND TO_NUMBER(SUBSTR(validity, 9, 8), '99999999') AND (INSTR(days_of_operation, TO_Character(TO_DATE(?, 'yyyymmdd'), 'd'), 1, 1) != 0)) AND (? = '--' OR carrier_code = ?) AND ((? = 'y' and tra_airport_code is null) OR ? = 'n') AND (? = '----' OR (TO_NUMBER(dep_time, '9999') BETWEEN TO_NUMBER(?, '9999') AND TO_NUMBER(?, '9999')))";

	//	static{
//		StringBuilder sb = new StringBuilder();
//		sb.append("SELECT * FROM trs_flight_segment")
//			.append(" WHERE dep_airport_code = :depAirportCode")
//			.append(" AND arr_airport_code = :arrAirportCode")
//			.append(" AND (")
//				.append(" TO_NUMBER(:depDate, '99999999') BETWEEN TO_NUMBER(SUBSTR(validity, 1, 8), '99999999') AND TO_NUMBER(SUBSTR(validity, 9, 8), '99999999')")
//				.append(" AND (INSTR(days_of_operation, TO_Character(TO_DATE(:depDate, 'yyyymmdd'), 'd'), 1, 1) != 0)")
//			.append( ")")
//			.append(" AND (:carrierCode = '--' OR carrier_code = :carrierCode)")
//			.append(" AND ((:nonStop = 'y' and tra_airport_code is null) OR :nonStop = 'n')")
//			.append(" AND (")
//				.append(" :earliest = '----'")
//				.append(" OR (TO_NUMBER(dep_time, '9999') BETWEEN TO_NUMBER(:earliest, '9999') AND TO_NUMBER(:latest, '9999'))")
//			.append( ")");
//		querySql = sb.toString();
//	}
	
	private static final String carrierCodeDefault = "/-/-";
	private static final char nonStopDefault = 'n';
	private static final String earliestDefault = "/-/-/-/-";
	private static final String latestDefault = "/-/-/-/-";
	
	@Override
	public List<Segment> get() {
		// TODO
		return loadAll();
	}

	@Override
	public Segment get(SegmentId id) {
		// TODO
		return super.get(id);
	}

	@Override
	public List<Segment> get(String depAirportCode, String arrAirportCode, String depDate) {
		return get(depAirportCode, arrAirportCode, depDate, carrierCodeDefault, nonStopDefault, earliestDefault, latestDefault);
	}

	@Override
	public List<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode) {
		return get(depAirportCode, arrAirportCode, depDate, carrierCode, nonStopDefault, earliestDefault, latestDefault);
	}

	@Override
	public List<Segment> get(String depAirportCode, String arrAirportCode, String depDate, char nonStop) {
		return get(depAirportCode, arrAirportCode, depDate, carrierCodeDefault, nonStop, earliestDefault, latestDefault);
	}

	@Override
	public List<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String earliest,
			String latest) {
		return get(depAirportCode, arrAirportCode, depDate, carrierCodeDefault, nonStopDefault, earliest, latest);
	}

	@Override
	public List<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode,
			char nonStop) {
		return get(depAirportCode, arrAirportCode, depDate, carrierCode, nonStop, earliestDefault, latestDefault);
	}

	@Override
	public List<Segment> get(String depAirportCode, String arrAirportCode, String depDate, char nonStop,
			String earliest, String latest) {
		return get(depAirportCode, arrAirportCode, depDate, carrierCodeDefault, nonStop, earliest, latest);
	}

	@Override
	public List<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode,
			String earliest, String latest) {
		return get(depAirportCode, arrAirportCode, depDate, carrierCode, nonStopDefault, earliest, latest);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode,
			char nonStop, String earliest, String latest) {
		Object[] params = new Object[]{
				depAirportCode
				,arrAirportCode
				,depDate
				,carrierCode
				,String.valueOf(nonStop)
				,earliest
				,latest
		};
//		Object[] params = new Object[]{
//			depAirportCode
//			,arrAirportCode
//			,depDate
//			,depDate
//			,carrierCode
//			,carrierCode
//			,String.valueOf(nonStop)
//			,String.valueOf(nonStop)
//			,earliest
//			,earliest
//			,latest
//		};
		//		return (List<Segment>) find(querySql, params);
		return (List<Segment>) findByNamedParams(querySql, queryParamNames, params);
//		return (List<Segment>) findByParamsWithSQL(querySql, params);
	}

	@Override
	public void post(Segment segment) {
		save(segment);
	}

	@Override
	public void post(Iterable<Segment> segments) {
		for (Segment segment : segments) {
			post(segment);
		}
	}

	@Override
	public void put(Segment segment) {
		super.update(segment);
	}

	@Override
	public void put(Iterable<Segment> segments) {
		for (Segment segment : segments) {
			put(segment);
		}
	}

	@Override
	public void delete(SegmentId id) {
		super.deleteByID(id);
	}

	@Override
	public void delete(Segment segment) {
		super.delete(segment);
	}

	@Override
	public void delete(Iterable<Segment> segments) {
		for (Segment segment : segments) {
			delete(segment);
		}
	}

}
