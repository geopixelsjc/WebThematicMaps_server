package geopixel.service;

import geopixel.enumeration.DataBaseTypeEnum;

public class DataBase {
	private String host;
	private String user;
	private String password;
	private String database;
	private String port;
	private DataBaseTypeEnum dataBaseTypeEnum;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public DataBaseTypeEnum getDataBaseTypeEnum() {
		return dataBaseTypeEnum;
	}
	public void setDataBaseTypeEnum(DataBaseTypeEnum dataBaseTypeEnum) {
		this.dataBaseTypeEnum = dataBaseTypeEnum;
	}

}
