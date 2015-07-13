package geopixel.main;

import geopixel.service.TerracoreService;

import java.io.IOException;
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
		
	try {
		System.out.println(TerracoreService.checkKey("a760cc1f7f4451240dc5deab2eddc554"));
	} catch (IOException | SQLException e) {
		e.printStackTrace();
	}
	}
}
