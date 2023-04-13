package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurFenetreGraphe implements EventHandler<ActionEvent> {

    String username;
    String password;
    Button mode_process_engineer;
    Utilisateurs utilisateurs;

    public ControleurFenetreGraphe(String username, String password, Button mode_process_engineer){

        this.username = username;
        this.password = password;
        this.mode_process_engineer = mode_process_engineer;
        this.utilisateurs = new Utilisateurs();

    }

    @Override
    public void handle(ActionEvent actionEvent) {

        String role = utilisateurs.getRole(username, password);
        if( role.equals("Process_engineer")){

            Stage stage = (Stage) mode_process_engineer.getScene().getWindow();
            stage.close();


            FenetreProcessEngineer fenetreProcessEngineer = new FenetreProcessEngineer();
            fenetreProcessEngineer.start(stage);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas les accès");
            alert.showAndWait();
        }


    }
}
