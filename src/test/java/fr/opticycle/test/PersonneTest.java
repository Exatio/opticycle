package fr.opticycle.test;

import fr.opticycle.model.Chercheur;
import fr.opticycle.model.Etudiant;
import fr.opticycle.model.Personne;
import fr.opticycle.tsp.Ville;
import fr.opticycle.utils.Discipline;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonneTest {

    private Personne personne;

    @BeforeEach
    public void start() {
        System.out.println("Début des tests de Personne");
        personne = new Chercheur("Dupont", "Jean", 25, new Ville("Paris", "75000", 2200000, 105, "75", 48.8566f, 2.3522f), Set.of(Discipline.INFORMATIQUE, Discipline.MATHEMATIQUES), 50);
    }

    @Test
    public void testIndividu() {
        assertEquals("Dupont", personne.getNom());
        assertEquals("Jean", personne.getPrenom());
        assertEquals(25, personne.getAge());
        assertEquals("Paris", personne.getVille().getNom());
        assertEquals("75000", personne.getVille().getCodePostal());
        assertEquals(2200000, personne.getVille().getPopulation2012());
        assertEquals(105, personne.getVille().getSuperficie());
        assertEquals("75", personne.getVille().getDepartement());
        assertEquals(48.8566f, personne.getVille().getLatitude());
        assertEquals(2.3522f, personne.getVille().getLongitude());
        assertTrue(personne.hasDiscipline(Discipline.INFORMATIQUE));
    }

    @Test
    public void testEncadre() {
        Chercheur chercheur = (Chercheur) this.personne;
        assertTrue(chercheur.peutEncadrer());
        Etudiant etudiant = new Etudiant("Durand", "Paul", 23, new Ville("Paris", "75000", 2200000, 105, "75", 48.8566f, 2.3522f), "Thèse sur les graphes", Discipline.INFORMATIQUE, 1, chercheur);
        assertTrue(chercheur.hasEtudiant());
        assertEquals(etudiant, chercheur.getEtudiants().iterator().next());
    }

    @AfterEach
    public void end() {
        System.out.println("Fin des tests de Personne");
    }
}
