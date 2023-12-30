package com.algowizards.finalpricetracker;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;

public class UserManager
{

    // Instance variables
    private static final String userInformationFileName = "UserInformation.csv";
    private static final String securityQuestionListFileName = "SecurityQuestionList.csv";
    private static List<User> userList = new ArrayList<>();
    private static int userListSize;

    private static Map<String, User> userMap;
    private static int userMapSize;
    private static List<String> usernameList;
    private static List<String> securityQuestionList = new ArrayList<>();
    private static DataStructure.Mapping<Integer, String> securityQuestionMap = new DataStructure.Mapping<>();

    // Constructors (none needed; all variables are static)

    // Getter and setter methods
    // Getter methods
    static String getUserInformationFileName()
    {

        return userInformationFileName;

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

    // Other methods
    public static void writeToFile()
    {

        try
        {

            FileWriter fw = new FileWriter(userInformationFileName);
            PrintWriter pw = new PrintWriter(fw);

            for (User user : userList)
            {

                pw.println(user.toCsvString());

            }

            fw.close();
            pw.close();

        } catch (IOException exception) {

            exception.printStackTrace();

        }

    }

    public static void readFromFile()
    {

        try {

            FileReader fr = new FileReader(userInformationFileName);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null)
            {

                if (!line.isEmpty())
                {

                    String[] userInfo = line.split(",");

                    if (userInfo.length >= 10)
                    {

                        userList.add(new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4], userInfo[5], userInfo[6], userInfo[7], userInfo[8], userInfo[9]));

                    } else {

                        System.out.println("Invalid data in the file: " + line);

                    }

                }

                line = br.readLine();

            }

            fr.close();
            br.close();

        } catch (IOException exception) {

            exception.printStackTrace();

        }

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