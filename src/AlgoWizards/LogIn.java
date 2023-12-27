/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlgoWizards;

import java.util.List;
import java.util.Scanner;

public class LogIn {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Log In");
        System.out.println("------");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        List<UserInfo> existingUsers = UserInfo.readFromFile();
        boolean userFound = false;

        for (UserInfo user : existingUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                userFound = true;
                break;
            }
        }

        if (!userFound) {
            System.out.println("Invalid username or password. Please try again.");
        }
    }
}
