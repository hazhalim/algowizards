package com.algowizards.finalpricetracker;

import com.opencsv.exceptions.CsvException;

import java.util.*;
import java.io.*;

public class SimilaritySearch
{

    // Instance variables
    private static List<String> productNameList = new ArrayList<>();
    private static int SIMILARITY_TOLERANCE = 8;
    private static Scanner keyboard = new Scanner(System.in);
    private static char searchMenuChoice = 'Y';
    private static boolean isFirstTimeOpening = true;

    // Constructors (none)

    // Getter and setter methods
    // Getter methods
    List<String> getProductNameList()
    {

        return productNameList;

    }

    int getSimilarityTolerance()
    {

        return SIMILARITY_TOLERANCE;

    }

    // Setter methods
    void setProductNameList(List<String> productNameList)
    {

        SimilaritySearch.productNameList = productNameList;

    }

    // Main method
    public static void main(String[] args) throws IOException, CsvException {

        while (searchMenuChoice != 'N')
        {

            boolean foundProduct = false;
            List<Product> similarProductList = new ArrayList<>();

            System.out.println(FontColor.ansiYellow + "-----= Search for a Product =-----" + FontColor.ansiReset);

            System.out.println();

            System.out.print("> Enter the name of the product you want to search for: ");

            if(isFirstTimeOpening)
            {

                isFirstTimeOpening = false;


            } else {

                keyboard.nextLine();

            }

            String productKey = keyboard.nextLine();

            System.out.println();

            System.out.print("> Enter the unit of the product (e.g. 1kg, leave blank if unit is unknown): ");
            String unitKey = keyboard.nextLine();

            if (unitKey.isEmpty())
            {


                similarProductList = getSimilarProductList(productKey, ProductManager.getProductList());

            } else {

                similarProductList = getSimilarProductList(productKey, unitKey, ProductManager.getProductList());

            }

            if (!similarProductList.isEmpty())
            {

                Map<Integer, Integer> searchProductMapping = new HashMap<>();

                System.out.println("\nSearch Results:\n");

                for (int i = 0; i < similarProductList.size(); i++)
                {

                    System.out.println((i + 1) + ". " + similarProductList.get(i).getItemName() + " " + similarProductList.get(i).getUnit());

                    searchProductMapping.put((i + 1), similarProductList.get(i).getItemCode());

                }

                System.out.print("\n> Select a product: ");
                int productChoice = keyboard.nextInt();

                System.out.println("\nYou've chosen: " + ProductManager.getProductByKey(searchProductMapping.get(productChoice)).getItemName() + " " + ProductManager.getProductByKey(searchProductMapping.get(productChoice)).getUnit() + "\n");

                foundProduct = true;

                Outputter.productMenu(ProductManager.getProductByKey(searchProductMapping.get(productChoice)));

            } else {

                System.out.print("\n> Sorry, PriceWizard cannot find products with the specified name. Type 'Y' to try again, any other character to quit: ");

                searchMenuChoice = keyboard.next().charAt(0);

                if (searchMenuChoice != 'Y')
                {

                    System.out.println("Exiting to the main menu...");

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

    // Other methods
    private static List<Product> getSimilarProductList(String productKey, List<Product> productList)
    {
        List<Product> similarProductList = new ArrayList<>();

        for (Product product : productList)
        {

            String currentProductName = product.getItemName();

            if (similarProductList.size() < 10)
            {

                if (currentProductName.toLowerCase().contains(productKey.toLowerCase().substring(0, Math.min(productKey.length(), SIMILARITY_TOLERANCE))))
                {

                    similarProductList.add(product);

                } else if ((currentProductName.toLowerCase().endsWith(productKey.toLowerCase().substring(0, Math.min(productKey.length(), SIMILARITY_TOLERANCE))))) {

                    similarProductList.add(product);

                } else if (getSimilarityDistance(currentProductName.toLowerCase(), productKey.toLowerCase()) <= SIMILARITY_TOLERANCE) {

                    similarProductList.add(product);

                }

            }
//

        }

        return similarProductList;

    }

    private static List<Product> getSimilarProductList(String productKey, String unitKey, List<Product> productList)
    {

        String finalProductKey = productKey + " " + unitKey;

        List<Product> similarProductList = new ArrayList<>();

        for (Product product : productList)
        {

            String currentProductName = product.getItemName();
            String currentProductUnit = product.getUnit();

            if (similarProductList.size() < 10)
            {

                if (currentProductName.toLowerCase().contains(finalProductKey.toLowerCase().substring(0, Math.min(finalProductKey.length(), SIMILARITY_TOLERANCE))))
                {

                    similarProductList.add(product);

                } else if ((currentProductName.toLowerCase().endsWith(finalProductKey.toLowerCase().substring(0, Math.min(finalProductKey.length(), SIMILARITY_TOLERANCE))))) {

                    similarProductList.add(product);

                } else if (getSimilarityDistance(currentProductName.toLowerCase(), finalProductKey.toLowerCase()) <= SIMILARITY_TOLERANCE) {

                    similarProductList.add(product);

                }

            }

        }

        return similarProductList;

    }

    private static int getSimilarityDistance(String firstString, String secondString)
    {

        /*

            Basically, we divide the two strings into a 2D array with headers at the rows and columns consisting
            of their characters. We're also adding an empty string "" as the first substring of both the first
            string and the second string, so both have their sizes increased by one. The value of the array
            at a certain position with index i and j stores the value of the similarity distance value of the
            substring of the first string from 0 up to i and the substring of the second string from 0 up to j.
            Following this logic, the actual similarity distance between the whole of both strings is the value
            stored at the (i - 1)-th row and (j - 1)-th column of the array, the very last element.

         */

        int[][] similarityValuesMatrix = new int[firstString.length() + 1][secondString.length() + 1];

        for (int i = 0; i < similarityValuesMatrix.length; i++)
        {

            similarityValuesMatrix[i][0] = i;

        }

        for (int i = 0; i < similarityValuesMatrix[0].length; i++)
        {

            similarityValuesMatrix[0][i] = i;

        }

        for (int i = 1; i < similarityValuesMatrix.length; i++)
        {

            for (int j = 1; j < similarityValuesMatrix[0].length; j++)
            {

                int cost = (firstString.charAt(i - 1) == secondString.charAt(j - 1)) ? 0 : 1;

                similarityValuesMatrix[i][j] = minimum(
                        similarityValuesMatrix[i][j - 1],
                        similarityValuesMatrix[i - 1][j],
                        similarityValuesMatrix[i - 1][j - 1]) + cost;

            }

        }

        return similarityValuesMatrix[firstString.length()][secondString.length()];

    }

    private static int minimum(int a, int b, int c)
    {

        return Math.min(Math.min(a, b), c);

    }

}