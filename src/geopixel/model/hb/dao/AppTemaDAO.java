package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppTema;
import geopixel.server.helper.HibernateUtil;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Home object for domain model class AppTema.
 * @see .AppTema
 * @author Hibernate Tools
 */
@Stateless
public class AppTemaDAO extends GenericDAOHibernate<AppTema> {

	public AppTemaDAO(Class<AppTema> entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	//private static final Log log = LogFactory.getLog(AppTemaDAO.class);

	private Session session;

	public void persist(AppTema transientInstance) {
		//log.debug("persisting AppTema instance");
		session = getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.persist(transientInstance);
			transaction.commit();
			
			//log.debug("persist successful");
		} catch (RuntimeException re) {
			transaction.rollback();
			//log.error("persist failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public void remove(AppTema persistentInstance) {
		//log.debug("removing AppTema instance");
		session = getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(persistentInstance);
			transaction.commit();
			//log.debug("remove successful");
		} catch (RuntimeException re) {
			transaction.rollback();
			//log.error("remove failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public AppTema merge(AppTema detachedInstance) {
		//log.debug("merging AppTema instance");
		session = getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			AppTema result = (AppTema) session.merge(detachedInstance);
			transaction.commit();
			
			//log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			transaction.rollback();
			//log.error("merge failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public AppTema findById(int id) {
		//log.debug("getting AppTema instance with id: " + id);
		session = getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			AppTema instance = (AppTema) session.get(AppTema.class, id);
			transaction.commit();
			
			//log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			transaction.rollback();
			//log.error("get failed", re);
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
