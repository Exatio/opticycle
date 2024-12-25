package fr.opticycle.test;

import fr.opticycle.algo.AlgoGenetique;
import fr.opticycle.algo.Individu;
import fr.opticycle.tsp.Ville;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgoGenetiqueTest {

    @Test
    public void test() {
        List<Ville> villes = new ArrayList<>();
        villes.add(new Ville("Paris", "75000", 2200000, 105, "75", 48.8566f, 2.3522f));
        villes.add(new Ville("Paris", "75000", 2200000, 105, "75", 48.8566f, 2.3522f));

        Individu individu = AlgoGenetique.geneticAlgorithm(villes, 5, 0.1f, 0.1f, 1, 1);
        assertEquals(0, individu.getDistance());
    }

    @Test
    public void test2() {
        List<Ville> villes = new ArrayList<>();
        villes.add(new Ville("Paris", "75000", 2200000, 105, "75", 48.8566f, 2.3522f));
        villes.add(new Ville("Lyon", "69000", 500000, 47, "69", 45.75f, 4.85f));

        Individu individu = AlgoGenetique.geneticAlgorithm(villes, 5, 0.1f, 0.1f, 1, 1);
        assertEquals(786, ((int) individu.getDistance()));
    }

}
