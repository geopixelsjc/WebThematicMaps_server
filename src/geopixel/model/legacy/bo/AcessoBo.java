package geopixel.model.legacy.bo;

import java.io.IOException;
import java.sql.SQLException;

import geopixel.model.legacy.dao.AcessoDao;
import geopixel.model.legacy.dto.Acesso;

public class AcessoBo {
	AcessoDao acessoDao;


	public void saveOrUpdate(Acesso entidade) {
		AcessoDao acessoDao = new AcessoDao();
		try {
			acessoDao.saveOrUpdate(entidade);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void delete(Acesso entidade) {
		
		
	}

	
	public Acesso getByID(Object id) {
		
		return null;
	}

}
