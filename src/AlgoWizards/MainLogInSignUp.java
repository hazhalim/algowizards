/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlgoWizards;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tmhta_
 */
public class MainLogInSignUp {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Welcome to AlgoWizard's Price Tracker");
        System.out.println("-------------------------------------");

        while (true) {
            System.out.println("1. Log In");
            System.out.println("2. Sign Up");
            System.out.println("3. Forgot Password");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scn.nextInt();
            scn.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    LogIn.main(null);
                    break;
                case 2:
                    SignUp.main(null);
                    break;
                case 3:
                    ForgotPassword.main(null);
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    List<UserInfo> existingUsers = UserInfo.readFromFile();

            }
        }
    }
}