package fr.opticycle;

import java.util.Set;

public class Titulaire extends Personne {
    private Set<Discipline> disciplines;
    private int numBureau;

    public Titulaire(String nom, String prenom, int age, String ville, Set<Discipline> disciplines, int numBureau) {
        super(nom, prenom, age, ville);
        if(disciplines.size() != 1 && disciplines.size() != 2) {
            System.out.println("Nb de disciplines invalide : " + disciplines.size() + " pour " + prenom + ", " + nom);
        }
        this.disciplines = disciplines;
        this.numBureau = numBureau;
    }

    public boolean hasMultipleDisciplines() {
        return disciplines.size() == 2;
    }

    @Override
    public String toString() {
        return "Titulaire{" +
                "discipline=" + disciplines +
                ", numBureau=" + numBureau +
                '}';
    }
}
