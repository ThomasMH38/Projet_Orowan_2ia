package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CoefficientFrictions extends Bdd{



    public CoefficientFrictions(){
        super(Bdd.dbConnection);
    }

    public Collection<String> retrieve(){

        ArrayList<String> coefficientFrictions = new ArrayList<>();

        try (Statement st = dbConnection.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT * FROM Coefficient_friction");

            while (rs.next()) {
                String row = rs.getInt(1) + ", " +
                        rs.getInt(2);

                coefficientFrictions.add(row);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return coefficientFrictions;
    }

    @Override
    public void insert(String[] columns) {
        int id = Integer.parseInt(columns[0]);
        int coeff = Integer.parseInt(columns[1]);

        try {
            PreparedStatement insertion = dbConnection.prepareStatement(
                    "INSERT INTO COEFFICIENT_FRICTION (id_coef_friction, coef_friction) " +
                            "VALUES (?, ?)");
            //L'ex�cution des requ�tes de modification est d�clench�e par la m�thode executeUpdate
            insertion.setInt(1, id);
            insertion.setDouble(2, coeff);

            insertion.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement deletion = dbConnection.prepareStatement("DELETE FROM COEFFICIENT_FRICTION WHERE id_coef_friction=?");
            deletion.setInt(1, id);
            deletion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
