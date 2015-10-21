package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppUsuario;
import geopixel.server.helper.HibernateUtil;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Home object for domain model class AppUsuario.
 * @see .AppUsuario
 * @author Hibernate Tools
 */
@Stateless
public class AppUsuarioDAO extends GenericDAOHibernate<AppUsuario>{

	public AppUsuarioDAO(Class<AppUsuario> entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	//private static final Log log = LogFactory.getLog(AppUsuarioDAO.class);

	private Session session;

	public void persist(AppUsuario transientInstance) {
		//log.debug("persisting AppUsuario instance");
		Transaction transaction = null;
		session = getSession();
		try {
			transaction = session.beginTransaction();
			session.save(transientInstance);
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

	public void remove(AppUsuario persistentInstance) {
		//log.debug("removing AppUsuario instance");
		Transaction transaction = null;
		session = getSession();
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

	public AppUsuario merge(AppUsuario detachedInstance) {
		//log.debug("merging AppUsuario instance");
		Transaction transaction = null;
		session = getSession();
		try {
			transaction = session.beginTransaction();
			AppUsuario result = (AppUsuario) session.merge(detachedInstance);
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

	public AppUsuario findById(int id) {
		//log.debug("getting AppUsuario instance with id: " + id);
		Transaction transaction = null;
		session = getSession();
		try {
			transaction = session.beginTransaction();
			AppUsuario instance = (AppUsuario) session.get(AppUsuario.class, id);
			transaction.commit();
			//log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			transaction.rollback();
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
