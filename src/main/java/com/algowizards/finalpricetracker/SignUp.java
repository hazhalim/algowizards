package com.algowizards.finalpricetracker;

/**
 * @author tmhta_
 */

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.*;

public class SignUp
{

    static DataStructure.List1D<String> listOfStates = null;
    static DataStructure.Mapping<Integer, String> mapOfStates = null;

    static DataStructure.List1D<String> listOfDistricts = null;
    static DataStructure.Mapping<Integer, String> mapOfDistricts = null;

    public static void main(String[] args) throws IOException, CsvException
    {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("-----= Sign Up for a PriceWizard Account =-----\n");
        System.out.println("New Account Details:\n");

        // New account's username
        System.out.print("Enter the account's username: ");
        String username = keyboard.nextLine();

        // Check if the username already exists
        UserManager.readFromFile(); // Load the list of users into the program

        if (UserManager.getUserList() != null) // AKA if the file is not empty
        {

            for (User user : UserManager.getUserList())
            {

                if (user.getUsername().equals(username)) {

                    System.out.println("This username already exists on PriceWizard. Please choose a different one.\n");

                    return;

                }

            }

        }

        // New account's password
        System.out.print("Enter the account's password: ");
        String password = keyboard.nextLine();

        if (isBlank("password", password))
        {

            return;

        }

        // New account's first name
        System.out.print("Enter your first name: ");
        String firstName = keyboard.nextLine();

        if (isBlank("first name", firstName))
        {

            return;

        }

        // New account's last name
        System.out.print("Enter your last name: ");
        String lastName = keyboard.nextLine();

        if (isBlank("last name", lastName))
        {

            return;

        }

        // New account's contact information
        System.out.print("Enter your phone number: ");
        String contactInfo = keyboard.nextLine();

        if (isBlank("phone number", contactInfo))
        {

            return;

        }

        System.out.print("Enter your current home address: ");
        String address = keyboard.nextLine();

        if (isBlank("address", address))
        {

            return;

        }

        // Get the home state of the user
        System.out.println("Enter the state where your home address is located in:");
        System.out.println("This will determine the premises most relevant to you.\n");

        listOfStates = UserManager.getStateList(new DataStructure.List1D<>(PremiseManager.getPremiseList()));
        mapOfStates = UserManager.getStateMapping(listOfStates);

        for (Integer currentKey : mapOfStates.getKeys())
        {

            System.out.println(currentKey + ". " + mapOfStates.getValueByKey(currentKey));

        }

        System.out.print("\n> Choose your home state: ");

        int chosenState = keyboard.nextInt();
        String chosenStateString = mapOfStates.getValueByKey(chosenState); // <--- the user's home state

        System.out.println("\nYou've chosen the state of: " + chosenStateString + "\n");

        listOfDistricts = UserManager.getDistrictList(chosenStateString, new DataStructure.List1D<>(PremiseManager.getPremiseList()));
        mapOfDistricts = UserManager.getDistrictMapping(listOfDistricts);

        System.out.println("The districts of the state of " + chosenStateString + " are:\n");

        for (Integer currentKey : mapOfDistricts.getKeys())
        {

            System.out.println(currentKey + ". " + mapOfDistricts.getValueByKey(currentKey));

        }

        System.out.print("\n> Choose your home district: ");

        int chosenDistrict = keyboard.nextInt();
        String chosenDistrictString = mapOfDistricts.getValueByKey(chosenDistrict); // <--- the user's home district

        System.out.println("\nYou've chosen the district of: " + chosenDistrictString + ", " + chosenStateString + "\n");

        System.out.print("Enter a security question: ");
        keyboard.nextLine(); // <--- consume the newline character

        String securityQuestion = keyboard.nextLine();

        if (isBlank("security question", securityQuestion))
        {

            return;

        }

        System.out.print("Enter the answer to the security question: ");
        String securityAnswer = keyboard.nextLine();

        if (isBlank("security answer", securityAnswer))
        {

            return;

        }

        User newUser = new User(username, password, firstName, lastName, contactInfo, address, chosenStateString, chosenDistrictString, securityQuestion, securityAnswer);

        UserManager.getUserList().add(newUser);

        UserManager.writeToFile();

        System.out.println("Your account is successfully registered!");

    }

    static boolean isBlank(String description, String value)
    {

        if (value.isBlank())
        {

            System.out.println("The account's " + description + " cannot be left blank. Please try again.\n");

            return true;

        } else {

            return false;

        }

    }

}