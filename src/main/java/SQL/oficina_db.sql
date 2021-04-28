DROP DATABASE IF EXISTS oficina;
CREATE DATABASE oficina;
USE oficina;

DROP TABLE IF EXISTS users;

CREATE table users (
	user_id INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(45) NOT NULL,
    user_login VARCHAR(35) NOT NULL,
    user_password VARCHAR(45) NOT NULL,
    user_email VARCHAR(45) NOT NULL,
    user_role ENUM('admin', 'manager', 'employee') NOT NULL,
    PRIMARY KEY(user_id)
);

DROP TABLE IF EXISTS orders;

SET @@time_zone = 'SYSTEM';

CREATE table orders (
    order_id INT NOT NULL AUTO_INCREMENT,
    order_customer_name VARCHAR(45) NOT NULL,
    order_customer_phone VARCHAR(45) NOT NULL,
    order_total DECIMAL(10,2) NOT NULL,
    order_status ENUM('opened', 'closed'),
    user_id INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    closed_at DATETIME,
    PRIMARY KEY(order_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

DROP TABLE IF EXISTS products;

CREATE table products(
	product_id INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(45) NOT NULL,
    product_description VARCHAR(250) NOT NULL,
    product_price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(product_id)
);

DROP TABLE IF EXISTS order_product;

CREATE table order_product(
	order_id INT NOT NULL,
    product_id INT NOT NULL,
    FOREIGN KEY(order_id) REFERENCES orders(order_id),
    FOREIGN KEY(product_id) REFERENCES products(product_id)
);

DROP TABLE IF EXISTS pack;

CREATE table pack(
	pack_id INT NOT NULL AUTO_INCREMENT,
    pack_title varchar(45) NOT NULL,
    pack_total DECIMAL(10,2) NOT NULL,
    pack_description varchar(250) NOT NULL,
    primary key (pack_id)
);

DROP table if exists pack_products;

CREATE table pack_products(
	pack_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    descount INT NOT NULL,
	foreign key (pack_id) references pack(pack_id),
    foreign key (product_id) references products(product_id),
    CONSTRAINT pk_pack_product primary key (pack_id, product_id)
);

INSERT INTO users (user_name, user_login, user_password, user_email, user_role) VALUES ("admin","admin","admin123","admin@email.com","admin");
INSERT INTO users (user_name, user_login, user_password, user_email, user_role) VALUES ("gerente","gerente","gerente123","gerente@email.com","manager");
INSERT INTO users (user_name, user_login, user_password, user_email, user_role) VALUES ("user","user","user123","user@email.com","employee");
INSERT INTO orders (order_customer_name, order_customer_phone, user_id, order_total, order_status, created_at) VALUES ("Cleyton", "(11)99999-9999",3, 300, "opened", '2021-03-25 10:10:10');
INSERT INTO products (product_name, product_description, product_price) VALUES ("Produto 1", "DescriÃ§Ã£o do produto 1", 200.99);
INSERT INTO products (product_name, product_description, product_price) VALUES ("Produto 2", "DescriÃ§Ã£o do produto 2", 100.99);
INSERT INTO products (product_name, product_description, product_price) VALUES ("Produto 3", "DescriÃ§Ã£o do produto 3", 50.99);
INSERT INTO pack (pack_title, pack_total, pack_description) VALUES ("Default Pack", 150.50, "This pack is auto generated");
INSERT INTO pack (pack_title, pack_total, pack_description) VALUES ("Default Pack 2", 150.50, "This pack is auto generated");
INSERT INTO pack_products (pack_id, product_id, quantity, descount) VALUES (1, 2, 2, 10);
INSERT INTO pack_products (pack_id, product_id, quantity, descount) VALUES (1, 3, 2, 10);


SELECT * FROM orders;
SELECT * FROM products;
select * from users;
/*select pack.pack_id AS 'ID', pack_products.product_id AS 'ID PRODUTO', products.product_name as 'NOME'
	from pack INNER JOIN ( pack_products inner join products on pack_products.product_id = products.product_id) on pack.pack_id = pack_products.pack_id;*/
