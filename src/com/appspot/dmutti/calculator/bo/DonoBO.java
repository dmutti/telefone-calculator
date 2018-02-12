package com.appspot.dmutti.calculator.bo;

import java.util.*;

import com.appspot.dmutti.calculator.dao.*;
import com.appspot.dmutti.calculator.pojo.*;

public class DonoBO {
    
    private DonoDAO donoDAO;
    
    public void init() {
        if (donoDAO.find().isEmpty()) {
            donoDAO.save(new Dono("casa@1156421744", "Casa", true));
            donoDAO.save(new Dono("leila@1156421744", "Leila", true));
            donoDAO.save(new Dono("dmutti@gmail.com", "Pateta", false));
            donoDAO.save(new Dono("fernando.lf@gmail.com", "Fernandinho", false));
            donoDAO.save(new Dono("igor.alexander@gmail.com", "Igor", false));
            donoDAO.save(new Dono("childbrz@gmail.com", "Nerso", false));
            donoDAO.save(new Dono("danielkawamoto@gmail.com", "Kawamoto", false));
        }
    }
    
    public Map<String, Dono> getAllAsMap() {
        Map<String, Dono> result = new LinkedHashMap<String, Dono>();
        for (Dono d : donoDAO.find()) {
            result.put(d.getEmail(), d);
        }
        return result;
    }
    
    public Dono findByEmail(String email) {
        List<Dono> parcial = donoDAO.findByPrimaryKey(email);
        if (!parcial.isEmpty()) {
            return parcial.get(0);
        }
        return null;
    }

    public void setDonoDao(DonoDAO donoDAO) {
        this.donoDAO = donoDAO;
    }
}
