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

    // Instance variables
    private List<Premise> premiseList;
    private Map<Integer, Premise> premiseMap;

    // Constructors
    public PremiseManager() // Default constructor
    {

        this.premiseList = new ArrayList<>();
        this.premiseMap = new HashMap<>();

    }

    // Getter and setter methods
    // Getter methods
    List<Premise> getPremiseList()
    {

        return this.premiseList;

    }

    Map<Integer, Premise> getPremiseMap()
    {

        return this.premiseMap;

    }

    // Setter methods
    void setPremiseList(List<Premise> premiseList)
    {

        this.premiseList = premiseList;

    }

    void setPremiseMap(Map<Integer, Premise> premiseMap)
    {

        this.premiseMap = premiseMap;

    }

    // Other methods
    void addPremiseToList(Premise premise) // Add a premise object to the list of premises
    {

        // Add the product to the list of products
        this.premiseList.add(premise);

    }

    void addPremiseToMap(Premise premise) // Add a premise object to the mapping of premises
    {

        // Add a mapping of (item code, product object) in productMap
        this.premiseMap.put(premise.getPremiseCode(), premise);

    }

    Premise getPremiseByKey(int premiseCode) // Get the premise object if premiseCode is provided
    {

        return this.premiseMap.get(premiseCode);

    }

    // Transferred from DataInitialisation class
    // Generates list of premises
    static DataStructure.List1D<Premise> generateListOfPremises(DataStructure.List2D<String> lookupPremise, PremiseManager premiseManager)
    {

        for (List<String> row : lookupPremise.getList2D())
        {

            Premise premise = new Premise(Integer.parseInt(row.get(0)), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5));

            premiseManager.addPremiseToList(premise);

        }

        return new DataStructure.List1D<>(premiseManager.getPremiseList());

    }

    // Generates mapping of premises
    static DataStructure.Mapping<Integer, Premise> generateMapOfPremises(DataStructure.List2D<String> lookupPremise, PremiseManager premiseManager)
    {

        for (List<String> row : lookupPremise.getList2D())
        {

            Premise premise = new Premise(Integer.parseInt(row.get(0)), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5));

            premiseManager.addPremiseToMap(premise);

        }

        return new DataStructure.Mapping<>(premiseManager.getPremiseMap());

    }

}