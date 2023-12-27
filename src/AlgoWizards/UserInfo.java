/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlgoWizards;

/**
 * @author tmhta_
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

public class UserInfo {

    private static final String fileName = "UserInformation.csv";

    private final String username;
    private String password;
    private final String firstName;
    private final String lastName;
    private final String contactInfo;
    private final String address;

    public UserInfo(String username, String password, String firstName, String lastName, String contactInfo, String address) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getAddress() {
        return address;
    }
    
    public void setPassword (String password) {
        this.password = password;
    }

    private String toCsvString() {
        return String.join(",", username, password, firstName, lastName, contactInfo, address);
    }

    public static void writeToFile(List<UserInfo> userInfoList) {
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter writer = new PrintWriter(fw);

            for (UserInfo userInfo : userInfoList) {
                writer.println(userInfo.toCsvString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<UserInfo> readFromFile () {
        List<UserInfo> userInfoList = new ArrayList<>();
        try {
            FileReader fr = new FileReader (fileName);
            BufferedReader br = new BufferedReader (fr);
            
            String line = br.readLine();
            while (line != null){
                String[] userInfo = line.split(",");
                userInfoList.add(new UserInfo (userInfo[0],userInfo[1],userInfo[2],userInfo[3],userInfo[4],userInfo[5]));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        
        return userInfoList;
    }

}
