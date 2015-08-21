package geopixel.main;

import geopixel.model.hb.dto.AppPermissao;
import geopixel.thematic.CityInformation;
import geopixel.thematic.Dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RunApp { 
        
        public static void main(String[] args){
                
                //		AcessoDao acessoDao = new AcessoDao();
                //		Acesso entidade = new Acesso();
                //		
                //		//entidade.setAscId(1);
                //		entidade.setNome("Administrador");
                //		entidade.setLogin("admin");
                //		entidade.setChave("gsgdjsgsdk");
                //		entidade.setSenha("5nvq02");
                //		
                //		try {
                //			acessoDao.saveOrUpdate(entidade);
                //		} catch (IOException | SQLException e) {
                //			e.printStackTrace();
                //		}
                //		
                
                //	AppAcesso acesso = new AppAcesso();
                //	AppUsuario usuario = new AppUsuario();
                //	acesso.setAscId(2200);
                //	acesso.setNome("inacio");
                //	acesso.setLogin("in@cio");
                //	acesso.setSenha("a7a567");
                //	usuario.setUsrId(2200);
                //	usuario.setNome("INACIO");
                //	
                //	acesso.setAppUsuario(usuario);
                
                
                //	AppAcessoDAO dao = new AppAcessoDAO(AppAcesso.class);
                //	dao.persist(acesso);
                
                //	AppUsuario usuario = new AppUsuario();
                //	//usuario.setUsrId(2200);
                //	usuario.setNome("INACIO2");
                //	
                //	AppTemaDAO dao = new AppTemaDAO(AppTema.class);
                //	try {
                //		//dao.persist(usuario);
                //		//access via Iterator
                //		Iterator<?> iterator = dao.getByID(1).getAppPermissaos().iterator();
                //		
                //		while(iterator.hasNext()){
                //			
                //		  AppPermissao element = (AppPermissao) iterator.next();
                //		  System.out.println(element.getPermit());
                //		}
                //		
                //		System.out.println();
                //	} catch (Exception e) {
                //		e.printStackTrace();
                //	}
                //	AppUsuario usuario = new AppUsuario();
                //	AppUsuarioDAO dao = new AppUsuarioDAO(AppUsuario.class);
                //	
                //	try {
                //		//usuario = dao.getByID(1);
                //	} catch (Exception e) {
                //		e.printStackTrace();
                //	}
                //	
                //	try {
                //		Iterator<?> iterator = dao.getByID(1).getAppAcessos().iterator();
                //		while (iterator.hasNext()) {
                //			AppAcesso element = (AppAcesso) iterator.next();
                //			System.out.println(element.getSenha());
                //			
                //		} 
                //	} catch (Exception e) {
                //		e.printStackTrace();
                //	}
                //	
                //System.out.println(usuario.getAppAcessos());
                //	try {
                //		System.out.println(TerracoreService.getDataDictionaryByKey("61fba0891b737bff308badbe119e0160"));
                //	} catch (IOException | SQLException e) {
                //		// TODO Auto-generated catch block
                //		e.printStackTrace();
                //	}
                
                //		DataBase dataBase = new DataBase();
                //		dataBase.setDatabase("IP");
                //		dataBase.setHost("74.208.229.211");
                //		dataBase.setPort("5432");
                //		dataBase.setUser("postgres");
                //		dataBase.setPassword("geo@px2015");
                //		dataBase.setDataBaseTypeEnum(DataBaseTypeEnum.POSTGRES);
                //		
                //		try {
                //			ArrayList<String> data = DataBaseService.getExternalTable(dataBase,"spatial_ref_sys",4).getAttributes();
                //			
                //			for (String value : data) {
                //				System.out.println(value);
                //			}
                //			
                //		} catch (IOException | SQLException e) {
                //			e.printStackTrace();
                //		}
                
                //		try {
                //			
                //			ArrayList<AppTabela> tabelas = TerracoreService.getUserTableByThemeId(1);
                //			
                //			ArrayList<String> data = TerracoreService.getPhysicalTable(tabelas.get(1),1,10).getAttributes();
                //			
                //			for (String value : data) {
                //				System.out.println(value);
                //			}
                //			
                //			
                //			for (AppTabela appTabela : tabelas) {
                //				System.out.println(appTabela.getNome());
                //			}
                //		
                //			
                //		} catch (IOException | SQLException e) {
                //			e.printStackTrace();
                //		}
                
                
                try {
                        ResultSet result =  Dao.getCityGeoJSON();  

                        while(result.next()){
                                CityInformation city = new CityInformation();
                                city.setId(result.getLong(1));
                        
                                System.out.println(city);                        
                        }
                        
                } catch (IOException | SQLException e) {
                        e.printStackTrace();
                }
                
                
        }
        
}
