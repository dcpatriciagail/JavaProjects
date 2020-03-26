/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.carModelDaoImpl.CarModelMapper;
import com.sg.CarDealership.dao.makeDaoImpl.MakeMapper;
import com.sg.CarDealership.dto.Model;
import com.sg.CarDealership.dto.Make;
import com.sg.CarDealership.dto.Vehicle;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
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
public class vehicleDaoImpl implements vehicleDao{

    private final JdbcTemplate jdbc;

    public vehicleDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO Vehicles(modelID, year, transmission, "
                + "mileage, color, interior, bodyStyle, VIN, salesPrice, msrp, "
                + "description, used, featured, special, inStock, picURL)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        jdbc.update(query, 
                vehicle.getModel().getModelId(),
                vehicle.getYear(),
                vehicle.getTransmission(),
                vehicle.getMileage(),
                vehicle.getColor(),
                vehicle.getInterior(),
                vehicle.getBodyType(),
                vehicle.getVin(),
                vehicle.getSalesPrice(),
                vehicle.getMsrp(),
                vehicle.getDescription(),
                vehicle.isUsed(),
                vehicle.isFeatured(),
                vehicle.isSpecial(),
                vehicle.isInStock(),
                vehicle.getPicURL());
        
        Integer newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        vehicle.setVehicleId(newId);
        
        return vehicle;
    }

    @Override
    public Vehicle getVehicleById(Integer id) {
       Vehicle vehicle = null;
       String query = "SELECT * FROM Vehicles WHERE vehicleID = ?";
       try {
           vehicle = jdbc.queryForObject(query, new VehicleMapper(), id);
           vehicle.setModel(getModelForVehicle(vehicle));
           vehicle.getModel().setMake(getMakeForVehicle(vehicle));
       } catch (EmptyResultDataAccessException ex) {
       }
       return vehicle;
   }

   private Model getModelForVehicle(Vehicle vehicle) {
       String query = "SELECt cm.* FROM Models cm " +
               "INNER JOIN Vehicles v ON cm.modelID = v.modelID "+
               "WHERE v.vehicleID = ?";

       return jdbc.queryForObject(query, new CarModelMapper(), vehicle.getVehicleId());
   }    

    private Make getMakeForVehicle(Vehicle vehicle) {
        String query = "SELECT m.* FROM Makes m "+
                       "INNER JOIN Models cm ON m.makeID = cm.makeID "+
                       "INNER JOIN Vehicles v ON cm.modelID = v.modelID "+
                       "WHERE v.vehicleID = ?";
        return jdbc.queryForObject(query, new MakeMapper(), vehicle.getVehicleId());
    }   
   
   
    @Override
    public void updateVehicle(Vehicle vehicle) {
        final String query = "UPDATE Vehicles SET "
               + "vehicleID = ?,"
               + "modelID = ?,"
               + "year = ?,"
               + "transmission = ?,"
               + "mileage = ?,"
               + "color = ?,"
               + "interior = ?,"
               + "bodyStyle = ?,"
               + "VIN = ?,"
               + "salesPrice = ?,"
               + "msrp = ?,"
               + "description = ?,"
               + "used = ?,"
               + "featured = ?,"
               + "special = ?,"
               + "inStock = ?,"
               + "picURL = ?"
               + "WHERE vehicleID = ?";

       jdbc.update(query, vehicle.getVehicleId(),
               vehicle.getModel().getModelId(),
               vehicle.getYear(),
               vehicle.getTransmission(),
               vehicle.getMileage(),
               vehicle.getColor(),
               vehicle.getInterior(),
               vehicle.getBodyType(),
               vehicle.getVin(),
               vehicle.getSalesPrice(),
               vehicle.getMsrp(),
               vehicle.getDescription(),
               vehicle.isUsed(),
               vehicle.isFeatured(),
               vehicle.isSpecial(),
               vehicle.isInStock(),
               vehicle.getPicURL(),
               vehicle.getVehicleId());
    }

    @Override
    public void deleteVehicleById(Integer id) {
        final String query = "DELETE FROM Vehicles WHERE vehicleID = ?";
        jdbc.update(query, id);
    }

    @Override
    public List<Vehicle> readAllVehicle() {
       String query = "SELECT * FROM Vehicles";
       List<Vehicle> vehicles = jdbc.query(query, new VehicleMapper());
       for (Vehicle vehicle : vehicles) {
           vehicle.setModel(getModelForVehicle(vehicle));
           vehicle.getModel().setMake(getMakeForVehicle(vehicle));
       }
       return vehicles;//the list of cars
    }

@Override
    public List<Vehicle> searchForVehicle(String vehicleSearch, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice) {
        String BASE_QUERY = "SELECT v.* FROM Vehicles v " +
                            "JOIN Models cm ON v.modelID = cm.modelID " +
                            "JOIN Makes m ON cm.makeID = m.makeID " +
                            "WHERE v.used = FALSE " +
                            "AND v.inStock = TRUE ";
        
        String VEHICLE_SEARCH_QUERY = " (m.makeName LIKE ? OR cm.modelName LIKE ?) ";
        String YEAR_QUERY = " (v.year BETWEEN ? AND ?) ";
        String PRICE_QUERY = " (v.salesPrice BETWEEN ? AND ?) ";
        String TRUE_QUERY = BASE_QUERY;
        
        List<Vehicle> allVehicles = new ArrayList<>();
        List<String> searchParams = new ArrayList<>();
        
        // Dynamically add vehicle
        if (!vehicleSearch.equals("") && vehicleSearch != null) {
            TRUE_QUERY += "AND " + VEHICLE_SEARCH_QUERY;
            
            // Add to params array
            searchParams.add(vehicleSearch + "%");
            searchParams.add(vehicleSearch + "%");
        }
        
        // Dynamically add year
        if (minYear != null || maxYear != null) { // If minYear & maxYear both arent null
            if (minYear == null) {
                minYear = 2000;
            } else if (maxYear == null) {
                int currentYear = Year.now().getValue();
                maxYear = currentYear + 1;
            }
            
            TRUE_QUERY += "AND " + YEAR_QUERY;
            
            // Add to params array
            searchParams.add(minYear + "%");
            searchParams.add(maxYear + "%");
        }
        
        // Dynamically add price
        if (minPrice != null || maxPrice != null) { // If minPrice & maxPrice both arent null
            if (minPrice == null) {
                minPrice = new BigDecimal("0.00");
            } else if (maxPrice == null) {
                maxPrice = new BigDecimal("999999.99");
            }
            
            TRUE_QUERY += "AND " + PRICE_QUERY;
            
            // Add to params array
            searchParams.add(minPrice + "%");
            searchParams.add(maxPrice + "%");
        }
        
        // Convert to true params aray
        String[] trueSearchParams = new String[searchParams.size()];
        
        for (int i = 0; i < searchParams.size(); i++) {
            trueSearchParams[i] = searchParams.get(i);
        }
        
        // Put it all together
        TRUE_QUERY += " ORDER BY v.msrp DESC LIMIT 20";
        allVehicles = jdbc.query(TRUE_QUERY, trueSearchParams, new VehicleMapper());
      
        for (Vehicle v : allVehicles) {
            v.setModel(getModelForVehicle(v));
            v.getModel().setMake(getMakeForVehicle(v));
        }  
        
        return allVehicles;
    }

    @Override
    public List<Vehicle> searchForFeatured() {
        String BASE_QUERY = "SELECT v.* FROM Vehicles v " +
                            "JOIN Models cm ON v.modelID = cm.modelID " +
                            "JOIN Makes m ON cm.makeID = m.makeID " +
                            "WHERE v.featured = TRUE "
                            + "ORDER BY v.msrp DESC LIMIT 20";
        
        
        List<Vehicle> featuredV = jdbc.query(BASE_QUERY, new VehicleMapper());
        for(Vehicle v : featuredV) {
            v.setModel(getModelForVehicle(v));
            v.getModel().setMake(getMakeForVehicle(v));
        }
        return featuredV;
    }

    @Override
    public List<Vehicle> searchForNewVehicle(String makeOrModel, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice) {
        String BASE_QUERY = "SELECT v.* FROM Vehicles v " +
                            "JOIN Models cm ON v.modelID = cm.modelID " +
                            "JOIN Makes m ON cm.makeID = m.makeID " +
                            "WHERE v.used = FALSE " +
                            "AND v.inStock = TRUE ";
        
        String VEHICLE_SEARCH_QUERY = " (m.makeName LIKE ? OR cm.modelName LIKE ?) ";
        String YEAR_QUERY = " (v.year BETWEEN ? AND ?) ";
        String PRICE_QUERY = " (v.salesPrice BETWEEN ? AND ?) ";
        String TRUE_QUERY = BASE_QUERY;
        
        List<Vehicle> allVehicles = new ArrayList<>();
        List<String> searchParams = new ArrayList<>();
        
        // Dynamically add vehicle
        if (!makeOrModel.equals("") && makeOrModel != null) {
            TRUE_QUERY += "AND " + VEHICLE_SEARCH_QUERY;
            
            // Add to params array
            searchParams.add(makeOrModel + "%");
            searchParams.add(makeOrModel + "%");
        }
        
        // Dynamically add year
        if (minYear != null || maxYear != null) { // If minYear & maxYear both arent null
            if (minYear == null) {
                minYear = 2000;
            } else if (maxYear == null) {
                int currentYear = Year.now().getValue();
                maxYear = currentYear + 1;
            }
            
            TRUE_QUERY += "AND " + YEAR_QUERY;
            
            // Add to params array
            searchParams.add(minYear + "%");
            searchParams.add(maxYear + "%");
        }
        
        // Dynamically add price
        if (minPrice != null || maxPrice != null) { // If minPrice & maxPrice both arent null
            if (minPrice == null) {
                minPrice = new BigDecimal("0.00");
            } else if (maxPrice == null) {
                maxPrice = new BigDecimal("999999.99");
            }
            
            TRUE_QUERY += "AND " + PRICE_QUERY;
            
            // Add to params array
            searchParams.add(minPrice + "%");
            searchParams.add(maxPrice + "%");
        }
        
        // Convert to true params aray
        String[] trueSearchParams = new String[searchParams.size()];
        
        for (int i = 0; i < searchParams.size(); i++) {
            trueSearchParams[i] = searchParams.get(i);
        }
        
        // Put it all together
        TRUE_QUERY += " ORDER BY v.msrp DESC LIMIT 20";
        allVehicles = jdbc.query(TRUE_QUERY, trueSearchParams, new VehicleMapper());
      
        for (Vehicle v : allVehicles) {
            v.setModel(getModelForVehicle(v));
            v.getModel().setMake(getMakeForVehicle(v));
        }  
        return allVehicles;
    }

    @Override
    public List<Vehicle> searchForUsedVehicle(String makeOrModel, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice) {
        String BASE_QUERY = "SELECT v.* FROM Vehicles v " +
                            "JOIN Models cm ON v.modelID = cm.modelID " +
                            "JOIN Makes m ON cm.makeID = m.makeID " +
                            "WHERE v.used = TRUE " +
                            "AND v.inStock = TRUE ";
        
        String VEHICLE_SEARCH_QUERY = " (m.makeName LIKE ? OR cm.modelName LIKE ?) ";
        String YEAR_QUERY = " (v.year BETWEEN ? AND ?) ";
        String PRICE_QUERY = " (v.salesPrice BETWEEN ? AND ?) ";
        String TRUE_QUERY = BASE_QUERY;
        
        List<Vehicle> allVehicles = new ArrayList<>();
        List<String> searchParams = new ArrayList<>();
        
        // Dynamically add vehicle
        if (!makeOrModel.equals("") && makeOrModel != null) {
            TRUE_QUERY += "AND " + VEHICLE_SEARCH_QUERY;
            
            // Add to params array
            searchParams.add(makeOrModel + "%");
            searchParams.add(makeOrModel + "%");
        }
        
        // Dynamically add year
        if (minYear != null || maxYear != null) { // If minYear & maxYear both arent null
            if (minYear == null) {
                minYear = 2000;
            } else if (maxYear == null) {
                int currentYear = Year.now().getValue();
                maxYear = currentYear + 1;
            }
            
            TRUE_QUERY += "AND " + YEAR_QUERY;
            
            // Add to params array
            searchParams.add(minYear + "%");
            searchParams.add(maxYear + "%");
        }
        
        // Dynamically add price
        if (minPrice != null || maxPrice != null) { // If minPrice & maxPrice both arent null
            if (minPrice == null) {
                minPrice = new BigDecimal("0.00");
            } else if (maxPrice == null) {
                maxPrice = new BigDecimal("999999.99");
            }
            
            TRUE_QUERY += "AND " + PRICE_QUERY;
            
            // Add to params array
            searchParams.add(minPrice + "%");
            searchParams.add(maxPrice + "%");
        }
        
        // Convert to true params aray
        String[] trueSearchParams = new String[searchParams.size()];
        
        for (int i = 0; i < searchParams.size(); i++) {
            trueSearchParams[i] = searchParams.get(i);
        }
        
        // Put it all together
        TRUE_QUERY += " ORDER BY v.msrp DESC LIMIT 20";
        allVehicles = jdbc.query(TRUE_QUERY, trueSearchParams, new VehicleMapper());
      
        for (Vehicle v : allVehicles) {
            v.setModel(getModelForVehicle(v));
            v.getModel().setMake(getMakeForVehicle(v));
        }  
        
        return allVehicles;
    }

    @Override
    public List<Vehicle> seeInStockVehicles(String makeOrModel, Integer minYear, Integer maxYear, BigDecimal minPrice, BigDecimal maxPrice) {
        String BASE_QUERY = "SELECT v.* FROM Vehicles v " +
                            "JOIN Models cm ON v.modelID = cm.modelID " +
                            "JOIN Makes m ON cm.makeID = m.makeID " +
                            "WHERE v.inStock = TRUE ";
        
        String VEHICLE_SEARCH_QUERY = " (m.makeName LIKE ? OR cm.modelName LIKE ?) ";
        String YEAR_QUERY = " (v.year BETWEEN ? AND ?) ";
        String PRICE_QUERY = " (v.salesPrice BETWEEN ? AND ?) ";
        String TRUE_QUERY = BASE_QUERY;
        
        List<Vehicle> allVehicles = new ArrayList<>();
        List<String> searchParams = new ArrayList<>();
        
        // Dynamically add vehicle
        if (!makeOrModel.equals("") && makeOrModel != null) {
            TRUE_QUERY += "AND " + VEHICLE_SEARCH_QUERY;
            
            // Add to params array
            searchParams.add(makeOrModel + "%");
            searchParams.add(makeOrModel + "%");
        }
        
        // Dynamically add year
        if (minYear != null || maxYear != null) { // If minYear & maxYear both arent null
            if (minYear == null) {
                minYear = 2000;
            } else if (maxYear == null) {
                int currentYear = Year.now().getValue();
                maxYear = currentYear + 1;
            }
            
            TRUE_QUERY += "AND " + YEAR_QUERY;
            
            // Add to params array
            searchParams.add(minYear + "%");
            searchParams.add(maxYear + "%");
        }
        
        // Dynamically add price
        if (minPrice != null || maxPrice != null) { // If minPrice & maxPrice both arent null
            if (minPrice == null) {
                minPrice = new BigDecimal("0.00");
            } else if (maxPrice == null) {
                maxPrice = new BigDecimal("999999.99");
            }
            
            TRUE_QUERY += "AND " + PRICE_QUERY;
            
            // Add to params array
            searchParams.add(minPrice + "%");
            searchParams.add(maxPrice + "%");
        }
        
        // Convert to true params aray
        String[] trueSearchParams = new String[searchParams.size()];
        
        for (int i = 0; i < searchParams.size(); i++) {
            trueSearchParams[i] = searchParams.get(i);
        }
        
        // Put it all together
        TRUE_QUERY += " ORDER BY v.msrp DESC LIMIT 20";
        allVehicles = jdbc.query(TRUE_QUERY, trueSearchParams, new VehicleMapper());
      
        for (Vehicle v : allVehicles) {
            v.setModel(getModelForVehicle(v));
            v.getModel().setMake(getMakeForVehicle(v));
        }  
        
        return allVehicles;    }

    
    
public static class VehicleMapper implements RowMapper<Vehicle>{

      @Override
      public Vehicle mapRow(ResultSet rs, int i) throws SQLException {
          Vehicle vehicle = new Vehicle();
          vehicle.setVehicleId(rs.getInt("vehicleID"));
          vehicle.setYear(rs.getInt("year"));
          vehicle.setTransmission(rs.getString("transmission"));
          vehicle.setMileage(rs.getInt("mileage"));
          vehicle.setColor(rs.getString("color"));
          vehicle.setInterior(rs.getString("interior"));
          vehicle.setBodyType(rs.getString("bodyStyle"));
          vehicle.setVin(rs.getString("VIN"));
          vehicle.setSalesPrice(rs.getBigDecimal("salesPrice"));
          vehicle.setMsrp(rs.getBigDecimal("msrp"));
          vehicle.setDescription(rs.getString("description"));
          vehicle.setUsed(rs.getBoolean("used"));
          vehicle.setFeatured(rs.getBoolean("featured"));
          vehicle.setSpecial(rs.getBoolean("special"));
          vehicle.setInStock(rs.getBoolean("inStock"));
          vehicle.setPicURL(rs.getString("picURL"));


          return vehicle;
      }
  }    
}
