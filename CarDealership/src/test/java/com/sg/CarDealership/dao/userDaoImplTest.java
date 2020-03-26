/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Make;
import com.sg.CarDealership.dto.User;
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
public class userDaoImplTest {
    
    @Autowired
    userDao userDao;
    
    
    public userDaoImplTest() {
    }

    /**
     * Test of addUser method, of class userDaoImpl.
     */
    @Test
    public void testAddUser() {
        //Arrange
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        
        //Act
        User createUser = userDao.addUser(user);        
        User fetchedUser = userDao.getUserById(createUser.getUserId());
        
        //Assert
        assertEquals(fetchedUser.getUserId(), createUser.getUserId());
        assertEquals(fetchedUser.getEmail(), "user@email.com");
        assertEquals(fetchedUser.getFirstName(), "first");
        
    }

    /**
     * Test of getCategoryById method, of class userDaoImpl.
     */
    @Test
    public void testgetUserById() {
        //Arrange
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = userDao.addUser(user);        

        //Act
        User fetchedUser = userDao.getUserById(createUser.getUserId());
        
        //Assert
        assertEquals(fetchedUser.getUserId(), createUser.getUserId());
        assertEquals(fetchedUser.getEmail(), "user@email.com");
        assertEquals(fetchedUser.getFirstName(), "first");
        
    
    }

    /**
     * Test of updateUser method, of class userDaoImpl.
     */
    @Test
    public void testUpdateUser() {
        //Arrange 
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = userDao.addUser(user);         
        
        createUser.setPassword("password");
        
        //Act
        userDao.updateUser(createUser);
        User fetchedUser = userDao.getUserById(createUser.getUserId());
        
        //Assert
        assertEquals(fetchedUser.getUserId(), createUser.getUserId());
        assertEquals(fetchedUser.getPassword(), createUser.getPassword());        
    }


    /**
     * Test of readAllUser method, of class userDaoImpl.
     */
    @Test
    public void testReadAllUser() {
        //Arrange user 1
        User user = new User();
        user.setEmail("user@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("pass");
        user.setRole("admin");
        User createUser = userDao.addUser(user);  

        //Arrange user 2        
        User user2 = new User();
        user2.setEmail("user2@email.com");
        user2.setFirstName("second");
        user2.setLastName("second last");
        user2.setPassword("pass");
        user2.setRole("admin");
        User createUser2 = userDao.addUser(user2);         
        
        //Act
        List<User> listUser = userDao.readAllUser();
        
        //Assert
        assertEquals(listUser.size(),2);
        assertEquals(createUser.getFirstName(), "first");
        assertEquals(createUser.getUserId(),  listUser.get(0).getUserId());        
    }
   
    
    
}
