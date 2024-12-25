package fr.opticycle.model;

import fr.opticycle.tsp.Ville;
import fr.opticycle.utils.Discipline;

/**
 * Une instance Personne est une personne avec ses informations
 */
public abstract class Personne {
    private static int nbPersonnes = 0;

    private final int ID;
    protected final String nom;
    protected final String prenom;
    protected final int age;
    protected final Ville ville;

    /**
     * Créer une personne avec son nom, son prénom, son âge, sa ville
     * @param nom Nom de la personne
     * @param prenom Prénom de la personne
     * @param age Age de la personne
     * @param ville Ville où réside la personne
     */
    public Personne(String nom, String prenom, int age, Ville ville) {
        this.ID = nbPersonnes++;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.ville = ville;
    }

    /**
     * Récupère le nom de la personne
     * @return Renvoie le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Récupère le prénom de la personne
     * @return Renvoie le prénom
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Récupère l'âge de la personne
     * @return Renvoie l'âge
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Récupère l'ID de la personne
     * @return Renvoie l'ID
     */
    public int getID() {
        return this.ID;
    }

    public abstract boolean hasDiscipline(Discipline discipline);

    /**
     * Réécriture de la méthode toString() qui permet d'afficher une instance de la classe Personne
     * @return Renvoie un affichage des informations d'une personne
     */
    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", ville='" + ville + '\'' +
                ", ID=" + ID +
                ", nbPersonnes=" + nbPersonnes +
                '}';
    }

    /**
     * Récupère la ville de la personne
     * @return Renvoie la ville
     */
    public Ville getVille() {
        return this.ville;
    }
}
