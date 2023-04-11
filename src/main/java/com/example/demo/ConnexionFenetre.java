package com.example.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class ConnexionFenetre {

    private Stage stage;
    private Scene scene;
    private VBox layout;
    private Label titreLabel, nomLabel, mdpLabel, erreurLabel;
    private TextField nomTextField;
    private PasswordField mdpPasswordField;
    private Button connexionButton;

    public ConnexionFenetre() {
        // Initialisation des éléments graphiques de la fenêtre
        titreLabel = new Label("Connexion");
        nomLabel = new Label("Nom d'utilisateur : ");
        mdpLabel = new Label("Mot de passe : ");
        erreurLabel = new Label();
        nomTextField = new TextField();
        mdpPasswordField = new PasswordField();
        connexionButton = new Button("Se connecter");

        // Mise en page des éléments graphiques dans une grille
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(nomLabel, 0, 0);
        grid.add(nomTextField, 1, 0);
        grid.add(mdpLabel, 0, 1);
        grid.add(mdpPasswordField, 1, 1);
        grid.add(connexionButton, 1, 2);
        grid.add(erreurLabel, 0, 3, 2, 1);

        // Mise en page de la grille dans une boîte verticale
        layout = new VBox();
        layout.getChildren().addAll(titreLabel, grid);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        // Création de la scène
        scene = new Scene(layout, 400, 200);

        // Configuration du bouton de connexion
        connexionButton.setOnAction(event -> {
            String nom = nomTextField.getText();
            String mdp = mdpPasswordField.getText();

            // Vérification des informations de connexion dans la base de données
            try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Utilisateur WHERE Nom = ?")) {
                stmt.setString(1, nom);
                ResultSet rs = stmt.executeQuery();

                if (rs.next() && rs.getString("MotDePasse").equals(mdp)) {
                    // Ouverture de la fenêtre principale
                    MainFenetre mainFenetre = new MainFenetre();
                    mainFenetre.ouvrir();
                } else {
                    // Affichage d'un message d'erreur
                    erreurLabel.setText("Nom d'utilisateur ou mot de passe incorrect");
                }
            } catch (SQLException e) {
                erreurLabel.setText("Erreur de connexion à la base de données");
            }
        });
    }

    public void show(Stage primaryStage) {
        // Affichage de la fenêtre
        this.stage = primaryStage;
        stage.setScene(scene);
        stage.setTitle("Connexion");
        stage.show();
    }
}
