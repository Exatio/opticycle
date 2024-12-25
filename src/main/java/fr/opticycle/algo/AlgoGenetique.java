package fr.opticycle.algo;

import fr.opticycle.tsp.Ville;

import java.util.*;

/**
 * Une instance AlgoGenetique est une exécution de l'algorithem génétique avec des paramètres donnés dans la méthode geneticAlgorithm
 */
public class AlgoGenetique {

    /**
     * Execute l'algorithme génétique sur une population pour trouver le meilleur individu
     * @param individus Population sur laquelle on effectue l'algorithme
     * @param sizepop taille de la population
     * @param probMutate probabilité que l'individu subisse une mutation
     * @param rateElite pourcentage d'individu qui doit être remplacé
     * @param distanceCap Distance qu'on souhaiterait ne pas dépasser (fitness à atteindre)
     * @param n nombre d'itérations maximum de l'algorithme
     * @return Renvoie le meilleur individu de la population
     */
    public static Individu geneticAlgorithm(List<Ville> individus, int sizepop, float probMutate, float rateElite, double distanceCap, int n) {

        Population population = Population.generatePopulation(individus, sizepop);

        for(int i = 0 ; i < n && population.getMeilleurIndividu().getDistance() > distanceCap ; i++) {

            Population nouvellePopulation = new Population(new ArrayList<>());

            for (int j = 0; j < sizepop / 2 + 1; j++) {

                List<Individu> parents = population.selection();
                Individu parent1 = parents.get(0);
                Individu parent2 = parents.get(1);

                List<Individu> enfants = crossover(parent1, parent2);
                Individu child1 = enfants.get(0);
                Individu child2 = enfants.get(1);

                if (Math.random() <= probMutate) {
                    child1.mutate();
                }
                if (Math.random() <= probMutate) {
                    child2.mutate();
                }

                nouvellePopulation.addIndividu(child1);
                nouvellePopulation.addIndividu(child2);
            }

            int nbElite = Math.round(sizepop * rateElite);

            nouvellePopulation.removeXPiresIndividus(nbElite);
            population.getXMeilleursIndividus(nbElite).forEach(nouvellePopulation::addIndividu);
            population = nouvellePopulation;
        }

        return population.getMeilleurIndividu();
    }


    private static void copyToChild(List<Ville> parent1, List<Ville> parent2, List<Ville> child1, List<Ville> child2, int index, List<Integer> updatedIndices) {
        if(child1.get(index) == null) {
            child1.set(index, parent1.get(index));
            child2.set(index, parent2.get(index));
            updatedIndices.add(index);
        }

    }

    /**
     * Génère deux nouveaux individus (fils) à partir de deux individus de la population (parents) en faisant un crossover
     * @param parent1 premier individu de base
     * @param parent2 deuxieme individu de base
     * @return Renvoie les deux nouveaux individus issus du crossover
     */
    public static List<Individu> crossover(Individu parent1, Individu parent2) {

        int cycleSize = parent1.getCycle().size();
        Random rand = new Random();
        List<Ville> child1 = new ArrayList<>(Collections.nCopies(cycleSize, null));
        List<Ville> child2 = new ArrayList<>(Collections.nCopies(cycleSize, null));

        for (int i = 0; i < cycleSize; i++) {

            if(child1.get(i) != null) continue;

            boolean useParent1 = rand.nextBoolean();

            child1.set(i, useParent1 ? parent1.getCycle().get(i) : parent2.getCycle().get(i));
            child2.set(i, useParent1 ? parent2.getCycle().get(i) : parent1.getCycle().get(i));

            List<Integer> updatedIndices = new ArrayList<>();
            updatedIndices.add(i);

            while(!updatedIndices.isEmpty()) {
                int index = updatedIndices.get(0);
                updatedIndices.remove(0);

                List<Ville> par1 = new ArrayList<>(parent1.getCycle());
                List<Ville> par2 = new ArrayList<>(parent2.getCycle());

                par1.set(i, null);
                par2.set(i, null);

                if(par1.contains(child1.get(index))) copyToChild(par1, par2, child2, child1, par1.indexOf(child1.get(index)), updatedIndices);
                else if(par1.contains(child2.get(index))) copyToChild(par1, par2, child1, child2, par1.indexOf(child2.get(index)), updatedIndices);

                if(par2.contains(child1.get(index))) copyToChild(par1, par2, child1, child2, par2.indexOf(child1.get(index)), updatedIndices);
                else if(par2.contains(child2.get(index))) copyToChild(par1, par2, child2, child1, par2.indexOf(child2.get(index)), updatedIndices);
            }

        }

        return Arrays.asList(new Individu(child1), new Individu(child2));
    }

}
