package com.algowizards.finalpricetracker;

import java.util.List;
import java.util.Scanner;

public class ForgotPassword
{

    public static void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Forgot Password");
        System.out.println("---------------");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        boolean userFound = false;

        for (User user : UserManager.getUserList())
        {

            if (user.getUsername().equals(username))
            {

                System.out.print("Enter new password: ");
                String newPassword = scanner.nextLine();

                user.setPassword(newPassword);

                userFound = true;

                UserManager.writeToFile();

                break;

            }
        }

        if (!userFound)
        {

            System.out.println("Username not found. Please check your username and try again.");

        } else {

            System.out.println("Password updated successfully!");

        }

    }

}