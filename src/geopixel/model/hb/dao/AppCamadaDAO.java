package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppCamada;
import geopixel.server.helper.HibernateUtil;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Home object for domain model class AppCamada.
 * @see .AppCamada
 * @author Hibernate Tools
 */
@Stateless
public class AppCamadaDAO extends GenericDAOHibernate<AppCamada> {

	public AppCamadaDAO(Class<AppCamada> entity) {
		super(entity);
	}

	private static final Log log = LogFactory.getLog(AppCamadaDAO.class);

	@PersistenceContext
	private Session session ;

	public void persist(AppCamada transientInstance) {
		log.debug("persisting AppCamada instance");
		Transaction transaction = null;
		session = getSession();
		try {
			
			transaction = session.beginTransaction();
			session.persist(transientInstance);
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

	public void remove(AppCamada persistentInstance) {
		log.debug("removing AppCamada instance");
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

	public AppCamada merge(AppCamada detachedInstance) {
		log.debug("merging AppCamada instance");
		Transaction transaction = null;
		session = getSession();
		try {
			transaction = session.beginTransaction();
			AppCamada result = (AppCamada) session.merge(detachedInstance);
			transaction.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("merge failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public AppCamada findById(int id) {
		log.debug("getting AppCamada instance with id: " + id);
		try {
			AppCamada instance = (AppCamada) session.get(AppCamada.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
}
