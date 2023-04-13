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

    static Utilisateurs utilisateurs;
    static CsvInputs csvInputs;
    static CsvOutputs csvOutputs;
    static FileFormats fileFormats;
    static CoefficientFrictions coefficientFrictions;


    public static void main(String[] args)
    {
        utilisateurs = new Utilisateurs();
        csvInputs = new CsvInputs();
        csvOutputs = new CsvOutputs();
        fileFormats = new FileFormats();
        coefficientFrictions = new CoefficientFrictions();


        utilisateurs.openDBConnection("jdbc:h2:tcp://localhost/~/bdd_orowan", "sa", "sa");
        utilisateurs.clearDB();


        try {
            File file = new File("1939351_F2.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("; ");
                String[] utile = new String[11];
                utile[0] = parts[0];
                utile[1] = parts[4];
                utile[2] = parts[5];
                utile[3] = parts[6];
                utile[4] = parts[7];
                utile[5] = parts[10];
                utile[6] = parts[12];
                utile[7] = parts[17];
                utile[8] = parts[15];
                utile[9] = parts[8];
                utile[10] = parts[9];

                fileFormats.insert(parts);
                csvInputs.insert(utile);
            }

            scanner.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("Le fichier n'a pas été trouvé. \n");
            e.printStackTrace();
        }

        utilisateurs.convertTableToTxt("CSV_input","CSV_input.txt");
        System.out.print("Début Orowan \n");
        OrowanFunction.executer("CSV_input.txt");
        System.out.print("Fin Orowan \n");

        try {
            File file = new File("CSV_output.txt");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] parts = line.split("\t");

                csvOutputs.insert(parts);
            }
            scanner.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("Le fichier n'a pas été trouvé.");
            e.printStackTrace();
        }

        utilisateurs.moyenneFriction();


        Application.launch(FenetreLogin.class, args);
    }
}