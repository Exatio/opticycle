package fr.opticycle;

import fr.opticycle.algo.AlgoGenetique;
import fr.opticycle.algo.Individu;
import fr.opticycle.db.DBAccessor;
import fr.opticycle.model.*;
import fr.opticycle.tsp.Ville;
import fr.opticycle.utils.Discipline;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Menu d'exécution de l'algorithme génétique sur un écosystème de recherche avec possiblité de le modifier ou de chercher une solution particulière
 */
public class Main {

    public static void main(String[] args) {

        try {
            DBAccessor.INSTANCE.openConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "aa");
        } catch (SQLException e) {
            System.out.println("Impossible de se connecter à la base de donnee");
            System.out.println(e.getMessage());
            return;
        }

        Chercheur Boris = new Chercheur("Haspot", "Boris", 30, Ville.getVilleFromNomReel("Ajaccio"), Set.of(Discipline.MATHEMATIQUES), 513);
        Chercheur Glass = new Chercheur("GLASS", "Olivier", 45, Ville.getVilleFromNomReel("Bordeaux"), Set.of(Discipline.MATHEMATIQUES, Discipline.DROIT), 300);
        MCF Vigeral = new MCF("Vigeral", "Guillaume", 30, Ville.getVilleFromNomReel("Toulouse"), Set.of(Discipline.MATHEMATIQUES, Discipline.SCIENCESSOCIALES), 526);
        MCF Lazard = new MCF("Lazard", "Emmanuel", 30, Ville.getVilleFromNomReel("Paris"), Set.of(Discipline.INFORMATIQUE, Discipline.GESTION), 999);
        Etudiant Dai = new Etudiant("Dai", "Ziyan", 20, Ville.getVilleFromNomReel("Marseille"), "peine de mort",Discipline.DROIT, 2, Glass);
        Etudiant Amiel = new Etudiant("Amiel", "Julien", 20, Ville.getVilleFromNomReel("Dijon"), "soft skills", Discipline.GESTION, 1, Lazard);
        Etudiant Hossain = new Etudiant("Hossain", "Nibir", 20, Ville.getVilleFromNomReel("Bobigny"), "inégalité salariales hommes femmes", Discipline.SCIENCESSOCIALES, 3, Vigeral);
        Etudiant Benziane = new Etudiant("Benziane", "Ilyes", 18, Ville.getVilleFromNomReel("Pontoise"), "tetriste", Discipline.INFORMATIQUE, 2, Lazard);
        Etudiant Zhang = new Etudiant("Zhang", "Franck", 20, Ville.getVilleFromNomReel("Rouen"), "lock in", Discipline.MATHEMATIQUES, 2, Boris);

        ArrayList<Personne> ecosysteme = new ArrayList<>(Arrays.asList(Boris, Glass, Vigeral, Lazard, Dai, Amiel, Hossain, Benziane, Zhang));

        Scanner scanner = new Scanner(System.in);

        int choix;

        do {
            System.out.println("-------------");
            System.out.println("Menu principal");
            System.out.println("--------------");
            System.out.println("Tapez 0 pour quitter le programme.");
            System.out.println("Tapez 1 pour voir l'ecosysteme de recherche actuel");
            System.out.println("Tapez 2 pour ajouter une personne a l'ecosysteme");
            System.out.println("Tapez 3 pour faire une recherche sur un cycle hamiltonien particulier");
            choix = getValidInt(scanner, "Votre choix : ");

            switch (choix) {
                case 0:
                    System.out.println("Au revoir !");
                    break;

                case 1:
                    System.out.println("Ecosysteme actuel :");
                    if (ecosysteme.isEmpty()) {
                        System.out.println("L'ecosysteme est vide.");
                    } else {
                        for (Personne personne : ecosysteme) {
                            System.out.println(personne);
                        }
                    }
                    break;

                case 2:
                    ajouterPersonne(scanner, ecosysteme);
                    break;

                case 3:
                    rechercheCycleHamiltonien(scanner, ecosysteme);
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
                    break;
            }
        } while (choix != 0);

        scanner.close();


        // Voir l'ensemble des chercheurs ayant des étudiants en thèse, ou l'ensemble des MCF travaillant sur une discipline particulière.

        try {
            DBAccessor.INSTANCE.closeConnection();
        } catch (SQLException e) {
            System.out.println("Impossible de fermer la connexion à la base de donnee");
            System.out.println(e.getMessage());
        }

    }

    /**
     * Recherche un cycle hamiltonien particulier dans l'écosystème de recherche
     * @param scanner Scanner pour lire l'entrée utilisateur
     * @param ecosysteme Liste des personnes de l'écosystème
     */
    public static void rechercheCycleHamiltonien(Scanner scanner, ArrayList<Personne> ecosysteme) {
        System.out.println("-------------");
        System.out.println("Recherche de cycle hamiltonien");
        System.out.println("-------------");

        if(ecosysteme.isEmpty()) {
            System.out.println("L'ecosysteme est vide.");
            return;
        }

        ArrayList<Personne> selection = new ArrayList<>(ecosysteme);

        do {
            System.out.println("1. Ajouter une condition sur le statut (MCF, Chercheur, Etudiant)");
            System.out.println("2. Ajouter une condition sur la discipline");
            System.out.println("3. Ajouter une condition sur l'age");
            System.out.println("4. Lancer la recherche");
            System.out.println("5. Retour au menu principal");

            int choix = getValidInt(scanner, "Votre choix : ");

            if(choix == 1) {
                System.out.println("1. MCF");
                System.out.println("2. Chercheur");
                System.out.println("3. Etudiant");
                int type = getValidInt(scanner, "Votre choix : ");
                selection.removeIf(p -> (type == 1 && !(p instanceof MCF)) || (type == 2 && !(p instanceof Chercheur)) || (type == 3 && !(p instanceof Etudiant)));
            } else if(choix == 2) {
                System.out.println("Disciplines possibles : " + Arrays.toString(Discipline.values()));
                Discipline discipline = null;
                while (discipline == null) {
                    try {
                        System.out.print("Entrez la discipline : ");
                        String input = scanner.nextLine().trim().toUpperCase();
                        discipline = Discipline.valueOf(input);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Discipline invalide. Veuillez reessayer.");
                    }
                }
                Discipline finalDiscipline = discipline;
                selection.removeIf(p -> !p.hasDiscipline(finalDiscipline));
            } else if(choix == 3) {
                System.out.println("Age egal, minimum ou maximum ? ");
                System.out.println("1. Egal");
                System.out.println("2. Minimum");
                System.out.println("3. Maximum");
                int type = getValidInt(scanner, "Votre choix : ");

                if(type == 1) {
                    int age = getValidInt(scanner, "Entrez l'age : ");
                    selection.removeIf(p -> p.getAge() != age);
                } else if(type == 2) {
                    int age = getValidInt(scanner, "Entrez l'age minimum : ");
                    selection.removeIf(p -> p.getAge() <= age);
                } else if(type == 3) {
                    int age = getValidInt(scanner, "Entrez l'age maximum : ");
                    selection.removeIf(p -> p.getAge() >= age);
                }

            }

            if(choix == 4) break;
            if(choix == 5) return;

        } while (true);

        ArrayList<Ville> villes = new ArrayList<>();
        for(Personne personne : selection) {
            villes.add(personne.getVille());
        }

        System.out.println("Lancement de l'algorithme genetique...");
        System.out.println("Taille de la population ? ");
        int taillePopulation;
        do {
            taillePopulation = getValidInt(scanner, "Votre choix : ");
            if(taillePopulation < 2) {
                System.out.println("La taille de la population doit etre superieure a 1.");
            }
        } while (taillePopulation < 2);

        System.out.println("Probabilite (en chiffre a virgules, par exemple 0,1) de mutation ? ");

        float probaMutation;
        do {
            probaMutation = getValidFloat(scanner, "Votre choix : ");
            if(probaMutation < 0 || probaMutation > 1) {
                System.out.println("La probabilite doit etre comprise entre 0 et 1.");
            }
        } while (probaMutation < 0 || probaMutation > 1);


        System.out.println("Pourcentage (en chiffre a virgules, par exemple 0,1) d'individus remplaces ? ");
        float rateElite;
        do {
            rateElite = getValidFloat(scanner, "Votre choix : ");
            if(rateElite < 0 || rateElite > 1) {
                System.out.println("Le pourcentage doit etre compris entre 0 et 1.");
            }
        } while (rateElite < 0 || rateElite > 1);

        System.out.println("Distance suffisante pour arreter l'algorithme ? ");
        int distanceMax = getValidInt(scanner, "Votre choix : ");

        System.out.println("Nombre de generations maximum ? ");
        int nbGenerations = getValidInt(scanner, "Votre choix : ");

        Individu resultat = AlgoGenetique.geneticAlgorithm(villes, taillePopulation, probaMutation, rateElite, distanceMax, nbGenerations);
        System.out.println("Resultat : ");
        System.out.println(resultat.getDistance() + " km, cycle : " + resultat.getCycle());

        System.out.print("Souhaitez-vous enregistrer le resultat dans un fichier ? (O/N) : ");
        boolean enregistrer = scanner.nextLine().equalsIgnoreCase("O");
        if(!enregistrer) return;

        System.out.println("Veuillez entrer le chemin du fichier :");
        String s = scanner.nextLine();

        try {
            resultat.toFile(s);
        } catch (IOException e) {
            System.out.println("Impossible d'ecrire dans le fichier");
        }

    }

    /**
     * Ajoute une personne à l'écosystème (MCF, Chercheur ou Etudiant)
     * @param scanner Scanner pour lire l'entrée utilisateur
     * @param ecosysteme Liste des personnes de l'écosystème
     */
    public static void ajouterPersonne(Scanner scanner, ArrayList<Personne> ecosysteme) {
        System.out.println("Quel type de personne voulez-vous ajouter ?");
        System.out.println("1. MCF");
        System.out.println("2. Chercheur");
        System.out.println("3. Etudiant");
        System.out.println("4. Retour au menu principal");

        int type = getValidInt(scanner, "Votre choix : ");

        if(type == 4) return;

        System.out.print("Entrez le nom : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le prenom : ");
        String prenom = scanner.nextLine();

        int age = getValidInt(scanner, "Entrez l'age : ");
        Ville ville;
        do {
            System.out.print("Entrez la ville : ");
            ville = Ville.getVilleFromNomReel(scanner.nextLine());
        } while (ville == null);

        if(type == 1 || type == 2) {
            int numBureau = getValidInt(scanner, "Entrez le numero de bureau : ");

            int nbDisciplines;
            do {
                nbDisciplines = getValidInt(scanner, "Une ou deux disciplines ? (1/2) : ");
            } while (nbDisciplines != 1 && nbDisciplines != 2);

            System.out.println("Disciplines possibles : " + Arrays.toString(Discipline.values()));
            Set<Discipline> disciplines = new HashSet<>();
            for (int i = 0; i < nbDisciplines; i++) {
                Discipline discipline = null;
                while (discipline == null) {
                    try {
                        System.out.print("Entrez la discipline " + (i + 1) + " : ");
                        String input = scanner.nextLine().trim().toUpperCase();
                        discipline = Discipline.valueOf(input);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Discipline invalide. Veuillez reessayer.");
                    }
                }
                disciplines.add(discipline);
            }

            if (type == 1) {
                ecosysteme.add(new MCF(nom, prenom, age, ville, disciplines, numBureau));
                System.out.println("MCF ajoute avec succes !");
            } else {
                ecosysteme.add(new Chercheur(nom, prenom, age, ville, disciplines, numBureau));
                System.out.println("Chercheur ajoute avec succes !");
            }
        } else if (type == 3) {

            System.out.print("Quel est le sujet de these de cet etudiant ? ");
            String sujet = scanner.nextLine();

            int annee;
            do {
                annee = getValidInt(scanner, "Entrez l'annee de these (1 a 3) : ");
            } while (annee < 1 || annee > 3);

            Discipline discipline = null;
            while (discipline == null) {
                try {
                    System.out.print("Quelle discipline etudie cet etudiant ? ");
                    String input = scanner.nextLine().trim().toUpperCase();
                    discipline = Discipline.valueOf(input);
                } catch (IllegalArgumentException e) {
                    System.out.println("Discipline invalide. Veuillez reessayer.");
                    System.out.println("Disciplines possibles : " + Arrays.toString(Discipline.values()));
                }
            }

            System.out.println("Qui est le titulaire encadrant cet etudiant ?");
            System.out.println("Valeurs possibles : ");
            ArrayList<Integer> ids = new ArrayList<>();
            for(Personne personne : ecosysteme) {
                if(personne instanceof Titulaire && ((Titulaire) personne).peutEncadrer() && personne.hasDiscipline(discipline)) {
                    System.out.println(personne.getID() + " : " + personne.getNom() + " " + personne.getPrenom());
                    ids.add(personne.getID());
                }
            }

            if(ids.isEmpty()) {
                System.out.println("Aucun titulaire ne peut encadrer cet etudiant. Veuillez en creer un avant de continuer.");
                return;
            }

            int id;
            do {
                id = getValidInt(scanner, "Votre choix : ");
            } while (!ids.contains(id));

            int finalID = id;
            Titulaire titulaire = (Titulaire) ecosysteme.stream().filter(p -> p.getID() == finalID).findFirst().get();

            Etudiant etudiant = new Etudiant(nom, prenom, age, ville, sujet, discipline, annee, titulaire);
            ecosysteme.add(etudiant);
            titulaire.encadrer(etudiant);
            System.out.println("Etudiant ajoute avec succes !");
        } else {
            System.out.println("Type invalide.");
        }
    }

    /**
     * Demande à l'utilisateur de rentrer un entier valide
     * @param scanner L'objet Scanner qui permet de lire l'entrée utilisateur
     * @param message Message à afficher
     * @return L'entier rentré par l'utilisateur
     */
    public static int getValidInt(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                int i = scanner.nextInt();
                scanner.nextLine();
                return i;
            } catch (InputMismatchException e) {
                System.out.println("Entree invalide. Veuillez entrer un nombre.");
                scanner.next();
            }
        }
    }

    /**
     * Demande à l'utilisateur de rentrer un chiffre à virgules valide
     * @param scanner L'objet Scanner qui permet de lire l'entrée utilisateur
     * @param message Message à afficher
     * @return Le chiffre à virgules rentré par l'utilisateur
     */
    public static float getValidFloat(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                float i = scanner.nextFloat();
                scanner.nextLine();
                return i;
            } catch (InputMismatchException e) {
                System.out.println("Entree invalide. Veuillez entrer un nombre a virgules (ex: 45,6)");
                scanner.next();
            }
        }
    }

}