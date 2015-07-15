package geopixel.model.hb.dao;
// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppAcesso;
import geopixel.model.legacy.dto.Acesso;
import geopixel.server.helper.HibernateUtil;

import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Home object for domain model class AppAcesso.
 * @see .Acesso
 * @author Hibernate Tools
 * @param <T>
 */
@Stateless
public class AppAcessoDAO extends GenericDAOHibernate<AppAcesso>{


	public AppAcessoDAO(Class<AppAcesso> entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	private static final Log log = LogFactory.getLog(AppAcessoDAO.class);

	private Session session;

	public void persist(AppAcesso transientInstance) {
		Transaction transaction = null;
		session = getSession();
		
		log.debug("persisting AppAcesso instance");
		try {
			transaction = session.beginTransaction();
			session.persist(transientInstance);
			transaction.commit();
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			transaction.rollback();
			
			throw re;
		}finally{
			session.close();
		}
	}

	public void remove(AppAcesso persistentInstance) {
		
		log.debug("removing AppAcesso instance");
		session = getSession();
		try {
			session.delete(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Acesso merge(AppAcesso detachedInstance) {
		session = getSession();
		log.debug("merging AppAcesso instance");
		try {
			Acesso result = (Acesso) session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Acesso findById(int id) {
		log.debug("getting AppAcesso instance with id: " + id);
		try {
			Acesso instance = (Acesso) session.get(Acesso.class, id);
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
