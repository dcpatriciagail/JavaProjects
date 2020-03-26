/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dto.Purchase;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface PurchaseService {
    
    Purchase createPurchase(Purchase purchase) throws InvalidPurchaseException, DuplicateVINException;
    
    Purchase getPurchaseById (Integer id);
    
    List <Purchase> readAllPurchase();
    
}
