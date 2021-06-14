package com.jb.db;

import com.jb.utils.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    public static final String url = "jdbc:mysql://localhost:3306?createDatabaseIfNotExist=FALSE&useTimezone=TRUE&serverTimezone=UTC";
    public static final String username = "root";
    public static final String password = "123456";

    private static final String CREATE_SCHEMA = "create schema java129";
    private static final String DROP_SCHEMA = "drop schema java129";
    private static final String CREATE_TABLE_PERSON = "CREATE TABLE `java129`.`person` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NULL,\n" +
            "  `city` VARCHAR(45) NULL,\n" +
            "  `birthday` DATE NULL,\n" +
            "  `hobby` VARCHAR(45) NULL,\n" +
            "  PRIMARY KEY (`id`));\n";
    private static final String DROP_TABLE_PERSON = "DROP TABLE `java129`.`person`;";

    private static Connection connection;

    public static void createSchema() throws SQLException {
        try {
            //STEP 2 - Open Connection to DB
            connection = DriverManager.getConnection(url, username, password);

            //STEP 3 - Run your SQL instruction
            PreparedStatement statement = connection.prepareStatement(CREATE_SCHEMA);
            statement.executeUpdate();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            //STEP 5 - Close Connection to DB
            connection.close();
        }
    }

    public static void dropSchema() throws SQLException {

        try {
            //STEP 2 - Open Connection to DB
            connection = DriverManager.getConnection(url, username, password);

            //STEP 3 - Run your SQL instruction
            PreparedStatement statement = connection.prepareStatement(DROP_SCHEMA);
            statement.execute();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            //STEP 5 - Close Connection to DB
            connection.close();
        }
    }

    public static void createTablePerson() throws SQLException {
        DBUtils.runQuery(DROP_SCHEMA);
        DBUtils.runQuery(CREATE_SCHEMA);
        DBUtils.runQuery(CREATE_TABLE_PERSON);
    }

    public static void dropTablePerson() throws SQLException {
        DBUtils.runQuery(DROP_SCHEMA);
        DBUtils.runQuery(CREATE_SCHEMA);
        DBUtils.runQuery(DROP_TABLE_PERSON);
    }




}