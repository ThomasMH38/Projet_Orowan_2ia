package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnexionBDD {
    private static final String URL = "jdbc:h2:./test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static List<Utilisateur> chercherUtilisateurs(String nom, String motDePasse) {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM Utilisateur WHERE Nom = ? AND MotDePasse = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, motDePasse);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String role = result.getString("Role");
                Utilisateur utilisateur = new Utilisateur(id, nom, motDePasse, role);
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }
}