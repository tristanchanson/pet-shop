package com.tristanchanson.petshop.inventory;

import com.tristanchanson.petshop.inventory.data.DatabaseManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFileReader {

    private static final String PRODUCTS_FILE = "products.csv";

    public List<Product> read() {
        try {
            Path path = Paths.get(DatabaseManager.class.getClassLoader().getResource(PRODUCTS_FILE).toURI());
            return Files.lines(path)
                    .map(s -> s.split(","))
                    .map(this::mapToProduct)
                    .collect(Collectors.toList());
        } catch (
                IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private Product mapToProduct(String[] columns) {
        String name = columns[0];
        Integer count = Integer.valueOf(columns[1]);
        Double cost = Double.valueOf(columns[2]);
        Double price = Double.valueOf(columns[3]);
        return new Product(name, count, cost, price);
    }
}
