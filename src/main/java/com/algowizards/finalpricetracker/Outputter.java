package com.algowizards.finalpricetracker;

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

        int currentMenuChoice = 0;

        System.out.println("PriceTracker - Track Item Prices\n");
        System.out.println("Welcome to Product Search and Selection!\n");

        while (currentMenuChoice != 6)
        {

            System.out.println("Choose an option from the menu below: \n");

            System.out.println("1. Import Data");
            System.out.println("2. Browse by Categories");
            System.out.println("3. Search for a Product");
            System.out.println("4. View Shopping Cart");
            System.out.println("5. Account Settings");
            System.out.println("6. Exit");

            System.out.print("Enter your choice (1/2/3/4/5/6): ");

            currentMenuChoice = keyboard.nextInt();

            switch(currentMenuChoice)
            {

                case 1:
                {
                    // 1. Import Data (File -> Program stage)
                    // Import data by creating 2D lists from the CSV files, passing into an object of DataStructure.List2D<String> class...
                    DataStructure.List2D<String> lookupItem = new DataStructure.List2D<>(FileManager.readCSVFileInto2DList("lookup_item.csv"));
                    DataStructure.List2D<String> lookupPremise = new DataStructure.List2D<>(FileManager.readCSVFileInto2DList("lookup_premise.csv"));
                    DataStructure.List2D<String> priceCatcher = new DataStructure.List2D<>(FileManager.readCSVFileInto2DList("pricecatcher_2023-08.csv"));

                    // All data has been loaded into the program, no more csv files are being read

                    // Intitialising product manager, getting list of products and map of products...
                    ProductManager productManager = new ProductManager();

                    DataStructure.List1D<Product> listOfProducts = ProductManager.generateListOfProducts(lookupItem, productManager);
                    DataStructure.Mapping<Integer, Product> mapOfProducts = ProductManager.generateMapOfProducts(lookupItem, productManager);

                    // Intitialising premise manager, getting list of premises and map of premises...
                    PremiseManager premiseManager = new PremiseManager();

                    DataStructure.List1D<Premise> listOfPremises = PremiseManager.generateListOfPremises(lookupPremise, premiseManager);
                    DataStructure.Mapping<Integer, Premise> mapOfPremises = PremiseManager.generateMapOfPremises(lookupPremise, premiseManager);

                    // Initialising price catcher manager, getting list of price catchers (price points of an item at a premise on a given date)...
                    PriceCatcherManager priceCatcherManager = new PriceCatcherManager();

                    DataStructure.List1D<PriceCatcher> listOfPriceCatchers = PriceCatcherManager.generateListOfPriceCatchers(priceCatcher, priceCatcherManager);

                    System.out.println("All data (products, premises, and price points) has been successfully imported into the program!");

                    break;

                }

                case 2:
                {
                    // 2. Browse by Categories
//                    browseByCategories();
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