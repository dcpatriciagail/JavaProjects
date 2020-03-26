/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dao.contactDao;
import com.sg.CarDealership.dto.Contact;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Patricia
 */
@Service
public class ContactServiceImpl implements ContactService{

    private contactDao contactDao;
    
    @Autowired
    public ContactServiceImpl(contactDao contactDao) {
        this.contactDao = contactDao;
    }
        
        
    @Override
    public Contact createContact(Contact contact) throws InvalidContactException{
        validateContact(contact);
        return  contactDao.addContact(contact);    }

    @Override
    public Contact readContact(Integer contactID) {
        return contactDao.getContactById(contactID);
    }

    @Override
    public List<Contact> readAllContacts() {
        return contactDao.readAllContact();
    }
    
    private void validateContact(Contact contact) throws InvalidContactException {
        
        if(contact.getFirstName().equals("") || contact.getLastName().equals("") ||
           contact.getEmail().equals("") || contact.getPhone().equals("")) {
            throw new InvalidContactException();
        }
        
        String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        if(!contact.getEmail().matches(emailRegex)) {
            throw new InvalidContactException();
        }
        
        if(contact.getEmail().length()>50 || contact.getFirstName().length()>50 || 
           contact.getLastName().length()>50 || 
           contact.getPhone().length() > 13 ||
           contact.getMessage().length()>500) {
            throw new InvalidContactException(); 
        }
        
        if(!validatePhone(contact.getPhone())){
            throw new InvalidContactException();
        }
    }
    
    private boolean validatePhone(String phone) {
        //validate phone numbers of format "1234567890"
        if (phone.matches("\\d{10}")) {
            return true;
        } //validating phone number with -, . or spaces
        else if (phone.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            return true;
        }  //validating phone number where area code is in braces ()
        else if (phone.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            return true;
        } //return false if nothing matches the input
        else {
            return false;
        }
    }
}
