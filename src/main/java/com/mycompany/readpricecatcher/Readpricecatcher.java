/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.readpricecatcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Nazrul Ikram
 */
public class Readpricecatcher {

//    public static void main(String[] args) {
//        String csvFile = "C:\\Users\\Nazrul Ikram\\Documents\\NetBeansProjects\\pricecatcher_2023-08.csv";
//        String line = "";
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);   
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static void main(String[] args) {
        String path = "C:\\Users\\Nazrul Ikram\\Documents\\NetBeansProjects\\pricecatcher_2023-08.csv";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // Assuming the first line contains headers
            String headerLine = br.readLine();
            if (headerLine != null) {
                String[] headers = headerLine.split(",");
                int dateIndex = -1;
                int premise_codeIndex = -1;
                int item_codeIndex = -1;
                int priceIndex = -1;
               

                // Find the indices of Code and State columns
                for (int i = 0; i < headers.length; i++) {
                    if ("date".equalsIgnoreCase(headers[i])) {
                        dateIndex = i;
                    } else if ("premise_code".equalsIgnoreCase(headers[i])) {
                        premise_codeIndex = i; 
                    } else if ("item_code".equalsIgnoreCase(headers[i])) {
                        item_codeIndex = i;
                    } else if ("price".equalsIgnoreCase(headers[i])) {
                        priceIndex = i;
                    }
                }

                if (dateIndex != -1 && premise_codeIndex != -1 && item_codeIndex != -1 && priceIndex != -1) {
                    while ((line = br.readLine()) != null) {
                        String[] values = line.split(",");
                        if (values.length > dateIndex && values.length > premise_codeIndex && values.length > item_codeIndex && values.length > priceIndex ) {
                            System.out.println("date: " + values[dateIndex] + " \npremise_code: " + values[premise_codeIndex] + " \nitem_code: " + values[item_codeIndex]
                            + " \nprice: " + values[priceIndex]);
                            System.out.println("____________________________________________");
                        } else {
                            System.out.println("Error in line: " + line);
                        }
                    }
                } else {
                    System.out.println("Code or State column not found in headers.");
                }
            } else {
                System.out.println("Empty file or unable to read headers.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

