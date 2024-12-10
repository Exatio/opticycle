package fr.opticycle;

public class Etudiant extends Personne {

    private String sujetDeThese;
    private Discipline discipline;
    private int anneeDeThese;
    private Titulaire encadrant;

    public Etudiant(String nom, String prenom, int age, String ville, String sujetDeThese, Discipline discipline, int anneeDeThese, Titulaire encadrant) {
        super(nom, prenom, age, ville);
        this.sujetDeThese = sujetDeThese;
        this.discipline = discipline;
        if(!(1 <= anneeDeThese && anneeDeThese <= 3)) {
            System.out.println("Erreur année invalide : " + anneeDeThese + " pour " + nom + ", " + prenom);
            // throw Exception
        }
        this.anneeDeThese = anneeDeThese;
        this.encadrant = encadrant;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "sujetDeThese='" + sujetDeThese + '\'' +
                ", discipline=" + discipline +
                ", anneeDeThese=" + anneeDeThese +
                ", encadrant=" + encadrant +
                '}';
    }
}
