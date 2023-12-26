package com.algowizards.finalpricetracker;

import javax.xml.crypto.Data;
import java.util.*;

/**
 *
 * @author AlgoWizards
 *
 * Class Description: This class manages all the instances/objects of the Product class.
 *
 */

public class ProductManager
{

    // Instance variables
    private List<Product> productList;
    private Map<Integer, Product> productMap;

    // Constructors
    public ProductManager() // Default constructor
    {

        this.productList = new ArrayList<>();
        this.productMap = new HashMap<>();

    }

    // Getter and setter methods
    // Getter methods
    List<Product> getProductList()
    {

        return this.productList;

    }

    Map<Integer, Product> getProductMap()
    {

        return this.productMap;

    }

    // Setter methods
    void setProductList(List<Product> productList)
    {

        this.productList = productList;

    }

    void setProductMap(Map<Integer, Product> productMap)
    {

        this.productMap = productMap;

    }

    // Other methods
    void addProductToList(Product product) // Add a product object to the list of products
    {

        // Add the product to the list of products
        this.productList.add(product);

    }

    void addProductToMap(Product product) // Add a product object to the mapping of products
    {

        // Add a mapping of (item code, product object) in productMap
        this.productMap.put(product.getItemCode(), product);

    }

    Product getProductByKey(int itemCode) // Get the product object if itemCode is provided
    {

        return this.productMap.get(itemCode);

    }

    // Transferred from DataInitialisation class
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

    // Methods for Browse Product by Categories
    // Method to get the product group list
    static DataStructure.List1D<String> getProductGroupList(DataStructure.List1D<Product> listOfProducts)
    {

        Set<String> uniqueGroups = new HashSet<>();

        for (Product product : listOfProducts.getList1D())
        {

            String group = product.getItemGroup();

            if(!uniqueGroups.contains(group))
            {

                uniqueGroups.add(group);

            }

        }

        return new DataStructure.List1D<>(new ArrayList<>(uniqueGroups));

    }

    // Method to get the product group mapping
    static DataStructure.Mapping<Integer, String> getProductGroupMapping(DataStructure.List1D<String> productGroupList)
    {

        Map<Integer, String> productGroupMapping = new HashMap<>();

        for (int i = 0; i < productGroupList.getList1DSize(); i++)
        {

            productGroupMapping.put(i + 1, productGroupList.getList1DValue(i));

        }

        return new DataStructure.Mapping<>(productGroupMapping);

    }

    // After obtaining the chosen group, get its list of categories
    static DataStructure.List1D<String> getProductCategoryList(String chosenGroup, DataStructure.List1D<Product> listOfProducts)
    {

        Set<String> uniqueCategories = new HashSet<>();

        for (Product product : listOfProducts.getList1D())
        {

            String category = product.getItemCategory();

            if((product.getItemGroup().equals(chosenGroup)))
            {

                uniqueCategories.add(category);

            }

        }

        return new DataStructure.List1D<>(new ArrayList<>(uniqueCategories));

    }

    // After obtaining the chosen group, get its mapping of categories
    static DataStructure.Mapping<Integer, String> getProductCategoryMapping(DataStructure.List1D<String> productCategoryList)
    {

        Map<Integer, String> productCategoryMapping = new HashMap<>();

        for (int i = 0; i < productCategoryList.getList1DSize(); i++)
        {

            productCategoryMapping.put(i + 1, productCategoryList.getList1DValue(i));

        }

        return new DataStructure.Mapping<>(productCategoryMapping);

    }

    static DataStructure.List1D<Product> getCategorisedProductList(String chosenCategory, DataStructure.List1D<Product> listOfProducts)
    {

        List<Product> categorisedProductList = new ArrayList<>();

        for (Product product : listOfProducts.getList1D())
        {

            if(product.getItemCategory().equals(chosenCategory))
            {

                categorisedProductList.add(product);

            }

        }

        return new DataStructure.List1D<>(categorisedProductList);

    }

    static DataStructure.Mapping<Integer, Integer> getCategorisedProductMapping(DataStructure.List1D<Product> categorisedProductList)
    {

        Map<Integer, Integer> categorisedProductMapping = new HashMap<>();

        for (int i = 0; i < categorisedProductList.getList1DSize(); i++)
        {

            categorisedProductMapping.put(i + 1, categorisedProductList.getList1DValue(i).getItemCode());

        }

        return new DataStructure.Mapping<>(categorisedProductMapping);

    }

}