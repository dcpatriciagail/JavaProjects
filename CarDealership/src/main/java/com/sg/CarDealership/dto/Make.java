/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dto;

import java.util.Objects;

/**
 *
 * @author Patricia
 */
public class Make {
    
    private Integer makeId;
    private String makeName;

    public Integer getMakeId() {
        return makeId;
    }

    public void setMakeId(Integer makeId) {
        this.makeId = makeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.makeId);
        hash = 83 * hash + Objects.hashCode(this.makeName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Make other = (Make) obj;
        if (!Objects.equals(this.makeName, other.makeName)) {
            return false;
        }
        if (!Objects.equals(this.makeId, other.makeId)) {
            return false;
        }
        return true;
    }
    
    
    
}
