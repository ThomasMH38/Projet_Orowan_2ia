package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Utilisateurs extends Bdd{

    public Utilisateurs(){
        super(Bdd.dbConnection);
    }

    @Override
    public Collection<String> retrieve() {

        ArrayList<String> utilisateurs = new ArrayList<>();

        try (Statement st = dbConnection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM Utilisateur");
            while (rs.next()) {
                String row = rs.getInt("id") + ", " +
                        rs.getString("Nom") + ", " +
                        rs.getString("MotDePasse") + ", " +
                        rs.getString("Role");
                utilisateurs.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    @Override
    public void insert(String[] columns) {

        //int id = Integer.parseInt(columns[0]);
        String nom = columns[1];
        String motDePasse = columns[2];
        String role = columns[3];

        try {
            PreparedStatement insertion = dbConnection.prepareStatement(
                    /*"INSERT INTO Utilisateur (id, Nom, MotDePasse, Role) " +
                            "VALUES (?, ?, ?, ?)");*/
                    "INSERT INTO Utilisateur (Nom, MotDePasse, Role) " +
                    "VALUES (?, ?, ?)");

            //insertion.setInt(1, id);
            insertion.setString(1, nom);
            insertion.setString(2, motDePasse);
            insertion.setString(3, role);

            insertion.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement deletion = dbConnection.prepareStatement("DELETE FROM Utilisateur WHERE id=?");
            deletion.setInt(1, id);
            deletion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verifConnexion(String nom, String motDePasse) {
        String query = "SELECT COUNT(*) FROM Utilisateur WHERE Nom = ? AND MotDePasse = ?";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(query)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, motDePasse);
            ResultSet rs = pstmt.executeQuery();
            // retourne la valeur de la première colonne de la ligne courante du résultat de la requête SQL sous forme d'un entier
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error during authentication: " + e.getMessage());
            return false;
        }
    }

    public boolean verifSameName(String nom) {
        String query = "SELECT COUNT(*) FROM Utilisateur WHERE Nom = ?";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(query)) {
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            // retourne la valeur de la première colonne de la ligne courante du résultat de la requête SQL sous forme d'un entier
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error during authentication: " + e.getMessage());
            return false;
        }
    }

    public String getRole(String nom, String motDePasse) {
        String role = null;
        String query = "SELECT Role FROM Utilisateur WHERE Nom = ? AND MotDePasse = ?";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(query)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, motDePasse);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("Role");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error during role retrieval: " + e.getMessage());
        }
        System.out.println(role);
        return role;
    }



    public void changeRole(String nom, String nouveauRole) {
        try {
            String query = "UPDATE Utilisateur SET Role = ? WHERE Nom = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, nouveauRole);
            preparedStatement.setString(2, nom);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error during role change: " + e.getMessage());
        }
    }

}
