package com.example.demo;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class ControleurFenetreProcessEngineer implements EventHandler<ActionEvent> {

    TextField text_add_user_username;
    PasswordField text_add_user_mdp;
    TextField text_remove_user_username;
    PasswordField text_remove_user_mdp;
    TextField text_update_update_user_old_username;
    TextField text_update_update_user_new_username;
    PasswordField text_update_update_user_old_mdp;
    PasswordField text_update_update_user_new_mdp;
    TextField text_changer_role_nom_utilisateur;
    Button valider_add_user;
    Button valider_remove_user;
    Button valider_update_user;
    Button valider_change_roll;

    Utilisateurs utilisateurs;

    public ControleurFenetreProcessEngineer(TextField text_add_user_username,
                                            PasswordField text_add_user_mdp,
                                            TextField text_remove_user_username,
                                            PasswordField text_remove_user_mdp,
                                            TextField text_update_update_user_old_username,
                                            TextField text_update_update_user_new_username,
                                            PasswordField text_update_update_user_old_mdp,
                                            PasswordField text_update_update_user_new_mdp,
                                            TextField text_changer_role_nom_utilisateur,
                                            Button valider_add_user,
                                            Button valider_remove_user,
                                            Button valider_update_user,
                                            Button valider_change_roll) {

        this.text_add_user_username = text_add_user_username;
        this.text_add_user_mdp = text_add_user_mdp;
        this.text_remove_user_username = text_remove_user_username;
        this.text_remove_user_mdp = text_remove_user_mdp;
        this.text_update_update_user_old_username = text_update_update_user_old_username;
        this.text_update_update_user_new_username = text_update_update_user_new_username;
        this.text_update_update_user_old_mdp = text_update_update_user_old_mdp;
        this.text_update_update_user_new_mdp = text_update_update_user_new_mdp;
        this.text_changer_role_nom_utilisateur = text_changer_role_nom_utilisateur;
        this.valider_add_user = valider_add_user;
        this.valider_remove_user = valider_remove_user;
        this.valider_update_user = valider_update_user;
        this.valider_change_roll = valider_change_roll;

        this.utilisateurs = new Utilisateurs();
    }

    @Override
    public void handle(ActionEvent event) {

        if (event.getSource() == valider_add_user) {

            String username = text_add_user_username.getText();
            String mdp = text_add_user_mdp.getText();

            if (utilisateurs.verifConnexion(username, mdp)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Cet identifiant est déjà utilisé");
                alert.showAndWait();
            } else {
                String[] columns = {username, mdp, "Worker"};
                utilisateurs.insert(columns);
            }
        }

    }



}



