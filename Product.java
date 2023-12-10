package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

/**
 * Project 04 -- Product.java
 * creates Product class
 * Handles all Product related tasks
 * and functions.
 *
 * @author Armaan Sayyad, 05
 * @version November 10, 2023
 */
public class Product {
    private String name; // name of the product
    private String description; // description of product
    private int stockQuantity; // quantity of the product left
    private int quantitySold; // quantity of product sold
    private double purchasePrice; // price of product

    // Optional Features Parameters
    private int saleCap; // Amount of product on sale
    private int saleSold; //Amount of product Sales during the sale
    private double salePrice; // Sale price

    private int orderCap; // Max number a customer can order

    /**
     * Creats a new Product Class
     *
     * @param name
     * @param purchasePrice
     * @param quantity
     */
    public Product(String name, double purchasePrice, int quantity) {
        this.name = name;
        this.stockQuantity = quantity;
        this.purchasePrice = purchasePrice;
        this.quantitySold = 0;
        this.orderCap = 0;
    }

    /**
     * Creats a new Product Class
     *
     * @param name
     * @param description
     * @param purchasePrice
     * @param quantity
     */
    public Product(String name, String description, double purchasePrice, int quantity) {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.stockQuantity = quantity;
        this.quantitySold = 0;
        this.salePrice = purchasePrice;
        this.saleCap = 0;
        this.orderCap = 0;
    }

    public Product(String name, String description, double purchasePrice, int quantity, double salePrice, int saleCap, int orderCap) {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.stockQuantity = quantity;
        this.quantitySold = 0;
        this.salePrice = salePrice;
        this.saleCap = saleCap;
        this.orderCap = orderCap;
    }

    /**
     * Getter for the Product's quantity sold
     *
     * @return quantitySold
     */
    public int getQuantitySold() {
        return quantitySold;
    }

    /**
     * Setter for the Product's quantity sold
     *
     * @param quantitySold
     */
    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    /**
     * Getter for the Product's Name
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the Product's Name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the Product's description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the Product's description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the Product's stock quantity
     *
     * @return stockQuantity
     */
    public int getStockQuantity() {
        if ((this.orderCap > 0) && (orderCap < stockQuantity)) {
            return orderCap;
        }
        if (this.getOnSale()) {
            return saleCap;
        }
        return stockQuantity;
    }

    /**
     * Setter for the Product's stockQuantity
     *
     * @param stockQuantity
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * @param quantity
     * @return true if success in buying product false if not
     */
    public boolean buyProduct(int quantity) {
        if (this.getOnSale()) {
            if (quantity <= this.saleCap && (quantity <= this.orderCap || (this.orderCap == 0))) {
                this.stockQuantity -= quantity;
                this.quantitySold += quantity;
                this.saleSold += quantity;
                this.saleCap -= quantity;
            } else {
                return false;
            }
        } else {
            if (quantity <= stockQuantity && (quantity <= this.orderCap || (this.orderCap == 0))) {
                this.stockQuantity -= quantity;
                this.quantitySold += quantity;
            } else {
                return false;
            }
        }
        Seller.writeToFile();
        return true;
    }

    /**
     * Getter for the Product's purchase price
     *
     * @return purchasePrice
     */
    public double getPurchasePrice() {
        if (this.getOnSale()) {
            return salePrice;
        } else {
            return purchasePrice;
        }
    }

    /**
     * Setter for the Product's purchase price
     *
     * @param purchasePrice
     */
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * Sorting Algorithm that sorts Products Alphabetically
     *
     * @param products (ArrayList<Product></>)
     * @return ArrayList<Product></>
     */
    public static ArrayList<Product> sortAlphabetically(ArrayList<Product> products) {
        for (int i = 0; i < products.size() - 1; i++) { //parses through the list
            for (int j = i + 1; j < products.size(); j++) { //takes the next element
                if (products.get(i).getName().compareTo(products.get(j).getName()) > 0) { //compares the two elements
                    Collections.swap(products, i, j); // makes the necessary switch
                }
            }
        }
        return products;
    }

    /**
     * Sorting Algorithm that sorts Products from Cheapest to most Expensive
     *
     * @param products (ArrayList<Product></>)
     * @return ArrayList<Product> </>
     */
    public static ArrayList<Product> sortByCheapest(ArrayList<Product> products) {
        for (int i = 0; i < products.size(); i++) { //parses through the list of products
            for (int j = i + 1; j < products.size(); j++) { //takes each element of the list of products
                if (products.get(i).getPurchasePrice() > products.get(j).getPurchasePrice()) { //compares the
                    // purchase price of two products
                    Collections.swap(products, i, j); //swaps the products if one lesser than the other
                }
            }
        }
        return products;
    }

    /**
     * get revenue
     *
     * @return
     */
    public double getRevenue() {
        int temp = quantitySold - saleSold;
        return salePrice * saleSold + (purchasePrice * temp);
    }

    /**
     * Reads the products from a file and adds them to an ArrayList
     *
     * @param fileName (String)
     */
    public static void readProductFile(String fileName) {
        ArrayList<String> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); //reads the first line of the file
            while (line != null) {
                products.add(line + ";"); //adds this line to the ArrayList
                line = br.readLine();
            }
            products.remove(products.size() - 1); //removes the tailing semicolon
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Optional Feature
     * creates a sale
     * sale cannot exceed stock available
     * price cannot be negative
     *
     * @param salePrice
     * @param saleCap
     */
    public boolean startSale(double salePrice, int saleCap) {
        if (salePrice <= 0 || saleCap < 0) {
            return false;
        } else {
            if (saleCap > this.stockQuantity) {
                return false;
            } else {
                this.saleCap = saleCap;
                this.salePrice = salePrice;
            }
        }
        Seller.writeToFile();
        return true;
    }

    /**
     * Ends sale
     *
     * @return
     */
    public boolean endSale() {
        saleCap = 0;
        return true;
    }

    /**
     * Returns sale cap
     *
     * @return
     */
    public int getSaleCap() {
        return saleCap;
    }

    /**
     * Gets sale price
     *
     * @return
     */
    public double getSalePrice() {
        return salePrice;
    }


    /**
     * Sets order capacity for each order
     * makes sure it is greater than 0
     *
     * @param cap
     */
    public boolean setCap(int cap) {
        if (cap >= 0) {
            this.orderCap = cap;
        } else {
            return false;
        }
        Seller.writeToFile();
        return true;
    }

    /**
     * Gets order cap
     * makes sure it is greater than 0
     *
     * @return orderCap
     */
    public int getOrderCap() {
        return orderCap;
    }

    /**
     * Gets onSale
     *
     * @return
     */
    public boolean getOnSale() {
        if (saleCap > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A toString for the Product Class
     */
    @Override
    public String toString() {
        return name + "," + description + "," + purchasePrice + "," + stockQuantity + "," +
               salePrice + "," + saleCap + "," + orderCap;
    }
}
