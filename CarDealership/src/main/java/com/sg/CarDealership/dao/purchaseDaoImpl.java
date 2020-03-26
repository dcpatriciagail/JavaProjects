/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.carModelDaoImpl.CarModelMapper;
import com.sg.CarDealership.dao.makeDaoImpl.MakeMapper;
import com.sg.CarDealership.dao.userDaoImpl.UserMapper;
import com.sg.CarDealership.dao.vehicleDaoImpl.VehicleMapper;
import com.sg.CarDealership.dto.Model;
import com.sg.CarDealership.dto.Make;
import com.sg.CarDealership.dto.Purchase;
import com.sg.CarDealership.dto.User;
import com.sg.CarDealership.dto.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Patricia
 */
@Repository 
public class purchaseDaoImpl implements purchaseDao {

    private final JdbcTemplate jdbc;

    public purchaseDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Purchase addPurchase(Purchase purchase) {
        String query = "INSERT INTO Purchases (vehicleID, userID, purchasePrice, purchaseDate, "
                + "purchaseType, customerName) VALUES (?,?,?,?,?,?)";
        jdbc.update(query, 
                purchase.getVehicle().getVehicleId(),
                purchase.getUser().getUserId(),
                purchase.getPurchasePrice(),
                purchase.getPurchaseDate(),
                purchase.getPurchaseType(),
                purchase.getCustomerName());
        
        Integer newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        purchase.setPurchaseId(newId);
        return purchase;
    }
    
    @Transactional 
    @Override
    public Purchase getPurchaseById(Integer id) {
        Purchase purchase = null;
        String query = "SELECT * FROM Purchases WHERE purchaseID = ?";
        try {
            purchase = jdbc.queryForObject(query, new PurchaseMapper(), id);
            purchase.setVehicle(getVehicleForPurchase(purchase));
            purchase.getVehicle().setModel(getModelForPurchase(purchase));
            purchase.getVehicle().getModel().setMake(getMakeForPurchase(purchase));
                      
            purchase.setUser(getUserForPurchase(purchase));
            
        } catch (EmptyResultDataAccessException ex) {
            
        }
        
        return purchase;
    }

        
      private Vehicle getVehicleForPurchase (Purchase purchase) {
          String query = "SELECT v. * FROM Vehicles v "
                  + "INNER JOIN Purchases p ON v.vehicleID = p.vehicleID "
                  + "WHERE p.purchaseID = ?";
          return jdbc.queryForObject(query, new VehicleMapper(), purchase.getPurchaseId());
      }
      

    private Model getModelForPurchase(Purchase purchase) {
       String query = "SELECt cm.* FROM Models cm " +
               "INNER JOIN Vehicles v ON cm.modelID = v.modelID "+
               "INNER JOIN Purchases p ON v.vehicleID = p.vehicleID "+
               "WHERE p.purchaseID = ?";

       return jdbc.queryForObject(query, new CarModelMapper(), purchase.getPurchaseId());
   }
   
    private Make getMakeForPurchase(Purchase purchase) {
        String query = "SELECT m.* FROM Makes m "+
                       "INNER JOIN Models cm ON m.makeID = cm.makeID "+
                       "INNER JOIN Vehicles v ON cm.modelID = v.modelID "+
                       "INNER JOIN Purchases p ON v.vehicleID = p.vehicleID "+
                       "WHERE p.purchaseID = ?";
        return jdbc.queryForObject(query, new MakeMapper(), purchase.getPurchaseId());
    }    
    
    
    
    private User getUserForPurchase (Purchase purchase) {
        String query = "SELECT * FROM Users INNER JOIN Purchases "
                + "ON Purchases.userID = Users.userID "
                + "WHERE Purchases.purchaseID = ?";
        return jdbc.queryForObject(query, new UserMapper(), purchase.getPurchaseId());
    }
     
    @Override
    public void updatePurchase(Purchase purchase) {
        String query = "UPDATE Purchases SET"
                + " vehicleID = ?,"
                + " userID = ?,"
                + " purchasePrice = ?,"
                + " purchaseDate = ?,"
                + " purchaseType = ?,"
                + " customerName = ? "
                + "WHERE purchaseID = ";
        jdbc.update(query, 
                purchase.getVehicle().getVehicleId(),
                purchase.getUser().getUserId(),
                purchase.getPurchasePrice(),
                purchase.getPurchaseDate(),
                purchase.getPurchaseType(),
                purchase.getCustomerName(),
                purchase.getPurchaseId());
    }

    @Override
    public void deletePurchaseById(Integer id) {
        final String query = "DELETE FROM Purchases WHERE purchaseID = ?";
        jdbc.update(query, id);
    }

    @Override
    public List<Purchase> readAllPurchase() {
        String query = "SELECT * FROM Purchases";
        List<Purchase> purchases = jdbc.query(query, new PurchaseMapper());
        for (Purchase p : purchases) {
            p.setVehicle(getVehicleForPurchase(p));
            p.getVehicle().setModel(getModelForPurchase(p));
            p.getVehicle().getModel().setMake(getMakeForPurchase(p));
            p.setUser(getUserForPurchase(p));
        }

        return purchases;
    }

    public static class PurchaseMapper implements RowMapper<Purchase>{

        @Override
        public Purchase mapRow (ResultSet rs, int i) throws SQLException {
            Purchase purchase = new Purchase();
            purchase.setPurchaseId(rs.getInt("purchaseID"));
            purchase.setPurchasePrice(rs.getBigDecimal("purchasePrice"));
            purchase.setPurchaseDate(rs.getDate("purchaseDate").toLocalDate());
            purchase.setPurchaseType(rs.getString("purchaseType"));
            purchase.setCustomerName(rs.getString("customerName"));
            return purchase;
        }
    }    
    
}
