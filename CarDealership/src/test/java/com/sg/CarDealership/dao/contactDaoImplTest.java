/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Contact;
import java.util.List;
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
public class contactDaoImplTest {
    
    @Autowired
    contactDao contactDao;
    
    public contactDaoImplTest() {
    }

    /**
     * Test of addContact method, of class contactDaoImpl.
     */
    @Test
    public void testAddContact() {
        //Arrange
        Contact contact = new Contact();
        contact.setFirstName("first");
        contact.setLastName("last");
        contact.setEmail("mail@email.com");
        contact.setPhone("444-444-4444");
        contact.setMessage("my message");
        
        //Act
        Contact createContact = contactDao.addContact(contact);
        Contact fetchedContact = contactDao.getContactById(createContact.getContactID());
        
        //Assert
        assertEquals (fetchedContact.getContactID(), createContact.getContactID());
        assertEquals (fetchedContact.getEmail(), "mail@email.com");
        assertEquals (fetchedContact.getFirstName(), "first");
        assertEquals (fetchedContact.getLastName(), "last");
        assertEquals (fetchedContact.getPhone(), "444-444-4444");
        assertEquals (fetchedContact.getMessage(), "my message");        
    }

    /**
     * Test of getContactById method, of class contactDaoImpl.
     */
    @Test
    public void testGetContactById() {
        //Arrange
        Contact contact = new Contact();
        contact.setFirstName("first");
        contact.setLastName("last");
        contact.setEmail("mail@email.com");
        contact.setPhone("444-444-4444");
        contact.setMessage("my message");
        Contact createContact = contactDao.addContact(contact);
        
        //Act        
        Contact fetchedContact = contactDao.getContactById(createContact.getContactID());
        
        //Assert
        assertEquals (fetchedContact.getContactID(), createContact.getContactID());
        assertEquals (fetchedContact.getEmail(), "mail@email.com");
        assertEquals (fetchedContact.getFirstName(), "first");
        assertEquals (fetchedContact.getLastName(), "last");
        assertEquals (fetchedContact.getPhone(), "444-444-4444");
        assertEquals (fetchedContact.getMessage(), "my message");        
            
    }

    /**
     * Test of readAllContact method, of class contactDaoImpl.
     */
    @Test
    public void testReadAllContact() {
        //Arrange contact 1
        Contact contact = new Contact();
        contact.setFirstName("first");
        contact.setLastName("last");
        contact.setEmail("mail@email.com");
        contact.setPhone("444-444-4444");
        contact.setMessage("my message");
        Contact createContact = contactDao.addContact(contact);
        
        //Arrange contact 2
        Contact contact2 = new Contact();
        contact2.setFirstName("seonnd");
        contact2.setLastName("second last");
        contact2.setEmail("mail2@email.com");
        contact2.setPhone("212-444-4444");
        contact2.setMessage("my second message");
        Contact createContact2 = contactDao.addContact(contact2);
    
        //Act
        List<Contact>  listContact = contactDao.readAllContact();
        
        //Assert
        assertEquals(listContact.size(),2);
        assertEquals(createContact.getContactID(), listContact.get(0).getContactID());
        assertEquals(createContact2.getContactID(), listContact.get(1).getContactID());        
    
    }
    
}
