package com.algowizards.finalpricetracker;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.List;

import com.opencsv.exceptions.CsvException;
import org.jline.terminal.*;
import org.jline.utils.Display;

import java.io.IOException;

/**
 *
 * @author AlgoWizards
 * 
 * Class Description: This class will contain all methods used to display the final output
 * on the console that appears to the end user.
 * 
 */

public class Outputter
{
    
    // Instance variables
    private Terminal TERMINAL;
    private Display DISPLAY;

    static Scanner keyboard = new Scanner(System.in);

    static int mainMenuChoice = 0;
    static int productMenuChoice = 0;
    static char searchMenuChoice = 'Y';
    static int shoppingCartMenuChoice = 0;
    static boolean needsUpdate = false;

    // Pre-manager 2D lists of String
    static DataStructure.List2D<String> lookupItem = null;
    static DataStructure.List2D<String> lookupPremise = null;
    static DataStructure.List2D<String> priceCatcher = null;

    // List of products and map of products
    static DataStructure.List1D<Product> listOfProducts = null;
    static DataStructure.Mapping<Integer, Product> mapOfProducts = null;

    // List of premises and map of premises
    static DataStructure.List1D<Premise> listOfPremises = null;
    static DataStructure.Mapping<Integer, Premise> mapOfPremises = null;

    // List of price catchers
    static DataStructure.List1D<PriceCatcher> listOfPriceCatchers = null;

    // Data structures used in browse product by categories
    // Group data structures
    static DataStructure.List1D<String> productGroupList = null;
    static DataStructure.Mapping<Integer, String> productGroupMapping = null;

    // Category (subgroup) data structures
    static DataStructure.List1D<String> productCategoryList = null;
    static DataStructure.Mapping<Integer, String> productCategoryMapping = null;

    // Categorised product data structures
    static DataStructure.List1D<Product> categorisedProductList = null;
    static DataStructure.Mapping<Integer, Integer> categorisedProductMapping = null;

    // Constructors
    public Outputter()
    {
        
        try
        {
            
            TERMINAL = TerminalBuilder.builder().system(true).build();
            
            DISPLAY = new Display(TERMINAL, false);
            
        } catch (IOException exception)
        {
            
            throw new RuntimeException("Error initializing terminal", exception);
            
        }
        
    }
    
    // Getter and setter methods

    // Other methods
    static void mainMenu() throws IOException, CsvException // The method that calls to display the main menu of the program
    {

        while (mainMenuChoice != 6)
        {

            System.out.println(FontColor.ansiYellow + "\n-----= Main Menu =-----\n" + FontColor.ansiReset);
            System.out.println("Choose an option from the menu below: \n");

            System.out.println("1. Reimport Data into PriceWizard");
            System.out.println("2. Browse Product by Categories");
            System.out.println("3. Search for a Product");
            System.out.println("4. View Shopping Cart");
            System.out.println("5. Account Settings");
            System.out.println("6. Sign Out of PriceWizard Account");

            System.out.print("\n> Enter your choice (1/2/3/4/5/6): ");

            mainMenuChoice = keyboard.nextInt();

            System.out.println();

            switch(mainMenuChoice)
            {

                case 1:
                {

                    // 1. Import Data (File ---> Program stage)
                    FileManager.importAndPopulateData();

                    break;

                }

                case 2:
                {

                    // 2. Browse Product by Categories

                    int groupNumber = 1;

                    if (true) // check if the product group list and group mapping are null (never been generated before) or needs an update
                    {

                        productGroupList = ProductManager.getProductGroupList();
                        productGroupMapping = ProductManager.getProductGroupMapping(productGroupList);

                        needsUpdate = false;

                    }

                    System.out.println(FontColor.ansiYellow + "-----= Browse Product by Categories =-----\n" + FontColor.ansiReset);
                    System.out.println("Choose a category:\n");

                    for (Integer currentKey : productGroupMapping.getKeys())
                    {

                        System.out.println(currentKey + ". " + productGroupMapping.getValueByKey(currentKey));

                    }

                    System.out.print("\n> Choose a category: ");

                    int chosenGroup = keyboard.nextInt();
                    String chosenGroupString = productGroupMapping.getValueByKey(chosenGroup);

                    System.out.println("\nYou've chosen: " + chosenGroupString + "\n");

                    // subcategory
                    productCategoryList = ProductManager.getProductCategoryList(chosenGroupString);
                    productCategoryMapping = ProductManager.getProductCategoryMapping(productCategoryList);

                    System.out.println("The subcategories of " + chosenGroupString + " are:\n");

                    for (Integer currentKey : productCategoryMapping.getKeys())
                    {

                        System.out.println(currentKey + ". " + productCategoryMapping.getValueByKey(currentKey));

                    }

                    System.out.print("\n> Choose a subcategory: ");

                    int chosenCategory = keyboard.nextInt();
                    String chosenCategoryString = productCategoryMapping.getValueByKey(chosenCategory);

                    System.out.println("\nYou've chosen: " + chosenCategoryString + "\n");

                    // getting categorised product
                    categorisedProductList = ProductManager.getCategorisedProductList(chosenCategoryString);
                    categorisedProductMapping = ProductManager.getCategorisedProductMapping(categorisedProductList);

                    System.out.println("-------------------------------------------");
                    System.out.println(FontColor.ansiBlue + "\n Browse products in " + chosenGroupString + " >> " + chosenCategoryString + ":\n" + FontColor.ansiReset);

                    for (Integer currentKey : categorisedProductMapping.getKeys())
                    {

                        System.out.println(currentKey + ". " + ProductManager.getProductByKey(categorisedProductMapping.getValueByKey(currentKey)).getItemName() + " " + ProductManager.getProductByKey(categorisedProductMapping.getValueByKey(currentKey)).getUnit());

                    }

                    System.out.print("\n> Choose a product: ");

                    int chosenProductNumber = keyboard.nextInt();
                    Product chosenProduct = ProductManager.getProductByKey(categorisedProductMapping.getValueByKey(chosenProductNumber));
                    String nameOfChosenProduct = chosenProduct.getItemName();

                    System.out.println("\nYou've chosen: " + nameOfChosenProduct + " " + chosenProduct.getUnit() + "\n");

                    productMenu(chosenProduct);

                    break;

                }

                case 3:
                {

                    searchMenu();

                    break;

                }

                case 4:
                {

                    viewShoppingCart();

                    break;

                }

                case 5:
                {

                    Settings.main(null);

                    break;

                }

                case 6:
                {

                    System.out.println(FontColor.ansiGreen + "Thank you for using PriceWizard!\n");
                    System.out.println("Signing out of your account...\n" + FontColor.ansiReset);

                    UserManager.getCurrentUser().getShoppingCartList().clear();
                    UserManager.setCurrentUser(null);

                    break;

                }

                default:
                {

                    System.out.println("The integer representing the choice is not within 1 to 6, please try again...");

                }

            }

        }

        mainMenuChoice = 0;

    }

    static void productMenu(Product chosenProduct) throws IOException
    {

        while (productMenuChoice != 6)
        {

            System.out.println(FontColor.ansiBlue + "-----= Product: " + chosenProduct.getItemName() + " " + chosenProduct.getUnit() + " =-----\n" + FontColor.ansiReset);

            System.out.println(chosenProduct.getItemGroup() + " >> " + chosenProduct.getItemCategory() + " >> " + chosenProduct.getItemName() + " " +  chosenProduct.getUnit() + "\n");
            System.out.println("Actions:\n");

            System.out.println("1. View this product's details");
            System.out.println("2. Modify this product's details");
            System.out.println("3. View the top 5 cheapest sellers of this product");
            System.out.println("4. View the trend of the price of this product");
            System.out.println("5. Add/remove quantities of this product to the shopping cart");
            System.out.println("6. Go back to the previous menu");

            System.out.print("\n> Select an action: ");
            productMenuChoice = keyboard.nextInt();

            System.out.println();

            switch (productMenuChoice)
            {

                case 1:
                {

                    viewProductDetails(chosenProduct);

                    break;

                }

                case 2:
                {

                    modifyProductDetails(chosenProduct);

                    break;

                }

                case 3:
                {

                    PriceCatcherManager.viewTopFiveCheapestSellers(chosenProduct);

                    break;

                }

                case 4:
                {

                    PriceCatcherManager.displayPriceTrendGraph(chosenProduct);

                    break;

                }

                case 5:
                {

                    if (UserManager.getCurrentUser().getShoppingCartList().contains(chosenProduct))
                    {

                        System.out.println("Current Quantities of this Product in Shopping Cart: " + UserManager.getCurrentUser().getShoppingCartList().get(UserManager.getCurrentUser().getShoppingCartList().indexOf(chosenProduct)).getQuantity() + "\n");

                    }

                    System.out.print("> Enter the quantity of this product to be added/removed to the shopping cart (leave blank for +1): ");

                    keyboard.nextLine(); // Consume the newline character above

                    String quantityString = keyboard.nextLine();

                    if (quantityString.isEmpty())
                    {

                        if (!UserManager.getCurrentUser().getShoppingCartList().contains(chosenProduct))
                        {

                            UserManager.getCurrentUser().getShoppingCartList().add(chosenProduct);
                            UserManager.getCurrentUser().getShoppingCartList().get(UserManager.getCurrentUser().getShoppingCartList().indexOf(chosenProduct)).setQuantity(1);

                        } else {

                            UserManager.getCurrentUser().getShoppingCartList().get(UserManager.getCurrentUser().getShoppingCartList().indexOf(chosenProduct)).setQuantity(UserManager.getCurrentUser().getShoppingCartList().get(UserManager.getCurrentUser().getShoppingCartList().indexOf(chosenProduct)).getQuantity() + 1);

                        }

                        UserManager.getCurrentUser().writeToShoppingCart();

                        System.out.println("\n1 quantity of " + chosenProduct.getItemName() + " " + chosenProduct.getUnit() + " was just added to your shopping cart!\n");
                        System.out.println(FontColor.ansiBlue + "Current Quantities of this Product in Shopping Cart: " + chosenProduct.getQuantity() + "\n" + FontColor.ansiBlue);

                    } else if (Integer.parseInt(quantityString) == 0) {

                        System.out.println(FontColor.ansiBlue + "\nNo quantities of this product were added to the shopping cart." + FontColor.ansiReset);

                    } else if (Integer.parseInt(quantityString) < 0) {

                        if (UserManager.getCurrentUser().getShoppingCartList().contains(chosenProduct))
                        {

                            UserManager.getCurrentUser().getShoppingCartList().get(UserManager.getCurrentUser().getShoppingCartList().indexOf(chosenProduct)).setQuantity(Math.max(0, UserManager.getCurrentUser().getShoppingCartList().get(UserManager.getCurrentUser().getShoppingCartList().indexOf(chosenProduct)).getQuantity() + Integer.parseInt(quantityString)));

                        }

                        if (UserManager.getCurrentUser().getShoppingCartList().get(UserManager.getCurrentUser().getShoppingCartList().indexOf(chosenProduct)).getQuantity() == 0)
                        {

                            System.out.println("\nAll quantities of " + chosenProduct.getItemName() + " " + chosenProduct.getUnit() + " were just removed from your shopping cart.\n");
                            System.out.println(chosenProduct.getItemName() + " " + chosenProduct.getUnit() + " is no longer in your shopping cart.\n");

                        } else {

                            System.out.println("\n" + -Integer.parseInt(quantityString) + " quantities of " + chosenProduct.getItemName() + " " + chosenProduct.getUnit() + " were just removed from your shopping cart.\n");
                            System.out.println("Current Quantities of this Product in Shopping Cart: " + chosenProduct.getQuantity() + "\n");

                        }

                        if (UserManager.getCurrentUser().getShoppingCartList().get(UserManager.getCurrentUser().getShoppingCartList().indexOf(chosenProduct)).getQuantity() == 0)
                        {

                            UserManager.getCurrentUser().getShoppingCartList().remove(chosenProduct);

                        }

                        UserManager.getCurrentUser().writeToShoppingCart();

                    } else {

                        if (!UserManager.getCurrentUser().getShoppingCartList().contains(chosenProduct))
                        {

                            UserManager.getCurrentUser().getShoppingCartList().add(chosenProduct);
                            UserManager.getCurrentUser().getShoppingCartList().get(UserManager.getCurrentUser().getShoppingCartList().indexOf(chosenProduct)).setQuantity(Integer.parseInt(quantityString));

                        } else {

                            UserManager.getCurrentUser().getShoppingCartList().get(UserManager.getCurrentUser().getShoppingCartList().indexOf(chosenProduct)).setQuantity(UserManager.getCurrentUser().getShoppingCartList().get(UserManager.getCurrentUser().getShoppingCartList().indexOf(chosenProduct)).getQuantity() + Integer.parseInt(quantityString));

                        }

                        UserManager.getCurrentUser().writeToShoppingCart();

                        System.out.println("\n" + quantityString + " quantities of " + chosenProduct.getItemName() + " " + chosenProduct.getUnit() + " was just added to your shopping cart!\n");
                        System.out.println("Current Quantities of this Product in Shopping Cart: " + chosenProduct.getQuantity() + "\n");

                    }

                    break;

                }

                case 6:
                {

                    PriceCatcherManager.getCheapestSellerAveragePriceCatcherList().clear();
                    PriceCatcherManager.getPriceTrendAveragePriceCatcherList().clear();

                    System.out.println("Returning to the main menu...\n");

                    break;

                }

                default:
                {

                    System.out.println("The integer representing the choice of action is not within 1 to 6, please try again...");

                    break;

                }

            }

        }

        // Reset the value of productMenuChoice
        productMenuChoice = 0;

    }

    static void viewProductDetails(Product product)
    {

        System.out.println(FontColor.ansiYellow + "-----= Details of " + product.getItemName() + " =-----\n" + FontColor.ansiReset);

        System.out.println("Product Name: " + product.getItemName());
        System.out.println("Unit: " + product.getUnit());
        System.out.println("Product Category: " + product.getItemGroup() + " >> " + product.getItemCategory());
        System.out.println("Product Code: " + product.getItemCode() + "\n");

    }

    static void modifyProductDetails(Product product)
    {

        String previousProductName = product.getItemName();
        String previousProductUnit = product.getUnit();
        String previousProductGroup = product.getItemGroup();
        String previousProductCategory = product.getItemCategory();
        int previousProductCode = product.getItemCode();

        System.out.println(FontColor.ansiYellow + "-----= Modifying Details of " + product.getItemName() + " =-----\n" + FontColor.ansiReset);

        System.out.println("Modifying:\n");

        System.out.println("Existing Product Name: " + product.getItemName());
        System.out.print("> New Product Name: ");
        keyboard.nextLine();
        String newProductName = keyboard.nextLine();
        product.setItemName(newProductName);
        System.out.println();

        System.out.println("Existing Product Unit: " + product.getUnit());
        System.out.print("> New Product Unit: ");
        String newProductUnit = keyboard.nextLine();
        product.setUnit(newProductUnit);
        System.out.println();

        System.out.println("Existing Product Category: " + product.getItemGroup());
        System.out.print("> New Product Category: ");
        String newProductGroup = keyboard.nextLine();
        product.setItemGroup(newProductGroup);
        System.out.println();

        System.out.println("Existing Product Subcategory: " + product.getItemCategory());
        System.out.print("> New Product Subcategory: ");
        String newProductCategory = keyboard.nextLine();
        product.setItemCategory(newProductCategory);
        System.out.println();

        System.out.println("Existing Product Code: " + product.getItemCode());
        System.out.print("> New Product Code: ");
        int newProductCode = keyboard.nextInt();
        product.setItemCode(newProductCode);
        System.out.println();

        System.out.println("----------------------------------------------");
        System.out.println(FontColor.ansiBlue + "-----= Summary of Changes to " + previousProductName + " =-----\n" + FontColor.ansiReset);

        displayProductSummaryChanges(previousProductName, product.getItemName(), "Product Name");
        displayProductSummaryChanges(previousProductUnit, product.getUnit(), "Product Unit");
        displayProductSummaryChanges(previousProductGroup, product.getItemGroup(), "Product Category");
        displayProductSummaryChanges(previousProductCategory, product.getItemCategory(), "Product Subcategory");
        displayProductSummaryChanges(Integer.toString(previousProductCode), Integer.toString(product.getItemCode()), "Product Code");

        int index = -1;

        for (int i = 0; i < ProductManager.getProductList().size(); i++)
        {

            if (listOfProducts.getList1DValue(i).getItemCode() == previousProductCode)
            {

                index = i;

                listOfProducts.setList1DValue(i, product);

                mapOfProducts.removeEntry(previousProductCode);
                mapOfProducts.addEntry(product.getItemCode(), product);

                needsUpdate = true;

                System.out.println("Your modifications to the product details have been successfully saved.\n");

                break;

            }

        }

        if (index == -1)
        {

            System.out.println(FontColor.ansiRedBold + "ERROR: There was an error saving the modified details of the product. Your changes have not been saved.\n" + FontColor.ansiReset);

        }

    }

    static void displayProductSummaryChanges(String previousValue, String currentValue, String variableName)
    {

        if (previousValue.equals(currentValue))
        {

            System.out.println(variableName + ": (unchanged)");

        } else {

            System.out.println(variableName + ": " + previousValue + " ---> " + currentValue);

        }

    }

    static void searchMenu() throws IOException
    {

        while (searchMenuChoice != 'N') {

            boolean foundProduct = false;

            System.out.println(FontColor.ansiBlue + "-----= Search for a Product =-----\n" + FontColor.ansiReset);

            keyboard.nextLine(); // Consume the newline character above

            System.out.print("> Enter the name of the product you want to search for: ");
            String productKey = keyboard.nextLine();

            System.out.print("\n> Enter the unit of the product (e.g. 1kg, leave blank if unit is unknown): ");
            String unitKey = keyboard.nextLine();

            List<Product> searchProductList = ProductManager.searchProduct(productKey, unitKey);

            if (!searchProductList.isEmpty()) {

                Map<Integer, Integer> searchProductMapping = new HashMap<>();

                System.out.println("\nSearch Results:\n");

                for (int i = 0; i < searchProductList.size(); i++) {

                    System.out.println((i + 1) + ". " + searchProductList.get(i).getItemName() + " " + searchProductList.get(i).getUnit());

                    searchProductMapping.put((i + 1), searchProductList.get(i).getItemCode());

                }

                System.out.print("\n> Select a product: ");
                int productChoice = keyboard.nextInt();

                System.out.println("\nYou've chosen: " + ProductManager.getProductByKey(searchProductMapping.get(productChoice)).getItemName() + " " + ProductManager.getProductByKey(searchProductMapping.get(productChoice)).getUnit() + "\n");

                foundProduct = true;

                productMenu(ProductManager.getProductByKey(searchProductMapping.get(productChoice)));

            } else {

                System.out.print("\n> Sorry, PriceWizard cannot find products with name you specified. Type 'Y' to try again: ");

                searchMenuChoice = keyboard.next().charAt(0);

                if (searchMenuChoice != 'Y')
                {

                    System.out.println("Exiting to main menu...");

                    searchMenuChoice = 'N';

                }

            }

            if (foundProduct)
            {

                searchMenuChoice = 'N';

            }

            System.out.println();

        }

        // Reset the value of searchMenuChoice
        searchMenuChoice = 'Y';

    }

    static void viewShoppingCart() throws IOException, CsvException
    {

        Map<Integer, Product> shoppingCartMapping = new HashMap<>();

        while (shoppingCartMenuChoice != 4)
        {

            UserManager.getCurrentUser().loadShoppingCart();

            if (UserManager.getCurrentUser().getShoppingCartList().isEmpty())
            {

                System.out.println(FontColor.ansiRedBold + "You currently have no products in your shopping cart. Please add a product to your shopping cart first.\n" + FontColor.ansiReset);

                break;

            } else {

                for (int i = 0; i < UserManager.getCurrentUser().getShoppingCartList().size(); i++)
                {

                    shoppingCartMapping.put(i + 1, UserManager.getCurrentUser().getShoppingCartList().get(i));

                }

            }

            System.out.println(FontColor.ansiBlue + "-----= Shopping Cart =-----\n\n" + FontColor.ansiReset);

            for (int i = 0; i < UserManager.getCurrentUser().getShoppingCartList().size(); i++)
            {

                System.out.println(FontColor.ansiGreenBold + (i + 1) + ". " + shoppingCartMapping.get(i + 1).getItemName() + " " + shoppingCartMapping.get(i + 1).getUnit() + FontColor.ansiReset);
                System.out.println("Quantity: " + shoppingCartMapping.get(i + 1).getQuantity());
                PriceCatcherManager.topCheapestSeller(shoppingCartMapping.get(i + 1));

                System.out.println("Maximum number of premise visits to purchase all products: " + PriceCatcherManager.getWorstCaseScenarioPremisesVisitedSet().size());
                System.out.printf("Minimum total price of purchasing all products: RM %.2f\n\n", PriceCatcherManager.getWorstCaseScenarioTotalPrice());

            }

            System.out.println("Actions:\n");

            System.out.println("1. Select a product in the shopping cart");
            System.out.println("2. Remove a product from the shopping cart");
            System.out.println("3. Determine the best premises to purchase all products in the shopping cart");
            System.out.println("4. Return to the main menu\n");

            System.out.print("> Choose an action (1/2/3/4): ");
            shoppingCartMenuChoice = keyboard.nextInt();

            switch (shoppingCartMenuChoice)
            {

                case 1:
                {

                    System.out.println(FontColor.ansiBlue + "-----= Select a Product in Shopping Cart =-----\n" + FontColor.ansiReset);

                    PriceCatcherManager.setWorstCaseScenarioTotalPrice(0.0);
                    PriceCatcherManager.getWorstCaseScenarioPremisesVisitedSet().clear();

                    for (int i = 0; i < UserManager.getCurrentUser().getShoppingCartList().size(); i++)
                    {

                        System.out.println((i + 1) + ". " + shoppingCartMapping.get(i + 1).getItemName() + " " + shoppingCartMapping.get(i + 1).getUnit());

                    }

                    System.out.print("> Select a product: ");

                    int cartProductChoice = keyboard.nextInt();

                    System.out.println();

                    productMenu(shoppingCartMapping.get(cartProductChoice));

                    break;

                }

                case 2:
                {

                    System.out.println(FontColor.ansiBlue + "-----= Remove a Product from the Shopping Cart =-----\n" + FontColor.ansiReset);

                    PriceCatcherManager.setWorstCaseScenarioTotalPrice(0.0);
                    PriceCatcherManager.getWorstCaseScenarioPremisesVisitedSet().clear();

                    for (int i = 0; i < UserManager.getCurrentUser().getShoppingCartList().size(); i++)
                    {

                        System.out.println((i + 1) + ". " + shoppingCartMapping.get(i + 1).getItemName() + " " + shoppingCartMapping.get(i + 1).getUnit());

                    }

                    System.out.print("> Select a product to remove: ");

                    int cartProductChoice = keyboard.nextInt();

                    System.out.println("\nSuccessfully removed " + shoppingCartMapping.get(cartProductChoice).getItemName() + " " + shoppingCartMapping.get(cartProductChoice).getUnit() + " from the shopping cart.\n");

                    UserManager.getCurrentUser().getShoppingCartList().remove(shoppingCartMapping.get(cartProductChoice));
                    UserManager.getCurrentUser().writeToShoppingCart();

                    for (int i = 0; i < UserManager.getCurrentUser().getShoppingCartList().size(); i++)
                    {

                        shoppingCartMapping.put(i + 1, UserManager.getCurrentUser().getShoppingCartList().get(i));

                    }

                    cartProductChoice = 0;

                    break;

                }

                case 3:
                {

                    PriceCatcherManager.determineBestPremises();

                    break;

                }

                case 4:
                {

                    PriceCatcherManager.setWorstCaseScenarioTotalPrice(0.0);
                    PriceCatcherManager.getWorstCaseScenarioPremisesVisitedSet().clear();

                    System.out.println("\nReturning to the main menu...\n");

                    break;

                }

                default:
                {

                    PriceCatcherManager.setWorstCaseScenarioTotalPrice(0.0);
                    PriceCatcherManager.getWorstCaseScenarioPremisesVisitedSet().clear();

                    System.out.println("Sorry, your input was not between 1 to 4. Please try again.");

                    break;

                }

            }

        }

        shoppingCartMapping.clear();

        PriceCatcherManager.getCheapestSellerAveragePriceCatcherList().clear();
        PriceCatcherManager.getPriceTrendAveragePriceCatcherList().clear();

        shoppingCartMenuChoice = 0;

    }
    
    static void display2DList(List<List<String>> list) // Method that can display a 2D list
    {
        
        System.out.println("Viewing list:");
        
        for (int i = 0; i < list.size(); i++)
        {
            
            for (int j = 0; j < list.get(0).size(); j++)
            {
                
                if (j != 1)
                {
                    
                    System.out.printf("%-30s", list.get(i).get(j));
                    
                } else if (j == 3) {
                    
                    System.out.printf("%-40s", list.get(i).get(j));
                    
                } else { // for j == 1
                    
                    System.out.printf("%-100s", list.get(i).get(j));
                    
                }
                
            }
            
            System.out.println();
        
        }
        
    }
    
    void clearScreen() // To clear the console screen
    {
        
        try
        {
            
            DISPLAY.clear();
            
        } catch (RuntimeException exception) {
            
            exception.printStackTrace();
            
        }
        
    }
    
    void close() // To close the terminal, must be placed at the very end of the main method of Main class
    {
        
        if (TERMINAL != null)
        {
            
            try
            {
                
                TERMINAL.close();
                
            } catch (IOException exception) {
                
                exception.printStackTrace();
                
            }
            
        }
        
    }
    
}