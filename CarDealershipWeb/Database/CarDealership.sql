DROP DATABASE IF EXISTS CarDealership;
CREATE DATABASE CarDealership;

USE CarDealership;

CREATE TABLE Makes (
	makeID INT PRIMARY KEY AUTO_INCREMENT,
    makeName VARCHAR(20) NOT NULL
);

CREATE TABLE CarModels (
	modelID INT PRIMARY KEY AUTO_INCREMENT,
    modelName VARCHAR(20) NOT NULL,
    makeID INT NOT NULL,
    FOREIGN KEY (makeID) REFERENCES Makes (makeID)
);

CREATE TABLE Users (
	userID INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    `password` VARCHAR(16) NOT NULL,
    `role` VARCHAR(10) NOT NULL
);

CREATE TABLE Vehicles (
	vehicleID INT PRIMARY KEY AUTO_INCREMENT,
    modelID INT NOT NULL,
    `year` INT(4) NOT NULL,
    transmission VARCHAR(20) NOT NULL,
    mileage INT NOT NULL,
    color VARCHAR(20) NOT NULL,
    interior VARCHAR(20) NOT NULL,
    bodyStyle VARCHAR(20) NOT NULL,
    VIN CHAR(16) NOT NULL,
    salesPrice DECIMAL(8,2) NOT NULL,
    msrp DECIMAL(8,2) NOT NULL,
    `description` TEXT NOT NULL,
    used BOOL DEFAULT FALSE,
    featured BOOL DEFAULT FALSE,
    special BOOL DEFAULT FALSE,
    inStock BOOL DEFAULT TRUE,
    picURL VARCHAR(250), 
    FOREIGN KEY (modelID) REFERENCES CarModels (modelID)
);

CREATE TABLE Purchases (
	purchaseID INT PRIMARY KEY AUTO_INCREMENT,
    vehicleID INT NOT NULL,
    userID INT NOT NULL,
    purchasePrice DECIMAL(8,2) NOT NULL,
    purchaseDate DATE NOT NULL,
    purchaseType VARCHAR(20) NOT NULL,
    customerName VARCHAR(50) NOT NULL,
    FOREIGN KEY (vehicleID) REFERENCES Vehicles (vehicleID),
    FOREIGN KEY (userID) REFERENCES Users (userID)
);

CREATE TABLE Contacts (
	contactID INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(13) NOT NULL,
    message TEXT
);


-- INSERT INTO Purchases (vehicleID, userID, purchasePrice, purchaseDate, purchaseType, customerName) VALUES (?,?,?,?,?,?)