package geopixel.main;

import geopixel.model.hb.dao.AppTemaDAO;
import geopixel.model.hb.dao.AppUsuarioDAO;
import geopixel.model.hb.dto.AppAcesso;
import geopixel.model.hb.dto.AppPermissao;
import geopixel.model.hb.dto.AppTema;
import geopixel.model.hb.dto.AppUsuario;

import java.util.Iterator;


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
	
	AppUsuario usuario = new AppUsuario();
	//usuario.setUsrId(2200);
	usuario.setNome("INACIO2");
	
	AppTemaDAO dao = new AppTemaDAO(AppTema.class);
	try {
		//dao.persist(usuario);
		//access via Iterator
		Iterator<?> iterator = dao.getByID(1).getAppPermissaos().iterator();
		
		while(iterator.hasNext()){
			
		  AppPermissao element = (AppPermissao) iterator.next();
		  System.out.println(element.getPermit());
		}
		
		System.out.println();
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
}
