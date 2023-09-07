DROP DATABASE IF EXISTS sirLibrary;

CREATE DATABASE sirLibrary;

USE sirLibrary;

CREATE TABLE administrators(
    code INT UNIQUE NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL, 
    forename VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    userpassword VARCHAR(65) NOT NULL,
    lvl_acces TINYINT DEFAULT 0, /*default 0: principal rol*/
    PRIMARY KEY (code, email)
);

CREATE TABLE libraries(
    code INT UNIQUE NOT NULL,
    alias VARCHAR(50) NOT NULL,
    direction VARCHAR(50) NOT NULL,
    PRIMARY KEY(code)
);

CREATE TABLE categories(
    code INT UNIQUE  NOT NULL,
    category VARCHAR(50) NOT NULL,
    category_resume VARCHAR(500) NOT NULL,
    PRIMARY KEY (code, category)
);

CREATE TABLE books(
    isbn INT UNIQUE NOT NULL, 
    tittle VARCHAR(50) NOT NULL,
    cost DECIMAL(6,2) NOT NULL,
    author VARCHAR(50) NOT NULL,
    category INT NOT NULL,
    PRIMARY KEY (isbn), 
    CONSTRAINT category_fk
    FOREIGN KEY (category)
    REFERENCES categories(code)
);

CREATE TABLE receptionists(
    code INT UNIQUE NOT NULL,
    email VARCHAR(50) NOT NULL, 
    forename VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    userpassword VARCHAR(65) NOT NULL,
    isActive TINYINT(2) DEFAULT 1,
    lvl_acces TINYINT DEFAULT 1, /*default 1: receptionist rol*/
    library INT NOT NULL,
    PRIMARY KEY (code),
    CONSTRAINT receptionist_lib_fk 
    FOREIGN KEY (library)
    REFERENCES libraries(code)
);

CREATE TABLE dealers(
    code INT UNIQUE NOT NULL,
    email VARCHAR(50) NOT NULL, 
    forename VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    userpassword VARCHAR(65) NOT NULL,
    isActive TINYINT(2) DEFAULT 1,
    lvl_acces TINYINT DEFAULT 2, /*default 2: dealer rol*/
    deliveries INT DEFAULT 0,
    PRIMARY KEY (code)
);

CREATE TABLE users(
    code INT UNIQUE NOT NULL,
    email VARCHAR(50) NOT NULL, 
    forename VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    userpassword VARCHAR(65) NOT NULL,
    isActive TINYINT(2) DEFAULT 1,
    lvl_acces TINYINT DEFAULT 3, /*default 3: costumer rol*/
    amount DECIMAL(6,2) DEFAULT 0,
    max_books INT DEFAULT 5,
    PRIMARY KEY (code)
);

CREATE TABLE vip_users(
    user_code INT NOT NULL,
    library_code INT NOT NULL,
    date_suscription DATETIME NOT NULL,
    CONSTRAINT user_sub_fk
    FOREIGN KEY (user_code)
    REFERENCES users(code),
    CONSTRAINT library_code_fk
    FOREIGN KEY (library_code)
    REFERENCES libraries(code)
);

CREATE TABLE loan_requests(
    code INT UNIQUE NOT NULL,
    library INT NOT NULL,
    isbn INT NOT NULL,
    request_state TINYINT NOT NULL, /*0 solicitado, 1 aprobado, 2 rechazado, 3 cancelado*/
    date_request DATETIME NOT NULL,
    PRIMARY KEY (code)
);

CREATE TABLE loan_requests_detail(
    code_request INT UNIQUE NOT NULL,
    user INT NOT NULL,
    request_receiver INT,
    type_request TINYINT NOT NULL, /*1 store pickup, 2 home service*/
    retrieved_date INT,
    assigned_dealer INT DEFAULT NULL, 
    PRIMARY KEY (code_request),
    CONSTRAINT detail_reques_fk
    FOREIGN KEY (code_request)
    REFERENCES loan_requests(code),
    CONSTRAINT requesting_user_fk
    FOREIGN KEY (user)
    REFERENCES users(code)
);

CREATE TABLE book_lists(
    code_book INT NOT NULL,
    library_code INT NOT NULL,
    units INT NOT NULL,
    CONSTRAINT book_fk
    FOREIGN KEY (code_book)
    REFERENCES books(isbn),
    CONSTRAINT library_fk
    FOREIGN KEY (library_code)
    REFERENCES libraries(code)
);

CREATE TABLE library_deliveries(
    code INT NOT NULL, 
    receptionist INT NOT NULL, 
    library INT NOT NULL, 
    deliver_state TINYINT NOT NULL, /*0 pendign, 1 en curso, 2 entregado*/
    deliver_date DATETIME NOT NULL,
    PRIMARY KEY(code),
    CONSTRAINT receiver_code_fk
    FOREIGN KEY (receptionist)
    REFERENCES receptionists(code),
    CONSTRAINT library_destiny_fk
    FOREIGN KEY (library)
    REFERENCES libraries(code)
);

CREATE TABLE library_delivery_detail(
    delivery_code INT NOT NULL,
    book_code INT NOT NULL,
    units INT NOT NULL,
    PRIMARY KEY(delivery_code),
    CONSTRAINT library_delivery_code_fk
    FOREIGN KEY (delivery_code)
    REFERENCES library_deliveries(code),
    CONSTRAINT book_delivery_fk
    FOREIGN KEY (book_code)
    REFERENCES books(isbn)
);

CREATE TABLE user_deliveries(
    code INT NOT NULL,
    assigned_dealer INT NOT NULL,
    source_libray INT NOT NULL,
    deliver_state TINYINT NOT NULL, /*0 pendign, 1 en curso, 2 entregado*/
    deliver_date DATETIME NOT NULL,
    PRIMARY KEY (code), 
    CONSTRAINT assigned_dealer_fk
    FOREIGN KEY (assigned_dealer)
    REFERENCES dealers(code),
    CONSTRAINT source_libray_fk
    FOREIGN KEY (source_libray)
    REFERENCES libraries(code)
);

CREATE TABLE user_delivery_details(
    delivery_code INT NOT NULL,
    receiver_user INT NOT NULL,
    package_book INT NOT NULL,
    PRIMARY KEY (delivery_code), 
    CONSTRAINT delivery_code_fk
    FOREIGN KEY (delivery_code)
    REFERENCES user_deliveries(code),
    CONSTRAINT user_destiny_fk
    FOREIGN KEY (receiver_user)
    REFERENCES users(code),
    CONSTRAINT book_code_fk
    FOREIGN KEY (package_book)
    REFERENCES books(isbn)
);

CREATE TABLE loans(
    code INT NOT NULL, 
    library INT NOT NULL,
    loan_book INT NOT NULL,
    loan_state TINYINT NOT NULL, /*0 pendign, 1 en activa, 2 entregado*/
    loaned_date DATETIME NOT NULL,
    PRIMARY KEY (code),
    CONSTRAINT library_loan_fk
    FOREIGN KEY (library)
    REFERENCES libraries(code),
    CONSTRAINT loan_book_fk
    FOREIGN KEY (loan_book)
    REFERENCES books(isbn)
);

CREATE TABLE loanded_books(
    user_code INT NOT NULL,
    code_book INT NOT NULL,
    CONSTRAINT user_loan_fk
    FOREIGN KEY (user_code)
    REFERENCES users(code),
    CONSTRAINT code_book_fk
    FOREIGN KEY (code_book)
    REFERENCES books(isbn)
);

CREATE TABLE loan_details(
    loan_code INT NOT NULL,
    user_code INT NOT NULL,
    loan_finisher INT,
    retrieved_date DATETIME,
    late_fee DECIMAL(6.2) NOT NULL,
    PRIMARY KEY (loan_code),
    CONSTRAINT loan_code_fk
    FOREIGN KEY (loan_code)
    REFERENCES loans(code),
    CONSTRAINT user_code_fk
    FOREIGN KEY (user_code)
    REFERENCES users(code)
);

INSERT INTO libraries(code, alias, direction) 
    VALUES  (155, "la bendicion", "4ta calle 3a 3-20"),
            (255, 'Library A', '123 Main St'),
            (355, 'Library B', '456 Elm St');
INSERT INTO categories(code, category, category_resume) 
    VALUES  (601, "accion", "libro de accion"),
            (266, 'Fiction', 'Fiction books'),
            (366, 'Non-fiction', 'Non-fiction books');
INSERT INTO books (isbn, tittle, cost, author, category)
    VALUES  (1001, 'Book 1', 19.99, 'Author 1', 601),
            (1002, 'Book 2', 14.99, 'Author 2', 266),
            (2001, 'Book 3', 24.99, 'Author 3', 366);

INSERT INTO book_lists (code_book, library_code, units)
    VALUES (1001, 155, 32),
            (1002, 155, 32),
            (2001, 155, 32);

INSERT INTO administrators(code, email, forename, username, userpassword) VALUES (155,"admin@gmail.com", "admin1", "adminProfile", "b761bec41deab1e8e6a3b9245cf4ace25ef1e262c44bb45ad8c67809c0d6baec");
INSERT INTO receptionists(code, email, forename, username, userpassword, library) VALUES (155,"recept@gmail.com", "recept1", "receptProfile", "b761bec41deab1e8e6a3b9245cf4ace25ef1e262c44bb45ad8c67809c0d6baec", 155);
INSERT INTO dealers(code, email, forename, username, userpassword) VALUES (155,"dealer@gmail.com", "dealer1", "dealerProfile", "b761bec41deab1e8e6a3b9245cf4ace25ef1e262c44bb45ad8c67809c0d6baec");
INSERT INTO users(code, email, forename, username, userpassword) VALUES (155,"byronvasquez21@gmail.com", "user1", "userProfile", "b761bec41deab1e8e6a3b9245cf4ace25ef1e262c44bb45ad8c67809c0d6baec");

