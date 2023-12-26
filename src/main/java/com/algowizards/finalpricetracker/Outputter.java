package com.algowizards.finalpricetracker;

import java.security.Key;
import java.util.Map;
import java.util.Scanner;

import java.util.List;

import com.opencsv.exceptions.CsvException;
import org.jline.reader.*;
import org.jline.terminal.*;
import org.jline.utils.Display;

import javax.xml.crypto.Data;
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

        // Initialisation of variables and data structures
        int currentMenuChoice = 0;

        // Pre-manager 2D lists of String
        DataStructure.List2D<String> lookupItem = null;
        DataStructure.List2D<String> lookupPremise = null;
        DataStructure.List2D<String> priceCatcher = null;

        // Initialise product manager
        ProductManager productManager = new ProductManager();

        // List of products and map of products
        DataStructure.List1D<Product> listOfProducts = null;
        DataStructure.Mapping<Integer, Product> mapOfProducts = null;

        // Initialise premise manager
        PremiseManager premiseManager = new PremiseManager();

        // List of premises and map of premises
        DataStructure.List1D<Premise> listOfPremises = null;
        DataStructure.Mapping<Integer, Premise> mapOfPremises = null;

        // Initialise price catcher manager
        PriceCatcherManager priceCatcherManager = new PriceCatcherManager();

        // List of price catchers
        DataStructure.List1D<PriceCatcher> listOfPriceCatchers = null;

        // Data structures used in browse product by categories
        // Group data structures
        DataStructure.List1D<String> productGroupList = null;
        DataStructure.Mapping<Integer, String> productGroupMapping = null;

        // Category (subgroup) data structures
        DataStructure.List1D<String> productCategoryList = null;
        DataStructure.Mapping<Integer, String> productCategoryMapping = null;

        // Categorised product data structures
        DataStructure.List1D<Product> categorisedProductList = null;
        DataStructure.Mapping<Integer, Integer> categorisedProductMapping = null;

        // Start of output message to console
        System.out.println("PriceTracker - Track Item Prices\n");
        System.out.println("Welcome to Product Search and Selection!\n");

        while (currentMenuChoice != 6)
        {

            System.out.println("-----=Main Menu=-----\n");
            System.out.println("Choose an option from the menu below: \n");

            System.out.println("1. Import Data");
            System.out.println("2. Browse Product by Categories");
            System.out.println("3. Search for a Product");
            System.out.println("4. View Shopping Cart");
            System.out.println("5. Account Settings");
            System.out.println("6. Exit");

            System.out.print("\n> Enter your choice (1/2/3/4/5/6): ");

            currentMenuChoice = keyboard.nextInt();

            System.out.println();

            switch(currentMenuChoice)
            {

                case 1:
                {

                    // 1. Import Data (File ---> Program stage)

                    System.out.println("-----=Import Data=-----\n");

                    System.out.println("0: Hang on tight, PriceTracker is importing data from CSV files and converting them to list of list of Strings...");

                    // Import data by creating 2D lists from the CSV files, passing into an object of DataStructure.List2D<String> class...
                    lookupItem = new DataStructure.List2D<>(FileManager.readCSVFileInto2DList("lookup_item.csv"));
                    lookupPremise = new DataStructure.List2D<>(FileManager.readCSVFileInto2DList("lookup_premise.csv"));
                    priceCatcher = new DataStructure.List2D<>(FileManager.readCSVFileInto2DList("pricecatcher_2023-08.csv"));

                    // All data has been loaded into the program, no more .csv files are being read

                    System.out.println("1: Populating the list and map of products...");

                    // Populating list of products and map of products...
                    listOfProducts = ProductManager.generateListOfProducts(lookupItem, productManager);
                    mapOfProducts = ProductManager.generateMapOfProducts(lookupItem, productManager);

                    System.out.println("2: Populating the list and map of premises...");

                    // Populating list and map of premises...
                    listOfPremises = PremiseManager.generateListOfPremises(lookupPremise, premiseManager);
                    mapOfPremises = PremiseManager.generateMapOfPremises(lookupPremise, premiseManager);

                    System.out.println("3: Populating the list of price points...");

                    // Populating list of price catchers (price points of an item at a premise on a given date)...
                    listOfPriceCatchers = PriceCatcherManager.generateListOfPriceCatchers(priceCatcher, priceCatcherManager);

                    System.out.println("4: Success! All data (products, premises, and price points) has been successfully imported into the program!\n");

                    break;

                }

                case 2:
                {

                    // 2. Browse Product by Categories

                    int groupNumber = 1;

                    if (productGroupList == null && productGroupMapping == null) // check if the product group list and group mapping are null (never been generated before)
                    {

                        productGroupList = ProductManager.getProductGroupList(listOfProducts);
                        productGroupMapping = ProductManager.getProductGroupMapping(productGroupList);

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

                    System.out.println("Thank you for using PriceTracker.");
                    System.out.println("Exiting program...");

                    break;

                }

                default:
                {

                    System.out.println("The choice inputted is not within 1 to 6, try again...");

                }

            }

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