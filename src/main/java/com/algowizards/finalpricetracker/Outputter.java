package com.algowizards.finalpricetracker;

import java.util.List;

/**
 *
 * @author AlgoWizards
 * 
 * Class Description: This class will contain all methods used to display output to the end user.
 * 
 */

public class Outputter
{
    
    static void display2DList(List<List<String>> list)
    {
        
        System.out.println("Viewing list:");
        
        for (int i = 0; i < list.size(); i++)
        {
            
            for (int j = 0; j < list.get(0).size(); j++)
            {
                
                if (j != 1)
                {
                    
                    System.out.printf("%-30s", list.get(i).get(j));
                    
                } else if (j == 3) {
                    
                    System.out.printf("%-40s", list.get(i).get(j));
                    
                } else { // for j == 1
                    
                    System.out.printf("%-100s", list.get(i).get(j));
                    
                }
                
            }
            
            System.out.println();
        
        }
        
    }
    
}