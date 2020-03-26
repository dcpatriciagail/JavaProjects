/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

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
public class makeDaoImplTest {

    @Autowired
    makeDao makeDao;
    
    public makeDaoImplTest() {
    }
    

    /**
     * Test of addMake method, of class makeDaoImpl.
     */
    @Test
    public void testAddMake() {
        //Arrange
        Make make = new Make();
        make.setMakeName("Ford");
       
        
        //Act
        Make createdMake = makeDao.addMake(make); 
        Make fetchedMake = makeDao.getMakeById(createdMake.getMakeId());
        
        //Assert
        assertEquals(fetchedMake.getMakeId(),  createdMake.getMakeId());
        assertEquals(fetchedMake.getMakeName(), "Ford");
        
    }

    /**
     * Test of getMakeById method, of class makeDaoImpl.
     */
    @Test
    public void testGetMakeById() {       
        //Arrange
        Make make = new Make();
        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make); 
        
        //Act
        Make fetchedMake = makeDao.getMakeById(createdMake.getMakeId());
        
        //Assert
        assertEquals(fetchedMake.getMakeId(),  createdMake.getMakeId());
    }

    /**
     * Test of updateMake method, of class makeDaoImpl.
     */
    @Test
    public void testUpdateMake() {
        
        //Arrange
        Make make = new Make();
        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make); 
        
        createdMake.setMakeName("Fords");
        
        //Act
        makeDao.updateMake(createdMake);
        Make fetchedMake = makeDao.getMakeById(createdMake.getMakeId());        
        
        //Assert
        assertEquals(fetchedMake.getMakeName(), createdMake.getMakeName());
    }


    /**
     * Test of readAllMake method, of class makeDaoImpl.
     */
    @Test
    public void testReadAllMake() {
        //Arrange
        Make make = new Make();
        make.setMakeName("Ford");
        Make createdMake = makeDao.addMake(make); 
        
        Make make2 = new Make();
        make2.setMakeName("Honda");
        Make createdMake2 = makeDao.addMake(make2);        
        
        //Act
        List<Make> listMake = makeDao.readAllMake();
        
        //Assert
        assertEquals(listMake.size(),2);
        assertEquals(createdMake.getMakeName(), "Ford");
        assertEquals(createdMake.getMakeId(),  listMake.get(0).getMakeId());
    }
    
}
