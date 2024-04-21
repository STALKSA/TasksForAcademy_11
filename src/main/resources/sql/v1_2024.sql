CREATE TABLE CoffeeShop (
                            Id SERIAL PRIMARY KEY,
                            coffeeType VARCHAR(50) NOT NULL,
                            dessertType VARCHAR(50) NOT NULL,
                            orderTime TIMESTAMP NOT NULL,
                            workSchedule VARCHAR(100) NOT NULL
);



ALTER TABLE CoffeeShop
    ALTER COLUMN workSchedule TYPE VARCHAR(200);


-- Изменение длины столбца
ALTER TABLE CoffeeShop
    ALTER COLUMN workSchedule TYPE VARCHAR(200);

-- Добавление информации о новом заказе кофе
INSERT INTO CoffeeShop (coffeeType, dessertType, orderTime, workSchedule) VALUES
                                                                              ('Espresso', 'Chocolate Cake', '2024-04-21 09:30:00', 'Monday: 8:00 - 20:00; Tuesday: 8:00 - 20:00; Wednesday: 8:00 - 20:00; Thursday: 8:00 - 20:00; Friday: 8:00 - 22:00; Saturday: 9:00 - 22:00; Sunday: 10:00 - 20:00'),
                                                                              ('Cappuccino', 'Cheesecake', '2024-04-21 11:45:00', 'Monday: 8:00 - 20:00; Tuesday: 8:00 - 20:00; Wednesday: 8:00 - 20:00; Thursday: 8:00 - 20:00; Friday: 8:00 - 22:00; Saturday: 9:00 - 22:00; Sunday: 10:00 - 20:00'),
                                                                              ('Latte', '', '2024-04-21 14:00:00', 'Monday: 8:00 - 20:00; Tuesday: 8:00 - 20:00; Wednesday: 8:00 - 20:00; Thursday: 8:00 - 20:00; Friday: 8:00 - 22:00; Saturday: 9:00 - 22:00; Sunday: 10:00 - 20:00');
