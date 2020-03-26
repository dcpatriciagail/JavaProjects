/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

/**
 *
 * @author Patricia
 */
public class InvalidVehicleException extends Exception {

    public InvalidVehicleException() {
    }

    public InvalidVehicleException(String message) {
        super(message);
    }

    public InvalidVehicleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidVehicleException(Throwable cause) {
        super(cause);
    }

    public InvalidVehicleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
