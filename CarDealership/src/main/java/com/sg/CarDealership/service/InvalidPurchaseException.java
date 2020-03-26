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
public class InvalidPurchaseException extends Exception{

    public InvalidPurchaseException() {
    }

    public InvalidPurchaseException(String message) {
        super(message);
    }

    public InvalidPurchaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPurchaseException(Throwable cause) {
        super(cause);
    }

    public InvalidPurchaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
