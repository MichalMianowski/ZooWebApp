package zoo_webapp.zoomanager.Zoo;

public class ZOO {

    private int id;
    private String nameZoo;
    private String city;
    private String street;
    private String buildingNr;
    private String postcode;
    private String founder;
    private int budget;

    public ZOO(int id, String nameZoo){
        this.id = id;
        this.nameZoo = nameZoo;
    }

    public ZOO(int id, String nameZoo, String city, String street, String buildingNr, String postcode, String founder, int budget) {
        this.id = id;
        this.nameZoo = nameZoo;
        this.city = city;
        this.street = street;
        this.buildingNr = buildingNr;
        this.postcode = postcode;
        this.founder = founder;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameZoo() {
        return nameZoo;
    }

    public void setNameZoo(String nameZoo) {
        this.nameZoo = nameZoo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNr() {
        return buildingNr;
    }

    public void setBuildingNr(String buildingNr) {
        this.buildingNr = buildingNr;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
