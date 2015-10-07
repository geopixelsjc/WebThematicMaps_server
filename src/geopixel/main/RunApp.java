package geopixel.main;

import geopixel.service.DataBase;
import geopixel.service.DataBaseService;
import geopixel.thematic.Dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RunApp { 
        
        public static void main(String[] args) throws IOException, SQLException{
                
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
                
                
//                try {
//                        ResultSet result =  Dao.getGeometries(featureTable, featureGeocode, featureName, featureGeometry);
//
//                        while(result.next()){
//                                CityInformation city = new CityInformation();
//                                city.setId(result.getLong(1));
//                        
//                                System.out.println(city);                        
//                        }
//                        
//                } catch (IOException | SQLException e) {
//                        e.printStackTrace();
//                }
        	 
//        	List<String> json = new ArrayList<String>();
//        	json.add("LARANJA");
//        	json.add("AZUL");
//        	json.add("MARROM");
//        	json.add("ROXO");
//        	  	
//        	//System.out.println(JSonUtils.createJsonProperties(json));
//        	//System.out.println(JSonUtils.createJsonArray(json));
//        	String array = JSonUtils.createJsonArray(json);
//        	System.out.println(array);
//        	String properties=JSonUtils.createJsonProperties(json);
//        	System.out.println(properties);
//        	String feature=JSonUtils.createJsonFeature(array,properties);        	
//        	System.out.println(feature);
//        	List<String> fa = new ArrayList<String>();
//        	fa.add(feature);
//        	fa.add(feature);
//        	String featurearray=JSonUtils.createJsonArray(fa);
//        	String map = JSonUtils.createGeoJson(featurearray,"area", "4736");
//        	System.out.println(map);
//        	
//        	System.out.println(Controller.generateColors(4, "#ff0000", "#00ff00"));
//        	
//        	List<Double> range = new ArrayList<Double>();
//        	range.add(10.0);
//        	range.add(20.2);
//        	range.add(30.0);
//        	range.add(40.7);
//        	range.add(50.0);
//        	String legend=Controller.createLegend(range, "#000000", "#ffffff");
//        	System.out.println(legend);
//        	
//        	ResultSet teste = Dao.getIndicators("tab_indicadores");
//        	teste.next();
//        	System.out.println(JSonUtils.resultSet2Json(teste));
//        	
//        	System.out.println("//////////////");
//        	
//        	
//        	ResultSet teste2 = Dao.getGeometries("tab_municipios", "cod_ibge", "municipio", "geom");
//        	teste2.next();
//        	System.out.println(teste2.getString(1));
//        	System.out.println(teste2.getString(2));
//        	System.out.println(teste2.getString(3));
//        	
//        	System.out.println(JSonUtils.createJsonFeature(teste2.getString(3), "{[campo:'value']}"));
        	
         	DataBase dataBase=DataBaseService.getPostgresParameters();
        	Connection conn = DataBaseService.connect(dataBase);
        	
    		Charset charset = Charset.forName("UTF-8");
    		Path file = Paths.get("D:/Geopixel/Ceap", "tab.csv");    		
    		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
    		    String line = null;
    		    int count=0;
    		    List <String> columns = new ArrayList<String>();
    		    List <String> years = new ArrayList<String>();
    		    List <String> indicators = new ArrayList<String>();
    		    List <String> dataValues = new ArrayList<String>();
    		    String table="";
    		    String geocode="";
    		    String cityName="";
    		    String indicator="";
    		    String year="";
    		    String value="";
    		    while ((line = reader.readLine()) != null) {
    		    	count++;
    		    	switch (count) {
    			    	case 1:{
    			    		columns = getTokens(line);	
    			    		break;
    			    	}
    			    	case 2:{
    			    		years = getTokens(line);
    			    		break;
    			    	}
    			    	case 3:{
    			    		indicators=getTokens(line);	
    			    		break;
    			    	}
    			    	case 4:{
    			    		//documentation line
    			    		break;
    			    	}
    			    	default:{
    			    		dataValues=getTokens(line);
    			    		for (int i=3;i<columns.size();i++){
    				    		geocode=dataValues.get(1); 
    				    		cityName=dataValues.get(2);
    				    		indicator=indicators.get(i);
    				    		if (indicator.length()>0){
	    				    		year=years.get(i);
	    				    		value=dataValues.get(i);
	    				    		System.out.println(geocode+";"+cityName+";"+indicator+";"+year+";"+value);
	    				    		
	    				    		Dao.insertRow (conn,table, geocode,cityName,indicator, year, value);    		
    				    		}
    				    					    		
    			    		}
    			    		break;//f	
    			    	}//def  	
    		    	}//sw
    		    }//wh
    		} catch (IOException x) {
    		    System.err.format("IOException: %s%n", x);
    		}	 	
        	
        }
        public static List<String> getTokens(String line){
    		List<String> tokens = new ArrayList<String>();
    		int first=0;		
    		int last=0;
    		for (int j=0; j<line.length();j++){
    			if (Character.toString(line.charAt(j)).equals(";")){
    				//last=j-1;
    				last=j;
    				if (last<first){
    					tokens.add("");
    				}
    				else{
    					tokens.add(line.substring(first,last));    					
    				}				
    				first=j+1;				
    			}
    		}
    		return tokens;	
    	}
        
}
