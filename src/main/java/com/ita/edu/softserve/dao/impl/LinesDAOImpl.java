package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ita.edu.softserve.dao.AbstractDAOClass;
import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author iryna
 * 
 */
public class LinesDAOImpl extends AbstractDAOClass implements LinesDAO {

	@PersistenceContext(name = PERSISTENCE_UNIT_NAME)
	private static EntityManager entityManager;

	@Override
	public Lines findByName(String lineName) {
		Query query = entityManager.createNamedQuery(Lines.FIND_BY_NAME)
				.setParameter(1, lineName);
		return (Lines) find(query);
	}

	@Override
	public void save(Lines line) {
		entityManager.persist(line);
	}

	@Override
	public void remove(Lines line) {
		entityManager.remove(line);
	}

	@Override
	public Lines update(Lines line) {
		return entityManager.merge(line);
	}

	/**
	 * Return Lines that includes two stations in certain order
	 * 
	 * @param station1
	 *            - first station, departure
	 * @param station2
	 *            - second station, arrival
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	public static List<Lines> getLinesTwoStationsCertainOrder(
			Stations station1, Stations station2) {
		Query query = entityManager
				.createNamedQuery(Lines.GET_LINES_TWO_STATIONS_CERTAIN_ORDER)
				.setParameter("station1", station1)
				.setParameter("station2", station2);
		@SuppressWarnings("unchecked")
		List<Lines> listOfLines = query.getResultList();

		return listOfLines;
	}

}
