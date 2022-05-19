import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Item(name, price, quantity, isOnSale, food, water, taste, greenhouse, plastic, biodegradability)

        Item potato = new Item("potato", 2.99, 28, randomBool(), 20, 9, 23, 5, 1, 40);
        Item broccoli = new Item("broccoli", 3.99, 42, randomBool(), 40, 5, 15, 6, 2, 34);
        Item cereal = new Item("cereal", 5.17, 30, randomBool(), 32, 8, 27, 12, 27, 2);
        Item chips = new Item("chips", .98, 20, randomBool(), 18, 2, 51, 29, 18, 3);
        Item salt = new Item("salt", 1.92, 35, randomBool(), 17, 0, 26, 17, 22, 10);
        Item milk = new Item("milk", 4.21, 18, randomBool(), 23, 49, 32, 20, 29, 48);
        Item eggs = new Item("eggs", 8.39, 70, randomBool(), 27, 12, 29, 12, 1, 57);
        Item bread = new Item("bread", 7.91, 45, randomBool(), 60, 3, 34, 26, 9, 20);
        Item apple = new Item("apple", 2.19, 48, randomBool(), 44, 19, 19, 10, 2, 61);
        Item cheese = new Item("cheese", 7.89, 24, randomBool(), 21, 4, 31, 25, 12, 21);
        Item juice = new Item("juice", 4.28, 29, randomBool(), 2, 38, 30, 41, 19, 12);


        Shop shop = new Shop();
        shop.setShop(new Item[]{potato, broccoli, cereal, chips, salt, milk, eggs, bread, apple, cheese, juice});
        Item[] cart = new Item[]{};


        Scanner s = new Scanner(System.in);

        double balance = ((int)(Math.random()*2000) + 20)/10.;
        System.out.println("Your balance is $" + String.format("%.2f", balance));
        
        boolean quit = false;
        while (!quit) {
            printMenu();
            String response = s.nextLine().trim().toUpperCase();

            switch(response) {
                case "SHOP":
                    printStock(shop.getShop());
                    break;
                
                case "CART":
                    if (cart.length == 0) {
                        System.out.println("Your cart is empty!");
                        System.out.println();
                    } else {
                        printStock(cart);
                    }
                    break;

                case "ADD":
                    String itemToBuy = "";
                    int amtToBuy = 0;
                    try {
                        System.out.println();
                        System.out.print("Which item would you like to buy? ");
                        itemToBuy = s.nextLine();
                        System.out.println();
                        System.out.print("How many would you like to buy? ");
                        amtToBuy = Integer.parseInt(s.nextLine());
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println();
                        System.out.println("Please enter valid info");
                        System.out.println();
                        break;
                    }
                    

                    boolean found = false;

                    for (Item i : shop.getShop()) {
                        if (i.getName().toLowerCase().equals(itemToBuy.toLowerCase())) {
                            found = true;
                            if (i.getStock() > 0 && i.getStock() >= amtToBuy) {
                                i.setStock(i.getStock()-amtToBuy);
                                Item[] tempCart = new Item[cart.length+1];
                                for (int j = 0; j < cart.length; j++) {
                                    tempCart[j] = cart[j];
                                }
                                tempCart[cart.length] = new Item(itemToBuy, i.getPrice(), amtToBuy, i.getOnSale(), i.getFoodValue(), i.getWaterValue(), i.getTasteValue(), i.getGreenhouseGasValue(), i.getPlasticValue(), i.getBiodegradability());
                                cart = tempCart;
                                System.out.println("Added " + amtToBuy + " " + itemToBuy + " to cart");
                            } else {
                                System.out.println("Not enough in stock");
                                break;
                            }
                        }
                    }

                    if (!found) {
                        System.out.println("Item not found");
                    }
                    System.out.println();
                    break;
                
                case "REMOVE":
                    String itemToRemove = "";
                    int amtToRemove = 0;
                    try {
                        System.out.println();
                        System.out.print("Which item would you like to remove? ");
                        itemToRemove = s.nextLine();
                        System.out.print("How many would you like to remove? ");
                        amtToRemove = Integer.parseInt(s.nextLine());
                    } catch (Exception e) {
                        System.out.println();
                        System.out.println("Please enter valid info");
                        System.out.println();
                        break;
                    }

                    for (int i = 0; i < cart.length; i++) {
                        if (cart[i].getName().toLowerCase().equals(itemToRemove.toLowerCase())) {
                            if (cart[i].getStock() >= amtToRemove) {
                                cart[i].setStock(cart[i].getStock()-amtToRemove);
                                if (cart[i].getStock() == 0) {
                                    Item[] newCart = new Item[cart.length-1];
                                    boolean reachedDeletedItem = false;
                                    for (int j = 0; j < newCart.length; j++) {
                                        if (j == i) {
                                            reachedDeletedItem = true;
                                        }

                                        if (reachedDeletedItem) {
                                            newCart[j] = cart[j+1];
                                        } else {
                                            newCart[j] = cart[j];
                                        }
                                    }
                                    cart = newCart;
                                }
                                System.out.println();
                                System.out.println("Removed " + amtToRemove + " " + itemToRemove + " from your cart");
                                System.out.println();
                            }
                        }
                    }
                    break;
                
                case "CHECK":
                    System.out.print("Which item would you like to check? ");
                    String itemToCheck = s.nextLine();
                    int amtLeft = checkStock(shop.getShop(), itemToCheck);
                    if (amtLeft > 0) {
                        System.out.println(itemToCheck.toLowerCase() + " is in stock. There are " + amtLeft + " available");
                    } else if (amtLeft == 0) {
                        System.out.println("Your item was found but there are 0 left in stock");
                    } else {
                        System.out.println("Your item was not found");
                    }
                    System.out.println();
                    break;
                
                case "RESTOCK":
                    System.out.println("You wait for items to restock... ");
                    System.out.println();
                    for (Item i : shop.getShop()) {
                        i.setStock(i.getStock() + (int)(Math.random()*10));
                    }
                    shop.getShop()[(int)(Math.random()*shop.getShop().length)].promotionDeal();
                    break;
                
                case "COUPON":
                    if (cart.length == 0) {
                        System.out.println("Can't use a coupon when your  cart is empty!");
                        System.out.println();
                        break;
                    }
                    if (Math.random() < .15) {
                        int index = (int)(Math.random()*cart.length);
                        Coupon coupon = new Coupon(cart[index], 20);
                        Item newItem = coupon.getNewItem();
                        cart[index] = newItem;
                        System.out.println("Success! Your " + cart[index].getName() + " got 20% off");
                    } else {
                        System.out.println("The coupon didn't work since it was expired. :(");
                    }
                    System.out.println();
                    break;
                
                case "SALES":
                    int totalSales = 0;
                    for (Item i : shop.getShop()) {
                        if (i.getOnSale()) {
                            totalSales++;
                        }
                    }
                    System.out.println("There are a total number of " + totalSales + " items on sale right now");
                    break;
                
                case "SPENDING":
                    if (cart.length == 0){ 
                        System.out.println("You have no items in your cart. Add some items before displaying this");
                        System.out.println();
                        break;
                    }

                    double[] prices = new double[cart.length];
                    for (int i = 0; i < prices.length; i++) {
                        prices[i] = cart[i].getPrice()*cart[i].getStock();
                    }

                    double minPrice = Double.MAX_VALUE;
                    int minIndex = -1;
                    double maxPrice = Double.MIN_VALUE;
                    int maxIndex = -1;

                    for (int i = 0; i < prices.length; i++) {
                        if (prices[i] < minPrice) {
                            minPrice = prices[i];
                            minIndex = i;
                        }

                        if (prices[i] > maxPrice) {
                            maxPrice = prices[i];
                            maxIndex = i;
                        }
                    }
                    System.out.println();
                    System.out.println("You are spending the least money on " + cart[minIndex].getName() + " for a total of $" + String.format("%.2f", minPrice));
                    System.out.println();
                    System.out.println("You are spending the money money on " + cart[maxIndex].getName() + " for a total of $" + String.format("%.2f", maxPrice));
                    System.out.println();
                    break;
                
                case "CHECKOUT":
                    double total = 0.;
                    int totalItems = 0;
                    int totalFood = 0;
                    int totalWater = 0;
                    int totalTaste = 0;
                    int totalGreenhouse = 0;
                    int totalPlastic = 0;
                    int totalBiodegradability = 0;
                    for (Item i : cart) {
                        total += i.getPrice()*i.getStock();
                        totalItems += i.getStock();
                        totalFood += i.getFoodValue();
                        totalWater += i.getWaterValue();
                        totalTaste += i.getTasteValue();
                        totalGreenhouse += i.getGreenhouseGasValue();
                        totalPlastic += i.getPlasticValue();
                        totalBiodegradability += i.getBiodegradability();
                    }

                    if (total > balance) {
                        System.out.println("You don't have enough money. Your balance is $" + String.format("%.2f", balance) + " and your cart costs $" + String.format("%.2f", total));
                        break;
                    }

                    System.out.println("You checked out " + totalItems + " items for a total of $" + total);

                    if (totalFood < 100) {
                        System.out.println("You didn't meet your food requirement and starved this week. You were only at " + totalFood + "% the required amount");
                    } else {
                        System.out.println();
                        System.out.println("You met your food requirement for the week :)");
                        System.out.println();
                    }

                    if (totalWater < 100) {
                        System.out.println("You didn't meet your water requirement and ended up dehydrated this week. You were only at " + totalWater + "% the required amount");
                        System.out.println();
                    } else {
                        System.out.println("You met your water requirement for the week :)");
                        System.out.println();
                    }

                    if (totalTaste < 100) {
                        System.out.println("You didn't get enough good tasting food this week so you decided to eat school lunch instead and wasted money. You were only at " + totalTaste + "% the required amount");
                        System.out.println();
                    } else {
                        System.out.println("You bought good tasting food this week. Nice job. :)");
                        System.out.println();
                    }

                    boolean envFriendly = true;
                    if (totalGreenhouse > 100) {
                        System.out.println("You bought too many products that produce greenhouse gas emissions. Not cool bro");
                        envFriendly = false;
                        System.out.println();
                    }
                    
                    if (totalPlastic > 100) {
                        System.out.println("You bought too many plastic products. Bruh moment");
                        envFriendly = false;
                        System.out.println();
                    }

                    if (totalBiodegradability < 100) {
                        System.out.println("You bought too many products that aren't biodegradable. yikes");
                        envFriendly = false;
                        System.out.println();
                    }

                    if (envFriendly) {
                        System.out.println("Your items are environmentally friendly. Nice!");
                    }

                    quit = true;
                    break;
                
                default:
                    System.out.println("Please enter a valid option");
                    System.out.println();
                    break;
            }
        }
        s.close();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("SHOP     | display item shop");
        System.out.println("CART     | display your cart");
        System.out.println("ADD      | add an item to cart");
        System.out.println("REMOVE   | remove an item from cart");
        System.out.println("CHECK    | check if an item is in stock");
        System.out.println("RESTOCK  | wait for the shop to restock");
        System.out.println("COUPON   | try your luck with a coupon deal");
        System.out.println("SALES    | check how many items are on sale");
        System.out.println("SPENDING | displays what you are spending the least/most on");
        System.out.println("CHECKOUT | checkout your cart");
        System.out.println();
        System.out.println();
        System.out.print("Select an option: ");
    }

    public static void printStock(Item[] stock) {
        System.out.println("Item        Price               Stock");
        int i = 0;
        while (i < stock.length) {
            System.out.println(stock[i]);
            i++;
        }
        System.out.println();
    }

    public static void printCart(Item[] cart) {
        System.out.println("Item        Price               Stock");
        double total = 0;
        for (Item i : cart) {
            System.out.println(i);
            total += i.getPrice()*i.getStock();
        }
        System.out.println("------------------------------------");
        System.out.println("TOTAL:" + String.format("%-20s", total));
        System.out.println();
    }

    public static int checkStock(Item[] shop, String item) {
        for (Item i : shop) {
            if (item.toLowerCase().equals(i.getName().toLowerCase())) {
                return i.getStock();
            }
        }

        return -1;   
    }

    public static boolean randomBool() {
        return Math.random() < .5;
    }
}