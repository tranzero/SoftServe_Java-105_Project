package com.ita.edu.softserve.old_dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ita.edu.softserve.db_util.HibernateUtil;

/**
 * 
 * @author iryna
 *
 * @param <E>
 */
public class ElementDAOImpl<E> implements ElementDAO<E> {
	
	private Class<E> elementClass;

	private SessionFactory factory; // factory

	public ElementDAOImpl(Class<E> elementClass) {

		factory = HibernateUtil.getSessionFactory(); // here
		this.elementClass = elementClass;
	}

	public void addElement(E element) {
		Session session = null;

		try {
			session = factory.openSession(); // getCurrentSession
			Transaction transaction = session.beginTransaction();
			session.save(element);
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public void updateElement(E element) {
		Session session = null;

		try {
			session = factory.openSession(); // getCurrentSession
			Transaction transaction = session.beginTransaction();
			session.update(element);
			transaction.commit();

		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public E getElementByID(Long elementId) {
		Session session = null;
		E element = null;

		try {
			session = factory.openSession(); // getCurrentSession		
			element = (E) session.get(elementClass, elementId);
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return element;
	}

	public List<E> getAllElements() {
		Session session = null;
		List<E> elements = new ArrayList<E>();
		try {
			session = factory.openSession(); // getCurrentSession
			elements = session.createCriteria(elementClass).list();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return elements;
	}

	public void deleteElement(E element) {
		Session session = null;
		try {
			session = factory.openSession(); // getCurrentSession
			Transaction transaction = session.beginTransaction();
			session.delete(element);
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

}