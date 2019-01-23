package com.tristanchanson.petshop.inventory;

import java.util.List;

public class ProductResultSetPrinter {

    public void displayResults(List<Product> products) {

        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %30s %20s %5s %5s", "PRODUCT ID", "NAME", "COUNT", "COST", "PRICE");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");

        products.forEach(product -> {
                    System.out.format("%10s %30s %20s %5s %5s", product.getId(), product.getName(), product.getCount(), product.getCost(), product.getPrice());
                    System.out.println();
                });

    }
}
