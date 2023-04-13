package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class FileFormats extends Bdd{



    public FileFormats(){
        super(Bdd.dbConnection);
    }

    @Override
    public Collection<String> retrieve() {

        ArrayList<String> fileFormats = new ArrayList<>();

        try (Statement st = dbConnection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM File_format");
            while (rs.next()) {
                String row = rs.getInt(1) + ", " +
                        rs.getInt(2) + ", " +
                        rs.getDouble(3) + ", " +
                        rs.getDouble(4) + ", " +
                        rs.getDouble(5) + ", " +
                        rs.getDouble(6) + ", " +
                        rs.getDouble(7) + ", " +
                        rs.getDouble(8) + ", " +
                        rs.getDouble(9) + ", " +
                        rs.getDouble(10) + ", " +
                        rs.getDouble(11) + ", " +
                        rs.getDouble(12) + ", " +
                        rs.getDouble(13) + ", " +
                        rs.getDouble(14) + ", " +
                        rs.getDouble(15) + ", " +
                        rs.getDouble(16) + ", " +
                        rs.getDouble(17) + ", " +
                        rs.getDouble(18) + ", " +
                        rs.getDouble(19) + ", " +
                        rs.getDouble(20) + ", " +
                        rs.getDouble(21) + ", " +
                        rs.getDouble(22) + ", " +
                        rs.getDouble(23) + ", " +
                        rs.getDouble(24) + ", " +
                        rs.getDouble(25);
                fileFormats.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileFormats;
    }

    @Override
    public void insert(String[] columns) {

        for (int i = 2; i < columns.length; i++) {
            columns[i] = columns[i].replace(',', '.');
        }

        int LP = Integer.parseInt(columns[0]);
        int MatID = Integer.parseInt(columns[1]);
        double XTime = Double.parseDouble(columns[2]);
        double XLoc = Double.parseDouble(columns[3]);
        double EnThick = Double.parseDouble(columns[4]);
        double ExThick = Double.parseDouble(columns[5]);
        double EnTens = Double.parseDouble(columns[6]);
        double ExTens = Double.parseDouble(columns[7]);
        double RollForce = Double.parseDouble(columns[8]);
        double FSlip = Double.parseDouble(columns[9]);
        double Daiameter = Double.parseDouble(columns[10]);
        double RolledLengthForWorkRolls = Double.parseDouble(columns[11]);
        double YoungModulus = Double.parseDouble(columns[12]);
        double BackupRollDiameter = Double.parseDouble(columns[13]);
        double RolledLengthForBackupRolls = Double.parseDouble(columns[14]);
        double Mu = Double.parseDouble(columns[15]);
        double Torque = Double.parseDouble(columns[16]);
        double AverageSigma = Double.parseDouble(columns[17]);
        double InputError = Double.parseDouble(columns[18]);
        double LubWFIUP = Double.parseDouble(columns[19]);
        double LubWFILo = Double.parseDouble(columns[20]);
        double LubOilFIUp = Double.parseDouble(columns[21]);
        double LubOilFILo = Double.parseDouble(columns[22]);
        double WorkRollSpeed = Double.parseDouble(columns[23]);

        boolean insertLine = true;

        if (XLoc < 0 || EnThick < 1 || EnThick > 70 || ExThick < 1 || ExThick > 70 ||
                EnTens < 0 || EnTens > 50 || ExTens < 0 || ExTens > 50 || RollForce < 0 || RollForce > 5000 ||
                FSlip < 0 || FSlip > 20 || Daiameter < 500 || Daiameter > 1500 ||
                YoungModulus < 120000 || YoungModulus > 250000 || Mu < 0.05 || Mu > 1) {
            insertLine = false;
        }


        if (insertLine) {
            try {
                String query = "INSERT INTO File_format (LP, MatID, XTime, XLoc, EnThick, ExThick, EnTens, ExTens, " +
                        "RollForce, FSlip, Daiameter, Rolled_length_for_Work_Rolls, YoungModulus, Backup_roll_diameter, " +
                        "Rolled_length_for_Backup_rolls, Mu, Torque, AverageSigma, InputError, LubWFIUP, LubWFILo, " +
                        "LubOilFIUp, LubOilFILo, Work_roll_speed) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertion = dbConnection.prepareStatement(query);

                insertion.setInt(1, LP);
                insertion.setInt(2, MatID);
                insertion.setDouble(3, XTime);
                insertion.setDouble(4, XLoc);
                insertion.setDouble(5, EnThick);
                insertion.setDouble(6, ExThick);
                insertion.setDouble(7, EnTens);
                insertion.setDouble(8, ExTens);
                insertion.setDouble(9, RollForce);
                insertion.setDouble(10, FSlip);
                insertion.setDouble(11, Daiameter);
                insertion.setDouble(12, RolledLengthForWorkRolls);
                insertion.setDouble(13, YoungModulus);
                insertion.setDouble(14, BackupRollDiameter);
                insertion.setDouble(15, RolledLengthForBackupRolls);
                insertion.setDouble(16, Mu);
                insertion.setDouble(17, Torque);
                insertion.setDouble(18, AverageSigma);
                insertion.setDouble(19, InputError);
                insertion.setDouble(20, LubWFIUP);
                insertion.setDouble(21, LubWFILo);
                insertion.setDouble(22, LubOilFIUp);
                insertion.setDouble(23, LubOilFILo);
                insertion.setDouble(24, WorkRollSpeed);

                insertion.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement deletion = dbConnection.prepareStatement("DELETE FROM File_format WHERE LP=?");
            deletion.setInt(1, id);
            deletion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
