package com.algowizards.finalpricetracker;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author AlgoWizards
 *
 * Class Description: This class manages all the instances of the PriceCatcher class.
 *
 */

public class PriceCatcherManager
{
    // Instance variables
    private static List<PriceCatcher> priceCatcherList = new ArrayList<>();
    private static List<PriceCatcher> averagePriceCatcherList = new ArrayList<>();
    private static List<PriceCatcher> cheapestSellerAveragePriceCatcherList = new ArrayList<>();
    private static List<PriceCatcher> priceTrendAveragePriceCatcherList = new ArrayList<>();

    // Constructors

    // Getter and setter methods
    static List<PriceCatcher> getPriceCatcherList()
    {

        return priceCatcherList;

    }

    static List<PriceCatcher> getAveragePriceCatcherList()
    {

        return averagePriceCatcherList;

    }

    public static List<PriceCatcher> getCheapestSellerAveragePriceCatcherList()
    {

        return cheapestSellerAveragePriceCatcherList;

    }

    public static List<PriceCatcher> getPriceTrendAveragePriceCatcherList()
    {

        return priceTrendAveragePriceCatcherList;

    }

    // Setter methods
    static void setPriceCatcherList(List<PriceCatcher> newPriceCatcherList)
    {

        priceCatcherList = newPriceCatcherList;

    }

    static void setAveragePriceCatcherList(List<PriceCatcher> newAveragePriceCatcherList)
    {

        averagePriceCatcherList = newAveragePriceCatcherList;

    }

    public static void setCheapestSellerAveragePriceCatcherList(List<PriceCatcher> cheapestSellerAveragePriceCatcherList)
    {

        PriceCatcherManager.cheapestSellerAveragePriceCatcherList = cheapestSellerAveragePriceCatcherList;

    }

    public static void setPriceTrendAveragePriceCatcherList(List<PriceCatcher> priceTrendAveragePriceCatcherList)
    {

        PriceCatcherManager.priceTrendAveragePriceCatcherList = priceTrendAveragePriceCatcherList;

    }

    // Other methods
    static void addPriceCatcherToList(PriceCatcher priceCatcher) // Add a price catcher object to the list of price catchers
    {

        // Add the price catcher to the list of price catchers
        priceCatcherList.add(priceCatcher);

    }


    static List<PriceCatcher> sortBy(String type, String sortDirection)
    {

        if (type.equalsIgnoreCase("price"))
        {

            if (sortDirection.equalsIgnoreCase("ascending"))
            {

                return priceCatcherList.stream().sorted(Comparator.comparingDouble(PriceCatcher::getItemPrice)).collect(Collectors.toList());

            } else { // Returns the price catcher list sorted by descending price

                return priceCatcherList.stream().sorted(Comparator.comparingDouble(PriceCatcher::getItemPrice).reversed()).collect(Collectors.toList());

            }

        } else { // Add more types of sort later...

            // Nothing is sorted, the unchanged price catcher list is returned (for now)
            return priceCatcherList;

        }

    }

    // Implemented from DataInitialisation class
    // Generates list of price catchers (price of an item at a premise at a given date)
    static void generateListOfPriceCatchers(DataStructure.List2D<String> priceCtch)
    {

        for (List<String> row : priceCtch.getList2D())
        {

            PriceCatcher priceCatcher = new PriceCatcher(row.get(0), Integer.parseInt(row.get(1)), Integer.parseInt(row.get(2)), Double.parseDouble(row.get(3)));

            addPriceCatcherToList(priceCatcher);

        }

    }

    static void generateListOfCheapestSellerAveragePriceCatchers(DataStructure.List1D<PriceCatcher> priceCatcherList, Set<Integer> setOfPremises, Product product)
    {

        for (Integer premiseCode : setOfPremises)
        {

            double totalPrice = 0.0;
            int count = 0;

            for (PriceCatcher priceCatcher : priceCatcherList.getList1D())
            {

                if ((priceCatcher.getPremiseCode() == PremiseManager.getPremiseByKey(premiseCode).getPremiseCode()) && (priceCatcher.getItemCode() == product.getItemCode()) && (PremiseManager.getPremiseByKey(premiseCode).getPremiseDistrict().equals(UserManager.getCurrentUser().getDistrict())))
                {

                    totalPrice += priceCatcher.getItemPrice();
                    count++;

                }

            }

            if (count > 0)
            {

                double averagePrice = (totalPrice / (double) count);

                PriceCatcher averagePriceCatcher = new PriceCatcher("2023-08-29", premiseCode, product.getItemCode(), averagePrice);

                cheapestSellerAveragePriceCatcherList.add(averagePriceCatcher);

            }

        }

    }

    static void viewTopFiveCheapestSellers(Product product)
    {

        DataStructure.List1D<PriceCatcher> priceCatcherItemOnly = new DataStructure.List1D<>(new ArrayList<>());
        Set<Integer> setOfPremises = new HashSet<>(new HashSet<>());

        for (PriceCatcher priceCatcher : priceCatcherList)
        {

            if (priceCatcher.getItemCode() == product.getItemCode())
            {

                priceCatcherItemOnly.getList1D().add(priceCatcher);
                setOfPremises.add(priceCatcher.getPremiseCode());

            }

        }

        generateListOfCheapestSellerAveragePriceCatchers(priceCatcherItemOnly, setOfPremises, product);

        Comparator<PriceCatcher> priceComparator = Comparator.comparingDouble(PriceCatcher::getItemPrice);

        cheapestSellerAveragePriceCatcherList.sort(priceComparator);

        System.out.println("-----= Cheapest Sellers of " + product.getItemName() + " " +  product.getUnit() + " =-----\n");

        System.out.println("District: " + UserManager.getCurrentUser().getDistrict() + ", " + UserManager.getCurrentUser().getState() + "\n");

        for (int i = 0; i < Math.min(5, cheapestSellerAveragePriceCatcherList.size()); i++)
        {

            System.out.printf("%d. %s\n", (i + 1), PremiseManager.getPremiseByKey(cheapestSellerAveragePriceCatcherList.get(i).getPremiseCode()).getPremiseName());
            System.out.printf("Price: RM %.2f\n", cheapestSellerAveragePriceCatcherList.get(i).getItemPrice());
            System.out.printf("Address: %s\n\n", PremiseManager.getPremiseByKey(cheapestSellerAveragePriceCatcherList.get(i).getPremiseCode()).getPremiseAddress());

        }

    }

    static void displayPriceTrendGraph(Product product)
    {

        DataStructure.List1D<PriceCatcher> priceCatcherItemOnly = new DataStructure.List1D<>(new ArrayList<>());
        Set<Integer> setOfDays = new HashSet<>(new HashSet<>());

        for (PriceCatcher priceCatcher : priceCatcherList)
        {

            if (priceCatcher.getItemCode() == product.getItemCode())
            {

                priceCatcherItemOnly.getList1D().add(priceCatcher);
                setOfDays.add(Integer.valueOf(priceCatcher.getPriceDay()));

            }

        }

        generateListOfDayAveragePriceCatchers(priceCatcherItemOnly, setOfDays, product);

        Comparator<PriceCatcher> dayComparator = Comparator.comparingInt(priceCatcher -> Integer.parseInt(priceCatcher.getPriceDay()));

        priceTrendAveragePriceCatcherList.sort(dayComparator);

        System.out.println("-----= Price Trend of " + product.getItemName() + " " +  product.getUnit() + " =-----\n");

        System.out.println("District: " + UserManager.getCurrentUser().getDistrict() + ", " + UserManager.getCurrentUser().getState() + "\n");

        System.out.println("Day     | Price (RM)");
        System.out.println("---------------------------");

        for (PriceCatcher priceCatcher : priceTrendAveragePriceCatcherList)
        {

            System.out.print(priceCatcher.getPriceDay() + "      | ");

            int characterScale = (int) (priceCatcher.getItemPrice() / 0.10); // 0.10 is the real scale, can modify this accordingly

            for (int i = 0; i < characterScale; i++)
            {

                System.out.print("*");

            }

            System.out.printf("\t(%.2f)\n", priceCatcher.getItemPrice());

        }

        System.out.println("\n\nScale: ");
        System.out.println("* = RM 0.10\n");

    }

    static void generateListOfDayAveragePriceCatchers(DataStructure.List1D<PriceCatcher> priceCatcherList, Set<Integer>  setOfDays, Product product)
    {

        for (Integer day : setOfDays)
        {

            double totalPrice = 0.0;
            int count = 0;

            for (PriceCatcher priceCatcher : priceCatcherList.getList1D())
            {

                Premise currentPremise = PremiseManager.getPremiseByKey(priceCatcher.getPremiseCode());

                if ((Integer.parseInt(priceCatcher.getPriceDay()) == day)
                        && (priceCatcher.getItemCode() == product.getItemCode())
                        && (currentPremise.getPremiseDistrict().equals(UserManager.getCurrentUser().getDistrict())))
                {

                    totalPrice += priceCatcher.getItemPrice();
                    count++;

                }

            }

            if (count > 0)
            {

                double dayAveragePrice = (totalPrice / (double) count);

                String date = String.format("2023-08-%02d", day);
                PriceCatcher dayAveragePriceCatcher = new PriceCatcher(date, 0, product.getItemCode(), dayAveragePrice);

                priceTrendAveragePriceCatcherList.add(dayAveragePriceCatcher);

            }

        }

    }

//    static DataStructure.List1D<PriceCatcher> averagedPriceCatchers(Product product)
//    {
//
//        DataStructure.List1D<PriceCatcher> priceCatcherPremiseOnly = new DataStructure.List1D<>(new ArrayList<>());
//
//        for (PriceCatcher priceCatcher : priceCatcherList)
//        {
//
//            if (priceCatcher.getItemCode() == product.getItemCode())
//            {
//
//                priceCatcherItemOnly.getList1D().add(priceCatcher);
//                setOfPremises.add(priceCatcher.getPremiseCode());
//
//            }
//
//        }
//
//        generateListOfCheapestSellerAveragePriceCatchers(priceCatcherItemOnly, setOfPremises, product);
//
//        Comparator<PriceCatcher> priceComparator = Comparator.comparingDouble(PriceCatcher::getItemPrice);
//
//        cheapestSellerAveragePriceCatcherList.sort(priceComparator);
//
//        System.out.println("-----= Cheapest Sellers of " + product.getItemName() + " " +  product.getUnit() + " =-----\n");
//
//        System.out.println("District: " + UserManager.getCurrentUser().getDistrict() + ", " + UserManager.getCurrentUser().getState() + "\n");
//
//        for (int i = 0; i < Math.min(5, cheapestSellerAveragePriceCatcherList.size()); i++)
//        {
//
//            System.out.printf("%d. %s\n", (i + 1), PremiseManager.getPremiseByKey(cheapestSellerAveragePriceCatcherList.get(i).getPremiseCode()).getPremiseName());
//            System.out.printf("Price: RM %.2f\n", cheapestSellerAveragePriceCatcherList.get(i).getItemPrice());
//            System.out.printf("Address: %s\n\n", PremiseManager.getPremiseByKey(cheapestSellerAveragePriceCatcherList.get(i).getPremiseCode()).getPremiseAddress());
//
//        }
//
//    }

//    static void viewTopFiveCheapestSellers(Product product) {
//        List<PriceCatcher> priceCatcherItemOnly = priceCatcherList.stream().filter(pc -> pc.getItemCode() == product.getItemCode()).toList();
//
//        // Group PriceCatcher objects by premise code
//        Map<Integer, List<PriceCatcher>> groupedByPremise = priceCatcherItemOnly.stream().collect(Collectors.groupingBy(PriceCatcher::getPremiseCode));
//
//        // Calculate average price for each premise
//
//        Map<Integer, Double> averagePricesByPremise = new HashMap<>();
//
//        for (Map.Entry<Integer, List<PriceCatcher>> entry : groupedByPremise.entrySet()) {
//            List<PriceCatcher> premisePriceCatchers = entry.getValue();
//            double totalPrice = premisePriceCatchers.stream().mapToDouble(PriceCatcher::getItemPrice).sum();
//            double averagePrice = (totalPrice / premisePriceCatchers.size());
//            averagePricesByPremise.put(entry.getKey(), averagePrice);
//        }
//
//        // Sort premises by average price in ascending order
//        List<Integer> sortedPremises = averagePricesByPremise.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue())
//                .map(Map.Entry::getKey)
//                .toList();
//
//        // View the top five cheapest sellers
//        System.out.println("Top Five Cheapest Sellers for " + product.getItemCode());
//        int topCount = Math.min(5, sortedPremises.size());
//        for (int i = 0; i < topCount; i++) {
//            Integer premiseCode = sortedPremises.get(i);
//            double averagePrice = averagePricesByPremise.get(premiseCode);
//            System.out.println("Premise: " + PremiseManager.getPremiseByKey(premiseCode).getPremiseName() + ", Average Price: " + averagePrice);
//        }
//    }

    // a map for price catcher is actually really needed

}