/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dto.Contact;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface ContactService {
    
    Contact createContact (Contact contact) throws InvalidContactException;
    
    Contact readContact (Integer contactID);
    
    List <Contact> readAllContacts();
    
    //validate email & phone number & not blank 
    
    
}
