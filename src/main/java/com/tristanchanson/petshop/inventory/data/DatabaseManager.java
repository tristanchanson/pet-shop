package com.tristanchanson.petshop.inventory.data;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.stream.Collectors;

public class DatabaseManager {

    private final DataSource dataSource;

    // JDBC driver name and database URL
    private static final String DB_URL = "jdbc:h2:mem:test";

    //  Database credentials
    private static final String USER = "sa";
    private static final String PASS = "";

    public DatabaseManager() {
        // Initialize H2 Database
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(DB_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASS);

        this.dataSource = dataSource;
        loadDbSchema();
    }

    private void loadDbSchema() {

        try {
            // Load schema.sql file to build tables in DB
            Path path = Paths.get(DatabaseManager.class.getClassLoader().getResource("schema.sql").toURI());
            String sql = Files.lines(path).collect(Collectors.joining("\n"));

            Statement statement = dataSource.getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException | URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
