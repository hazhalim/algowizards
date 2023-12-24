package com.algowizards.finalpricetracker;

import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    
//    static List<List<String>> readCSVFileInto2DList(String fileName) // fileName is the relative path to a .csv file
//    {
//        
//        String csvFile = fileName; // name of the .csv file
//        String line = ""; // the line String which will read rows of the CSV data
//        String delimiter = ","; // When separating a line into columns, use this character to denote when to break off into a new column
//        
//        List<List<String>> finalList = new ArrayList<>(); // Declare a new 2D list of Strings named finalList
//        
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
//        {
//            
//            while ((line = br.readLine()) != null) // Read a line from the CSV file
//            {
//                
//                String[] column = line.split(delimiter); // Make an array of Strings where a line is separated into columns by the delimiter
//                
//                List<String> row = new ArrayList<>(); // Prepare a list for the separated columns named row
//                
//                for (String c : column)
//                {
//                    
//                    row.add(c); // Add each column into the list 
//                    
//                }
//                
//                finalList.add(row); // After all the columns have been added to a "row", ADD that "row" to final list
//                
//            }
//            
//        } catch (IOException exception) {
//            
//            exception.printStackTrace(); // Exception message if reading the file causes an error
//            
//        }
//        
//        return finalList; // Finally, return the 2D list of Strings
//        
//    }
    
//    static List<List<String>> readCSVFileInto2DList(String fileName) {
//        String csvFile = fileName;
//        List<List<String>> finalList = new ArrayList<>();
//        String line = "";
//        String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
//        Pattern pattern = Pattern.compile(regex);
//
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//            while ((line = br.readLine()) != null) {
//                Matcher matcher = pattern.matcher(line);
//                List<String> columns = new ArrayList<>();
//
//                while (matcher.find()) {
//                    String column = line.substring(matcher.start(), matcher.end());
//                    columns.add(column.replaceAll("^\"|\"$", ""));
//                }
//
//                // Add the last column (or the only column if there is no comma)
//                String lastColumn = line.substring(matcher.end());
//                columns.add(lastColumn.replaceAll("^\"|\"$", ""));
//
//                finalList.add(columns);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return finalList;
//    }
    
    
    public static void main(String[] args) throws IOException, CsvException {
        
        Outputter outputter = new Outputter();
        
        // Login stuffs... (Tengku)
        
        // Reading and storing items into list of list of Strings (2D list of Strings)
        
        DataStructures lookupItems = new DataStructures("list2D", 2, FileManager.readCSVFileInto2DList("lookup_premise.csv"));
        List<List<String>> lookupPremise = FileManager.readCSVFileInto2DList("lookup_premise.csv");
        List<List<String>> priceCatcher = FileManager.readCSVFileInto2DList("pricecatcher_2023-08.csv");
        
        Outputter.display2DList(lookupItems.getList2D());
        
        outputter.clearScreen();

        DataStructures itemsList = new DataStructures("list", lookupItems.getList2DColumn(1));

        String userInput = "DAGING"; // A typo in the search query
        
        System.out.println(userInput + "\n");

        // Fuzzy search
        
        List<String> matches = FuzzySearchExample.similaritySearch(itemsList.getList(), userInput);

        // Display the matches
        System.out.println("Possible Matches:");
        for (String match : matches) {
            System.out.println(match);
        }
        
    }
    
}