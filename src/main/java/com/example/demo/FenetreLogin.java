package com.example.demo;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FenetreLogin extends Application {

    /*public static void main(String[] args) {
        launch(args);
    }*/

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

        ControleurFenetreLogin controleurFenetreLogin = new ControleurFenetreLogin(txtUsername, txtPassword, btnLogin);

        btnLogin.setOnAction(controleurFenetreLogin);

        // Création de la scène principale
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
