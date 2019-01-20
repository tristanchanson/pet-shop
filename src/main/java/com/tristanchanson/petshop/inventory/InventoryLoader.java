package com.tristanchanson.petshop.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InventoryLoader {

    private final ProductFileReader productFileReader = new ProductFileReader();
    private final DatabaseManager databaseManager = DatabaseManager.openDatabase();
    private final ProductResultSetPrinter productResultSetPrinter = new ProductResultSetPrinter();

    public static void main(String[] args) {
        new InventoryLoader().run();
    }

    private void run() {

        // Read Products from file
        List<Product> products = productFileReader.read();

        // Loop through products and insert into database
        for (Product product : products) {
            databaseManager.insertProduct(product);
        }

        // Query all products from database and display results
        ResultSet resultSet = databaseManager.findAllProducts();
        productResultSetPrinter.displayResults(resultSet);

        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
