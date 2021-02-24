package zoo_webapp.zoomanager.Vehicles;

public class Vehicle {

    private int id;
    private String productionYear;
    private String registryNr;
    private String fuelType;
    private String transmissionType;
    private int capacity;
    private String supplier;
    private String zooName;
    private  String modelName;
    private String brandName;

    public Vehicle(int id, String productionYear, String registryNr, String fuelType,
                   String transmissionType, int capacity, String supplier, String zooName,
                   String modelName, String brandName) {
        this.id = id;
        this.productionYear = productionYear;
        this.registryNr = registryNr;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.capacity = capacity;
        this.supplier = supplier;
        this.zooName = zooName;
        this.modelName = modelName;
        this.brandName = brandName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getRegistryNr() {
        return registryNr;
    }

    public void setRegistryNr(String registryNr) {
        this.registryNr = registryNr;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getZooName() {
        return zooName;
    }

    public void setZooName(String zooName) {
        this.zooName = zooName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", productionYear='" + productionYear + '\'' +
                ", registryNr='" + registryNr + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", transmissionType='" + transmissionType + '\'' +
                ", capacity=" + capacity +
                ", supplier='" + supplier + '\'' +
                ", zooName='" + zooName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", brandName='" + brandName + '\'' +
                '}';
    }
}
