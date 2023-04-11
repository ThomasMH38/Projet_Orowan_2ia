package com.example.demo;

import java.sql.*;

public class MoyenneFriction {

    private final Connection conn;

    public MoyenneFriction(Connection conn) {
        this.conn = conn;
    }


    public void executeMoyenneFriction(Connection conn) {
        try {


            // Récupération des données de la table CSV_output
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CSV_output");

            // Boucle sur les lignes de CSV_output par groupe de 5
            int rowCount = 0;
            double sumFriction = 0;
            int sumCas = 0;
            while (rs.next()) {
                rowCount++;
                sumFriction += rs.getDouble("Friction");
                System.out.println(sumFriction);
                sumCas += rs.getInt("Cas");
                if (rowCount == 5) {
                    // Calcul de la moyenne de Friction et Cas
                    double moyenneFriction = sumFriction / 5.0;
                    int moyenneCas = (int) Math.floor((double) sumCas / 5);
                    System.out.println("Moyenne friction = " + moyenneFriction);
                    // Insertion de la moyenne de Friction et Cas dans la table Coefficient_friction
                    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Coefficient_friction (id_coef_friction, coef_friction) VALUES (?, ?)");
                    pstmt.setInt(1, moyenneCas);
                    pstmt.setDouble(2, moyenneFriction);
                    pstmt.executeUpdate();

                    // Remise à zéro des compteurs
                    rowCount = 0;
                    sumFriction = 0;
                    sumCas = 0;
                }
            }

            // Fermeture de la connexion
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}