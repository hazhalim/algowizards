package com.algowizards.finalpricetracker;

import java.util.ArrayList;
import java.util.List;

public class FuzzySearchExample
{

    static List<String> similaritySearch(List<String> phrases, String searchKey)
    {
        
        List<String> similarPhrases = new ArrayList<>();

        for (String phrase : phrases)
        {
            
            if (calculateLevenshteinDistance(phrase, searchKey) <= 10)
            {
                
                similarPhrases.add(phrase);
                
            }
            
        }

        return similarPhrases;
        
    }

    static int calculateLevenshteinDistance(String s1, String s2)
    {
        
        int[][] distanceMatrix = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++)
        {
            
            distanceMatrix[i][0] = i;
            
        }

        for (int j = 0; j <= s2.length(); j++)
        {
            
            distanceMatrix[0][j] = j;
            
        }

        for (int i = 1; i <= s1.length(); i++)
        {
            
            for (int j = 1; j <= s2.length(); j++)
            {
                
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                
                distanceMatrix[i][j] = minimum(
                        distanceMatrix[i - 1][j] + 1,
                        distanceMatrix[i][j - 1] + 1,
                        distanceMatrix[i - 1][j - 1] + cost
                );
                
            }
            
        }

        return distanceMatrix[s1.length()][s2.length()];
        
    }

    static int minimum(int a, int b, int c)
    {
        
        return Math.min(Math.min(a, b), c);
        
    }
    
}