/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.makeDaoImpl.MakeMapper;
import com.sg.CarDealership.dto.Model;
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
public class carModelDaoImpl implements carModelDao {

    private final JdbcTemplate jdbc;   

    public carModelDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Model addModel(Model model) {
        String query = "INSERT INTO Models (modelName, makeId) VALUES (?,?)";
        jdbc.update(query,
                    model.getModelName(),
                    model.getMake().getMakeId());
        Integer newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setModelId(newId);
        return model;
    }

    @Override
    public Model getModelById(Integer id) {
        Model model = null;
        String query = "SELECT * FROM Models WHERE modelID = ?";
        try {
            model = jdbc.queryForObject(query,new CarModelMapper(), id);
            model.setMake(getMakeForModel(model));
        } catch(EmptyResultDataAccessException ex){}
        
        return model;
    }
    private Make getMakeForModel(Model model) {
        String query = "SELECT m.* FROM Makes m "+
                       "INNER JOIN Models cm ON m.makeId = cm.makeId "+
                       "WHERE modelId = ?";
        return jdbc.queryForObject(query,new MakeMapper(),model.getModelId());
    }
    
    @Override
    public void updateModel(Model model) {
        String query = "UPDATE Models SET modelID = ?, modelName = ?, makeID = ? WHERE modelID = ?";
        jdbc.update(query,
                    model.getModelId(),
                    model.getModelName(),
                    model.getMake().getMakeId(),
                    model.getModelId());
    }

    @Override
    public List<Model> readAllModels() {
       String query = "SELECT * FROM Models";
        
        List<Model> models = jdbc.query(query,new CarModelMapper());
        
        for(Model model : models) {
            model.setMake(getMakeForModel(model));
        }
        
        return models;
    }

    @Override
    public List<Model> getAllModelsByMake(Integer makeId) {
       String query = "SELECT * FROM Models WHERE makeID = ?";
       List<Model> models = jdbc.query(query, new CarModelMapper(), makeId);

       for(Model model : models) {
           model.setMake(getMakeForModel(model));
       }

       return models;
    }



    public static class CarModelMapper implements RowMapper<Model>{

        @Override
        public Model mapRow (ResultSet rs, int i) throws SQLException {
            Model model = new Model();
            model.setModelId(rs.getInt("modelID"));
            model.setModelName(rs.getString("modelName"));

            return model;
        }
    }      
    
    
    
    
}
