package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppFuncao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class AppFuncao.
 * @see .AppFuncao
 * @author Hibernate Tools
 */
@Stateless
public class AppFuncaoDAO {

	private static final Log log = LogFactory.getLog(AppFuncaoDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AppFuncao transientInstance) {
		log.debug("persisting AppFuncao instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AppFuncao persistentInstance) {
		log.debug("removing AppFuncao instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AppFuncao merge(AppFuncao detachedInstance) {
		log.debug("merging AppFuncao instance");
		try {
			AppFuncao result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AppFuncao findById(int id) {
		log.debug("getting AppFuncao instance with id: " + id);
		try {
			AppFuncao instance = entityManager.find(AppFuncao.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
