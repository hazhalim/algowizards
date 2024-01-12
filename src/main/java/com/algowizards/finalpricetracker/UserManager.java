package com.algowizards.finalpricetracker;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;

public class UserManager
{

    // Instance variables
    private static final String USER_INFORMATION_FILE_NAME = "UserInformation.csv";
    private static final String securityQuestionListFileName = "SecurityQuestionList.csv";
    private static List<User> userList = new ArrayList<>();
    private static int userListSize;

    private static Map<String, User> userMap;
    private static int userMapSize;
    private static List<String> usernameList;
    private static List<String> securityQuestionList = new ArrayList<>();
    private static DataStructure.Mapping<Integer, String> securityQuestionMap = new DataStructure.Mapping<>();

    private static User currentUser = null;

    // Constructors (none needed; all variables are static)

    // Getter and setter methods
    // Getter methods
    static String getUserInformationFileName()
    {

        return USER_INFORMATION_FILE_NAME;

    }
    static List<User> getUserList()
    {

        return userList;

    }

    static int getUserListSize()
    {

        return userListSize;

    }

    static Map<String, User> getUserMap()
    {

        return userMap;

    }

    static int getUserMapSize()
    {

        return userMapSize;

    }

    static List<String> getUsernameList()
    {

        return usernameList;

    }

    static List<String> getSecurityQuestionList()
    {

        return securityQuestionList;

    }

    static DataStructure.Mapping<Integer, String> getSecurityQuestionMap()
    {

        return securityQuestionMap;

    }

    public static User getCurrentUser()
    {

        return currentUser;

    }

    // Setter methods
    static void setUserList(List<User> newUserList)
    {

        if (newUserList != null)
        {

            userList = newUserList;

        }

    }

    static void setUserMap(Map<String, User> newUserMap)
    {

        if (newUserMap != null)
        {

            userMap = newUserMap;

        }

    }

    static void setUserListSize(int newUserListSize)
    {

        if (newUserListSize >= 0)
        {

            userListSize = newUserListSize;

        }

    }

    static void setUserMapSize(int newUserMapSize)
    {

        if (newUserMapSize >= 0)
        {

            userMapSize = newUserMapSize;

        }

    }

    static void setUsernameList(List<String> newUsernameList)
    {

        if (newUsernameList != null)
        {

            usernameList = newUsernameList;

        }

    }

    static void setSecurityQuestionList() throws IOException, CsvException
    {

        FileReader fileReader = new FileReader(securityQuestionListFileName);
        CSVReader csvReader = new CSVReaderBuilder(fileReader).build();

        List<String[]> intermediateData = csvReader.readAll();

        for (String[] row : intermediateData)
        {

            securityQuestionList.add(row[0]);

        }

    }

    static void setSecurityQuestionMap()
    {

        for (int i = 0; i < securityQuestionList.size(); i++)
        {

            securityQuestionMap.addEntry(i + 1, securityQuestionList.get(i));

        }

    }

    public static void setCurrentUser(User currentUser)
    {
        UserManager.currentUser = currentUser;

    }

    // Other methods
//    public static void writeToFile()
//    {
//
//        try
//        {
//
//            FileWriter fw = new FileWriter(USER_INFORMATION_FILE_NAME,true);
//            PrintWriter pw = new PrintWriter(fw);
//
//            for (User user : userList)
//            {
//
//                pw.println(user.toCsvString());
//
//            }
//
//            fw.close();
//            pw.close();
//
//        } catch (IOException exception) {
//
//            exception.printStackTrace();
//
//        }
//
//    }
    
    // this method is actually copy from above je, but i add the "user object" 
    //and remove the for loop WHICH WAS THE MAIN CAUSE DIA JADI DUPLICATED, SEBAB U WROTE "for each user, write it to file"
    //ha centu la lebih kurang
    public static void writeToFile(User user) {

        try {

            FileWriter fw = new FileWriter(USER_INFORMATION_FILE_NAME,true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(user.toCsvString());

            fw.close();
            pw.close();

        } catch (IOException exception) {

            exception.printStackTrace();

        }

    }



    public static void readFromUserInformationFile()
    {

        String previousCurrentUsername = (currentUser != null) ? (currentUser.getUsername()) : (null);

        userList.clear();

        try (FileReader fileReader = new FileReader(USER_INFORMATION_FILE_NAME);
             CSVReader csvReader = new CSVReader(fileReader))
        {

            List<String[]> intermediateData = csvReader.readAll();

            for (String[] row : intermediateData)
            {

                userList.add(new User(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9]));

            }

            for (User user : userList)
            {

                if (user.getUsername().equals(previousCurrentUsername))
                {

                    UserManager.setCurrentUser(user);

                    break;

                }

            }

        } catch (IOException exception) {

            exception.printStackTrace();

        } catch (CsvException exception) {

            throw new RuntimeException(exception);

        }

    }

    static String[] toArray(User user)
    {

        return new String[]
                {

                        user.getUsername(),
                        user.getPassword(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getContactInfo(),
                        user.getAddress(),
                        user.getState(),
                        user.getDistrict(),
                        user.getSecurityQuestion(),
                        user.getSecurityAnswer()

                };

    }

    static void writeToUserInformationFile() throws IOException
    {

        List<String[]> intermediateData = new ArrayList<>();

        for (User user : userList)
        {

            intermediateData.add(toArray(user));

        }

        FileManager.writeUserListIntoCSVFile(USER_INFORMATION_FILE_NAME, intermediateData);

    }

    // Method to get list of states for the user to choose their state
    static DataStructure.List1D<String> getStateList(DataStructure.List1D<Premise> listOfPremises)
    {

        Set<String> uniqueStates = new HashSet<>();

        for (Premise premise : listOfPremises.getList1D())
        {

            String state = premise.getPremiseState();

            uniqueStates.add(state);

        }

        return new DataStructure.List1D<>(new ArrayList<>(uniqueStates));

    }

    // Method to get the state mapping
    static DataStructure.Mapping<Integer, String> getStateMapping(DataStructure.List1D<String> stateList)
    {

        Map<Integer, String> stateMapping = new HashMap<>();

        for (int i = 0; i < stateList.getList1DSize(); i++)
        {

            stateMapping.put(i + 1, stateList.getList1DValue(i));

        }

        return new DataStructure.Mapping<>(stateMapping);

    }

    // After obtaining the chosen state, get its list of districts
    static DataStructure.List1D<String> getDistrictList(String chosenState, DataStructure.List1D<Premise> listOfPremises)
    {

        Set<String> uniqueDistricts = new HashSet<>();

        for (Premise premise : listOfPremises.getList1D())
        {

            String district = premise.getPremiseDistrict();

            if((premise.getPremiseState().equals(chosenState)))
            {

                uniqueDistricts.add(district);

            }

        }

        return new DataStructure.List1D<>(new ArrayList<>(uniqueDistricts));

    }

    // After obtaining the chosen state, get its mapping of districts
    static DataStructure.Mapping<Integer, String> getDistrictMapping(DataStructure.List1D<String> districtList)
    {

        Map<Integer, String> districtMapping = new HashMap<>();

        for (int i = 0; i < districtList.getList1DSize(); i++)
        {

            districtMapping.put(i + 1, districtList.getList1DValue(i));

        }

        return new DataStructure.Mapping<>(districtMapping);

    }

}