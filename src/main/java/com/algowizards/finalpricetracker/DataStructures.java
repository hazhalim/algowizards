package com.algowizards.finalpricetracker;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author AlgoWizards
 * 
 * Class Description: This class manages all the data structures used in the program (arrays, lists, etc.).
 * 
 */

public class DataStructures
{
    
    // Instance variables (type of data structures)
    private List<String> list;
    private List<List<String>> list2D;
    
    // For 1D list variables
    private int listSize;
    
    // For 2D list variables
    private int rowSize;
    private int columnSize; // Assuming all rows have the same number of columns
    
    
    
    // Constructors
    public DataStructures()
    {
        
        throw new IllegalArgumentException("Constructor DataStructures() failed: no arguments passed into constructor");
        
    }
    
    public DataStructures(String dataStructure, List<String> list) // 1D list
    {
        if (dataStructure.equalsIgnoreCase("list"))
        {
            
            this.list = list;
            this.listSize = this.list.size();
            
        } else {
            
            throw new IllegalArgumentException("Constructor DataStructures(" + dataStructure + ", <List<String> list) failed: string denoting data structure does not match data structure type passed into constructor");
            
        }
        
    }
    
    public DataStructures(String dataStructure, int dimension, List<List<String>> list2D) // 2D list
    {
        if ((dataStructure.equalsIgnoreCase("list2D")) && (dimension == 2))
        {
            
            this.list2D = list2D;
            this.rowSize = this.list2D.size();
            this.columnSize = this.list2D.get(0).size();
            
            
        } else {
            
            throw new IllegalArgumentException("Constructor DataStructures(" + dataStructure + ", " + dimension + ", List<List<String>>) failed: string does not match data structure type or dimension does not match dimension of data structure passed into constructor");
            
        }
        
    }
    
    // Getter and setter methods
    // For 1D lists
    int getListSize()
    {
        
        return this.listSize;
        
    }
    
    List<String> getList()
    {
        
        return this.list;
        
    }
    
    String getListValue(int i)
    {
        
        return this.list.get(i);
        
    }
    
    void setListSize(int listSize)
    {
        
        if (listSize >= 0)
        {
            
            this.listSize = listSize;
            
        } else {
            
            throw new IllegalArgumentException("Method setListSize(" + listSize + ") failed: list size must be at least 0");
            
        }
        
    }
    
    void setList(List<String> list)
    {
        
        this.list = list;
        setListSize(this.list.size());
        
    }
    
    void setListValue(int i, String value)
    {
        
        this.list.set(i, value);
        
    }
    
    // For 2D lists
    int getRowSize()
    {
        
        return this.rowSize;
        
    }
    
    int getColumnSize()
    {
        
        return this.columnSize;
        
    }
    
    List<List<String>> getlist2D()
    {
        
        return this.list2D;
        
    }
    
    List<String> getlist2DRow(int i)
    {
        
        return this.list2D.get(i);
        
    }
    
    List<String> getlist2DColumn(int j)
    {
        
        List<String> column = new ArrayList<>();
        
        // ...
        
    }
    
    
    
    // Other methods
    
}