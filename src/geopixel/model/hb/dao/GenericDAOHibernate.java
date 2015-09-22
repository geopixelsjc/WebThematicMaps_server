package geopixel.model.hb.dao;

import geopixel.server.helper.HibernateUtil;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * @since 
 */
public abstract class GenericDAOHibernate<T> implements GenericDAO<T> {

	private Class<T> entity;
	private Object object;
	private Transaction transaction;
	private Session session;

	public GenericDAOHibernate(Class<T> entity) {
		this.entity = entity;
		this.session = this.getSession();
	}

	abstract Session getSession();

	private Transaction getTransaction() {
		return this.session.beginTransaction();
	}

	private void rollBackTransaction() {
		HibernateUtil.rollbackTransaction(this.transaction);
		HibernateUtil.closeSession(this.session);
	}

	@Override
	public void saveOrUpdate(T entity) throws Exception {
		try {
			this.transaction = this.getTransaction();
			this.session.saveOrUpdate(entity);
			this.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.rollBackTransaction();
			throw new Exception("Error in Method: public void save(T entidade).", e);
		} finally {
			HibernateUtil.closeSession(this.session);
		}
	}

	@Override
	public void delete(T entity) throws Exception {
		try {
			this.transaction = this.getTransaction();
			this.session.delete(entity);
			this.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.rollBackTransaction();
			throw new Exception("Error in Method: public void delete(T entidade).", e);
		} finally {
			HibernateUtil.closeSession(this.session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getByID(Object id) throws Exception {
		try {
			this.object = session.get(this.entity, (Serializable) id);
			HibernateUtil.closeSession(this.session);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in Method: public T getByID(Object id)", e);
		} finally {
			HibernateUtil.closeSession(this.session);
		}
		return (T) this.object;
	}
}
