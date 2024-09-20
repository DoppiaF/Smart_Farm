package com.unife.project.model.mo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CisternaTest {

    @Test
    public void testGettersAndSetters() {
        Cisterna cisterna = new Cisterna(500, 300);

        assertEquals(500, cisterna.getCapacita());
        assertEquals(300, cisterna.getQuantita());

        cisterna.setCapacita(600);
        cisterna.setQuantita(400);

        assertEquals(600, cisterna.getCapacita());
        assertEquals(400, cisterna.getQuantita());
    }
}
