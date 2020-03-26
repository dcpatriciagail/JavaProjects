/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dao.userDao;
import com.sg.CarDealership.dto.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Patricia
 */
@Service
public class UserServiceImpl implements UserService {
    
    private userDao userDao;

    @Autowired  
    public UserServiceImpl(userDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User createUser(User user) throws InvalidUserException {
        validateUser(user);
        return userDao.addUser(user);
    }

    @Override
    public User readUser(Integer userID) {
        return userDao.getUserById(userID);
    }

    @Override
    public List<User> readAllUsers() {
        return userDao.readAllUser();
    }

    @Override
    public void updateUser(User user)throws InvalidUserException  {
        validateUser(user);
        userDao.updateUser(user);
    }

    private void validateUser(User user) throws InvalidUserException {
        if(user.getEmail().length()>50 ||
                user.getFirstName().length()>50 ||
                user.getLastName().length()>50 ||
                user.getPassword().length()>16 ||
                user.getRole().length()>10 ||
                user.getEmail().equals("") ||
                user.getFirstName().equals("") ||
                user.getLastName().equals("") ||
                user.getPassword().equals("") ||
                user.getRole().equals("")){
             throw new InvalidUserException();
        }
        
        String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(!user.getEmail().matches(emailRegex)) {
            throw new InvalidUserException();
        }
    
    
    
    }
    
    
}
