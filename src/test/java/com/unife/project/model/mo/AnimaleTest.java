package com.unife.project.model.mo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.unife.project.model.mo.Animale;

public class AnimaleTest {

    @Test
    public void testGettersAndSetters() {
        // Create LocalDate instances for testing
        LocalDate dataNascita = LocalDate.of(2020, 1, 1);
        LocalDate dataIngresso = LocalDate.of(2021, 1, 1);
        LocalDate dataUscita = LocalDate.of(2022, 1, 1);
        LocalDate dataMorte = LocalDate.of(2023, 1, 1);
        LocalDate dataVaccino = LocalDate.of(2024, 1, 1);

        // Create an instance of Animale
        Animale animale = new Animale();
        animale.setId_animale(1);
        animale.setPeso(100);
        animale.setSesso('M');
        animale.setRazza("Bovino");
        animale.setTipoAlimentazione("Erba");
        animale.setNomeStalla("Stalla1");
        animale.setData_nascita(dataNascita);
        animale.setData_ingresso(dataIngresso);
        animale.setData_uscita(dataUscita);
        animale.setData_morte(dataMorte);
        animale.setData_vaccino(dataVaccino);

        // Test getters
        assertEquals(1, animale.getId_animale());
        assertEquals(100, animale.getPeso());
        assertEquals('M', animale.getSesso());
        assertEquals("Bovino", animale.getRazza());
        assertEquals("Erba", animale.getTipoAlimentazione());
        assertEquals("Stalla1", animale.getNomeStalla());
        assertEquals(dataNascita, animale.getData_nascita());
        assertEquals(dataIngresso, animale.getData_ingresso());
        assertEquals(dataUscita, animale.getData_uscita());
        assertEquals(dataMorte, animale.getData_morte());
        assertEquals(dataVaccino, animale.getData_vaccino());
    }
}
