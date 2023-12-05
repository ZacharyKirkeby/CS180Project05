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
 *
 * @version December 1, 2023
 *
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
            // throw a thread creator here for multi threading
            System.out.println("Client connected!");
            boolean logOrRegistration = false;
            boolean isLoggedIn = false;
            do {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    String username;
                    String password;
                    while(socket.isConnected()) {
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
                                switch(temp[1]) {
                                    case "createProduct":
                                        bool = Seller.createProduct(temp[2], temp[3], temp[4],
                                                Double.parseDouble(temp[5]), Integer.parseInt(temp[6]), temp[7]);
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
                                        Seller.readProductsFromCSV(temp[2], temp[3]);
                                        break;
                                    case "triggerSale":
                                        Store store = Seller.whichStore(temp[2]);
                                        bool = store.triggerSale(temp[3], Double.parseDouble(temp[4]), Integer.parseInt(temp[5]));
                                        writer.println(bool);
                                        writer.flush();
                                        break;
                                    case "triggerOrderCap":
                                        store = Seller.whichStore(temp[2]);
                                        bool = store.triggerOrderCap(temp[3], Integer.parseInt(temp[4]));
                                        writer.println(bool);
                                        writer.flush();
                                        break;
                                    default:
                                        writer.println(false);
                                        writer.flush();
                                }
                                break;
                            case "sellerStatistics":
                                switch(temp[2]) {
                                    case "getCustomersAndPurchases":
                                        writer.println(Seller.getCustomersAndPurchases(temp[3],
                                                temp[4], Boolean.parseBoolean(temp[5])));
                                        writer.flush();
                                        break;
                                    case "getProductSales":
                                        writer.println(Seller.getProductSales(temp[3],
                                                temp[4], Boolean.parseBoolean(temp[5])));
                                        writer.flush();
                                        break;
                                    case "getShoppingCart":
                                        writer.println(Seller.getShoppingCartProducts(temp[3]));
                                        writer.flush();
                                        break;
                                    case "writeProductsToCSV":
                                        bool = Seller.writeProductsToCSV(temp[3], temp[4]);
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
                                bool = Seller.deleteStore(temp[2], temp[3]);
                                writer.println(bool);
                                writer.flush();
                                break;
                            case "viewCustomerReviews":
                                writer.println(Seller.viewCustomerReviews(temp[2], temp[3]));
                                writer.flush();
                                break;
                            case "manageAccount":
                                switch(temp[2]) {
                                    case "changeUsername":
                                        bool = Account.changeUsername(temp[3], temp[4]);
                                        writer.println(bool);
                                        writer.flush();
                                        break;
                                    case "changePassword":
                                        bool = Account.changePassword(temp[3], temp[4], temp[5]);
                                        writer.println(bool);
                                        writer.flush();
                                        break;
                                    case "changeRole":
                                        bool = Account.changeRole(temp[3], temp[4], temp[5]);
                                        writer.println(bool);
                                        writer.flush();
                                        break;
                                    case "deleteAccount":
                                        bool = Account.deleteAccount(temp[3], temp[4]);
                                        writer.println(bool);
                                        writer.flush();
                                        break;
                                }
                                break;
                            case "logout":
                                isLoggedIn = false;
                                break;
                            // and now for the customer experience    
                        }
                    }







                } catch(Exception e) {
                    e.printStackTrace();
                }


            } while (!(socket == null) && socket.isConnected());


        }

    }
}
