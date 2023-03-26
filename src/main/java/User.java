import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<String> addresses=new ArrayList<String>();
    public ArrayList<UserRequest> requests=new ArrayList<UserRequest>();
    public ArrayList<String> news=new ArrayList<String>();
    public ArrayList<Product> cart=new ArrayList<Product>();
    public ArrayList<ArrayList<Product>> orders=new ArrayList<ArrayList<Product>>();
    private int wallet=0;

    public void addRequest(UserRequest request){
        requests.add(request);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addAddress(String address) {
        addresses.add(address);
    }

    public void addNews(String newd) {
        news.add(newd);
    }

    public void addToCart(Product product) {
        cart.add(product);
    }


    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getWallet() {
        return wallet;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
