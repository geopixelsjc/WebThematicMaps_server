package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppDicionarioDado;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class AppDicionarioDado.
 * @see .AppDicionarioDado
 * @author Hibernate Tools
 */
@Stateless
public class AppDicionarioDadoDAO {

	private static final Log log = LogFactory
			.getLog(AppDicionarioDadoDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AppDicionarioDado transientInstance) {
		log.debug("persisting AppDicionarioDado instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AppDicionarioDado persistentInstance) {
		log.debug("removing AppDicionarioDado instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AppDicionarioDado merge(AppDicionarioDado detachedInstance) {
		log.debug("merging AppDicionarioDado instance");
		try {
			AppDicionarioDado result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AppDicionarioDado findById(int id) {
		log.debug("getting AppDicionarioDado instance with id: " + id);
		try {
			AppDicionarioDado instance = entityManager.find(
					AppDicionarioDado.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
