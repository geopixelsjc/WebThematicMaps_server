package geopixel.model.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

@SuppressWarnings("unchecked")
public class HibernateGenericDAO<PK, T> {
	private EntityManager entityManager;

	public HibernateGenericDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public T getById(Integer id) {
		return (T) entityManager.find(getTypeClass(), id);
	}

	public boolean save(T entity) {
		boolean success = false;
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			success = true;
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}

		return success;

	}

	public boolean update(T entity) {
		boolean success = false;
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			success = true;
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}

		return success;
	}

	public boolean delete(T entity) {
		boolean success = false;
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			success = true;
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}

		return success;
	}

	public List<T> findAll() {
		return entityManager.createQuery(("FROM " + getTypeClass().getName())).getResultList();
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		return clazz;
	}
}
