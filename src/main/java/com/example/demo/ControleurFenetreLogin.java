package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControleurFenetreLogin implements EventHandler<ActionEvent> {

    private TextField txtUsername;
    private PasswordField txtPassword;
    private Button btnLogin;
    private Utilisateurs utilisateurs;

    public ControleurFenetreLogin(TextField txtUsername, PasswordField txtPassword, Button btnLogin) {

        this.txtUsername = txtUsername;
        this.txtPassword = txtPassword;
        this.btnLogin = btnLogin;
        this.utilisateurs = new Utilisateurs();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (utilisateurs.verifConnexion(username, password)) {
            System.out.println("Connexion réussie");
            // Ajout de l'action à réaliser après la connexion réussie

            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();

            // Ouvrir la fenêtre principale
            FenetreGraphe fenetreGraphe = new FenetreGraphe(username, password);
            fenetreGraphe.start(stage);
        }

        else {
            // Affichage d'une fenêtre d'alerte en cas d'échec de la connexion
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText(null);
            alert.setContentText("Identifiant ou mot de passe incorrect");
            alert.showAndWait();
        }
    }
}
