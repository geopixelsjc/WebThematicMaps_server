package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppDocumentoDigital;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class AppDocumentoDigital.
 * @see .AppDocumentoDigital
 * @author Hibernate Tools
 */
@Stateless
public class AppDocumentoDigitalDAO {

	private static final Log log = LogFactory
			.getLog(AppDocumentoDigitalDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AppDocumentoDigital transientInstance) {
		log.debug("persisting AppDocumentoDigital instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AppDocumentoDigital persistentInstance) {
		log.debug("removing AppDocumentoDigital instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AppDocumentoDigital merge(AppDocumentoDigital detachedInstance) {
		log.debug("merging AppDocumentoDigital instance");
		try {
			AppDocumentoDigital result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AppDocumentoDigital findById(int id) {
		log.debug("getting AppDocumentoDigital instance with id: " + id);
		try {
			AppDocumentoDigital instance = entityManager.find(
					AppDocumentoDigital.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
