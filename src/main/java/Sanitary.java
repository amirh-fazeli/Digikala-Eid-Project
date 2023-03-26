import java.util.ArrayList;

public class Sanitary extends Product{
    private int mass;
    private ArrayList<String> ingredient;
    private String country;

    public void setMass(int mass) {
        this.mass = mass;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setIngredient(ArrayList ingredient) {
        this.ingredient = ingredient;
    }
}
