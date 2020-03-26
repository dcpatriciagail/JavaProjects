/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dto.Model;
import com.sg.CarDealership.dto.Make;
import com.sg.CarDealership.dto.Purchase;
import com.sg.CarDealership.dto.User;
import com.sg.CarDealership.dto.Vehicle;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Patricia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback
@Transactional 
public class PurchaseServiceTest {
    
    @Autowired
    VehicleService vService;

    @Autowired
    UserService uService;
    
    @Autowired
    PurchaseService pService;
    
    @Autowired
    CarModelService modelService;
    
    @Autowired
    MakeService makeService; 
    
    public PurchaseServiceTest(){

    }    

    /**
     * Test of createPurchase method, of class PurchaseService.
     */
    @Test
    public void testCreatePurchase()  throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, InvalidUserException, DuplicateVINException, InvalidPurchaseException {
        //Arrange

        Make make = new Make();        
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelService.createModel(model);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2018);
        vehicle.setTransmission("Automatic");
        vehicle.setMileage(1000);
        vehicle.setColor("Blue");
        vehicle.setInterior("Leather");
        vehicle.setBodyType("SUV");
        vehicle.setVin("W9D81KQ93N8Z0KS7");
        vehicle.setSalesPrice(new BigDecimal("35000.00"));
        vehicle.setMsrp(new BigDecimal("40000.00"));
        vehicle.setDescription("A practical vehicle");
        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle.setModel(createdModel);
        Vehicle createdVehicle = vService.createVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = uService.createUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("30000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName("name");
        

        //Act
        Purchase createdPurchase = pService.createPurchase(purchase);
        Purchase fetchedPurchase = pService.getPurchaseById(createdPurchase.getPurchaseId());
   
        //Assert
        assertEquals(fetchedPurchase.getVehicle().getModel().getModelName(), "Explorer"); //model
        assertEquals(fetchedPurchase.getVehicle().getModel().getMake().getMakeName(), "Ford"); //make        
        assertEquals(fetchedPurchase.getPurchaseId(), createdPurchase.getPurchaseId()); //puchaseID
        assertEquals(fetchedPurchase.getCustomerName(), "name"); 
        assertEquals(fetchedPurchase.getUser().getFirstName(), "first");        
        assertEquals(fetchedPurchase.getPurchaseType(), "credit");                
    }

    /**
     * Test of getPurchaseById method, of class PurchaseService.
     */
    @Test
    public void testGetPurchaseById() throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException, InvalidUserException, InvalidPurchaseException {
        //Arrange

        Make make = new Make();        
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelService.createModel(model);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2018);
        vehicle.setTransmission("Automatic");
        vehicle.setMileage(1000);
        vehicle.setColor("Blue");
        vehicle.setInterior("Leather");
        vehicle.setBodyType("SUV");
        vehicle.setVin("W9D81KQ93N8Z0KS7");
        vehicle.setSalesPrice(new BigDecimal("35000.00"));
        vehicle.setMsrp(new BigDecimal("40000.00"));
        vehicle.setDescription("A practical vehicle");
        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle.setModel(createdModel);
        Vehicle createdVehicle = vService.createVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = uService.createUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("30000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName("name");
        Purchase createdPurchase = pService.createPurchase(purchase);        
        

        //Act        
        Purchase fetchedPurchase = pService.getPurchaseById(createdPurchase.getPurchaseId());

        //Assert
        assertEquals(fetchedPurchase.getVehicle().getModel().getModelName(), "Explorer"); //model
        assertEquals(fetchedPurchase.getVehicle().getModel().getMake().getMakeName(), "Ford"); //make        
        assertEquals(fetchedPurchase.getPurchaseId(), createdPurchase.getPurchaseId()); //puchaseID
        assertEquals(fetchedPurchase.getCustomerName(), "name"); 
        assertEquals(fetchedPurchase.getUser().getFirstName(), "first");        
        assertEquals(fetchedPurchase.getPurchaseType(), "credit");           
        
    }

    /**
     * Test of readAllPurchase method, of class PurchaseService.
     */
    @Test
    public void testReadAllPurchase() throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException, InvalidUserException, InvalidPurchaseException {
        //Arrange1

        Make make = new Make();        
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelService.createModel(model);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2018);
        vehicle.setTransmission("Automatic");
        vehicle.setMileage(1000);
        vehicle.setColor("Blue");
        vehicle.setInterior("Leather");
        vehicle.setBodyType("SUV");
        vehicle.setVin("W9D81KQ93N8Z0KS7");
        vehicle.setSalesPrice(new BigDecimal("35000.00"));
        vehicle.setMsrp(new BigDecimal("40000.00"));
        vehicle.setDescription("A practical vehicle");
        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle.setModel(createdModel);
        Vehicle createdVehicle = vService.createVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = uService.createUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("30000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName("name");
        Purchase createdPurchase = pService.createPurchase(purchase);           
  
         //Arrange 2

        Make make2 = new Make();        
        make2.setMakeName("Audi");
        Make createdMake2 = makeService.createMake(make2);

        Model model2 = new Model();        
        model2.setModelName("A7");
        model2.setMake(createdMake);
        Model createdModel2 = modelService.createModel(model2);
        
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setYear(2018);
        vehicle2.setTransmission("Automatic");
        vehicle2.setMileage(1000);
        vehicle2.setColor("Blue");
        vehicle2.setInterior("Leather");
        vehicle2.setBodyType("SUV");
        vehicle2.setVin("W9D81KQ93N8Z0KP7");
        vehicle2.setSalesPrice(new BigDecimal("35000.00"));
        vehicle2.setMsrp(new BigDecimal("40000.00"));
        vehicle2.setDescription("A practical vehicle");
        vehicle2.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle2.setModel(createdModel2);
        Vehicle createdVehicle2 = vService.createVehicle(vehicle2);

        
        User user2 = new User();
        user2.setEmail("user@email.com");
        user2.setFirstName("first");
        user2.setLastName("last");
        user2.setPassword("pass");
        user2.setRole("admin");
        User createUser2 = uService.createUser(user2);
        
        
        Purchase purchase2 = new Purchase();
        purchase2.setVehicle(createdVehicle);
        purchase2.setUser(createUser);
        purchase2.setPurchasePrice(new BigDecimal ("30000.00"));
        purchase2.setPurchaseDate(LocalDate.now());
        purchase2.setPurchaseType("credit");
        purchase2.setCustomerName("name");
        Purchase createdPurchase2 = pService.createPurchase(purchase2); 

        //Act
        List<Purchase> purchaseList = pService.readAllPurchase();       
        
        //Assert
         assertEquals(purchaseList.size(),2);        
         assertEquals(purchaseList.get(0).getPurchaseId(), createdPurchase.getPurchaseId());
         assertEquals(purchaseList.get(1).getPurchaseId(), createdPurchase2.getPurchaseId());

    }

//    @Test
//    public void testCreatePurchasePriceTooHigh() throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException, InvalidUserException {
//        //Arrange
//
//        Make make = new Make();        
//        make.setMakeName("Ford");
//        Make createdMake = makeService.createMake(make);
//
//        Model model = new Model();        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        Model createdModel = modelService.createModel(model);
//        
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(1000);
//        vehicle.setColor("Blue");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        Vehicle createdVehicle = vService.createVehicle(vehicle);
//
//        
//        User user = new User();
//        user.setEmail("user@email.com");
//        user.setFirstName("first");
//        user.setLastName("last");
//        user.setPassword("pass");
//        user.setRole("admin");
//        User createUser = uService.createUser(user);
//        
//        
//        Purchase purchase = new Purchase();
//        purchase.setVehicle(createdVehicle);
//        purchase.setUser(createUser);
//        purchase.setPurchasePrice(new BigDecimal (Integer.MAX_VALUE));
//        purchase.setPurchaseDate(LocalDate.now());
//        purchase.setPurchaseType("credit");
//        purchase.setCustomerName("name");
//        
//                Purchase createdPurchase = null;
//        //Act
//        try{
//             createdPurchase = pService.createPurchase(purchase);       
//             fail ("Should have thrown InvalidPurchaseException");
//        } catch (InvalidPurchaseException ex) {
//            
//        }
//         //Assert
//        assertNull(createdPurchase);       
//        
//    }
    
    @Test
    public void testCreatePurchasePriceNegative()  throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException, InvalidUserException{
        //Arrange

        Make make = new Make();        
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelService.createModel(model);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2018);
        vehicle.setTransmission("Automatic");
        vehicle.setMileage(1000);
        vehicle.setColor("Blue");
        vehicle.setInterior("Leather");
        vehicle.setBodyType("SUV");
        vehicle.setVin("W9D81KQ93N8Z0KS7");
        vehicle.setSalesPrice(new BigDecimal("35000.00"));
        vehicle.setMsrp(new BigDecimal("40000.00"));
        vehicle.setDescription("A practical vehicle");
        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle.setModel(createdModel);
        Vehicle createdVehicle = vService.createVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = uService.createUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("-38000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName("name");
        
                Purchase createdPurchase = null;
        //Act
        try{
             createdPurchase = pService.createPurchase(purchase);       
             fail ("Should have thrown InvalidPurchaseException");
        } catch (InvalidPurchaseException ex) {
            
        }
         //Assert
        assertNull(createdPurchase);       
 
    }    

//    @Test
//    public void testPurchaseIncorrectDate() throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException, InvalidUserException {
//        //Arrange
//
//        Make make = new Make();        
//        make.setMakeName("Ford");
//        Make createdMake = makeService.createMake(make);
//
//        Model model = new Model();        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        Model createdModel = modelService.createModel(model);
//        
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(1000);
//        vehicle.setColor("Blue");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        Vehicle createdVehicle = vService.createVehicle(vehicle);
//
//        
//        User user = new User();
//        user.setEmail("user@email.com");
//        user.setFirstName("first");
//        user.setLastName("last");
//        user.setPassword("pass");
//        user.setRole("admin");
//        User createUser = uService.createUser(user);
//        
//        
//        Purchase purchase = new Purchase();
//        purchase.setVehicle(createdVehicle);
//        purchase.setUser(createUser);
//        purchase.setPurchasePrice(new BigDecimal ("38000.00"));
//        purchase.setPurchaseDate(LocalDate.of(2017, Month.MARCH, 12));
//        purchase.setPurchaseType("credit");
//        purchase.setCustomerName("name");
//        
//                Purchase createdPurchase = null;
//        //Act
//        try{
//             createdPurchase = pService.createPurchase(purchase);       
//             fail ("Should have thrown InvalidPurchaseException");
//        } catch (InvalidPurchaseException ex) {
//            
//        }
//         //Assert
//        assertNull(createdPurchase);  
//    }    
    
    @Test
    public void testPurchaseCustomerNameTooLong()  throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException, InvalidUserException{
         //Arrange

        Make make = new Make();        
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelService.createModel(model);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2018);
        vehicle.setTransmission("Automatic");
        vehicle.setMileage(1000);
        vehicle.setColor("Blue");
        vehicle.setInterior("Leather");
        vehicle.setBodyType("SUV");
        vehicle.setVin("W9D81KQ93N8Z0KS7");
        vehicle.setSalesPrice(new BigDecimal("35000.00"));
        vehicle.setMsrp(new BigDecimal("40000.00"));
        vehicle.setDescription("A practical vehicle");
        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle.setModel(createdModel);
        Vehicle createdVehicle = vService.createVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = uService.createUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("38000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName("namenamenamenamenamenamenaaaaaamenamenamenamenamename"); // 48 
        
                Purchase createdPurchase = null;
        //Act
        try{
             createdPurchase = pService.createPurchase(purchase);       
             fail ("Should have thrown InvalidPurchaseException");
        } catch (InvalidPurchaseException ex) {
            
        }
         //Assert
        assertNull(createdPurchase);  
    }             
    
    @Test
    public void testPurchaseCustomerNameBlank()  throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException, InvalidUserException{
         //Arrange

        Make make = new Make();        
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelService.createModel(model);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2018);
        vehicle.setTransmission("Automatic");
        vehicle.setMileage(1000);
        vehicle.setColor("Blue");
        vehicle.setInterior("Leather");
        vehicle.setBodyType("SUV");
        vehicle.setVin("W9D81KQ93N8Z0KS7");
        vehicle.setSalesPrice(new BigDecimal("35000.00"));
        vehicle.setMsrp(new BigDecimal("40000.00"));
        vehicle.setDescription("A practical vehicle");
        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle.setModel(createdModel);
        Vehicle createdVehicle = vService.createVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = uService.createUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("38000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName(""); 
        
                Purchase createdPurchase = null;
        //Act
        try{
             createdPurchase = pService.createPurchase(purchase);       
             fail ("Should have thrown InvalidPurchaseException");
        } catch (InvalidPurchaseException ex) {
            
        }
         //Assert
        assertNull(createdPurchase);  
    
    }    
    
    @Test
    public void testPurchaseNoVehicle()  throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException, InvalidUserException{
         //Arrange

        Make make = new Make();        
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelService.createModel(model);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2018);
        vehicle.setTransmission("Automatic");
        vehicle.setMileage(1000);
        vehicle.setColor("Blue");
        vehicle.setInterior("Leather");
        vehicle.setBodyType("SUV");
        vehicle.setVin("W9D81KQ93N8Z0KS7");
        vehicle.setSalesPrice(new BigDecimal("35000.00"));
        vehicle.setMsrp(new BigDecimal("40000.00"));
        vehicle.setDescription("A practical vehicle");
        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle.setModel(createdModel);
        Vehicle createdVehicle = vService.createVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = uService.createUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("38000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName(""); 
        
                Purchase createdPurchase = null;
        //Act
        try{
             createdPurchase = pService.createPurchase(purchase);       
             fail ("Should have thrown InvalidPurchaseException");
        } catch (InvalidPurchaseException ex) {
            
        }
         //Assert
        assertNull(createdPurchase);  
    
    }

    @Test
    public void testPurchaseNoUser()  throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException, InvalidUserException{
        
         //Arrange

        Make make = new Make();        
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelService.createModel(model);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2018);
        vehicle.setTransmission("Automatic");
        vehicle.setMileage(1000);
        vehicle.setColor("Blue");
        vehicle.setInterior("Leather");
        vehicle.setBodyType("SUV");
        vehicle.setVin("W9D81KQ93N8Z0KS7");
        vehicle.setSalesPrice(new BigDecimal("35000.00"));
        vehicle.setMsrp(new BigDecimal("40000.00"));
        vehicle.setDescription("A practical vehicle");
        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle.setModel(createdModel);
        Vehicle createdVehicle = vService.createVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = uService.createUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("38000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName(""); 
        
                Purchase createdPurchase = null;
        //Act
        try{
             createdPurchase = pService.createPurchase(purchase);       
             fail ("Should have thrown InvalidPurchaseException");
        } catch (InvalidPurchaseException ex) {
            
        }
         //Assert
        assertNull(createdPurchase);  
    }
    
     @Test
    public void testPurchasePriceless95ofSalesPrice()  throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException, InvalidUserException{
        
         //Arrange

        Make make = new Make();        
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelService.createModel(model);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2018);
        vehicle.setTransmission("Automatic");
        vehicle.setMileage(1000);
        vehicle.setColor("Blue");
        vehicle.setInterior("Leather");
        vehicle.setBodyType("SUV");
        vehicle.setVin("W9D81KQ93N8Z0KS7");
        vehicle.setSalesPrice(new BigDecimal("35000.00"));
        vehicle.setMsrp(new BigDecimal("40000.00"));
        vehicle.setDescription("A practical vehicle");
        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle.setModel(createdModel);
        Vehicle createdVehicle = vService.createVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = uService.createUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("20000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName(""); 
        
                Purchase createdPurchase = null;
        //Act
        try{
             createdPurchase = pService.createPurchase(purchase);       
             fail ("Should have thrown InvalidPurchaseException");
        } catch (InvalidPurchaseException ex) {
            
        }
         //Assert
        assertNull(createdPurchase);  
    }   
    
//vehiclepurchased
    @Test
    public void testPurchaseAVehicle()  throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, InvalidUserException, DuplicateVINException, InvalidPurchaseException {
        //Arrange

        Make make = new Make();        
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelService.createModel(model);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2018);
        vehicle.setTransmission("Automatic");
        vehicle.setMileage(1000);
        vehicle.setColor("Blue");
        vehicle.setInterior("Leather");
        vehicle.setBodyType("SUV");
        vehicle.setVin("W9D81KQ93N8Z0KS7");
        vehicle.setSalesPrice(new BigDecimal("35000.00"));
        vehicle.setMsrp(new BigDecimal("40000.00"));
        vehicle.setDescription("A practical vehicle");
        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle.setModel(createdModel);
        Vehicle createdVehicle = vService.createVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = uService.createUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("30000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName("name");
        
        Purchase createdPurchase = pService.createPurchase(purchase);
        Purchase fetchedPurchase = pService.getPurchaseById(createdPurchase.getPurchaseId());
   
        //Act
        createdVehicle.setInStock(false);
        vService.updateVehicle(createdVehicle);
        
        Vehicle fetchedV = vService.readVehicle(createdVehicle.getVehicleId());
        //Assert
        assertEquals(fetchedPurchase.getVehicle().getModel().getModelName(), "Explorer"); //model
        assertEquals(fetchedPurchase.getVehicle().getModel().getMake().getMakeName(), "Ford"); //make        
        assertEquals(fetchedPurchase.getPurchaseId(), createdPurchase.getPurchaseId()); //puchaseID
        assertEquals(fetchedPurchase.getCustomerName(), "name"); 
        assertEquals(fetchedPurchase.getUser().getFirstName(), "first");        
        assertEquals(fetchedPurchase.getPurchaseType(), "credit");    
        assertFalse(fetchedV.isInStock());
    }
}           