package geopixel.model.external;

/**
 * Class to maintain all choropleth map parameters
 * Has only methods to set and get parameters
 * @author Freitas,U.M
 *
 */
public class ChoroplethMapDescription {
	private  String Table;
	private  String Attribute;
	private  String GeoCode; 
	private  String Value;
	private  String Layer;
	private  String FeatureCode;
	private  String FeatureName;
	private  String Box;	
	private  String Geometry = "geom";			
	private  String CRS = "-1";
	private  String GroupingType;
	private  String NClasses;
	private  String FirstColor;
	private  String LastColor;
	private  String Year;
	private  String TargetYear;
	private  String TargetAttribute;
	
	public void setAttributeTable(String table){Table=table;}
	public String getAttributeTable(){return Table;}
	public void setAttribute(String attribute){Attribute=attribute;}
	public String getAttribute(){return Attribute;}
	public void setGeocode(String geocode){GeoCode=geocode;}
	public String getGeoCode() {return GeoCode;}
	public void setValue(String value){Value=value;}
	public String getValue() {return Value;}
	public void setLayer(String layer){Layer=layer;}
	public String getLayer(){return Layer;}
	public void setFeatureCode(String featureCode){FeatureCode=featureCode;}
	public String getFeatureCode() {return FeatureCode;}
	public void setGroupingType(String groupingType){GroupingType=groupingType;}
	public String getGroupingType(){return GroupingType;}
	public void setFeatureName(String name){FeatureName=name;}
	public String getFeatureName(){return FeatureName;}
	public String getBox() {return Box;}
	public void setBox(String box) {Box = box;}
	public void setGeometry (String geom){Geometry = geom;}
	public String getGeometry() {return Geometry;}
	public void setCRS(String crs){CRS = crs;}
	public String getCRS() {return CRS;}
	public void setNClasses(String nClasses){NClasses=nClasses;}
	public String getNClasses(){return NClasses;}
	public void setFirstColor(String firstColor){FirstColor="#"+firstColor;}
	public String getFirstColor(){return FirstColor;} 
	public void setLastColor(String lastColor){LastColor="#"+lastColor;}
	public String getLastColor(){return LastColor;}
	public void setYear(String year){Year=year;}
	public String getYear() {return Year;}
	public void setTargetYear(String targetyear){TargetYear=targetyear;}
	public String getTargetYear() {return TargetYear;}
	public void setTargetAttribute(String targetattribute){TargetAttribute=targetattribute;}
	public String getTargetAttribute() {return TargetAttribute;}
	
}
