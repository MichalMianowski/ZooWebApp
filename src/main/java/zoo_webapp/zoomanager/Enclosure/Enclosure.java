package zoo_webapp.zoomanager.Enclosure;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

// KLASA WYBIEG
public class Enclosure {

    private int id;
    private String typeEnclosure;
    private int surface;
    private String attractions;
    private String areVisitorsAllowed;
    private int nrBuilding;

    public Enclosure(){

    }

    public Enclosure(int id, String type, int surface, String attractions, String guestAllowed, int nrBuild) {
        this.id = id;
        this.typeEnclosure = type;
        this.surface = surface;
        this.attractions = attractions;
        this.areVisitorsAllowed = guestAllowed;
        this.nrBuilding = nrBuild;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeEnclosure() {
        return typeEnclosure;
    }

    public void setTypeEnclosure(String type) {
        this.typeEnclosure = type;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getNrBuilding() {
        return nrBuilding;
    }

    public void setNrBuilding(int nrBuild) {
        this.nrBuilding = nrBuild;
    }

    public String getAttractions() {
        return attractions;
    }

    public void setAttractions(String attractions) {
        this.attractions = attractions;
    }

    public String getAreVisitorsAllowed() {
        return areVisitorsAllowed;
    }

    public void setAreVisitorsAllowed(String areVisitorsAllowed) {
        this.areVisitorsAllowed = areVisitorsAllowed;
    }

    @Override
    public String toString() {
        return "Enclosure{" +
                "id=" + id +
                ", type='" + typeEnclosure + '\'' +
                ", surface=" + surface +
                ", attractions=" + attractions +
                ", are guests allowed=" + areVisitorsAllowed +
                ", nr of building=" + nrBuilding +
                '}';
    }


}
