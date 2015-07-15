package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppTabela;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class AppTabela.
 * @see .AppTabela
 * @author Hibernate Tools
 */
@Stateless
public class AppTabelaDAO {

	private static final Log log = LogFactory.getLog(AppTabelaDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AppTabela transientInstance) {
		log.debug("persisting AppTabela instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AppTabela persistentInstance) {
		log.debug("removing AppTabela instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AppTabela merge(AppTabela detachedInstance) {
		log.debug("merging AppTabela instance");
		try {
			AppTabela result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AppTabela findById(int id) {
		log.debug("getting AppTabela instance with id: " + id);
		try {
			AppTabela instance = entityManager.find(AppTabela.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
