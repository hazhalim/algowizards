package com.algowizards.finalpricetracker;

/**
 * @author tmhta_
 */

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {

    private static List<User> listOfUsers;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String contactInfo;
    private String address;
    private String state;
    private String district;
    private String securityQuestion;
    private String securityAnswer;
    private List<Product> shoppingCartList;
    private String cartPath;

    private static final String CART_DIRECTORY = "/carts";

    // Constructors
    public User() {

        throw new IllegalArgumentException("No arguments passed into constructor");

    }

    public User(String username, String password, String firstName, String lastName, String contactInfo, String address, String state, String district, String securityQuestion, String securityAnswer) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
        this.address = address;
        this.state = state;
        this.district = district;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.shoppingCartList = new ArrayList<>();
//        this.cartPath = CART_DIRECTORY + this.username + "_cart.csv";

//        File cartFile = new File(cartPath);

//        if (!cartFile.exists()) {
//
//            try {
//
//                cartFile.createNewFile();
//
//            } catch (IOException exception) {
//
//                throw new RuntimeException(exception);
//
//            }
//
//        }

    }

    // Getter and setter methods
    // Getter methods
    String getUsername() {

        return this.username;

    }

    String getPassword() {

        return this.password;

    }

    String getFirstName() {

        return this.firstName;

    }

    String getLastName() {

        return this.lastName;

    }

    String getContactInfo() {

        return this.contactInfo;

    }

    String getAddress() {

        return this.address;

    }

    String getState() {

        return this.state;

    }

    String getDistrict() {

        return this.district;

    }

    String getSecurityQuestion() {

        return this.securityQuestion;

    }

    String getSecurityAnswer() {

        return this.securityAnswer;

    }

    List<Product> getShoppingCartList() {

        return this.shoppingCartList;

    }

    public String getCartPath() {

        return this.cartPath;

    }

    // Setter methods
    void setUsername(String username) {

        if (!username.isEmpty()) {

            this.username = username;

        } else {

            throw new IllegalArgumentException("Username cannot be set to blank");

        }

    }

    void setPassword(String password) {

        if (!password.isEmpty()) {

            this.password = password;

        } else {

            throw new IllegalArgumentException("Password cannot be set to blank");

        }

    }

    void setFirstName(String firstName) {

        if (!firstName.isEmpty()) {

            this.firstName = firstName;

        } else {

            throw new IllegalArgumentException("First name cannot be set to blank");

        }

    }

    void setLastName(String lastName) {

        if (!lastName.isEmpty()) {

            this.lastName = lastName;

        } else {

            throw new IllegalArgumentException("Last name cannot be set to blank");

        }

    }

    void setContactInfo(String contactInfo) {

        if (!contactInfo.isEmpty()) {

            this.contactInfo = contactInfo;

        } else {

            throw new IllegalArgumentException("Contact information cannot be set to blank");

        }

    }

    void setAddress(String address) {

        if (!address.isEmpty()) {

            this.address = address;

        } else {

            throw new IllegalArgumentException("Address cannot be set to blank");

        }

    }

    void setState(String state) {

        if (!state.isEmpty()) {

            this.state = state;

        } else {

            throw new IllegalArgumentException("State cannot be set to blank");

        }

    }

    void setDistrict(String district) {

        if (!district.isEmpty()) {

            this.district = district;

        } else {

            throw new IllegalArgumentException("District cannot be set to blank");

        }

    }

    void setSecurityQuestion(String securityQuestion) {

        if (!securityQuestion.isEmpty()) {

            this.securityQuestion = securityQuestion;

        } else {

            throw new IllegalArgumentException("Security question cannot be set to blank");

        }

    }

    void setSecurityAnswer(String securityAnswer) {

        if (!securityAnswer.isEmpty()) {

            this.securityAnswer = securityAnswer;

        } else {

            throw new IllegalArgumentException("Security answer cannot be set to blank");

        }

    }

    void setShoppingCartList(List<Product> shoppingCartList) {

        this.shoppingCartList = shoppingCartList;

    }

    public void setCartPath(String cartPath) {

        this.cartPath = cartPath;

    }

    // Other methods
    String toCsvString() {

        return String.join(",", username, password, firstName, lastName, contactInfo, address, state, district, securityQuestion, securityAnswer);

    }

//    void loadShoppingCart() throws IOException, CsvException
//    {
//
//        DataStructure.List2D<String> cartData = new DataStructure.List2D<>(FileManager.readCSVFileInto2DList(cartPath));
//
//        ProductManager.generateListOfCartProducts(cartData);
//
//    }

    void writeToShoppingCart(Product product)
    {



    }

}