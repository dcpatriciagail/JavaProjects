/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Vehicle;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface vehicleDao {
    
    //CRUD
    
    Vehicle addVehicle(Vehicle vehicle);
    
    Vehicle getVehicleById (Integer id);
    
    void updateVehicle (Vehicle vehicle);
    
    void deleteVehicleById (Integer id);
    
    List <Vehicle> readAllVehicle();    
    
    
    public List<Vehicle> searchForVehicle(String vehicleSearch, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice);
    
    List<Vehicle> searchForFeatured();
    
    List<Vehicle> searchForNewVehicle(String makeOrModel, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice);

    List<Vehicle> searchForUsedVehicle(String makeOrModel, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice);

    public List<Vehicle> seeInStockVehicles(String makeOrModel, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice);
    
}
