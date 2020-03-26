/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dto.Contact;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Patricia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback
@Transactional 
public class ContactServiceTest {
    
    @Autowired
    ContactService contactService;
    
    public ContactServiceTest() {
    }
    

    /**
     * Test of createContact method, of class ContactService.
     */
    @Test
    public void testCreateContact() throws InvalidContactException {
        //Arrange
        Contact contact = new Contact();
        contact.setFirstName("first");
        contact.setLastName("last");
        contact.setEmail("mail@email.com");
        contact.setPhone("444-444-4444");
        contact.setMessage("my message");
        
        //Act
        Contact createContact = contactService.createContact(contact);
        Contact fetchedContact = contactService.readContact(createContact.getContactID());
        
        //Assert
        assertEquals (fetchedContact.getContactID(), createContact.getContactID());
        assertEquals (fetchedContact.getEmail(), "mail@email.com");
        assertEquals (fetchedContact.getFirstName(), "first");
        assertEquals (fetchedContact.getLastName(), "last");
        assertEquals (fetchedContact.getPhone(), "444-444-4444");
        assertEquals (fetchedContact.getMessage(), "my message");  
    }

    /**
     * Test of readContact method, of class ContactService.
     */
    @Test
    public void testReadContact() throws InvalidContactException {
        //Arrange
        Contact contact = new Contact();
        contact.setFirstName("first");
        contact.setLastName("last");
        contact.setEmail("mail@email.com");
        contact.setPhone("444-444-4444");
        contact.setMessage("my message");
        Contact createContact = contactService.createContact(contact);
        
        //Act        
        Contact fetchedContact = contactService.readContact(createContact.getContactID());
        
        //Assert
        assertEquals (fetchedContact.getContactID(), createContact.getContactID());
        assertEquals (fetchedContact.getEmail(), "mail@email.com");
        assertEquals (fetchedContact.getFirstName(), "first");
        assertEquals (fetchedContact.getLastName(), "last");
        assertEquals (fetchedContact.getPhone(), "444-444-4444");
        assertEquals (fetchedContact.getMessage(), "my message");  
    }

    @Test
    public void testNotNull() {
        //Arrange
        Contact contact = new Contact();
        contact.setFirstName("first");
        contact.setLastName("");
        contact.setEmail("mail@email.com");
        contact.setPhone("444-444-4444");
        contact.setMessage("my message");
        Contact createdContact = null;
        //Act
        try{
            createdContact = contactService.createContact(contact);    
            fail("Should have thrown InvalidContactException.");
        } catch (InvalidContactException ex) {
            
        }
        assertNull(createdContact);
        
    }
    
    @Test
    public void testEmail() {
        //Arrange
        Contact contact = new Contact();
        contact.setFirstName("first");
        contact.setLastName("last");
        contact.setEmail("email.com");
        contact.setPhone("444-444-4444");
        contact.setMessage("my message");
        Contact createdContact = null;
        //Act
        try{
            createdContact = contactService.createContact(contact);    
            fail("Should have thrown InvalidContactException.");
        } catch (InvalidContactException ex) {
            
        }
        assertNull(createdContact);
        
    }    
    
    @Test
    public void testPhone() {
        //Arrange
        Contact contact = new Contact();
        contact.setFirstName("first");
        contact.setLastName("last");
        contact.setEmail("email@gmail.com");
        contact.setPhone("444-aaa-4444");
        contact.setMessage("my message");
        Contact createdContact = null;
        //Act
        try{
            createdContact = contactService.createContact(contact);    
            fail("Should have thrown InvalidContactException.");
        } catch (InvalidContactException ex) {
            
        }
        assertNull(createdContact);
        
    }      
    
    @Test
    public void AnyFieldLonger50() {
        //Arrange
        Contact contact = new Contact();
        contact.setFirstName("firstfirstfirstfirstfirstfirstfirstfirstfirstfirstfirst");
        contact.setLastName("last");
        contact.setEmail("mail@email.com");
        contact.setPhone("444-444-4444");
        contact.setMessage("my message");

        Contact createdContact = null;
        //Act      
        try{
            createdContact = contactService.createContact(contact);
            fail("Should have thrown InvalidContactException.");
        } catch(InvalidContactException ex)    {   
        }
        //Assert
        assertNull(createdContact);
    }
    
}
