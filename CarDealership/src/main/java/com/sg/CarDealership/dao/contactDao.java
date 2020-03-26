/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Contact;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface contactDao {

    //CRUD
    
    Contact addContact(Contact contact);
    
    Contact getContactById (Integer id);
    
    List <Contact> readAllContact();    
    
}
