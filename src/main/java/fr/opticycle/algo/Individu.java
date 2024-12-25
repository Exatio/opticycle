package fr.opticycle.algo;

import fr.opticycle.tsp.Ville;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Une instance Individu est un cycle hamiltonien solution du problème TSP
 */
public class Individu {
    private final List<Ville> cycle;
    private double fitness;

    /**
     * Créer un Individu composé d'une liste de villes et de sa fitness
     * @param cycle liste ordonnée de villes
     */
    public Individu(List<Ville> cycle) {
        this.cycle = new ArrayList<>(cycle);
        updateFitness();
    }

    /**
     * Calcule et met à jour la fitness d'un individu, soit la somme des distances entre les villes
     */
    public void updateFitness() {
        this.fitness = 0;
        for (int i = 0; i < cycle.size(); i++) {
            Ville ville1 = cycle.get(i);
            Ville ville2 = cycle.get((i + 1) % cycle.size()); // when i + 1 = cycle_size(), we'll have i = cycle.size() - 1 and i + 1 = 0, that means we connect the last city to the first
            this.fitness += 2 * 6371 * Math.asin(Math.sqrt(Math.pow(Math.sin(Math.toRadians(ville2.getLatitude() - ville1.getLatitude()) / 2), 2) + Math.cos(Math.toRadians(ville1.getLatitude())) * Math.cos(Math.toRadians(ville2.getLatitude())) * Math.pow(Math.sin(Math.toRadians(ville2.getLongitude() - ville1.getLongitude()) / 2), 2)));
        }
        this.fitness = 1 / this.fitness;
    }

    /**
     * Effectue une mutation sur l'instance courante d'individu, soit une permutation de place entre deux villes de l'individu
     */
    public void mutate() {
        // Swap 2 random villes
        Random rand = new Random();
        int i, j = rand.nextInt(this.cycle.size());
        do {
            i = rand.nextInt(this.cycle.size());
        } while (i == j);
        Collections.swap(this.cycle, i, j);

        // update fitness
        updateFitness();
    }

    /**
     * Récupère la liste des villes d'un individu
     * @return Renvoie la liste
     */
    public List<Ville> getCycle() {
        return this.cycle;
    }

    /**
     * Récupère la fitness d'un individu
     * @return Renvoie la fitness (1/distance : plus la fitness est grande, plus la distance est petite)
     */
    public double getFitness() {
        return this.fitness;
    }

    /**
     * Récupère la distance totale du cycle
     * @return Renvoie la distance totale
     */
    public double getDistance() {
        return 1/this.fitness;
    }

    /**
     * Permet d'écrire dans un fichier toutes les villes du cycle et la distance totale à parcourir
     */
    public void toFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename), true))) {
            writer.write("Distance totale à parcourir : " + getDistance() + " km");
            writer.newLine();

            for (Ville v : getCycle()) {
                writer.write(v.getNom() + " (" + v.getDepartement() + ")");
                writer.newLine();
            }
        }
    }


    /**
     * Réécriture de la méthode toString() qui permet d'afficher une instance de la classe Individu
     * @return Renvoie un affichage de la distance totale du cycle de l'individu
     */
    @Override
    public String toString() {
        return "Individu [distance=" + getDistance() + "]";
    }

}