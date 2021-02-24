package zoo_webapp.zoomanager.Animal;

public class Animal {

    private int id;
    private String areTrained;
    private String partInShows;
    private String diet;
    private String speciesName;

    public Animal(){
    }
    public Animal(int id, String areTrained, String partInShows, String diet, String speciesName) {
        this.id = id;
        this.areTrained = areTrained;
        this.partInShows = partInShows;
        this.diet = diet;
        this.speciesName = speciesName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreTrained() {
        return areTrained;
    }

    public void setAreTrained(String areTrained) {
        this.areTrained = areTrained;
    }

    public String getPartInShows() {
        return partInShows;
    }

    public void setPartInShows(String partInShows) {
        this.partInShows = partInShows;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", areTrained='" + areTrained + '\'' +
                ", partInShows='" + partInShows + '\'' +
                ", diet='" + diet + '\'' +
                ", speciesName=" + speciesName +
                '}';
    }
}
