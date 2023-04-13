package com.example.demo;

import org.h2.jdbcx.JdbcDataSource;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Collection;

public abstract class Bdd {

    static Connection dbConnection;

    public Bdd(Connection dbConnection){
        this.dbConnection = dbConnection;
    }

    public void openDBConnection(String url, String user, String password) {
        JdbcDataSource dataSource = new JdbcDataSource();

        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        try {
            dbConnection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void clearDB() {
        try {
            Statement stmt = dbConnection.createStatement();

            stmt.executeUpdate("DELETE FROM File_format;");
            stmt.executeUpdate("DELETE FROM CSV_input;");
            stmt.executeUpdate("DELETE FROM CSV_output;");
            stmt.executeUpdate("DELETE FROM Coefficient_friction;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void display() {

        Collection<String> table = retrieve();

        for (String s : table) {
            System.out.println(s);
        }
    }

    public abstract Collection<String> retrieve();

    public abstract void insert(String[] columns);

    public abstract void delete(int id);

    public void convertTableToTxt(String tableName, String txtFilePath) {

        try (Statement st = dbConnection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);
             FileWriter writer = new FileWriter(txtFilePath)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Write headers to file
            for (int i = 1; i <= columnCount; i++) {
                writer.write(metaData.getColumnLabel(i));
                if (i < columnCount) {
                    writer.write("\t");
                } else {
                    writer.write("\n");
                }
            }

            // Write data to file
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    writer.write(rs.getString(i));
                    if (i < columnCount) {
                        writer.write("\t");
                    } else {
                        writer.write("\n");
                    }
                }
            }

            System.out.println("Conversion completed successfully.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void moyenneFriction(){

        try {
            // Récupération des données de la table CSV_output
            Statement stmt = dbConnection.createStatement();
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
                    PreparedStatement pstmt = dbConnection.prepareStatement("INSERT INTO Coefficient_friction (id_coef_friction, coef_friction) VALUES (?, ?)");
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
