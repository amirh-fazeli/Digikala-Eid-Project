import java.util.ArrayList;
import java.util.LinkedHashMap;

public class OrderRequest {
    private LinkedHashMap<Product, Integer> cart=new LinkedHashMap<Product, Integer>();
    private String address;
    private User user;
    private String status="not announced";

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCart(LinkedHashMap<Product, Integer> cart) {
        this.cart = cart;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "user: " + user.getUsername() +
                ", status: '" + status + '\'' +
                ", address: '" + address + '\'';
    }
}
