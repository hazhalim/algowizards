package com.algowizards.finalpricetracker;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LogIn {

    public static void main(String[] args) throws IOException, CsvException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Log In");
        System.out.println("------");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        UserManager.readFromFile();
        boolean userFound = false;

        for (User user : UserManager.getUserList()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                userFound = true;
                break;
            }
        }

        if (!userFound) {
            System.out.println("Invalid username or password. Please try again.");
        } else {

            Main.main(null);

        }
    }
}
