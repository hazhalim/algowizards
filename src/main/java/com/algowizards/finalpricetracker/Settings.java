package com.algowizards.finalpricetracker;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author tmhta_
 */

public class Settings
{

    static Scanner keyboard = new Scanner(System.in);

    static int settingsMenuChoice = 0;

    public static void main(String[] args) throws IOException
    {

        while (settingsMenuChoice != 3)
        {

            System.out.println("\n-----= PriceWizard Account Settings =-----\n");

            System.out.println("Hello, " + UserManager.getCurrentUser().getFirstName() + " " + UserManager.getCurrentUser().getLastName() + "!\n");

            System.out.println("Actions:\n");

            System.out.println("""
                           1. Change your PriceWizard account username
                           2. Change your PriceWizard account password
                           3. Return to the main menu
                           """);

            System.out.print("> Select an action (1/2/3): ");
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

//                UserManager.readFromUserInformationFile(); // Load the list of users into the program

                    if (!UserManager.getCurrentUser().getSecurityAnswer().equals(securityAnswer))
                    {

                        System.out.println("Sorry, your answer to the security question is incorrect. PriceWizard is unable to verify your identity at this time.\n");

                    } else {

                        System.out.print("\nYour answer is correct, and your identity is verified! Enter your new account username, please: ");
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

//                    UserManager.readFromUserInformationFile(); // Load the list of users into the program

                    if (!UserManager.getCurrentUser().getSecurityAnswer().equals(securityAnswer))
                    {

                        System.out.println("Sorry, your answer to the security question is incorrect. PriceWizard is unable to verify your identity at this time.\n");

                    } else {

                        System.out.print("\nYour answer is correct, and your identity is verified! Enter your new account password, please: ");
                        String newPassword = keyboard.nextLine();

                        System.out.println("\nYour account password has been successfully changed from \"" + UserManager.getCurrentUser().getPassword() + "\" to \"" + newPassword + "\"!\n");

                        UserManager.getCurrentUser().setPassword(newPassword);

                        UserManager.writeToUserInformationFile();

                    }

                    break;

                }

                case 3:
                {

                    System.out.println("Exiting to the main menu...\n");

                    break;

                }

                default:
                {

                    System.out.println("Sorry, the choice inputted was not between 1 to 3. Please try again.");

                    break;

                }

            }

        }

        settingsMenuChoice = 0;

    }

}