package fr.opticycle;

public class Personne {
    private static int nbPersonnes = 0;

    private final int ID;
    private String nom;
    private String prenom;
    private int age;
    private String ville;

    public Personne(String nom, String prenom, int age, String ville) {
        this.ID = nbPersonnes++;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.ville = ville;
    }

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

    public String getVille() {
        return this.ville;
    }
}
