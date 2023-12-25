package com.algowizards.finalpricetracker;

/**
 *
 * @author AlgoWizards
 *
 * Class Description: This class manages the attributes of a product.
 *
 */

public class Product
{

    // Instance variables
    private int itemCode;
    private String itemName;
    private String unit;
    private String itemGroup;
    private String itemCategory;

    // Constructors
    public Product() throws IllegalArgumentException // Default constructor
    {

        throw new IllegalArgumentException("No arguments passed into constructor com.algowizards.finalpricetracker.Product()");

    }

    public Product(int itemCode, String itemName, String unit, String itemGroup, String itemCategory)
    {

        this.itemCode = itemCode;
        this.itemName = itemName;
        this.unit = unit;
        this.itemGroup = itemGroup;
        this.itemCategory = itemCategory;

    }

    // Getter and setter methods
    // Getter methods
    int getItemCode()
    {

        return this.itemCode;

    }
    String getItemName()
    {

        return this.itemName;

    }

    String getUnit()
    {

        return this.unit;

    }

    String getItemGroup()
    {

        return this.itemGroup;

    }

    String getItemCategory()
    {

        return this.itemCategory;

    }

    // Setter methods
    void setItemCode(int itemCode)
    {

        this.itemCode = itemCode;

    }

    void setItemName(String itemName)
    {

        this.itemName = itemName;

    }

    void setUnit(String unit)
    {

        this.unit = unit;

    }

    void setItemGroup(String itemGroup)
    {

        this.itemGroup = itemGroup;

    }

    void setItemCategory(String itemCategory)
    {

        this.itemCategory = itemCategory;

    }

    // Other methods

}