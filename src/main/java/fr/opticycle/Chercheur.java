package fr.opticycle;

import java.util.HashSet;
import java.util.Set;

public class Chercheur extends Titulaire {
    private Set<Etudiant> etudiants;

    public Chercheur(String nom, String prenom, int age, String ville, Set<Discipline> disciplines, int numBureau) {
        this(nom, prenom, age, ville, disciplines, numBureau, new HashSet<>());
    }

    public Chercheur(String nom, String prenom, int age, String ville, Set<Discipline> disciplines, int numBureau, Set<Etudiant> etudiants) {
        super(nom, prenom, age, ville, disciplines, numBureau);
        this.etudiants = etudiants;
    }

    public boolean hasEtudiant() {
        return !etudiants.isEmpty();
    }

    @Override
    public String toString() {
        return "Chercheur{" +
                "etudiants=" + etudiants +
                '}';
    }

}
