package com.example.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OrowanFunction {

    public static void executer(String inputpath) {
        try {
            // Chemin d'accès au fichier .exe
            String exePath = "Orowan_x64.exe.exe";

            // Créer le processus pour l'exécutable
            Process process = new ProcessBuilder(exePath).start();

            // Écrire les entrées dans le flux d'entrée standard
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write("i\n");
            writer.flush();
            writer.write("c\n");
            writer.flush();
            writer.write(inputpath + "\n");
            writer.flush();
            writer.write("CSV_output.txt\n");
            writer.flush();
            writer.close();

            // Attendre que le processus se termine
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("L'exécution s'est terminée avec succès !");
            } else {
                System.err.println("L'exécution a échoué avec le code de sortie " + exitCode);
            }

            // Récupérer le répertoire de travail courant
            String currentDir = System.getProperty("user.dir");

            // Chemin d'accès au fichier de sortie
            String outputFilePath = currentDir + File.separator + "CSV_output.txt";

            // Vérifier si le fichier de sortie existe
            File outputFile = new File(outputFilePath);

            if (outputFile.exists()) {
                System.out.println("Le fichier de sortie existe : " + outputFilePath);
            } else {
                System.err.println("Le fichier de sortie n'existe pas !");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}