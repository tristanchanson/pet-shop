package com.tristanchanson.petshop.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductResultSetPrinter {

    public void displayResults(ResultSet resultSet) {

        try {
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf("%10s %30s %20s %5s %5s", "PRODUCT ID", "NAME", "COUNT", "COST", "PRICE");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------");

            while (resultSet.next()) {
                // Retrieve by column name
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int count = resultSet.getInt("count");
                double cost = resultSet.getDouble("cost");
                double price = resultSet.getDouble("price");

                System.out.format("%10s %30s %20s %5s %5s",
                        id, name, count, cost, price);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
