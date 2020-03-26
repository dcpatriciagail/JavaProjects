/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Purchase;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface purchaseDao {

    //CRUD
    
    Purchase addPurchase(Purchase purchase);
    
    Purchase getPurchaseById (Integer id);
    
    void updatePurchase (Purchase purchase);
    
    void deletePurchaseById (Integer id);
    
    List <Purchase> readAllPurchase();    
    
    // need more methods
}
