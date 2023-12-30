package com.algowizards.finalpricetracker;

import java.util.Scanner;

public class ForgotPassword
{

    public static void main(String[] args)
    {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("-----= Reset PriceWizard Account Password =-----\n");

        System.out.print("Enter your username: ");
        String username = keyboard.nextLine();

        boolean userFound = false;

        UserManager.readFromFile();

        for (User user : UserManager.getUserList())
        {

            if (user.getUsername().equals(username))
            {

                userFound = true;

                System.out.println("Answer the security question correctly to verify your identity:\n");
                System.out.println(user.getSecurityQuestion() + "\n");

                System.out.print("Enter your answer to the question: ");
                String securityAnswer = keyboard.nextLine();

                if (securityAnswer.equals(user.getSecurityAnswer()))
                {

                    System.out.println("Your answer is correct!\n");

                    System.out.print("Enter your new password: ");
                    String password = keyboard.nextLine();

                    user.setPassword(password);

                    UserManager.writeToFile();

                    System.out.println("\nPassword updated successfully!");

                } else {

                    System.out.println("Sorry, your answer to the question is incorrect. Your identity cannot be verified.");

                }

                break;

            }

        }

        if (!userFound)
        {

            System.out.println("Username not found. Please check your username and try again.");

        }

    }

}