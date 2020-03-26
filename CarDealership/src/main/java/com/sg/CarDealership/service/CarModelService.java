/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dto.Model;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface CarModelService {
    
    Model createModel (Model model) throws InvalidModelException;
    
    Model readModel (Integer modelID);  
    
    List<Model> readAllModels();
    
    void updateModel (Model model) throws InvalidModelException;
 
    List<Model> getAllModelsByMake(Integer makeId);
}
