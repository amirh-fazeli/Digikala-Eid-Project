import java.util.ArrayList;
import java.util.Scanner;

public class DigikalaService {
    public ArrayList<Product> productList = new ArrayList<Product>();
    public ArrayList<User> userList=new ArrayList<User>();
    public ArrayList<Admin> adminList=new ArrayList<Admin>();
    public ArrayList<Seller> sellerList=new ArrayList<Seller>();
    public ArrayList<UserRequest> userRequestList=new ArrayList<UserRequest>();
    public ArrayList<OrderRequest> orderRequestList=new ArrayList<OrderRequest>();
    public ArrayList<SellerRequest> sellerRequestList=new ArrayList<SellerRequest>();


    public void addUser(User user) {
        userList.add(user);
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void addUserRequest(UserRequest request) {
        userRequestList.add(request);
    }

    public void addSellerRequest(SellerRequest request) {
        sellerRequestList.add(request);
    }

    public void addAdmin(Admin admin) {
        adminList.add(admin);
    }

    public void addSeller(Seller seller) {
        sellerList.add(seller);
    }

    public boolean userPasswordCheck(String username, String password) {
        int ind= userIndex(username);

        if(userList.get(ind).getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public boolean sellerPasswordCheck(String username, String password) {
        int ind= sellerIndex(username);

        if(sellerList.get(ind).getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public boolean adminPasswordCheck(String username, String password) {
        int ind= adminIndex(username);

        if(adminList.get(ind).getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public int userIndex(String username){
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)) {
                return i;
            }
        }

        //-1 indicates that the user doesn't exist in the library
        return -1;
    }

    public int sellerIndex(String username){
        for (int i = 0; i < sellerList.size(); i++) {
            if (sellerList.get(i).getUsername().equals(username)) {
                return i;
            }
        }

        //-1 indicates that the user doesn't exist in the library
        return -1;
    }

    public int adminIndex(String username){
        for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).getUsername().equals(username)) {
                return i;
            }
        }

        //-1 indicates that the user doesn't exist in the library
        return -1;
    }

    public boolean doesUserExist(String username) {
        int ind = userIndex(username);

        if (ind != -1) {
            return true;
        }
        return false;
    }

    public boolean doesSellerExist(String username) {
        int ind = sellerIndex(username);

        if (ind != -1) {
            return true;
        }
        return false;
    }

    public boolean doesAdminExist(String username) {
        int ind = adminIndex(username);

        if (ind != -1) {
            return true;
        }
        return false;
    }

    public User searchUser(String username){
        int ind=userIndex(username);
        return userList.get(ind);
    }

    public Seller searchSeller(String username){
        int ind=sellerIndex(username);
        return sellerList.get(ind);
    }

    public ArrayList<Product> searchProduct(){
        Scanner scan=new Scanner(System.in);
        ArrayList<Product> result=new ArrayList<>();

        System.out.println("insert name of the product you want to search");
        String title=scan.nextLine();
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).getName().contains(title) || title.contains(productList.get(i).getName())) {
                if (productList.get(i).getQuantity() > 0) {
                    result.add(productList.get(i));
                }
            }
        }
        System.out.println("do you want to apply a price limit? yes/no");
        if(scan.nextLine().equals("yes")){
            System.out.println("enter your price limit: (items above your limit wont be included in your search result)");
            int limit=Integer.parseInt(scan.nextLine());
            for(int i=0;i<result.size();){
                if(result.get(i).getPrice()>limit){
                    result.remove(result.get(i));
                    i=0;
                    continue;
                }
                i++;
            }
        }
        return result;
    }

    public void order(User user){
        Scanner scan=new Scanner(System.in);
        ArrayList<Product> result =searchProduct();

        if(result.size()==0){
            System.out.println("there was no result found");
        }

        else{
            System.out.println("there was " + result.size() + " results found");
            viewProductList(result);

            System.out.println("choose an item");
            int choice=Integer.parseInt(scan.nextLine()) - 1;

            System.out.println("what do you want to do with this product?");
            System.out.println("1.add to cart\n2.view full details\n3.view comments");

            switch (Integer.parseInt(scan.nextLine())){
                case 1:
                    if(!user.cartKeys.contains(result.get(choice))) {
                        user.addToCart(result.get(choice),scan);
                        System.out.println(result.get(choice).getName() + " added to cart");
                    }

                    else{
                        System.out.println(result.get(choice).getName() + " is already in your cart, do you want to" +
                                " remove it? yes/no");

                        if (scan.nextLine().equals("yes")){
                            user.cart.remove(result.get(choice));
                            System.out.println("removed successfully");
                        }
                    }
                    break;

                case 2:
                    System.out.println(result.get(choice));
                    System.out.println("do you want to add this to your cart? yes/no");

                    if(scan.nextLine().equals("yes")){
                        user.addToCart(result.get(choice),scan);
                        System.out.println(result.get(choice).getName() + " added to cart");
                    }
                    break;

                case 3:
                    result.get(choice).viewComments();
                    System.out.println("do you want to add this product to your cart? yes/no");

                    if(scan.nextLine().equals("yes")){
                        user.addToCart(result.get(choice),scan);
                        System.out.println(result.get(choice).getName() + " added to cart");
                    }
                    break;
            }
        }
    }

    public void viewProductList(ArrayList<Product> list){
        for(int i=0;i<list.size();i++){
            System.out.println(i+1 + ". " + list.get(i).viewProduct());
        }
    }

}
