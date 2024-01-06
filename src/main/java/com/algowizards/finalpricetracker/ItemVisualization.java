package com.algowizards.finalpricetracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class PriceEntry {
    String retailer;
    double price;
    String address;
    Date date;

    PriceEntry(String retailer, double price, String address, Date date) {
        this.retailer = retailer;
        this.price = price;
        this.address = address;
        this.date = date;
    }
}

public class ItemVisualization {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // File paths
        String lookupItemPath = "lookup_item.csv";
        String lookupPremisePath = "lookup_premise.csv";
        String pricecatcherPath = "pricecatcher_2023-08.csv";

        // Read CSV files
        List<String[]> items = readCSV(lookupItemPath);
        List<String[]> premises = readCSV(lookupPremisePath);
        List<String[]> prices = readCSV(pricecatcherPath);

        // Display all items and let user choose
        String itemCode = displayItemsAndChoose(items, scanner);

        // Display top five cheapest sellers
        displayCheapestSellers(prices, premises, itemCode);

        scanner.close();
    }

    // Reads a CSV file and returns a list of String arrays
    private static List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            // Skip the header row
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                records.add(scanner.nextLine().split(","));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

    // Display all items and let the user choose one
    private static String displayItemsAndChoose(List<String[]> items, Scanner scanner) {
        int index = 1;
        System.out.println("Available Items:");
        for (String[] item : items) {
            // Assuming the first column contains the item code and the second column contains the item name
            System.out.println(index + ". " + item[1] + " (Code: " + item[0] + ")");
            index++;
        }

        System.out.println("\nEnter the number of the item you want to find the cheapest sellers for:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline

        // Return the item code of the chosen item
        return items.get(choice - 1)[0];
    }

    // Displays the top five cheapest sellers for the given item
    private static void displayCheapestSellers(List<String[]> prices, List<String[]> premises, String itemCode) {
        Map<String, PriceEntry> priceMap = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Process price data
        for (String[] record : prices) {
            if (record[1].equals(itemCode)) {
                String premiseCode = record[2];
                double price = Double.parseDouble(record[3]);
                Date date = null;
                try {
                    date = dateFormat.parse(record[4]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                for (String[] premise : premises) {
                    if (premise[0].equals(premiseCode)) {
                        String retailer = premise[1];
                        String address = premise[2];
                        PriceEntry currentEntry = priceMap.get(premiseCode);
                        if (currentEntry == null || price < currentEntry.price) {
                            priceMap.put(premiseCode, new PriceEntry(retailer, price, address, date));
                        }
                        break;
                    }
                }
            }
        }

        // Get the top five cheapest entries
        List<PriceEntry> cheapestEntries = new ArrayList<>(priceMap.values());
        cheapestEntries.sort(Comparator.comparingDouble(e -> e.price));
        System.out.println("Top 5 Cheapest Sellers for Item with Code: " + itemCode);
        for (int i = 0; i < Math.min(5, cheapestEntries.size()); i++) {
            PriceEntry entry = cheapestEntries.get(i);
            System.out.println((i + 1) + ". " + entry.retailer);
            System.out.println("   Price : $" + entry.price);
            System.out.println("   Address : " + entry.address);
        }
    }
}

