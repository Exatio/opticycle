package fr.opticycle.db;

import java.sql.*;

/**
 * Une instance DBAccessor permet de se connecter à une base de données, de faire des requêtes dessus et récupérer les résultats.
 */
public class DBAccessor {

    /**
     *Instance de DBAccessor pour ouvrir une connection
     */
    public static final DBAccessor INSTANCE = new DBAccessor();

    private Connection connection;

    /**
     * Constructeur par défaut
     */
    public DBAccessor() {}

    /**
     * Etabli une connection avec une base de données
     * @param dbUrl Adresse de la BDD
     * @param dbUser Nom d'utilisateur pour se connecter
     * @param dbPass Mot de passe pour se connecter
     * @throws SQLException Exception dans le cas ou la connection est refusée
     */
    public void openConnection(String dbUrl, String dbUser, String dbPass) throws SQLException {
        this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }

    /**
     * Ferme la connection à la base de données
     * @throws SQLException Exception si on n'arrive pas à fermer la connection
     */
    public void closeConnection() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }

    /**
     * Effectue une requête à la base de données
     * @param query Requête en SQL
     * @return Renvoie le résultat de la requête SQL
     * @throws SQLException Exception si la requête ne s'exécute pas correctement
     */
    public ResultSet query(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }



}
