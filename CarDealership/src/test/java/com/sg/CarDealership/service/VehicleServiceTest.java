///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sg.CarDealership.service;
//
//import com.sg.CarDealership.dao.carModelDao;
//import com.sg.CarDealership.dao.makeDao;
//import com.sg.CarDealership.dao.vehicleDao;
//import com.sg.CarDealership.dto.Model;
//import com.sg.CarDealership.dto.Make;
//import com.sg.CarDealership.dto.Vehicle;
//import java.math.BigDecimal;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Patricia
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//@Rollback
//@Transactional 
//public class VehicleServiceTest {
//    
//    @Autowired
//    VehicleService vservice;
//    
//    @Autowired
//    carModelDao cmDao;
//    
//    @Autowired   
//    makeDao makeDao;
//    
//    public VehicleServiceTest() {
//    }
//    
//
//    /**
//     * Test of createVehicle method, of class VehicleService.
//     */
//    @Test
//    public void testCreateVehicle() throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException {
//        
//        Make make = new Make();        
//        make.setMakeName("Ford");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
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
//
//        //Act
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);        
//        Vehicle fetchedVehicle = vservice.readVehicle(createdVehicle.getVehicleId());
//        
//        //Assert
//        assertEquals(fetchedVehicle.getYear(),(int) 2018);
//        assertEquals(fetchedVehicle.getTransmission(), "Automatic");
//        assertEquals(fetchedVehicle.getMileage(), (int) 1000);
//        assertEquals(fetchedVehicle.getColor(), "Blue");
//        assertEquals(fetchedVehicle.getInterior(),"Leather");
//        assertEquals(fetchedVehicle.getBodyType(),"SUV");
//        assertEquals(fetchedVehicle.getVin(), "W9D81KQ93N8Z0KS7");
//        assertEquals(fetchedVehicle.getSalesPrice(), new BigDecimal("35000.00"));
//        assertEquals(fetchedVehicle.getMsrp(), new BigDecimal("40000.00"));
//        assertEquals(fetchedVehicle.getDescription(), "A practical vehicle");
//        assertEquals(fetchedVehicle.getPicURL(), "http://www.sampleurl.com/samplepic");
//        assertEquals(fetchedVehicle.getModel().getModelName(), "Explorer");
//        assertEquals(fetchedVehicle.getModel().getMake().getMakeName(), "Ford");        
//        
//    }
//
//    /**
//     * Test of readVehicle method, of class VehicleService.
//     */
//    @Test
//    public void testReadVehicle() throws InvalidMakeException, InvalidModelException, DuplicateMakeException, InvalidVehicleException, DuplicateVINException {
//        //Arrange
//        Make make = new Make();        
//        make.setMakeName("Ford");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
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
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);        
//
//
//        //Act        
//        Vehicle fetchedVehicle = vservice.readVehicle(createdVehicle.getVehicleId());
//        
//        //Assert
//        assertEquals(fetchedVehicle.getYear(),(int) 2018);
//        assertEquals(fetchedVehicle.getTransmission(), "Automatic");
//        assertEquals(fetchedVehicle.getMileage(), (int) 1000);
//        assertEquals(fetchedVehicle.getColor(), "Blue");
//        assertEquals(fetchedVehicle.getInterior(),"Leather");
//        assertEquals(fetchedVehicle.getBodyType(),"SUV");
//        assertEquals(fetchedVehicle.getVin(), "W9D81KQ93N8Z0KS7");
//        assertEquals(fetchedVehicle.getSalesPrice(), new BigDecimal("35000.00"));
//        assertEquals(fetchedVehicle.getMsrp(), new BigDecimal("40000.00"));
//        assertEquals(fetchedVehicle.getDescription(), "A practical vehicle");
//        assertEquals(fetchedVehicle.getPicURL(), "http://www.sampleurl.com/samplepic");
//        assertEquals(fetchedVehicle.getModel().getModelName(), "Explorer");
//        assertEquals(fetchedVehicle.getModel().getMake().getMakeName(), "Ford");   
//
//    }
//
//    /**
//     * Test of readAllVehicles method, of class VehicleService.
//     */
//    @Test
//    public void testReadAllVehicles() throws InvalidModelException, InvalidMakeException, DuplicateMakeException, InvalidVehicleException, DuplicateVINException {
//    //Arrange V1
//        Make make = new Make();        
//        make.setMakeName("Ford");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
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
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);        
//
//        //Arrange V2
//        Make make2 = new Make();        
//        make2.setMakeName("Honda");
//        Make createdMake2 = makeDao.addMake(make2);    
//        
//        Model model2 = new Model();        
//        model2.setModelName("Civic");
//        model2.setMake(createdMake2);
//        Model createdModel2 = cmDao.addModel(model2);        
//
//        Vehicle vehicle2 = new Vehicle();
//        vehicle2.setYear(2018);
//        vehicle2.setTransmission("Automatic");
//        vehicle2.setMileage(1000);
//        vehicle2.setColor("Green");
//        vehicle2.setInterior("Leather");
//        vehicle2.setBodyType("SUV");
//        vehicle2.setVin("W9D81KQ93N8Z0PDF");
//        vehicle2.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle2.setMsrp(new BigDecimal("40000.00"));
//        vehicle2.setDescription("A practical vehicle");
//        vehicle2.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle2.setModel(createdModel2);
//        Vehicle createdVehicle2 = vservice.createVehicle(vehicle2);        
//
//        //Act
//        List<Vehicle> vehicles = vservice.readAllVehicles();
//        
//        //Assert
//        assertEquals(createdVehicle.getVehicleId(),vehicles.get(0).getVehicleId());
//        assertEquals(createdVehicle2.getVehicleId(),vehicles.get(1).getVehicleId());
//        assertEquals(createdVehicle.getVin(),vehicles.get(0).getVin());
//        assertEquals(createdVehicle2.getVin(),vehicles.get(1).getVin());            
//    }
//
//    /**
//     * Test of updateVehicle method, of class VehicleService.
//     */
//    @Test
//    public void testUpdateVehicleMileageAndSold() throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Lexus");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("UX");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(1000);
//        vehicle.setColor("White");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);         
//        
//        //Act
//        createdVehicle.setMileage(2000);
//        createdVehicle.setSalesPrice(new BigDecimal ("40000.00"));
//        createdVehicle.setInStock(false);        
//        
//        
//        vservice.updateVehicle(createdVehicle);
//        
//        //Assert
//        Vehicle fetchedVehicle = vservice.readVehicle(createdVehicle.getVehicleId());
//        
//        assertEquals(fetchedVehicle.getMileage(), 2000);
//        assertFalse(fetchedVehicle.isInStock());
//        
//    }
//
//    @Test
//    public void testCreateVehicleInvalidYear() throws InvalidMakeException, DuplicateVINException, InvalidModelException, DuplicateMakeException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Lexus");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("UX");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(1010);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(1000);
//        vehicle.setColor("White");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        
//        //Act
//        Vehicle createdVehicle = null;
//        try{
//             createdVehicle = vservice.createVehicle(vehicle);
//             fail("Should have thrown InvalidVehicleException");
//        } catch (InvalidVehicleException ex) {
//            
//        }
//        
//        //Assert
//        assertNull(createdVehicle);
//        
//        
//    }
//
//    @Test
//    public void testCreateVehicleInvalidTransmissionTooLong() throws InvalidMakeException, DuplicateVINException, InvalidModelException, DuplicateMakeException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Lexus");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("UX");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("AutomaticAutomaticAutomatic");
//        vehicle.setMileage(1000);
//        vehicle.setColor("White");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        
//        //Act
//        Vehicle createdVehicle = null;
//        try{
//             createdVehicle = vservice.createVehicle(vehicle);
//             fail("Should have thrown InvalidVehicleException");
//        } catch (InvalidVehicleException ex) {
//            
//        }
//        
//        //Assert
//        assertNull(createdVehicle);        
//    }    
//    
//    
//    @Test
//    public void testCreateVehicleInvalidMileage() throws InvalidMakeException, DuplicateVINException, InvalidModelException, DuplicateMakeException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Lexus");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("UX");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(Integer.MAX_VALUE );
//        vehicle.setColor("White");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        
//        //Act
//        Vehicle createdVehicle = null;
//        try{
//             createdVehicle = vservice.createVehicle(vehicle);
//             fail("Should have thrown InvalidVehicleException");
//        } catch (InvalidVehicleException ex) {
//            
//        }
//        
//        //Assert
//        assertNull(createdVehicle);
//    }
//
//    @Test
//    public void testCreateVehicleInvalidSalesPrice() throws InvalidModelException, InvalidMakeException, DuplicateMakeException, DuplicateVINException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Lexus");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("UX");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(1000);
//        vehicle.setColor("White");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("48000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        
//        //Act
//        Vehicle createdVehicle = null;
//        try{
//             createdVehicle = vservice.createVehicle(vehicle);
//             fail("Should have thrown InvalidVehicleException");
//        } catch (InvalidVehicleException ex) {
//            
//        }
//        
//        //Assert
//        assertNull(createdVehicle);        
//    }
//    
//    @Test
//    public void testCreateVehicleInvalidMSRP() throws DuplicateVINException, InvalidModelException, InvalidMakeException, DuplicateMakeException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Lexus");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("UX");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(1000);
//        vehicle.setColor("White");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("48000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        
//        //Act
//        Vehicle createdVehicle = null;
//        try{
//             createdVehicle = vservice.createVehicle(vehicle);
//             fail("Should have thrown InvalidVehicleException");
//        } catch (InvalidVehicleException ex) {
//            
//        }
//        
//        //Assert
//        assertNull(createdVehicle);        
//    }
//
//    @Test
//    public void testCreateVehicleDuplicateVIN() throws InvalidMakeException, DuplicateVINException, InvalidModelException, InvalidVehicleException, DuplicateMakeException {
//    //Arrange V1
//        Make make = new Make();        
//        make.setMakeName("Ford");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
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
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);        
//
//        //Arrange V2
//        Make make2 = new Make();        
//        make2.setMakeName("Honda");
//        Make createdMake2 = makeDao.addMake(make2);    
//        
//        Model model2 = new Model();        
//        model2.setModelName("Civic");
//        model2.setMake(createdMake2);
//        Model createdModel2 = cmDao.addModel(model2);        
//
//        Vehicle vehicle2 = new Vehicle();
//        vehicle2.setYear(2018);
//        vehicle2.setTransmission("Automatic");
//        vehicle2.setMileage(1000);
//        vehicle2.setColor("Green");
//        vehicle2.setInterior("Leather");
//        vehicle2.setBodyType("SUV");
//        vehicle2.setVin("W9D81KQ93N8Z0KS7");
//        vehicle2.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle2.setMsrp(new BigDecimal("40000.00"));
//        vehicle2.setDescription("A practical vehicle");
//        vehicle2.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle2.setModel(createdModel2);
//                
//        Vehicle createdVehicle2 = null;
//        //Act
//        try{
//            vservice.createVehicle(vehicle2);  
//            fail("Should have thrown DuplicateVINException.");
//        } catch (DuplicateVINException ex) {
//            
//        }
//        //Assert
//        assertNull(createdVehicle2);
//    }
//
//    
//    @Test
//    public void testCreateVehicleNegativeInt() throws InvalidMakeException, DuplicateVINException, InvalidModelException, DuplicateMakeException, InvalidVehicleException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Ford");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(-1);
//        vehicle.setColor("Blue");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        
//            Vehicle createdVehicle = null;    
//        //Act
//        try {
//             vservice.createVehicle(vehicle);        
//             fail("Should have thrown InvalidVehicleException");
//        } catch (InvalidVehicleException ex ) {
//            
//        }
//        //Assert
//        assertNull(createdVehicle);
//    }
//            
//            
//    @Test
//    public void testCreateVehicleInvalidBodyStyle() throws InvalidMakeException, DuplicateVINException, InvalidModelException, DuplicateMakeException, InvalidVehicleException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Ford");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(1000);
//        vehicle.setColor("Blue");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUVSUVSUVSUVSUVSUVSUVSUV"); //24
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        
//                Vehicle createdVehicle = null;
//        //Act
//        try {
//             vservice.createVehicle(vehicle);       
//             fail("Should have thrown InvalidVehicleException");
//        } catch (InvalidVehicleException ex ) {
//        }
//        //Assert
//        assertNull(createdVehicle);        
//        
//    }
//
//    
//    @Test
//    public void testUpdateYEAR() throws InvalidMakeException, DuplicateMakeException, InvalidModelException, InvalidVehicleException, DuplicateVINException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Lexus");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("UX");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(1000);
//        vehicle.setColor("White");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);         
//        
//        //Act
//        createdVehicle.setYear(1999);
//        try {
//              vservice.updateVehicle(createdVehicle);
//              fail("Should have thrown InvalidVehicleException");
//        } catch (InvalidVehicleException ex) {
//            
//        }
//        //Assert
//        Vehicle fetchedVehicle = vservice.readVehicle(createdVehicle.getVehicleId());
//        
//        assertEquals(fetchedVehicle.getYear(), 2018);      
//    }    
//    
//    @Test
//    public void testCreateVehicleInvalidColor() throws InvalidMakeException, DuplicateVINException, InvalidModelException, DuplicateMakeException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Lexus");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("UX");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(1000);
//        vehicle.setColor("WhiteWhiteWhiteWhiteWhite"); //25
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        
//        //Act
//        Vehicle createdVehicle = null;
//        try{
//             createdVehicle = vservice.createVehicle(vehicle);
//             fail("Should have thrown InvalidVehicleException");
//        } catch (InvalidVehicleException ex) {
//            
//        }
//        
//        //Assert
//        assertNull(createdVehicle);
//    }    
//    
//     @Test
//    public void testCreateVehicleInvalidInterior() throws InvalidMakeException, DuplicateVINException, InvalidModelException, DuplicateMakeException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Lexus");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("UX");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic");
//        vehicle.setMileage(1000);
//        vehicle.setColor("White"); 
//        vehicle.setInterior("LeatherLeatherLeatherLeatherLeatherLeather"); //42
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        
//        //Act
//        Vehicle createdVehicle = null;
//        try{
//             createdVehicle = vservice.createVehicle(vehicle);
//             fail("Should have thrown InvalidVehicleException");
//        } catch (InvalidVehicleException ex) {
//            
//        }
//        
//        //Assert
//        assertNull(createdVehicle);
//    }  
//    
//    
//     @Test
//    public void testCreateVehicleNull() throws InvalidMakeException, DuplicateVINException, InvalidModelException, DuplicateMakeException {
//    //Arrange 
//        Make make = new Make();        
//        make.setMakeName("Lexus");
//        Make createdMake = makeDao.addMake(make);    
//        
//        Model model = new Model();        
//        model.setModelName("UX");
//        model.setMake(createdMake);
//        Model createdModel = cmDao.addModel(model);        
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.setYear(2018);
//        vehicle.setTransmission("");
//        vehicle.setMileage(1000);
//        vehicle.setColor(""); 
//        vehicle.setInterior(""); 
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("40000.00"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//        
//        //Act
//        Vehicle createdVehicle = null;
//        try{
//             createdVehicle = vservice.createVehicle(vehicle);
//             fail("Should have thrown InvalidVehicleException");
//        } catch (InvalidVehicleException ex) {
//            
//        }
//        
//        //Assert
//        assertNull(createdVehicle);
//    }     
//    @Test
//    public void testCreateVehicleMsrpNegative() throws InvalidMakeException, DuplicateMakeException, InvalidModelException, DuplicateVINException{
//        //Arrange
//        Vehicle vehicle = new Vehicle();
//        Model model = new Model();
//        Make make = new Make();
//        
//        make.setMakeName("Ford");
//        
//        Make createdMake = makeDao.addMake(make);
//        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        
//        Model createdModel = cmDao.addModel(model);
//        
//        vehicle.setYear(2018);
//        vehicle.setTransmission("Automatic"); //21 char
//        vehicle.setMileage(1000);
//        vehicle.setColor("Blue");
//        vehicle.setInterior("Leather");
//        vehicle.setBodyType("SUV");
//        vehicle.setVin("W9D81KQ93N8Z0KS7");
//        vehicle.setSalesPrice(new BigDecimal("35000.00"));
//        vehicle.setMsrp(new BigDecimal("-0.01"));
//        vehicle.setDescription("A practical vehicle");
//        vehicle.setPicURL("http://www.sampleurl.com/samplepic");
//        vehicle.setModel(createdModel);
//
//        //Act
//        Vehicle createdVehicle = null; 
//        try {
//            createdVehicle = vservice.createVehicle(vehicle);
//            fail("Should have thrown InvalidVehicleException");
//        } catch(InvalidVehicleException ex){}
//        
//        assertNull(createdVehicle);
//        
//    }    
//    
//    @Test
//    public void testUpdateVehicleTransmissionTooLong() throws InvalidVehicleException, DuplicateVINException, InvalidMakeException, DuplicateMakeException, InvalidModelException{
//        //Arrange
//        Vehicle vehicle = new Vehicle();
//        Model model = new Model();
//        Make make = new Make();
//        
//        make.setMakeName("Ford");
//        
//        Make createdMake = makeDao.addMake(make);
//        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        
//        Model createdModel = cmDao.addModel(model);
//        
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
//
//        //Act
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);
//        
//        createdVehicle.setTransmission("AutomaticAutomaticAut");
//        
//        try{
//            vservice.updateVehicle(createdVehicle);
//            fail("Should have thrown InvalidVehicleException");
//        } catch(InvalidVehicleException ex) {}
//        
//        Vehicle fetchedVehicle = vservice.readVehicle(createdVehicle.getVehicleId());
//        assertEquals(fetchedVehicle.getTransmission(),"Automatic");
//    }
//    
//    @Test
//    public void testUpdateVehicleMaxMileage() throws InvalidVehicleException, InvalidMakeException, InvalidModelException, DuplicateVINException, DuplicateMakeException{
//        //Arrange
//        Vehicle vehicle = new Vehicle();
//        Model model = new Model();
//        Make make = new Make();
//        
//        make.setMakeName("Ford");
//        
//        Make createdMake = makeDao.addMake(make);
//        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        
//        Model createdModel = cmDao.addModel(model);
//        
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
//
//        //Act
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);
//        
//        createdVehicle.setMileage(Integer.MAX_VALUE);
//        
//        try{
//            vservice.updateVehicle(createdVehicle);
//            fail("Should have thrown InvalidVehicleException");
//        } catch(InvalidVehicleException ex){}
//        
//        Vehicle fetchedVehicle = vservice.readVehicle(createdVehicle.getVehicleId());
//        assertEquals(fetchedVehicle.getMileage(), 1000);
//    }
//    
//    @Test
//    public void testUpdateVehicleNegativeMileage() throws InvalidVehicleException, InvalidMakeException, DuplicateVINException, InvalidModelException, DuplicateMakeException{
//        //Arrange
//        Vehicle vehicle = new Vehicle();
//        Model model = new Model();
//        Make make = new Make();
//        
//        make.setMakeName("Ford");
//        
//        Make createdMake = makeDao.addMake(make);
//        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        
//        Model createdModel = cmDao.addModel(model);
//        
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
//
//        //Act
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);
//        
//        createdVehicle.setMileage(-1);
//        
//        try{
//            vservice.updateVehicle(createdVehicle);
//            fail("Should have thrown InvalidVehicleException");
//        } catch(InvalidVehicleException ex){}
//        
//        Vehicle fetchedVehicle = vservice.readVehicle(createdVehicle.getVehicleId());
//        assertEquals(fetchedVehicle.getMileage(),1000);
//        
//    }
//    @Test
//    public void testUpdateVehicleSalesPriceNegative() throws InvalidVehicleException, InvalidModelException, DuplicateMakeException, InvalidMakeException, DuplicateVINException{
//        //Arrange
//        Vehicle vehicle = new Vehicle();
//        Model model = new Model();
//        Make make = new Make();
//        
//        make.setMakeName("Ford");
//        
//        Make createdMake = makeDao.addMake(make);
//        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        
//        Model createdModel = cmDao.addModel(model);
//        
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
//
//        //Act
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);
//        
//        createdVehicle.setSalesPrice(new BigDecimal("-.01"));
//        
//        try{
//            vservice.updateVehicle(createdVehicle);
//            fail("Should have thrown InvalidVehicleException");
//        } catch(InvalidVehicleException ex){}
//        
//        Vehicle fetchedVehicle = vservice.readVehicle(createdVehicle.getVehicleId());
//        assertEquals(fetchedVehicle.getSalesPrice(),new BigDecimal("35000.00"));
//        
//    }
//
//@Test
//    public void testSearchForVehicles() throws InvalidVehicleException, InvalidMakeException, DuplicateMakeException, InvalidModelException, DuplicateVINException{
//        Vehicle vehicle = new Vehicle();
//        Model model = new Model();
//        Make make = new Make();
//        
//        make.setMakeName("Ford");
//        
//        Make createdMake = makeDao.addMake(make);
//        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        
//        Model createdModel = cmDao.addModel(model);
//        
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
//        
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);
//        
//        //Vehicle 2
//        Vehicle vehicle2 = new Vehicle();
//        Model model2 = new Model();
//        Make make2 = new Make();
//        
//        make2.setMakeName("Chevy");
//        
//        Make createdMake2 = makeDao.addMake(make2);
//        
//        model2.setModelName("Forrester");
//        model2.setMake(createdMake2);
//        
//        Model createdModel2 = cmDao.addModel(model2);
//        
//        vehicle2.setYear(2015);
//        vehicle2.setTransmission("Automatic");
//        vehicle2.setMileage(30000);
//        vehicle2.setColor("Silver");
//        vehicle2.setInterior("Cloth");
//        vehicle2.setBodyType("Truck");
//        vehicle2.setVin("ALEDC65KPY8PSVKD");
//        vehicle2.setSalesPrice(new BigDecimal("18000.00"));
//        vehicle2.setMsrp(new BigDecimal("35000.00"));
//        vehicle2.setDescription("A sturdy vehicle meant for hauling trailers or other oversized loads");
//        vehicle2.setPicURL("http://www.sampleurl.com/samplepic2");
//        vehicle2.setModel(createdModel2);
//     
//        Vehicle createdVehicle2 = vservice.createVehicle(vehicle2);
//        
//        //Vehicle 3
//        Vehicle vehicle3 = new Vehicle();
//        Model model3 = new Model();
//        Make make3 = new Make();
//        
//        make3.setMakeName("Ford");
//        
//        Make createdMake3 = makeDao.addMake(make3);
//        
//        model3.setModelName("Carolla");
//        model3.setMake(createdMake3);
//        
//        Model createdModel3 = cmDao.addModel(model3);
//        
//        vehicle3.setYear(2016);
//        vehicle3.setTransmission("Automatic");
//        vehicle3.setMileage(25000);
//        vehicle3.setColor("Red");
//        vehicle3.setInterior("Cloth");
//        vehicle3.setBodyType("Sedan");
//        vehicle3.setVin("EZ4FPVL6NPD7VJCD");
//        vehicle3.setSalesPrice(new BigDecimal("23000.00"));
//        vehicle3.setMsrp(new BigDecimal("26000.00"));
//        vehicle3.setDescription("A feul efficient vehicle great for commuting in the city.");
//        vehicle3.setPicURL("http://www.sampleurl.com/samplepic3");
//        vehicle3.setModel(createdModel3);
//     
//        Vehicle createdVehicle3 = vservice.createVehicle(vehicle3);
//        
//        //Act
//        //List<Vehicle> vehicles = service.getAllVehicles();
//        String vehicleSearch = "Fo";
//        Integer minYear = 1990;
//        Integer maxYear = 2018;
//        BigDecimal minPrice = new BigDecimal("20000.00");
//        BigDecimal maxPrice = new BigDecimal("60000.00");
//
//        
//        List<Vehicle> allVehicles = vservice.searchVehicle(vehicleSearch, minYear, maxYear, minPrice, maxPrice);
//        
//        assertEquals(2, allVehicles.size());
//        assertEquals("Ford", allVehicles.get(0).getModel().getMake().getMakeName());
//        assertEquals("Carolla", allVehicles.get(1).getModel().getModelName());   
//    }
//
//@Test
//    public void testSearchForVehicles2() throws InvalidVehicleException, DuplicateVINException{
//        Vehicle vehicle = new Vehicle();
//        Model model = new Model();
//        Make make = new Make();
//        
//        make.setMakeName("Ford");
//        
//        Make createdMake = makeDao.addMake(make);
//        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        
//        Model createdModel = cmDao.addModel(model);
//        
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
//        
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);
//        
//        //Vehicle 2
//        Vehicle vehicle2 = new Vehicle();
//        Model model2 = new Model();
//        Make make2 = new Make();
//        
//        make2.setMakeName("Subaru");
//        
//        Make createdMake2 = makeDao.addMake(make2);
//        
//        model2.setModelName("Forrester");
//        model2.setMake(createdMake2);
//        
//        Model createdModel2 = cmDao.addModel(model2);
//        
//        vehicle2.setYear(2015);
//        vehicle2.setTransmission("Automatic");
//        vehicle2.setMileage(30000);
//        vehicle2.setColor("Silver");
//        vehicle2.setInterior("Cloth");
//        vehicle2.setBodyType("Truck");
//        vehicle2.setVin("ALEDC65KPY8PSVKD");
//        vehicle2.setSalesPrice(new BigDecimal("18000.00"));
//        vehicle2.setMsrp(new BigDecimal("35000.00"));
//        vehicle2.setDescription("A sturdy vehicle meant for hauling trailers or other oversized loads");
//        vehicle2.setPicURL("http://www.sampleurl.com/samplepic2");
//        vehicle2.setModel(createdModel2);
//     
//        Vehicle createdVehicle2 = vservice.createVehicle(vehicle2);
//        
//        //Vehicle 3
//        Vehicle vehicle3 = new Vehicle();
//        Model model3 = new Model();
//        Make make3 = new Make();
//        
//        make3.setMakeName("Ford");
//        
//        Make createdMake3 = makeDao.addMake(make3);
//        
//        model3.setModelName("Focus");
//        model3.setMake(createdMake3);
//        
//        Model createdModel3 = cmDao.addModel(model3);
//        
//        vehicle3.setYear(2016);
//        vehicle3.setTransmission("Automatic");
//        vehicle3.setMileage(25000);
//        vehicle3.setColor("Red");
//        vehicle3.setInterior("Cloth");
//        vehicle3.setBodyType("Sedan");
//        vehicle3.setVin("EZ4FPVL6NPD7VJCD");
//        vehicle3.setSalesPrice(new BigDecimal("23000.00"));
//        vehicle3.setMsrp(new BigDecimal("26000.00"));
//        vehicle3.setDescription("A feul efficient vehicle great for commuting in the city.");
//        vehicle3.setPicURL("http://www.sampleurl.com/samplepic3");
//        vehicle3.setModel(createdModel3);
//     
//        Vehicle createdVehicle3 = vservice.createVehicle(vehicle3);
//        
//        //Act
//        //List<Vehicle> vehicles = service.getAllVehicles();
//        String vehicleSearch = "Fo";
//        Integer minYear = 1990;
//        Integer maxYear = 2018;
//        BigDecimal minPrice = new BigDecimal("20000.00");
//        BigDecimal maxPrice = new BigDecimal("60000.00");
//
//        
//        List<Vehicle> allVehicles = vservice.searchVehicle(vehicleSearch, minYear, maxYear, minPrice, maxPrice);
//        
//        assertEquals(2, allVehicles.size());
//        assertEquals("Ford", allVehicles.get(0).getModel().getMake().getMakeName());
//        assertEquals("Ford", allVehicles.get(1).getModel().getMake().getMakeName());
//        assertEquals("Focus", allVehicles.get(1).getModel().getModelName());
//    }
//    
//    @Test
//    public void testSearchForVehiclesNoYear() throws InvalidVehicleException, DuplicateVINException{
//        Vehicle vehicle = new Vehicle();
//        Model model = new Model();
//        Make make = new Make();
//        
//        make.setMakeName("Ford");
//        
//        Make createdMake = makeDao.addMake(make);
//        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        
//        Model createdModel = cmDao.addModel(model);
//        
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
//        
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);
//        
//        //Vehicle 2
//        Vehicle vehicle2 = new Vehicle();
//        Model model2 = new Model();
//        Make make2 = new Make();
//        
//        make2.setMakeName("Subaru");
//        
//        Make createdMake2 = makeDao.addMake(make2);
//        
//        model2.setModelName("Forrester");
//        model2.setMake(createdMake2);
//        
//        Model createdModel2 = cmDao.addModel(model2);
//        
//        vehicle2.setYear(2015);
//        vehicle2.setTransmission("Automatic");
//        vehicle2.setMileage(30000);
//        vehicle2.setColor("Silver");
//        vehicle2.setInterior("Cloth");
//        vehicle2.setBodyType("Truck");
//        vehicle2.setVin("ALEDC65KPY8PSVKD");
//        vehicle2.setSalesPrice(new BigDecimal("18000.00"));
//        vehicle2.setMsrp(new BigDecimal("35000.00"));
//        vehicle2.setDescription("A sturdy vehicle meant for hauling trailers or other oversized loads");
//        vehicle2.setPicURL("http://www.sampleurl.com/samplepic2");
//        vehicle2.setModel(createdModel2);
//     
//        Vehicle createdVehicle2 = vservice.createVehicle(vehicle2);
//        
//        //Vehicle 3
//        Vehicle vehicle3 = new Vehicle();
//        Model model3 = new Model();
//        Make make3 = new Make();
//        
//        make3.setMakeName("Ford");
//        
//        Make createdMake3 = makeDao.addMake(make3);
//        
//        model3.setModelName("Focus");
//        model3.setMake(createdMake3);
//        
//        Model createdModel3 = cmDao.addModel(model3);
//        
//        vehicle3.setYear(2016);
//        vehicle3.setTransmission("Automatic");
//        vehicle3.setMileage(25000);
//        vehicle3.setColor("Red");
//        vehicle3.setInterior("Cloth");
//        vehicle3.setBodyType("Sedan");
//        vehicle3.setVin("EZ4FPVL6NPD7VJCD");
//        vehicle3.setSalesPrice(new BigDecimal("23000.00"));
//        vehicle3.setMsrp(new BigDecimal("26000.00"));
//        vehicle3.setDescription("A feul efficient vehicle great for commuting in the city.");
//        vehicle3.setPicURL("http://www.sampleurl.com/samplepic3");
//        vehicle3.setModel(createdModel3);
//     
//        Vehicle createdVehicle3 = vservice.createVehicle(vehicle3);
//        
//        //Act
//        //List<Vehicle> vehicles = service.getAllVehicles();
//        String vehicleSearch = "Fo";
//        Integer minYear = null;
//        Integer maxYear = null;
//        BigDecimal minPrice = new BigDecimal("20000.00");
//        BigDecimal maxPrice = new BigDecimal("60000.00");
//
//        
//        List<Vehicle> allVehicles = vservice.searchVehicle(vehicleSearch, minYear, maxYear, minPrice, maxPrice);
//        
//        assertEquals(2, allVehicles.size());
//        assertEquals("Ford", allVehicles.get(0).getModel().getMake().getMakeName());
//        assertEquals("Focus", allVehicles.get(1).getModel().getModelName());
//
//    }
//    
//    @Test
//    public void testSearchForVehiclesMaxYear() throws InvalidVehicleException, DuplicateVINException{
//        Vehicle vehicle = new Vehicle();
//        Model model = new Model();
//        Make make = new Make();
//        
//        make.setMakeName("Ford");
//        
//        Make createdMake = makeDao.addMake(make);
//        
//        model.setModelName("Explorer");
//        model.setMake(createdMake);
//        
//        Model createdModel = cmDao.addModel(model);
//        
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
//        
//        Vehicle createdVehicle = vservice.createVehicle(vehicle);
//        
//        //Vehicle 2
//        Vehicle vehicle2 = new Vehicle();
//        Model model2 = new Model();
//        Make make2 = new Make();
//        
//        make2.setMakeName("Subaru");
//        
//        Make createdMake2 = makeDao.addMake(make2);
//        
//        model2.setModelName("Forrester");
//        model2.setMake(createdMake2);
//        
//        Model createdModel2 = cmDao.addModel(model2);
//        
//        vehicle2.setYear(2015);
//        vehicle2.setTransmission("Automatic");
//        vehicle2.setMileage(30000);
//        vehicle2.setColor("Silver");
//        vehicle2.setInterior("Cloth");
//        vehicle2.setBodyType("Truck");
//        vehicle2.setVin("ALEDC65KPY8PSVKD");
//        vehicle2.setSalesPrice(new BigDecimal("18000.00"));
//        vehicle2.setMsrp(new BigDecimal("35000.00"));
//        vehicle2.setDescription("A sturdy vehicle meant for hauling trailers or other oversized loads");
//        vehicle2.setPicURL("http://www.sampleurl.com/samplepic2");
//        vehicle2.setModel(createdModel2);
//     
//        Vehicle createdVehicle2 = vservice.createVehicle(vehicle2);
//        
//        //Vehicle 3
//        Vehicle vehicle3 = new Vehicle();
//        Model model3 = new Model();
//        Make make3 = new Make();
//        
//        make3.setMakeName("Ford");
//        
//        Make createdMake3 = makeDao.addMake(make3);
//        
//        model3.setModelName("Focus");
//        model3.setMake(createdMake3);
//        
//        Model createdModel3 = cmDao.addModel(model3);
//        
//        vehicle3.setYear(2016);
//        vehicle3.setTransmission("Automatic");
//        vehicle3.setMileage(25000);
//        vehicle3.setColor("Red");
//        vehicle3.setInterior("Cloth");
//        vehicle3.setBodyType("Sedan");
//        vehicle3.setVin("EZ4FPVL6NPD7VJCD");
//        vehicle3.setSalesPrice(new BigDecimal("23000.00"));
//        vehicle3.setMsrp(new BigDecimal("26000.00"));
//        vehicle3.setDescription("A feul efficient vehicle great for commuting in the city.");
//        vehicle3.setPicURL("http://www.sampleurl.com/samplepic3");
//        vehicle3.setModel(createdModel3);
//     
//        Vehicle createdVehicle3 = vservice.createVehicle(vehicle3);
//        
//        //Act
//        //List<Vehicle> vehicles = service.getAllVehicles();
//        String vehicleSearch = "Fo";
//        Integer minYear = null;
//        Integer maxYear = 2016;
//        BigDecimal minPrice = new BigDecimal("5000.00");
//        BigDecimal maxPrice = new BigDecimal("60000.00");
//
//        
//        List<Vehicle> allVehicles = vservice.searchVehicle(vehicleSearch, minYear, maxYear, minPrice, maxPrice);
//        
//        assertEquals(2, allVehicles.size());
//        assertEquals("Subaru", allVehicles.get(0).getModel().getMake().getMakeName());
//        assertEquals("Focus", allVehicles.get(1).getModel().getModelName());
//    }    
//}    