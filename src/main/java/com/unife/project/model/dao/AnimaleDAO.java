package com.unife.project.model.dao;

import com.unife.project.model.mo.Animale;

import java.util.ArrayList;
import java.util.List;

public class AnimaleDAO implements GenericDAO<Animale> {
    private List<Animale> animali = new ArrayList<>();
    private int idCounter = 1;

    @Override
    public void save(Animale animale) {
        animale.setId_animale(idCounter++);
        animali.add(animale);
    }

    @Override
    public void update(Animale animale) {
        for (int i = 0; i < animali.size(); i++) {
            if (animali.get(i).getId_animale() == animale.getId_animale()) {
                animali.set(i, animale);
                return;
            }
        }
    }

    @Override
    public void delete(Animale animale) {
        animali.remove(animale);
    }

    @Override
    public Animale findById(int id) {
        for (Animale animale : animali) {
            if (animale.getId_animale() == id) {
                return animale;
            }
        }
        return null;
    }

    @Override
    public List<Animale> findAll() {
        return new ArrayList<>(animali);
    }
}
