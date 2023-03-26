import java.util.ArrayList;

public class SellerRequest {
    public Seller sender;
    private ArrayList<String> products=new ArrayList<String>();
    public String status="not announced";

    public void setSender(Seller sender) {
        this.sender = sender;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public Seller getSender() {
        return sender;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "sender: " + sender.getUsername() +
                ", products: " + products +
                ", status: '" + status + '\'' +
                '}';
    }
}
