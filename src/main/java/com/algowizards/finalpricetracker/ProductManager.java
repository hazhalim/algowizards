package com.algowizards.finalpricetracker;

import java.sql.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ProductManager
{

    // Instance variables
    private List<Product> productList;
    private Map<String, Product> productMap;

    // Constructors
    public ProductManager()
    {

        this.productList = new ArrayList<>();
        this.productMap = new HashMap<>();

    }

    // Getter and setter methods
    List<Product> getProductList()
    {

        return this.productList;

    }

    Map<String, Product> getProductMap()
    {

        return this.productMap;

    }

    void setProductList(List<Product> productList)
    {

        this.productList = productList;

    }

    void setProductMap(Map<String, Product>)
    {

        this.productMap = productMap;

    }

    // Other methods


}
