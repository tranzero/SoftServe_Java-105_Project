package com.ita.edu.softserve.old_dao;



/**
 * 
 * @author iryna
 * 
 */
public class DaoFactory {

	private UsersDAO usersDAO = null;
	private StopsDAO stopsDAO = null;
	private StationsDAO stationsDAO = null;
	private StationsOnLineDAO stationsOnLineDAO = null;
	private RoutesDAO routesDAO = null;
	private LinesDAO linesDAO = null;

	// object of DaoFactory
	private static DaoFactory instance = null;

	/**
	 *  private constructor
	 */
	private DaoFactory() {
		usersDAO = new UsersDAO();
		stopsDAO = new StopsDAO();
		stationsDAO = new StationsDAO();
		stationsOnLineDAO = new StationsOnLineDAO();
		routesDAO = new RoutesDAO();
		linesDAO = new LinesDAO();

	}

	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			instance = new DaoFactory();
		}
		return instance;
	}

	public UsersDAO getUsersDAO() {
		return usersDAO;
	}

	public StopsDAO getStopsDAO() {
		return stopsDAO;
	}

	public StationsDAO getStationsDAO() {
		return stationsDAO;
	}

	public StationsOnLineDAO getStationsOnLineDAO() {
		return stationsOnLineDAO;
	}

	public RoutesDAO getRoutesDAO() {
		return routesDAO;
	}

	public LinesDAO getLinesDAO() {
		return linesDAO;
	}

}
