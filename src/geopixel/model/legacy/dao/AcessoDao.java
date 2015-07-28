package geopixel.model.legacy.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import geopixel.model.legacy.dto.Acesso;
import geopixel.service.DataBaseService;

public class AcessoDao implements GenericDao<Acesso>{

	@Override
	public void saveOrUpdate(Acesso entidade) throws IOException, SQLException {
		Connection conn = DataBaseService.connect(DataBaseService.getPostgresParameters());
		conn.setAutoCommit(false);
		
		//save
		if(entidade.getAscId().equals(null)){
			
			
		}
		//update
		else{
			PreparedStatement update = null;

			String updateString =
			        "update app_acesso " +
			        "set nome = ?,"  + 
			        "login = ?, " +
			        "senha = ?," +
			        "chave = ? " +
			        "where asc_id = ?";
			
			 update = conn.prepareStatement(updateString);
			 
			 update.setString(1,entidade.getNome());
			 update.setString(2,entidade.getLogin());
			 update.setString(3,entidade.getSenha());
			 update.setString(4,entidade.getChave());
			 
			 update.setInt(5, entidade.getAscId());
			 int up = update.executeUpdate();
			 
			 if(up==1){
				 conn.commit();
			 }else{
				 conn.rollback();
			 }			 
		}
		 conn.close();
	}

	@Override
	public void delete(Acesso entidade) {
		
		
	}

	@Override
	public Acesso getByID(Object id) {
		
		return null;
	}

}
