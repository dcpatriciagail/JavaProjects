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
public class DuplicateMakeException extends Exception {

    public DuplicateMakeException() {
    }

    public DuplicateMakeException(String message) {
        super(message);
    }

    public DuplicateMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateMakeException(Throwable cause) {
        super(cause);
    }

    public DuplicateMakeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
