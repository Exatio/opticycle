package fr.opticycle.test;

import fr.opticycle.tsp.Ville;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VilleTest {

    private Ville ville;

    @BeforeEach
    public void start() {
        System.out.println("Début des tests de Ville");
        ville = new Ville("Paris", "75000", 2200000, 105, "75", 48.8566f, 2.3522f);
    }

    @Test
    public void testIndividu() {
        assertEquals("Paris", ville.getNom());
        assertEquals("75000", ville.getCodePostal());
        assertEquals(2200000, ville.getPopulation2012());
        assertEquals(105, ville.getSuperficie());
        assertEquals("75", ville.getDepartement());
        assertEquals(48.8566f, ville.getLatitude());
        assertEquals(2.3522f, ville.getLongitude());
    }

    @AfterEach
    public void end() {
        System.out.println("Fin des tests de Ville");
    }
}
