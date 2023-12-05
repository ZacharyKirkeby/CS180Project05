package src;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Project 04 -- MarketPlace.java
 * creates a Market to manage interactions and listings in a marketplace
 * Handles all MarketPlace related tasks
 * and functions.
 * Acts as the main interface of all the classes
 *
 * @author Armaan Sayyad, 05
 * @author Zachary Kirkeby, 05
 * @version November 10, 2023
 */
public class MarketPlace {
    private static String welcomePrompt = "Welcome to the Fruit Market!"; //
    private static String loginPrompt = "Would you like to Login or Register an Account? (Login / Register / " +
            "Exit)"; //
    private static String sellerChoices = " 1. Create Store \n" +
            " 2. Modify Store \n" +
            " 3. View Store Statistics \n" +
            " 4. Delete Store \n" +
            " 5. View Customer Reviews \n" +
            " 6. Manage Account \n" +
            " 7. Logout \n"; //
    private static String sellerModificationChoices = " 1. Create Product \n" +
            " 2. Change Product Price \n" +
            " 3. Change Product Quantity \n" +
            " 4. Delete Product \n" +
            " 5. Add products to Store from CSV \n" +
            " 6. Start Sale \n" +
            " 7. Add Purchase Limit \n" +
            " 8. Back \n"; //
    private static String accountChoices = " 1. Change Username \n" +
            " 2. Change Password \n" +
            " 3. Change Role \n" +
            " 4. Delete Account \n" +
            " 5. Back \n"; //
    private static String sellerStatisticsChoices = " 1. View Customer Purchases \n" +
            " 2. View Product Sales \n" +
            " 3. View Products in Shopping Cart \n" +
            " 4. View Products in Store as CSV file \n" +
            " 5. Back \n"; //
    private static String buyerprompt = " 1.  Search for a store \n" +
            " 2.  Search for a product \n" +
            " 3.  Search Product by Description \n" +
            " 4.  View All Products \n" +
            " 5.  Sort Products By Cheapest \n" +
            " 6.  Sort  Products By Most Expensive \n" +
            " 7.  Sort by Availability \n" +
            " 8.  Shopping Cart \n" +
            " 9.  Export Purchase History as file \n" +
            " 10. Leave Review \n" +
            " 11. View Product Reviews \n" +
            " 12. Manage Account \n" +
            " 13. Logout \n"; //
    private static String customerShoppingCartChoices = " 1. Add product(s) to cart \n" +
            " 2. Change Quantity of Product in Cart \n" +
            " 3. Remove product(s) from cart \n" +
            " 4. Buy products in cart \n" +
            " 5. View shopping cart \n" +
            " 6. Back\n"; //
    private static String availability = "1. Sort By Highest Stock \n" +
            "2. Sort By Low On Stock \n"; //
    private static String searchPrompt = "Enter search term: "; //
    private static ArrayList<Store> stores = new ArrayList<>(); //
    private static boolean isLoggedIn; //

    /**
     * Constructors for the MarketPlace
     *
     * @param stores (ArrayList<Stores></>)
     */
    public MarketPlace(ArrayList<Store> stores) {
        this.stores = stores;
        // some kind of logic tbd
    }

    /**
     * Getter for the stores in the MarketPlace
     *
     * @return stores
     */
    public static ArrayList<Store> getStores() {
        return stores;
    }

    /**
     * Main method for the Marketplace handles all the interactions
     * and interfaces between the classes
     *
     * @param args (String[])
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); //instantiates a scanner object to read terminal inputs
        do {
            boolean logOrRegistration = false;
            final String[] USERNAME = new String[1];

            /*
            LOGIN/REGISTRATION FRAME
             */
            JFrame loginRegisterFrame = new JFrame();
            JPanel loginRegisterPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            JButton openLoginButton = new JButton("Login");
            JButton openRegisterButton = new JButton("Register");
            loginRegisterPanel.add(openLoginButton);
            loginRegisterPanel.add(openRegisterButton);
            loginRegisterFrame.add(loginRegisterPanel);
            loginRegisterFrame.setTitle("Marketplace");
            loginRegisterFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            loginRegisterFrame.pack();
            loginRegisterFrame.setLocationRelativeTo(null);
            loginRegisterFrame.setVisible(true);

            JFrame loginFrame = new JFrame();
            JPanel loginPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            JLabel loginUsernameLabel = new JLabel("<html>Username<br>Or Email:</html>");
            loginPanel.add(loginUsernameLabel);
            JTextField loginUsernameOrEmailField = new JTextField();
            loginPanel.add(loginUsernameOrEmailField);
            JLabel loginPasswordLabel = new JLabel("Password:");
            loginPanel.add(loginPasswordLabel);
            JTextField loginPasswordField = new JTextField();
            loginPanel.add(loginPasswordField);
            JButton loginButton = new JButton("Login");
            loginPanel.add(new JLabel());
            loginPanel.add(loginButton);
            loginFrame.add(loginPanel);
            loginFrame.setTitle("Marketplace");
            loginFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(null);

            JFrame registerFrame = new JFrame();
            JPanel registerPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            JLabel registerEmailLabel = new JLabel("Email:");
            registerPanel.add(registerEmailLabel);
            JTextField registerEmailField = new JTextField();
            registerPanel.add(registerEmailField);
            registerPanel.add(new JLabel("Username:"));
            JTextField registerUsernameField = new JTextField();
            registerPanel.add(registerUsernameField);
            registerPanel.add(new JLabel("Password:"));
            JTextField registerPasswordField = new JTextField();
            registerPanel.add(registerPasswordField);
            registerPanel.add(new JLabel("Role:"));
            JComboBox registerRoleBox = new JComboBox(new String[]{"Buyer", "Seller"});
            registerPanel.add(registerRoleBox);
            registerPanel.add(new JLabel());
            JButton registerButton = new JButton("Register");
            registerPanel.add(registerButton);
            registerFrame.add(registerPanel);
            registerFrame.setTitle("Marketplace");
            registerFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            registerFrame.pack();
            registerFrame.setLocationRelativeTo(null);
            /*
            END LOGIN/REGISTRATION FRAME
             */

            /*
            SELLER OPTIONS FRAME
             */
            JFrame sellerOptionsFrame = new JFrame();
            JPanel sellerOptionsPanel = new JPanel(new GridLayout(0, 3, 4, 16));
            JButton createStoreButton = new JButton("Create Store");
            JButton modifyStoreButton = new JButton("Modify Store");
            JButton deleteStoreButton = new JButton("Delete Store");
            JButton sellerStatsButton = new JButton("Seller Stats");
            JButton customerReviewsButton = new JButton("View Customer Reviews");
            JButton sellerManageAccountButton = new JButton("Manage Account");
            JButton sellerLogoutButton = new JButton("Logout");
            sellerOptionsPanel.add(new JLabel());
            sellerOptionsPanel.add(new JLabel("Welcome to Seller Marketplace!"));
            sellerOptionsPanel.add(new JLabel());
            sellerOptionsPanel.add(createStoreButton);
            sellerOptionsPanel.add(modifyStoreButton);
            sellerOptionsPanel.add(deleteStoreButton);
            sellerOptionsPanel.add(sellerStatsButton);
            sellerOptionsPanel.add(customerReviewsButton);
            sellerOptionsPanel.add(sellerManageAccountButton);
            sellerOptionsPanel.add(new JLabel());
            sellerOptionsPanel.add(sellerLogoutButton);
            sellerOptionsFrame.add(sellerOptionsPanel);
            sellerOptionsFrame.setTitle("Seller Marketplace");
            sellerOptionsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            sellerOptionsFrame.pack();
            sellerOptionsFrame.setLocationRelativeTo(null);
            /*
            END SELLER OPTIONS FRAME
             */

            /*
            CREATE STORE FRAME
             */
            JFrame createStoreFrame = new JFrame();
            JPanel createStorePanel = new JPanel(new GridLayout(0, 2, 4, 16));
            JTextField createStoreNameField = new JTextField();
            JTextField createStoreLocationField = new JTextField();
            JButton createStoreEnterButton = new JButton("Create");
            createStorePanel.add(new JLabel("Store Name:"));
            createStorePanel.add(createStoreNameField);
            createStorePanel.add(new JLabel("Store Location:"));
            createStorePanel.add(createStoreLocationField);
            createStorePanel.add(new JLabel());
            createStorePanel.add(createStoreEnterButton);
            createStoreFrame.add(createStorePanel);
            createStoreFrame.setTitle("Create Store");
            createStoreFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            createStoreFrame.pack();
            createStoreFrame.setLocationRelativeTo(null);
            /*
            END CREATE STORE FRAME
             */

            /*
            MODIFY STORE FRAME
             */
            JFrame modifyStoreFrame = new JFrame();
            JPanel modifyStorePanel = new JPanel(new GridLayout(0, 3, 4, 16));
            JButton createProductButton = new JButton("Create Product");
            JButton editDescriptionButton = new JButton("Edit Description");
            JButton editPriceButton = new JButton("Edit Price");
            JButton editQuantityButton = new JButton("Edit Quantity");
            JButton deleteProductButton = new JButton("Delete Product");
            JButton addProductCSVButton = new JButton("Add From CSV");
            JButton startSaleButton = new JButton("Start Sale");
            JButton purchaseLimitButton = new JButton("Add Purchase Limit");
            modifyStorePanel.add(createProductButton);
            modifyStorePanel.add(editDescriptionButton);
            modifyStorePanel.add(editPriceButton);
            modifyStorePanel.add(editQuantityButton);
            modifyStorePanel.add(deleteProductButton);
            modifyStorePanel.add(addProductCSVButton);
            modifyStorePanel.add(startSaleButton);
            modifyStorePanel.add(purchaseLimitButton);
            modifyStoreFrame.add(modifyStorePanel);
            modifyStoreFrame.setTitle("Modify Store");
            modifyStoreFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            modifyStoreFrame.pack();
            modifyStoreFrame.setLocationRelativeTo(null);
            /*
            END MODIFY STORE FRAME
             */

            /*
            CREATE PRODUCT FRAME
             */
            JFrame createProductFrame = new JFrame();
            JPanel createProductPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            createProductPanel.add(new JLabel("Store Name:"));
            JTextField createProductStoreNameField = new JTextField();
            createProductPanel.add(createProductStoreNameField);
            createProductPanel.add(new JLabel("Product Name:"));
            JTextField createProductNameField = new JTextField();
            createProductPanel.add(createProductNameField);
            createProductPanel.add(new JLabel("Product Price:"));
            JTextField createProductPriceField = new JTextField();
            createProductPanel.add(createProductPriceField);
            createProductPanel.add(new JLabel("Product Quantity:"));
            JTextField createProductQuantityField = new JTextField();
            createProductPanel.add(createProductQuantityField);
            createProductPanel.add(new JLabel("Product Description:"));
            JTextField createProductDescriptionField = new JTextField();
            createProductPanel.add(createProductDescriptionField);
            createProductPanel.add(new JLabel());
            JButton createProduct = new JButton("Create");
            createProductPanel.add(createProduct);
            createProductFrame.add(createProductPanel);
            createProductFrame.setTitle("Create Product");
            createProductFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            createProductFrame.pack();
            createProductFrame.setLocationRelativeTo(null);
            createProduct.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (createProductStoreNameField.getText().isEmpty() ||
                                createProductNameField.getText().isEmpty() ||
                                createProductPriceField.getText().isEmpty() ||
                                createProductQuantityField.getText().isEmpty() ||
                                createProductDescriptionField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Enter All Fields",
                                    "Create Product", JOptionPane.ERROR_MESSAGE);
                        } else if ((Double.parseDouble(createProductPriceField.getText()) <= 0) ||
                                (Integer.parseInt(createProductQuantityField.getText()) < 1)) {
                            JOptionPane.showMessageDialog(null, "Enter Valid Numbers",
                                    "Create Product", JOptionPane.ERROR_MESSAGE);
                        } else if (Seller.createProduct(createProductStoreNameField.getText(),
                                createProductNameField.getText(), createProductDescriptionField.getText(),
                                Double.parseDouble(createProductPriceField.getText()),
                                Integer.parseInt(createProductQuantityField.getText()), USERNAME[0])) { // TODO: MOVE TO SERVER
                            JOptionPane.showMessageDialog(null, "Product Created",
                                    "Create Product", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Creation Failed",
                                    "Create Product", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Enter Valid Numbers",
                                "Create Product", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END CREATE PRODUCT FRAME
             */

            /*
            EDIT DESCRIPTION FRAME
             */
            JFrame editProductDescriptionFrame = new JFrame();
            JPanel editProductDescriptionPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            editProductDescriptionPanel.add(new JLabel("Store Name:"));
            JTextField editProductDescriptionStoreName = new JTextField();
            editProductDescriptionPanel.add(editProductDescriptionStoreName);
            editProductDescriptionPanel.add(new JLabel("Product Name:"));
            JTextField editProductDescriptionProductName = new JTextField();
            editProductDescriptionPanel.add(editProductDescriptionProductName);
            editProductDescriptionPanel.add(new JLabel("New Description:"));
            JTextField editProductDescriptionField = new JTextField();
            editProductDescriptionPanel.add(editProductDescriptionField);
            editProductDescriptionPanel.add(new JLabel());
            JButton editProductDescriptionButton = new JButton("Edit");
            editProductDescriptionPanel.add(editProductDescriptionButton);
            editProductDescriptionFrame.add(editProductDescriptionPanel);
            editProductDescriptionFrame.setTitle("Edit Product Description");
            editProductDescriptionFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            editProductDescriptionFrame.pack();
            editProductDescriptionFrame.setLocationRelativeTo(null);
            editProductDescriptionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (editProductDescriptionStoreName.getText().isEmpty() ||
                            editProductDescriptionProductName.getText().isEmpty() ||
                            editProductDescriptionField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Edit Product Description", JOptionPane.ERROR_MESSAGE);
                    } else if (Seller.editProductDescription(editProductDescriptionStoreName.getText(),
                            editProductDescriptionProductName.getText(),
                            editProductDescriptionField.getText(), USERNAME[0])) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Product Edited",
                                "Edit Product Description", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Edit Failed",
                                "Edit Product Description", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END EDIT DESCRIPTION FRAME
             */

            /*
            EDIT PRICE FRAME
             */
            JFrame editProductPriceFrame = new JFrame();
            JPanel editProductPricePanel = new JPanel(new GridLayout(0, 2, 4, 16));
            editProductPricePanel.add(new JLabel("Store Name:"));
            JTextField editProductPriceStoreName = new JTextField();
            editProductPricePanel.add(editProductPriceStoreName);
            editProductPricePanel.add(new JLabel("Product Name:"));
            JTextField editProductPriceProductName = new JTextField();
            editProductPricePanel.add(editProductPriceProductName);
            editProductPricePanel.add(new JLabel("New Price:"));
            JTextField editProductPriceField = new JTextField();
            editProductPricePanel.add(editProductPriceField);
            editProductPricePanel.add(new JLabel());
            JButton editProductPriceButton = new JButton("Edit");
            editProductPricePanel.add(editProductPriceButton);
            editProductPriceFrame.add(editProductPricePanel);
            editProductPriceFrame.setTitle("Edit Product Price");
            editProductPriceFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            editProductPriceFrame.pack();
            editProductPriceFrame.setLocationRelativeTo(null);
            editProductPriceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (Double.parseDouble(editProductPriceField.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Enter Valid Numbers",
                                    "Edit Product Price", JOptionPane.ERROR_MESSAGE);
                        } else if (editProductPriceStoreName.getText().isEmpty() ||
                                editProductPriceProductName.getText().isEmpty() ||
                                editProductPriceField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Enter All Fields",
                                    "Edit Product Price", JOptionPane.ERROR_MESSAGE);
                        } else if (Seller.editProductPrice(editProductPriceStoreName.getText(),
                                editProductPriceProductName.getText(),
                                Double.parseDouble(editProductPriceField.getText()), USERNAME[0])) { // TODO: MOVE TO SERVER
                            JOptionPane.showMessageDialog(null, "Product Edited",
                                    "Edit Product Price", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Edit Failed",
                                    "Edit Product Price", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Enter Valid Numbers",
                                "Edit Product Price", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END EDIT PRICE FRAME
             */

            /*
            EDIT QUANTITY FRAME
             */
            JFrame editProductQuantityFrame = new JFrame();
            JPanel editProductQuantityPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            editProductQuantityPanel.add(new JLabel("Store Name:"));
            JTextField editProductQuantityStoreName = new JTextField();
            editProductQuantityPanel.add(editProductQuantityStoreName);
            editProductQuantityPanel.add(new JLabel("Product Name:"));
            JTextField editProductQuantityProductName = new JTextField();
            editProductQuantityPanel.add(editProductQuantityProductName);
            editProductQuantityPanel.add(new JLabel("New Quantity:"));
            JTextField editProductQuantityField = new JTextField();
            editProductQuantityPanel.add(editProductQuantityField);
            editProductQuantityPanel.add(new JLabel());
            JButton editProductQuantityButton = new JButton("Edit");
            editProductQuantityPanel.add(editProductQuantityButton);
            editProductQuantityFrame.add(editProductQuantityPanel);
            editProductQuantityFrame.setTitle("Edit Product Quantity");
            editProductQuantityFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            editProductQuantityFrame.pack();
            editProductQuantityFrame.setLocationRelativeTo(null);
            editProductQuantityButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (editProductQuantityStoreName.getText().isEmpty() ||
                                editProductQuantityProductName.getText().isEmpty() ||
                                editProductQuantityField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Enter All Fields",
                                    "Edit Product Quantity", JOptionPane.ERROR_MESSAGE);
                        } else if (Integer.parseInt(editProductQuantityField.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Enter Valid Numbers",
                                    "Edit Product Quantity", JOptionPane.ERROR_MESSAGE);
                        } else if (Seller.editProductQuantity(editProductQuantityStoreName.getText(),
                                editProductQuantityProductName.getText(),
                                Integer.parseInt(editProductQuantityField.getText()), USERNAME[0])) {
                            JOptionPane.showMessageDialog(null, "Product Edited",
                                    "Edit Product Quantity", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Edit Failed",
                                    "Edit Product Quantity", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Enter Valid Numbers",
                                "Edit Product Quantity", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END EDIT QUANTITY FRAME
             */

            /*
            DELETE PRODUCT FRAME
             */
            JFrame deleteProductFrame = new JFrame();
            JPanel deleteProductPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            deleteProductPanel.add(new JLabel("Store Name:"));
            JTextField deleteProductStoreName = new JTextField();
            deleteProductPanel.add(deleteProductStoreName);
            deleteProductPanel.add(new JLabel("Product Name:"));
            JTextField deleteProductNameField = new JTextField();
            deleteProductPanel.add(deleteProductNameField);
            deleteProductPanel.add(new JLabel());
            JButton deleteProduct = new JButton("Delete");
            deleteProductPanel.add(deleteProduct);
            deleteProductFrame.add(deleteProductPanel);
            deleteProductFrame.setTitle("Delete Product");
            deleteProductFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            deleteProductFrame.pack();
            deleteProductFrame.setLocationRelativeTo(null);
            deleteProduct.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (deleteProductStoreName.getText().isEmpty() || deleteProductNameField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Delete Product", JOptionPane.ERROR_MESSAGE);
                    } else if (Seller.deleteProduct(deleteProductStoreName.getText(),
                            deleteProductNameField.getText(), USERNAME[0])) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Product Deleted",
                                "Delete Product", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Deletion Failed",
                                "Delete Product", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END DELETE PRODUCT FRAME
             */

            /*
            ADD PRODUCTS FROM CSV FRAME
             */
            JFrame addProductCSVFrame = new JFrame();
            JPanel addProductCSVPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            addProductCSVPanel.add(new JLabel("Store Name:"));
            JTextField addProductCSVStoreName = new JTextField();
            addProductCSVPanel.add(addProductCSVStoreName);
            addProductCSVPanel.add(new JLabel("CSV Path:"));
            JTextField addProductCSVPath = new JTextField();
            addProductCSVPanel.add(addProductCSVPath);
            addProductCSVPanel.add(new JLabel());
            JButton addProductCSV = new JButton("Add Products");
            addProductCSVPanel.add(addProductCSV);
            addProductCSVFrame.add(addProductCSVPanel);
            addProductCSVFrame.setTitle("Add Products From CSV");
            addProductCSVFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addProductCSVFrame.pack();
            addProductCSVFrame.setLocationRelativeTo(null);
            addProductCSV.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (addProductCSVStoreName.getText().isEmpty() || addProductCSVPath.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Add Products From CSV", JOptionPane.ERROR_MESSAGE);
                    } else if (Seller.readProductsFromCSV(addProductCSVStoreName.getText(),
                            addProductCSVPath.getText())) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Products Added",
                                "Add Products From CSV", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Addition Failed",
                                "Add Products From CSV", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END ADD PRODUCTS FROM CSV FRAME
             */

            /*
            START SALE FRAME
             */
            JFrame startSaleFrame = new JFrame();
            JPanel startSalePanel = new JPanel(new GridLayout(0, 2, 4, 16));
            startSalePanel.add(new JLabel("Store Name:"));
            JTextField saleStoreName = new JTextField();
            startSalePanel.add(saleStoreName);
            startSalePanel.add(new JLabel("Product Name:"));
            JTextField saleProductName = new JTextField();
            startSalePanel.add(saleProductName);
            startSalePanel.add(new JLabel("Sale Price:"));
            JTextField salePriceField = new JTextField();
            startSalePanel.add(salePriceField);
            startSalePanel.add(new JLabel("Sale Cap:"));
            JTextField saleCapField = new JTextField();
            startSalePanel.add(saleCapField);
            startSalePanel.add(new JLabel());
            JButton startSale = new JButton("Start");
            startSalePanel.add(startSale);
            startSaleFrame.add(startSalePanel);
            startSaleFrame.setTitle("Start Sale");
            startSaleFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            startSaleFrame.pack();
            startSaleFrame.setLocationRelativeTo(null);
            startSale.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Store store = Seller.whichStore(saleStoreName.getText()); // TODO: MOVE TO SERVER
                        if (saleStoreName.getText().isEmpty() || saleProductName.getText().isEmpty() ||
                                salePriceField.getText().isEmpty() || saleCapField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Enter All Fields",
                                    "Start Sale", JOptionPane.ERROR_MESSAGE);
                        } else if ((Double.parseDouble(salePriceField.getText()) <= 0)
                                || (Integer.parseInt(saleCapField.getText()) < 1)) {
                            JOptionPane.showMessageDialog(null, "Enter Valid Numbers",
                                    "Start Sale", JOptionPane.ERROR_MESSAGE);
                        } else if (store.triggerSale(saleProductName.getText(),
                                Double.parseDouble(salePriceField.getText()),
                                Integer.parseInt(saleCapField.getText()))) { // TODO: MOVE TO SERVER
                            JOptionPane.showMessageDialog(null, "Sale Created",
                                    "Start Sale", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Sale Creation Failed",
                                    "Start Sale", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Enter Valid Numbers",
                                "Start Sale", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END START SALE FRAME
             */

            /*
            PURCHASE LIMIT FRAME
             */
            JFrame purchaseLimitFrame = new JFrame();
            JPanel purchaseLimitPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            purchaseLimitPanel.add(new JLabel("Store Name:"));
            JTextField purchaseLimitStoreName = new JTextField();
            purchaseLimitPanel.add(purchaseLimitStoreName);
            purchaseLimitPanel.add(new JLabel("Product Name:"));
            JTextField purchaseLimitProductName = new JTextField();
            purchaseLimitPanel.add(purchaseLimitProductName);
            purchaseLimitPanel.add(new JLabel("Purchase Limit:"));
            JTextField purchaseLimitField = new JTextField();
            purchaseLimitPanel.add(purchaseLimitField);
            purchaseLimitPanel.add(new JLabel());
            JButton purchaseLimit = new JButton("Set Limit");
            purchaseLimitPanel.add(purchaseLimit);
            purchaseLimitFrame.add(purchaseLimitPanel);
            purchaseLimitFrame.setTitle("Set Purchase Limit");
            purchaseLimitFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            purchaseLimitFrame.pack();
            purchaseLimitFrame.setLocationRelativeTo(null);
            purchaseLimit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Store store = Seller.whichStore(purchaseLimitStoreName.getText()); // TODO: MOVE TO SERVER
                        if (purchaseLimitStoreName.getText().isEmpty() ||
                                purchaseLimitProductName.getText().isEmpty() ||
                                purchaseLimitField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Enter All Fields",
                                    "Set Purchase Limit", JOptionPane.ERROR_MESSAGE);
                        } else if (Integer.parseInt(purchaseLimitField.getText()) < 1) {
                            JOptionPane.showMessageDialog(null, "Enter Valid Numbers",
                                    "Set Purchase Limit", JOptionPane.ERROR_MESSAGE);
                        } else if (store.triggerOrderCap(purchaseLimitProductName.getText(),
                                Integer.parseInt(purchaseLimitField.getText()))) { // TODO: MOVE TO SERVER
                            JOptionPane.showMessageDialog(null, "Limit Set",
                                    "Set Purchase Limit", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Limit Failed",
                                    "Set Purchase Limit", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Enter Valid Numbers",
                                "Set Purchase Limit", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END PURCHASE LIMIT FRAME
             */

            /*
            DELETE STORE FRAME
             */
            JFrame deleteStoreFrame = new JFrame();
            JPanel deleteStorePanel = new JPanel(new GridLayout(0, 2, 4, 16));
            deleteStorePanel.add(new JLabel("Store Name:"));
            JTextField deleteStoreField = new JTextField();
            deleteStorePanel.add(deleteStoreField);
            deleteStorePanel.add(new JLabel());
            JButton deleteStore = new JButton("Delete");
            deleteStorePanel.add(deleteStore);
            deleteStoreFrame.add(deleteStorePanel);
            deleteStoreFrame.setTitle("Delete Store");
            deleteStoreFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            deleteStoreFrame.pack();
            deleteStoreFrame.setLocationRelativeTo(null);
            /*
            END DELETE STORE FRAME
             */

            /*
            SELLER STATS FRAME
             */
            JFrame sellerStatsFrame = new JFrame();
            JPanel sellerStatsPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            JButton customerPurchasesButton = new JButton("View Customer Purchases");
            sellerStatsPanel.add(customerPurchasesButton);
            JButton productSalesButton = new JButton("View Product Sales");
            sellerStatsPanel.add(productSalesButton);
            JButton cartProductsButton = new JButton("View Products In Cart");
            sellerStatsPanel.add(cartProductsButton);
            JButton getCSVButton = new JButton("Get Product CSV");
            sellerStatsPanel.add(getCSVButton);
            sellerStatsFrame.add(sellerStatsPanel);
            sellerStatsFrame.setTitle("Seller Stats");
            sellerStatsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            sellerStatsFrame.pack();
            sellerStatsFrame.setLocationRelativeTo(null);

            JFrame customerPurchasesFrame = new JFrame();
            JPanel customerPurchasesPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            customerPurchasesPanel.add(new JLabel("Store Name:"));
            JTextField customerPurchasesStoreName = new JTextField();
            customerPurchasesPanel.add(customerPurchasesStoreName);
            customerPurchasesPanel.add(new JLabel("Username:"));
            JTextField customerPurchasesUsername = new JTextField();
            customerPurchasesPanel.add(customerPurchasesUsername);
            customerPurchasesPanel.add(new JLabel("Sort:"));
            JComboBox customerPurchasesBox = new JComboBox(new String[]{"Yes", "No"});
            customerPurchasesPanel.add(customerPurchasesBox);
            customerPurchasesPanel.add(new JLabel());
            JButton customerPurchases = new JButton("View");
            customerPurchasesPanel.add(customerPurchases);
            customerPurchasesFrame.add(customerPurchasesPanel);
            customerPurchasesFrame.setTitle("View Customer Purchases");
            customerPurchasesFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            customerPurchasesFrame.pack();
            customerPurchasesFrame.setLocationRelativeTo(null);

            JFrame productSalesFrame = new JFrame();
            JPanel productSalesPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            productSalesPanel.add(new JLabel("Store Name:"));
            JTextField productSalesStoreName = new JTextField();
            productSalesPanel.add(productSalesStoreName);
            productSalesPanel.add(new JLabel("Username:"));
            JTextField productSalesUsername = new JTextField();
            productSalesPanel.add(productSalesUsername);
            productSalesPanel.add(new JLabel("Sort:"));
            JComboBox productSalesBox = new JComboBox(new String[]{"Yes", "No"});
            productSalesPanel.add(productSalesBox);
            productSalesPanel.add(new JLabel());
            JButton productSales = new JButton("View");
            productSalesPanel.add(productSales);
            productSalesFrame.add(productSalesPanel);
            productSalesFrame.setTitle("View Product Sales");
            productSalesFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            productSalesFrame.pack();
            productSalesFrame.setLocationRelativeTo(null);

            JFrame cartProductsFrame = new JFrame();
            JPanel cartProductsPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            cartProductsPanel.add(new JLabel("Username:"));
            JTextField cartProductsUsername = new JTextField();
            cartProductsPanel.add(cartProductsUsername);
            cartProductsPanel.add(new JLabel());
            JButton cartProducts = new JButton("View");
            cartProductsPanel.add(cartProducts);
            cartProductsFrame.add(cartProductsPanel);
            cartProductsFrame.setTitle("View Products In Cart");
            cartProductsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            cartProductsFrame.pack();
            cartProductsFrame.setLocationRelativeTo(null);

            JFrame getCSVFrame = new JFrame();
            JPanel getCSVPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            getCSVPanel.add(new JLabel("Store Name:"));
            JTextField getCSVStoreName = new JTextField();
            getCSVPanel.add(getCSVStoreName);
            getCSVPanel.add(new JLabel("File Path:"));
            JTextField getCSVPath = new JTextField();
            getCSVPanel.add(getCSVPath);
            getCSVPanel.add(new JLabel());
            JButton getCSV = new JButton("Write");
            getCSVPanel.add(getCSV);
            getCSVFrame.add(getCSVPanel);
            getCSVFrame.setTitle("Get Product CSV");
            getCSVFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            getCSVFrame.pack();
            getCSVFrame.setLocationRelativeTo(null);
            /*
            END SELLER STATS FRAME
             */

            /*
            CUSTOMER REVIEW FRAME
             */
            JFrame customerReviewsFrame = new JFrame();
            JPanel customerReviewsPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            customerReviewsPanel.add(new JLabel("Product Name (Leave Empty To View All):"));
            JTextField customerReviewsName = new JTextField();
            customerReviewsPanel.add(customerReviewsName);
            customerReviewsPanel.add(new JLabel());
            JButton customerReviews = new JButton("View");
            customerReviewsPanel.add(customerReviews);
            customerReviewsFrame.add(customerReviewsPanel);
            customerReviewsFrame.setTitle("View Customer Reviews");
            customerReviewsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            customerReviewsFrame.pack();
            customerReviewsFrame.setLocationRelativeTo(null);
            /*
            END CUSTOMER REVIEWS FRAME
             */
            /*
            CUSTOMER REVIEWS DISPLAY FRAME
             */
            JFrame sellerDisplayReviewsFrame = new JFrame();
            JPanel sellerDisplayReviewsPanel = new JPanel();
            /*
            END CUSTOMER REVIEWS DISPLAY FRAME
             */

            /*
            SELLER MANAGE ACCOUNT FRAME
             */
            JFrame sellerManageAccountFrame = new JFrame();
            JPanel sellerManageAccountPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            JButton sellerChangeUsernameButton = new JButton("Change Username");
            sellerManageAccountPanel.add(sellerChangeUsernameButton);
            JButton sellerChangePasswordButton = new JButton("Change Password");
            sellerManageAccountPanel.add(sellerChangePasswordButton);
            JButton sellerChangeRoleButton = new JButton("Change Role");
            sellerManageAccountPanel.add(sellerChangeRoleButton);
            JButton sellerDeleteAccountButton = new JButton("Delete Account");
            sellerManageAccountPanel.add(sellerDeleteAccountButton);
            sellerManageAccountFrame.add(sellerManageAccountPanel);
            sellerManageAccountFrame.setTitle("Manage Account");
            sellerManageAccountFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            sellerManageAccountFrame.pack();
            sellerManageAccountFrame.setLocationRelativeTo(null);

            JFrame sellerChangeUsernameFrame = new JFrame();
            JPanel sellerChangeUsernamePanel = new JPanel(new GridLayout(0, 2, 4, 16));
            sellerChangeUsernamePanel.add(new JLabel("New Username:"));
            JTextField sellerNewUsername = new JTextField();
            sellerChangeUsernamePanel.add(sellerNewUsername);
            sellerChangeUsernamePanel.add(new JLabel());
            JButton sellerChangeUsername = new JButton("Change");
            sellerChangeUsernamePanel.add(sellerChangeUsername);
            sellerChangeUsernameFrame.add(sellerChangeUsernamePanel);
            sellerChangeUsernameFrame.setTitle("Change Username");
            sellerChangeUsernameFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            sellerChangeUsernameFrame.pack();
            sellerChangeUsernameFrame.setLocationRelativeTo(null);

            JFrame sellerChangePasswordFrame = new JFrame();
            JPanel sellerChangePasswordPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            sellerChangePasswordPanel.add(new JLabel("Old Password:"));
            JTextField sellerChangeOldPassword = new JTextField();
            sellerChangePasswordPanel.add(sellerChangeOldPassword);
            sellerChangePasswordPanel.add(new JLabel("New Password:"));
            JTextField sellerChangeNewPassword = new JTextField();
            sellerChangePasswordPanel.add(sellerChangeNewPassword);
            sellerChangePasswordPanel.add(new JLabel());
            JButton sellerChangePassword = new JButton("Change");
            sellerChangePasswordPanel.add(sellerChangePassword);
            sellerChangePasswordFrame.add(sellerChangePasswordPanel);
            sellerChangePasswordFrame.setTitle("Change Password");
            sellerChangePasswordFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            sellerChangePasswordFrame.pack();
            sellerChangePasswordFrame.setLocationRelativeTo(null);

            JFrame sellerChangeRoleFrame = new JFrame();
            JPanel sellerChangeRolePanel = new JPanel(new GridLayout(0, 2, 4, 16));
            sellerChangeRolePanel.add(new JLabel("Password:"));
            JTextField sellerChangeRolePassword = new JTextField();
            sellerChangeRolePanel.add(sellerChangeRolePassword);
            sellerChangeRolePanel.add(new JLabel());
            JButton sellerChangeRole = new JButton("Change To Buyer");
            sellerChangeRolePanel.add(sellerChangeRole);
            sellerChangeRoleFrame.add(sellerChangeRolePanel);
            sellerChangeRoleFrame.setTitle("Change Role");
            sellerChangeRoleFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            sellerChangeRoleFrame.pack();
            sellerChangeRoleFrame.setLocationRelativeTo(null);

            JFrame sellerDeleteAccountFrame = new JFrame();
            JPanel sellerDeleteAccountPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            sellerDeleteAccountPanel.add(new JLabel("Password:"));
            JTextField sellerDeleteAccountField = new JTextField();
            sellerDeleteAccountPanel.add(sellerDeleteAccountField);
            sellerDeleteAccountPanel.add(new JLabel());
            JButton sellerDeleteAccount = new JButton("Delete");
            sellerDeleteAccountPanel.add(sellerDeleteAccount);
            sellerDeleteAccountFrame.add(sellerDeleteAccountPanel);
            sellerDeleteAccountFrame.setTitle("Delete Account");
            sellerDeleteAccountFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            sellerDeleteAccountFrame.pack();
            sellerDeleteAccountFrame.setLocationRelativeTo(null);
            /*
            END SELLER MANAGE ACCOUNT FRAME
             */


             /*
        BUYER OPTIONS FRAME
        */
            JFrame buyerOptionsFrame = new JFrame();
            JPanel buyerOptionsPanel = new JPanel(new GridLayout(0, 3, 4, 16));
            JButton searchForStoreButton = new JButton("Search for a Store");
            JButton searchForStoreButtonCopy = new JButton("Search for a Store");
            JButton searchForProductButton = new JButton("Search for a Product");
            JButton searchForProductButtonCopy = new JButton("Search for a Product");
            JButton searchProductByDescriptionButton = new JButton("Search for a Product by Description");
            JButton searchProductByDescriptionButtonCopy = new JButton("Search for a Product by Description");
            JButton viewAllProductsButton = new JButton("View all Products");
            JButton sortProductsByCheapestButton = new JButton("Sort Products by Cheapest");
            JButton sortProductsByMostExpensiveButton = new JButton("Sort Products by Most Expensive");
            JButton sortByAvailabilityButton = new JButton("Sort Products by Availability");
            JButton shoppingCartButton = new JButton("Shopping Cart");
            JButton exportPurchaseHistoryAsFileButton = new JButton("Export Purchase History as file");
            JButton buyerViewReviewsButton = new JButton("View Customer Reviews");
            JButton buyerLeaveReviewButton = new JButton("Leave Review");
            JButton buyerManageAccountButton = new JButton("Manage Account");
            JButton buyerLogoutButton = new JButton("Logout");
            buyerOptionsPanel.add(new JLabel());
            buyerOptionsPanel.add(new JLabel("Welcome to Buyer Marketplace!"));
            buyerOptionsPanel.add(new JLabel());
            buyerOptionsPanel.add(searchForStoreButton);
            buyerOptionsPanel.add(searchForProductButton);
            buyerOptionsPanel.add(searchProductByDescriptionButton);
            buyerOptionsPanel.add(viewAllProductsButton);
            buyerOptionsPanel.add(sortProductsByCheapestButton);
            buyerOptionsPanel.add(sortProductsByMostExpensiveButton);
            buyerOptionsPanel.add(sortByAvailabilityButton);
            buyerOptionsPanel.add(shoppingCartButton);
            buyerOptionsPanel.add(exportPurchaseHistoryAsFileButton);
            buyerOptionsPanel.add(buyerLeaveReviewButton);
            buyerOptionsPanel.add(buyerViewReviewsButton);
            buyerOptionsPanel.add(buyerManageAccountButton);
            buyerOptionsPanel.add(new JLabel());
            buyerOptionsPanel.add(buyerLogoutButton);
            buyerOptionsFrame.add(buyerOptionsPanel);
            buyerOptionsFrame.setTitle("Buyer Marketplace");
            buyerOptionsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerOptionsFrame.pack();
            buyerOptionsFrame.setLocationRelativeTo(null);
            /*
            END BUYER OPTIONS FRAME
             */
            /*
            BUYER SORT CHEAPEST FRAME
             */
            JFrame buyerSortCheapestFrame = new JFrame();
            JPanel buyerSortCheapestPanel = new JPanel();
            String[] coloumn = Seller.sortCheapest().split("\n");
            String[][] temp = new String[coloumn.length][4];
            String[] columnNames = {"Store Name", "Product Name", "Purchase Price", "Quantity in Stock"};
            JTable tableModelSortCheapest = new JTable(temp, columnNames);
            for(int i = 0; i < coloumn.length; i++){
                String[] row = coloumn[i].split(";");
                for(int j = 0; j < row.length; j++){
                    tableModelSortCheapest.setValueAt(row[j], i, j);
                }
            }
            buyerSortCheapestPanel.add(new JScrollPane(tableModelSortCheapest));
            buyerSortCheapestFrame.add(buyerSortCheapestPanel);
            buyerSortCheapestFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerSortCheapestFrame.pack();
            buyerSortCheapestFrame.setLocationRelativeTo(null);
            /*
            END BUYER SORT CHEAPEST FRAME
             */

            /*
            BUYER SORT EXPENSIVE FRAME
             */
            JFrame buyerSortExpensiveFrame = new JFrame();
            JPanel buyerSortExpensivePanel = new JPanel();
            coloumn = Seller.sortExpensive().split("\n");
            temp = new String[coloumn.length][4];
            columnNames = new String[]{"Store Name", "Product Name", "Purchase Price", "Quantity in Stock"};
            JTable tableModelSortExpensive = new JTable(temp, columnNames);
            for(int i = 0; i < coloumn.length; i++){
                String[] row = coloumn[i].split(";");
                for(int j = 0; j < row.length; j++){
                    tableModelSortExpensive.setValueAt(row[j], i, j);
                }
            }
            buyerSortExpensivePanel.add(new JScrollPane(tableModelSortExpensive));
            buyerSortExpensiveFrame.add(buyerSortExpensivePanel);
            buyerSortExpensiveFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerSortExpensiveFrame.pack();
            buyerSortExpensiveFrame.setLocationRelativeTo(null);
            /*
            END BUYER SORT EXPENSIVE FRAME
             */


            /*
            SORT AVAILIBILITY , LOWEST IN STOCK
             */
            JFrame buyerSortByLowestQuantityFrame = new JFrame();
            JPanel buyerSortByLowestQuantityPanel = new JPanel();
            coloumn = Seller.lowestQuant().split("\n");
            temp = new String[coloumn.length][4];
            columnNames = new String[]{"Store Name", "Product Name", "Purchase Price", "Quantity in Stock"};
            JTable tableModelSortLowestQuant = new JTable(temp, columnNames);
            for(int i = 0; i < coloumn.length; i++){
                String[] row = coloumn[i].split(";");
                for(int j = 0; j < row.length; j++){
                    tableModelSortLowestQuant.setValueAt(row[j], i, j);
                }
            }
            buyerSortByLowestQuantityPanel.add(new JScrollPane(tableModelSortLowestQuant));
            buyerSortByLowestQuantityFrame.add(buyerSortByLowestQuantityPanel);
            buyerSortByLowestQuantityFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerSortByLowestQuantityFrame.pack();
            buyerSortByLowestQuantityFrame.setLocationRelativeTo(null);
            /*
            END SORT AVAILIBILITY , LOWEST IN STOCK
             */

            /*
            SORT AVAILABILITY, HIGHEST IN STOCK
             */
            JFrame buyerSortByHighestQuantityFrame = new JFrame();
            JPanel buyerSortByHighestQuantityPanel = new JPanel();
            coloumn = Seller.highestQuant().split("\n");
            temp = new String[coloumn.length][4];
            columnNames = new String[]{"Store Name", "Product Name", "Purchase Price", "Quantity in Stock"};
            JTable tableModelSortHighestQuant = new JTable(temp, columnNames);
            for(int i = 0; i < coloumn.length; i++){
                String[] row = coloumn[i].split(";");
                for(int j = 0; j < row.length; j++){
                    tableModelSortHighestQuant.setValueAt(row[j], i, j);
                }
            }
            buyerSortByHighestQuantityPanel.add(new JScrollPane(tableModelSortHighestQuant));
            buyerSortByHighestQuantityFrame.add(buyerSortByHighestQuantityPanel);
            buyerSortByHighestQuantityFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerSortByHighestQuantityFrame.pack();
            buyerSortByHighestQuantityFrame.setLocationRelativeTo(null);
            /*
            END SORT AVAILABILITY, HIGHEST IN STOCK
             */

            /*
            BUYER VIEW ALL PRODUCTS FRAME
             */
            JFrame buyerViewAllProductsFrame = new JFrame();
            JPanel buyerViewAllProductsPanel = new JPanel();
            JButton buyerSearchForProductButton = new JButton("Search for a Product");
            JButton buyerViewAllAddToShoppingCart = new JButton("ADD TO CART");
            buyerViewAllProductsPanel.setLayout(new BorderLayout());
            buyerViewAllProductsPanel.add(buyerSearchForProductButton, BorderLayout.SOUTH);
            buyerViewAllProductsPanel.add(buyerViewAllAddToShoppingCart, BorderLayout.BEFORE_FIRST_LINE);
            //buyerViewAllProductsPanel.add(buyerSearchForProductButton);
            String[] coloumnViewAllProductsAndStores = Seller.printProductAndStores().split("\n");
            temp = new String[coloumnViewAllProductsAndStores.length][4];
            columnNames = new String[]{"Store Name", "Product Name", "Purchase Price", "Quantity in Stock"};
            JTable viewAllProductsAndStoresTable = new JTable(temp, columnNames);
            for(int i = 0; i < coloumnViewAllProductsAndStores.length; i++){
                String[] row = coloumnViewAllProductsAndStores[i].split(";");
                for(int j = 0; j < row.length; j++){
                    viewAllProductsAndStoresTable.setValueAt(row[j], i, j);
                }
            }
            buyerViewAllProductsPanel.add(new JScrollPane(viewAllProductsAndStoresTable));
            buyerViewAllProductsFrame.add(buyerViewAllProductsPanel);
            buyerViewAllProductsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerViewAllProductsFrame.pack();
            buyerViewAllProductsFrame.setLocationRelativeTo(null);

            /*
            END BUYER VIEW ALL PRODUCTS FRAME
             */
            /*


        /*
        BUYER MANAGE ACCOUNT FRAME
             */
            JFrame buyerManageAccountFrame = new JFrame();
            JPanel buyerManageAccountPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            JButton buyerChangeUsernameButton = new JButton("Change Username");
            buyerManageAccountPanel.add(buyerChangeUsernameButton);
            JButton buyerChangePasswordButton = new JButton("Change Password");
            buyerManageAccountPanel.add(buyerChangePasswordButton);
            JButton buyerChangeRoleButton = new JButton("Change Role");
            buyerManageAccountPanel.add(buyerChangeRoleButton);
            JButton buyerDeleteAccountButton = new JButton("Delete Account");
            buyerManageAccountPanel.add(buyerDeleteAccountButton);
            buyerManageAccountFrame.add(buyerManageAccountPanel);
            buyerManageAccountFrame.setTitle("Manage Account");
            buyerManageAccountFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerManageAccountFrame.pack();
            buyerManageAccountFrame.setLocationRelativeTo(null);

            JFrame buyerChangeUsernameFrame = new JFrame();
            JPanel buyerChangeUsernamePanel = new JPanel(new GridLayout(0, 2, 4, 16));
            buyerChangeUsernamePanel.add(new JLabel("New Username:"));
            JTextField buyerNewUsername = new JTextField();
            buyerChangeUsernamePanel.add(buyerNewUsername);
            buyerChangeUsernamePanel.add(new JLabel());
            JButton buyerChangeUsername = new JButton("Change");
            buyerChangeUsernamePanel.add(buyerChangeUsername);
            buyerChangeUsernameFrame.add(buyerChangeUsernamePanel);
            buyerChangeUsernameFrame.setTitle("Change Username");
            buyerChangeUsernameFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerChangeUsernameFrame.pack();
            buyerChangeUsernameFrame.setLocationRelativeTo(null);

            JFrame buyerChangePasswordFrame = new JFrame();
            JPanel buyerChangePasswordPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            buyerChangePasswordPanel.add(new JLabel("Old Password:"));
            JTextField buyerChangeOldPassword = new JTextField();
            buyerChangePasswordPanel.add(buyerChangeOldPassword);
            buyerChangePasswordPanel.add(new JLabel("New Password:"));
            JTextField buyerChangeNewPassword = new JTextField();
            buyerChangePasswordPanel.add(buyerChangeNewPassword);
            buyerChangePasswordPanel.add(new JLabel());
            JButton buyerChangePassword = new JButton("Change");
            buyerChangePasswordPanel.add(buyerChangePassword);
            buyerChangePasswordFrame.add(buyerChangePasswordPanel);
            buyerChangePasswordFrame.setTitle("Change Password");
            buyerChangePasswordFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerChangePasswordFrame.pack();
            buyerChangePasswordFrame.setLocationRelativeTo(null);

            JFrame buyerChangeRoleFrame = new JFrame();
            JPanel buyerChangeRolePanel = new JPanel(new GridLayout(0, 2, 4, 16));
            buyerChangeRolePanel.add(new JLabel("Password:"));
            JTextField buyerChangeRolePassword = new JTextField();
            buyerChangeRolePanel.add(buyerChangeRolePassword);
            buyerChangeRolePanel.add(new JLabel());
            JButton buyerChangeRole = new JButton("Change To Buyer");
            buyerChangeRolePanel.add(buyerChangeRole);
            buyerChangeRoleFrame.add(buyerChangeRolePanel);
            buyerChangeRoleFrame.setTitle("Change Role");
            buyerChangeRoleFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerChangeRoleFrame.pack();
            buyerChangeRoleFrame.setLocationRelativeTo(null);

            JFrame buyerDeleteAccountFrame = new JFrame();
            JPanel buyerDeleteAccountPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            buyerDeleteAccountPanel.add(new JLabel("Password:"));
            JTextField buyerDeleteAccountField = new JTextField();
            buyerDeleteAccountPanel.add(buyerDeleteAccountField);
            buyerDeleteAccountPanel.add(new JLabel());
            JButton buyerDeleteAccount = new JButton("Delete");
            buyerDeleteAccountPanel.add(buyerDeleteAccount);
            buyerDeleteAccountFrame.add(buyerDeleteAccountPanel);
            buyerDeleteAccountFrame.setTitle("Delete Account");
            buyerDeleteAccountFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerDeleteAccountFrame.pack();
            buyerDeleteAccountFrame.setLocationRelativeTo(null);
            /*
            END BUYER MANAGE ACCOUNT FRAME
             */

            /*
            BUYER LEAVE REVIEW FRAME
             */
            JFrame leaveReviewFrame = new JFrame();
            JPanel leaveReviewPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            leaveReviewPanel.add(new JLabel("Product Name:"));
            JTextField leaveReviewProductName = new JTextField();
            leaveReviewPanel.add(leaveReviewProductName);
            leaveReviewPanel.add(new JLabel("Store Name:"));
            JTextField leaveReviewStoreName = new JTextField();
            leaveReviewPanel.add(leaveReviewStoreName);
            String[] choices = { "1", "2", "3", "4", "5"};
            leaveReviewPanel.add(new JLabel("Rating (1-5):"));
            JComboBox<String> leaveReviewRating = new JComboBox<String>(choices);
            leaveReviewPanel.add(leaveReviewRating);
            leaveReviewPanel.add(new JLabel("Review Description: "));
            JTextField leaveReviewDescription = new JTextField();
            leaveReviewPanel.add(leaveReviewDescription);
            JButton leaveR = new JButton("Leave Review");
            leaveReviewPanel.add(leaveR);
            leaveReviewFrame.add(leaveReviewPanel);
            leaveReviewFrame.setTitle("Buyer Leave Review");
            leaveReviewFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            leaveReviewFrame.pack();
            leaveReviewFrame.setLocationRelativeTo(null);
            /*
            END BUYER LEAVE REVIEW FRAME
             */

            /*
            BUYER VIEW REVIEW FRAME
             */
            JFrame buyerViewReviewsFrame = new JFrame();
            JPanel buyerViewReviewsPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            buyerViewReviewsPanel.add(new JLabel("Enter Store Name: "));
            JTextField buyerViewReviewsStoreName = new JTextField();
            buyerViewReviewsPanel.add(buyerViewReviewsStoreName);
            buyerViewReviewsPanel.add(new JLabel("Product Name (Leave Empty To View All):"));
            JTextField buyerViewReviewsProductName = new JTextField();
            buyerViewReviewsPanel.add(buyerViewReviewsProductName);
            buyerViewReviewsPanel.add(new JLabel());
            JButton buyerViewReviewsButtonCopy = new JButton("View");
            buyerViewReviewsPanel.add(buyerViewReviewsButtonCopy);
            buyerViewReviewsFrame.add(buyerViewReviewsPanel);
            buyerViewReviewsFrame.setTitle("View Customer Reviews");
            buyerViewReviewsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerViewReviewsFrame.pack();
            buyerViewReviewsFrame.setLocationRelativeTo(null);
            /*
            END BUYER VIEW REVIEW FRAME
             */
            /*
            BUYER SHOPPING CART FRAMES
             */
            JFrame buyerShoppingCartFrame = new JFrame();
            JPanel buyerShoppingCartPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            JButton addToShoppingCart = new JButton("Add Products to Cart");
            buyerShoppingCartPanel.add(addToShoppingCart);
            JButton editShoppingCartQty = new JButton("Modify Quantity in Shopping Cart");
            buyerShoppingCartPanel.add(editShoppingCartQty);
            JButton removeFromShoppingCart = new JButton("Remove Products From Cart");
            buyerShoppingCartPanel.add(removeFromShoppingCart);
            JButton viewShoppingCart = new JButton("View Shopping Cart");
            buyerShoppingCartPanel.add(viewShoppingCart);
            buyerShoppingCartFrame.add(buyerShoppingCartPanel);
            buyerShoppingCartFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerShoppingCartFrame.pack();
            buyerShoppingCartFrame.setLocationRelativeTo(null);


            JFrame buyerAddToShoppingCartFrame = new JFrame();
            JPanel buyerAddToShoppingCartPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            JTextField buyerShoppingCartStoreName = new JTextField();
            buyerAddToShoppingCartPanel.add(new JLabel("Enter Store Name: "));
            buyerAddToShoppingCartPanel.add(buyerShoppingCartStoreName);
            JTextField buyerShoppingCartProductName = new JTextField();
            buyerAddToShoppingCartPanel.add(new JLabel("Enter Product Name: "));
            buyerAddToShoppingCartPanel.add(buyerShoppingCartProductName);
            JTextField buyerShoppingCartQuantity= new JTextField();
            buyerAddToShoppingCartPanel.add(new JLabel("Enter Quantity"));
            buyerAddToShoppingCartPanel.add(buyerShoppingCartQuantity);
            buyerAddToShoppingCartPanel.add(new JLabel());
            JButton addToCart = new JButton("Add");
            buyerAddToShoppingCartPanel.add(addToCart, BorderLayout.SOUTH);
            buyerAddToShoppingCartFrame.add(buyerAddToShoppingCartPanel);
            buyerAddToShoppingCartFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerAddToShoppingCartFrame.pack();
            buyerAddToShoppingCartFrame.setLocationRelativeTo(null);

            JFrame buyerRemoveFromShoppingCartFrame = new JFrame();
            JPanel buyerRemoveFromShoppingCartPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            JTextField buyerRemoveFromShoppingCartStoreName = new JTextField();
            buyerRemoveFromShoppingCartPanel.add(new JLabel("Enter Store Name: "));
            buyerRemoveFromShoppingCartPanel.add(buyerRemoveFromShoppingCartStoreName);
            JTextField buyerRemoveFromShoppingCartProductName = new JTextField();
            buyerRemoveFromShoppingCartPanel.add(new JLabel("Enter Product Name: "));
            buyerRemoveFromShoppingCartPanel.add(buyerRemoveFromShoppingCartProductName);
            JTextField buyerRemoveFromShoppingCartQuantity= new JTextField();
            buyerRemoveFromShoppingCartPanel.add(new JLabel("Enter Quantity"));
            buyerRemoveFromShoppingCartPanel.add(buyerRemoveFromShoppingCartQuantity);
            buyerRemoveFromShoppingCartPanel.add(new JLabel());
            JButton removeFromCart = new JButton("REMOVE");
            buyerRemoveFromShoppingCartPanel.add(removeFromCart, BorderLayout.SOUTH);
            buyerRemoveFromShoppingCartFrame.add(buyerRemoveFromShoppingCartPanel);
            buyerRemoveFromShoppingCartFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerRemoveFromShoppingCartFrame.pack();
            buyerRemoveFromShoppingCartFrame.setLocationRelativeTo(null);

            JFrame buyerChangeQtyInShoppingCartFrame = new JFrame();
            JPanel buyerChangeQtyInShoppingCartPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            JTextField buyerChangeQtyInShoppingCartStoreName = new JTextField();
            buyerChangeQtyInShoppingCartPanel.add(new JLabel("Enter Store Name: "));
            buyerChangeQtyInShoppingCartPanel.add(buyerChangeQtyInShoppingCartStoreName);
            JTextField buyerChangeQtyInShoppingCartProductName = new JTextField();
            buyerChangeQtyInShoppingCartPanel.add(new JLabel("Enter Product Name: "));
            buyerChangeQtyInShoppingCartPanel.add(buyerChangeQtyInShoppingCartProductName);
            JTextField buyerChangeQtyInShoppingCart= new JTextField();
            buyerChangeQtyInShoppingCartPanel.add(new JLabel("Enter Quantity"));
            buyerChangeQtyInShoppingCartPanel.add(buyerChangeQtyInShoppingCart);
            buyerChangeQtyInShoppingCartPanel.add(new JLabel());
            JButton changeQtyInCart = new JButton("CHANGE");
            buyerChangeQtyInShoppingCartPanel.add(changeQtyInCart, BorderLayout.SOUTH);
            buyerChangeQtyInShoppingCartFrame.add(buyerChangeQtyInShoppingCartPanel);
            buyerChangeQtyInShoppingCartFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            buyerChangeQtyInShoppingCartFrame.pack();
            buyerChangeQtyInShoppingCartFrame.setLocationRelativeTo(null);
            /*
            END BUYER SHOPPING CART FRAME
             */

            /*
            BUYER SEARCH FOR STORE FRAMES INITIALIZATION
             */
            JFrame buyerSearchByStoreFrame = new JFrame();
            JPanel buyerSearchByStorePanel = new JPanel(new GridLayout(0, 2, 4, 16));

            /*
            END BUYER SEARCH FOR STORE FRAMES INITIALIZATION
             */

            /*
            BUYER SEARCH BY DESCRIPTION
             */
            JFrame buyerSearchByDescriptionFrame = new JFrame();
            JPanel buyerSearchByDescriptionPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            /*
            END BUYER SEARCH BY DESCRIPTION
             */

            /*
            BUYER SEARCH BY PRODUCT BUTTON
             */
            JFrame buyerSearchByProductFrame = new JFrame();
            JPanel buyerSearchByProductPanel = new JPanel(new GridLayout(0, 2, 4, 16));
            /*
            END BUYER SEARCH BY PRODUCT
             */

            /*
            BUYER VIEW REVIEWS FINAL DISPLAY FRAME
             */
            JFrame buyerDisplayReviewsFrame = new JFrame();
            JPanel buyerDisplayReviewsPanel = new JPanel();
            /*
            END BUYER VIEW REVIEWS FINAL DISPLAY FRAME
             */

            /*
            LOGIN/REGISTRATION FRAME ACTION LISTENERS
             */
            openLoginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loginFrame.setVisible(true);
                }
            });
            openRegisterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registerFrame.setVisible(true);
                }
            });
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (loginUsernameOrEmailField.getText().isEmpty() ||
                            loginPasswordField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Marketplace", JOptionPane.ERROR_MESSAGE);
                    } else if (Account.login(loginUsernameOrEmailField.getText(), loginPasswordField.getText())) { // TODO: MOVE TO SERVER
                        // TODO: MOVE TO SERVER
                        USERNAME[0] = Account.getUsername(loginUsernameOrEmailField.getText());
                        loginRegisterFrame.setVisible(false);
                        registerFrame.setVisible(false);
                        loginFrame.setVisible(false);
                        loginUsernameOrEmailField.setText("");
                        loginPasswordField.setText("");
                        if (Account.getRole(USERNAME[0]).equalsIgnoreCase("Seller")) {
                            sellerOptionsFrame.setVisible(true);
                        } else if(Account.getRole(USERNAME[0]).equalsIgnoreCase("Buyer")){
                            buyerOptionsFrame.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed",
                                "Marketplace", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (registerEmailField.getText().isEmpty() || registerUsernameField.getText().isEmpty()
                            || registerPasswordField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Marketplace", JOptionPane.ERROR_MESSAGE);
                    } else if (Account.createAccount(registerEmailField.getText(), registerUsernameField.getText(), // TODO: MOVE TO SERVER
                            registerPasswordField.getText(), registerRoleBox.getSelectedItem().toString())) {
                        USERNAME[0] = registerUsernameField.getText();
                        loginRegisterFrame.setVisible(false);
                        registerFrame.setVisible(false);
                        loginFrame.setVisible(false);
                        registerEmailField.setText("");
                        registerUsernameField.setText("");
                        registerPasswordField.setText("");
                        if (registerRoleBox.getSelectedItem().toString().equalsIgnoreCase("Seller")) {
                            sellerOptionsFrame.setVisible(true);
                        } else if (registerRoleBox.getSelectedItem().toString().equalsIgnoreCase("Buyer")) {
                            buyerOptionsFrame.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration Failed",
                                "Marketplace", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END LOGIN/REGISTRATION FRAME ACTION LISTENERS
             */

            /*
            SELLER OPTIONS FRAME ACTION LISTENERS
             */
            createStoreButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createStoreFrame.setVisible(true);
                }
            });
            modifyStoreButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modifyStoreFrame.setVisible(true);
                }
            });
            deleteStoreButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteStoreFrame.setVisible(true);
                }
            });
            sellerStatsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sellerStatsFrame.setVisible(true);
                }
            });
            customerReviewsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    customerReviewsFrame.setVisible(true);
                }
            });
            sellerManageAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sellerManageAccountFrame.setVisible(true);
                }
            });
            sellerLogoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sellerOptionsFrame.setVisible(false);
                    loginRegisterFrame.setVisible(true);
                }
            });
            /*
            END SELLER OPTIONS FRAME ACTION LISTENERS
             */

            /*
            CREATE STORE FRAME ACTION LISTENERS
             */
            createStoreEnterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (createStoreNameField.getText().isEmpty() || createStoreLocationField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Create Store", JOptionPane.ERROR_MESSAGE);
                    } else if (Seller.createStore(createStoreNameField.getText(),
                            createStoreLocationField.getText(), USERNAME[0])) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Store Created",
                                "Create Store", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Creation Failed",
                                "Create Store", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END CREATE STORE FRAME ACTION LISTENERS
             */

            /*
            MODIFY STORE FRAME ACTION LISTENERS
             */
            createProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createProductFrame.setVisible(true);
                }
            });
            editDescriptionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editProductDescriptionFrame.setVisible(true);
                }
            });
            editPriceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editProductPriceFrame.setVisible(true);
                }
            });
            editQuantityButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editProductQuantityFrame.setVisible(true);
                }
            });
            deleteProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteProductFrame.setVisible(true);
                }
            });
            addProductCSVButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addProductCSVFrame.setVisible(true);
                }
            });
            startSaleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startSaleFrame.setVisible(true);
                }
            });
            purchaseLimitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    purchaseLimitFrame.setVisible(true);
                }
            });
            /*
            END MODIFY STORE FRAME ACTION LISTENERS
             */

            /*
            DELETE STORE FRAME ACTION LISTENERS
             */
            deleteStore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (deleteStoreField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Delete Store", JOptionPane.ERROR_MESSAGE);
                    } else if (Seller.deleteStore(deleteStoreField.getText(), USERNAME[0])) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Store Deleted",
                                "Delete Store", JOptionPane.INFORMATION_MESSAGE);
                    }  else {
                        JOptionPane.showMessageDialog(null, "Deletion Failed",
                                "Delete Store", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END DELETE STORE FRAME ACTION LISTENERS
             */

            /*
            SELLER STATS FRAME ACTION LISTENERS
             */
            customerPurchasesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    customerPurchasesFrame.setVisible(true);
                }
            });
            customerPurchases.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (customerPurchasesStoreName.getText().isEmpty()
                            || customerPurchasesUsername.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "View Customer Purchases", JOptionPane.ERROR_MESSAGE);
                    } else if (customerPurchasesBox.getSelectedItem().toString().equals("Yes")) {
                        JOptionPane.showMessageDialog(null,
                                Seller.getCustomersAndPurchases(customerPurchasesStoreName.getText(),
                                        customerPurchasesUsername.getText(), true), // TODO: MOVE TO SERVER
                                "View Customer Purchases", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                Seller.getCustomersAndPurchases(customerPurchasesStoreName.getText(),
                                        customerPurchasesUsername.getText(), false), // TODO: MOVE TO SERVER
                                "View Customer Purchases", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });
            productSalesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    productSalesFrame.setVisible(true);
                }
            });
            productSales.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (productSalesStoreName.getText().isEmpty() || productSalesUsername.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "View Product Sales", JOptionPane.ERROR_MESSAGE);
                    } else if (productSalesBox.getSelectedItem().toString().equals("Yes")) {
                        JOptionPane.showMessageDialog(null,
                                Seller.getProductSales(productSalesStoreName.getText(),
                                        productSalesUsername.getText(), true), // TODO: MOVE TO SERVER
                                "View Product Sales", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                Seller.getProductSales(productSalesStoreName.getText(),
                                        productSalesUsername.getText(), false), // TODO: MOVE TO SERVER
                                "View Product Sales", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });
            cartProductsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cartProductsFrame.setVisible(true);
                }
            });
            cartProducts.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cartProductsUsername.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "View Products In Cart", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                Seller.getShoppingCartProducts(cartProductsUsername.getText()), // TODO: MOVE TO SERVER
                                "View Products In Cart", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });
            getCSVButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getCSVFrame.setVisible(true);
                }
            });
            getCSV.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (getCSVStoreName.getText().isEmpty() || getCSVPath.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Get Product CSV", JOptionPane.ERROR_MESSAGE);
                    } else if (Seller.writeProductsToCSV(getCSVStoreName.getText(), getCSVPath.getText())) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Successfully Written",
                                "Get Product CSV", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Write Failed",
                                "Get Product CSV", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END SELLER STATS FRAME ACTION LISTENERS
             */

            /*
            CUSTOMER REVIEWS FRAME ACTION LISTENERS
             */
            customerReviews.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sellerDisplayReviews(true, customerReviewsName, USERNAME[0], sellerDisplayReviewsFrame,
                            sellerDisplayReviewsPanel);
                }
            });
            /*
            END CUSTOMER REVIEWS FRAME ACTION LISTENERS
             */

            /*
            SELLER MANAGE ACCOUNT FRAME ACTION LISTENERS
             */
            sellerChangeUsernameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sellerChangeUsernameFrame.setVisible(true);
                }
            });
            sellerChangeUsername.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (sellerNewUsername.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Change Username", JOptionPane.ERROR_MESSAGE);
                    } else if (Account.changeUsername(sellerNewUsername.getText(), USERNAME[0])) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Username Changed",
                                "Change Username", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Change Failed",
                                "Change Username", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            sellerChangePasswordButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sellerChangePasswordFrame.setVisible(true);
                }
            });
            sellerChangePassword.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (sellerChangeNewPassword.getText().isEmpty() || sellerChangeOldPassword.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Change Password", JOptionPane.ERROR_MESSAGE);
                    } else if (Account.changePassword(USERNAME[0], sellerChangeOldPassword.getText(),
                            sellerChangeNewPassword.getText())) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Password Changed",
                                "Change Password", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Change Failed",
                                "Change Password", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            sellerChangeRoleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sellerChangeRoleFrame.setVisible(true);
                }
            });
            sellerChangeRole.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (sellerChangeRolePassword.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Change Role", JOptionPane.ERROR_MESSAGE);
                    } else if (Account.changeRole(USERNAME[0], sellerChangePassword.getText(), "Buyer")) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Role Changed",
                                "Change Role", JOptionPane.INFORMATION_MESSAGE);
                        for (Frame frame : Frame.getFrames()) {
                            if (frame instanceof JFrame) {
                                ((JFrame)frame).setVisible(false);
                            }
                        }
                        loginRegisterFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Change Failed",
                                "Change Role", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            sellerDeleteAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sellerDeleteAccountFrame.setVisible(true);
                }
            });
            sellerDeleteAccount.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (sellerDeleteAccountField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Delete Account", JOptionPane.ERROR_MESSAGE);
                    } else if (Account.deleteAccount(USERNAME[0], sellerDeleteAccountField.getText())) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Account Deleted",
                                "Delete Account", JOptionPane.INFORMATION_MESSAGE);
                        for (Frame frame : Frame.getFrames()) {
                            if (frame instanceof JFrame) {
                                ((JFrame)frame).setVisible(false);
                            }
                        }
                        loginRegisterFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Deletion Failed",
                                "Delete Account", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END SELLER MANAGE ACCOUNT FRAME ACTION LISTENERS
             */

            /*
            Buyer Option Frames Action Listeners
             */
            buyerLeaveReviewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    leaveReviewFrame.setVisible(true);
                }
            });
            leaveR.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(leaveReviewProductName.getText().isEmpty() || leaveReviewStoreName.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Do not leave Store and Product" +
                                        " Fields Empty!",
                                "Leave Review", JOptionPane.ERROR_MESSAGE);
                    } else{
                        Customer.leaveReview(leaveReviewStoreName.getText(), leaveReviewProductName.getText(),
                                USERNAME[0], Integer.parseInt((String)leaveReviewRating.getSelectedItem()),
                                leaveReviewDescription.getText());
                        JOptionPane.showMessageDialog(null, "Review Left Successfully!",
                                "Leave Review", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            buyerViewReviewsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerViewReviewsFrame.setVisible(true);
                }
            });
            buyerViewReviewsButtonCopy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerDisplayReviews(true, buyerViewReviewsStoreName, buyerViewReviewsProductName,
                            buyerDisplayReviewsFrame, buyerDisplayReviewsPanel);

                }
            });
            searchForStoreButtonCopy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String buyerSearchedStore = JOptionPane.showInputDialog(null, "Enter Store " +
                            "Name", "Marketplace", JOptionPane.QUESTION_MESSAGE);
                    if(buyerSearchedStore == null || buyerSearchedStore.equals(null) || buyerSearchedStore.equals("")){
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Search By Store", JOptionPane.ERROR_MESSAGE);
                    } else {
                        searchByStore(true, buyerSearchedStore, searchForStoreButtonCopy, buyerSearchByStoreFrame,
                                buyerSearchByStorePanel);
                    }
                }
            });
            searchForStoreButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String buyerSearchedStore = JOptionPane.showInputDialog(null, "Enter Store " +
                            "Name", "Marketplace", JOptionPane.QUESTION_MESSAGE);
                    if(buyerSearchedStore == null || buyerSearchedStore.equals(null) || buyerSearchedStore.equals("")){
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Search By Store", JOptionPane.ERROR_MESSAGE);
                    } else {
                        searchByStore(true, buyerSearchedStore, searchForStoreButtonCopy, buyerSearchByStoreFrame,
                                buyerSearchByStorePanel);
                    }
                }
            });
            searchProductByDescriptionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String buyerSearchedDescription = JOptionPane.showInputDialog(null,
                            "Enter Description", "Marketplace", JOptionPane.QUESTION_MESSAGE);

                    if(buyerSearchedDescription == null || buyerSearchedDescription.equals(null) || buyerSearchedDescription.equals(
                            "")){
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Search By Description", JOptionPane.ERROR_MESSAGE);
                    } else {
                        searchByDescription(true, buyerSearchedDescription, searchProductByDescriptionButtonCopy,
                                buyerSearchByDescriptionFrame, buyerSearchByDescriptionPanel);
                    }
                }
            });
            searchProductByDescriptionButtonCopy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String buyerSearchedDescription = JOptionPane.showInputDialog(null,
                            "Enter Description", "Marketplace", JOptionPane.QUESTION_MESSAGE);

                    if(buyerSearchedDescription == null || buyerSearchedDescription.equals(null) || buyerSearchedDescription.equals(
                            "")){
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Search By Description", JOptionPane.ERROR_MESSAGE);
                    } else {
                        searchByDescription(true, buyerSearchedDescription, searchProductByDescriptionButtonCopy,
                                buyerSearchByDescriptionFrame, buyerSearchByDescriptionPanel);
                    }
                }
            });

            searchForProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String buyerSearchedProduct = JOptionPane.showInputDialog(null,
                            "Enter Product Name", "Marketplace", JOptionPane.QUESTION_MESSAGE);
                    if(buyerSearchedProduct == null || buyerSearchedProduct.equals(null) || buyerSearchedProduct.equals(
                            "")){
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Search By Product", JOptionPane.ERROR_MESSAGE);
                    } else {
                        searchByProduct(true, buyerSearchedProduct, searchForProductButtonCopy,
                                buyerSearchByProductFrame
                                , buyerSearchByProductPanel);
                    }

                }
            });
            searchForProductButtonCopy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String buyerSearchedProduct = JOptionPane.showInputDialog(null,
                            "Enter Product Name", "Marketplace", JOptionPane.QUESTION_MESSAGE);
                    if(buyerSearchedProduct == null || buyerSearchedProduct.equals(null) || buyerSearchedProduct.equals(
                            "")){
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Search By Product", JOptionPane.ERROR_MESSAGE);
                    } else {
                        searchByProduct(true, buyerSearchedProduct, searchForProductButtonCopy,
                                buyerSearchByProductFrame, buyerSearchByProductPanel);
                    }

                }
            });
            buyerSearchForProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String buyerSearchedProduct = JOptionPane.showInputDialog(null,
                            "Enter Product Name", "Marketplace", JOptionPane.QUESTION_MESSAGE);
                    if(buyerSearchedProduct == null || buyerSearchedProduct.equals(null) || buyerSearchedProduct.equals(
                            "")){
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Search By Product", JOptionPane.ERROR_MESSAGE);
                    } else {
                        buyerViewAllProductsFrame.setVisible(false);
                        searchByProduct(true, buyerSearchedProduct, buyerSearchForProductButton, buyerSearchByProductFrame
                                , buyerSearchByProductPanel);
                    }
                }
            });

            buyerLogoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerOptionsFrame.setVisible(false);
                    loginRegisterFrame.setVisible(true);
                }
            });
            buyerManageAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sellerManageAccountFrame.setVisible(true);
                }
            });

            sortProductsByCheapestButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerSortCheapestFrame.setVisible(true);
                }
            });
            sortProductsByMostExpensiveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerSortExpensiveFrame.setVisible(true);
                }
            });
            viewAllProductsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerViewAllProductsFrame.setVisible(true);
                }
            });

            sortByAvailabilityButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] options = {"Sort by Lowest Quantity", "Sort by Highest Quantity"};
                    String input = (String) JOptionPane.showInputDialog(null, "",
                            "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE,
                            null,options, options[0]);
                    System.out.println(input);
                    if(input != null) {
                        if (input.equals("Sort by Lowest Quantity")) {
                            buyerSortByLowestQuantityFrame.setVisible(true);
                            buyerSortByHighestQuantityFrame.setVisible(false);
                        } else if (input.equals("Sort by Highest Quantity")) {
                            buyerSortByHighestQuantityFrame.setVisible(true);
                            buyerSortByLowestQuantityFrame.setVisible(false);
                        }
                    }
                }
            });

            exportPurchaseHistoryAsFileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String filename = JOptionPane.showInputDialog(null,
                            "Enter Filename to be Exported to", "Marketplace",
                            JOptionPane.QUESTION_MESSAGE);
                    boolean bool = Customer.getPurchaseHistoryofCustomer(loginUsernameOrEmailField.getText(), filename);
                    if(bool){
                        JOptionPane.showMessageDialog(null, "Purchase History Exported " +
                                "Successfully!", "Leave Review", JOptionPane.INFORMATION_MESSAGE);
                    } else if(!bool){
                        JOptionPane.showMessageDialog(null, "Purchase History Export Failed!",
                                "Leave Review", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            /*
            BUYER MANAGE ACCOUNT FRAME ACTION LISTENERS
            */
            buyerChangeUsernameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerChangeUsernameFrame.setVisible(true);
                }
            });
            buyerChangeUsername.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (buyerNewUsername.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Change Username", JOptionPane.ERROR_MESSAGE);
                    } else if (Account.changeUsername(buyerNewUsername.getText(), USERNAME[0])) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Username Changed",
                                "Change Username", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Change Failed",
                                "Change Username", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            buyerChangePasswordButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerChangePasswordFrame.setVisible(true);
                }
            });
            buyerChangePassword.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (buyerChangeNewPassword.getText().isEmpty() || buyerChangeOldPassword.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Change Password", JOptionPane.ERROR_MESSAGE);
                    } else if (Account.changePassword(USERNAME[0], buyerChangeOldPassword.getText(),
                            buyerChangeNewPassword.getText())) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Password Changed",
                                "Change Password", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Change Failed",
                                "Change Password", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            buyerChangeRoleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerChangeRoleFrame.setVisible(true);
                }
            });
            buyerChangeRole.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (buyerChangeRolePassword.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Change Role", JOptionPane.ERROR_MESSAGE);
                    } else if (Account.changeRole(USERNAME[0], buyerChangePassword.getText(), "Buyer")) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Role Changed",
                                "Change Role", JOptionPane.INFORMATION_MESSAGE);
                        for (Frame frame : Frame.getFrames()) {
                            if (frame instanceof JFrame) {
                                ((JFrame)frame).setVisible(false);
                            }
                        }
                        loginRegisterFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Change Failed",
                                "Change Role", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            buyerDeleteAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerDeleteAccountFrame.setVisible(true);
                }
            });
            buyerDeleteAccount.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (buyerDeleteAccountField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter All Fields",
                                "Delete Account", JOptionPane.ERROR_MESSAGE);
                    } else if (Account.deleteAccount(USERNAME[0], buyerDeleteAccountField.getText())) { // TODO: MOVE TO SERVER
                        JOptionPane.showMessageDialog(null, "Account Deleted",
                                "Delete Account", JOptionPane.INFORMATION_MESSAGE);
                        for (Frame frame : Frame.getFrames()) {
                            if (frame instanceof JFrame) {
                                ((JFrame)frame).setVisible(false);
                            }
                        }
                        loginRegisterFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Deletion Failed",
                                "Delete Account", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END BUYER MANAGE ACCOUNT FRAME ACTION LISTENERS
            */

            /*
            BUYER SHOPPING CART ACTION LISTENERS
             */

            shoppingCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerShoppingCartFrame.setVisible(true);
                }
            });

            addToShoppingCart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerAddToShoppingCartFrame.setVisible(true);
                }
            });


            addToCart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean bool = true;
                    try{
                        Integer.parseInt(buyerShoppingCartQuantity.getText());
                    } catch(NumberFormatException f){
                        bool = false;
                    }
                    if(Customer.searchedStoreExists(buyerShoppingCartStoreName.getText(), stores) &&
                            Customer.searchedProductExists(buyerShoppingCartProductName.getText(), stores) && bool){
                        bool = Customer.addToCart(Account.getEmail(USERNAME[0]),
                                Account.getUsername(USERNAME[0]),
                                buyerShoppingCartStoreName.getText(), buyerShoppingCartProductName.getText(),
                                Integer.parseInt(buyerShoppingCartQuantity.getText()));
                        if (bool) {
                            JOptionPane.showMessageDialog(null, "Product Added to Cart!",
                                    "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
                         } else if (!bool) {
                            JOptionPane.showMessageDialog(null, "Something went wrong, " +
                                            "try again!", "Shopping Cart", JOptionPane.ERROR_MESSAGE);
                         }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Input",
                                "Shopping Cart", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            buyerViewAllAddToShoppingCart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerAddToShoppingCartFrame.setVisible(true);
                }
            });

            removeFromShoppingCart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerRemoveFromShoppingCartFrame.setVisible(true);
                }
            });

            removeFromCart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean bool = true;
                    try{
                        Integer.parseInt(buyerRemoveFromShoppingCartQuantity.getText());
                    } catch(NumberFormatException f){
                        bool = false;
                    }
                    if(Customer.searchedStoreExists(buyerRemoveFromShoppingCartStoreName.getText(), stores) &&
                            Customer.searchedProductExists(buyerRemoveFromShoppingCartProductName.getText(), stores)
                            && bool){
                        bool = Customer.removeFromCart(Account.getEmail(USERNAME[0]),
                                Account.getUsername(USERNAME[0]),
                                buyerRemoveFromShoppingCartStoreName.getText(),
                                buyerRemoveFromShoppingCartProductName.getText(),
                                Integer.parseInt(buyerRemoveFromShoppingCartQuantity.getText()));
                        if (bool) {
                            JOptionPane.showMessageDialog(null, "Product Removed From Cart!",
                                    "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
                        } else if (!bool) {
                            JOptionPane.showMessageDialog(null, "Something went wrong, " +
                                    "try again!", "Shopping Cart", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Input",
                                "Shopping Cart", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            editShoppingCartQty.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyerChangeQtyInShoppingCartFrame.setVisible(true);
                }
            });

            changeQtyInCart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean bool = true;
                    try{
                        Integer.parseInt(buyerChangeQtyInShoppingCart.getText());
                    } catch(NumberFormatException f){
                        bool = false;
                        System.out.println("integer parsing error");
                    }
                    if(Customer.searchedStoreExists(buyerChangeQtyInShoppingCartStoreName.getText(), stores) &&
                            Customer.searchedProductExists(buyerChangeQtyInShoppingCartProductName.getText(), stores)
                            && bool){
                        bool = Customer.addToCartChangeCheckoutQuantity(
                                buyerChangeQtyInShoppingCartStoreName.getText(),
                                buyerChangeQtyInShoppingCartProductName.getText(),
                                Integer.parseInt(buyerChangeQtyInShoppingCart.getText()));
                        if (bool) {
                            JOptionPane.showMessageDialog(null, "Product Removed From Cart!",
                                    "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
                        } else if (!bool) {
                            JOptionPane.showMessageDialog(null, "Something went wrong, " +
                                    "try again!", "Shopping Cart", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Input",
                                "Shopping Cart", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            /*
            END BUYER SHOPPING CART ACTION LISTENERS
             */
            while (!logOrRegistration) {
                System.out.println(welcomePrompt);
                System.out.println(loginPrompt);
                String input = scanner.nextLine().toLowerCase();
                isLoggedIn = false;
                switch (input) { // handles user case of login or register and directs to the next relevant step
                    // Authenticates User, begins use loop
                    case "login":
                        System.out.println("Input Username or Email: ");
                        String user = scanner.nextLine();
                        System.out.println("Enter your password: ");
                        String password = scanner.nextLine();
                        boolean bool = Account.login(user, password);
                        logOrRegistration = Account.login(user, password);
                        if (!logOrRegistration) {
                            System.out.println("Login Failed");
                            System.out.println("Try Again!");
                        } else if (logOrRegistration) {
                            isLoggedIn = true;
                            System.out.println("Login Successful!");
                        }

                        // Switch Var declarations
                        String storeName;
                        String productName;
                        double price;
                        int quantity;
                        boolean sorted;
                        String isSorted;
                        boolean isDouble = false;
                        boolean isInt = false;

                        // loops while definite user
                        while (isLoggedIn) {
                            //Seller Experience
                            if (Account.getRole(user) != null
                                    && Account.getRole(user).equalsIgnoreCase("seller")) {
                                System.out.print(sellerChoices);
                                input = scanner.nextLine();
                                switch (input) {
                                    //Create Store
                                    case "1":
                                        System.out.println("Enter a store name: ");
                                        storeName = scanner.nextLine();
                                        System.out.println("Enter a store location: ");
                                        String location = scanner.nextLine();
                                        bool = Seller.createStore(storeName, location, user);
                                        if (bool) {
                                            System.out.println("Successfully Created");
                                        } else {
                                            System.out.println("Creation Failed");
                                            System.out.println("Try Again!");
                                        }
                                        break;
                                    // Modify Store
                                    case "2":
                                        System.out.print(sellerModificationChoices);
                                        input = scanner.nextLine();
                                        switch (input) {
                                            // creates product
                                            case "1":
                                                System.out.println("Enter Store Name: ");
                                                storeName = scanner.nextLine();
                                                System.out.println("Enter Product Name: ");
                                                productName = scanner.nextLine();
                                                System.out.println("Enter Product Price: ");

                                                price = -0.1;
                                                isDouble = false;
                                                while (!isDouble) {
                                                    try {
                                                        price = Double.parseDouble(scanner.nextLine());
                                                        isDouble = true;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("Invalid Price!");
                                                        System.out.println("Enter Product Price: ");
                                                    }
                                                }

                                                System.out.println("Enter Product Quantity: ");
                                                quantity = -1;
                                                isInt = false;
                                                while (!isInt) {
                                                    try {
                                                        quantity = Integer.parseInt(scanner.nextLine());
                                                        if (quantity > 0) {
                                                            isInt = true;
                                                        } else {
                                                            System.out.println("Invalid Quantity!");
                                                            System.out.println("Enter Product Quantity: ");
                                                        }
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("Invalid Quantity!");
                                                        System.out.println("Enter Product Quantity: ");
                                                    }

                                                }
                                                System.out.println("Enter Product Description: ");
                                                String description = scanner.nextLine();
                                                bool = Seller.createProduct(storeName, productName, description,
                                                        price, quantity, user);
                                                if (bool) {
                                                    System.out.println("Successfully Added Product");
                                                } else {
                                                    System.out.println("Addition Failed");
                                                }
                                                break;
                                            // Edit Product Price
                                            case "2":
                                                System.out.println("Enter Store Name: ");
                                                storeName = scanner.nextLine();
                                                System.out.println("Enter Product Name: ");
                                                productName = scanner.nextLine();
                                                System.out.println("Enter New Product Price: ");
                                                price = -0.1;
                                                isDouble = false;
                                                while (!isDouble) {
                                                    try {
                                                        price = Double.parseDouble(scanner.nextLine());
                                                        isDouble = true;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("Invalid Price!");
                                                        System.out.println("Enter Product Price: ");
                                                    }
                                                }
                                                bool = Seller.editProductPrice(storeName,
                                                        productName, price, user);
                                                if (bool) {
                                                    System.out.println("Successfully edited");
                                                } else {
                                                    System.out.println("Edit Failed");
                                                }
                                                break;
                                            // Edit Product Quantity
                                            case "3":
                                                System.out.println("Enter Store Name: ");
                                                storeName = scanner.nextLine();
                                                System.out.println("Enter Product Name: ");
                                                productName = scanner.nextLine();
                                                System.out.println("Enter New Quantity: ");
                                                quantity = -1;
                                                isInt = false;
                                                while (!isInt) {
                                                    try {
                                                        quantity = Integer.parseInt(scanner.nextLine());
                                                        isInt = true;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("Error, Invalid Input");
                                                        System.out.println("Enter New Quantity: ");
                                                    }
                                                }
                                                bool = Seller.editProductQuantity(storeName, productName,
                                                        quantity, user);
                                                if (bool) {
                                                    System.out.println("Successfully Edited");
                                                } else {
                                                    System.out.println("Failed");
                                                }
                                                break;
                                            // Delete product
                                            case "4":
                                                bool = false;
                                                while (!bool) {
                                                    System.out.println("Enter Store Name: ");
                                                    storeName = scanner.nextLine();
                                                    System.out.println("Enter Product Name: ");
                                                    productName = scanner.nextLine();
                                                    bool = Seller.deleteProduct(storeName, productName, user);
                                                    if (bool) {
                                                        System.out.println("Successfully Deleted");
                                                    } else {
                                                        System.out.println("Deletion Failed");
                                                        System.out.println("Try Again!");
                                                    }
                                                }
                                                break;
                                            // add from CSV
                                            case "5":
                                                System.out.println("Enter Store Name: ");
                                                storeName = scanner.nextLine();
                                                System.out.println("Enter file path to be written from " +
                                                        "(include .csv)");
                                                String filePath = scanner.nextLine();
                                                Seller.readProductsFromCSV(storeName, filePath);
                                                break;
                                            // Trigger Sale
                                            case "6":
                                                System.out.println("Enter Store Name: ");
                                                storeName = scanner.nextLine();
                                                Store store = Seller.whichStore(storeName);
                                                System.out.println("Enter Product to Put on Sale: ");
                                                input = scanner.nextLine();
                                                System.out.println("Enter Sale price: ");
                                                isDouble = false;
                                                double salePrice = -0.1;
                                                while (!isDouble) {
                                                    try {
                                                        salePrice = Double.parseDouble(scanner.nextLine());
                                                        isDouble = true;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("Invalid Sale price!");
                                                        System.out.println("Enter Sale price: ");
                                                    }
                                                }
                                                System.out.println("Enter Quantity on sale: ");
                                                int numOnSale = -1;
                                                isInt = false;
                                                while (!isInt) {
                                                    try {
                                                        numOnSale = Integer.parseInt(scanner.nextLine());
                                                        isInt = true;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("Error, Invalid Input");
                                                        System.out.println("Enter Quantity on sale: ");
                                                    }
                                                }
                                                if (store.triggerSale(input, salePrice, numOnSale)) {
                                                    System.out.println("Sale Successfully Started!");
                                                } else {
                                                    System.out.println("Failed to start Sale!");
                                                }
                                                break;
                                            // put a purchase cap on a product
                                            case "7":
                                                System.out.println("Enter Store Name: ");
                                                storeName = scanner.nextLine();
                                                store = Seller.whichStore(storeName);
                                                System.out.println("Enter a product to restrict: ");
                                                input = scanner.nextLine();
                                                System.out.println("Enter Sales cap: ");
                                                int cap = -1;
                                                isInt = false;
                                                while (!isInt) {
                                                    try {
                                                        cap = Integer.parseInt(scanner.nextLine());
                                                        isInt = true;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("Error, Invalid Input");
                                                        System.out.println("Enter Sales cap: ");
                                                    }
                                                }
                                                scanner.nextLine();
                                                if (store.triggerOrderCap(input, cap)) {
                                                    System.out.println("Order Cap created successfully!");
                                                } else {
                                                    System.out.println("There was a problem " +
                                                            "implementing an order cap");
                                                }
                                                break;
                                            // back
                                            case "8":
                                                break;
                                            // handles anything else
                                            default:
                                                System.out.println("Invalid Option");
                                                break;
                                        }
                                        break;
                                    // Seller Stats menu
                                    case "3":
                                        System.out.print(sellerStatisticsChoices);
                                        input = scanner.nextLine();
                                        switch (input) {
                                            //View Customer Purchases
                                            case "1":
                                                System.out.println("Enter a store name: ");
                                                storeName = scanner.nextLine();
                                                System.out.println("Input Username: ");
                                                user = scanner.nextLine();
                                                System.out.println("Do you want to Sort the Products? (Y/N)");
                                                isSorted = scanner.nextLine();
                                                if (isSorted.equalsIgnoreCase("y")) {
                                                    sorted = true;
                                                } else if (isSorted.equalsIgnoreCase("n")) {
                                                    sorted = false;
                                                } else {
                                                    System.out.println("Invalid Input");
                                                    break;
                                                }
                                                System.out.println(Seller.getCustomersAndPurchases(storeName,
                                                        user, sorted));
                                                break;
                                            //View Product Sales
                                            case "2":
                                                System.out.println("Enter a store name: ");
                                                storeName = scanner.nextLine();
                                                System.out.println("Input Username: ");
                                                user = scanner.nextLine();
                                                System.out.println("Do you want to Sort the Products? (Y/N)");
                                                isSorted = scanner.nextLine();
                                                if (isSorted.equalsIgnoreCase("y")) {
                                                    sorted = true;
                                                } else if (isSorted.equalsIgnoreCase("n")) {
                                                    sorted = false;
                                                } else {
                                                    System.out.println("Invalid Input");
                                                    break;
                                                }
                                                if (Seller.getProductSales(storeName, user, sorted).equals(
                                                        "Customer " +
                                                                "Email | Customer Username | " +
                                                                "Store Name | Product Name | " +
                                                                "Quantity Purchased ")) {
                                                    System.out.println("No Product Sales Found!");
                                                } else {
                                                    System.out.println(Seller.getProductSales(storeName,
                                                            user, sorted));
                                                }

                                                break;
                                            //View Products in Shopping Cart
                                            case "3":
                                                System.out.println("Enter Username: ");
                                                user = scanner.nextLine();
                                                System.out.println(Seller.getShoppingCartProducts(user));
                                                break;
                                            //Get a CSV with all the Products in a Store
                                            case "4":
                                                System.out.println("Enter Store Name: ");
                                                storeName = scanner.nextLine();
                                                System.out.println("Enter file path to be written to (include .csv)");
                                                String filePath = scanner.nextLine();
                                                boolean check = Seller.writeProductsToCSV(storeName, filePath);
                                                if (check) {
                                                    System.out.println("Written to Successfully");
                                                } else {
                                                    System.out.println("Failed");
                                                }
                                                break;
                                            //Goes back to the Main menu
                                            case "5":
                                                break;
                                            default:
                                                System.out.println("Invalid Input");
                                                break;
                                        }
                                        break;
                                    //Delete Store
                                    case "4":
                                        bool = false;
                                        while (!bool) {
                                            System.out.println("Enter a store name: ");
                                            storeName = scanner.nextLine();
                                            System.out.println("Input Username: ");
                                            user = scanner.nextLine();
                                            bool = Seller.deleteStore(storeName, user);
                                            if (bool) {
                                                System.out.println("Successfully Deleted");
                                            } else {
                                                System.out.println("Deletion Failed");
                                                System.out.println("Try Again!");
                                            }
                                        }
                                        break;
                                    //View Customer Reviews
                                    case "5":
                                        System.out.println("Enter Product Name (Leave empty if you want to " +
                                                "view reviews of all products)");
                                        productName = scanner.nextLine();
                                        if (Seller.viewCustomerReviews(productName, user).equals("Store Name | " +
                                                "Product Name | " +
                                                "Customer Name | " +
                                                "Rating ")) {
                                            System.out.println("Could not find any reviews!");
                                        } else {
                                            System.out.print(Seller.viewCustomerReviews(productName, user));
                                        }
                                        break;
                                    //Manage the Seller's account
                                    case "6":
                                        System.out.print(accountChoices);
                                        input = scanner.nextLine();
                                        switch (input) {
                                            //Change Username
                                            case "1":
                                                bool = false;
                                                while (!bool) {
                                                    System.out.println("Enter Old Username");
                                                    String oldUsername = scanner.nextLine();
                                                    System.out.println("Enter New Username");
                                                    String newUsername = scanner.nextLine();
                                                    System.out.println("Enter New Username Again");
                                                    String newUsernameCheck = scanner.nextLine();
                                                    while (!(newUsername.equals(newUsernameCheck))) {
                                                        System.out.println("Errors, new usernames do not match");
                                                        System.out.println("Enter New Username");
                                                        newUsername = scanner.nextLine();
                                                        System.out.println("Enter New Username Again");
                                                        newUsernameCheck = scanner.nextLine();
                                                    }
                                                    bool = Account.changeUsername(newUsername, oldUsername);
                                                    if (bool) {
                                                        System.out.println("Successfully Changed Username");
                                                    } else {
                                                        System.out.println("Change Failed");
                                                        System.out.println("Try Again!");
                                                    }
                                                }
                                                break;
                                            //Change Password
                                            case "2":
                                                bool = false;
                                                while (!bool) {
                                                    System.out.println("Input Username or Email: ");
                                                    user = scanner.nextLine();
                                                    System.out.println("Enter Old Password: ");
                                                    String oldPassword = scanner.nextLine();
                                                    System.out.println("Enter New Password: ");
                                                    String newPassword = scanner.nextLine();
                                                    System.out.println("Enter New Password Again");
                                                    String newPasswordCheck = scanner.nextLine();
                                                    while (!(newPassword.equals(newPasswordCheck))) {
                                                        System.out.println("Error, new passwords do not match");
                                                        System.out.println("Enter New Password: ");
                                                        newPassword = scanner.nextLine();
                                                        System.out.println("Enter New Password Again");
                                                        newPasswordCheck = scanner.nextLine();
                                                    }
                                                    bool = Account.changePassword(user, oldPassword, newPassword);
                                                    if (bool) {
                                                        System.out.println("Successfully Changed Password");
                                                    } else {
                                                        System.out.println("Change Failed");
                                                        System.out.println("Try Again!");
                                                    }
                                                }
                                                break;
                                            //Change Role
                                            case "3":
                                                bool = false;
                                                while (!bool) {
                                                    System.out.println("Input Username or Email: ");
                                                    user = scanner.nextLine();
                                                    System.out.println("Enter Password: ");
                                                    password = scanner.nextLine();
                                                    System.out.println("Enter New Role: ");
                                                    String newRole = scanner.nextLine();
                                                    bool = Account.changeRole(user, password, newRole);
                                                    if (bool) {
                                                        System.out.println("Successfully Changed Role");
                                                    } else {
                                                        System.out.println("Change Failed");
                                                        System.out.println("Try Again!");
                                                    }
                                                }
                                                break;
                                            //Delete Account
                                            case "4":
                                                bool = false;
                                                while (!bool) {
                                                    System.out.println("Input Username or Email: ");
                                                    user = scanner.nextLine();
                                                    System.out.println("Enter Password: ");
                                                    password = scanner.nextLine();
                                                    bool = Account.deleteAccount(user, password);
                                                    if (bool) {
                                                        System.out.println("Successfully Deleted");
                                                        isLoggedIn = false;
                                                    } else {
                                                        System.out.println("Deletion Failed");
                                                        System.out.println("Try Again!");
                                                    }
                                                }
                                                break;
                                            case "5":
                                                break;
                                            default:
                                                System.out.println("Invalid Input");
                                                break;
                                        }
                                        break;
                                    //Logout
                                    case "7":
                                        isLoggedIn = false;
                                        System.out.println("Successfully Logged out");
                                        break;
                                    default:
                                        System.out.println("Invalid Input");
                                        break;
                                }
                                // Customer Experience
                            } else if (Account.getRole(user) != null
                                    && Account.getRole(user).equalsIgnoreCase("customer")) {
                                System.out.print(buyerprompt);
                                input = scanner.nextLine();
                                switch (input) {
                                    //Search for a Store
                                    case "1":
                                        System.out.println(searchPrompt);
                                        input = scanner.nextLine();
                                        System.out.println(Seller.searchByStore(input));
                                        break;
                                    //Search for a Product
                                    case "2":
                                        System.out.println(searchPrompt);
                                        input = scanner.nextLine();
                                        System.out.println(Seller.searchByProduct(input));
                                        break;
                                    //Search for a Product by Description
                                    case "3":
                                        System.out.println(searchPrompt);
                                        input = scanner.nextLine();
                                        System.out.println(Seller.searchByDescription(input));
                                        break;
                                    //View All Products
                                    case "4":
                                        Seller.printProductAndStores();
                                        System.out.println("Which Product would you like to view?");
                                        input = scanner.nextLine();
                                        String result = Seller.searchByProduct(input);
                                        if (result.isEmpty() || result == null) {
                                            System.out.println("No Products Available for Listing");
                                        } else {
                                            System.out.println(result);
                                        }
                                        break;
                                    //Sort Products by Cheapest
                                    case "5":
                                        System.out.println(Seller.sortCheapest());
                                        break;
                                    //Sort Products by Most Expensive
                                    case "6":
                                        System.out.println(Seller.sortExpensive());
                                        break;
                                    //Sort Products by Availability
                                    case "7":
                                        System.out.println(availability);
                                        input = scanner.nextLine();
                                        switch (input) {
                                            //Sort Products by Most in Stock
                                            case "1":
                                                System.out.println(Seller.highestQuant());
                                                break;
                                            //Sort Products By Least in stock
                                            case "2":
                                                System.out.println(Seller.lowestQuant());
                                                break;
                                        }
                                        break;
                                    // shopping cart
                                    case "8":
                                        System.out.print(customerShoppingCartChoices);
                                        input = scanner.nextLine();
                                        switch (input) {
                                            case "1": // add product to cart
                                                System.out.println("Enter the store name of the product " +
                                                        "you want to add to cart: ");
                                                storeName = scanner.nextLine();
                                                if (Customer.searchedStoreExists(storeName, stores)) {
                                                    bool = false;
                                                    System.out.println("Enter the name of the product " +
                                                            "you want to add to cart: ");
                                                    productName = scanner.nextLine();
                                                    if (Customer.searchedProductExists(productName, stores)) {
                                                        quantity = -1;
                                                        System.out.printf("Enter how many %s would you " +
                                                                        "like to buy: \n"
                                                                , productName);
                                                        isInt = false;
                                                        while (!isInt) {
                                                            try {
                                                                quantity = Integer.parseInt(scanner.nextLine());
                                                                isInt = true;
                                                            } catch (NumberFormatException e) {
                                                                System.out.println("Error, Invalid Input");
                                                                System.out.printf("Enter how many %s would " +
                                                                        "you like to buy: \n", productName);
                                                            }
                                                        }
                                                        bool = Customer.addToCart(Account.getEmail(user),
                                                                Account.getUsername(user),
                                                                storeName, productName, quantity);
                                                    }
                                                    if (bool) {
                                                        System.out.println("Product added to cart!");
                                                    } else if (!bool) {
                                                        System.out.println("Error, could not find Product!");
                                                    }
                                                } else {
                                                    System.out.println("Error, could not find store!");
                                                }

                                                break;
                                            case "2":
                                                System.out.println("Enter store of the product whose checkout " +
                                                        "quantity you want to change");
                                                storeName = scanner.nextLine();
                                                System.out.println("Enter name of the product whose " +
                                                        "quantity you want to change");
                                                productName = scanner.nextLine();
                                                System.out.println("Enter amount you want to change by (include " +
                                                        "- if you want to reduce)");
                                                quantity = Integer.parseInt(scanner.nextLine());
                                                bool = Customer.addToCartChangeCheckoutQuantity(
                                                        storeName, productName, quantity);
                                                if (bool) {
                                                    System.out.println("Change Successful");
                                                } else if (!bool) {
                                                    System.out.println("Change Failed, Invalid Checkout Quantity!");
                                                }
                                                break;
                                            case "3": // remove product from cart
                                                System.out.println("Enter the store name of " +
                                                        "the product you want to " +
                                                        "remove from cart: ");
                                                storeName = scanner.nextLine();
                                                if (Customer.searchedStoreExists(storeName, stores)) {
                                                    System.out.println("Enter the name of the product " +
                                                            "you want to " +
                                                            "remove from cart: ");
                                                    productName = scanner.nextLine();
                                                    if (Customer.searchedProductExists(productName, stores)) {
                                                        System.out.printf("Enter how many %s would " +
                                                                "you like to " +
                                                                "remove: \n", productName);
                                                        quantity = -1;
                                                        isInt = false;
                                                        while (!isInt) {
                                                            try {
                                                                quantity = Integer.parseInt(scanner.nextLine());
                                                                isInt = true;
                                                            } catch (NumberFormatException e) {
                                                                System.out.println("Error, Invalid Input");
                                                                System.out.printf("Enter how many %s " +
                                                                                "would you like " +
                                                                                "to remove: \n"
                                                                        , productName);
                                                            }
                                                        }
                                                        boolean productRemoved = Customer.removeFromCart(
                                                                Account.getEmail(user),
                                                                Account.getUsername(user),
                                                                storeName, productName, quantity);
                                                        if (productRemoved) {
                                                            System.out.println("Product removed from cart");
                                                        } else {
                                                            System.out.println("Product is not in cart");
                                                        }
                                                    }
                                                }
                                                break;
                                            case "4": // buy products in cart
                                                bool = Customer.buyProductsInShoppingCart(Account.getUsername(user));
                                                ArrayList<String> check = Customer.getShoppingCartofCustomer(user);
                                                while (!check.isEmpty()) {
                                                    bool = Customer.buyProductsInShoppingCart(
                                                            Account.getUsername(user));
                                                    check = Customer.getShoppingCartofCustomer(user);
                                                }
                                                if (bool) {
                                                    System.out.println("Products in cart purchased successfully!");
                                                } else if (!bool) {
                                                    System.out.println("Checkout failed");
                                                }
                                                break;
                                            case "5": // view shopping cart
                                                for (String s : Customer.getShoppingCartofCustomer(user)) {
                                                    String[] view = s.split(";");
                                                    System.out.println("Customer Name | Store Name " +
                                                            "| Product Name | Qty");
                                                    String output =
                                                            view[1] + " | " + view[2] +
                                                                    " | " + view[3] + " | " + view[4];
                                                    System.out.println(output);
                                                }
                                                break;
                                            case "6":
                                                break;
                                            default:
                                                break;
                                        }
                                        break;
                                    case "9":
                                        System.out.println("Enter Filename to be exported to (include .txt)");
                                        String filename = scanner.nextLine();
                                        bool = Customer.getPurchaseHistoryofCustomer(user, filename);
                                        if (bool) {
                                            System.out.println("Exported Successfully!");
                                        } else if (!bool) {
                                            System.out.println("Export Failed");
                                        }
                                        break;
                                    //Leave Review
                                    case "10":
                                        bool = false;
                                        int rating = 5;
                                        System.out.println("Name of Product reviewing: ");
                                        productName = scanner.nextLine();
                                        System.out.println("Name of Store Product is from: ");
                                        storeName = scanner.nextLine();
                                        System.out.println("Enter a rating between 1-5");
                                        isInt = false;
                                        while (!isInt) {
                                            try {
                                                rating = Integer.parseInt(scanner.nextLine());
                                                isInt = true;
                                            } catch (NumberFormatException e) {
                                                System.out.println("Error, Invalid Input");
                                                System.out.println("Enter a rating between 1-5");
                                            }
                                        }
                                        System.out.println("Review: ");
                                        String description = scanner.nextLine();
                                        boolean check = Customer.leaveReview(storeName,
                                                productName, user, rating, description);
                                        if (check) {
                                            System.out.println("Review Left Successfully");
                                        } else if (!check) {
                                            System.out.println("Failed");
                                        }
                                        break;
                                    //View Reviews of Products
                                    case "11":
                                        System.out.println("Enter Products Name to view Review");
                                        productName = scanner.nextLine();
                                        System.out.println("Enter Store Name (Leave Blank " +
                                                "to view all stores selling " +
                                                "that product)");
                                        storeName = scanner.nextLine();
                                        System.out.print(Customer.viewReviews(storeName, productName));
                                        break;
                                    //Manage Customer Account
                                    case "12":
                                        System.out.print(accountChoices);
                                        input = scanner.nextLine();
                                        switch (input) {
                                            //Change Username
                                            case "1":
                                                bool = false;
                                                while (!bool) {
                                                    System.out.println("Enter Old Username");
                                                    String oldUsername = scanner.nextLine();
                                                    System.out.println("Enter New Username");
                                                    String newUsername = scanner.nextLine();
                                                    System.out.println("Enter New Username Again");
                                                    String newUsernameCheck = scanner.nextLine();
                                                    while (!(newUsername.equals(newUsernameCheck))) {
                                                        System.out.println("Errors, new usernames do not match");
                                                        System.out.println("Enter New Username");
                                                        newUsername = scanner.nextLine();
                                                        System.out.println("Enter New Username Again");
                                                        newUsernameCheck = scanner.nextLine();
                                                    }
                                                    bool = Account.changeUsername(newUsername, oldUsername);
                                                    if (bool) {
                                                        System.out.println("Successfully Changed Username");
                                                    } else {
                                                        System.out.println("Change Failed");
                                                        System.out.println("Try Again!");
                                                    }
                                                }
                                                break;
                                            //Change Password
                                            case "2":
                                                bool = false;
                                                while (!bool) {
                                                    System.out.println("Input Username or Email: ");
                                                    user = scanner.nextLine();
                                                    System.out.println("Enter Old Password: ");
                                                    String oldPassword = scanner.nextLine();
                                                    System.out.println("Enter New Password: ");
                                                    String newPassword = scanner.nextLine();
                                                    System.out.println("Enter New Password Again");
                                                    String newPasswordCheck = scanner.nextLine();
                                                    while (!(newPassword.equals(newPasswordCheck))) {
                                                        System.out.println("Error, new passwords do not match");
                                                        System.out.println("Enter New Password: ");
                                                        newPassword = scanner.nextLine();
                                                        System.out.println("Enter New Password Again");
                                                        newPasswordCheck = scanner.nextLine();
                                                    }
                                                    bool = Account.changePassword(user, oldPassword, newPassword);
                                                    if (bool) {
                                                        System.out.println("Successfully Changed Password");
                                                    } else {
                                                        System.out.println("Change Failed");
                                                        System.out.println("Try Again!");
                                                    }
                                                }
                                                break;
                                            //Change Role
                                            case "3":
                                                bool = false;
                                                while (!bool) {
                                                    System.out.println("Input Username or Email: ");
                                                    user = scanner.nextLine();
                                                    System.out.println("Enter Password: ");
                                                    password = scanner.nextLine();
                                                    System.out.println("Enter New Role: ");
                                                    String newRole = scanner.nextLine();
                                                    bool = Account.changeRole(user, password, newRole);
                                                    if (bool) {
                                                        System.out.println("Successfully Changed");
                                                    } else {
                                                        System.out.println("Change Failed");
                                                        System.out.println("Try Again!");
                                                    }
                                                }
                                                break;
                                            //Delete Account
                                            case "4":
                                                bool = false;
                                                while (!bool) {
                                                    System.out.println("Input Username or Email: ");
                                                    user = scanner.nextLine();
                                                    System.out.println("Enter Password: ");
                                                    password = scanner.nextLine();
                                                    bool = Account.deleteAccount(user, password);
                                                    if (bool) {
                                                        System.out.println("Successfully Deleted");
                                                        isLoggedIn = false;
                                                    } else {
                                                        System.out.println("Deletion Failed");
                                                        System.out.println("Try Again!");
                                                    }
                                                }
                                                break;
                                            case "5":
                                                break;
                                            default:
                                                System.out.println("Invalid Input");
                                                break;
                                        }
                                        break;
                                    //Logout
                                    case "13":
                                        isLoggedIn = false;
                                        System.out.println("Sucessfully Logged out");
                                        break;
                                    default:
                                        System.out.println("Invalid Input");
                                        break;
                                }

                            }
                        }
                        break;
                    //Handles User Registration
                    case "register":
                        System.out.println("Enter an email: ");
                        String email = scanner.nextLine();
                        System.out.println("Input Username: ");
                        user = scanner.nextLine();
                        System.out.println("Enter your password: ");
                        password = scanner.nextLine();
                        System.out.println("Enter your role (customer / seller)");
                        String role = scanner.nextLine();
                        while (!(role.equalsIgnoreCase("customer") ||
                                role.equalsIgnoreCase("seller"))) {
                            System.out.println("Invalid Input!");
                            System.out.println("Enter your role (customer / seller)");
                            role = scanner.nextLine();
                        }
                        logOrRegistration = Account.createAccount(email, user, password, role);
                        if (logOrRegistration) {
                            System.out.println("Account Made Successfully");
                        } else if (!logOrRegistration) {
                            System.out.println("Account Creation failed. Invalid Credentials");
                        }
                        break;
                    case ("exit"):
                        System.out.println("Thank You For Using Our Fruit Market!");
                        return;

                    default:
                        System.out.println("Invalid Input");
                }
            }
        } while (true);
    }
    public static void searchByStore(boolean visible, String buyerSearchedStore, JButton searchForStoreButton,
                                     JFrame buyerSearchByStoreFrame, JPanel buyerSearchByStorePanel){
        /*
            BUYER SEARCH BY STORE FRAME
             */
        buyerSearchByStorePanel.removeAll();
        buyerSearchByStorePanel.repaint();
        buyerSearchByStorePanel.revalidate();
        buyerSearchByStorePanel.setLayout(new BorderLayout());
        String[] coloumnSearchStores = Seller.searchByStore(buyerSearchedStore).split("\n");
        String[][] temp = new String[coloumnSearchStores.length][2];
        String[] columnNames = new String[]{"Store Name", "Product Name"};
        JTable searchedStoreTable = new JTable(temp, columnNames);
        for (int i = 0; i < coloumnSearchStores.length; i++) {
            String[] row = coloumnSearchStores[i].split(";");
            for (int j = 0; j < row.length; j++) {
                searchedStoreTable.setValueAt(row[j], i, j);
            }
        }
        buyerSearchByStorePanel.add(new JScrollPane(searchedStoreTable));
        buyerSearchByStorePanel.add(searchForStoreButton, BorderLayout.SOUTH);
        buyerSearchByStoreFrame.add(buyerSearchByStorePanel);
        buyerSearchByStoreFrame.pack();
        buyerSearchByStoreFrame.setLocationRelativeTo(null);
        if(visible){
            buyerSearchByStoreFrame.setVisible(true);
        } else{
            buyerSearchByStoreFrame.setVisible(false);
        }

            /*
            END BUYER SEARCH BY STORE FRAME
             */
    }

    public static void searchByDescription(boolean visible, String buyerSearchedDescription,
                                           JButton searchProductByDescriptionButton,
                                           JFrame buyerSearchByDescriptionFrame, JPanel buyerSearchByDescriptionPanel){
        buyerSearchByDescriptionPanel.removeAll();
        buyerSearchByDescriptionPanel.repaint();
        buyerSearchByDescriptionPanel.revalidate();
        buyerSearchByDescriptionPanel.setLayout(new BorderLayout());
        String[] coloumnSearchDescription = Seller.searchByDescription(buyerSearchedDescription).split("\n");
        String[][] temp = new String[coloumnSearchDescription.length][4];
        String[] columnNames = new String[]{"Store Name", "Product Name", "Purchase Price", "Quantity in Stock"};
        JTable searchedDescriptionTable = new JTable(temp, columnNames);
        for (int i = 0; i < coloumnSearchDescription.length; i++) {
            String[] row = coloumnSearchDescription[i].split(";");
            for (int j = 0; j < row.length; j++) {
                searchedDescriptionTable.setValueAt(row[j], i, j);
            }
        }
        buyerSearchByDescriptionPanel.add(new JScrollPane(searchedDescriptionTable));
        JButton searchForStoreButtonCopy = new JButton("Search for Product");
        buyerSearchByDescriptionPanel.add(searchProductByDescriptionButton, BorderLayout.SOUTH);
        buyerSearchByDescriptionFrame.add(buyerSearchByDescriptionPanel);
        buyerSearchByDescriptionFrame.pack();
        buyerSearchByDescriptionFrame.setLocationRelativeTo(null);
        if(visible){
            buyerSearchByDescriptionFrame.setVisible(true);
        } else{
            buyerSearchByDescriptionFrame.setVisible(false);
        }
    }

    public static void searchByProduct(boolean visible, String buyerSearchedProduct,
                                       JButton searchForProductButton,
                                       JFrame buyerSearchByProductFrame, JPanel buyerSearchByProductPanel){
        buyerSearchByProductPanel.removeAll();
        buyerSearchByProductPanel.repaint();
        buyerSearchByProductPanel.revalidate();
        buyerSearchByProductPanel.setLayout(new BorderLayout());
        String[] coloumnSearchProduct = Seller.searchByProduct(buyerSearchedProduct).split("\n");
        String[][] temp = new String[coloumnSearchProduct.length][4];
        String[] columnNames = new String[]{"Store Name", "Product Name", "Purchase Price", "Quantity in Stock"};
        JTable searchedProductTable = new JTable(temp, columnNames);
        for (int i = 0; i < coloumnSearchProduct.length; i++) {
            String[] row = coloumnSearchProduct[i].split(";");
            for (int j = 0; j < row.length; j++) {
                searchedProductTable.setValueAt(row[j], i, j);
            }
        }
        buyerSearchByProductPanel.add(new JScrollPane(searchedProductTable));
        buyerSearchByProductPanel.add(searchForProductButton, BorderLayout.SOUTH);
        buyerSearchByProductFrame.add(buyerSearchByProductPanel);
        buyerSearchByProductFrame.pack();
        buyerSearchByProductFrame.setLocationRelativeTo(null);
        if(visible){
            buyerSearchByProductFrame.setVisible(true);
        } else{
            buyerSearchByProductFrame.setVisible(false);
        }

    }

    public static void buyerDisplayReviews(boolean visible, JTextField buyerViewReviewsStoreName,
                                           JTextField buyerViewReviewsProductName,
                                           JFrame buyerDisplayReviewsFrame, JPanel buyerDisplayReviewsPanel){
        buyerDisplayReviewsPanel.removeAll();
        buyerDisplayReviewsPanel.repaint();
        buyerDisplayReviewsPanel.revalidate();
        buyerDisplayReviewsPanel.setLayout(new BorderLayout());
        String[] buyerViewReviewsColoumn = Customer.viewReviews(buyerViewReviewsStoreName.getText(),
                buyerViewReviewsProductName.getText()).split("\n");
        String[][] temp = new String[buyerViewReviewsColoumn.length][5];
        String[] columnNames = new String[]{"Store Name", "Product Name", "Customer Username/Email", "Rating", "Review"};
        JTable buyerViewReviewsTable = new JTable(temp, columnNames);
        for (int i = 0; i < buyerViewReviewsColoumn.length; i++) {
            String[] row = buyerViewReviewsColoumn[i].split(";");
            System.out.println(buyerViewReviewsColoumn.length);
            System.out.println(row.length);
            for (int j = 0; j < row.length; j++) {
                buyerViewReviewsTable.setValueAt(row[j], i, j);
            }
        }
        buyerDisplayReviewsPanel.add(new JScrollPane(buyerViewReviewsTable));
        buyerDisplayReviewsFrame.add(buyerDisplayReviewsPanel);
        buyerDisplayReviewsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        buyerDisplayReviewsFrame.pack();
        buyerDisplayReviewsFrame.setLocationRelativeTo(null);
        if(visible){
            buyerDisplayReviewsFrame.setVisible(true);
        } else{
            buyerDisplayReviewsFrame.setVisible(false);
        }
    }

    public static void sellerDisplayReviews(boolean visible, JTextField sellerViewReviewsProductName, String username,
                                            JFrame sellerDisplayReviewsFrame, JPanel sellerDisplayReviewsPanel){
        sellerDisplayReviewsPanel.removeAll();
        sellerDisplayReviewsPanel.repaint();
        sellerDisplayReviewsPanel.revalidate();
        sellerDisplayReviewsPanel.setLayout(new BorderLayout());
        String[] sellerViewReviewsColoumn = Seller.viewCustomerReviews(sellerViewReviewsProductName.getText(),
                username).split("\n");
        String[][] temp = new String[sellerViewReviewsColoumn.length][5];
        String[] columnNames = new String[]{"Store Name", "Product Name", "Customer Username/Email", "Rating", "Review"};
        JTable buyerViewReviewsTable = new JTable(temp, columnNames);
        for (int i = 0; i < sellerViewReviewsColoumn.length; i++) {
            String[] row = sellerViewReviewsColoumn[i].split(";");
            System.out.println(sellerViewReviewsColoumn.length);
            System.out.println(row.length);
            for (int j = 0; j < row.length; j++) {
                buyerViewReviewsTable.setValueAt(row[j], i, j);
            }
        }
        sellerDisplayReviewsPanel.add(new JScrollPane(buyerViewReviewsTable));
        sellerDisplayReviewsFrame.add(sellerDisplayReviewsPanel);
        sellerDisplayReviewsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        sellerDisplayReviewsFrame.pack();
        sellerDisplayReviewsFrame.setLocationRelativeTo(null);
        if(visible){
            sellerDisplayReviewsFrame.setVisible(true);
        } else{
            sellerDisplayReviewsFrame.setVisible(false);
        }
    }
}
