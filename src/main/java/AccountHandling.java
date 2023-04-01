import java.util.ArrayList;
import java.util.Scanner;

public class AccountHandling {
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

                    if (service.sellerPasswordCheck(username, password)) {
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

    public static void writeOrderRequest(DigikalaService service,User user,Scanner scan){
        OrderRequest req=new OrderRequest();

        if(user.getAddresses().size()>0) {
            viewArray(user.getAddresses());
            System.out.println("choose an address");
            req.setAddress(user.getAddresses().get(Integer.parseInt(scan.nextLine()) - 1));
            req.setCart(user.cart);
            req.setUser(user);
            service.orderRequestList.add(req);
            System.out.println("your request to purchase this cart sent to admin");
        }

        else{
            System.out.println("you should first add an address to your account");
        }
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
                    service.userRequestList.remove(choice);
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

    public static void decideSellerRequest(DigikalaService service) {
        Scanner scan = new Scanner(System.in);
        if (service.sellerRequestList.size() > 0) {
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
            System.out.println("there is no active seller request at this moment");
        }
    }

    public static void decideOrderRequest(DigikalaService service,Scanner scan){
        if(service.orderRequestList.size()>0){
            viewArray(service.orderRequestList);
            System.out.println("choose a request");
            OrderRequest ch=service.orderRequestList.get(Integer.parseInt(scan.nextLine()) - 1);
            System.out.println("what do you want to do with this request?\n1.accept\n2.reject\n3.view products");

            switch (Integer.parseInt(scan.nextLine())){
                case 1:
                    ch.setStatus("accepted");
                    ch.getUser().purchaseCart(service);
                    service.orderRequestList.remove(ch);
                    ch.getUser().news.add("your order was accepted by admin");
                    break;

                case 2:
                    ch.setStatus("rejected");
                    service.orderRequestList.remove(ch);
                    ch.getUser().news.add("your order was rejected by admin");
                    break;

                case 3:
                    ch.getUser().viewCart();
                    decideOrderRequest(service,scan);
                    break;
            }
        }

        else {
            System.out.println("there is no active order requests at this moment");
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

    public static void updateUser(User user){
        Scanner scan=new Scanner(System.in);
        System.out.println("1.email\n2.phone\n3.address\n4.password");

        switch (Integer.parseInt(scan.nextLine())){
            case 1:
                updateUserEmail(user,scan);
                break;

            case 2:
                updateUserPhone(user,scan);
                break;

            case 3:
                updateUserAddress(user,scan);
                break;

            case 4:
                updateUserPassword(user,scan);
                break;

        }
    }

    public static void updateUserEmail(User user,Scanner scan) {
        if (!user.getEmail().equals("")) {
            System.out.println("your current email is " + user.getEmail());
            System.out.println("do you want to change it? yes/no");

            if (scan.nextLine().equals("no")) {
                return;
            }
        }

        String mailPattern = "^[A-Za-z0-9_\\.]+@[A-Za-z0-9]+\\.[A-Za-z]{3}$";
        while (true) {
            System.out.println("insert your email");
            String email = scan.nextLine();

            if (!email.matches(mailPattern)) {
                System.out.println("please insert a valid email");
            }

            else{
                user.setEmail(email);
                System.out.println("your email changed successfully");
                break;
            }
        }
    }

    public static void updateUserPhone(User user,Scanner scan) {
        if (!user.getPhone().equals("")) {
            System.out.println("your current phone number is " + user.getPhone());
            System.out.println("do you want to change it? yes/no");

            if (scan.nextLine().equals("no")) {
                return;
            }
        }

        String phonePattern="^09[0-9]{9}|\\+989[0-9]{9}|00989[0-9]{9}$";
        while (true) {
            System.out.println("insert your phone number");
            String phone = scan.nextLine();

            if (!phone.matches(phonePattern)) {
                System.out.println("please insert a valid phone number");
            }

            else{
                user.setPhone(phone);
                System.out.println("your phone number changed successfully");
                break;
            }
        }
    }

    public static void updateUserAddress(User user,Scanner scan){
        System.out.println("1.add an address\n2.remove an address");

        switch (Integer.parseInt(scan.nextLine())){
            case 1:
                System.out.println("insert an address");
                user.addAddress(scan.nextLine());
                System.out.println("your new address added successfully");
                break;

            case 2:
                if (user.getAddresses().size()==0){
                    System.out.println("you have not add an address yet");
                    return;
                }

                viewArray(user.getAddresses());
                System.out.println("choose an address");
                user.getAddresses().remove(Integer.parseInt(scan.nextLine()) - 1);
                System.out.println("the selected address removed successfully");
                break;

        }
    }

    public static void updateUserPassword(User user,Scanner scan) {
        if (user.getEmail().equals("")) {
            System.out.println("you should first add an email address");
            return;
        }

        String passPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

        System.out.println("insert your email");
        if (scan.nextLine().equals(user.getEmail())) {
            while (true) {
                System.out.println("insert your new password");
                String password = scan.nextLine();
                if (!password.matches(passPattern)) {
                    System.out.println("your password should at least consist of eight characters," +
                            " one uppercase letter, one lowercase letter and one number");
                } else {
                    System.out.println("re-enter your password");
                    if (password.equals(scan.nextLine())) {
                        user.setPassword(password);
                        return;
                    } else {
                        System.out.println("your passwords doesnt match");
                    }
                }
            }
        }
        else {
            System.out.println("your inserted email is not submitted in your account info");
        }
    }

    public static void rate(DigikalaService service,User user,Scanner scan){
        if(user.allBought.size()>0) {
            service.viewProductList(user.allBought);
            System.out.println("choose an item");
            int ch = Integer.parseInt(scan.nextLine()) - 1;

            if (user.rated.containsKey(user.allBought.get(ch))) {
                System.out.println("you have already rated this product do you want to change it? yes/no");

                if (scan.nextLine().equals("no")) {
                    return;
                }
            }

            System.out.println("rate from 0 to 5");
            while (true) {
                int rate = Integer.parseInt(scan.nextLine());

                if (rate >= 0 && rate <= 5) {
                    user.rated.put(user.allBought.get(ch), rate);
                    user.allBought.get(ch).addRating(rate);
                    System.out.println("rated successfully");
                    return;
                } else {
                    System.out.println("your rating should be in range of 0 to 5");
                }
            }
        }

        else{
            System.out.println("you haven't bought any items yet");
        }
    }

    public static void viewArray(ArrayList array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println(i + 1 + "." + array.get(i));
        }
    }
}
