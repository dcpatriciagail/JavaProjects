/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;


import com.sg.CarDealership.dto.Model;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface carModelDao {
    
    //CRUD
    
    Model addModel(Model model);
    
    Model getModelById (Integer id);
    
    void updateModel (Model model);
    
    List <Model> readAllModels();
        
    // need more methods
    
    List<Model> getAllModelsByMake(Integer makeId);
    
}
