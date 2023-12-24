package com.algowizards.finalpricetracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AlgoWizards (Nazrul Ikram)
 * 
 * Class Description: This class contains all the methods used to move data from files into the program.
 * 
 */

public class FileManager
{
    
    static List<List<String>> readCSVFileInto2DList(String fileName) // fileName is the relative path to a .csv file
    {
        
        String csvFile = fileName; // name of the .csv file
        String line = ""; // the line String which will read rows of the CSV data
        String delimiter = ","; // When separating a line into columns, use this character to denote when to break off into a new column
        
        List<List<String>> finalList = new ArrayList<>(); // Declare a new 2D list of Strings named finalList
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
        {
            
            while ((line = br.readLine()) != null) // Read a line from the CSV file
            {
                
                String[] column = line.split(delimiter); // Make an array of Strings where a line is separated into columns by the delimiter
                
                List<String> row = new ArrayList<>(); // Prepare a list for the separated columns named row
                
                for (String c : column)
                {
                    
                    row.add(c); // Add each column into the list 
                    
                }
                
                finalList.add(row); // After all the columns have been added to a "row", ADD that "row" to final list
                
            }
            
        } catch (IOException exception) {
            
            exception.printStackTrace(); // Exception message if reading the file causes an error
            
        }
        
        return finalList; // Finally, return the 2D list of Strings
        
    }
    
}