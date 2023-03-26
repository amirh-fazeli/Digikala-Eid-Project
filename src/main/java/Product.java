import java.util.ArrayList;

public class Product {
    private String name;
    private Seller seller;
    private String company;
    private int price;
    private int quantity;
    private ArrayList<String> other;
    private ArrayList<String> commentList;

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

    public void addComments(String comment) {
        commentList.add(comment);
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
}
