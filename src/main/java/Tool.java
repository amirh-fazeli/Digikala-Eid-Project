public class Tool extends Product {
    private int weight;
    private int size;

    public void setSize(int size) {
        this.size = size;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", weight: " + weight +
                ", size: " + size ;
    }
}
