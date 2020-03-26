/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Model;
import com.sg.CarDealership.dto.Make;
import com.sg.CarDealership.dto.Vehicle;
import java.math.BigDecimal;
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
public class vehicleDaoImplTest {
    @Autowired
    vehicleDao vehicleDao;
    
    @Autowired
    carModelDao modelDao;
    
    @Autowired
    makeDao makeDao;
          
    
    public vehicleDaoImplTest() {
    }
    


    @Test
    public void testAddVehicle() {
        //Arrange
        Vehicle vehicle = new Vehicle();
        Model model = new Model();
        Make make = new Make();
        
        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        Model createdModel = modelDao.addModel(model);
        
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
        //Act
        Vehicle createdVehicle = vehicleDao.addVehicle(vehicle);
        Vehicle fetchedVehicle = vehicleDao.getVehicleById(createdVehicle.getVehicleId());
        
        //Assert
        assertEquals(fetchedVehicle.getYear(),(int) 2018);
        assertEquals(fetchedVehicle.getTransmission(), "Automatic");
        assertEquals(fetchedVehicle.getMileage(), (int) 1000);
        assertEquals(fetchedVehicle.getColor(), "Blue");
        assertEquals(fetchedVehicle.getInterior(),"Leather");
        assertEquals(fetchedVehicle.getBodyType(),"SUV");
        assertEquals(fetchedVehicle.getVin(), "W9D81KQ93N8Z0KS7");
        assertEquals(fetchedVehicle.getSalesPrice(), new BigDecimal("35000.00"));
        assertEquals(fetchedVehicle.getMsrp(), new BigDecimal("40000.00"));
        assertEquals(fetchedVehicle.getDescription(), "A practical vehicle");
        assertEquals(fetchedVehicle.getPicURL(), "http://www.sampleurl.com/samplepic");
        assertEquals(fetchedVehicle.getModel().getModelName(), "Explorer");
        assertEquals(fetchedVehicle.getModel().getMake().getMakeName(), "Ford");
        
        
        
    }

    /**
     * Test of getVehicleById method, of class vehicleDaoImpl.
     */
    @Test
    public void testGetVehicleById() {
        //Arrange
        Vehicle vehicle = new Vehicle();
        Model model = new Model();
        Make make = new Make();
        
        make.setMakeName("Ford");
        
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        
        Model createdModel = modelDao.addModel(model);
        
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
        
        //Act
        Vehicle fetchedVehicle = vehicleDao.getVehicleById(createdVehicle.getVehicleId());
        
        //Assert
        assertEquals(fetchedVehicle.getYear(),(int) 2018);
        assertEquals(fetchedVehicle.getTransmission(), "Automatic");
        assertEquals(fetchedVehicle.getMileage(), (int) 1000);
        assertEquals(fetchedVehicle.getColor(), "Blue");
        assertEquals(fetchedVehicle.getInterior(),"Leather");
        assertEquals(fetchedVehicle.getBodyType(),"SUV");
        assertEquals(fetchedVehicle.getVin(), "W9D81KQ93N8Z0KS7");
        assertEquals(fetchedVehicle.getSalesPrice(), new BigDecimal("35000.00"));
        assertEquals(fetchedVehicle.getMsrp(), new BigDecimal("40000.00"));
        assertEquals(fetchedVehicle.getDescription(), "A practical vehicle");
        assertEquals(fetchedVehicle.getPicURL(), "http://www.sampleurl.com/samplepic");
        assertEquals(fetchedVehicle.getModel().getModelName(), "Explorer");
        assertEquals(fetchedVehicle.getModel().getMake().getMakeName(), "Ford");
    }

    /**
     * Test of updateVehicle method, of class vehicleDaoImpl.
     */
    @Test
    public void testUpdateVehicleSold() {
        //Arrange
        Vehicle vehicle = new Vehicle();
        Model model = new Model();
        Make make = new Make();
        
        make.setMakeName("Ford");
        
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        
        Model createdModel = modelDao.addModel(model);
        
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

        createdVehicle.setSalesPrice(new BigDecimal ("30000.00"));
        createdVehicle.setInStock(false);
        
        //Act        
        vehicleDao.updateVehicle(createdVehicle);
        
        //Assert
        Vehicle fetchedVehicle = vehicleDao.getVehicleById(createdVehicle.getVehicleId());
        
        assertEquals(vehicle.getSalesPrice(), fetchedVehicle.getSalesPrice());
        assertFalse(fetchedVehicle.isInStock());
        
    }

    /**
     * Test of deleteVehicleById method, of class vehicleDaoImpl.
     */
    @Test
    public void testDeleteVehicleById() {
        //Arrange
        Vehicle vehicle = new Vehicle();
        Model model = new Model();
        Make make = new Make();
        
        make.setMakeName("Ford");
        
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        
        Model createdModel = modelDao.addModel(model);
        
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

        //Act
        vehicleDao.deleteVehicleById(vehicle.getVehicleId());
        
        //Assert
        Vehicle fetchedVehicle = vehicleDao.getVehicleById(createdVehicle.getVehicleId());
        assertNull (fetchedVehicle);
        
    }

    /**
     * Test of readAllVehicle method, of class vehicleDaoImpl.
     */
    @Test
    public void testReadAllVehicle() {
        //Arrange
        //Vehicle 1
        Vehicle vehicle = new Vehicle();
        Model model = new Model();
        Make make = new Make();
        
        make.setMakeName("Ford");
        
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        
        Model createdModel = modelDao.addModel(model);
        
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
        
        //Vehicle 2
        Vehicle vehicle2 = new Vehicle();
        Model model2 = new Model();
        Make make2 = new Make();
        
        make2.setMakeName("Chevy");
        
        Make createdMake2 = makeDao.addMake(make2);
        
        model2.setModelName("Silverado");
        model2.setMake(createdMake2);
        
        Model createdModel2 = modelDao.addModel(model2);
        
        vehicle2.setYear(2015);
        vehicle2.setTransmission("Automatic");
        vehicle2.setMileage(30000);
        vehicle2.setColor("Silver");
        vehicle2.setInterior("Cloth");
        vehicle2.setBodyType("Truck");
        vehicle2.setVin("ALEDC65KPY8PSVKD");
        vehicle2.setSalesPrice(new BigDecimal("28000.00"));
        vehicle2.setMsrp(new BigDecimal("35000.00"));
        vehicle2.setDescription("A sturdy vehicle meant for hauling trailers or other oversized loads");
        vehicle2.setPicURL("http://www.sampleurl.com/samplepic2");
        vehicle2.setModel(createdModel2);
     
        Vehicle createdVehicle2 = vehicleDao.addVehicle(vehicle2);
        
        //Vehicle 3
        Vehicle vehicle3 = new Vehicle();
        Model model3 = new Model();
        Make make3 = new Make();
        
        make3.setMakeName("Toyota");
        
        Make createdMake3 = makeDao.addMake(make3);
        
        model3.setModelName("Carolla");
        model3.setMake(createdMake3);
        
        Model createdModel3 = modelDao.addModel(model3);
        
        vehicle3.setYear(2016);
        vehicle3.setTransmission("Automatic");
        vehicle3.setMileage(25000);
        vehicle3.setColor("Red");
        vehicle3.setInterior("Cloth");
        vehicle3.setBodyType("Sedan");
        vehicle3.setVin("EZ4FPVL6NPD7VJCD");
        vehicle3.setSalesPrice(new BigDecimal("23000.00"));
        vehicle3.setMsrp(new BigDecimal("26000.00"));
        vehicle3.setDescription("A feul efficient vehicle great for commuting in the city.");
        vehicle3.setPicURL("http://www.sampleurl.com/samplepic3");
        vehicle3.setModel(createdModel3);
     
        Vehicle createdVehicle3 = vehicleDao.addVehicle(vehicle3);
        
        //Act
        List<Vehicle> vehicles = vehicleDao.readAllVehicle();
        
        //Assert
        assertEquals(createdVehicle.getVehicleId(),vehicles.get(0).getVehicleId());
        assertEquals(createdVehicle2.getVehicleId(),vehicles.get(1).getVehicleId());
        assertEquals(createdVehicle3.getVehicleId(),vehicles.get(2).getVehicleId());
        assertEquals(createdVehicle.getVin(),vehicles.get(0).getVin());
        assertEquals(createdVehicle2.getVin(),vehicles.get(1).getVin());
        assertEquals(createdVehicle3.getVin(),vehicles.get(2).getVin());
    }
 
    
    
}
