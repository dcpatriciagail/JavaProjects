/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Model;
import com.sg.CarDealership.dto.Make;
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
public class carModelDaoImplTest {
    
    @Autowired
    carModelDao modelDao;
    
    @Autowired
    makeDao makeDao;
    
    public carModelDaoImplTest() {
    }
    

    /**
     * Test of addModel method, of class carModelDaoImpl.
     */
    @Test
    public void testAddModel() {
        //Arrange
        Model model = new Model();
        Make make = new Make();

        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);
        
        //Act
        Model createdModel = modelDao.addModel(model);    
        Model fetchedModel = modelDao.getModelById(createdModel.getModelId());
        
        //Assert
        assertEquals(fetchedModel.getModelName(), "Explorer");
        assertEquals(fetchedModel.getModelId(), createdModel.getModelId());
        assertEquals(fetchedModel.getMake().getMakeName(), "Ford");
    }

    /**
     * Test of getModelById method, of class carModelDaoImpl.
     */
    @Test
    public void testGetModelById() {
        //Arrange
        Model model = new Model();
        Make make = new Make();

        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);        
        
        Model createdModel = modelDao.addModel(model);    

        //Act        
        Model fetchedModel = modelDao.getModelById(createdModel.getModelId());

        //Assert
        assertEquals(fetchedModel.getModelName(), "Explorer");
        assertEquals(fetchedModel.getModelId(), createdModel.getModelId());
        assertEquals(fetchedModel.getMake().getMakeName(), "Ford");        
        
        
        
    }

    /**
     * Test of updateModel method, of class carModelDaoImpl.
     */
    @Test
    public void testUpdateModel() {
        //Arrange
        Model model = new Model();
        Make make = new Make();

        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);

        Model createdModel = modelDao.addModel(model); 
        createdModel.setModelName("Explorers");
        
        //Act     
        modelDao.updateModel(createdModel);
        Model fetchedModel = modelDao.getModelById(createdModel.getModelId());

        //Assert
        assertEquals(fetchedModel.getModelName(), createdModel.getModelName());
        
        
    }



    /**
     * Test of readAllModels method, of class carModelDaoImpl.
     */
    @Test
    public void testReadAllModels() {
        //Arrange
        Model model = new Model();
        Make make = new Make();

        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make);
        
        model.setModelName("Explorer");
        model.setMake(createdMake);        
        Model createdModel = modelDao.addModel(model);          
        
        Model model2 = new Model();
        Make make2 = new Make();

        make2.setMakeName("Honda");
        Make createdMake2 = makeDao.addMake(make2);
        
        model2.setModelName("Civic");
        model2.setMake(createdMake);        
        Model createdModel2 = modelDao.addModel(model2);
        
        //Act
        List<Model> carModels = modelDao.readAllModels();
        
        //Assert
        assertEquals(createdModel.getModelName(), carModels.get(0).getModelName());
        assertEquals(createdModel2.getModelName(), carModels.get(1).getModelName());       
        assertEquals(createdModel.getModelId(), carModels.get(0).getModelId());
        assertEquals(createdModel2.getModelId(), carModels.get(1).getModelId());  
        assertEquals(carModels.size(), 2);
        
    }
    
}
