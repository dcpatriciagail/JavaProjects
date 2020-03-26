/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.viewmodel.VehicleReport;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface VehicleReportService {
    List<VehicleReport> getNewInventoryReport();
    
    List<VehicleReport> getUsedInventoryReport();    
}
