package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppTabela;
import geopixel.server.helper.HibernateUtil;

import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Home object for domain model class AppTabela.
 * @see .AppTabela
 * @author Hibernate Tools
 */
@Stateless
public class AppTabelaDAO extends GenericDAOHibernate<AppTabela> {

	public AppTabelaDAO(Class<AppTabela> entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}
	private static final Log log = LogFactory.getLog(AppTabelaDAO.class);

	private Session session;

	public void persist(AppTabela transientInstance) {
		log.debug("persisting AppTabela instance");
		session = getSession();
		Transaction transaction = null;
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

	public void remove(AppTabela persistentInstance) {
		log.debug("removing AppTabela instance");
		session = getSession();
		Transaction transaction = null;
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

	public AppTabela merge(AppTabela detachedInstance) {
		log.debug("merging AppTabela instance");
		session = getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			AppTabela result = (AppTabela) session.merge(detachedInstance);
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

	public AppTabela findById(int id) {
		log.debug("getting AppTabela instance with id: " + id);
		session = getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			AppTabela instance = (AppTabela) session.get(AppTabela.class, id);
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
