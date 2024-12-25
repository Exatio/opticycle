package fr.opticycle.model;

import fr.opticycle.tsp.Ville;
import fr.opticycle.utils.Discipline;

import java.util.HashSet;
import java.util.Set;

/**
 * Une instance Titulaire est une personne qui possède un bureau et étudie plusieurs disciplines
 */
public abstract class Titulaire extends Personne {
    protected final Set<Discipline> disciplines;
    protected final int numBureau;

    /**
     * Créer un Titulaire qui dérive de Personne
     * @param nom Nom de la personne
     * @param prenom Prénom de la personne
     * @param age Age de la personne
     * @param ville Ville où réside la personne
     * @param disciplines Disciplines que le titulaire étudie
     * @param numBureau Numéro du bureau du titulaire
     */
    public Titulaire(String nom, String prenom, int age, Ville ville, Set<Discipline> disciplines, int numBureau) {
        super(nom, prenom, age, ville);
        if(disciplines.size() != 1 && disciplines.size() != 2) {
            System.out.println("Nb de disciplines invalide : " + disciplines.size() + " pour " + prenom + ", " + nom);
        }
        this.disciplines = new HashSet<>(disciplines);
        this.numBureau = numBureau;
    }

    public abstract boolean peutEncadrer();
    public abstract void encadrer(Etudiant etudiant);

    /**
     * Vérifie si le Titulaire étudie une discipline donnée (pour savoir s'il peut encadrer un étudiant dans cette discipline)
     * @param discipline Discipline à vérifier
     * @return Renvoie True si le Titulaire étudie la discipline donnée, False sinon
     */
    @Override
    public boolean hasDiscipline(Discipline discipline) {
        return this.disciplines.contains(discipline);
    }

    /**
     * Réécriture de la méthode toString() qui permet d'afficher une instance de la classe Titulaire
     * @return Renvoie un affichage des informations du titulaire
     */
    @Override
    public String toString() {
        return "Titulaire{" +
                "nom=" + nom +
                ", prenom=" + prenom +
                ", age=" + age +
                ", ville=" + ville +
                ", discipline=" + disciplines +
                ", numBureau=" + numBureau +
                '}';
    }
}
