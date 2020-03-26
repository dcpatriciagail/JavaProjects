/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dao.VehicleReportDao;
import com.sg.CarDealership.viewmodel.VehicleReport;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Patricia
 */
@Service
public class VehicleReportServiceImpl implements VehicleReportService {

    private VehicleReportDao dao;
    
    @Autowired
    public VehicleReportServiceImpl(VehicleReportDao dao) {
        this.dao = dao;
    }
    
    @Override
    public List<VehicleReport> getNewInventoryReport() {
        return dao.getNewInventoryReport();
    }

    @Override
    public List<VehicleReport> getUsedInventoryReport() {
        return dao.getUsedInventoryReport();
    }
    
}
