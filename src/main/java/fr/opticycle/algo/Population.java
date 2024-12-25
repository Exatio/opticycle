package fr.opticycle.algo;

import fr.opticycle.tsp.Ville;

import java.util.*;

/**
 * Une instance Population est un ensemble d'individu
 */
public class Population {

    /**
     * Liste des individus dans la population
     */
    public final List<Individu> individus;

    /**
     * Créer une population en donnant ses individus
     * @param individus liste d'individus
     */
    public Population(List<Individu> individus){
        this.individus = new ArrayList<>(individus);
    }

    /**
     * Permet d'ajouter un individu à une Population
     * @param individu est une instance de la classe Individu
     */
    public void addIndividu(Individu individu) {
        this.individus.add(individu);
    }

    /**
     * Génère aléatoirement des individus de la population (cycle de villes)
     * @param villes les villes qui seront contenues dans les cycles (individus) générés
     * @param nbIndividus nombre d'individus à générer dans la population
     * @return Population générée
     */
    public static Population generatePopulation(List<Ville> villes, int nbIndividus) {

        List<Individu> individus = new ArrayList<>();
        for(int i = 0 ; i < nbIndividus ; i++) {
            individus.add(new Individu(villes));
            Collections.shuffle(villes);
        }

        return new Population(individus);
    }

    /**
     * Trouve l'individu avec la meilleure fitness dans une population
     * @return Renvoie cet individu
     */
    public Individu getMeilleurIndividu() {
        return (Individu) getXMeilleursIndividus(1).toArray()[0];
    }


    /**
     * Trouve les x individus avec la meilleure fitness dans une population
     * @param x Nombre d'individus à trouver
     * @return les x individus avec la meilleure fitness
     */
    public Set<Individu> getXMeilleursIndividus(int x) {
        HashSet<Individu> xMeilleurs = new HashSet<>();
        individus.stream().sorted(Comparator.comparingDouble(Individu::getFitness).reversed()).limit(x).forEach(xMeilleurs::add);
        return xMeilleurs;
    }

    /**
     * Trouve les x individus avec la pire fitness dans une population et les enlève de la population
     * @param x Nombre d'individus à enlever
     */
    public void removeXPiresIndividus(int x) {
        individus.stream().sorted(Comparator.comparingDouble(Individu::getFitness)).limit(x).forEach(individus::remove);
    }

    /**
     * Calcule la somme des fitness des individus d'une population
     * @return la somme des fitness des individus d'une population
     */
    public double totalFitness() {
        return individus.stream().mapToDouble(Individu::getFitness).sum();
    }

    /**
     * Sélectionne un individu au hasard proportionnellement à sa fitness
     * @return Renvoie l'individu sélectionné
     */
    private Individu selectionUnique() {
        double seuil = Math.random() * totalFitness();
        double sommeFitness = 0.0;

        for (Individu individu : individus) {
            sommeFitness += individu.getFitness();
            if (sommeFitness >= seuil) {
                return individu;
            }
        }

        throw new IllegalStateException("Ne devrait jamais arriver : aucun individu sélectionné");
    }

    /**
     * Sélectionne deux individus uniques au hasard proportionnellement à leur fitness
     * @return renvoie une liste contenant les deux individus
     */
    public List<Individu> selection() {

        Individu parent1 = selectionUnique();
        Individu parent2;
        do {
            parent2 = selectionUnique();

        } while (parent1 == parent2);

        return Arrays.asList(parent1, parent2);
    }


}