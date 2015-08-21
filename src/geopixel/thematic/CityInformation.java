package geopixel.thematic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class CityInformation implements Serializable {
        private static final long serialVersionUID = -28626766222400L;
        
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", updatable = false, nullable = false)
        private Long              id               = null;
        
        @Version
        @Column(name = "version")
        private int               version          = 0;
        
        public Long getId() {
                return this.id;
        }
        
        public void setId(final Long id) {
                this.id = id;
        }
        
        public int getVersion() {
                return this.version;
        }
        
        public void setVersion(final int version) {
                this.version = version;
        }
        
        public String toString() {
                String result = "";
                if (id != null) result += id;
                return result;
        }
        
        @Override
        public boolean equals(Object that) {
                if (this == that) { return true; }
                if (that == null) { return false; }
                if (getClass() != that.getClass()) { return false; }
                if (id != null) { return id.equals(((CityInformation) that).id); }
                return super.equals(that);
        }
        
        @Override
        public int hashCode() {
                if (id != null) { return id.hashCode(); }
                return super.hashCode();
        }
}