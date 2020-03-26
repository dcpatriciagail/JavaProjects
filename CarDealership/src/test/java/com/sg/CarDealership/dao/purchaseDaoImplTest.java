/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Model;
import com.sg.CarDealership.dto.Make;
import com.sg.CarDealership.dto.Purchase;
import com.sg.CarDealership.dto.User;
import com.sg.CarDealership.dto.Vehicle;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class purchaseDaoImplTest {
    @Autowired
    vehicleDao vehicleDao;

    @Autowired
    userDao userDao;
    
    @Autowired
    purchaseDao purchaseDao;
    
    @Autowired
    carModelDao modelDao;
    
    @Autowired
    makeDao makeDao;
    
    public purchaseDaoImplTest() {
    }

    /**
     * Test of addPurchase method, of class purchaseDaoImpl.
     */
    @Test
    public void testAddPurchase() {
        //Arrange

        Make make = new Make();        
        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelDao.addModel(model);
        
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
        Vehicle createdVehicle = vehicleDao.addVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = userDao.addUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("30000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName("name");
        

        //Act
        Purchase createdPurchase = purchaseDao.addPurchase(purchase);
        Purchase fetchedPurchase = purchaseDao.getPurchaseById(createdPurchase.getPurchaseId());
   
        //Assert
        assertEquals(fetchedPurchase.getVehicle().getModel().getModelName(), "Explorer"); //model
        assertEquals(fetchedPurchase.getVehicle().getModel().getMake().getMakeName(), "Ford"); //make        
        assertEquals(fetchedPurchase.getPurchaseId(), createdPurchase.getPurchaseId()); //puchaseID
        assertEquals(fetchedPurchase.getCustomerName(), "name"); 
        assertEquals(fetchedPurchase.getUser().getFirstName(), "first");        
        assertEquals(fetchedPurchase.getPurchaseType(), "credit");
        
    }

    /**
     * Test of getPurchaseById method, of class purchaseDaoImpl.
     */
    @Test
    public void testGetPurchaseById() {
        //Arrange
        Model model = new Model();
        Make make = new Make();
        
        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelDao.addModel(model);
        
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
        Vehicle createdVehicle = vehicleDao.addVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = userDao.addUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("30000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName("name");
        Purchase createdPurchase = purchaseDao.addPurchase(purchase);
        //Act

        Purchase fetchedPurchase = purchaseDao.getPurchaseById(createdPurchase.getPurchaseId());
   
        //Assert
        assertEquals(fetchedPurchase.getVehicle().getModel().getModelName(), "Explorer"); //model
        assertEquals(fetchedPurchase.getVehicle().getModel().getMake().getMakeName(), "Ford"); //make        
        assertEquals(fetchedPurchase.getPurchaseId(), createdPurchase.getPurchaseId()); //puchaseID
        assertEquals(fetchedPurchase.getCustomerName(), "name"); 
        assertEquals(fetchedPurchase.getUser().getFirstName(), "first");        
        assertEquals(fetchedPurchase.getPurchaseType(), "credit");        
    }

    /**
     * Test of updatePurchase method, of class purchaseDaoImpl.
     */
    @Test
    public void testUpdatePurchase() {
    }

    /**
     * Test of deletePurchaseById method, of class purchaseDaoImpl.
     */
    @Test
    public void testDeletePurchaseById() {
    }

    /**
     * Test of readAllPurchase method, of class purchaseDaoImpl.
     */
    @Test
    public void testReadAllPurchase() {
        //Arrange purchase1

        Make make = new Make();
        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);

        Model model = new Model();        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelDao.addModel(model);
        
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
        Vehicle createdVehicle = vehicleDao.addVehicle(vehicle);

        
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = userDao.addUser(user);
        
        
        Purchase purchase = new Purchase();
        purchase.setVehicle(createdVehicle);
        purchase.setUser(createUser);
        purchase.setPurchasePrice(new BigDecimal ("30000.00"));
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchaseType("credit");
        purchase.setCustomerName("name");
        Purchase createdPurchase = purchaseDao.addPurchase(purchase);
        
        
        //Arrange purchase2

        Make make2 = new Make();
        make2.setMakeName("Ford");
        Make createdMake2 = makeDao.addMake(make2);

        Model model2 = new Model();        
        model2.setModelName("Explorer");
        model2.setMake(createdMake2);
        Model createdModel2 = modelDao.addModel(model2);
        
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setYear(2018);
        vehicle2.setTransmission("Automatic");
        vehicle2.setMileage(1000);
        vehicle2.setColor("Blue");
        vehicle2.setInterior("Leather");
        vehicle2.setBodyType("SUV");
        vehicle2.setVin("W9D81KQ93N8Z0KS7");
        vehicle2.setSalesPrice(new BigDecimal("35000.00"));
        vehicle2.setMsrp(new BigDecimal("40000.00"));
        vehicle2.setDescription("A practical vehicle");
        vehicle2.setPicURL("http://www.sampleurl.com/samplepic");
        vehicle2.setModel(createdModel2);
        Vehicle createdVehicle2 = vehicleDao.addVehicle(vehicle2);

        
        User user2 = new User();
        user2.setEmail("user@email.com");
        user2.setFirstName("first");
        user2.setLastName("last");
        user2.setPassword("pass");
        user2.setRole("admin");
        User createUser2 = userDao.addUser(user2);
        
        
        Purchase purchase2 = new Purchase();
        purchase2.setVehicle(createdVehicle2);
        purchase2.setUser(createUser2);
        purchase2.setPurchasePrice(new BigDecimal ("30000.00"));
        purchase2.setPurchaseDate(LocalDate.now());
        purchase2.setPurchaseType("credit");
        purchase2.setCustomerName("name");
        Purchase createdPurchase2 = purchaseDao.addPurchase(purchase2);    
        
        //Act
        List<Purchase> purchaseList = purchaseDao.readAllPurchase();
        
        //Assert
         assertEquals(purchaseList.size(),2);    
         assertEquals(purchaseList.get(0).getPurchaseId(), createdPurchase.getPurchaseId());
         assertEquals(purchaseList.get(1).getPurchaseId(), createdPurchase2.getPurchaseId());
         assertEquals(purchaseList.get(0).getVehicle().getModel().getModelName(), "Explorer");
         
    }
    
}
