package geopixel.enumeration;

public enum DataBaseTypeEnum {
	
	POSTGRES(1),SQLSERVER(2),MYSQL(3),ORACLE(4); 
	
	public int bdType; 
	
	private DataBaseTypeEnum(int val) {
		bdType = val; 
	}

}
