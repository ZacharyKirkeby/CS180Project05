package src;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * MarketServer
 * creates server
 * runs until program is manually shut down
 * handles all data calls
 *
 * @author Zachary Kirkeby, 05
 * @version December 1, 2023
 */
public class MarketServer {

    public static void main(String[] args) throws IOException {
        startServer(4242); //server starts immediately

    }

    public static void startServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            System.out.println("Waiting for the client to connect...");
            Socket socket;
            socket = serverSocket.accept();

            ClientThread clientThread = new ClientThread(socket);
            new Thread(clientThread).start();
        }
    }
}

/**
 * A class that handles user interaction in each thread.
 *
 * @author William Hyun
 * @version December 8, 2023
 */
class ClientThread implements Runnable {

    private Socket threadSocket;

    public ClientThread(Socket socket) {
        threadSocket = socket;
    }

    @Override
    public void run() {
        // throw a thread creator here for multi threading
        System.out.println("Client connected!");
        boolean logOrRegistration = false;
        boolean isLoggedIn = false;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(threadSocket.getInputStream()));
            writer = new PrintWriter(threadSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        do {
            try {
                String username;
                String password;
                String message = reader.readLine();
                String[] temp = message.split(",");
                message = temp[0];
                boolean bool = false;
                // format of method,parameter1,parameter2
                switch (message) {
                    case "login":
                        boolean loggedIn = Account.login(temp[1], temp[2]);
                        writer.println(loggedIn);
                        writer.flush();
                        if (loggedIn) {
                            String check1 = reader.readLine();
                            if (check1 != null) {
                                writer.println(Account.getUsername(check1));
                                writer.flush();
                                bool = Account.getRole(check1).equalsIgnoreCase("Seller");
                                writer.println(bool);
                                writer.flush();
                            }
                        }
                        break;
                    case "register":
                        boolean success = Account.createAccount(temp[1], temp[2], temp[3], temp[4]);
                        writer.println(success);
                        writer.flush();
                        break;
                    case "createStore":
                        bool = Seller.createStore(temp[1], temp[2], temp[3]);
                        writer.println(bool);
                        writer.flush();
                        break;
                    case "sellermodificationchoices":
                        switch (temp[1]) {
                            case "createProduct":
                                bool = Seller.createProduct(temp[2], temp[3], temp[4],
                                    Double.parseDouble(temp[5]), Integer.parseInt(temp[6]), temp[7]);
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "editProductDescription":
                                bool = Seller.editProductDescription(temp[2], temp[3], temp[4], temp[5]);
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "editProductPrice":
                                bool = Seller.editProductPrice(temp[2],
                                    temp[3], Double.parseDouble(temp[4]), temp[5]);
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "editProductQuantity":
                                bool = Seller.editProductQuantity(temp[2], temp[3],
                                    Integer.parseInt(temp[4]), temp[5]);
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "deleteProduct":
                                bool = Seller.deleteProduct(temp[2], temp[3], temp[4]);
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "readProductsFromCSV":
                                bool = Seller.readProductsFromCSV(temp[2], temp[3]);
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "triggerSale":
                                Store store = Seller.whichStore(temp[2]);
                                if (store != null) {
                                    bool = store.triggerSale(temp[3], Double.parseDouble(temp[4]),
                                            Integer.parseInt(temp[5]));
                                } else {
                                    bool = false;
                                }
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "triggerOrderCap":
                                store = Seller.whichStore(temp[2]);
                                if (store != null) {
                                    bool = store.triggerOrderCap(temp[3], Integer.parseInt(temp[4]));
                                } else {
                                    bool = false;
                                }
                                writer.println(bool);
                                writer.flush();
                                break;
                            default:
                                writer.println(false);
                                writer.flush();
                        }
                        break;
                    case "sellerStatistics":
                        switch (temp[1]) {
                            case "getCustomersAndPurchases":
                                writer.println(Seller.getCustomersAndPurchases(temp[2],
                                    temp[3], Boolean.parseBoolean(temp[4])));
                                writer.flush();
                                break;
                            case "getProductSales":
                                if (Seller.validateProductSales(temp[2],
                                    temp[3])) {
                                    String productSales = Customer.getProductSales(temp[2],
                                        temp[3], Boolean.parseBoolean(temp[4]));
                                    writer.println(productSales);
                                    writer.flush();
                                } else {
                                    writer.println("None Found");
                                    writer.flush();
                                }
                                break;
                            case "getShoppingCart":
                                writer.println(Seller.getShoppingCartProducts(temp[2]));
                                writer.flush();
                                break;
                            case "writeProductsToCSV":
                                bool = Seller.writeProductsToCSV(temp[2], temp[3]);
                                writer.println(bool);
                                writer.flush();
                                break;
                            default:
                                writer.println(false);
                                writer.flush();
                                break;
                        }
                        break;
                    case "deleteStore":
                        bool = Seller.deleteStore(temp[1], temp[2]);
                        writer.println(bool);
                        writer.flush();
                        break;
                    case "viewCustomerReviews":
                        writer.println(Seller.viewCustomerReviews(temp[1], temp[2]));
                        writer.flush();
                        break;
                    case "manageAccount":
                        switch (temp[1]) {
                            case "changeUsername":
                                bool = Account.changeUsername(temp[2], temp[3]);
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "changePassword":
                                bool = Account.changePassword(temp[2], temp[3], temp[4]);
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "changeRole":
                                bool = Account.changeRole(temp[2], temp[3], temp[4]);
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "deleteAccount":
                                bool = Account.deleteAccount(temp[2], temp[3]);
                                writer.println(bool);
                                writer.flush();
                                break;
                        }
                        break;
                    case "logout":
                        isLoggedIn = false;
                        break;
                    // and now for the customer experience
                    case "searchByStore":
                        writer.println(Seller.searchByStore(temp[1]));
                        writer.flush();
                        break;
                    case "searchByProduct":
                        writer.println(Seller.searchByProduct(temp[1]));
                        writer.flush();
                        break;
                    case "searchByDescription":
                        writer.println(Seller.searchByDescription(temp[1]));
                        writer.flush();
                        break;
                    case "printProductAndStore":
                        Seller.readFromFile();
                        writer.println(Seller.printProductAndStores());
                        writer.flush();
                        break;
                    case "sortCheapest":
                        writer.println(Seller.sortCheapest());
                        writer.flush();
                        break;
                    case "sortExpensive":
                        writer.println(Seller.sortExpensive());
                        writer.flush();
                        break;
                    case "availability":
                        switch (temp[1]) {
                            case "highestQuant":
                                writer.println(Seller.highestQuant());
                                writer.flush();
                                break;
                            case "lowestQuant":
                                writer.println(Seller.lowestQuant());
                                writer.flush();
                                break;
                        }
                        break;
                    case "searchedStoreExists":
                        ArrayList<Store> stores = MarketPlace.getStores();
                        bool = Customer.searchedStoreExists(temp[1], stores);
                        writer.println(bool);
                        writer.flush();
                        break;
                    case "searchedProductExists":
                        stores = MarketPlace.getStores();
                        bool = Customer.searchedProductExists(temp[1], stores);
                        writer.println(bool);
                        writer.flush();
                        break;
                    case "shoppingCart":
                        switch (temp[1]) {
                            case "addToCart":
                                bool = Customer.addToCart(Account.getEmail(temp[2]), temp[3],
                                    temp[4], temp[5], Integer.parseInt(temp[6]));
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "changeCheckoutQuantity":
                                bool = Customer.addToCartChangeCheckoutQuantity(
                                    temp[2], temp[3], Integer.parseInt(temp[4]));
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "removeFromCart":
                                bool = Customer.removeFromCart(
                                    Account.getEmail(temp[2]),
                                    temp[3],
                                    temp[4], temp[5], 5);
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "buyProducts":
                                bool = Customer.buyProductsInShoppingCart(Account.getUsername(temp[2]));
                                boolean check = Customer.getShoppingCartofCustomer(temp[2]).isEmpty();
                                writer.println(bool);
                                writer.flush();
                                writer.println(check);
                                writer.flush();
                                break;
                            case "getShoppingCart":
                                String print = "";
                                print = Customer.getShoppingCartofCustomer(temp[2]);
                                writer.println(print);
                                writer.flush();
                                break;
                        }
                        break;
                    case "getPurchaseHistory":
                        bool = Customer.getPurchaseHistoryofCustomer(temp[1], temp[2]);
                        writer.println(bool);
                        writer.flush();
                        break;
                    case "leaveReview":
                        bool = Customer.leaveReview(temp[1],
                            temp[2], temp[3], Integer.parseInt(temp[4]), temp[5]);
                        writer.println(bool);
                        writer.flush();
                        break;
                    case "viewReviews":
                        writer.println(Customer.viewReviews(temp[1], temp[2]));
                        writer.flush();
                        break;
                    default:
                        writer.println(false);
                        writer.flush();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!(threadSocket == null) && threadSocket.isConnected());
    }
}
