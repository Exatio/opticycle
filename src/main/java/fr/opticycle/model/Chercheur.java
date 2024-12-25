package fr.opticycle.model;

import fr.opticycle.tsp.Ville;
import fr.opticycle.utils.Discipline;

import java.util.HashSet;
import java.util.Set;

/**
 * Une instance Chercheur est un titulaire qui est une personne, il encadre autant d'étudiants qu'il veut
 */
public class Chercheur extends Titulaire {
    
    private final Set<Etudiant> etudiants;

    /**
     * Créer un chercheur qui dérive de Titulaire qui dérive de Personne
     * @param nom Nom de la personne
     * @param prenom Prénom de la personne
     * @param age Age de la personne
     * @param ville Ville où réside la personne
     * @param disciplines Disciplines que le chercheur étudie
     * @param numBureau Numéro du bureau du chercheur
     */
    public Chercheur(String nom, String prenom, int age, Ville ville, Set<Discipline> disciplines, int numBureau) {
        this(nom, prenom, age, ville, disciplines, numBureau, new HashSet<>());
    }

    /**
     * Créer un chercheur qui dérive de Titulaire qui dérive de Personne et qui encadre des étudiants
     * @param nom Nom de la personne
     * @param prenom Prénom de la personne
     * @param age Age de la personne
     * @param ville Ville où réside la personne
     * @param disciplines Disciplines que le chercheur étudie
     * @param numBureau Numéro du bureau du chercheur
     * @param etudiants Liste des étudiants sous la supervision du chercheur (autant qu'il le souhaite)
     */
    public Chercheur(String nom, String prenom, int age, Ville ville, Set<Discipline> disciplines, int numBureau, Set<Etudiant> etudiants) {
        super(nom, prenom, age, ville, disciplines, numBureau);
        this.etudiants = new HashSet<>(etudiants);
    }

    /**
     * Vérifie si un chercheur encadre un étudiant ou non
     * @return Renvoie un booléen True si le chercheur encadre un étudiant False sinon
     */
    public boolean hasEtudiant() {
        return !etudiants.isEmpty();
    }

    /**
     * Getter pour la liste des étudiants encadrés par le chercheur
     * @return Renvoie la liste des étudiants encadrés par le chercheur
     */
    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    /**
     * Un chercheur peut encadrer autant d'élèves qu'il le souhaite
     * @return True
     */
    @Override
    public boolean peutEncadrer() {
        return true;
    }

    /**
     * Ajoute un étudiant à la liste des étudiants encadrés par le chercheur
     * @param etudiant Etudiant à encadrer
     */
    @Override
    public void encadrer(Etudiant etudiant) {
        this.etudiants.add(etudiant);
    }

    /**
     * Réécriture de la méthode toString() qui permet d'afficher une instance de la classe Chercheur
     * @return Renvoie un affichage des informations du chercheur
     */
    @Override
    public String toString() {
        return "Chercheur{" +
                "nom=" + nom +
                ", prenom=" + prenom +
                ", age=" + age +
                ", ville=" + ville +
                ", disciplines=" + disciplines +
                ", numBureau=" + numBureau +
                ", etudiants=" + etudiants +
                '}';
    }

}
