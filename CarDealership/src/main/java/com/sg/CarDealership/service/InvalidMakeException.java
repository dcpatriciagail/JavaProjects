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
public class InvalidMakeException extends Exception {

    public InvalidMakeException() {
    }

    public InvalidMakeException(String message) {
        super(message);
    }

    public InvalidMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMakeException(Throwable cause) {
        super(cause);
    }

    public InvalidMakeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
