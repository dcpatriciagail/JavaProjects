/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.SalesReport;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface SalesReportDao {
    
List<SalesReport> seeSalesReportForDateRange(String userName, LocalDate fromDate, LocalDate toDate);    

}
