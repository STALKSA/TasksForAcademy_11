package com.example.coffeehouse.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoffeeShopDatabaseManager {
    private final Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/coffeeshop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public CoffeeShopDatabaseManager() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Нет подключения.");
            throw new RuntimeException(e);
        }
    }

    // Get all orders
    public List<Coffee> getAllOrders() throws SQLException {
        List<Coffee> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM CoffeeShop")) {
            while (resultSet.next()) {
                Coffee coffee = new Coffee();
                coffee.setId(resultSet.getInt("id"));
                coffee.setCoffeeType(resultSet.getString("coffeeType"));
                coffee.setDessertType(resultSet.getString("dessertType"));
                coffee.setOrderTime(resultSet.getTimestamp("orderTime"));
                coffee.setWorkSchedule(resultSet.getString("workSchedule"));
                orders.add(coffee);
            }
        }
        return orders;
    }

    // Add new coffee order
    public void addCoffeeOrder(Coffee coffee) throws SQLException {
        String sql = "INSERT INTO CoffeeShop (coffeeType, dessertType, orderTime, workSchedule) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, coffee.getCoffeeType());
            statement.setString(2, coffee.getDessertType());
            statement.setTimestamp(3, coffee.getOrderTime());
            statement.setString(4, coffee.getWorkSchedule());
            statement.executeUpdate();
        }
    }

    // Add new dessert order
    public void addDessertOrder(Coffee coffee) throws SQLException {
        String sql = "INSERT INTO CoffeeShop (dessertType, orderTime, workSchedule) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, coffee.getDessertType());
            statement.setTimestamp(2, coffee.getOrderTime());
            statement.setString(3, coffee.getWorkSchedule());
            statement.executeUpdate();
        }
    }

    // Add new work schedule for next Monday
    public void addNextMondaySchedule(String workSchedule) throws SQLException {
        String sql = "INSERT INTO CoffeeShop (workSchedule) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, workSchedule);
            statement.executeUpdate();
        }
    }

    // Add new coffee type
    public void addCoffeeType(Coffee coffee) throws SQLException {
        String sql = "INSERT INTO CoffeeShop (coffeeType, workSchedule) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, coffee.getCoffeeType());
            statement.setString(2, coffee.getWorkSchedule());
            statement.executeUpdate();
        }
    }

    public void close() throws SQLException {
        this.connection.close();
    }
}