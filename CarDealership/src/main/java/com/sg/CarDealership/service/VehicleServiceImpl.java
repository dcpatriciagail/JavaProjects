/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dao.vehicleDao;
import com.sg.CarDealership.dto.Vehicle;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Patricia
 */

@Service
public class VehicleServiceImpl implements VehicleService{
    
    private vehicleDao vehicleDao;

    public VehicleServiceImpl(vehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) throws InvalidVehicleException{
        validateVehicle(vehicle);
        return vehicleDao.addVehicle(vehicle);
    }

    @Override
    public Vehicle readVehicle(Integer vehicleID) {
        return vehicleDao.getVehicleById(vehicleID);
    }

    @Override
    public List<Vehicle> readAllVehicles() {
        return vehicleDao.readAllVehicle();
    }

    @Override
    public void updateVehicle(Vehicle vehicle) throws InvalidVehicleException {
        vehicleDao.updateVehicle(vehicle);
    }

    @Override
    public void deleteVehicle(Integer vehicle) {
                vehicleDao.deleteVehicleById(vehicle);
    }
    
    //search method
    
    private void validateVehicle(Vehicle vehicle) throws InvalidVehicleException{
        if(vehicle.getModel().equals("") ||
                vehicle.getYear()<= 2000 || 
                vehicle.getTransmission().equals("") ||
                vehicle.getTransmission().length()>20 ||
                vehicle.getMileage()>Integer.MAX_VALUE-1 ||
                vehicle.getMileage()<0 ||
                vehicle.getColor().equals("") ||
                vehicle.getColor().length()>20 ||
                vehicle.getInterior().equals("") ||
                vehicle.getInterior().length()>20 ||
                vehicle.getBodyType().equals("") ||
                vehicle.getBodyType().length()>20 ||                
                vehicle.getVin().equals("") ||
                vehicle.getVin().length()>16 ||                 
                vehicle.getSalesPrice().equals("") ||
                vehicle.getSalesPrice().compareTo(vehicle.getMsrp())==1 ||                  
                vehicle.getMsrp().equals("") ||
                vehicle.getMsrp().compareTo(vehicle.getSalesPrice()) == -1 ||
                vehicle.getDescription().equals("") ||
                vehicle.getDescription().length()>1500
                ){
            throw new InvalidVehicleException();
        }      

        if(vehicle.getSalesPrice().compareTo(BigDecimal.ZERO) == -1 ||
                vehicle.getMsrp().compareTo(BigDecimal.ZERO) == -1){
            throw new InvalidVehicleException();
        }
        
    }
   
    
    //Search
    @Override
    public List<Vehicle> searchVehicle (String vehicleSearch, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice) {
        return vehicleDao.searchForVehicle(vehicleSearch, minYear, maxYear, minPrice, maxPrice);
    }    

    @Override
    public List<Vehicle> searchForFeatured() {
        return vehicleDao.searchForFeatured();
    }

    @Override
    public List<Vehicle> searchForNewVehicle(String makeOrModel, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice) {
        return vehicleDao.searchForNewVehicle(makeOrModel, minYear, maxYear, minPrice, maxPrice);
    }

    @Override
    public List<Vehicle> searchForUsedVehicle(String makeOrModel, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice) {
        return vehicleDao.searchForUsedVehicle(makeOrModel, minYear, maxYear, minPrice, maxPrice);
    }

    @Override
    public List<Vehicle> seeInStockVehicles(String makeOrModel, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice) {
        return vehicleDao.seeInStockVehicles(makeOrModel, minYear, maxYear, minPrice, maxPrice);
    }
    
    
}
