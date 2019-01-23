package com.tristanchanson.petshop.inventory.data;

import com.tristanchanson.petshop.inventory.Product;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final String INSERT_TEMPLATE = "INSERT INTO Products(name, count, cost, price) VALUES ('%s', %s, %s, %s)";
    private static final String FIND_ALL_QUERY = "SELECT id, name, count, cost, price FROM PRODUCTS";

    private final DataSource dataSource;

    public ProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Build Insert Statement and Execute on Database
    public void insert(Product product) {
        try {
            Statement statement = dataSource.getConnection().createStatement();
            statement.executeUpdate(String.format(INSERT_TEMPLATE, product.getName(), product.getCount(), product.getCost(), product.getPrice()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Run Select Query and Map to Objects
    public List<Product> findAll() {
        ResultSet resultSet = null;
        try {
            Statement statement = dataSource.getConnection().createStatement();
            resultSet = statement.executeQuery(FIND_ALL_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapProducts(resultSet);
    }

    private List<Product> mapProducts(ResultSet resultSet) {
        List<Product> products = new ArrayList<>();
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    // Retrieve by column name
                    String name = resultSet.getString("name");
                    int id = resultSet.getInt("id");
                    int count = resultSet.getInt("count");
                    double cost = resultSet.getDouble("cost");
                    double price = resultSet.getDouble("price");
                    products.add(new Product(id, name, count, cost, price));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }
}
