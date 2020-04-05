USE CarDealership;
INSERT INTO Makes (makeName)
VALUES ("Ford"),
       ("Chevrolet"),
       ("Dodge"),
       ("Toyota");
INSERT INTO Models (makeID,modelName)
VALUES (1,"F-150"),
       (1,"Mustang"),
       (1,"Explorer"),
       (2,"Silverado"),
       (2,"Malibu"),
       (3,"Ram 1500"),
       (3,"Charger"),
       (4,"Highlander"),
       (4,"Corolla");
	

INSERT INTO Users (firstName,lastName,email,`password`,`role`)
VALUES ("Bob","Smith","bob.smith@dealership.com","password1","Sales");

INSERT INTO Vehicles (modelId,year,transmission,mileage,color,interior,bodyStyle,VIN,salesPrice,msrp,description,
                      featured, picURL)
VALUES (1,2015,"Automatic",50000,"Silver","Cloth","Truck","4DCUDFSEMV84ZYN9",34999.99,36000.00,"Built Ford Tough", 1, "car1.png"),
       (2,2017,"Automatic",15000,"Electric Blue","Cloth","Coupe","3U6UAAUC76E78WXK",32999.99,35000.00,"Badass looking muscle car", 1,"car2.png"),
       (5,2016,"Automatic",22417,"Red","Leather","Sedan","7NLU7CX3PADM9MJ3",17999.99,20000.00,"A comfortable 4 door vehicle that gets great mileage in the city with a luxurious interior", 1, "car3.png"),
       (6,2016,"Automatic",22417,"Red","Leather","Sedan","7NLU7CX3PADM9MJ3",17999.99,20000.00,"A comfortable 4 door vehicle that gets great mileage in the city with a luxurious interior", 1, "car4.jpg"),
       (3,2015,"Automatic",50000,"Silver","Cloth","Truck","4DCUDFSEMV84ZYN9",34999.99,36000.00,"Built Ford Tough", 1, "car1.png"),
       (8,2017,"Automatic",15000,"Electric Blue","Cloth","Coupe","3U6UAAUC76E78WXK",32999.99,35000.00,"Badass looking muscle car", 1,"car2.png"),
       (9,2016,"Automatic",22417,"Red","Leather","Sedan","7NLU7CX3PADM9MJ3",17999.99,20000.00,"A comfortable 4 door vehicle that gets great mileage in the city with a luxurious interior", 1, "car3.png"),
       (1,2016,"Automatic",22417,"Red","Leather","Sedan","7NLU7CX3PADM9MJ3",17999.99,20000.00,"A comfortable 4 door vehicle that gets great mileage in the city with a luxurious interior", 1, "car4.jpg");
       
       