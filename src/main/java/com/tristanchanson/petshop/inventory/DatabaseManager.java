package com.tristanchanson.petshop.inventory;

import org.h2.tools.SimpleResultSet;

import java.sql.*;

public class DatabaseManager {

    private static DatabaseManager databaseManager;

    private static final String INSERT_TEMPLATE = "INSERT INTO Products(name, count, cost, price) VALUES ('%s', %s, %s, %s)";
    private static final String FIND_ALL_QUERY = "SELECT id, name, count, cost, price FROM PRODUCTS";

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:test";

    //  Database credentials
    private static final String USER = "sa";
    private static final String PASS = "";

    private static Connection connection;

    private DatabaseManager() {
        createDatabase();
    }

    private static void createDatabase() {
        try {
            System.out.println("Creating Database");
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE PRODUCTS " +
                    "(id INTEGER not NULL auto_increment, " +
                    " name VARCHAR(255), " +
                    " count NUMBER, " +
                    " cost NUMBER, " +
                    " price NUMBER, " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseManager openDatabase() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public void insertProduct(Product product) {
        System.out.println("Inserting Product Record");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(INSERT_TEMPLATE, product.getName(), product.getCount(), product.getCost(), product.getPrice()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet findAllProducts() {
        System.out.println("Querying all products");
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(FIND_ALL_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new SimpleResultSet();
    }
}
