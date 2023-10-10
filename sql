CREATE TABLE Customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    total_purchase_amount DOUBLE DEFAULT 0,
    tier VARCHAR(255) DEFAULT 'Silver',
    loyalty_points INT DEFAULT 0
);

INSERT INTO Customer (name, total_purchase_amount, tier, loyalty_points) VALUES
    ('Rohit', 7500, 'Golden', 300),
    ('Steve Smith', 3000, 'Silver', 100);

CREATE TABLE Purchase (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE NOT NULL,
    purchase_date DATETIME NOT NULL,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
);

INSERT INTO Purchase (amount, purchase_date, customer_id) VALUES
    (1500, '2023-10-10 10:00:00', 1),
    (5000, '2023-10-10 11:30:00', 2);
