package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class CsvInputs extends Bdd{

    double he_min;
    double he_max;
    double hs_min;
    double hs_max;
    double te_min;
    double te_max;
    double ts_min;
    double ts_max;
    double force_min;
    double force_max;
    double g_min;
    double g_max;
    double diamWr_min;
    double diamWr_max;
    double wrYoung_min;
    double wrYoung_max;
    double muIni_min;
    double muIni_max;

    public CsvInputs(){
        super(Bdd.dbConnection);
        this.he_min = 1;
        this.he_max = 70;
        this.hs_min = 1;
        this.hs_max = 70;
        this.te_min = 0;
        this.te_max = 50;
        this.ts_min = 0;
        this.ts_max = 50;
        this.force_min = 0;
        this.force_max = 5000;
        this.g_min = 0;
        this.g_max = 20;
        this.diamWr_min = 500;
        this.diamWr_max = 1500;
        this.wrYoung_min = 120000;
        this.wrYoung_max = 250000;
        this.muIni_min = 0.05;
        this.muIni_max = 1;
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

        for (int i = 1; i < columns.length; i++) {
            columns[i] = columns[i].replace(',', '.');
        }

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

        if (he < he_min || he > he_max || hs < hs_min || hs > hs_max ||
                te < te_min || te > te_max || ts < ts_min || ts > ts_max ||
                force < force_min || force > force_max ||
                g < g_min || g > g_max || diamWr < diamWr_min || diamWr > diamWr_max ||
                wrYoung < wrYoung_min || wrYoung > wrYoung_max || muIni < muIni_min || muIni > muIni_max) {
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

    @Override
    public void delete(int cas) {
        try {
            PreparedStatement deletion = dbConnection.prepareStatement("DELETE FROM CSV_input WHERE Cas=?");
            deletion.setInt(1, cas);
            deletion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
