package com.tristanchanson.petshop.inventory;

import com.tristanchanson.petshop.inventory.data.DatabaseManager;
import com.tristanchanson.petshop.inventory.data.ProductDao;

import java.util.List;

public class InventoryLoader {

    private final ProductFileReader productFileReader = new ProductFileReader();
    private final DatabaseManager databaseManager = new DatabaseManager();
    private final ProductResultSetPrinter productResultSetPrinter = new ProductResultSetPrinter();
    private final ProductDao productDao = new ProductDao(databaseManager.getDataSource());

    public static void main(String[] args) {
        new InventoryLoader().run();
    }

    private void run() {

        // Read Products from file
        List<Product> products = productFileReader.read();

        // Loop through products and insert into database
        for (Product product : products) {
            productDao.insert(product);
        }

        // Query all products from database and display results
        List<Product> productList = productDao.findAll();
        productResultSetPrinter.displayResults(productList);

    }
}
