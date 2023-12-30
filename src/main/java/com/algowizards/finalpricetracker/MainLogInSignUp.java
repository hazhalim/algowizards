package com.algowizards.finalpricetracker;

import com.opencsv.exceptions.CsvException;

import java.awt.desktop.OpenURIEvent;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tmhta_
 */
public class MainLogInSignUp {

    public static void main(String[] args) throws IOException, CsvException {

        Scanner scn = new Scanner(System.in);

        System.out.println("Welcome to PriceWizard!");
        System.out.println("-------------------------------------");

        Outputter.importAndPopulateData();

        while (true) {
            System.out.println("1. Log In to PriceWizard Account");
            System.out.println("2. Sign Up for a PriceWizard Account");
            System.out.println("3. Reset PriceWizard Account Password");
            System.out.println("4. Exit PriceWizard\n");
            System.out.print("> Enter your choice (1/2/3/4): ");

            int choice = scn.nextInt();
            scn.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    LogIn.main(null);
                    break;
                case 2:
                    SignUp.main(null);
                    break;
                case 3:
                    ForgotPassword.main(null);
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    UserManager.readFromFile();

            }
        }
    }
}