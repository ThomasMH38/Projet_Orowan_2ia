package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        int id = Integer.parseInt(columns[0]);
        String nom = columns[1];
        String motDePasse = columns[2];
        String role = columns[3];

        try {
            PreparedStatement insertion = dbConnection.prepareStatement(
                    "INSERT INTO Utilisateur (id, Nom, MotDePasse, Role) " +
                            "VALUES (?, ?, ?, ?)");

            insertion.setInt(1, id);
            insertion.setString(2, nom);
            insertion.setString(3, motDePasse);
            insertion.setString(4, role);

            insertion.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
