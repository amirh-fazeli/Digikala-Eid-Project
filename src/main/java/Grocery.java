public class Grocery extends Product {
    private int weight;
    private int licenceNumber;

    public void setLicenceNumber(int licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", weight: " + weight +
                ", licenceNumber: " + licenceNumber;
    }
}
