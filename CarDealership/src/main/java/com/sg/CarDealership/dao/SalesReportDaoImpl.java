/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.SalesReport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class SalesReportDaoImpl implements SalesReportDao{

private String BASE_QUERY = "SELECT CONCAT(u.firstName,' ',u.lastName) AS userName, "+
                                "SUM(p.purchasePrice) AS totalSales, "+
                                "COUNT(p.purchasePrice) AS totalVehicles "+
                                "FROM Purchases p "+
                                "JOIN Users u ON p.userID = u.userID ";
                        
    private JdbcTemplate jdbc;
    
    @Autowired
    public SalesReportDaoImpl(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    
    
    @Override
    public List<SalesReport> seeSalesReportForDateRange(String userName, LocalDate fromDate, LocalDate toDate) {
        String USER_QUERY = "WHERE CONCAT(u.firstName,' ',u.lastName) LIKE ?";
        String DATE_QUERY = "";
        String TRUE_QUERY = BASE_QUERY;
        
        List<SalesReport> allSalesReports = new ArrayList<>();
        List<String> searchParams = new ArrayList<>();
        
        if(!userName.equals("") && userName != null) { //if userName is not "" and its not null
            TRUE_QUERY += USER_QUERY; //add the USER_QUERY onto end of TRUE QUERY 
            
            searchParams.add(userName +"%");
        }
        
        
        
        if(fromDate != null && toDate != null) {
            if(TRUE_QUERY.contains(USER_QUERY)){
                DATE_QUERY = " AND p.purchaseDate BETWEEN ? AND ?";
            }
            else{
                DATE_QUERY = " WHERE p.purchaseDate BETWEEN ? AND ?";
            }
            
            TRUE_QUERY += DATE_QUERY;
            searchParams.add(fromDate +"%");
            searchParams.add(toDate +"%");
        }
        
        if(fromDate != null || toDate != null ) { //if fromDate is filled or toDate is filled
            if(fromDate == null) {
                if(TRUE_QUERY.contains(USER_QUERY)){
                   DATE_QUERY = " AND p.purchaseDate < ?"; 
                }
                else {
                   DATE_QUERY = " WHERE p.purchaseDate < ?";
                }
                //DATE_QUERY = " AND p.purchaseDate < ?";
                TRUE_QUERY += DATE_QUERY;
                searchParams.add(toDate+"%");
            }
            
            else if(toDate == null) {
                if(TRUE_QUERY.contains(USER_QUERY)){
                    DATE_QUERY = " AND p.purchaseDate > ?";
                }
                else {
                    DATE_QUERY = " WHERE p.purchaseDate > ?";
                }
                TRUE_QUERY += DATE_QUERY;
                searchParams.add(fromDate + "%");
            }
            
        }
        
        String[] trueSearchParams = new String[searchParams.size()];
        
        for (int i = 0; i < searchParams.size(); i++) {
            trueSearchParams[i] = searchParams.get(i);
        }
        
        TRUE_QUERY += " GROUP BY CONCAT(u.firstName,' ',u.lastName)";
        allSalesReports = jdbc.query(TRUE_QUERY, new SalesReportMapper(), trueSearchParams);
        
        return allSalesReports;
    }
    
    private static class SalesReportMapper implements RowMapper<SalesReport> {
        @Override
        public SalesReport mapRow(ResultSet rs, int i) throws SQLException {
            SalesReport salesReport = new SalesReport();
            salesReport.setUser(rs.getString("userName"));
            salesReport.setTotalSales(rs.getBigDecimal("totalSales"));
            salesReport.setTotalVehicles(rs.getInt("totalVehicles"));
            return salesReport;
        }
        
    }    
}
