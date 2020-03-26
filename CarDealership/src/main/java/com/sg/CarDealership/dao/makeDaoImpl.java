/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Make;
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
public class makeDaoImpl implements makeDao{
    
    private final JdbcTemplate jdbc;   

    public makeDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Make addMake(Make make) {
        String query = "INSERT INTO Makes (makeName) VALUES (?)";
        jdbc.update(query,make.getMakeName());
        Integer newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        make.setMakeId(newId);
        return make;    }

    @Override
    public Make getMakeById(Integer id) {
        Make make = null;
        String query = "SELECT * FROM Makes WHERE makeID = ?";
        try {
            make = jdbc.queryForObject(query,new MakeMapper(), id);

        } catch(EmptyResultDataAccessException ex){}
        
        return make;        
    }

    @Override
    public void updateMake(Make category) {
        String query = "UPDATE Makes SET makeID = ?, makeName = ? WHERE makeID = ?";
        jdbc.update(query,
                category.getMakeId(),
                category.getMakeName(),
                category.getMakeId());
    }

    @Override
    public List<Make> readAllMake() {
        String query = "SELECT * FROM Makes";
        
        List<Make> makes = jdbc.query(query, new MakeMapper());
        return makes;
    }
    
    public static class MakeMapper implements RowMapper<Make>{

        @Override
        public Make mapRow (ResultSet rs, int i) throws SQLException {
            Make make = new Make();
            make.setMakeId(rs.getInt("makeID"));
            make.setMakeName(rs.getString("makeName"));
            return make;
        }
    }    
}
