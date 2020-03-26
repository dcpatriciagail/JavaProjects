/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dto;

import java.math.BigDecimal;

/**
 *
 * @author Patricia
 */
public class SalesReport {

private String userName;
    private BigDecimal totalSales;
    private Integer totalVehicles;

    public String getUserName() {
        return userName;
    }

    public void setUser(String userName) {
        this.userName = userName;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public Integer getTotalVehicles() {
        return totalVehicles;
    }

    public void setTotalVehicles(Integer totalVehicles) {
        this.totalVehicles = totalVehicles;
    }
    
}
