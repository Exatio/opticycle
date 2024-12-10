package fr.opticycle;

import java.util.Set;

public class MCF extends Titulaire {

    private Etudiant etudiant;

    public MCF(String nom, String prenom, int age, String ville, Set<Discipline> disciplines, int numBureau) {
        super(nom, prenom, age, ville, disciplines, numBureau);
    }

    public MCF(String nom, String prenom, int age, String ville, Set<Discipline> disciplines, int numBureau, Etudiant etudiant) {
        super(nom, prenom, age, ville, disciplines, numBureau);
        this.etudiant = etudiant;
    }

    public boolean hasEtudiant() {
        return this.etudiant != null;
    }

    public Etudiant getEtudiant() {
        return this.etudiant;
    }

    @Override
    public String toString() {
        return "MCF{" +
                "etudiant=" + this.etudiant +
                '}';
    }
}
