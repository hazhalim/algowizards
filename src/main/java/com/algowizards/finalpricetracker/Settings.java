package com.algowizards.finalpricetracker;

import static com.algowizards.finalpricetracker.SignUp.isInvalid;
import static com.algowizards.finalpricetracker.SignUp.listOfDistricts;
import static com.algowizards.finalpricetracker.SignUp.listOfStates;
import static com.algowizards.finalpricetracker.SignUp.mapOfDistricts;
import static com.algowizards.finalpricetracker.SignUp.mapOfStates;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author tmhta_
 */

public class Settings
{

    // Instance variables
    static Scanner keyboard = new Scanner(System.in);
    public static final String ansiReset = "\u001B[0m";
    public static final String ansiYellow = "\u001B[33m";

    static int settingsMenuChoice = 0;

    // Main method
    public static void main(String[] args) throws IOException
    {

        while (settingsMenuChoice != 5)
        {

//            JLabel intro = new Jlabel ("\n-----= PriceWizard Account Settings =-----\n", JLabel.CENTER);
//            intro.setForeground(Color.CYAN);
//            System.out.println(intro);

            System.out.println(ansiYellow + "\n-----= PriceWizard Account Settings =-----\n" + ansiReset);

            UserManager.readFromUserInformationFile();
            
            System.out.println("Hello, " + UserManager.getCurrentUser().getFirstName() + " " + UserManager.getCurrentUser().getLastName() + "!\n");

            System.out.println("Actions:\n");

            System.out.println("""
                           1. Change your PriceWizard account username
                           2. Change your PriceWizard account password
                           3. Change your PriceWizard name
                           4. Change your PriceWizard address
                           5. Return to the main menu
                           """);

            System.out.print("> Select an action (1/2/3/4/5): ");
            settingsMenuChoice = keyboard.nextInt();

            System.out.println();

            switch (settingsMenuChoice)
            {

                case 1:
                {

                    System.out.println("To verify your identity, please answer the security question you selected when you first signed up for your PriceWizard account.\n");
                    System.out.println("Question: \"" + UserManager.getCurrentUser().getSecurityQuestion() + "\"\n");

                    System.out.print("> Enter your answer to the question: ");

                    keyboard.nextLine(); // Consume the newline character above

                    String securityAnswer = keyboard.nextLine();

                    if (!UserManager.getCurrentUser().getSecurityAnswer().equals(securityAnswer))
                    {

                        System.out.println("Sorry, your answer to the security question is incorrect. PriceWizard is unable to verify your identity at this time.\n");

                    } else {

                        System.out.print("\n> Your answer is correct, and your identity is verified! Enter your new account username, please: ");
                        String newUsername = keyboard.nextLine();

                        File existingCartFile = new File(UserManager.getCurrentUser().getCartPath());

                        System.out.println("\nYour account username has been successfully changed from \"" + UserManager.getCurrentUser().getUsername() + "\" to \"" + newUsername + "\"!\n");

                        UserManager.getCurrentUser().setUsername(newUsername);

                        UserManager.getCurrentUser().setCartPath(UserManager.getCurrentUser().getCartDirectory() + UserManager.getCurrentUser().getUsername() + "_cart.csv");

                        UserManager.writeToUserInformationFile();

                        File newCartFile = new File(UserManager.getCurrentUser().getCartPath());

                        if (existingCartFile.exists())
                        {

                            boolean successRenameCartFile = existingCartFile.renameTo(newCartFile);

                            if (!successRenameCartFile)
                            {

                                throw new RuntimeException("Failed to rename the user's cart file to the new username...");

                            }

                        }

                    }

                    break;

                }

                case 2:
                {

                    System.out.println("To verify your identity, please answer the security question you selected when you first signed up for your PriceWizard account.\n");

                    System.out.println("Question: \"" + UserManager.getCurrentUser().getSecurityQuestion() + "\"\n");

                    System.out.print("> Enter your answer to the question: ");

                    keyboard.nextLine(); // Consume the newline character above

                    String securityAnswer = keyboard.nextLine();

                    if (!UserManager.getCurrentUser().getSecurityAnswer().equals(securityAnswer))
                    {

                        System.out.println("Sorry, your answer to the security question is incorrect. PriceWizard is unable to verify your identity at this time.\n");

                    } else {

                        String previousPassword = UserManager.getCurrentUser().getPassword();

                        System.out.print("\n> Your answer is correct, and your identity is verified! Enter your new account password, please: ");
                        String newPassword = keyboard.nextLine();

                        UserManager.getCurrentUser().setPassword(newPassword);

                        UserManager.writeToUserInformationFile();

                        System.out.println("\nYour account password has been successfully changed from \"" + previousPassword + "\" to \"" + UserManager.getCurrentUser().getPassword() + "\"!\n");

                    }

                    break;

                }

                case 3:
                {

                    System.out.println("Actions:\n");
                    System.out.println("""
                                       1. Change your first name
                                       2. Change your last name
                                       """);

                    System.out.print("> Select an action (1/2): ");
                    int settingsChangeNameChoice = keyboard.nextInt();

                    switch (settingsChangeNameChoice)
                    {

                        case 1:
                        {
                            System.out.println("To verify your identity, please answer the security question you selected when you first signed up for your PriceWizard account.\n");

                            System.out.println("Question: \"" + UserManager.getCurrentUser().getSecurityQuestion() + "\"\n");

                            System.out.print("> Enter your answer to the question: ");

                            keyboard.nextLine(); // Consume the newline character above

                            String securityAnswer = keyboard.nextLine();

                            if (!UserManager.getCurrentUser().getSecurityAnswer().equals(securityAnswer))
                            {

                                System.out.println("Sorry, your answer to the security question is incorrect. PriceWizard is unable to verify your identity at this time.\n");

                            } else {

                                String previousFirstName = UserManager.getCurrentUser().getFirstName();

                                System.out.print("\n> Your answer is correct, and your identity is verified! Enter your new First Name, please: ");
                                String newFirstName = keyboard.nextLine();

                                UserManager.getCurrentUser().setFirstName(newFirstName);

                                UserManager.writeToUserInformationFile();

                                System.out.println("\nYour first name has been successfully changed from \"" + previousFirstName + "\" to \"" + UserManager.getCurrentUser().getFirstName() + "\"!\n");

                            }

                            break;

                        }

                        case 2:
                        {

                            System.out.println("To verify your identity, please answer the security question you selected when you first signed up for your PriceWizard account.\n");

                            System.out.println("Question: \"" + UserManager.getCurrentUser().getSecurityQuestion() + "\"\n");

                            System.out.print("> Enter your answer to the question: ");

                            keyboard.nextLine(); // Consume the newline character above

                            String securityAnswer = keyboard.nextLine();

                            if (!UserManager.getCurrentUser().getSecurityAnswer().equals(securityAnswer))
                            {

                                System.out.println("Sorry, your answer to the security question is incorrect. PriceWizard is unable to verify your identity at this time.\n");

                            } else {

                                String previousLastName = UserManager.getCurrentUser().getLastName();

                                System.out.print("\n> Your answer is correct, and your identity is verified! Enter your new Last Name, please: ");
                                String newLastName = keyboard.nextLine();

                                UserManager.getCurrentUser().setLastName(newLastName);

                                UserManager.writeToUserInformationFile();

                                System.out.println("\nYour last name has been successfully changed from \"" + previousLastName + "\" to \"" + UserManager.getCurrentUser().getLastName() + "\"!\n");

                            }

                            break;

                        }

                    }

                    break;

                }

                case 4:
                {

                    System.out.println("Actions:\n");
                    System.out.println("""
                                       1. Change your address
                                       2. Change your district
                                       3. Change your state
                                       """);

                    System.out.print("> Select an action (1/2/3): ");
                    int settingsChangeAddressChoice = keyboard.nextInt();

                    switch (settingsChangeAddressChoice)
                    {

                        case 1:
                        {

                            String previousAddress = UserManager.getCurrentUser().getAddress();

                            System.out.print("Next, enter your new home address: ");

                            keyboard.nextLine(); // Consume the newline character above

                            String newAddress = keyboard.nextLine();

                            if (isInvalid("address", newAddress)) {

                                return;

                            }
                            
                            UserManager.getCurrentUser().setAddress(newAddress);
                            
                            UserManager.writeToUserInformationFile();

                            System.out.println("Your address has been successfully changed from:\n");

                            System.out.println("\"" + previousAddress + "\"\n");

                            System.out.println("to\n");

                            System.out.println("\"" + UserManager.getCurrentUser().getAddress() + "\"!\n");

                            break;

                        }

                        case 2:
                        {

                            // To update an account's home district
                            String previousDistrict = UserManager.getCurrentUser().getDistrict();

                            System.out.println("Enter the district where your new home is located in:\n");

                            System.out.println("This will determine the premises most relevant to you.\n");

                            listOfDistricts = UserManager.getDistrictList(UserManager.getCurrentUser().getState(), new DataStructure.List1D<>(PremiseManager.getPremiseList()));
                            mapOfDistricts = UserManager.getDistrictMapping(listOfDistricts);

                            System.out.println("The districts of the state of " + UserManager.getCurrentUser().getState() + " are:\n");

                            for (Integer currentKey : mapOfDistricts.getKeys())
                            {

                                System.out.println(currentKey + ". " + mapOfDistricts.getValueByKey(currentKey));

                            }

                            System.out.print("\n> Choose a new home district: ");

                            int newChosenDistrict = keyboard.nextInt();
                            String newChosenDistrictString = mapOfDistricts.getValueByKey(newChosenDistrict); // <--- the user's home district

                            System.out.println("\nYou've chosen the district of: " + newChosenDistrictString + ", " + UserManager.getCurrentUser().getState() + "\n");

                            UserManager.getCurrentUser().setDistrict(newChosenDistrictString);
                            UserManager.writeToUserInformationFile();

                            System.out.println("Your home district has been changed from " + previousDistrict + " to " + UserManager.getCurrentUser().getDistrict() + "!\n");

                            break;

                        }

                        case 3:
                        {

                            // To update an account's home state
                            String previousState = UserManager.getCurrentUser().getState();
                            String previousDistrict = UserManager.getCurrentUser().getDistrict();

                            System.out.println("Enter the state where your new home address is located in:");
                            System.out.println("This will determine the premises most relevant to you.\n");

                            listOfStates = UserManager.getStateList(new DataStructure.List1D<>(PremiseManager.getPremiseList()));
                            mapOfStates = UserManager.getStateMapping(listOfStates);

                            for (Integer currentKey : mapOfStates.getKeys())
                            {

                                System.out.println(currentKey + ". " + mapOfStates.getValueByKey(currentKey));

                            }

                            System.out.print("\n> Choose a new home state: ");

                            int newChosenState = keyboard.nextInt();
                            String newChosenStateString = mapOfStates.getValueByKey(newChosenState);

                            System.out.println("\nYou've chosen the state of: " + newChosenStateString + "\n");

                            UserManager.getCurrentUser().setState(newChosenStateString);
                            UserManager.writeToUserInformationFile();

                            System.out.println("Your home state has been changed from " + previousState + " to " + UserManager.getCurrentUser().getState() + "!\n");

                            // Repeat to choose a district within the new state
                            System.out.println("Next, choose a new home district within the state of " + newChosenStateString + ":\n");

                            System.out.println("This will determine the premises most relevant to you.\n");

                            listOfDistricts = UserManager.getDistrictList(UserManager.getCurrentUser().getState(), new DataStructure.List1D<>(PremiseManager.getPremiseList()));
                            mapOfDistricts = UserManager.getDistrictMapping(listOfDistricts);

                            System.out.println("The districts of the state of " + UserManager.getCurrentUser().getState() + " are:\n");

                            for (Integer currentKey : mapOfDistricts.getKeys())
                            {

                                System.out.println(currentKey + ". " + mapOfDistricts.getValueByKey(currentKey));

                            }

                            System.out.print("\n> Choose a new home district: ");

                            int newChosenDistrict = keyboard.nextInt();
                            String newChosenDistrictString = mapOfDistricts.getValueByKey(newChosenDistrict);

                            System.out.println("\nYou've chosen the district of: " + newChosenDistrictString + ", " + UserManager.getCurrentUser().getState() + "\n");

                            UserManager.getCurrentUser().setDistrict(newChosenDistrictString);
                            UserManager.writeToUserInformationFile();

                            System.out.println("Your home district has been changed from " + previousDistrict + " to " + UserManager.getCurrentUser().getDistrict() + "!\n");

                            break;

                        }

                    }

                    break;

                }

                case 5:
                {

                    System.out.println("Exiting to the main menu...\n");

                    break;

                }

                default:
                {

                    System.out.println("Sorry, the choice inputted was not between 1 to 5. Please try again.");

                    break;

                }

            }

        }

        settingsMenuChoice = 0;

    }

}