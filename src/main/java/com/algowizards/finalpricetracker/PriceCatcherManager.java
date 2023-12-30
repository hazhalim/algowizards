package com.algowizards.finalpricetracker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    // Constructors

    // Getter and setter methods
    static List<PriceCatcher> getPriceCatcherList()
    {

        return priceCatcherList;

    }

    static void setPriceCatcherList(List<PriceCatcher> newPriceCatcherList)
    {

        priceCatcherList = newPriceCatcherList;

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

        for(List<String> row : priceCtch.getList2D())
        {

            PriceCatcher priceCatcher = new PriceCatcher(row.get(0), Integer.parseInt(row.get(1)), Integer.parseInt(row.get(2)), Double.parseDouble(row.get(3)));

            addPriceCatcherToList(priceCatcher);

        }

    }

    // a map for price catcher is actually really needed

}