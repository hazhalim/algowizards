package com.algowizards.finalpricetracker;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DatabaseManager
{

    // Instance variables
    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/PriceWizard?allowLoadLocalInfile=true";
    private static String DATABASE_USERNAME = "root";
    private static String DATABASE_PASSWORD = "algowizards";

    // Constructors (none)

    // Getter and setter methods
    // Getter methods
    static String getDatabaseURL()
    {

        return DATABASE_URL;

    }

    static String getDatabaseUsername()
    {

        return DATABASE_USERNAME;

    }

    static String getDatabasePassword()
    {

        return DATABASE_PASSWORD;

    }

    // Setter methods
    void setDatabaseURL(String serverAddress, String serverPort, String databaseName)
    {

        if (!serverAddress.isEmpty() && !serverPort.isEmpty() && !databaseName.isEmpty())
        {

            DATABASE_URL = "jdbc:mysql://" + serverAddress + ":" + serverPort + "/" + databaseName;

        }

    }

    void setDatabaseUsername(String username)
    {

        if (!username.isEmpty())
        {

            DATABASE_USERNAME = username;

        }

    }

    void setDatabasePassword(String password)
    {

        if (!password.isEmpty())
        {

            DATABASE_PASSWORD = password;

        }

    }

    // Other methods
    static boolean isDatabaseInitialised() {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             Statement statement = connection.createStatement())
        {

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables("PriceWizard", "PriceWizard", "Product", null);

            return tables.next();

        } catch (SQLException exception) {

            System.out.println("Error! An SQL exception occurred... printing stack trace.");
            System.out.println();

            exception.printStackTrace();

            return false;

        }

    }

    static boolean isDataImported()
    {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             Statement statement = connection.createStatement())
        {

            String sqlCode = "SELECT * FROM Product";

            ResultSet products = statement.executeQuery(sqlCode);

            return products.next();

        } catch (SQLException exception) {

            System.out.println("Error! An SQL exception occurred... printing stack trace.");
            System.out.println();

            exception.printStackTrace();

            return false;

        }

    }

    static void initialiseDatabase() throws SQLException
    {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             Statement statement = connection.createStatement())
        {

            String sqlCode =
                    "CREATE TABLE `SecurityQuestion` (\n" +
                            "    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                            "    `questionText` VARCHAR(255) NOT NULL\n" +
                            ");\n" +

                            "CREATE TABLE `Product` (\n" +
                            "    `itemCode` INT NOT NULL PRIMARY KEY,\n" +
                            "    `itemName` VARCHAR(255) NOT NULL,\n" +
                            "    `itemUnit` VARCHAR(255) NOT NULL,\n" +
                            "    `itemGroup` VARCHAR(255) NOT NULL,\n" +
                            "    `itemCategory` VARCHAR(255) NOT NULL\n" +
                            ");\n" +

                            "CREATE TABLE `Premise` (\n" +
                            "    `premiseCode` INT NOT NULL PRIMARY KEY,\n" +
                            "    `premiseName` VARCHAR(255) NOT NULL,\n" +
                            "    `premiseAddress` VARCHAR(255) NOT NULL,\n" +
                            "    `premiseType` VARCHAR(255) NOT NULL,\n" +
                            "    `state` VARCHAR(255) NOT NULL,\n" +
                            "    `district` VARCHAR(255) NOT NULL\n" +
                            ");\n" +

                            "CREATE TABLE `User` (\n" +
                            "    `username` VARCHAR(255) NOT NULL,\n" +
                            "    `password` VARCHAR(255) NOT NULL,\n" +
                            "    `firstName` VARCHAR(255) NOT NULL,\n" +
                            "    `lastName` VARCHAR(255) NOT NULL,\n" +
                            "    `phoneNumber` VARCHAR(255) NOT NULL,\n" +
                            "    `address` VARCHAR(255) NOT NULL,\n" +
                            "    `state` VARCHAR(255) NOT NULL,\n" +
                            "    `district` VARCHAR(255) NOT NULL,\n" +
                            "    `securityQuestionID` INT NOT NULL,\n" +
                            "    `securityAnswer` VARCHAR(255) NOT NULL,\n" +
                            "    PRIMARY KEY (`username`),\n" +
                            "    CONSTRAINT `UserSecurityQuestionIDForeign` FOREIGN KEY (`securityQuestionID`) REFERENCES `SecurityQuestion` (`id`)\n" +
                            ");\n" +

                            "CREATE TABLE `Cart` (\n" +
                            "    `username` VARCHAR(255) NOT NULL,\n" +
                            "    `itemCode` INT NOT NULL,\n" +
                            "    `quantity` INT NOT NULL,\n" +
                            "    PRIMARY KEY (`username`),\n" +
                            "    CONSTRAINT `CartItemCodeForeign` FOREIGN KEY (`itemCode`) REFERENCES `Product` (`itemCode`),\n" +
                            "    CONSTRAINT `CartUsernameForeign` FOREIGN KEY (`username`) REFERENCES `User` (`username`)\n" +
                            ");\n" +

                            "CREATE TABLE `PriceCatcher` (\n" +
                            "    `day` DATE NOT NULL,\n" +
                            "    `itemCode` INT NOT NULL,\n" +
                            "    `premiseCode` INT NOT NULL,\n" +
                            "    `price` DOUBLE NOT NULL,\n" +
                            "    PRIMARY KEY (`day`, `itemCode`, `premiseCode`),\n" +
                            "    CONSTRAINT `PriceCatcherItemCodeForeign` FOREIGN KEY (`itemCode`) REFERENCES `Product` (`itemCode`),\n" +
                            "    CONSTRAINT `PriceCatcherPremiseCodeForeign` FOREIGN KEY (`premiseCode`) REFERENCES `Premise` (`premiseCode`)\n" +
                            ");\n" +

                            "CREATE INDEX CartItemCodeIndex ON Cart(itemCode);\n" +

                            "CREATE INDEX PriceCatcherItemCodeIndex ON PriceCatcher(itemCode);\n" +
                            "CREATE INDEX PriceCatcherPremiseCodeIndex ON PriceCatcher(premiseCode);";

            statement.executeUpdate(sqlCode);
            loadCSVFileIntoDatabaseTable(ProductManager.getLookupItemDatabaseFileName(), "Product");
            loadCSVFileIntoDatabaseTable(PremiseManager.getLookupPremiseDatabaseFileName(), "Premise");
            loadCSVFileIntoDatabaseTable(PriceCatcherManager.getPriceCatcherDatabaseFileName(), "PriceCatcher");
            loadCSVFileIntoDatabaseTable(UserManager.getSecurityQuestionDatabaseFileName(), "SecurityQuestion", true);

        } catch (SQLException exception) {

            System.out.println("Error! There was an SQL exception...");
            exception.printStackTrace();

        }

    }

    static void loadCSVFileIntoDatabaseTable(String csvFilePath, String tableName) throws SQLException
    {

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement statement = connection.createStatement())
        {

            String projectRoot = System.getProperty("user.dir");
            String finalPath = projectRoot + "/" + csvFilePath;

            String sqlCode = "LOAD DATA LOCAL INFILE '" + finalPath + "' INTO TABLE " + tableName + " FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\\n' IGNORE 1 ROWS";

            statement.executeUpdate(sqlCode);

        } catch (SQLException exception) {

            System.out.println("Error! There was an SQL exception...");
            exception.printStackTrace();

        }

    }

    static void loadCSVFileIntoDatabaseTable(String csvFilePath, String tableName, boolean isSecurityQuestion) throws SQLException
    {

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement statement = connection.createStatement())
        {

            String projectRoot = System.getProperty("user.dir");
            String finalPath = projectRoot + "/" + csvFilePath;

            String sqlCode = "LOAD DATA LOCAL INFILE '" + finalPath + "' INTO TABLE " + tableName + " FIELDS TERMINATED BY '\\n' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\\n' (questionText)";

            statement.executeUpdate(sqlCode);

        } catch (SQLException exception) {

            System.out.println("Error! There was an SQL exception...");
            exception.printStackTrace();

        }

    }

    static void loadPremiseCSVFileIntoDatabaseTable(String csvFilePath) throws SQLException
    {

        System.out.println("Loading price catchers into the database...");

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement statement = connection.createStatement())
        {

            String projectRoot = System.getProperty("user.dir");
            String finalPath = projectRoot + "/" + csvFilePath;

            for (Premise premise : PremiseManager.getPremiseList())
            {

                String sqlCode = "INSERT INTO Premise (premiseCode, premiseName, premiseAddress, premiseType, state, district) VALUES (" + premise.getPremiseCode() + ", '" + premise.getPremiseName() + "', '" + premise.getPremiseAddress() + "', '" + premise.getPremiseType() + "', '" + premise.getPremiseState() + "', '" + premise.getPremiseDistrict() + "');";

                statement.executeUpdate(sqlCode);

            }


        } catch (SQLException exception) {

            System.out.println("Error! There was an SQL exception...");
            exception.printStackTrace();

        }

    }

    static void loadPriceCatcherCSVFileIntoDatabaseTable(String csvFilePath) throws SQLException
    {

        System.out.println("Loading price catchers into the database...");

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement statement = connection.createStatement())
        {

            String projectRoot = System.getProperty("user.dir");
            String finalPath = projectRoot + "/" + csvFilePath;

            for (PriceCatcher priceCatcher : PriceCatcherManager.getPriceCatcherList())
            {

                String sqlCode = "INSERT INTO PriceCatcher (day, itemCode, premiseCode, price) VALUES ('" + priceCatcher.getPriceDate() + "', " + priceCatcher.getItemCode() + ", " + priceCatcher.getPremiseCode() + ", '" + priceCatcher.getItemPrice() + "');";

                statement.executeUpdate(sqlCode);

            }


        } catch (SQLException exception) {

            System.out.println("Error! There was an SQL exception...");
            exception.printStackTrace();

        }

    }

    static void loadSecurityQuestionCSVFileIntoDatabaseTable(String csvFilePath) throws SQLException
    {

        System.out.println("Loading price catchers into the database...");

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement statement = connection.createStatement())
        {

            String projectRoot = System.getProperty("user.dir");
            String finalPath = projectRoot + "/" + csvFilePath;

            for (String question : UserManager.getSecurityQuestionList())
            {

                String sqlCode = "INSERT INTO SecurityQuestion (questionText) VALUES ('" + question + "');";

                statement.executeUpdate(sqlCode);

            }


        } catch (SQLException exception) {

            System.out.println("Error! There was an SQL exception...");
            exception.printStackTrace();

        }

    }

}