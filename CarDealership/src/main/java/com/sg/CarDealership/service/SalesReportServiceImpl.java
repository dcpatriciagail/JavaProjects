/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dao.SalesReportDao;
import com.sg.CarDealership.dto.SalesReport;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Patricia
 */
@Service
public class SalesReportServiceImpl implements SalesReportService{

    @Autowired
    SalesReportDao salesReportDao;
    
    
    @Override
    public List<SalesReport> seeSalesReportForDateRange(String userName, LocalDate fromDate, LocalDate toDate) {
        return salesReportDao.seeSalesReportForDateRange(userName, fromDate, toDate);
    }
    
}
