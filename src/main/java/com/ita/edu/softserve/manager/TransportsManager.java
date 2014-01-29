package com.ita.edu.softserve.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Transports;

/**
 * @author Roman
 */
public interface TransportsManager extends BaseManager {

	/**
	 * Finds transports by ID.
	 */
	Transports findTransportsById(int id);

	/**
	 * Saves transports.
	 */
	void saveTransports(Transports... entities);

	/**
	 * Removes transports.
	 */
	void removeTransports(Transports... entities);

	/**
	 * Updates transports.
	 */
	List<Transports> updateTransports(Transports... entities);

	/**
	 * Gets all transports.
	 */
	List<Transports> getAllTransports();
}
