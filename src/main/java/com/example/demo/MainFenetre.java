package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainFenetre extends Application {

    private Stage stage;

    public MainFenetre() {
        stage = new Stage();
        stage.setTitle("Ma super application");

        Button deconnexionButton = new Button("Déconnexion");
        deconnexionButton.setOnAction(e -> {
            Platform.exit();
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(deconnexionButton);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }

    public void start(Stage primaryStage) throws Exception {
        // Création de la fenêtre principale et ajout des éléments graphiques
        primaryStage.setTitle("Ma première application JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Lancement de l'application

        Application.launch(ConnexionFenetre.class, args);
    }
    public void ouvrir() {
        stage.show();
    }

    public void fermer() {
        stage.close();
    }
}
