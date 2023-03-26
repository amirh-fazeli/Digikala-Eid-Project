import java.util.*;

public class Main {
    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu() {
        DigikalaService service = new DigikalaService();
        Adder adder=new Adder();

        Admin main=new Admin();
        main.setPassword("admin");
        main.setUsername("admin");
        service.addAdmin(main);

        User kh=new User();
        kh.setPassword("Amir1383");
        kh.setUsername("amir");
        service.addUser(kh);

        Phone pr=new Phone();
        pr.setName("galaxy phone");
        pr.setCompany("samsung");
        pr.setPrice(200);
        pr.setCamera("ziadddd");
        service.productList.add(pr);

        Scanner scan = new Scanner(System.in);

        String username, password;
        boolean k = true;

        while (true) {
            k = true;
            System.out.println("what program do you want to open? \n1.digikala (for admins) \n2.digikala (for sellers)" +
                    "\n3.digikala");

            switch (Integer.parseInt(scan.nextLine())) {

                case 1:
                    if(adminLogIn(service)){
                        adminMenu(service);
                    }
                    break;

                case 2:
                    k = true;
                    while (k) {
                        System.out.println("1.sign up \n2.log in\n3.exit");

                        System.out.println("insert an number: ");

                        switch (Integer.parseInt(scan.nextLine())) {
                            case 1:
                                createSellerAccount(service);
                                break;

                            case 2:
                                Seller loggedIn = sellerLogIn(service);

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
                                User user = createUserAccount(service);

                                if (user != null) {
                                    service.addUser(user);
                                }
                                break;

                            case 2:
                                User loggedIn = userLogIn(service);

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
            System.out.println("what do you want to do? \n1.add admin \n2.user requests");
            System.out.println("3.seller requests \n4.remove a tv show \n5.exit");

            switch (Integer.parseInt(scan.nextLine())) {
                case 1:
                    createAdmin(service);
                    break;

                case 2:
                    while (true) {
                        decideUserRequest(service);

                        System.out.println("do you want to check another request? yes/no");

                        if(!scan.nextLine().equals("yes")){
                            break;
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        decideSellerRequest(service);

                        System.out.println("do you want to check another request? yes/no");

                        if(!scan.nextLine().equals("yes")){
                            break;
                        }
                    }
                    break;

                case 4:
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
            System.out.println("what do you want to do? \n1.add products \n2.user requests");
            System.out.println("3.seller requests \n4.remove a tv show \n5.exit");

            switch (Integer.parseInt(scan.nextLine())) {
                case 1:
                    adder.inputProduct(service,seller);
                    break;

                case 2:
                    while (true) {
                        decideUserRequest(service);

                        System.out.println("do you want to check another request? yes/no");

                        if(!scan.nextLine().equals("yes")){
                            break;
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        decideSellerRequest(service);

                        System.out.println("do you want to check another request? yes/no");

                        if(!scan.nextLine().equals("yes")){
                            break;
                        }
                    }
                    break;

                case 4:
                    break;

                case 5:
                    k=false;
                    break;
            }
        }
    }

    public static void userMenu(DigikalaService service,User user){
        boolean k = true;
        Scanner scan=new Scanner(System.in);

        while (k) {
            System.out.println("balance: " + user.getWallet());
            System.out.println("what do you want to do? \n1.add new request \n2.view your requests");
            System.out.println("3.search a product \n4.view cart \n5.exit");

            switch (Integer.parseInt(scan.nextLine())) {
                case 1:
                    writeUserRequest(service,user);
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
                    service.viewProductList(user.cart);
                    break;

                case 5:
                    k=false;
                    break;
            }
        }
    }

    public static User createUserAccount (DigikalaService service){
        User user = new User();
        while (true) {
            Scanner scan = new Scanner(System.in);

            System.out.println("insert your username:");
            String username = scan.nextLine();

            if (service.doesUserExist(username)) {
                System.out.println("this username already exists");
                System.out.println("do you want to try again? yes/no");
                String ch = scan.nextLine();

                if (!ch.equals("yes")) {
                    return null;
                }
            } else {
                user.setUsername(username);
                while (true) {
                    System.out.println("insert your password: ");
                    String password = scan.nextLine();
                    String passPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
                    if (!password.matches(passPattern)) {
                        System.out.println("your password should at least consist of eight characters," +
                                " one uppercase letter, one lowercase letter and one number");
                    } else {
                        System.out.println("re-enter your password");
                        if (password.equals(scan.nextLine())) {
                            user.setPassword(password);
                            System.out.println("you signed up successfully");
                            return user;
                        } else {
                            System.out.println("your entered passwords doesnt match");
                        }
                    }
                }
            }
        }
    }

    public static void createSellerAccount (DigikalaService service){
        Seller seller = new Seller();
        SellerRequest request=new SellerRequest();
        ArrayList<String> products=new ArrayList<String>();
        boolean k=true;

        while (k) {
            Scanner scan = new Scanner(System.in);

            System.out.println("insert your username:");
            String username = scan.nextLine();

            if (service.doesSellerExist(username)) {
                System.out.println("this username already has been submitted");
                System.out.println("do you want to try again? yes/no");
                String ch = scan.nextLine();

                if (!ch.equals("yes")) {
                    continue;
                }
            } else {
                seller.setUsername(username);
                while (true) {
                    System.out.println("insert your password: ");
                    String password = scan.nextLine();
                    String passPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
                    if (!password.matches(passPattern)) {
                        System.out.println("your password should at least consist of eight characters," +
                                " one uppercase letter, one lowercase letter and one number");
                    } else {
                        System.out.println("re-enter your password");
                        if (password.equals(scan.nextLine())) {
                            seller.setPassword(password);
                            request.setSender(seller);
                            seller.setRequest(request);

                            System.out.println("what kind of products do you intend to sell?");
                            System.out.println("(enter products, when you're finished enter the word end)");

                            while (true) {
                                String product = scan.nextLine();

                                if (!product.equals("end")) {
                                    products.add(product);
                                } else {
                                    break;
                                }
                            }

                            System.out.println("your request has been sent to the admin, after being verified you will" +
                                    " be able to log in");

                            request.setProducts(products);
                            service.addSellerRequest(request);
                            service.addSeller(seller);
                            k=false;
                            break;

                        } else {
                            System.out.println("your entered passwords doesnt match");
                        }
                    }
                }
            }
        }
    }


    public static User userLogIn(DigikalaService service){
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("username: ");
            String username = scan.nextLine();
            if (service.doesUserExist(username)) {
                System.out.println("password: ");
                String password = scan.nextLine();

                if (service.userPasswordCheck(username, password)) {
                    return service.searchUser(username);
                } else {
                    System.out.println("wrong password");

                    System.out.println("do you want to try again? yes/no");
                    String ch = scan.nextLine();

                    if (!ch.equals("yes")) {
                        return null;
                    }
                }
            } else {
                System.out.println("there is no such user with this username");
                System.out.println("do you want to try again? yes/no");
                String ch = scan.nextLine();

                if (!ch.equals("yes")) {
                    return null;
                }
            }
        }
    }

    public static Seller sellerLogIn(DigikalaService service){
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("username: ");
            String username = scan.nextLine();
            if (service.doesSellerExist(username)) {
                if(service.searchSeller(username).getRequest().getStatus().equals("approved")) {
                    System.out.println("password: ");
                    String password = scan.nextLine();

                    if (service.userPasswordCheck(username, password)) {
                        return service.searchSeller(username);
                    } else {
                        System.out.println("wrong password");

                        System.out.println("do you want to try again? yes/no");
                        String ch = scan.nextLine();

                        if (!ch.equals("yes")) {
                            return null;
                        }
                    }
                }

                else{
                    System.out.println("this username has not been approved by admin yet");
                    return null;
                }
            } else {
                System.out.println("there is no such seller with this username");
                System.out.println("do you want to try again? yes/no");
                String ch = scan.nextLine();

                if (!ch.equals("yes")) {
                    return null;
                }
            }
        }
    }



    public static void createAdmin(DigikalaService service){
        Admin admin = new Admin();
        boolean k=true;
        while (k) {
            Scanner scan = new Scanner(System.in);

            System.out.println("insert username:");
            String username = scan.nextLine();

            if (service.doesAdminExist(username)) {
                System.out.println("an admin with this username already exists");
                System.out.println("do you want to try again? yes/no");
                String ch = scan.nextLine();

                if (!ch.equals("yes")) {
                    break;
                }
            } else {
                admin.setUsername(username);
                while (true) {
                    System.out.println("insert password: ");
                    String password = scan.nextLine();
                    String passPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
                    if (!password.matches(passPattern)) {
                        System.out.println("the password should at least consists of eight characters," +
                                " one uppercase letter, one lowercase letter and one number");
                    } else {
                        System.out.println("re-enter password: ");
                        if (password.equals(scan.nextLine())) {
                            admin.setPassword(password);
                            System.out.println(admin.getUsername() + " signed up successfully");
                            service.addAdmin(admin);
                            k=false;
                            break;
                        } else {
                            System.out.println("entered passwords doesnt match");
                        }
                    }
                }
            }
        }
    }

    public static void writeUserRequest(DigikalaService service,User user){
        Scanner scan=new Scanner(System.in);

        UserRequest request=new UserRequest();

        request.setSender(user);

        System.out.println("how much are you requesting? ");
        request.setAmount(Integer.parseInt(scan.nextLine()));

        user.addRequest(request);
        service.addUserRequest(request);

        System.out.println("your request for " + request.getAmount() + " submitted");
    }

    public static void decideUserRequest(DigikalaService service){
        Scanner scan=new Scanner(System.in);
        if(service.userRequestList.size()>0) {
            viewArray(service.userRequestList);

            System.out.println("choose a request: ");
            UserRequest choice = service.userRequestList.get(Integer.parseInt(scan.nextLine()) - 1);

            System.out.println("what do you want to do with this request?\n1.accept\n2.reject\n3.go back");

            switch (Integer.parseInt(scan.nextLine())) {
                case 1:
                    choice.getSender().setWallet(choice.getSender().getWallet() + choice.getAmount());
                    service.userRequestList.remove(choice);
                    choice.getSender().requests.remove(choice);
                    choice.getSender().addNews("your request for " + choice.getAmount() + " dollars is approved by" +
                            " the admin, you're balance is now " + choice.getSender().getWallet());
                    System.out.println("you approved this request");
                    break;

                case 2:
                    choice.setStatus("rejected");
                    choice.getSender().requests.remove(choice);
                    choice.getSender().addNews("your request for " + choice.getAmount() + " dollars got rejected by" +
                            " the admin");
                    System.out.println("you rejected this request");
                    break;

                case 3:
                    break;
            }
        }

        else{
            System.out.println("there is no active user request at this moment");
        }
    }

    public static void decideSellerRequest(DigikalaService service){
        Scanner scan=new Scanner(System.in);
        if(service.sellerRequestList.size()>0) {
            viewArray(service.sellerRequestList);

            System.out.println("choose a request: ");
            SellerRequest choice = service.sellerRequestList.get(Integer.parseInt(scan.nextLine()) - 1);

            System.out.println("what do you want to do with this request?\n1.accept\n2.reject\n3.go back");

            switch (Integer.parseInt(scan.nextLine())) {
                case 1:
                    choice.setStatus("approved");
                    service.sellerRequestList.remove(choice);
                    System.out.println("you approved this request");
                    break;

                case 2:
                    System.out.println("you rejected this request");
                    service.sellerRequestList.remove(choice);
                    service.sellerList.remove(choice.getSender());
                    break;

                case 3:
                    break;
            }
        }

        else {
            System.out.println("there is no active seller requests at this moment");
        }
    }

    public static boolean adminLogIn(DigikalaService service){
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("username: ");
            String username = scan.nextLine();
            if (service.doesAdminExist(username)) {
                System.out.println("password: ");
                String password = scan.nextLine();

                if (service.adminPasswordCheck(username, password)) {
                    return true;
                } else {
                    System.out.println("wrong password");

                    System.out.println("do you want to try again? yes/no");
                    String ch = scan.nextLine();

                    if (!ch.equals("yes")) {
                        return false;
                    }
                }
            } else {
                System.out.println("there is no such admin with this username");
                System.out.println("do you want to try again? yes/no");
                String ch = scan.nextLine();

                if (!ch.equals("yes")) {
                    return false;
                }
            }
        }
    }

    public static void viewArray(ArrayList array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println(i + 1 + "." + array.get(i));
        }
    }
}
