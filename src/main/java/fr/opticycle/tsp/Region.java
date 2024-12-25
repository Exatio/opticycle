package fr.opticycle.tsp;

import fr.opticycle.db.DBAccessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Enumération Region contenant les régions de France
 */
public enum Region {
    /**
     * Guadeloupe
     */
    GUA("Guadeloupe"),
    /**
     *Martinique
     */
    MAR("Martinique"),
    /**
     *Guyane
     */
    GUY("Guyane"),
    /**
     *La Réunion
     */
    REU("La Réunion"),
    /**
     *Mayotte
     */
    MAY("Mayotte"),
    /**
     *Île-de-France
     */
    IDF("Île-de-France"),
    /**
     *Centre-Val de Loire
     */
    CVL("Centre-Val de Loire"),
    /**
     *Bourgogne-Franche-Comté
     */
    BFC("Bourgogne-Franche-Comté"),
    /**
     *Normandie
     */
    NOR("Normandie"),
    /**
     *Hauts-de-France
     */
    HDF("Hauts-de-France"),
    /**
     *Grand Est
     */
    GES("Grand Est"),
    /**
     *Pays de la Loire
     */
    PDL("Pays de la Loire"),
    /**
     *Bretagne
     */
    BRE("Bretagne"),
    /**
     *Nouvelle-Aquitaine
     */
    NAQ("Nouvelle-Aquitaine"),
    /**
     *Occitanie
     */
    OCC("Occitanie"),
    /**
     *Auvergne-Rhône-Alpes
     */
    ARA("Auvergne-Rhône-Alpes"),
    /**
     *Provence-Alpes-Côte d'Azur
     */
    PAC("Provence-Alpes-Côte d'Azur"),
    /**
     *Corse
     */
    COR("Corse"),
    ;

    private final String nom;
    private final Ville chefLieu;
    private final int superficie;
    private final int population2024;
    private final Set<String> departements;

    Region(String nom) {

        Ville chefLieu = null;
        int superficie = 0;
        int pop2024 = 0;
        Set<String> dpts = new HashSet<>();

        try (ResultSet res = DBAccessor.INSTANCE.query("SELECT dr.name, r.ville_id, r.superficie, r.population2024 FROM regionscheflieu r JOIN departmentsregions dr ON dr.region_code = r.code WHERE r.name = '" + nom.replaceAll("'", "''")+ "'")) {

            if(!res.next()) {
                throw new Exception("Pas de donnée trouvée pour cette région : " + nom);
            }

            chefLieu = Ville.getVilleFromID(res.getInt("ville_id"));
            superficie = res.getInt("superficie");
            pop2024 = res.getInt("population2024");

            do {
                dpts.add(res.getString("name")); // pour limiter le nb de requêtes on en fait une seule avec un join puis on itère sur la première colonne
            } while(res.next());

            res.close();

        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de la bdd pour la région " + nom);
            System.err.println(e.getMessage());
        }

        this.nom = nom;
        this.chefLieu = chefLieu;
        this.superficie = superficie;
        this.population2024 = pop2024;
        this.departements = dpts;
    }

    /**
     * Getter pour récupérer la région d'une ville donnée
     * @param ville Ville dont on souhaite connaître la région
     * @return Renvoie la région de la ville
     */
    public static Region getRegionByVille(String ville) {

        try(ResultSet resultSet = DBAccessor.INSTANCE.query("SELECT dep.departement_nom FROM departements dep JOIN villes_france_free v ON v.ville_departement = dep.departement_code WHERE v.ville_nom_reel = '" + ville.replaceAll("'", "''") + "'")) {

            if(resultSet.next()) {

                String departement = resultSet.getString("departement_nom");
                resultSet.close();

                for(Region region : values()) {
                    if(region.departements.contains(departement)) {
                        return region;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la région pour la ville " + ville);
            System.err.println(e.getMessage());
        }

        return null;
    }

    /**
     * Méthode toString pour afficher les informations de la région
     * @return Renvoie les informations de la région
     */
    @Override
    public String toString() {
        return "Region{" +
                "nom=" + nom +
                ", chefLieu=" + chefLieu +
                ", superficie=" + superficie +
                ", population2024=" + population2024 +
                ", departements=" + departements +
                '}';
    }
}
