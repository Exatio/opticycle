package fr.opticycle.model;

import fr.opticycle.tsp.Ville;
import fr.opticycle.utils.Discipline;

/**
 * Une instance Etudiant est une personne qui effectue une thèse sur 3 ans dans une discipline accompagné d'un encadrant
 */
public class Etudiant extends Personne {

    private final String sujetDeThese;
    private final Discipline discipline;
    private final int anneeDeThese;
    private final Titulaire encadrant;

    /**
     * Créer un étudiant qui dérive de Personne
     * @param nom Nom de la personne
     * @param prenom Prénom de la personne
     * @param age Age de la personne
     * @param ville Ville où réside la personne
     * @param sujetDeThese Sujet de these de l'étudiant
     * @param discipline Discipline étudiée par l'étudiant
     * @param anneeDeThese Année de thèse de l'étudiant
     * @param encadrant Titulaire de l'étudiant
     */
    public Etudiant(String nom, String prenom, int age, Ville ville, String sujetDeThese, Discipline discipline, int anneeDeThese, Titulaire encadrant) {
        super(nom, prenom, age, ville);
        this.sujetDeThese = sujetDeThese;
        this.discipline = discipline;
        if(!(1 <= anneeDeThese && anneeDeThese <= 3)) {
            System.out.println("Erreur annee de these invalide : " + anneeDeThese + " pour " + nom + ", " + prenom);
        }
        this.anneeDeThese = anneeDeThese;
        this.encadrant = encadrant;
        this.encadrant.encadrer(this);
    }

    public boolean hasDiscipline(Discipline discipline) {
        return this.discipline == discipline;
    }

    /**
     * Réécriture de la méthode toString() qui permet d'afficher une instance de la classe Etudiant
     * @return Renvoie un affichage des informations de l'étudiant
     */
    @Override
    public String toString() {
        return "Etudiant{" +
                "nom=" + nom +
                ", prenom=" + prenom +
                ", age=" + age +
                ", ville=" + ville +
                ", sujetDeThese='" + sujetDeThese + '\'' +
                ", discipline=" + discipline +
                ", anneeDeThese=" + anneeDeThese +
                ", encadrant='" + encadrant.getNom() + " " + encadrant.getPrenom() + "'" +
                '}';
    }
}
