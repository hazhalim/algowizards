package com.algowizards.finalpricetracker;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author AlgoWizards
 *
 * Class Description: This class manages all the instances of the Product class.
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


}
