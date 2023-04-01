import java.util.ArrayList;

public class Product {
    protected String name;
    protected Seller seller;
    protected String company;
    protected int price;
    protected int quantity;
    protected ArrayList<String> other = new ArrayList<String>();
    protected ArrayList<Integer> ratings = new ArrayList<Integer>();
    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setOther(ArrayList<String> other) {
        this.other = other;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addRating(int rate) {
        ratings.add(rate);
    }

    public double rate(){
        double ans=0;
        for (Integer rating : ratings) {
            ans += rating;
        }

        if(ratings.size()>0) {
            return ans / ratings.size();
        }

        else{
            return 0;
        }
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String viewProduct(){
        return name + ", " + company + ", " + price + " dollars";
    }

    @Override
    public String toString() {
        return "name: '" + name + '\'' +
                ", rating: " + rate() + " based on " + ratings.size() + " reviews" +
                ", company: '" + company + '\'' +
                ", price: " + price +
                ", quantity: " + quantity +
                ", properties: " + other;
    }
}
