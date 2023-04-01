import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    Scanner scan=new Scanner(System.in);

    public void order(DigikalaService service,User user){
        ArrayList<Product> result = service.searchProduct();

        if(result.size()==0){
            System.out.println("there was no result found");
        }

        else{
            System.out.println("there was " + result.size() + " results found");
            service.viewProductList(result);

            System.out.println("choose an item");
            Product choice=result.get(Integer.parseInt(scan.nextLine()) - 1);

            System.out.println("what do you want to do with this product?");
            System.out.println("1.add to cart\n2.view full details");

            switch (Integer.parseInt(scan.nextLine())){
                case 1:
                    user.addToCart(result.get(Integer.parseInt(scan.nextLine()) - 1),scan);
                    System.out.println(choice.getName() + " added to cart");
                    break;

                case 2:
                    System.out.println(result.get(Integer.parseInt(scan.nextLine()) - 1));
                    System.out.println("do you want to add this to your cart? yes/no");

                    if(scan.nextLine().equals("yes")){
                        user.addToCart(choice,scan);
                        System.out.println(choice.getName() + " added to cart");
                    }
                    break;
            }
        }
    }
}
