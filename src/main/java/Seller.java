import java.util.ArrayList;

public class Seller {
    private String username;
    private String password;
    private double balance;
    public SellerRequest request;
    public ArrayList<Product> products=new ArrayList<Product>();

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRequest(SellerRequest request) {
        this.request = request;
    }

    public void addProducts(Product product) {
        products.add(product);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public SellerRequest getRequest() {
        return request;
    }

    public double getBalance() {
        return balance;
    }
}
