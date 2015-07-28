package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import java.io.Serializable;

import geopixel.model.hb.dto.AppUsarioxprefil;
import geopixel.model.hb.dto.AppUsarioxprefilId;
import geopixel.server.helper.HibernateUtil;

import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Home object for domain model class AppUsarioxprefil.
 * @see .AppUsarioxprefil
 * @author Hibernate Tools
 */
@Stateless
public class AppUsarioxprefilDAO extends GenericDAOHibernate<AppUsarioxprefil> {

	public AppUsarioxprefilDAO(Class<AppUsarioxprefil> entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	private static final Log log = LogFactory.getLog(AppUsarioxprefilDAO.class);

	private Session session;

	public void persist(AppUsarioxprefil transientInstance) {
		log.debug("persisting AppUsarioxprefil instance");
		Transaction transaction = null;
		session = getSession();
		try {
			
			transaction = session.beginTransaction();
			session.save(transientInstance);
			transaction.commit();
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("persist failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public void remove(AppUsarioxprefil persistentInstance) {
		log.debug("removing AppUsarioxprefil instance");
		Transaction transaction = null;
		session = getSession();
		try {
			
			transaction = session.beginTransaction();
			session.delete(persistentInstance);
			transaction.commit();
			
			log.debug("remove successful");
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("remove failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public AppUsarioxprefil merge(AppUsarioxprefil detachedInstance) {
		log.debug("merging AppUsarioxprefil instance");
		Transaction transaction = null;
		session = getSession();
		try {
			
			transaction = session.beginTransaction();
			AppUsarioxprefil result = (AppUsarioxprefil) session.merge(detachedInstance);
			transaction.commit();
			
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public AppUsarioxprefil findById(AppUsarioxprefilId id) {
		log.debug("getting AppUsarioxprefil instance with id: " + id);
		Transaction transaction = null;
		session = getSession();
		try {
			
			transaction = session.beginTransaction();
			AppUsarioxprefil instance = (AppUsarioxprefil) session.get(AppUsarioxprefil.class, (Serializable) id);
			transaction.commit();
			log.debug("get successful");
			return instance;
			
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("get failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	@Override
	Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
}
