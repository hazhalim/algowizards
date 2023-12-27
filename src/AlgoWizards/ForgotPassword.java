/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlgoWizards;

import java.util.List;
import java.util.Scanner;

public class ForgotPassword {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Forgot Password");
        System.out.println("---------------");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        List<UserInfo> existingUsers = UserInfo.readFromFile();
        boolean userFound = false;

        for (UserInfo user : existingUsers) {
            if (user.getUsername().equals(username)) {
                System.out.print("Enter new password: ");
                String newPassword = scanner.nextLine();
                user.setPassword(newPassword);
                userFound = true;
                break;
            }
        }

        if (!userFound) {
            System.out.println("Username not found. Please check your username and try again.");
        } else {
            UserInfo.writeToFile(existingUsers);
            System.out.println("Password updated successfully!");
        }
    }
}
