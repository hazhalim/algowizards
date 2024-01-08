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

        System.out.println("\n-----= Sign Up for a PriceWizard Account =-----\n");
        System.out.println("New Account Details:\n");

        // New account's username
        System.out.print("Enter the account's username: ");
        String username = keyboard.nextLine();

        // Check if the username already exists
        UserManager.readFromUserInformationFile(); // Load the list of users into the program

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
        System.out.print("Next, enter the account's password: ");
        String password = keyboard.nextLine();

        if (isInvalid("password", password))
        {

            return;

        }

        // New account's first name
        System.out.print("Next, enter your first name: ");
        String firstName = keyboard.nextLine();

        if (isInvalid("first name", firstName))
        {

            return;

        }

        // New account's last name
        System.out.print("Next, enter your last name: ");
        String lastName = keyboard.nextLine();

        if (isInvalid("last name", lastName))
        {

            return;

        }

        // New account's contact information
        System.out.print("Next, enter your phone number: ");
        String contactInfo = keyboard.nextLine();

        if (isInvalid("phone number", contactInfo))
        {

            return;

        }

        System.out.print("Next, enter your current home address: ");
        String address = keyboard.nextLine();

        if (isInvalid("address", address))
        {

            return;

        }

        // New account's home state
        System.out.println("Next, enter the state where your home address is located in:");
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

        // New account's home district
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

        // New account's security question
        System.out.println("Next, choose a security question that is easy and memorisable for you:");
        System.out.println("This question will be helpful if you ever forget your account password.\n");

        UserManager.setSecurityQuestionList();
        UserManager.setSecurityQuestionMap();

        for (Integer currentKey : UserManager.getSecurityQuestionMap().getKeys())
        {

            System.out.println(currentKey + ". " + UserManager.getSecurityQuestionMap().getValueByKey(currentKey));

        }

        System.out.print("\n> Enter your choice of the security questions presented: ");
        int chosenSecurityQuestion = keyboard.nextInt();
        String chosenSecurityQuestionString = UserManager.getSecurityQuestionMap().getValueByKey(chosenSecurityQuestion);

        if (isInvalid("security question", chosenSecurityQuestionString))
        {

            return;

        }

        System.out.println("\nNext, enter the answer to the security question you chose previously:");
        System.out.println("The answer you enter is very important. Do not forget your answer to prevent losing access to your account.\n");

        System.out.println("Your chosen security question: \"" + chosenSecurityQuestionString + "\"\n");

        System.out.print("> Enter your answer to the chosen security question: ");
        keyboard.nextLine();

        String securityAnswer = keyboard.nextLine();

        if (isInvalid("security answer", securityAnswer))
        {

            return;

        }

        System.out.println("\nYour answer to the security question is \"" + securityAnswer + "\". Please remember to remember or jot down your answer somewhere safe in a physical location.\n");

        UserManager.getUserList().add(new User(username, password, firstName, lastName, contactInfo, address, chosenStateString, chosenDistrictString, chosenSecurityQuestionString, securityAnswer));

        UserManager.writeToUserInformationFile();

        System.out.println("Your PriceWizard account has been successfully registered!\n");

    }

    static boolean isInvalid(String description, String value)
    {

        if (value.isBlank() || value == null)
        {

            System.out.println("The account's " + description + " cannot be left blank or be invalid. Please try again.\n");

            return true;

        } else {

            return false;

        }

    }

}