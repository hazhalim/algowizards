package com.algowizards.finalpricetracker;

import java.util.List;

/**
 *
 * @author AlgoWizards
 *
 * Class Description: This class manages the initialisation of the program. Methods from this program
 * generate the required list and maps of products, premises, and price catchers.
 *
 */

public class DataInitialisation
{

    // Generates list of products
    static DataStructure.List1D<Product> generateListOfProducts(DataStructure.List2D<String> lookupItem, ProductManager productManager)
    {

        for (List<String> row : lookupItem.getList2D())
        {

            Product product = new Product(Integer.parseInt(row.get(0)), row.get(1), row.get(2), row.get(3), row.get(4));

            productManager.addProductToList(product);

        }

        return new DataStructure.List1D<>(productManager.getProductList());

    }

    // Generates mapping of products
    static DataStructure.Mapping<Integer, Product> generateMapOfProducts(DataStructure.List2D<String> lookupItem, ProductManager productManager)
    {

        for (List<String> row : lookupItem.getList2D())
        {

            Product product = new Product(Integer.parseInt(row.get(0)), row.get(1), row.get(2), row.get(3), row.get(4));

            productManager.addProductToMap(product);

        }

        return new DataStructure.Mapping<>(productManager.getProductMap());

    }

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

    // Generates list of price catchers (price of an item at a premise at a given date)
    static DataStructure.List1D<PriceCatcher> generateListOfPriceCatchers(DataStructure.List2D<String> priceCtch, PriceCatcherManager priceCatcherManager)
    {

        for(List<String> row : priceCtch.getList2D())
        {

            PriceCatcher priceCatcher = new PriceCatcher(row.get(0), Integer.parseInt(row.get(1)), Integer.parseInt(row.get(2)), Double.parseDouble(row.get(3)));

            priceCatcherManager.addPriceCatcherToList(priceCatcher);

        }

        return new DataStructure.List1D<>(priceCatcherManager.getPriceCatcherList());

    }

}