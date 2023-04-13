/*package com.example.demo;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControleurFenetreProcessEngineer {

  private Connection dbConnection;

    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Méthode pour valider la nouvelle valeur de Enthick_min
    public void validerEnThickMin(String newValue) {
        // Appeler la méthode pour mettre à jour la contrainte dans la base de données
        updateEnThickMinConstraintInDatabase(newValue);
    }

    // Méthode pour mettre à jour la contrainte Enthick dans la base de données
    private void updateEnThickMinConstraintInDatabase(String newValue) {
        // Assumer que la connexion à la base de données est déjà établie
        // Préparer la requête SQL pour mettre à jour la contrainte
        String sql = "UPDATE File_format SET Enthick_min = ?;";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setString(1, newValue);
            // Exécuter la requête
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions liées à la base de données
        }
    }
}*/



