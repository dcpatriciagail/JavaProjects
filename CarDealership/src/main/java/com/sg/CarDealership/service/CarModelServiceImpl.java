/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dao.carModelDao;
import com.sg.CarDealership.dto.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Patricia
 */
@Service 
public class CarModelServiceImpl implements CarModelService {

  
    private carModelDao modelDao;

    @Autowired
    public CarModelServiceImpl(carModelDao modelDao) {
        this.modelDao = modelDao;
    }
  
    
    @Override
    public Model createModel(Model model) throws InvalidModelException {
        validateModel(model);
        return  modelDao.addModel(model);
    }

    @Override
    public Model readModel(Integer modelID) {
        return modelDao.getModelById(modelID);
    }

    @Override
    public List<Model> readAllModels() {
        return modelDao.readAllModels();
    }

    @Override
    public void updateModel(Model model) throws InvalidModelException {
        validateModel(model);        
        modelDao.updateModel(model);
    }
    
    
    private void validateModel(Model model) throws InvalidModelException {
        if(model.getMake() == null || model.getMake().getMakeName().equals("")) {
            throw new InvalidModelException();
        }
         
        if (model.getModelName().equals("") || model.getModelName().length() > 20) {
            throw new InvalidModelException();
        }
    }

    @Override
    public List<Model> getAllModelsByMake(Integer makeId) {
       return modelDao.getAllModelsByMake(makeId);
    }
    
    
    
    
}
