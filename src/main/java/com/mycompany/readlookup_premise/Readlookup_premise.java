/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.readlookup_premise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Nazrul Ikram
 */
public class Readlookup_premise {

    public static void main(String[] args) {
        String csvFile = "C:\\Users\\Nazrul Ikram\\Documents\\NetBeansProjects\\lookup_premise.csv";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                System.out.println(line);   
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

