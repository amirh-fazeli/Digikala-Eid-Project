import java.util.ArrayList;
import java.util.Scanner;

public class RequestHandling {

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

    public static void viewArray(ArrayList array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println(i + 1 + "." + array.get(i));
        }
    }
}
