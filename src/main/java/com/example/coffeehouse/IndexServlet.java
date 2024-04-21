package com.example.coffeehouse;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.coffeehouse.model.Coffee;
import com.example.coffeehouse.model.CoffeeShopDatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "IndexServlet", value = "/index")
public class IndexServlet extends HttpServlet {

    private CoffeeShopDatabaseManager managerData;

    public void init() {
        managerData = new CoffeeShopDatabaseManager();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        try {
            // Получение всех заказов
            List<Coffee> orders = managerData.getAllOrders();

            // Добавление нового заказа кофе
            addNewCoffeeOrder();

            // Добавление нового заказа десерта
            addNewDessertOrder();

            // Добавление нового графика работы на ближайший понедельник
            addNextMondaySchedule();

            // Добавление нового вида кофе
            addNewCoffeeType();

            request.setAttribute("orders", orders);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException | ParseException e) {
            throw new ServletException(e);
        }
    }

    public void destroy() {
        try {
            managerData.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Метод для добавления нового заказа кофе
    private void addNewCoffeeOrder() throws SQLException, ParseException {
        Coffee newCoffeeOrder = new Coffee();
        newCoffeeOrder.setCoffeeType("Americano");
        newCoffeeOrder.setDessertType("Apple Pie");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = sdf.parse("2024-04-21 10:30:00");
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        newCoffeeOrder.setOrderTime(timestamp);
        newCoffeeOrder.setWorkSchedule("Monday: 8:00 - 20:00; Tuesday: 8:00 - 20:00; Wednesday: 8:00 - 20:00; Thursday: 8:00 - 20:00; Friday: 8:00 - 22:00; Saturday: 9:00 - 22:00; Sunday: 10:00 - 20:00");
        managerData.addCoffeeOrder(newCoffeeOrder);
    }

    // Метод для добавления нового заказа десерта
    private void addNewDessertOrder() throws SQLException, ParseException {
        Coffee newDessertOrder = new Coffee();
        newDessertOrder.setDessertType("Cinnamon Roll");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = sdf.parse("2024-04-21 13:15:00");
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        newDessertOrder.setOrderTime(timestamp);
        newDessertOrder.setWorkSchedule("Monday: 8:00 - 20:00; Tuesday: 8:00 - 20:00; Wednesday: 8:00 - 20:00; Thursday: 8:00 - 20:00; Friday: 8:00 - 22:00; Saturday: 9:00 - 22:00; Sunday: 10:00 - 20:00");
        managerData.addDessertOrder(newDessertOrder);
    }

    // Метод для добавления нового графика работы на ближайший понедельник
    private void addNextMondaySchedule() throws SQLException {
        managerData.addNextMondaySchedule("Monday: 8:00 - 20:00; Tuesday: 8:00 - 20:00; Wednesday: 8:00 - 20:00; Thursday: 8:00 - 20:00; Friday: 8:00 - 22:00; Saturday: 9:00 - 22:00; Sunday: 10:00 - 20:00");
    }

    // Метод для добавления нового вида кофе
    private void addNewCoffeeType() throws SQLException {
        Coffee newCoffeeType = new Coffee();
        newCoffeeType.setCoffeeType("Mocha");
        newCoffeeType.setWorkSchedule("Monday: 8:00 - 20:00; Tuesday: 8:00 - 20:00; Wednesday: 8:00 - 20:00; Thursday: 8:00 - 20:00; Friday: 8:00 - 22:00; Saturday: 9:00 - 22:00; Sunday: 10:00 - 20:00");
        managerData.addCoffeeType(newCoffeeType);
    }
}