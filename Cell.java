package Java_Project;

public class Cell {
    private boolean hasCrop;
    private Farmer farmer;
    private Pest pest;
    private Protector protector;

    public Cell() {
        this.hasCrop = false;
        this.farmer = null;
        this.pest = null;
        this.protector = null;
    }

    public boolean hasCrop() {
        return hasCrop;
    }

    public void plantCrop() {
        hasCrop = true;
    }

    public void removeCrop() {
        hasCrop = false;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public Pest getPest() {
        return pest;
    }

    public void setPest(Pest pest) {
        this.pest = pest;
    }

    public Protector getProtector() {
        return protector;
    }

    public void setProtector(Protector protector) {
        this.protector = protector;
    }
}
