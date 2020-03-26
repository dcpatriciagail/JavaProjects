/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Make;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface makeDao {
    
        //CRUD
    
    Make addMake(Make make);
    
    Make getMakeById (Integer id);
    
    void updateMake (Make category);
    
    List <Make> readAllMake();
    
    
}
