package zoo_webapp.zoomanager.Building;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

public class Building {

    //public enum TypeBudilding{gospodarczy,logistyczny,uslugowy,ze zwierzetami;}
    //public enum TypeSection{A,B,C,D,E;}

    private int id;
    private String type;
    private String section;
    private int surface;
    private int zooId;

    public Building(int id, String type, String section, int surface, int zooId) {
        this.id = id;
        this.type = type;
        this.section = section;
        this.surface = surface;
        this.zooId = zooId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getZooId() {
        return zooId;
    }

    public void setZooId(int zooId) {
        this.zooId = zooId;
    }
}
