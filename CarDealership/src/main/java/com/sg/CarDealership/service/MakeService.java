/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dto.Make;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface MakeService {
    
    
    Make createMake (Make make) throws InvalidMakeException, DuplicateMakeException;
    
    Make readMake (Integer makeID);
    
    List<Make> readAllMakes();
    
    void updateMake (Make make)throws InvalidMakeException, DuplicateMakeException;
    
}
