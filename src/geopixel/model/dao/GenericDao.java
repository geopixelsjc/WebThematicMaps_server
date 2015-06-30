package geopixel.model.dao;

public interface GenericDao<T> {

	void saveOrUpdate(T entidade);

	void delete(T entidade);

	T getByID(Object id);

	T executeQuery(String sql);
}
