package com.algowizards.finalpricetracker;

public class PriceCatcher
{

    // Instance variables
    private String priceDate;
    private String priceDay;
    private String priceMonth;
    private String priceYear;
    private int premiseCode;
    private int itemCode;
    private double itemPrice;

    // Constructors
    public PriceCatcher() throws IllegalArgumentException // Default constructor
    {

        throw new IllegalArgumentException("No arguments passed into constructor PriceCatcher()");

    }

    public PriceCatcher(String priceDate, int premiseCode, int itemCode, double itemPrice)
    {

        this.priceDate = priceDate;
        this.premiseCode = premiseCode;
        this.itemCode = itemCode;
        this.itemPrice = itemPrice;

        splitPriceDate();

    }

    // Getter and setter methods
    // Getter methods
    String getPriceDate()
    {

        return this.priceDate;

    }

    String getPriceDay()
    {

        return this.priceDay;

    }

    String getPriceMonth()
    {

        return this.priceMonth;

    }

    String getPriceYear()
    {

        return this.priceYear;

    }

    int getPremiseCode()
    {

        return this.premiseCode;

    }

    int getItemCode()
    {

        return this.itemCode;

    }

    double getItemPrice()
    {

        return this.itemPrice;

    }

    // Setter methods
    void setPriceDate(String[] dateArray)
    {

        this.priceDay = dateArray[0];
        this.priceMonth = dateArray[1];
        this.priceYear = dateArray[2];

        this.priceDate = this.priceDate + "/" + this.priceMonth + "/" + this.priceYear;

    }

    void setPriceDay(String priceDay)
    {

        if ((Integer.parseInt(priceDay) >= 1) && (Integer.parseInt(priceDay) <= 31))
        {

            this.priceDay = priceDay;

        } else {

            throw new IllegalArgumentException("Day must be between 1 and 31");

        }

    }

    void setPriceMonth(String priceMonth)
    {

        if ((Integer.parseInt(priceMonth) >= 1) && (Integer.parseInt(priceMonth) <= 12))
        {

            this.priceMonth = priceMonth;

        } else {

            throw new IllegalArgumentException("Month must be between 1 and 12");

        }

    }

    void setPriceYear(String priceYear)
    {

        if (Integer.parseInt(priceYear) >= 0)
        {

            this.priceYear = priceYear;

        } else {

            throw new IllegalArgumentException("Year must be at least 0");

        }

    }

    void setPremiseCode(int premiseCode)
    {

        if (premiseCode >= 0)
        {

            this.premiseCode = premiseCode;

        } else {

            throw new IllegalArgumentException("Premise code must be at least 0");

        }

    }

    void setItemCode(int itemCode)
    {

        if (itemCode >= 0)
        {

            this.itemCode = itemCode;

        } else {

            throw new IllegalArgumentException("Item code must be at least 0");

        }

    }

    void setItemPrice(double itemPrice)
    {

        if (itemPrice >= 0.0)
        {

            this.itemPrice = itemPrice;

        } else {

            throw new IllegalArgumentException("Item price must be at least 0.0");

        }

    }

    // Other methods
    void splitPriceDate()
    {

        String[] dateParts = this.priceDate.split("/");

        setPriceDay(dateParts[0]);
        setPriceMonth(dateParts[1]);
        setPriceYear(dateParts[2]);

    }

    void splitPriceDate(String priceDate) // If this method is given a new date, it will set the date and its parts to this date
    {

        String[] dateParts = priceDate.split("/");

        setPriceDay(dateParts[0]);
        setPriceMonth(dateParts[1]);
        setPriceYear(dateParts[2]);

        setPriceDate(dateParts);

    }

}