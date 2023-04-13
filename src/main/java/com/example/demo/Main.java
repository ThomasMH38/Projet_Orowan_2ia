package com.example.demo;

import javafx.application.Application;

import java.io.File;
import java.io.FileNotFoundException;
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


        Application.launch(FenetreLogin.class, args);
    }
}