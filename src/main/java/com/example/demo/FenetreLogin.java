package com.example.demo;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FenetreLogin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fenetre de connexion");

        // Création des éléments de la vue
        Label lblUsername = new Label("Nom d'utilisateur:");
        TextField txtUsername = new TextField();
        Label lblPassword = new Label("Mot de passe:");
        PasswordField txtPassword = new PasswordField();
        Button btnLogin = new Button("Se connecter");

        // Création d'une grille pour organiser les éléments de la vue
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Ajout des éléments à la grille
        gridPane.add(lblUsername, 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(btnLogin, 1, 2);

        // Ajout d'un événement pour le bouton de connexion
        btnLogin.setOnAction(event -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            // Vérification de l'identifiant et du mot de passe
            if (username.equals("admin") && password.equals("admin")) {
                System.out.println("Connexion réussie");
                // Ajout de l'action à réaliser après la connexion réussie
            } else {
                // Affichage d'une fenêtre d'alerte en cas d'échec de la connexion
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText(null);
                alert.setContentText("Identifiant ou mot de passe incorrect");
                alert.showAndWait();
            }
        });

        // Création de la scène principale
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
