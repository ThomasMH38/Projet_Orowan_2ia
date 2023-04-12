package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class CsvOutputs extends Bdd{

    public CsvOutputs(){
        super(Bdd.dbConnection);
    }

    @Override
    public Collection<String> retrieve() {

        ArrayList<String> csvOutputs = new ArrayList<>();

        try (Statement st = dbConnection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM CSV_output");
            while (rs.next()) {
                String row = rs.getInt("Cas") + ", " +
                        rs.getString("Errors") + ", " +
                        rs.getDouble("OffsetYield") + ", " +
                        rs.getDouble("Friction") + ", " +
                        rs.getDouble("Rolling_Torque") + ", " +
                        rs.getDouble("Sigma_Moy") + ", " +
                        rs.getDouble("Sigma_Ini") + ", " +
                        rs.getDouble("Sigma_Out") + ", " +
                        rs.getDouble("Sigma_Max") + ", " +
                        rs.getDouble("Force_Error") + ", " +
                        rs.getDouble("Slip_Error") + ", " +
                        rs.getString("Has_Converged");
                csvOutputs.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return csvOutputs;
    }

    @Override
    public void insert(String[] columns) {
        int cas = Integer.parseInt(columns[0]);
        String errors = columns[1];
        double offsetYield = Double.parseDouble(columns[2]);
        double friction = Double.parseDouble(columns[3]);
        double rollingTorque = Double.parseDouble(columns[4]);
        double sigmaMoy = Double.parseDouble(columns[5]);
        double sigmaIni = Double.parseDouble(columns[6]);
        double sigmaOut = Double.parseDouble(columns[7]);
        double sigmaMax = Double.parseDouble(columns[8]);
        double forceError = Double.parseDouble(columns[9]);
        double slipError = Double.parseDouble(columns[10]);
        String hasConverged = columns[11];

        try {
            PreparedStatement insertion = dbConnection.prepareStatement(
                    "INSERT INTO CSV_output (Cas, Errors, OffsetYield, Friction, Rolling_Torque, Sigma_Moy, Sigma_Ini, Sigma_Out, Sigma_Max, Force_Error, Slip_Error, Has_Converged) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            insertion.setInt(1, cas);
            insertion.setString(2, errors);
            insertion.setDouble(3, offsetYield);
            insertion.setDouble(4, friction);
            insertion.setDouble(5, rollingTorque);
            insertion.setDouble(6, sigmaMoy);
            insertion.setDouble(7, sigmaIni);
            insertion.setDouble(8, sigmaOut);
            insertion.setDouble(9, sigmaMax);
            insertion.setDouble(10, forceError);
            insertion.setDouble(11, slipError);
            insertion.setString(12, hasConverged);

            insertion.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
