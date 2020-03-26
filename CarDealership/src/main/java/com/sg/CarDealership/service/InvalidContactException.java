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
public class InvalidContactException extends Exception {

    public InvalidContactException() {
    }

   
    
    
    public InvalidContactException(String message) {
        super(message);
    }

    public InvalidContactException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
