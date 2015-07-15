package geopixel.model.hb.dao;

// default package
// Generated 15/07/2015 10:53:57 by Hibernate Tools 4.0.0

import geopixel.model.hb.dto.AppEndereco;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class AppEndereco.
 * @see .AppEndereco
 * @author Hibernate Tools
 */
@Stateless
public class AppEnderecoDAO {

	private static final Log log = LogFactory.getLog(AppEnderecoDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AppEndereco transientInstance) {
		log.debug("persisting AppEndereco instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AppEndereco persistentInstance) {
		log.debug("removing AppEndereco instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AppEndereco merge(AppEndereco detachedInstance) {
		log.debug("merging AppEndereco instance");
		try {
			AppEndereco result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AppEndereco findById(int id) {
		log.debug("getting AppEndereco instance with id: " + id);
		try {
			AppEndereco instance = entityManager.find(AppEndereco.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
