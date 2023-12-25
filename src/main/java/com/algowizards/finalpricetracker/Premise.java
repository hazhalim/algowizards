package com.algowizards.finalpricetracker;

/**
 *
 * @author AlgoWizards
 *
 * Class Description: This class manages the attributes of a premise.
 *
 */

public class Premise
{

    // Instance variables
    private int premiseCode;
    private String premiseName;
    private String premiseAddress;
    private String premiseType;
    private String premiseState;
    private String premiseDistrict;


    // Constructors
    public Premise() throws IllegalArgumentException // Default constructor
    {

        throw new IllegalArgumentException("No arguments passed in constructor Premise()");

    }

    public Premise(int premiseCode, String premiseName, String premiseAddress, String premiseType, String premiseState, String premiseDistrict)
    {

        this.premiseCode = premiseCode;
        this.premiseName = premiseName;
        this.premiseAddress = premiseAddress;
        this.premiseType = premiseType;
        this.premiseState = premiseState;
        this.premiseDistrict = premiseDistrict;

    }

    // Getter and setter methods
    // Getter methods
    int getPremiseCode()
    {

        return this.premiseCode;

    }

    String getPremiseName()
    {

        return this.premiseName;

    }

    String getPremiseAddress()
    {

        return this.premiseAddress;

    }

    String getPremiseType()
    {

        return this.premiseType;

    }

    String getPremiseState()
    {

        return this.premiseState;

    }

    String getPremiseDistrict()
    {

        return this.premiseDistrict;

    }

    // Setter methods

    void setPremiseCode(int premiseCode)
    {

        if (premiseCode > 0)
        {

            this.premiseCode = premiseCode;

        } else {

            throw new IllegalArgumentException("Premise code must be greater than 0");

        }

    }

    void setPremiseName(String premiseName)
    {

        if (!premiseName.isEmpty())
        {

            this.premiseName = premiseName;

        } else {

            throw new IllegalArgumentException("Premise name cannot be left blank");

        }

    }

    void setPremiseAddress(String premiseAddress)
    {

        if (!premiseAddress.isEmpty())
        {

            this.premiseAddress = premiseAddress;

        } else {

            throw new IllegalArgumentException("Premise address cannot be left blank");

        }

    }

    void setPremiseType(String premiseType)
    {

        if (!premiseType.isEmpty())
        {

            this.premiseType = premiseType;

        } else {

            throw new IllegalArgumentException("Premise type cannot be left blank");

        }

    }

    void setPremiseState(String premiseState)
    {

        if (!premiseState.isEmpty())
        {

            this.premiseState = premiseState;

        } else {

            throw new IllegalArgumentException("Premise state cannot be left blank");

        }

    }

    void setPremiseDistrict(String premiseDistrict)
    {

        if (!premiseDistrict.isEmpty())
        {

            this.premiseDistrict = premiseDistrict;

        } else {

            throw new IllegalArgumentException("Premise district cannot be left blank");

        }

    }

    // Other methods

}