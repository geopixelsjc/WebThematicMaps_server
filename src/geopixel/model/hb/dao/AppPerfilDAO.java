package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppPerfil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class AppPerfil.
 * @see .AppPerfil
 * @author Hibernate Tools
 */
@Stateless
public class AppPerfilDAO {

	private static final Log log = LogFactory.getLog(AppPerfilDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AppPerfil transientInstance) {
		log.debug("persisting AppPerfil instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AppPerfil persistentInstance) {
		log.debug("removing AppPerfil instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AppPerfil merge(AppPerfil detachedInstance) {
		log.debug("merging AppPerfil instance");
		try {
			AppPerfil result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AppPerfil findById(int id) {
		log.debug("getting AppPerfil instance with id: " + id);
		try {
			AppPerfil instance = entityManager.find(AppPerfil.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
