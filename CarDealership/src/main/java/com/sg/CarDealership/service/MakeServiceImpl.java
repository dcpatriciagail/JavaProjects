/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dao.makeDao;
import com.sg.CarDealership.dto.Make;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Patricia
 */
@Service
public class MakeServiceImpl implements MakeService{
    
    private makeDao makeDao;

    public MakeServiceImpl(makeDao makeDao) {
        this.makeDao = makeDao;
    }

    @Override
    public Make createMake(Make make) throws InvalidMakeException, DuplicateMakeException {
        //validate
        validateMake(make);
        return makeDao.addMake(make);
    }

    @Override
    public Make readMake(Integer makeID) {
        return makeDao.getMakeById(makeID);
    }

    @Override
    public List<Make> readAllMakes() {
        return makeDao.readAllMake();
    }

    @Override
    public void updateMake(Make make) throws InvalidMakeException, DuplicateMakeException{
        //validate
        validateMake(make);
        makeDao.updateMake(make);
    }

    private void validateMake(Make make) throws InvalidMakeException, DuplicateMakeException {
       if (make.getMakeName().equals("") || make.getMakeName() == null) {
           throw new InvalidMakeException();
       }

       List<Make> allMakes = readAllMakes();

       for (Make m : allMakes) {
           if (make.getMakeName().equalsIgnoreCase(m.getMakeName())) {
               throw new DuplicateMakeException();
           }
       }
       if(make.getMakeName().length() > 20) {
           throw new InvalidMakeException();
       }
    }
    
    
    }
