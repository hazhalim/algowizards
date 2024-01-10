package com.algowizards.finalpricetracker;

import static com.algowizards.finalpricetracker.SignUp.isInvalid;
import static com.algowizards.finalpricetracker.SignUp.listOfDistricts;
import static com.algowizards.finalpricetracker.SignUp.listOfStates;
import static com.algowizards.finalpricetracker.SignUp.mapOfDistricts;
import static com.algowizards.finalpricetracker.SignUp.mapOfStates;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author tmhta_
 */
public class Settings {

    static Scanner keyboard = new Scanner(System.in);

    static int settingsMenuChoice = 0;

    public static void main(String[] args) throws IOException {

        while (settingsMenuChoice != 3) {

            System.out.println("\n-----= PriceWizard Account Settings =-----\n");

            UserManager.readFromUserInformationFile();
            
            System.out.println("Hello, " + UserManager.getCurrentUser().getFirstName() + " " + UserManager.getCurrentUser().getLastName() + "!\n");

            System.out.println("Actions:\n");

            System.out.println("""
                           1. Change your PriceWizard account username
                           2. Change your PriceWizard account password
                           3. Change your PriceWizard name
                           4. Change your PriceWizard address
                           5. Return to the main menu
                           """);

            System.out.print("> Select an action (1/2/3/4/5): ");
            settingsMenuChoice = keyboard.nextInt();

            System.out.println();

            switch (settingsMenuChoice) {

                case 1: {

                    System.out.println("To verify your identity, please answer the security question you selected when you first signed up for your PriceWizard account.\n");
                    System.out.println("Question: \"" + UserManager.getCurrentUser().getSecurityQuestion() + "\"\n");

                    System.out.print("> Enter your answer to the question: ");

                    keyboard.nextLine(); // Consume the newline character above

                    String securityAnswer = keyboard.nextLine();

//                UserManager.readFromUserInformationFile(); // Load the list of users into the program
                    if (!UserManager.getCurrentUser().getSecurityAnswer().equals(securityAnswer)) {

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

                        if (existingCartFile.exists()) {

                            boolean successRenameCartFile = existingCartFile.renameTo(newCartFile);

                            if (!successRenameCartFile) {

                                throw new RuntimeException("Failed to rename the user's cart file to the new username...");

                            }

                        }

                    }

                    break;

                }

                case 2: {

                    System.out.println("To verify your identity, please answer the security question you selected when you first signed up for your PriceWizard account.\n");

                    System.out.println("Question: \"" + UserManager.getCurrentUser().getSecurityQuestion() + "\"\n");

                    System.out.print("> Enter your answer to the question: ");

                    keyboard.nextLine(); // Consume the newline character above

                    String securityAnswer = keyboard.nextLine();

//                    UserManager.readFromUserInformationFile(); // Load the list of users into the program
                    if (!UserManager.getCurrentUser().getSecurityAnswer().equals(securityAnswer)) {

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

                case 3: {

                    System.out.println("Actions:\n");
                    System.out.println("""
                                       1. Change your first name
                                       2. Change your last name
                                       """);

                    System.out.print("> Select an action (1/2): ");
                    int settingsChangeNameChoice = keyboard.nextInt();

                    switch (settingsChangeNameChoice) {

                        case 1: {
                            System.out.println("To verify your identity, please answer the security question you selected when you first signed up for your PriceWizard account.\n");

                            System.out.println("Question: \"" + UserManager.getCurrentUser().getSecurityQuestion() + "\"\n");

                            System.out.print("> Enter your answer to the question: ");

                            keyboard.nextLine(); // Consume the newline character above

                            String securityAnswer = keyboard.nextLine();

                            if (!UserManager.getCurrentUser().getSecurityAnswer().equals(securityAnswer)) {

                                System.out.println("Sorry, your answer to the security question is incorrect. PriceWizard is unable to verify your identity at this time.\n");

                            } else {

                                System.out.print("\nYour answer is correct, and your identity is verified! Enter your new First Name, please: ");
                                String newFirstName = keyboard.nextLine();

                                System.out.println("\nYour First Name has been successfully changed from \"" + UserManager.getCurrentUser().getFirstName() + "\" to \"" + newFirstName + "\"!\n");

                                UserManager.getCurrentUser().setPassword(newFirstName);

                                UserManager.writeToUserInformationFile();

                            }

                            break;

                        }

                        case 2: {

                            System.out.println("To verify your identity, please answer the security question you selected when you first signed up for your PriceWizard account.\n");

                            System.out.println("Question: \"" + UserManager.getCurrentUser().getSecurityQuestion() + "\"\n");

                            System.out.print("> Enter your answer to the question: ");

                            keyboard.nextLine(); // Consume the newline character above

                            String securityAnswer = keyboard.nextLine();

                            if (!UserManager.getCurrentUser().getSecurityAnswer().equals(securityAnswer)) {

                                System.out.println("Sorry, your answer to the security question is incorrect. PriceWizard is unable to verify your identity at this time.\n");

                            } else {

                                System.out.print("\nYour answer is correct, and your identity is verified! Enter your new Last Name, please: ");
                                String newLastName = keyboard.nextLine();

                                System.out.println("\nYour Last Name has been successfully changed from \"" + UserManager.getCurrentUser().getPassword() + "\" to \"" + newLastName + "\"!\n");

                                UserManager.getCurrentUser().setPassword(newLastName);

                                UserManager.writeToUserInformationFile();

                            }

                            break;

                        }
                    }

                    break;

                }

                case 4: {

                    System.out.println("Actions:\n");
                    System.out.println("""
                                       1. Change your address
                                       2. Change your district
                                       3. Change your state
                                       """);

                    System.out.print("> Select an action (1/2/3): ");
                    int settingsChangeAddressChoice = keyboard.nextInt();

                    switch (settingsChangeAddressChoice) {

                        case 1: {

                            System.out.print("Next, enter your new home address: ");
                            String newAddress = keyboard.nextLine();

                            if (isInvalid("address", newAddress)) {

                                return;

                            }
                            
                            UserManager.getCurrentUser().setDistrict(newAddress);
                            
                            UserManager.writeToUserInformationFile();
                            
                            break;

                        }

                        case 2: {

                            // to update account's home district
                            System.out.println("Enter the district where your new home is located in:");
                            System.out.println("This will determine the premises most relevant to you.\n");
                            listOfDistricts = UserManager.getDistrictList(UserManager.getCurrentUser().getState(), new DataStructure.List1D<>(PremiseManager.getPremiseList()));
                            mapOfDistricts = UserManager.getDistrictMapping(listOfDistricts);

                            System.out.println("The districts of the state of " + UserManager.getCurrentUser().getState() + " are:\n");

                            for (Integer currentKey : mapOfDistricts.getKeys()) {

                                System.out.println(currentKey + ". " + mapOfDistricts.getValueByKey(currentKey));

                            }

                            System.out.print("\n> Choose your home district: ");

                            int newChosenDistrict = keyboard.nextInt();
                            String newChosenDistrictString = mapOfDistricts.getValueByKey(newChosenDistrict); // <--- the user's home district

                            System.out.println("\nYou've chosen the district of: " + newChosenDistrictString + ", " + UserManager.getCurrentUser().getState() + "\n");

                            UserManager.getCurrentUser().setDistrict(newChosenDistrictString);
                            UserManager.writeToUserInformationFile();

                            break;

                        }

                        case 3: {

                            // to update account's home state
                            System.out.println("Enter the state where your new home address is located in:");
                            System.out.println("This will determine the premises most relevant to you.\n");

                            listOfStates = UserManager.getStateList(new DataStructure.List1D<>(PremiseManager.getPremiseList()));
                            mapOfStates = UserManager.getStateMapping(listOfStates);

                            for (Integer currentKey : mapOfStates.getKeys()) {

                                System.out.println(currentKey + ". " + mapOfStates.getValueByKey(currentKey));

                            }

                            System.out.print("\n> Choose your new home state: ");

                            int newChosenState = keyboard.nextInt();
                            String newChosenStateString = mapOfStates.getValueByKey(newChosenState); // <--- the user's home state

                            System.out.println("\nYou've chosen the state of: " + newChosenStateString + "\n");

                            UserManager.getCurrentUser().setState(newChosenStateString);
                            UserManager.writeToUserInformationFile();

                            break;

                        }
                    }

                    break;

                }

                case 5: {

                    System.out.println("Exiting to the main menu...\n");

                    break;

                }

                default: {

                    System.out.println("Sorry, the choice inputted was not between 1 to 3. Please try again.");

                    break;

                }

            }

        }

        settingsMenuChoice = 0;

    }

}