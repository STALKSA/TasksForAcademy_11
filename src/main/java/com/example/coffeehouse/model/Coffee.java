package com.example.coffeehouse.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Coffee {
    private int id;
    private String coffeeType;
    private String dessertType;
    private Timestamp orderTime;
    private String workSchedule;
}