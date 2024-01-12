package com.algowizards.finalpricetracker;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author AlgoWizards
 *
 * Class Description: This class manages all the instances of the Premise class.
 *
 */

public class PremiseManager
{

    static DataStructure.List2D<String> lookupPremise = null;
    // Instance variables
    private static List<Premise> premiseList = new ArrayList<>();
    private static Map<Integer, Premise> premiseMap = new HashMap<>();

    // Constructors


    // Getter and setter methods
    // Getter methods
    static List<Premise> getPremiseList()
    {

        return premiseList;

    }

    static Map<Integer, Premise> getPremiseMap()
    {

        return premiseMap;

    }

    // Setter methods
    static void setPremiseList(List<Premise> newPremiseList)
    {

        premiseList = newPremiseList;

    }

    static void setPremiseMap(Map<Integer, Premise> newPremiseMap)
    {

        premiseMap = newPremiseMap;

    }

    // Other methods
    static void addPremiseToList(Premise premise) // Add a premise object to the list of premises
    {

        // Add the product to the list of products
        premiseList.add(premise);

    }

    static void addPremiseToMap(Premise premise) // Add a premise object to the mapping of premises
    {

        // Add a mapping of (item code, product object) in productMap
        premiseMap.put(premise.getPremiseCode(), premise);

    }

    static Premise getPremiseByKey(int premiseCode) // Get the premise object if premiseCode is provided
    {

        return premiseMap.get(premiseCode);

    }

    // Transferred from DataInitialisation class
    // Generates list of premises
    static void generateListOfPremises(DataStructure.List2D<String> lookupPremise)
    {

        for (List<String> row : lookupPremise.getList2D())
        {

            Premise premise = new Premise(Integer.parseInt(row.get(0)), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5));

            addPremiseToList(premise);

        }

    }

    // Generates mapping of premises
    static void generateMapOfPremises(DataStructure.List2D<String> lookupPremise)
    {

        for (List<String> row : lookupPremise.getList2D())
        {

            Premise premise = new Premise(Integer.parseInt(row.get(0)), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5));

            addPremiseToMap(premise);

        }

    }

}