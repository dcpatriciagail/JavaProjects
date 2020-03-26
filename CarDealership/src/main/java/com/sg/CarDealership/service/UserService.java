/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dto.User;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface UserService {

    User createUser (User user) throws InvalidUserException;
    
    User readUser (Integer userID);
    
    List <User> readAllUsers();    
    
    void updateUser (User user) throws InvalidUserException ;
    
}
