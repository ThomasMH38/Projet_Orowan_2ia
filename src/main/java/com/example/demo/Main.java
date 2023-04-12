package com.example.demo;

import org.h2.jdbcx.JdbcDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author thoma
 *
 */
//Note : Ce code a pour objectif de pr�senter un exemple de connexion et de gestion de requ�tes avec JDBC.
//Son architecture est volontairement simplifi�e.
//Dans le cadre du projet GL, l'architecture des applications doit comporter des classes de DAO pour g�rer les requ�tes
//et une classe technique g�rant la connection avec la base de donn�es (classes singleton).

public class Main{

    /*static Connection dbConnection;*/
    PreparedStatement insertion;

    public static void main(String[] args)
    {


        Main m = new Main();
        m.openDBConnection("jdbc:h2:tcp://localhost/~/bdd_orowan", "sa", "sa");
        m.clearDB();


        try {
            File file = new File("1939351_F2.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("; ");

                int part0 = Integer.parseInt(parts[0]);
                int part1 = Integer.parseInt(parts[1]);
                for (int i = 2; i < parts.length; i++) {
                    parts[i] = parts[i].replace(',', '.');
                }
                double part2 = Double.parseDouble(parts[2]); double part3 = Double.parseDouble(parts[3]);double part4 = Double.parseDouble(parts[4]);
                double part5 = Double.parseDouble(parts[5]);double part6 = Double.parseDouble(parts[6]);double part7 = Double.parseDouble(parts[7]);
                double part8 = Double.parseDouble(parts[8]);double part9 = Double.parseDouble(parts[9]);double part10 = Double.parseDouble(parts[10]);
                double part11 = Double.parseDouble(parts[11]);double part12 = Double.parseDouble(parts[12]);double part13 = Double.parseDouble(parts[13]);
                double part14 = Double.parseDouble(parts[14]);double part15 = Double.parseDouble(parts[15]);double part16 = Double.parseDouble(parts[16]);
                double part17 = Double.parseDouble(parts[17]);double part18 = Double.parseDouble(parts[18]);double part19 = Double.parseDouble(parts[19]);
                double part20 = Double.parseDouble(parts[20]);double part21 = Double.parseDouble(parts[21]);double part22 = Double.parseDouble(parts[22]);
                double part23 = Double.parseDouble(parts[23]);

                m.insertFileFormat(part0, part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12,
                        part13, part14, part15, part16, part17, part18, part19, part20, part21, part22, part23);
                m.insertCsvInput(part0, part4, part5, part6, part7, part10, part12, part17, part15, part8, part9);
            }


            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier n'a pas été trouvé.");
            e.printStackTrace();
        }
        m.convertTableToTxt("CSV_input","CSV_input.txt");
        System.out.print("Début Orowan");
        OrowanFunction.executer("CSV_input.txt");
        System.out.print("Fin Orowan");

        try {
            File file = new File("CSV_output.txt");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");

                int part0 = Integer.parseInt(parts[0]);
                String part1 = parts[1];
                for (int i = 2; i < 10; i++) {
                    parts[i] = parts[i].replace(',', '.');
                }
                double part2 = Double.parseDouble(parts[2]); double part3 = Double.parseDouble(parts[3]);double part4 = Double.parseDouble(parts[4]);
                double part5 = Double.parseDouble(parts[5]);double part6 = Double.parseDouble(parts[6]);double part7 = Double.parseDouble(parts[7]);
                double part8 = Double.parseDouble(parts[8]);double part9 = Double.parseDouble(parts[9]);double part10 = Double.parseDouble(parts[10]);
                String part11 = parts[11];

                m.insertCsvOutput(part0, part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier n'a pas été trouvé.");
            e.printStackTrace();
        }
        // Création de l'instance de la classe CSVOutputProcessor
        MoyenneFriction processor = new MoyenneFriction(dbConnection);

        // Traitement de CSV_output
        processor.executeMoyenneFriction(dbConnection);

    }










    // Etablit la connexion avec la base de donn�es.
    // L'ouverture d'une connection est une op�ration lente et co�teuse en resssouces.
    // Une seule connection est suffisante pour g�rer les requ�tes dans un contexte non-concurrent
    // (parall�lisation de requ�tes).
    /*public void openDBConnection()
    {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:tcp://localhost/~/bdd_orowan");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");

        try {
            dbConnection=dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }*/





    /*public void clearDB() {
        try {
            Statement stmt = dbConnection.createStatement();

            stmt.executeUpdate("DELETE FROM Utilisateur;");
            stmt.executeUpdate("DELETE FROM File_format;");
            stmt.executeUpdate("DELETE FROM CSV_input;");
            stmt.executeUpdate("DELETE FROM CSV_output;");
            stmt.executeUpdate("DELETE FROM Coefficient_friction;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/










    /*public void displayCoefficientFrictions() {

        Collection<String> coefficientFrictions = retrieveCoefficientFrictions();

        for (String s : coefficientFrictions) {
            System.out.println(s);
        }
    }*/

    /*private Collection<String> retrieveCoefficientFrictions() {

        ArrayList<String> coefficientFrictions = new ArrayList<>();

        // Utilisation d'une clause try-ressource permettant de g�rer les exceptions d'ouverture
        // et de fermeture (automatique) d'une ressource (interface Closeable)
        try (Statement st = dbConnection.createStatement()) {

            // Les requ�tes de consultation sont �x�cut�es avec la m�thode executeQuery.
            // Cette m�thode retourne une objet ResultSet contenant le r�sultat.
            // Si cette requ�te est r�currente, il est possible d'utiliser un PreparedStatement.
            ResultSet rs = st.executeQuery("select * from Coefficient_friction");

            //It�rateur. Retourne True quand il se positionne sur le tuple r�sultat suivant.
            while (rs.next())
            {
                String row = rs.getInt(1) + ", " +
                        rs.getInt(2);

                coefficientFrictions.add(row);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return coefficientFrictions;
    }*/



    // Ins�re une nouvelle ligne dans la table Coefficient_friction � l'aide du PreparedStatement
    /*public void insertCoefficientFriction(int id,int coeff) {
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
    }*/


    // Supprime la ligne d'identifiant id dans la table Coefficient_friction � l'aide du PreparedStatement
    public void deleteCoefficientFriction(int id) {
        try {
            PreparedStatement deletion = dbConnection.prepareStatement("DELETE FROM COEFFICIENT_FRICTION WHERE id_coef_friction=?");
            deletion.setInt(1, id);
            deletion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


















    /*public void displayFileFormats() {
        Collection<String> fileFormats = retrieveFileFormats();
        for (String s : fileFormats) {
            System.out.println(s);
        }
    }*/


    /*private Collection<String> retrieveFileFormats() {
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
    }*/

    /*public void insertFileFormat(int LP, int MatID, double XTime, double XLoc, double EnThick, double ExThick,
                                 double EnTens, double ExTens, double RollForce, double FSlip, double Daiameter,
                                 double RolledLengthForWorkRolls, double YoungModulus, double BackupRollDiameter,
                                 double RolledLengthForBackupRolls, double Mu, double Torque, double AverageSigma,
                                 double InputError, double LubWFIUP, double LubWFILo, double LubOilFIUp,
                                 double LubOilFILo, double WorkRollSpeed) {

        boolean insertLine = true;

        // Vérification des contraintes
        if (XLoc < 0 || EnThick < 1 || EnThick > 70 || ExThick < 1 || ExThick > 70 ||
                EnTens < 0 || EnTens > 50 || ExTens < 0 || ExTens > 50 || RollForce < 0 || RollForce > 5000 ||
                FSlip < 0 || FSlip > 20 || Daiameter < 500 || Daiameter > 1500 ||
                YoungModulus < 120000 || YoungModulus > 250000 || Mu < 0.05 || Mu > 1) {
            insertLine = false;
        }


        if (insertLine) {
            try {
                // Préparation de la requête avec des paramètres
                String query = "INSERT INTO File_format (LP, MatID, XTime, XLoc, EnThick, ExThick, EnTens, ExTens, " +
                        "RollForce, FSlip, Daiameter, Rolled_length_for_Work_Rolls, YoungModulus, Backup_roll_diameter, " +
                        "Rolled_length_for_Backup_rolls, Mu, Torque, AverageSigma, InputError, LubWFIUP, LubWFILo, " +
                        "LubOilFIUp, LubOilFILo, Work_roll_speed) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertion = dbConnection.prepareStatement(query);

                // Fixation des valeurs des paramètres de la requête
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

                // Exécution de la requête
                insertion.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/

    // Supprime la ligne d'identifiant id dans la table File_format à l'aide du PreparedStatement
    public void deleteFileFormat(int id) {
        try {
            PreparedStatement deletion = dbConnection.prepareStatement("DELETE FROM File_format WHERE LP=?");
            deletion.setInt(1, id);
            deletion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

















    /*public void displayCsvInput() {

        Collection<String> csvInputs = retrieveCsvInputs();

        for (String s : csvInputs) {
            System.out.println(s);
        }
    }*/

    /*private Collection<String> retrieveCsvInputs() {
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
    }*/

    /*public void insertCsvInput(int cas, double he, double hs, double te, double ts, double diamWr,
                               double wrYoung, double offsetValue, double muIni, double force, double g) {

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
    }*/

    public void deleteCsvInput(int cas) {
        try {
            PreparedStatement deletion = dbConnection.prepareStatement("DELETE FROM CSV_input WHERE Cas=?");
            deletion.setInt(1, cas);
            deletion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
















    /*public void displayCsvOutput() {
        Collection<String> csvOutputs = retrieveCsvOutputs();

        for (String s : csvOutputs) {
            System.out.println(s);
        }
    }*/

    /*private Collection<String> retrieveCsvOutputs() {
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
    }*/

    /*public void insertCsvOutput(int cas, String errors, double offsetYield, double friction, double rollingTorque,
                                double sigmaMoy, double sigmaIni, double sigmaOut, double sigmaMax, double forceError, double slipError,
                                String hasConverged) {

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
    }*/

    public void deleteCsvOutput(int cas) {
        try {
            PreparedStatement deletion = dbConnection.prepareStatement("DELETE FROM CSV_output WHERE Cas=?");
            deletion.setInt(1, cas);
            deletion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


















    /*public void displayUtilisateur() {
        Collection<String> utilisateurs = retrieveUtilisateurs();

        for (String s : utilisateurs) {
            System.out.println(s);
        }
    }*/

    /*private Collection<String> retrieveUtilisateurs() {
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
    }*/

    /*public void insertUtilisateur(int id, String nom, String motDePasse, String role) {

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
    }*/

    public void deleteUtilisateur(int id) {
        try {
            PreparedStatement deletion = dbConnection.prepareStatement("DELETE FROM Utilisateur WHERE id=?");
            deletion.setInt(1, id);
            deletion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






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



}
