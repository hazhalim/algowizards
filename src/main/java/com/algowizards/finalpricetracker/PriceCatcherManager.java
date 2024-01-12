package com.algowizards.finalpricetracker;

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
    static DataStructure.List2D<String> priceCatcher = null;
    // Instance variables
    private static List<PriceCatcher> priceCatcherList = new ArrayList<>();
    private static List<PriceCatcher> averagePriceCatcherList = new ArrayList<>();
    private static List<PriceCatcher> cheapestSellerAveragePriceCatcherList = new ArrayList<>();
    private static List<PriceCatcher> priceTrendAveragePriceCatcherList = new ArrayList<>();
    private static double totalPriceAtBestPremises = 0.0;
    private static List<Premise> bestPremisesList = new ArrayList<>();
    private static Set<Premise> worstCaseScenarioPremisesVisitedSet = new HashSet<>();
    private static double worstCaseScenarioTotalPrice = 0.0;

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

    public static double getTotalPriceAtBestPremises()
    {

        return totalPriceAtBestPremises;

    }

    public static List<Premise> getBestPremisesList()
    {

        return bestPremisesList;

    }

    public static Set<Premise> getWorstCaseScenarioPremisesVisitedSet()
    {

        return worstCaseScenarioPremisesVisitedSet;

    }

    public static double getWorstCaseScenarioTotalPrice()
    {

        return worstCaseScenarioTotalPrice;

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

    public static void setTotalPriceAtBestPremises(double totalPriceAtBestPremises)
    {

        PriceCatcherManager.totalPriceAtBestPremises = totalPriceAtBestPremises;

    }

    public static void setBestPremisesList(List<Premise> bestPremisesList)
    {

        PriceCatcherManager.bestPremisesList = bestPremisesList;

    }

    public static void setWorstCaseScenarioPremisesVisitedSet(Set<Premise> worstCaseScenarioPremisesVisitedSet)
    {

        PriceCatcherManager.worstCaseScenarioPremisesVisitedSet = worstCaseScenarioPremisesVisitedSet;

    }

    public static void setWorstCaseScenarioTotalPrice(double worstCaseScenarioTotalPrice)
    {

        PriceCatcherManager.worstCaseScenarioTotalPrice = worstCaseScenarioTotalPrice;

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

        cheapestSellerAveragePriceCatcherList.clear();

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

        System.out.println(Settings.ansiYellow + "-----= Cheapest Sellers of " + product.getItemName() + " " +  product.getUnit() + " =-----\n");

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

        System.out.println(Settings.ansiYellow + "-----= Price Trend of " + product.getItemName() + " " +  product.getUnit() + " =-----\n");

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

    static void topCheapestSeller(Product product)
    {

        cheapestSellerAveragePriceCatcherList.clear();

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

        if (cheapestSellerAveragePriceCatcherList.isEmpty())
        {

            System.out.println("\tCheapest premise selling this product: data is not available");

        } else {

            PriceCatcher cheapestPriceCatcher = cheapestSellerAveragePriceCatcherList.get(0);
            Premise cheapestPremise = PremiseManager.getPremiseByKey(cheapestPriceCatcher.getPremiseCode());

            double totalPrice = cheapestPriceCatcher.getItemPrice() * ((double) product.getQuantity());

            System.out.println("\tCheapest premise selling this product: " + cheapestPremise.getPremiseName());
            System.out.println("\tPremise type: " + cheapestPremise.getPremiseType());
            System.out.printf("\tAddress: %s\n\n", cheapestPremise.getPremiseAddress());
            System.out.printf("\tTotal price of this product at this premise: (RM %.2f / %s) x %d = RM %.2f\n\n", cheapestPriceCatcher.getItemPrice(), product.getUnit(), product.getQuantity(), totalPrice);

            worstCaseScenarioTotalPrice += totalPrice;
            worstCaseScenarioPremisesVisitedSet.add(cheapestPremise);

        }

    }

    static boolean isSellingProduct(Product product, Premise premise)
    {

        cheapestSellerAveragePriceCatcherList.clear();

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

        for (PriceCatcher priceCatcher : cheapestSellerAveragePriceCatcherList)
        {

            if ((priceCatcher.getPremiseCode() == premise.getPremiseCode()) && (priceCatcher.getItemCode() == product.getItemCode()))
            {

                return true;

            }

        }

        return false;

    }

    static boolean isCheapest(Product product, Premise premise, int topN)
    {

        cheapestSellerAveragePriceCatcherList.clear();

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

        if (cheapestSellerAveragePriceCatcherList.isEmpty())
        {

            return false;

        } else {

            // Check if the premise is among the top N cheapest sellers
            int premiseCode = premise.getPremiseCode();

            for (int i = 0; i < Math.min(topN, cheapestSellerAveragePriceCatcherList.size()); i++)
            {

                if (cheapestSellerAveragePriceCatcherList.get(i).getPremiseCode() == premiseCode)
                {

                    return true;

                }
            }

            return false;
        }


    }

    static Premise getCheapestPremise(Product product)
    {

        cheapestSellerAveragePriceCatcherList.clear();

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

        return PremiseManager.getPremiseByKey(cheapestSellerAveragePriceCatcherList.get(0).getPremiseCode());

    }

    // For a given list of products, return a sublist containing all the products that are sold by a given premise
    static List<Product> getAllProductsSoldByPremise(Premise premise, List<Product> givenProductList)
    {

        List<Product> productsSoldByPremiseList = new ArrayList<>();

        for (Product product : givenProductList)
        {

            if (isSellingProduct(product, premise))
            {

                productsSoldByPremiseList.add(product);

            }

        }

        return productsSoldByPremiseList;

    }

    static double getAveragePriceOfProductAtPremise(Product product, Premise premise)
    {

        double totalPrice = 0.0;
        double count = 0;
        int productCode = product.getItemCode();
        int premiseCode = premise.getPremiseCode();

        for (PriceCatcher priceCatcher : priceCatcherList)
        {

            if ((priceCatcher.getPremiseCode() == premiseCode) && (priceCatcher.getItemCode() == productCode))
            {

                totalPrice += priceCatcher.getItemPrice();
                count++;

            }

        }

        return (totalPrice / count);

    }

    static List<Premise> getAllPremisesSellingProduct(Product product)
    {

        Set<Integer> setOfPremiseCodes = new HashSet<>(new HashSet<>());

        String userDistrict = UserManager.getCurrentUser().getDistrict();

        for (PriceCatcher priceCatcher : priceCatcherList)
        {

            int currentPremiseCode = priceCatcher.getPremiseCode();
            Premise currentPremise = PremiseManager.getPremiseByKey(currentPremiseCode);

            if (priceCatcher.getItemCode() == product.getItemCode() && (currentPremise.getPremiseDistrict().equals(userDistrict)))
            {

                setOfPremiseCodes.add(priceCatcher.getPremiseCode());

            }

        }

        List<Premise> listOfPremisesSellingProduct = new ArrayList<>();

        for (Integer premiseCode : setOfPremiseCodes)
        {

            listOfPremisesSellingProduct.add(PremiseManager.getPremiseByKey(premiseCode));

        }

        return listOfPremisesSellingProduct;

    }

    static void determineBestPremises()
    {

        System.out.println("\nHang on tight, PriceWizard is starting to calculate details... this may take a while based on the amount of products in the cart.");

        // 1. Initialise temporary, separate copy of user's shopping cart
        List<Product> temporaryShoppingCart = new ArrayList<>(UserManager.getCurrentUser().getShoppingCartList());

        // 2. Declare set of all possible premises spanning all products in shopping cart
        Set<Premise> setOfAllPossiblePremises = new HashSet<>();

        // 3. Initialise premise scorelist
        DataStructure.List2D<Integer> premiseScoreList = new DataStructure.List2D<>(new ArrayList<>());

        while (!temporaryShoppingCart.isEmpty())
        {

            // Edge case: if there's only one product left after removing the products (from a previous iteration of this while loop)
            if (temporaryShoppingCart.size() == 1)
            {

                Premise currentlyBestPremise = getCheapestPremise(temporaryShoppingCart.get(0));
                bestPremisesList.add(currentlyBestPremise);

                // Display the premise and the products that the user should buy under this premise
                displayProductsAtCurrentlyBestPremise(temporaryShoppingCart, currentlyBestPremise);

                premiseScoreList.getList2D().clear();
                temporaryShoppingCart.clear();

                break;

            }

//            System.out.println("PriceWizard is getting possibilities of premises...");

            // premiseScoreList's structure: (explaining each column)
            // Premise Code | # of Products of Cart this Premise Sells | # of Products of Cart this Premise is Cheapest
            // 1011         | 6                                        |      3
            // 1012         | 6                                        |      6
            // 10101001     | 5                                        |      2
            // 1010010
            // 10101010

            // 4. Get all possible premises that sell that at least one product in the shopping cart
            for (Product product : temporaryShoppingCart)
            {

                List<Premise> premiseList = getAllPremisesSellingProduct(product);

                for (Premise premise : premiseList)
                {

                    if (isSellingProduct(product, premise))
                    {

                        if (!setOfAllPossiblePremises.contains(premise))
                        {

                            premiseScoreList.getList2D().add(new ArrayList<>(List.of(premise.getPremiseCode(), 0, 0)));

                            setOfAllPossiblePremises.add(premise);

                        }

                    }

                }

            }

//            System.out.println("PriceWizard is calculating premise metrics...");

            // 5. Determine the "sells" and "cheapest" values for each premise
            for (List<Integer> currentPremiseRow : premiseScoreList.getList2D())
            {

                Premise currentPremise = PremiseManager.getPremiseByKey(currentPremiseRow.get(0));

                for (Product product : temporaryShoppingCart)
                {

                    if (isSellingProduct(product, currentPremise))
                    {

                        currentPremiseRow.set(1, currentPremiseRow.get(1) + 1);

                        if (isCheapest(product, currentPremise, 3))
                        {

                            currentPremiseRow.set(2, currentPremiseRow.get(2) + 1);

                        }

                    }

                }

            }

//            System.out.println("PriceWizard is sorting data...");

            // 6. Sort the premise scorelist by decreasing sells
            Comparator<List<Integer>> sellsComparator = Comparator.comparingInt(list -> list.get(1));

            premiseScoreList.getList2D().sort(sellsComparator.reversed());

//            System.out.println("PriceWizard is processing data...");

            // 7. Create a sublist from premises tying with the highest sells value
            List<List<Integer>> tyingSellsList = new ArrayList<>();

            for (List<Integer> currentPremiseRow : premiseScoreList.getList2D())
            {

                if (currentPremiseRow.get(1) == premiseScoreList.getList2D().get(0).get(1))
                {

                    tyingSellsList.add(currentPremiseRow);

                }

            }

//            System.out.println("PriceWizard is sorting data...");

            // 8. Sort the tying sublist by decreasing cheapest

            Comparator<List<Integer>> cheapestComparator = Comparator.comparingInt(list -> list.get(2));

            tyingSellsList.sort(cheapestComparator.reversed());

//            System.out.println("PriceWizard is determining premises...");

            // 9. The first index of the tying sells is now the best premise to buy the products in the cart
            Premise currentlyBestPremise = PremiseManager.getPremiseByKey(tyingSellsList.get(0).get(0));

            bestPremisesList.add(currentlyBestPremise);

//            System.out.println("PriceWizard is finalising results...\n");

            // 10. Display the current best premise, display the products that you should Remove products that are sold by this premise
            Iterator<Product> iterator = temporaryShoppingCart.iterator();

            // Display the premise and the products that the user should buy under this premise
            displayProductsAtCurrentlyBestPremise(temporaryShoppingCart, currentlyBestPremise);

            while (iterator.hasNext())
            {

                Product product = iterator.next();

                if (isSellingProduct(product, currentlyBestPremise))
                {

                    iterator.remove();

                }

            }

            // 11. Clear premiseScoreList for a new loop (this loop will not end until all products have been accounted for)
            premiseScoreList.getList2D().clear();

        }

        // 12. Display the total price for all products when purchased at the best premises
        System.out.printf("Total price for all products: RM %.2f\n\n", totalPriceAtBestPremises);

        // 13. Compare the scenario where lower total prices are prioritised and PriceWizard's recommendation
        System.out.println(Settings.ansiYellow + "-----= Premise Visits and Total Price Comparisons =----\n");

        System.out.println("1. Premise Visits\n");

        System.out.println("\tDescription: The number of premises needed to visit to purchase all products in the shopping cart.\n");

        System.out.println("\ta. Whilst always prioritising lower prices: " + worstCaseScenarioPremisesVisitedSet.size() + " premise visits");

        System.out.println("\tb. PriceWizard's recommendation: " + bestPremisesList.size() + " premise visits");

        int totalPremiseVisitsDifference = Math.abs(worstCaseScenarioPremisesVisitedSet.size() - bestPremisesList.size());
        double totalPremiseVisitsDifferencePercentage = Math.abs((((double) totalPremiseVisitsDifference) / ((double) worstCaseScenarioPremisesVisitedSet.size())) * 100);

        System.out.printf("\tc. Total Difference: %d premise visits (%.2f%%)\n\n", totalPremiseVisitsDifference, totalPremiseVisitsDifferencePercentage);

        System.out.println("\tExplanation: PriceWizard's recommendation system helps you find products that can be bought at the same premise whilst\n\tkeeping product prices optimally low.\n");

        System.out.println("2. Total Price\n");

        System.out.println("\tDescription: The total cost of purchasing all the products in your shopping cart.\n");

        System.out.printf("\ta. Whilst always prioritising lower prices: RM %.2f\n", worstCaseScenarioTotalPrice);

        System.out.printf("\tb. PriceWizard's recommended total price: RM %.2f\n", totalPriceAtBestPremises);

        double totalPriceDifference = Math.abs(totalPriceAtBestPremises - worstCaseScenarioTotalPrice);
        double totalPriceDifferencePercentage = Math.abs((totalPriceDifference / worstCaseScenarioTotalPrice) * 100);

        System.out.printf("\tc. Total Difference: RM %.2f (%.2f%%)\n\n", totalPriceDifference, totalPriceDifferencePercentage);

        System.out.println("\tExplanation: Though the recommended total price may be slightly higher, PriceWizard encourages balancing prioritising lower premise visits and\n\tlower total prices to best simulate practical purchasing patterns.\n");

        totalPriceAtBestPremises = 0.0;
        bestPremisesList.clear();

        worstCaseScenarioTotalPrice = 0.0;
        worstCaseScenarioPremisesVisitedSet.clear();

    }

    static void displayProductsAtCurrentlyBestPremise(List<Product> temporaryShoppingCart, Premise currentlyBestPremise)
    {

        if (bestPremisesList.size() <= 1)
        {

            System.out.println(Settings.ansiYellow + "\n-----= Best Premises to Buy Products in Shopping Cart From =-----\n");

        }

        System.out.println(Settings.ansiYellow + (bestPremisesList.indexOf(currentlyBestPremise) + 1) + ". " + currentlyBestPremise.getPremiseName());
        System.out.println("\n\tPremise Type: " + currentlyBestPremise.getPremiseType());
        System.out.println("\tPremise Address: " + currentlyBestPremise.getPremiseAddress());

        System.out.println("\n\tProduct(s) to purchase at this premise:\n");

        List<Product> productsSoldByCurrentPremiseList = getAllProductsSoldByPremise(currentlyBestPremise, temporaryShoppingCart);

        for (int i = 0; i < productsSoldByCurrentPremiseList.size(); i++)
        {

            Product currentProduct = productsSoldByCurrentPremiseList.get(i);

            double averagePriceOfProductAtPremise = getAveragePriceOfProductAtPremise(currentProduct, currentlyBestPremise);
            String currentProductUnit = currentProduct.getUnit();
            int currentProductQuantity = currentProduct.getQuantity();
            double currentTotalProductPrice = (averagePriceOfProductAtPremise * currentProductQuantity);

            System.out.println(Settings.ansiYellow + "\t\t" + (i + 1) + ". " + currentProduct.getItemName() + " " + currentProductUnit);
            System.out.println("\n\t\t\tQuantity: " + currentProductQuantity);
            System.out.printf("\t\t\tPrice of Product: (RM %.2f / %s) x %d = RM %.2f\n\n", averagePriceOfProductAtPremise, currentProductUnit, currentProductQuantity, currentTotalProductPrice);

            totalPriceAtBestPremises += currentTotalProductPrice;

        }

    }

}

//static DataStructure.List1D<PriceCatcher> averagedPriceCatchers(Product product)
//{
//
//    DataStructure.List1D<PriceCatcher> priceCatcherPremiseOnly = new DataStructure.List1D<>(new ArrayList<>());
//
//    for (PriceCatcher priceCatcher : priceCatcherList)
//    {
//
//        if (priceCatcher.getItemCode() == product.getItemCode())
//        {
//
//            priceCatcherItemOnly.getList1D().add(priceCatcher);
//            setOfPremises.add(priceCatcher.getPremiseCode());
//
//        }
//
//    }
//
//    generateListOfCheapestSellerAveragePriceCatchers(priceCatcherItemOnly, setOfPremises, product);
//
//    Comparator<PriceCatcher> priceComparator = Comparator.comparingDouble(PriceCatcher::getItemPrice);
//
//    cheapestSellerAveragePriceCatcherList.sort(priceComparator);
//
//    System.out.println("-----= Cheapest Sellers of " + product.getItemName() + " " +  product.getUnit() + " =-----\n");
//
//    System.out.println("District: " + UserManager.getCurrentUser().getDistrict() + ", " + UserManager.getCurrentUser().getState() + "\n");
//
//    for (int i = 0; i < Math.min(5, cheapestSellerAveragePriceCatcherList.size()); i++)
//    {
//
//        System.out.printf("%d. %s\n", (i + 1), PremiseManager.getPremiseByKey(cheapestSellerAveragePriceCatcherList.get(i).getPremiseCode()).getPremiseName());
//        System.out.printf("Price: RM %.2f\n", cheapestSellerAveragePriceCatcherList.get(i).getItemPrice());
//        System.out.printf("Address: %s\n\n", PremiseManager.getPremiseByKey(cheapestSellerAveragePriceCatcherList.get(i).getPremiseCode()).getPremiseAddress());
//
//    }
//
//}
//
//static void viewTopFiveCheapestSellers(Product product) {
//    List<PriceCatcher> priceCatcherItemOnly = priceCatcherList.stream().filter(pc -> pc.getItemCode() == product.getItemCode()).toList();
//
//    // Group PriceCatcher objects by premise code
//    Map<Integer, List<PriceCatcher>> groupedByPremise = priceCatcherItemOnly.stream().collect(Collectors.groupingBy(PriceCatcher::getPremiseCode));
//
//    // Calculate average price for each premise
//
//    Map<Integer, Double> averagePricesByPremise = new HashMap<>();
//
//    for (Map.Entry<Integer, List<PriceCatcher>> entry : groupedByPremise.entrySet()) {
//        List<PriceCatcher> premisePriceCatchers = entry.getValue();
//        double totalPrice = premisePriceCatchers.stream().mapToDouble(PriceCatcher::getItemPrice).sum();
//        double averagePrice = (totalPrice / premisePriceCatchers.size());
//        averagePricesByPremise.put(entry.getKey(), averagePrice);
//    }
//
//    // Sort premises by average price in ascending order
//    List<Integer> sortedPremises = averagePricesByPremise.entrySet().stream()
//            .sorted(Map.Entry.comparingByValue())
//            .map(Map.Entry::getKey)
//            .toList();
//
//    // View the top five cheapest sellers
//    System.out.println("Top Five Cheapest Sellers for " + product.getItemCode());
//    int topCount = Math.min(5, sortedPremises.size());
//    for (int i = 0; i < topCount; i++) {
//        Integer premiseCode = sortedPremises.get(i);
//        double averagePrice = averagePricesByPremise.get(premiseCode);
//        System.out.println("Premise: " + PremiseManager.getPremiseByKey(premiseCode).getPremiseName() + ", Average Price: " + averagePrice);
//    }
//}