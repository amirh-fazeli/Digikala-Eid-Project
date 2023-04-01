import java.util.*;

public class User {
    private String username;
    private String password;
    private ArrayList<String> addresses=new ArrayList<String>();
    private String phone="";
    private String email="";
    HashMap<Product, Integer> rated = new HashMap<Product, Integer>();
    public ArrayList<UserRequest> requests=new ArrayList<UserRequest>();
    public ArrayList<String> news=new ArrayList<String>();
    LinkedHashMap<Product, Integer> cart = new LinkedHashMap<Product, Integer>();
    ArrayList<Product> cartKeys = new ArrayList<Product>();
    public ArrayList<Product> allBought=new ArrayList<Product>();
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

    public void addToCart(Product product, Scanner scan) {
        while (true) {
            System.out.println("how many of this item do you want? ");
            int q = Integer.parseInt(scan.nextLine());
            if (q <= product.getQuantity()) {
                cart.put(product, q);
                cartKeys.add(product);
                break;
            } else {
                System.out.println("the seller does no have that many available");
            }
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getWallet() {
        return wallet;
    }

    public ArrayList<String> getAddresses() {
        return addresses;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void purchaseCart(DigikalaService service){
        for(int i=0;i<cart.size();i++){
            allBought.add(cartKeys.get(i));
            cartKeys.get(i).getSeller().setBalance(cartKeys.get(i).getSeller().getBalance() +
                    (0.2 * cartKeys.get(i).getPrice()));
            cartKeys.get(i).setQuantity(cartKeys.get(i).quantity - cart.get(cartKeys.get(i)));
        }
        setWallet(wallet - cartPrice());
        cart.clear();
        System.out.println(username + " purchased this cart successfully");
    }

    public int cartPrice(){
        int price=0;
        for(int i=0;i<cart.size();i++){
            price+=(cartKeys.get(i).getPrice()*cart.get(cartKeys.get(i)));
        }

        return price;
    }

    public void viewCart(){
        for (int i=0;i<cartKeys.size();i++){
            System.out.println("[" + cartKeys.get(i).getName() + ", " + cartKeys.get(i).getPrice() + "dollars, " + cart.get(cartKeys.get(i)) + "]");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", addresses=" + addresses +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
