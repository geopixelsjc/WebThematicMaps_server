package geopixel.model.legacy.dao;

import java.io.IOException;
import java.sql.SQLException;

public interface GenericDao<T> {

	void saveOrUpdate(T entidade) throws IOException, SQLException;

	void delete(T entidade);

	T getByID(Object id);

}
