package geopixel.model.external;

public class GeoJsonChoroplethMap {
	private String Legend;
	private String Map;
	
	public String getLegend() {return Legend;}
	
	public void setLegend(String jsonLegend) { Legend=jsonLegend;}
	
	public String getMap () {return Map;}
	
	public void setMap (String geoJsonMap) {Map=geoJsonMap;}

}
