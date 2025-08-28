create database ecommerce;
use ecommerce;


create table Product(
	productId INT primary key auto_increment,
    name varchar(100),
    description TEXT,
    price DECIMAL(10,2),
    categoryId INT,
    stockQuantity INT,
    FOREIGN KEY (categoryId) REFERENCES Category(categoryId)
    );
CREATE TABLE Category ( 
 categoryId INT PRIMARY KEY AUTO_INCREMENT, 
 name VARCHAR(100) 
);

CREATE TABLE User ( 
 userId INT PRIMARY KEY AUTO_INCREMENT, 
 username VARCHAR(50) UNIQUE, 
 password VARCHAR(255), 
 role ENUM('CUSTOMER', 'ADMIN'), 
 email VARCHAR(100) 
); 
CREATE TABLE pOrder ( 
 orderId INT PRIMARY KEY AUTO_INCREMENT, 
 userId INT, 
 totalAmount DECIMAL(10, 2), 
 orderDate DATE, 
 status ENUM('PENDING', 'SHIPPED', 'DELIVERED', 'CANCELLED'), 
 FOREIGN KEY (userId) REFERENCES User(userId) 
); 

CREATE TABLE Payment ( 
 paymentId INT PRIMARY KEY AUTO_INCREMENT, 
 orderId INT, 
 amount DECIMAL(10, 2), 
 paymentStatus ENUM('PENDING', 'COMPLETED', 'FAILED'), 
 paymentDate DATE, 
 FOREIGN KEY (orderId) REFERENCES pOrder(orderId) 
 ); 
 CREATE TABLE Cart ( 
 cartId INT PRIMARY KEY AUTO_INCREMENT, 
 userId INT, 
 productId INT, 
 quantity INT, 
 FOREIGN KEY (userId) REFERENCES User(userId), 
 FOREIGN KEY (productId) REFERENCES Product(productId) 
); 
 show tables;
 
 INSERT INTO Category (name) VALUES 
('Electronics'),
('Books'),
('Clothing'),
('Home & Kitchen');

INSERT INTO Product (name, description, price, categoryId, stockQuantity) VALUES 
('Smartphone', 'Latest model with 128GB storage', 699.99, 1, 50),
('Bluetooth Speaker', 'Portable speaker with deep bass', 49.99, 1, 100),
('Novel - The Alchemist', 'Inspirational fiction by Paulo Coelho', 12.99, 2, 200),
('T-Shirt', 'Cotton t-shirt with graphic print', 19.99, 3, 150),
('Blender', '500W blender with 3-speed settings', 34.99, 4, 80);

INSERT INTO User (username, password, role, email) VALUES 
('john_doe', 'hashed_password_1', 'CUSTOMER', 'john@example.com'),
('admin_user', 'hashed_password_2', 'ADMIN', 'admin@example.com'),
('jane_smith', 'hashed_password_3', 'CUSTOMER', 'jane@example.com');

INSERT INTO pOrder (userId, totalAmount, orderDate, status) VALUES 
(1, 749.98, '2025-08-20', 'SHIPPED'),
(3, 32.98, '2025-08-22', 'PENDING');

INSERT INTO Payment (orderId, amount, paymentStatus, paymentDate) VALUES 
(1, 749.98, 'COMPLETED', '2025-08-20'),
(2, 32.98, 'PENDING', '2025-08-22');

INSERT INTO Cart (userId, productId, quantity) VALUES 
(1, 1, 1),
(1, 2, 1),
(3, 3, 2),
(3, 4, 1);

SELECT * FROM cart;
SELECT * FROM category;
SELECT * FROM payment;
SELECT * FROM porder;
SELECT * FROM product;
SELECT * FROM user;

 
 