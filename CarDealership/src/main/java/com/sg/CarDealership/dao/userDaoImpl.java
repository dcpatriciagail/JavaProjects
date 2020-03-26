/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.User;
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
public class userDaoImpl implements userDao{
    
    private final JdbcTemplate jdbc;     

    public userDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public User addUser(User user) {
        String query = "INSERT INTO Users (email, firstName, lastName, password, role) VALUES "
                + "(?,?,?,?,?)";
        
        jdbc.update(query,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getRole());
        
        Integer newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setUserId(newId);
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        User user = null;
        String query = "SELECT * FROM Users WHERE userID = ?";
        try {
            user = jdbc.queryForObject(query,new UserMapper(), id);

        } catch(EmptyResultDataAccessException ex){}
        
        return user;        
        
    }

    @Override
    public void updateUser(User user) {
        String query = "UPDATE Users SET userID = ?, email = ?, firstName = ?, lastName = ?,"
                + " password = ?, role = ? WHERE userID = ?";
        jdbc.update(query,
                user.getUserId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getRole(),
                user.getUserId());
    }

    @Override
    public List<User> readAllUser() {
        String query = "SELECT * FROM Users";
        
        List<User> users = jdbc.query(query, new UserMapper());
        return users;        
    }
    
    public static class UserMapper implements RowMapper<User>{

        @Override
        public User mapRow (ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("userID"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            
            return user;
        }
    }      
}
