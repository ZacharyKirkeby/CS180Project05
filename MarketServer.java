package CS180Project05;
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
                    String message = reader.readLine();
                    switch(message) {
                        case "login":
                            String username = reader.readLine();
                            String password = reader.readLine();
                            logOrRegistration = Account.login(username, password);
                            if (!logOrRegistration) {
                                System.out.println("Login Failed");
                                System.out.println("Try Again!");
                            } else if (logOrRegistration) {
                                isLoggedIn = true;
                                System.out.println("Login Successful!");
                            }
                        case "register":

                    }







                } catch(Exception e) {
                    e.printStackTrace();
                }


            } while (!(socket == null) && socket.isConnected());


        }

    }
}
