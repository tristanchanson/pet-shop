package com.tristanchanson.petshop.inventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFileReader {

    private static final String PRODUCTS_FILE = "/products.csv";

    public List<Product> read() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ProductFileReader.class.getResourceAsStream(PRODUCTS_FILE)))) {
            return reader.lines()
                    .map(s -> s.split(","))
                    .map(this::mapToProduct)
                    .collect(Collectors.toList());
        } catch (IOException e) {
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
