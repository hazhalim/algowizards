package com.algowizards.finalpricetracker;

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
    static boolean needsUpdate = false;

    // Pre-manager 2D lists of String
    static DataStructure.List2D<String> lookupItem = null;
    static DataStructure.List2D<String> lookupPremise = null;
    static DataStructure.List2D<String> priceCatcher = null;

    // Initialise product manager
    static ProductManager productManager = new ProductManager();

    // List of products and map of products
    static DataStructure.List1D<Product> listOfProducts = null;
    static DataStructure.Mapping<Integer, Product> mapOfProducts = null;

    // Initialise premise manager
    static PremiseManager premiseManager = new PremiseManager();

    // List of premises and map of premises
    static DataStructure.List1D<Premise> listOfPremises = null;
    static DataStructure.Mapping<Integer, Premise> mapOfPremises = null;

    // Initialise price catcher manager
    static PriceCatcherManager priceCatcherManager = new PriceCatcherManager();

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
            
        } catch (IOException exception) {
            
            throw new RuntimeException("Error initializing terminal", exception);
            
        }
        
    }
    
    // Getter and setter methods
    
    // Other methods
    static void mainMenu() throws IOException, CsvException // The method that calls to display the main menu of the program
    {

        // Start of output message to console
        System.out.println("PriceTracker - Track Item Prices\n");
        System.out.println("Welcome to Product Search and Selection!\n");

        while (mainMenuChoice != 6)
        {

            System.out.println("-----=Main Menu=-----\n");
            System.out.println("Choose an option from the menu below: \n");

            System.out.println("1. Import Data");
            System.out.println("2. Browse Product by Categories");
            System.out.println("3. Search for a Product");
            System.out.println("4. View Shopping Cart");
            System.out.println("5. Account Settings");
            System.out.println("6. Sign Out of Account");

            System.out.print("\n> Enter your choice (1/2/3/4/5/6): ");

            mainMenuChoice = keyboard.nextInt();

            System.out.println();

            switch(mainMenuChoice)
            {

                case 1:
                {

                    // 1. Import Data (File ---> Program stage)
                    importAndPopulateData();

                    break;

                }

                case 2:
                {

                    // 2. Browse Product by Categories

                    int groupNumber = 1;

                    if ((productGroupList == null && productGroupMapping == null)) // check if the product group list and group mapping are null (never been generated before) or needs an update
                    {

                        productGroupList = ProductManager.getProductGroupList(listOfProducts);
                        productGroupMapping = ProductManager.getProductGroupMapping(productGroupList);

                        needsUpdate = false;

                    }

                    System.out.println("-----=Browse Product by Categories=-----\n");
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
                    productCategoryList = ProductManager.getProductCategoryList(chosenGroupString, listOfProducts);
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
                    categorisedProductList = ProductManager.getCategorisedProductList(chosenCategoryString, listOfProducts);
                    categorisedProductMapping = ProductManager.getCategorisedProductMapping(categorisedProductList);

                    System.out.println("-------------------------------------------");
                    System.out.println("\n Browse products in " + chosenGroupString + " >> " + chosenCategoryString + ":\n");

                    for (Integer currentKey : categorisedProductMapping.getKeys())
                    {

                        System.out.println(currentKey + ". " + mapOfProducts.getValueByKey(categorisedProductMapping.getValueByKey(currentKey)).getItemName());

                    }

                    System.out.print("\n> Choose a product: ");

                    int chosenProductNumber = keyboard.nextInt();
                    Product chosenProduct = mapOfProducts.getValueByKey(categorisedProductMapping.getValueByKey(chosenProductNumber));
                    String nameOfChosenProduct = chosenProduct.getItemName();

                    System.out.println("\nYou've chosen: " + nameOfChosenProduct + "\n");

                    productMenu(chosenProduct);

                    break;

                }

                case 3:
                {

//                    searchForProduct();
                    break;

                }

                case 4:
                {

//                    viewShoppingCart();
                    break;

                }

                case 5:
                {

//                    viewAccountSettings();
                    break;

                }

                case 6:
                {

                    System.out.println("Thank you for using PriceWizard!\n");
                    System.out.println("Signing out of your account...\n");

                    break;

                }

                default:
                {

                    System.out.println("The integer representing the choice is not within 1 to 6, please try again...");

                }

            }

        }

    }

    static void importAndPopulateData() throws IOException, CsvException {

        System.out.println("-----= Import Data =-----\n");

        System.out.println("0%: Hang on tight, PriceWizard is importing data from CSV files and converting them to list of list of Strings...");

        // Import data by creating 2D lists from the CSV files, passing into an object of DataStructure.List2D<String> class...
        lookupItem = new DataStructure.List2D<>(FileManager.readCSVFileInto2DList("lookup_item.csv"));
        lookupPremise = new DataStructure.List2D<>(FileManager.readCSVFileInto2DList("lookup_premise.csv"));
        priceCatcher = new DataStructure.List2D<>(FileManager.readCSVFileInto2DList("pricecatcher_2023-08.csv"));

        // All data has been loaded into the program, no more .csv files are being read

        System.out.println("25%: Populating the list and map of products...");

        // Populating list of products and map of products...
        ProductManager.generateListOfProducts(lookupItem);
        ProductManager.generateMapOfProducts(lookupItem);

        System.out.println("50%: Populating the list and map of premises...");

        // Populating list and map of premises...
        PremiseManager.generateListOfPremises(lookupPremise);
        PremiseManager.generateMapOfPremises(lookupPremise);

        System.out.println("75%: Populating the list of price points...");

        // Populating list of price catchers (price points of an item at a premise on a given date)...
        PriceCatcherManager.generateListOfPriceCatchers(priceCatcher);

        System.out.println("100% (Complete): Success! All data (products, premises, and price points) has been successfully imported into PriceWizard!\n");

    }

    static void productMenu(Product chosenProduct)
    {

        while (productMenuChoice != 6)
        {

            System.out.println("-----= Product: " + chosenProduct.getItemName() + " =-----\n");

            System.out.println(chosenProduct.getItemGroup() + " >> " + chosenProduct.getItemCategory() + " >> " + chosenProduct.getItemName() + "\n");
            System.out.println("Actions:\n");

            System.out.println("1. View this product's details");
            System.out.println("2. Modify this product's details");
            System.out.println("3. View the top 5 cheapest sellers of this product");
            System.out.println("4. View the trend of the price of this product");
            System.out.println("5. Add this product to the shopping cart");
            System.out.println("6. Go back to the main menu");

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

                    // viewTopFiveCheapestSellers(Product chosenProduct);

                    break;

                }

                case 4:
                {

                    // viewPriceTrend(Product chosenProduct);

                    break;

                }

                case 5:
                {

                    // addProductToShoppingCart(Product chosenProduct);

                    break;

                }

                case 6:
                {

                    System.out.println("Returning to main menu...\n");

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

        System.out.println("-----= Details of " + product.getItemName() + " =-----\n");

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

        System.out.println("-----= Modifying Details of " + product.getItemName() + " =-----\n");

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
        System.out.println("-----= Summary of Changes to " + previousProductName + " =-----\n");

        displayProductSummaryChanges(previousProductName, product.getItemName(), "Product Name");
        displayProductSummaryChanges(previousProductUnit, product.getUnit(), "Product Unit");
        displayProductSummaryChanges(previousProductGroup, product.getItemGroup(), "Product Category");
        displayProductSummaryChanges(previousProductCategory, product.getItemCategory(), "Product Subcategory");
        displayProductSummaryChanges(Integer.toString(previousProductCode), Integer.toString(product.getItemCode()), "Product Code");

        int index = -1;

        for (int i = 0; i < listOfProducts.getList1DSize(); i++)
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

            System.out.println("ERROR: There was an error saving the modified details of the product. Your changes have not been saved.\n");

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