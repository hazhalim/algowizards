package com.algowizards.finalpricetracker;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author tmhta_
 *
 */

public class MainLogInSignUp
{

    public static void main(String[] args) throws IOException, CsvException, SQLException
    {

        Scanner keyboard = new Scanner(System.in);

        int loginMenuChoice = 0;

        System.out.println(FontColor.ansiBlue + "-----= Welcome to PriceWizard! =-----\n" + FontColor.ansiReset);
        System.out.println("Track the prices of your favourite items and necessities with ease!\n");

//        System.out.println("Importing price points into the database...");
//        DatabaseManager.loadCSVFileIntoDatabaseTable(PriceCatcherManager.getPriceCatcherDatabaseFileName(), "PriceCatcher");
//
//        System.out.println("Importing security questions into the database...");
//        DatabaseManager.loadCSVFileIntoDatabaseTable(UserManager.getSecurityQuestionDatabaseFileName(), "SecurityQuestion", true);

        FileManager.importAndPopulateData(); // We MUST import the data first for the state and district selection in SignUp class to function properly
//        UserManager.setSecurityQuestionList();

//        if (!DatabaseManager.isDatabaseInitialised())
//        {
//
//            DatabaseManager.initialiseDatabase();
//
//        } else {
//
//            if (!DatabaseManager.isDataImported())
//            {
//
//                System.out.println("Importing security questions into the database...");
//                DatabaseManager.loadSecurityQuestionCSVFileIntoDatabaseTable(UserManager.getSecurityQuestionDatabaseFileName());
//
//                System.out.println("Importing products into the database...");
//                DatabaseManager.loadCSVFileIntoDatabaseTable(ProductManager.getLookupItemDatabaseFileName(), "Product");
//
//                System.out.println("Importing premises into the database...");
//                DatabaseManager.loadPremiseCSVFileIntoDatabaseTable(PremiseManager.getLookupPremiseDatabaseFileName());
//
//                System.out.println("Importing price points into the database...");
//                DatabaseManager.loadPriceCatcherCSVFileIntoDatabaseTable(PriceCatcherManager.getPriceCatcherDatabaseFileName());
//
//            }
//
//        }

        System.out.println(PriceCatcherManager.getPriceCatcherList().size());
        while (loginMenuChoice != 4)
        {

            System.out.println(FontColor.ansiGreen + "-----= PriceWizard Login Menu =-----\n" + FontColor.ansiReset);

            System.out.println("1. Log In to PriceWizard");
            System.out.println("2. Sign Up for a PriceWizard Account");
            System.out.println("3. Reset PriceWizard Account Password");
            System.out.println("4. Exit PriceWizard\n");

            System.out.print("> Enter your choice (1/2/3/4): ");

            loginMenuChoice = keyboard.nextInt();
            keyboard.nextLine(); // Consume the newline character

            switch (loginMenuChoice)
            {
                case 1:
                {

                    LogIn.main(null);

                    break;

                }

                case 2:
                {

                    SignUp.main(null);

                    break;

                }

                case 3:
                {

                    ForgotPassword.main(null);

                    break;

                }

                case 4:
                {

                    System.out.println("\nThank you for using this program!\n");
                    System.out.println("Exiting PriceWizard...");

                    break;

                }

                default:
                {

                    System.out.println("The choice entered is invalid. Please try again.\n");
                    UserManager.readFromUserInformationFile();

                    break;

                }

            }

        }

    }

}