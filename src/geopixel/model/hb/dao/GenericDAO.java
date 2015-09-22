package geopixel.model.hb.dao;

/**
*
* @since 25/06/2012
*/
public interface GenericDAO<T> {

   void saveOrUpdate(T entidade) throws Exception;

   void delete(T entidade) throws Exception;

   T getByID(Object id) throws Exception;
}
