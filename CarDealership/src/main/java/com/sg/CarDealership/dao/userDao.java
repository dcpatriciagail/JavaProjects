/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.User;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface userDao {

    //CRUD
    
    User addUser(User user);
    
    User getUserById (Integer id);
    
    void updateUser (User user);
    
    List <User> readAllUser();  
    
    // need more methods
}
