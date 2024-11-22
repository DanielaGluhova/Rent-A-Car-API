-- Клиенти
CREATE TABLE IF NOT EXISTS clients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(256),
    address VARCHAR(256),
    phone VARCHAR(256),
    age INT,
    has_incidents INT DEFAULT 0, -- 0 за няма, 1 за има участие в инциденти
    is_deleted INT DEFAULT 0 -- 0 за неизтрит, 1 за изтрит запис
);

-- Автомобили
CREATE TABLE IF NOT EXISTS cars (
    id INT PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(256),
    car_year INT CHECK (car_year >= 1900 AND car_year <= EXTRACT(YEAR FROM CURRENT_DATE)),
    daily_rate DECIMAL,
    location VARCHAR(256) CHECK (location IN ('Пловдив', 'София', 'Варна', 'Бургас')),
    status ENUM('available', 'rented', 'maintenance') DEFAULT 'available',
    is_deleted INT DEFAULT 0 -- 0 за неизтрит, 1 за изтрит запис
);

-- Оферти
CREATE TABLE IF NOT EXISTS offers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    client_id INT,
    car_id INT,
    rental_details_id INT,
    status ENUM('created', 'accepted', 'rejected') DEFAULT 'created',
    is_deleted INT DEFAULT 0 -- 0 за неизтрит, 1 за изтрит запис
);

-- Детайли за наем
CREATE TABLE IF NOT EXISTS rental_details (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rental_days INT,
    weekend_days INT DEFAULT 0
);



DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS rental_details;
DROP TABLE IF EXISTS offers;
