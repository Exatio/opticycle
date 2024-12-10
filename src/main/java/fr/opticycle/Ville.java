package fr.opticycle;

public class Ville {
    private String nom;
    private int codePostal;
    private int population;
    private int superficie;
    private String departement; // int ?

    public Ville(String nom, int codePostal, int population, int superficie, String departement) {
        this.nom = nom;
        this.codePostal = codePostal;
        this.population = population;
        this.superficie = superficie;
        this.departement = departement;
    }
}
