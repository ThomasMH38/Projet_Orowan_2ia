package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class CsvInputs extends Bdd{


    public CsvInputs(){
        super(Bdd.dbConnection);
    }

    @Override
    public Collection<String> retrieve() {

        ArrayList<String> csvInputs = new ArrayList<>();

        try (Statement st = dbConnection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM CSV_input");
            while (rs.next()) {
                String row = rs.getInt("Cas") + ", " +
                        rs.getDouble("He") + ", " +
                        rs.getDouble("Hs") + ", " +
                        rs.getDouble("Te") + ", " +
                        rs.getDouble("Ts") + ", " +
                        rs.getDouble("Diam_WR") + ", " +
                        rs.getDouble("WRyoung") + ", " +
                        rs.getDouble("offset_value") + ", " +
                        rs.getDouble("mu_ini") + ", " +
                        rs.getDouble("Force") + ", " +
                        rs.getDouble("G");
                csvInputs.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return csvInputs;
    }

    @Override
    public void insert(String[] columns) {
        int cas = Integer.parseInt(columns[0]);
        double he = Double.parseDouble(columns[1]);
        double hs = Double.parseDouble(columns[2]);
        double te = Double.parseDouble(columns[3]);
        double ts = Double.parseDouble(columns[4]);
        double diamWr = Double.parseDouble(columns[5]);
        double wrYoung = Double.parseDouble(columns[6]);
        double offsetValue = Double.parseDouble(columns[7]);
        double muIni = Double.parseDouble(columns[8]);
        double force = Double.parseDouble(columns[9]);
        double g = Double.parseDouble(columns[10]);

        boolean insertLine = true;

        if (he < 1 || he > 70 || hs < 1 || hs > 70 ||
                te < 0 || te > 50 || ts < 0 || ts > 50 || force < 0 || force > 5000 ||
                g < 0 || g > 20 || diamWr < 500 || diamWr > 1500 ||
                wrYoung < 120000 || wrYoung > 250000 || muIni < 0.05 || muIni > 1) {
            insertLine = false;
        }
        if (insertLine) {
            try {
                PreparedStatement insertion = dbConnection.prepareStatement(
                        "INSERT INTO CSV_input (Cas, He, Hs, Te, Ts, Diam_WR, WRyoung, offset_value, mu_ini, Force, G) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                insertion.setInt(1, cas);
                insertion.setDouble(2, he);
                insertion.setDouble(3, hs);
                insertion.setDouble(4, te);
                insertion.setDouble(5, ts);
                insertion.setDouble(6, diamWr);
                insertion.setDouble(7, wrYoung);
                insertion.setDouble(8, offsetValue);
                insertion.setDouble(9, muIni);
                insertion.setDouble(10, force);
                insertion.setDouble(11, g);

                insertion.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
