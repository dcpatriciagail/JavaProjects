/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dao.purchaseDao;
import com.sg.CarDealership.dto.Purchase;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Patricia
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
    
    private purchaseDao purchaseDao;
    private VehicleService vehicleService;    

    public PurchaseServiceImpl(purchaseDao purchaseDao, VehicleService vehicleService) {
        this.purchaseDao = purchaseDao;
        this.vehicleService = vehicleService;
    }
    
    @Override
    public Purchase createPurchase(Purchase purchase) throws InvalidPurchaseException, DuplicateVINException {
        validatePurchase(purchase);
        purchase.setPurchaseDate(LocalDate.now());
        purchase.getVehicle().setInStock(false);
       try {
        vehicleService.updateVehicle(purchase.getVehicle());
       } catch (InvalidVehicleException ex) {
           
       }
        return purchaseDao.addPurchase(purchase);
    }

    @Override
    public Purchase getPurchaseById(Integer id) {
        return purchaseDao.getPurchaseById(id);
    }

    @Override
    public List<Purchase> readAllPurchase() {
        return purchaseDao.readAllPurchase();
    }
    
    private void validatePurchase(Purchase purchase) throws InvalidPurchaseException {
        if (
                purchase.getPurchasePrice().equals("") ||
                purchase.getPurchasePrice().compareTo(BigDecimal.ZERO) == -1 ||
                //purchase.getPurchasePrice().compareTo(purchase.getVehicle().getMsrp()) == 1 ||
                purchase.getPurchaseType().length()>20 ||
                purchase.getPurchaseType().equals("") ||
                purchase.getCustomerName().length()>50 ||
                purchase.getCustomerName().equals("")
                ) {
            throw new InvalidPurchaseException();
        }
        
        
//        if (purchase.getPurchasePrice().compareTo(purchase.getVehicle().getSalesPrice().multiply(new BigDecimal ("0.95")).setScale(2,RoundingMode.HALF_UP))  == 1) {
//            throw new InvalidPurchaseException();            
//        }
           
    }
   
}