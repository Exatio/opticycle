package fr.opticycle.model;

import fr.opticycle.tsp.Ville;
import fr.opticycle.utils.Discipline;

import java.util.Set;

/**
 * Une instance MCF est un maître de conférence qui est un titulaire qui est une personne, il encadre 0 ou 1 étudiant
 */
public class MCF extends Titulaire {

    private Etudiant etudiant;

    /**
     * Crée un objet représentant un.e maître.sse de conférences
     * @param nom Nom de la personne
     * @param prenom Prénom de la personne
     * @param age Age de la personne
     * @param ville Ville où réside la personne
     * @param disciplines Disciplines que le MCF étudie
     * @param numBureau Numéro du bureau du MCF
     */
    public MCF(String nom, String prenom, int age, Ville ville, Set<Discipline> disciplines, int numBureau) {
        super(nom, prenom, age, ville, disciplines, numBureau);
    }

    /**
     * Crée un MCF qui encadre un étudiant
     * @param nom Nom de la personne
     * @param prenom Prénom de la personne
     * @param age Age de la personne
     * @param ville Ville où réside la personne
     * @param disciplines Disciplines que le MCF étudie
     * @param numBureau Numéro du bureau du MCF
     * @param etudiant Etudiant encadré par le MCF
     */
    public MCF(String nom, String prenom, int age, Ville ville, Set<Discipline> disciplines, int numBureau, Etudiant etudiant) {
        super(nom, prenom, age, ville, disciplines, numBureau);
        this.etudiant = etudiant;
    }

    /**
     * Un MCF ne peut encadrer que 0 ou 1 personne
     * @return True si le MCF peut encadrer un étudiant, False sinon
     */
    @Override
    public boolean peutEncadrer() {
        return this.etudiant == null;
    }

    /**
     * Spécifie que l'élève est encadré par le MCF
     * @param etudiant Etudiant à encadrer
     */
    @Override
    public void encadrer(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    /**
     * Réécriture de la méthode toString() qui permet d'afficher une instance de la classe MCF
     * @return Renvoie un affichage des informations du MCF
     */
    @Override
    public String toString() {
        return "MCF{" +
                "nom=" + nom +
                ", prenom=" + prenom +
                ", age=" + age +
                ", ville=" + ville +
                ", disciplines=" + disciplines +
                ", numBureau=" + numBureau +
                ", etudiant=" + etudiant +
                '}';
    }
}
