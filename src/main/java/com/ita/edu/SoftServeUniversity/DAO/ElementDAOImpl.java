package com.ita.edu.SoftServeUniversity.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ElementDAOImpl<E> implements ElementDAO <E>{
    private Class<E> elementClass;

    public ElementDAOImpl(Class<E> elementClass) {
        this.elementClass = elementClass;
    }

    public void addElement(E element) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
            session = HibernateUtil.getSessionFactory().openSession(); 
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
            session = HibernateUtil.getSessionFactory().openSession(); 
            
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
            session = HibernateUtil.getSessionFactory().openSession(); 
            
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
            session = HibernateUtil.getSessionFactory().openSession(); 
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
