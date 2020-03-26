/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.viewmodel.VehicleReport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Patricia
 */
@Repository
public class VehicleReportDaoImpl implements VehicleReportDao {
    
    private JdbcTemplate jdbc;
    
    @Autowired
    public VehicleReportDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<VehicleReport> getNewInventoryReport() {
        String query = "SELECT v.`year`, mo.modelName, ma.makeName, " +
                       "SUM(v.msrp) AS stockValue, COUNT(mo.modelName) AS vehicleCount " +
                       "FROM Vehicles v INNER JOIN Models mo ON v.modelID = mo.modelID " +
                       "INNER JOIN Makes ma ON mo.makeID = ma.makeID " +
                       "WHERE v.used = false GROUP BY v.`year`";
        
        List<VehicleReport> reports = jdbc.query(query, new VehicleReportMapper());
        
        return reports;
    }

    @Override
    public List<VehicleReport> getUsedInventoryReport() {
        String query = "SELECT v.`year`, mo.modelName, ma.makeName, " +
                       "SUM(v.msrp) AS stockValue, COUNT(mo.modelName) AS vehicleCount " +
                       "FROM Vehicles v INNER JOIN Models mo ON v.modelID = mo.modelID " +
                       "INNER JOIN Makes ma ON mo.makeID = ma.makeID " +
                       "WHERE v.used = true GROUP BY v.`year`";
        
        List<VehicleReport> reports = jdbc.query(query, new VehicleReportMapper());
        
        return reports;
    }
    
    public static class VehicleReportMapper implements RowMapper<VehicleReport> {

        @Override
        public VehicleReport mapRow(ResultSet rs, int i) throws SQLException {
            VehicleReport report = new VehicleReport();
            report.setYear(rs.getInt("year"));
            report.setMake(rs.getString("ma.makeName"));
            report.setModel(rs.getString("mo.modelName"));
            report.setCount(rs.getInt("vehicleCount"));
            report.setStockValue(rs.getBigDecimal("stockValue"));
            
            return report;
        }
    }
    
}
