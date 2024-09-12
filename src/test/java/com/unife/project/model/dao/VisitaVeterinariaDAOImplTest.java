package com.unife.project.model.dao;

import com.unife.project.model.mo.VisitaVeterinaria;

import java.util.List;

import com.unife.project.model.dao.DAOFactory;

public class VisitaVeterinariaDAOImplTest {

    public static void main(String[] args) {
        List<VisitaVeterinaria> visite = DAOFactory.getVisitaVeterinariaDAO().findByIdAnimale(4);

        for(VisitaVeterinaria v : visite){
            System.out.println(v);
        }
    }
}

