/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Patricia
 */
@Repository
public class contactDaoImpl implements contactDao{
    private final JdbcTemplate jdbc;

    public contactDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Contact addContact(Contact contact) {
        String query = "INSERT INTO Contacts (firstName, lastName, email, phone, message) VALUES"
                + " (?,?,?,?,?)";
        
        jdbc.update(query, 
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getMessage());

        Integer newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        contact.setContactID(newId);
        return contact;
    }

    @Override
    public Contact getContactById(Integer id) {
        Contact contact = null;
        String query = "SELECT * FROM Contacts WHERE contactID = ?";
        try {
            contact = jdbc.queryForObject(query,new ContactMapper(), id);

        } catch(EmptyResultDataAccessException ex){}
        
        return contact;   
    }

    @Override
    public List<Contact> readAllContact() {

        String query = "SELECT * FROM Contacts";
        
        List<Contact> contact = jdbc.query(query, new ContactMapper());
        return contact;      
    }
    
    
    private static class ContactMapper implements RowMapper<Contact>{

        @Override
        public Contact mapRow (ResultSet rs, int i) throws SQLException {
            Contact contact = new Contact();
            
            contact.setContactID(rs.getInt("contactID"));
            contact.setFirstName(rs.getString("firstName"));
            contact.setLastName(rs.getString("lastName"));
            contact.setEmail(rs.getString("email"));
            contact.setPhone(rs.getString("phone"));
            contact.setMessage(rs.getString("message"));

            return contact;
        }
    }       
}
