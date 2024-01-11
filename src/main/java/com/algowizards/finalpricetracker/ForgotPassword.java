package com.algowizards.finalpricetracker;

import java.io.IOException;
import java.util.Scanner;

public class ForgotPassword
{

    public static void main(String[] args) throws IOException
    {

        Scanner keyboard = new Scanner(System.in);

        System.out.println(FontColor.ansiBlue + "\n-----= Reset PriceWizard Account Password =-----\n" + FontColor.ansiReset);

        System.out.print("> Enter the username of your account: ");
        String username = keyboard.nextLine();

        boolean userFound = false;

        UserManager.readFromUserInformationFile();

        for (User user : UserManager.getUserList())
        {

            if (user.getUsername().equals(username))
            {

                userFound = true;

                System.out.println("\nTo verify your identity, please answer the security question you selected when you first signed up for your PriceWizard account.\n");
                System.out.println("Question: \"" + FontColor.ansiYellow + user.getSecurityQuestion() + FontColor.ansiReset + "\"\n");

                System.out.print("> Enter your answer to the question: ");

                String securityAnswer = keyboard.nextLine();

                if (securityAnswer.equals(user.getSecurityAnswer()))
                {

                    System.out.println("\nYour answer is correct! Your identity has been verified.\n");

                    System.out.print("> Enter your new account password: ");
                    String password = keyboard.nextLine();

                    user.setPassword(password);

                    UserManager.writeToUserInformationFile();

                    System.out.println("\nYour account password has been updated successfully!\n");

                } else {

                    System.out.println("\nSorry, your answer to the question is incorrect. Your identity cannot be verified.\n");

                }

                break;

            }

        }

        if (!userFound)
        {

            System.out.println("\nUsername not found. Please check your username and try again.\n");

        }

    }

}