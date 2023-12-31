package com.algowizards.finalpricetracker;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

/**
 *
 * @author AlgoWizards (Nazrul Ikram)
 * 
 * Class Description: This class contains all the methods used to move data from files into the program.
 * (CSV File ---> Program)
 * 
 */

public class FileManager
{
    
    static List<List<String>> readCSVFileInto2DList(String fileName) throws FileNotFoundException, IOException, CsvException // fileName is the relative path to a .csv file
    {

        try {

            FileReader fileReader = new FileReader(fileName);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

            List<String[]> intermediateData = csvReader.readAll();

            List<List<String>> finalList = new ArrayList<>();

            for (String[] row : intermediateData)
            {

                finalList.add(Arrays.asList(row));

            }

            return finalList;

        } catch (FileNotFoundException exception) {

            throw new FileNotFoundException("The file " + fileName + "was not found.");

        } catch (IOException exception) {

            throw new IOException("There was an error reading " + fileName + ".");

        } catch (CsvException exception) {

            throw new CsvException("There was an error in the OpenCSV library.");

        }

    }

    static List<List<String>> readCSVFileInto2DList(String fileName, boolean noHeaders) throws FileNotFoundException, IOException, CsvException // fileName is the relative path to a .csv file
    {
        if (noHeaders)
        {

            try {

                FileReader fileReader = new FileReader(fileName);
                CSVReader csvReader = new CSVReaderBuilder(fileReader).build();

                List<String[]> intermediateData = csvReader.readAll();

                List<List<String>> finalList = new ArrayList<>();

                for (String[] row : intermediateData)
                {

                    finalList.add(Arrays.asList(row));

                }

                fileReader.close();
                csvReader.close();

                return finalList;

            } catch (FileNotFoundException exception) {

                throw new FileNotFoundException("The file " + fileName + "was not found.");

            } catch (IOException exception) {

                throw new IOException("There was an error reading " + fileName + ".");

            } catch (CsvException exception) {

                throw new CsvException("There was an error in the OpenCSV library.");

            }

        } else {

            try {

                FileReader fileReader = new FileReader(fileName);
                CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

                List<String[]> intermediateData = csvReader.readAll();

                List<List<String>> finalList = new ArrayList<>();

                for (String[] row : intermediateData)
                {

                    finalList.add(Arrays.asList(row));

                }

                return finalList;

            } catch (FileNotFoundException exception) {

                throw new FileNotFoundException("The file " + fileName + "was not found.");

            } catch (IOException exception) {

                throw new IOException("There was an error reading " + fileName + ".");

            } catch (CsvException exception) {

                throw new CsvException("There was an error in the OpenCSV library.");

            }

        }

    }

    static void write2DListIntoCSVFile(String fileName, List<String[]> list) throws IOException
    {

        try (FileWriter fileWriter = new FileWriter(UserManager.getCurrentUser().getCartPath());
             CSVWriter csvWriter = new CSVWriter(fileWriter))

        {

            csvWriter.writeAll(list);

        }

    }

    static void writeUserListIntoCSVFile(String fileName, List<String[]> userList) throws IOException
    {

        try (FileWriter fileWriter = new FileWriter(UserManager.getUserInformationFileName());
             CSVWriter csvWriter = new CSVWriter(fileWriter))
        {

            csvWriter.writeAll(userList);

        }

    }

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

}