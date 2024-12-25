package fr.opticycle.tsp;

import fr.opticycle.db.DBAccessor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Une instance Ville est une ville avec toutes ses informations
 */
public class Ville {
    private final String nom;
    private final String codePostal;
    private final int population2012;
    private final int superficie;
    private final float latitude;
    private final float longitude;
    private final String departement;

    /**
     * Crée une classe Ville content son code postal, sa population, sa superficie et son département
     * @param nom Nom de la ville
     * @param codePostal Code Postal de la ville
     * @param population2012 Nombre d'habitants en 2012 de la ville
     * @param superficie Superficie de la ville
     * @param departement Numéro du département auquel appartient la ville
     * @param latitude Latitude de la ville
     * @param longitude Longitude de la ville
     */
    public Ville(String nom, String codePostal, int population2012, int superficie, String departement, float latitude, float longitude) {
        this.nom = nom;
        this.codePostal = codePostal;
        this.population2012 = population2012;
        this.superficie = superficie;
        this.departement = departement;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Getter pour le nom de la ville
     * @return Renvoie le nom de la ville
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Getter pour le code postal de la ville
     * @return Renvoie le code postal de la ville
     */
    public String getCodePostal() {
        return this.codePostal;
    }

    /**
     * Getter pour la population en 2012 de la ville
     * @return Renvoie la population en 2012 de la ville
     */
    public int getPopulation2012() {
        return this.population2012;
    }

    /**
     * Getter pour le département de la ville
     * @return Renvoie le département de la ville
     */
    public String getDepartement() {
        return this.departement;
    }

    /**
     * Getter pour la latitude de la ville
     * @return Renvoie la latitude de la ville
     */
    public float getLatitude() {
        return this.latitude;
    }

    /**
     * Getter pour la longitude de la ville
     * @return Renvoie la longitude de la ville
     */
    public float getLongitude() {
        return this.longitude;
    }

    /**
     * Getter pour la superficie de la ville
     * @return Renvoie la superficie de la ville
     */
    public int getSuperficie() {
        return this.superficie;
    }

    /**
     * Récupère un objet Ville à partir de son identifiant dans la base de donnée
     * @param id Identifiant de la ville
     * @return Renvoie une instance Ville avec ses informations
     */
    public static Ville getVilleFromID(int id) {
        try (ResultSet res = DBAccessor.INSTANCE.query("SELECT v.ville_nom_reel, v.ville_code_postal, v.ville_population_2012, v.ville_surface, v.ville_departement, v.ville_latitude_deg, v.ville_longitude_deg FROM villes_france_free v WHERE v.ville_id = " + id)) {
            if(res.next()) {
                return new Ville(res.getString("ville_nom_reel"), res.getString("ville_code_postal"), res.getInt("ville_population_2012"), res.getInt("ville_surface"), res.getString("ville_departement"), res.getFloat("ville_latitude_deg"), res.getFloat("ville_longitude_deg"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la ville " + id);
        }
        return null;
    }

    /**
     * Récupère un objet ville à partir de son nom réel
     * @param nom_reel Nom réel de la ville
     * @return Renvoie une instance Ville avec ses informations
     */
    public static Ville getVilleFromNomReel(String nom_reel) {
        try (ResultSet res = DBAccessor.INSTANCE.query("SELECT v.ville_nom_reel, v.ville_code_postal, v.ville_population_2012, v.ville_surface, v.ville_departement, v.ville_latitude_deg, v.ville_longitude_deg FROM villes_france_free v WHERE v.ville_nom_reel = '" + nom_reel + "'")) {
            if(res.next()) {
                return new Ville(res.getString("ville_nom_reel"), res.getString("ville_code_postal"), res.getInt("ville_population_2012"), res.getInt("ville_surface"), res.getString("ville_departement"), res.getFloat("ville_latitude_deg"), res.getFloat("ville_longitude_deg"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la ville " + nom_reel);
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Méthode toString pour afficher les informations de la ville
     * @return Renvoie un affichage simple de la ville
     */
    @Override
    public String toString() {
        return nom + " (" + departement + ")";
    }

}
