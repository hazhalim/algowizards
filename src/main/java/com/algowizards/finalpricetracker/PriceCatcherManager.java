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
    private List<PriceCatcher> priceCatcherList;

    // Constructors
    public PriceCatcherManager()
    {

        this.priceCatcherList = new ArrayList<>();

    }

    // Getter and setter methods
    List<PriceCatcher> getPriceCatcherList()
    {

        return this.priceCatcherList;

    }

    void setPriceCatcherList(List<PriceCatcher> priceCatcherList)
    {

        this.priceCatcherList = priceCatcherList;

    }

    // Other methods
    void addPriceCatcherToList(PriceCatcher priceCatcher) // Add a price catcher object to the list of price catchers
    {

        // Add the price catcher to the list of price catchers
        priceCatcherList.add(priceCatcher);

    }

    List<PriceCatcher> sortBy(String type, String sortDirection)
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

}