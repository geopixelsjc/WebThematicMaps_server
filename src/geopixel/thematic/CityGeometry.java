package geopixel.thematic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CityGeometry implements Serializable {
        
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", updatable = false, nullable = false)
        private Long   id = null;
        
        @Column(name = "gid")
        private String geometryId;
        
        @Column(name = "nm_municip")
        private String cityNumber;
        
        //@Type(type = "org.hibernate.spatial.GeometryType")
        //@Column(name="geom")
        //private Point geometry;
        
        public Long getId() {
                return id;
        }
        
        public void setId(Long id) {
                this.id = id;
        }
        
        public String getGeometryId() {
                return geometryId;
        }
        
        public void setGeometryId(String geometryId) {
                this.geometryId = geometryId;
        }
        
        public String getCityNumber() {
                return cityNumber;
        }
        
        public void setCityNumber(String cityNumber) {
                this.cityNumber = cityNumber;
        }
}