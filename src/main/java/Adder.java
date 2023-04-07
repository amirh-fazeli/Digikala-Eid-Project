import java.util.ArrayList;
import java.util.Scanner;

public class Adder {
    Scanner scan=new Scanner(System.in);

    public void inputProduct(DigikalaService service,Seller seller){
        System.out.println("where does your product fit among out categories?");
        System.out.println("1.Electronics\n2.Home Decorations\n3.Groceries\n4.Sanitaries\n5.Tools\n6.Books\n7.Clothing" +
                "\n8.kids\n9.other");

        switch (Integer.parseInt(scan.nextLine())){
            case 1:
                System.out.println("what subcategory?");
                System.out.println("1.laptops\n2.phone\n3.external supplies\n4.other");


                switch (Integer.parseInt(scan.nextLine())){
                    case 1:
                        Laptop laptop=inputLaptop(seller);
                        service.addProduct(laptop);
                        seller.addProducts(laptop);

                    case 2:
                        Phone phone=inputPhone(seller);
                        service.addProduct(phone);
                        seller.addProducts(phone);
                    case 3:
                        ExternalSupplies ex=inputExternal(seller);
                        service.addProduct(ex);
                        seller.addProducts(ex);

                    case 4:
                        Electronics electronic=new Electronics();
                        setGenerals(seller,electronic);
                        setElectronic(electronic);
                        setOtherProperties(electronic);
                        service.addProduct(electronic);
                        seller.addProducts(electronic);
            }
                break;

            case 2:
                Decor decor=inputDecor(seller);
                service.addProduct(decor);
                seller.addProducts(decor);
                break;

            case 3:
                Grocery grocery=inputGrocery(seller);
                service.addProduct(grocery);
                seller.addProducts(grocery);
                break;

            case 4:
                Sanitary sanitary=inputSanitary(seller);
                service.addProduct(sanitary);
                seller.addProducts(sanitary);
                break;

            case 5:
                System.out.println("1.electrical\n2.non-electrical");

                switch (Integer.parseInt(scan.nextLine())){
                    case 1:
                        Etool etool=inputEtool(seller);
                        service.addProduct(etool);
                        seller.addProducts(etool);
                        break;

                    case 2:
                        Tool tool=new Tool();
                        setTool(tool);
                        setGenerals(seller,tool);
                        setOtherProperties(tool);
                        service.addProduct(tool);
                        seller.addProducts(tool);
                        break;
                }
                break;

            case 6:
                Book book = inputBook(seller);
                service.addProduct(book);
                seller.addProducts(book);
                break;

            case 7:
                Clothing clothing=inputClothing(seller);
                service.addProduct(clothing);
                seller.addProducts(clothing);
                break;

            case 8:
                Kid kid=inputKid(seller);
                service.addProduct(kid);
                seller.addProducts(kid);
                break;

            case 9:
                Product product=new Product();
                setGenerals(seller,product);
                setOtherProperties(product);
                service.addProduct(product);
                seller.addProducts(product);
                break;

        }
        System.out.println("your product successfully added");
    }

    public void setGenerals(Seller seller,Product product){
        System.out.println("name: ");
        product.setName(scan.nextLine());

        System.out.println("producer company: ");
        product.setCompany(scan.nextLine());

        System.out.println("price: ");
        product.setPrice(Integer.parseInt(scan.nextLine()));

        System.out.println("numbers available: ");
        product.setQuantity(Integer.parseInt(scan.nextLine()));

        product.setSeller(seller);
    }

    public void setOtherProperties(Product product){
        ArrayList<String> properties=new ArrayList<String>();

        System.out.println("other properties: ");
        System.out.println("(enter properties, when you're finished enter the word end)");

        while (true) {
            String property = scan.nextLine();

            if (!property.equals("end")) {
                properties.add(property);
            } else {
                break;
            }
        }
    }

    public void setElectronic(Electronics product){
        System.out.println("size: ");
        product.setSize(Integer.parseInt(scan.nextLine()));

        System.out.println("wight: ");
        product.setWeight(Integer.parseInt(scan.nextLine()));

        System.out.println("color: ");
        product.setColor(scan.nextLine());
    }

    public void setTool(Tool product){
        System.out.println("size: ");
        product.setSize(Integer.parseInt(scan.nextLine()));

        System.out.println("wight: ");
        product.setWeight(Integer.parseInt(scan.nextLine()));
    }

    public Laptop inputLaptop(Seller seller){
        Laptop laptop=new Laptop();

        setGenerals(seller,laptop);
        setElectronic(laptop);

        System.out.println("graphic card: ");
        laptop.setGpu(scan.nextLine());

        System.out.println("CPU: ");
        laptop.setCpu(scan.nextLine());

        System.out.println("RAM: ");
        laptop.setRam(Integer.parseInt(scan.nextLine()));

        System.out.println("ability to scan fingerprint: ");
        laptop.setFingerprint(scan.nextLine());

        setOtherProperties(laptop);

        return laptop;
    }

    public Phone inputPhone(Seller seller){
        Phone phone=new Phone();
        ArrayList<String> ports = new ArrayList<String>();

        setGenerals(seller,phone);
        setElectronic(phone);

        System.out.println("RAM: ");
        phone.setRam(Integer.parseInt(scan.nextLine()));

        System.out.println("camera: ");
        phone.setCamera(scan.nextLine());

        System.out.println("ports: ");
        System.out.println("(enter ports, when you're finished enter the word end)");

        while (true) {
            String port = scan.nextLine();

            if (!port.equals("end")) {
                ports.add(port);
            } else {
                break;
            }
        }

        phone.setPorts(ports);

        setOtherProperties(phone);

        return phone;
    }

    public ExternalSupplies inputExternal(Seller seller){
        ExternalSupplies external=new ExternalSupplies();

        setGenerals(seller,external);
        setElectronic(external);

        System.out.println("devices compatible with: ");
        external.setCompatibleDevices(scan.nextLine());

        setOtherProperties(external);

        return external;
    }

    public Decor inputDecor(Seller seller){
        Decor decor=new Decor();

        setGenerals(seller,decor);

        System.out.println("size: ");
        decor.setSize(Integer.parseInt(scan.nextLine()));

        System.out.println("wight: ");
        decor.setWeight(Integer.parseInt(scan.nextLine()));

        System.out.println("color: ");
        decor.setColor(scan.nextLine());

        setOtherProperties(decor);

        return decor;
    }

    public Grocery inputGrocery(Seller seller){
        Grocery grocery=new Grocery();

        setGenerals(seller,grocery);

        System.out.println("wight: ");
        grocery.setWeight(Integer.parseInt(scan.nextLine()));

        System.out.println("licence number: ");
        grocery.setLicenceNumber(Integer.parseInt(scan.nextLine()));

        setOtherProperties(grocery);

        return grocery;
    }

    public Sanitary inputSanitary(Seller seller){
        Sanitary sanitary=new Sanitary();
        ArrayList<String> ingredients=new ArrayList<String>();

        setGenerals(seller,sanitary);

        System.out.println("mass: ");
        sanitary.setMass(Integer.parseInt(scan.nextLine()));

        System.out.println("made in: ");
        sanitary.setCountry(scan.nextLine());

        System.out.println("ingredients: ");
        System.out.println("(enter ingredient, when you're finished enter the word end)");

        while (true) {
            String ingredient = scan.nextLine();

            if (!ingredient.equals("end")) {
                ingredients.add(ingredient);
            } else {
                break;
            }
        }
        sanitary.setIngredient(ingredients);

        setOtherProperties(sanitary);

        return sanitary;
    }

    public Etool inputEtool(Seller seller){
        Etool etool=new Etool();

        setGenerals(seller,etool);
        setTool(etool);

        System.out.println("voltage: ");
        etool.setVoltage(Integer.parseInt(scan.nextLine()));

        setOtherProperties(etool);

        return etool;
    }

    public Book inputBook(Seller seller){
        Book book=new Book();

        setGenerals(seller,book);

        System.out.println("publisher: ");
        book.setPublisher(scan.nextLine());

        System.out.println("author: ");
        book.setAuthor(scan.nextLine());

        System.out.println("pages: ");
        book.setPagesNumber(Integer.parseInt(scan.nextLine()));

        System.out.println("is it hardcover? ");
        book.setHardcover(scan.nextLine());

        setOtherProperties(book);

        return book;
    }

    public Clothing inputClothing(Seller seller){
        Clothing clothing=new Clothing();

        setGenerals(seller,clothing);

        System.out.println("type of clothing: ");
        clothing.setType(scan.nextLine());

        System.out.println("sex: ");
        clothing.setSex(scan.nextLine());

        System.out.println("size: ");
        clothing.setSize(Integer.parseInt(scan.nextLine()));

        setOtherProperties(clothing);

        return clothing;
    }

    public Kid inputKid(Seller seller){
        Kid kid=new Kid();

        setGenerals(seller,kid);

        System.out.println("age range: ");
        kid.setAge(scan.nextLine());

        setOtherProperties(kid);

        return kid;
    }

}
