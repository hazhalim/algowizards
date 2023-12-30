package com.algowizards.finalpricetracker;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.Scanner;

public class LogIn
{

    public static void main(String[] args) throws IOException, CsvException
    {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Log In to PriceWizard");
        System.out.println("------");

        System.out.print("Enter your username: ");
        String username = keyboard.nextLine();

        System.out.print("Enter your password: ");
        String password = keyboard.nextLine();

        UserManager.readFromFile();
        boolean userFound = false;

        for (User user : UserManager.getUserList())
        {

            if ((user.getUsername().equals(username)) && (user.getPassword().equals(password)))
            {

                System.out.println("Your login is successful, welcome!");
                userFound = true;

                break;

            }

        }

        if (!userFound)
        {

            System.out.println("Invalid username or password. Please try again.");

        } else {

            userFound = false;

            Outputter.mainMenu();

        }

    }

}