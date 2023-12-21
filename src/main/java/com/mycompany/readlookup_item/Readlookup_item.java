/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.readlookup_item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Nazrul Ikram
 */
public class Readlookup_item {

    public static void main(String[] args) {
        String csvFile = "C:\\Users\\Nazrul Ikram\\Documents\\NetBeansProjects\\lookup_item.csv";
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
