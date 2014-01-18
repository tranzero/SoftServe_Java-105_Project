
package com.ita.edu.softserve.old_dao;

import java.util.List;

/**
 * @author tranzero
 * @param <E>
 *
 */
public interface ElementDAO <E> {
	
	public void addElement(E element);

    public void updateElement(E element);

    public E getElementByID(Long elementId);

    public List<E> getAllElements();

    public void deleteElement(E element);

}
