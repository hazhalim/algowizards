/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algowizards.finalpricetracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tmhta_
 */
public class Settings {

    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n-----= PrizeWizard Account Settings =-----\n");
        System.out.println("Hello, " + UserManager.getCurrentUser().getFirstName() + "!");
        System.out.println("""
                           1. Change username
                           2. Change password
                           """);
        System.out.print("Enter your choice (1/2) --> ");
        int userChoice = keyboard.nextInt();

        switch (userChoice) {
            case 1: {
                int loop = 0; // to loop if current username is not matched

                do {
                    System.out.println("Answer this question before changing your username.");
                    System.out.print(UserManager.getCurrentUser().getSecurityQuestion());
                    System.out.print("Your answer : ");
                    String securityAnswer = keyboard.nextLine();

                    UserManager.readFromFile(); // Load the list of users into the program

                    if (UserManager.getUserList() != null) // AKA if the file is not empty
                    {

                        if (!UserManager.getCurrentUser().getSecurityAnswer().equals(securityAnswer)) {

                            System.out.println("The security answer you enter is not match to your current username.\n");
                            loop = 1; //to loop again the process until user enter the correct username
                            //return;

                        } else {

                            System.out.println("Alright! Now, set your new username : ");
                            String newUsername = keyboard.nextLine();
                            UserManager.getCurrentUser().setUsername(newUsername);
                            List<String[]> intermediateUser = new ArrayList<>();
                            for (User user : UserManager.getUserList()){
                                intermediateUser.add(UserManager.toArray(user));
                            }
                            FileManager.write2DListIntoCSVFile()
                            UserManager.writeToFile();
                        }

                    }

                } while (loop == 1);
            }
        }
    }
}
