import java.util.*;

public class Main {
    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu() {
        DigikalaService service = new DigikalaService();
        Adder adder=new Adder();
        test(service);

        Scanner scan = new Scanner(System.in);

        String username, password;
        boolean k = true;

        while (true) {
            k = true;
            System.out.println("what program do you want to open? \n1.digikala (for admins) \n2.digikala (for sellers)" +
                    "\n3.digikala");

            switch (Integer.parseInt(scan.nextLine())) {

                case 1:
                    if(AccountHandling.adminLogIn(service)){
                        adminMenu(service);
                    }
                    break;

                case 2:
                    k = true;
                    while (k) {
                        System.out.println("1.sign up\n2.log in\n3.exit");

                        System.out.println("insert an number: ");

                        switch (Integer.parseInt(scan.nextLine())) {
                            case 1:
                                AccountHandling.createSellerAccount(service);
                                break;

                            case 2:
                                Seller loggedIn = AccountHandling.sellerLogIn(service);

                                if (loggedIn != null) {
                                    System.out.println("you successfully logged in");
                                    sellerMenu(service,loggedIn,adder);
                                }

                                break;

                            case 3:
                                k = false;
                                break;


                        }
                    }
                    break;

                case 3:
                    k = true;
                    while (k) {
                        System.out.println("1.sign up \n2.log in\n3.exit");

                        System.out.println("insert an number: ");

                        switch (Integer.parseInt(scan.nextLine())) {
                            case 1:
                                User user = AccountHandling.createUserAccount(service);

                                if (user != null) {
                                    service.addUser(user);
                                }
                                break;

                            case 2:
                                User loggedIn = AccountHandling.userLogIn(service);

                                if (loggedIn != null) {
                                    System.out.println("you successfully logged in");
                                    userMenu(service,loggedIn);
                                }

                                break;

                            case 3:
                                k = false;
                                break;


                        }
                    }
                    break;
            }
        }
    }

    public static void adminMenu(DigikalaService service){
        boolean k = true;
        Scanner scan=new Scanner(System.in);

        while (k) {
            System.out.println("what do you want to do? \n1.add admin \n2.user balance requests");
            System.out.println("3.seller requests \n4.purchase requests \n5.exit");

            switch (Integer.parseInt(scan.nextLine())) {
                case 1:
                    AccountHandling.createAdmin(service);
                    break;

                case 2:
                    while (true) {
                        RequestHandling.decideUserRequest(service);

                        System.out.println("do you want to check another request? yes/no");

                        if(!scan.nextLine().equals("yes")){
                            break;
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        RequestHandling.decideSellerRequest(service);

                        System.out.println("do you want to check another request? yes/no");

                        if(!scan.nextLine().equals("yes")){
                            break;
                        }
                    }
                    break;

                case 4:
                    RequestHandling.decideOrderRequest(service,scan);
                    break;

                case 5:
                    k=false;
                    break;
            }
        }
    }

    public static void sellerMenu(DigikalaService service,Seller seller,Adder adder){
        boolean k = true;
        Scanner scan=new Scanner(System.in);

        while (k) {
            System.out.println(seller.getUsername() + "                   balance: " + seller.getBalance());
            System.out.println("what do you want to do? \n1.add products \n2.view your products \n3.exit");

            switch (Integer.parseInt(scan.nextLine())) {
                case 1:
                    adder.inputProduct(service,seller);
                    break;

                case 2:
                    if(seller.products.size()==0){
                        System.out.println("you have not add any products yes");
                    }

                    else{
                        viewArray(seller.products);
                    }
                    break;

                case 3:
                    k=false;
                    break;
            }
        }
    }

    public static void userMenu(DigikalaService service, User user){
        boolean k = true;
        Scanner scan=new Scanner(System.in);

        while (k) {
            System.out.println( user.getUsername() + "        balance: " + user.getWallet());
            System.out.println("what do you want to do? \n1.add new request \n2.view your requests");
            System.out.println("3.search a product \n4.view cart \n5.update information\n6.rate a product\n7.news (" +
                    user.news.size() + ")" + "\n8.exit");

            switch (Integer.parseInt(scan.nextLine())) {
                case 1:
                    RequestHandling.writeUserRequest(service,user);
                    break;

                case 2:
                    if(user.requests.size()>0) {
                        viewArray(user.requests);
                    }

                    else{
                        System.out.println("you have no active requests at this moment");
                    }
                    break;

                case 3:
                    service.order(user);
                    break;

                case 4:
                    if(user.cart.size()>0) {
                        user.viewCart();

                        System.out.println("total price: " + user.cartPrice());
                        System.out.println("do you want to purchase this cart? yes/no");
                        if (scan.nextLine().equals("yes")) {
                            if (user.cartPrice() <= user.getWallet()) {
                                RequestHandling.writeOrderRequest(service, user, scan);
                            } else {
                                System.out.println("your balance does not suffice with this cart");
                            }
                        }
                    }

                    else{
                        System.out.println("you dont have any items in your cart");
                    }
                    break;

                case 5:
                    AccountHandling.updateUser(user);
                    break;

                case 6:
                    user.rateAndComment(service,scan);
                    break;

                case 7:
                    viewArray(user.news);
                    user.news.clear();
                    break;

                case 8:
                    k=false;
                    break;
            }
        }
    }

    public static void viewArray(ArrayList array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println(i + 1 + "." + array.get(i));
        }
    }

    public static void test(DigikalaService service){
        Admin main=new Admin();
        main.setPassword("admin");
        main.setUsername("admin");
        service.addAdmin(main);

        SellerRequest req=new SellerRequest();
        req.setStatus("approved");

        User user=new User();
        user.setPassword("Amir1383");
        user.setUsername("amir");
        user.setWallet(1000);
        user.addAddress("khkhkh");
        service.addUser(user);

        Seller seller1=new Seller();
        seller1.setPassword("Amir1383");
        seller1.setUsername("atefe");
        seller1.setRequest(req);
        service.addSeller(seller1);

        Seller seller2=new Seller();
        seller2.setPassword("Amir1383");
        seller2.setUsername("arefe");
        seller2.setRequest(req);
        service.addSeller(seller2);

        Product product1= new Product();
        product1.setName("galaxy phone");
        product1.setPrice(800);
        product1.setSeller(seller1);
        seller1.products.add(product1);
        product1.setQuantity(100);
        service.addProduct(product1);

        Product product2= new Product();
        product2.setName("iphone phone");
        product2.setPrice(900);
        product2.setSeller(seller1);
        product2.setQuantity(12);
        seller1.products.add(product2);
        service.addProduct(product2);

        Book book= new Book();
        book.setName("pride and prejudice");
        book.setPrice(12);
        book.setSeller(seller1);
        book.setHardcover("yes");
        book.setPagesNumber(320);
        book.setAuthor("jane austin");
        book.setPublisher("atash");
        book.setQuantity(12);
        seller1.products.add(book);
        service.addProduct(book);

    }
}
