package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppPermissao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class AppPermissao.
 * @see .AppPermissao
 * @author Hibernate Tools
 */
@Stateless
public class AppPermissaoDAO {

	private static final Log log = LogFactory.getLog(AppPermissaoDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AppPermissao transientInstance) {
		log.debug("persisting AppPermissao instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AppPermissao persistentInstance) {
		log.debug("removing AppPermissao instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AppPermissao merge(AppPermissao detachedInstance) {
		log.debug("merging AppPermissao instance");
		try {
			AppPermissao result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AppPermissao findById(int id) {
		log.debug("getting AppPermissao instance with id: " + id);
		try {
			AppPermissao instance = entityManager.find(AppPermissao.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
