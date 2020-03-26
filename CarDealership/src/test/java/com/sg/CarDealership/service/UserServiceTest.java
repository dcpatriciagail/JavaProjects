/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dao.userDao;
import com.sg.CarDealership.dto.User;
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
public class UserServiceTest {
    
    @Autowired
    UserService userService;
    
    
    public UserServiceTest() {
    }
    

    /**
     * Test of createUser method, of class UserService.
     */
    @Test
    public void testCreateUser() throws InvalidUserException {
        //Arrange
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        
        //Act
        User createdUser = userService.createUser(user);
        User fetchedUser = userService.readUser(createdUser.getUserId());
        
        //Assert
        assertEquals(fetchedUser.getUserId(), createdUser.getUserId());
        assertEquals(fetchedUser.getEmail(), "user@email.com");
        assertEquals(fetchedUser.getFirstName(), "first");
                
    }

    /**
     * Test of readUser method, of class UserService.
     */
    @Test
    public void testReadUser() throws InvalidUserException {
        //Arrange
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createdUser = userService.createUser(user);
             

        //Act
        User fetchedUser = userService.readUser(createdUser.getUserId());
        
        //Assert
        assertEquals(fetchedUser.getUserId(), createdUser.getUserId());
        assertEquals(fetchedUser.getEmail(), "user@email.com");
        assertEquals(fetchedUser.getFirstName(), "first");    
    
    }

    /**
     * Test of readAllUsers method, of class UserService.
     */
    @Test
    public void testReadAllUsers() throws InvalidUserException {
        //Arrange user 1
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createdUser = userService.createUser(user);  

        //Arrange user 2        
        User user2 = new User();
        user2.setEmail("user2@email.com");
        user2.setFirstName("second");
        user2.setLastName("second last");
        user2.setPassword("pass");
        user2.setRole("admin");
        User createUser2 = userService.createUser(user2);         
        
        //Act
        List<User> listUser = userService.readAllUsers();
        
        //Assert
        assertEquals(listUser.size(),2);
        assertEquals(createdUser.getFirstName(), "first");
        assertEquals(createdUser.getUserId(),  listUser.get(0).getUserId());
    }

    @Test
    public void testNotEmpty() {
        //Arrange
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        
        User createdUser= null;
        //Act
        try{
            createdUser = userService.createUser(user);    
            fail("Should have thrown InvalidUserException.");
        } catch (InvalidUserException ex) {
            
        }
        assertNull(createdUser);
        
    }   
    
    @Test
    public void AnyFieldLonger50FirstName() {
        //Arrange
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("firstfirstfirstfirstfirstfirstfirstfirstfirstfirstfirst");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        
        //Act
        User createdUser = null;
        try {
            createdUser = userService.createUser(user);
            fail("Should have thrown InvalidUserException.");
        } catch (InvalidUserException ex) {
            
        }     
        assertNull(createdUser);
    }    
    
    @Test
    public void AnyFieldLonger50LastName() {
        //Arrange
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("lastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlast");
        user.setPassword("pass");
        user.setRole("admin");
        
        //Act
        User createdUser = null;
        try {
            createdUser = userService.createUser(user);
            fail("Should have thrown InvalidUserException.");
        } catch (InvalidUserException ex) {
            
        }     
        assertNull(createdUser);
    }        

    @Test
    public void AnyFieldLonger2Password() {
        //Arrange
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("passpasspasspasspass");
        user.setRole("admin");
        
        //Act
        User createdUser = null;
        try {
            createdUser = userService.createUser(user);
            fail("Should have thrown InvalidUserException.");
        } catch (InvalidUserException ex) {
            
        }     
        assertNull(createdUser);
    }      

    @Test
    public void testEmail() {
        //Arrange
        User user = new User();
        user.setEmail("email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("passpass");
        user.setRole("admin");
        
        //Act
        User createdUser = null;
        try {
            createdUser = userService.createUser(user);
            fail("Should have thrown InvalidUserException.");
        } catch (InvalidUserException ex) {}     
        assertNull(createdUser);
    }  
    
    @Test
    public void testEmailLongerThan50() {
        //Arrange
        User user = new User();
        user.setEmail("emailemailemailemailemailemailemailemailemailemailrandom@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("passpass");
        user.setRole("admin");
        
        //Act
        User createdUser = null;
        try {
            createdUser = userService.createUser(user);
            fail("Should have thrown InvalidUserException.");
        } catch (InvalidUserException ex) {}     
        
        assertNull(createdUser);
    }      
    

    @Test
    public void testUpdateUserFirstNameLongerThan50() throws InvalidUserException {
        //Arrange
        User user = new User();
        user.setEmail("email@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("passpass");
        user.setRole("admin");
        User createdUser = userService.createUser(user);
        
        createdUser.setFirstName("lastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlast");
    
        try {
            userService.updateUser(createdUser);
            fail("Should have thrown InvalidUserException.");            
        } catch (InvalidUserException ex) {}
        
        User fetchedUser = userService.readUser(createdUser.getUserId());
        
        assertEquals(fetchedUser.getFirstName(), "first");
    }
 
    @Test
    public void testUpdateUserEmailLongerThan50() throws InvalidUserException {
        //Arrange
        User user = new User();
        user.setEmail("email@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createdUser = userService.createUser(user);
        
        createdUser.setEmail("emailemailemailemailemailemailemailemailemailemailemailemailemail@email.com");
    
        try {
            userService.updateUser(createdUser);
            fail("Should have thrown InvalidUserException.");            
        } catch (InvalidUserException ex) {}
        
        User fetchedUser = userService.readUser(createdUser.getUserId());
        
        assertEquals(fetchedUser.getEmail(), "email@email.com");
    }    
    @Test
    public void testUpdateUserLastNameLongerThan50() throws InvalidUserException {
        //Arrange
        User user = new User();
        user.setEmail("email@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createdUser = userService.createUser(user);
        
        createdUser.setLastName("lastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlastlast");
    
        try {
            userService.updateUser(createdUser);
            fail("Should have thrown InvalidUserException.");            
        } catch (InvalidUserException ex) {}
        
        User fetchedUser = userService.readUser(createdUser.getUserId());
        
        assertEquals(fetchedUser.getLastName(), "last");
    }    
    
    
    //updateanyfieldsblank
    //namelongerthan50
    //updatepasswordtoolong
}
