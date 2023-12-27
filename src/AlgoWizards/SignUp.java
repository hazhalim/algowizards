/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlgoWizards;

/**
 * @author tmhta_
 */
import java.util.List;
import java.util.Scanner;

public class SignUp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sign Up");
        System.out.println("-------");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Check if the username already exists
        List<UserInfo> existingUsers = UserInfo.readFromFile();
        for (UserInfo user : existingUsers) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Please choose a different one.");
                return;
            }
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter contact info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        UserInfo newUser = new UserInfo(username, password, firstName, lastName, contactInfo, address);

        existingUsers.add(newUser);

        UserInfo.writeToFile(existingUsers);

        System.out.println("User successfully registered!");
    }
}
