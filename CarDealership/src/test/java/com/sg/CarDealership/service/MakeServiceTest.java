/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

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
public class MakeServiceTest {
    
    @Autowired
    MakeService makeService;
    
    
    public MakeServiceTest() {
    }

    /**
     * Test of createMake method, of class MakeService.
     */
    @Test
    public void testCreateMake() throws InvalidMakeException, DuplicateMakeException {
        //Arrange
        Make make = new Make();
        make.setMakeName("Ford");
       
        
        //Act
        Make createdMake = makeService.createMake(make); 
        Make fetchedMake = makeService.readMake(createdMake.getMakeId());
        
        //Assert
        assertEquals(fetchedMake.getMakeId(),  createdMake.getMakeId());
        assertEquals(fetchedMake.getMakeName(), "Ford");
    }

    /**
     * Test of readMake method, of class MakeService.
     */
    @Test
    public void testReadMake() throws InvalidMakeException, DuplicateMakeException {
        //Arrange
        Make make = new Make();
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make); 
        
        //Act
        Make fetchedMake = makeService.readMake(createdMake.getMakeId());
        
        //Assert
        assertEquals(fetchedMake.getMakeId(),  createdMake.getMakeId());
    }

    /**
     * Test of readAllMakes method, of class MakeService.
     */
    @Test
    public void testReadAllMakes() throws InvalidMakeException, DuplicateMakeException {
        //Arrange
        Make make = new Make();
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make); 
        
        Make make2 = new Make();
        make2.setMakeName("Honda");
        Make createdMake2 = makeService.createMake(make2);        
        
        //Act
        List<Make> listMake = makeService.readAllMakes();
        
        //Assert
        assertEquals(listMake.size(),2);
        assertEquals(createdMake.getMakeName(), "Ford");
        assertEquals(createdMake.getMakeId(),  listMake.get(0).getMakeId());
    }

    /**
     * Test of updateMake method, of class MakeService.
     */
    @Test
    public void testUpdateMake() throws InvalidMakeException, DuplicateMakeException {
        //Arrange
        Make make = new Make();
        make.setMakeName("Ford");
        Make createdMake = makeService.createMake(make); 
        
        createdMake.setMakeName("Fords");
        
        //Act
        makeService.updateMake(createdMake);
        Make fetchedMake = makeService.readMake(createdMake.getMakeId());        
        
        //Assert
        assertEquals(fetchedMake.getMakeName(), createdMake.getMakeName());
    }

    @Test
    public void testCreateMakeTooLongName() throws DuplicateMakeException {
        //Arrange
        Make make = new Make();
        make.setMakeName("FordFordFordFordFordFord");
        
        //Act
        Make createdMake = null;
        
        try{
            createdMake = makeService.createMake(make);
            fail("Should have thrown InvalidMakeException");
        } catch(InvalidMakeException ex) {
            
        }
        assertNull(createdMake);
    }
    
    @Test
    public void testCreateMakeDuplicate() throws InvalidMakeException, DuplicateMakeException {
        //Arrange
        Make make = new Make();
        make.setMakeName("Ford");

        Make createMake = makeService.createMake(make);
        
        Make sameMake = new Make();
        sameMake.setMakeName("Ford");

        //Act
        Make createdSameMake = null;
        
        try{
            createdSameMake = makeService.createMake(sameMake);
            fail("Should have thrown DuplicateMakeException");
        } catch(DuplicateMakeException ex) {
            
        }
        assertNull(createdSameMake); 
    }
    
    @Test
    public void testCreateMakeNoName() throws InvalidMakeException, DuplicateMakeException {
        //Arrange
        Make make = new Make();
        make.setMakeName("Ford");

        Make createdMake = makeService.createMake(make);

        createdMake.setMakeName("");

        try{
           makeService.updateMake(createdMake);
            fail("Should have thrown InvalidMakeException");
        } catch (InvalidMakeException ex) {
            
        }
        Make fetchedMake = makeService.readMake(createdMake.getMakeId());
        assertEquals(fetchedMake.getMakeName(), "Ford");        
    }
    
    @Test
    public void testUpdateMakeTooLong() throws InvalidMakeException, DuplicateMakeException {
        //Arrange
        Make make = new Make();
        make.setMakeName("Ford");

        Make createdMake = makeService.createMake(make);

        createdMake.setMakeName("HondaHondaHondaHondaHonda");
        try{
            makeService.updateMake(createdMake);
            fail("Should have thrown InvalidMakeException");            
        } catch (InvalidMakeException ex) {
            
        }
        
        Make fetchedMake = makeService.readMake(createdMake.getMakeId());
        
        assertEquals(fetchedMake.getMakeName(), "Ford");        
    }
    
    @Test
    public void testUpdateMakeDuplicate() throws InvalidMakeException, DuplicateMakeException {
        //Arrange1
        Make make = new Make();
        make.setMakeName("Ford");

        Make createdMake = makeService.createMake(make);
        
        //Arrange2
        Make make2 = new Make();
        make2.setMakeName("Lexus");

        Make createdMake2 = makeService.createMake(make2);        
        
        //Change Ford to Lexus
        createdMake2.setMakeName("Ford");
        
        try {
            makeService.updateMake(createdMake2);
            fail("Should have thrown DuplicateMakeException");
        } catch (DuplicateMakeException ex) {
            
        }
        Make fetchedMake =makeService.readMake(createdMake2.getMakeId());
        assertEquals (fetchedMake.getMakeName(), "Lexus");
    
    }    
    
    
}
