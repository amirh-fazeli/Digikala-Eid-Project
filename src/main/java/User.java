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

    public void rateAndComment(DigikalaService service, Scanner scan){
        if(allBought.size()>0) {
            service.viewProductList(allBought);
            System.out.println("choose an item");
            int ch = Integer.parseInt(scan.nextLine()) - 1;

            if (rated.containsKey(allBought.get(ch))) {
                System.out.println("you have already rated this product do you want to change it? yes/no");

                if (scan.nextLine().equals("no")) {
                    return;
                }
            }

            System.out.println("rate from 0 to 5");
            while (true) {
                int rate = Integer.parseInt(scan.nextLine());

                if (rate >= 0 && rate <= 5) {
                    rated.put(allBought.get(ch), rate);
                    allBought.get(ch).addRating(rate);
                    System.out.println("rated successfully");
                    break;
                } else {
                    System.out.println("your rating should be in range of 0 to 5");
                }
            }

            System.out.println("do you want to comment on this product? yes/no");

            if (scan.nextLine().equals("yes")){
                System.out.println("insert your comment");
                allBought.get(ch).addComment(scan.nextLine());
                System.out.println("your comment submitted successfully");
            }
        }

        else{
            System.out.println("you haven't bought any items yet");
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
                    (0.9 * cartKeys.get(i).getPrice()));
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
