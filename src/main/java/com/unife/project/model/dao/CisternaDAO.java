package com.unife.project.model.dao;

import java.util.List;
import com.unife.project.model.mo.Cisterna;

public interface CisternaDAO extends GenericDAO<Cisterna>{
    //metodi da definire per cisterna    
    public List<Integer> findAllIds();
}
