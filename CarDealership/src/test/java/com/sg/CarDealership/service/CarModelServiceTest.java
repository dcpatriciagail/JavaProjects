/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dao.carModelDao;
import com.sg.CarDealership.dao.makeDao;
import com.sg.CarDealership.dto.Model;
import com.sg.CarDealership.dto.Make;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class CarModelServiceTest {
    
    @Autowired
    CarModelService modelService;
    
    @Autowired
     makeDao makeDao;
    
    public CarModelServiceTest() {
    }
    

    /**
     * Test of createModel method, of class CarModelService.
     */
    @Test
    public void testCreateModel() throws InvalidModelException {
        //Arrange
        Model model = new Model();
        Make make = new Make();

        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        
        //Act
        Model createdModel = modelService.createModel(model);    
        Model fetchedModel = modelService.readModel(createdModel.getModelId());
        
        //Assert
        assertEquals(fetchedModel.getModelName(), "Explorer");
        assertEquals(fetchedModel.getModelId(), createdModel.getModelId());
        assertEquals(fetchedModel.getMake().getMakeName(), "Ford");        
    }

    /**
     * Test of readModel method, of class CarModelService.
     */
    @Test
    public void testReadModel() throws InvalidModelException {
        //Arrange
        Model model = new Model();
        Make make = new Make();

        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);        
        
        Model createdModel = modelService.createModel(model);    

        //Act        
        Model fetchedModel = modelService.readModel(createdModel.getModelId());

        //Assert
        assertEquals(fetchedModel.getModelName(), "Explorer");
        assertEquals(fetchedModel.getModelId(), createdModel.getModelId());
        assertEquals(fetchedModel.getMake().getMakeName(), "Ford");             
    }

    /**
     * Test of readAllModels method, of class CarModelService.
     */
    @Test
    public void testReadAllModels() throws InvalidModelException {
        //Arrange
        Model model = new Model();
        Make make = new Make();

        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);        
        Model createdModel = modelService.createModel(model);          
        
        Model model2 = new Model();
        Make make2 = new Make();

        make2.setMakeName("Honda");
        Make createdMake2 = makeDao.addMake(make2);
        
        model2.setModelName("Civic");
        model2.setMake(createdMake);        
        Model createdModel2 = modelService.createModel(model2);
        
        //Act
        List<Model> carModels = modelService.readAllModels();
        
        //Assert
        assertEquals(createdModel.getModelName(), carModels.get(0).getModelName());
        assertEquals(createdModel2.getModelName(), carModels.get(1).getModelName());       
        assertEquals(createdModel.getModelId(), carModels.get(0).getModelId());
        assertEquals(createdModel2.getModelId(), carModels.get(1).getModelId());  
        assertEquals(carModels.size(), 2);        
        
        
    }


    @Test
    public void testCreateModelNoName() {
        //Arrange
        Model model = new Model();
        Make make = new Make();

        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("");
        model.setMake(createdMake);        
        Model createdModel=null;
        //Act
        try {
            createdModel = modelService.createModel(model);          
            fail("Should have thrown InvalidModelException");
        } catch (InvalidModelException ex) {
            
        }
        assertNull(createdModel);
    }
    
    
    @Test
    public void testCreateModelNoMake() {
        //Arrange
        Model model = new Model();
        Make make = null;

        model.setMake(make);
        
        model.setModelName("");       
        Model createdModel=null;
        //Act
        try {
            createdModel = modelService.createModel(model);          
            fail("Should have thrown InvalidModelException");
        } catch (InvalidModelException ex) {
            
        }
        assertNull(createdModel);
    }    
    
    @Test
    public void testCreateModelTooLongName() {
        //Arrange
        Model model = new Model();
        Make make = new Make();

        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setMake(createdMake);
        
        model.setModelName("qwertyuiopasdfghjklzxcvbnm");       
        Model createdModel=null;
        //Act
        try {
            createdModel = modelService.createModel(model);          
            fail("Should have thrown InvalidModelException");
        } catch (InvalidModelException ex) {
            
        }
        assertNull(createdModel);
    }       
    
    
    @Test
    public void testCreateModelTooShort() {
        //Arrange
        Model model = new Model();
        Make make = new Make();

        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setMake(createdMake);
        
        model.setModelName("qwertyuiop");    //10 characters  
        Model createdModel=null;      
        
        //Act
        try {
            createdModel = modelService.createModel(model);          
            
        } catch (InvalidModelException ex) {
            fail("Should have thrown InvalidModelException");
        }
        assertNotNull(createdModel);        
 
        
    }
    
    
@Test
    public void testUpdateModelNoName() throws InvalidModelException{
        //Arrange
        Make make = new Make();
        Model model = new Model();
        
        make.setMakeName("Ford");
        
        Make createdMake = makeDao.addMake(make);
        
        model.setMake(createdMake);
        model.setModelName("Mustang");
        
        Model createdModel = modelService.createModel(model);
        createdModel.setModelName("");
        //Act
        try {
            createdModel.setModelName("");
            modelService.updateModel(createdModel);
            fail("Should have thrown InvalidModelException");
        } catch (InvalidModelException ex) {}
        
        Model fetchedModel = modelService.readModel(createdModel.getModelId());
        assertEquals(fetchedModel.getModelName(),"Mustang");
    }    

@Test
    public void testUpdateModelTooLong() throws InvalidModelException {
        //Arrange
        Make make = new Make();
        Model model = new Model();
        
        make.setMakeName("Ford");
        
        Make createdMake = makeDao.addMake(make);
        
        model.setMake(createdMake);
        model.setModelName("Mustang");
        
        Model createdModel = modelService.createModel(model);
        //createdModel.setCarModelName("");
        //Act
        try {
            createdModel.setModelName("FocusFocusFocusFocusF");
            modelService.updateModel(model);
            fail("Should have thrown InvalidModelException");
        } catch (InvalidModelException ex) {}

        Model fetchedModel = modelService.readModel(createdModel.getModelId());        
        assertEquals(fetchedModel.getModelName(),"Mustang");
    }
    
    @Test
    public void testUpdateModelJustUnderCharLimit() throws InvalidModelException {
        //Arrange
        Make make = new Make();
        Model model = new Model();
        
        make.setMakeName("Ford");
        
        Make createdMake = makeDao.addMake(make);
        
        model.setMake(createdMake);
        model.setModelName("Mustang");
        
        Model createdModel = modelService.createModel(model);
        createdModel.setModelName("");
        //Act
        try {
            createdModel.setModelName("FocusFocusFocusFocu");
            modelService.updateModel(model);

        } catch (InvalidModelException ex) {
            fail("Should have thrown InvalidModelException");
        }
        
        assertEquals(createdModel.getModelName(),"FocusFocusFocusFocu");
    }



}
