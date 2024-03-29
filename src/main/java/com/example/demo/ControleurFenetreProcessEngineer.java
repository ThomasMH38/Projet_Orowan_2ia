package com.example.demo;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;


public class ControleurFenetreProcessEngineer implements EventHandler<ActionEvent> {

    String username;
    String password;

    TextField text_add_user_username;
    PasswordField text_add_user_mdp;
    TextField text_remove_user_username;
    TextField text_update_update_username;
    PasswordField text_update_update_user_new_mdp;
    TextField text_changer_role_nom_utilisateur;
    ComboBox<String> comboBox_choice_role;


    TextField en_thick_min;
    TextField en_thick_max;
    TextField ex_thick_min;
    TextField ex_thick_max;
    TextField en_tens_min;
    TextField en_tens_max;
    TextField ex_tens_min;
    TextField ex_tens_max;
    TextField diamater_min;
    TextField diamater_max;
    TextField roll_force_min;
    TextField roll_force_max;
    TextField forward_slip_min;
    TextField forward_slip_max;


    Button valider_add_user;
    Button valider_remove_user;
    Button valider_update_user;
    Button valider_change_roll;
    Button valider_change_constraints;
    Button repasser_fenetre_graphe;

    Utilisateurs utilisateurs;
    FileFormats fileFormats;

    public ControleurFenetreProcessEngineer(String username,
                                            String password,
                                            TextField text_add_user_username,
                                            PasswordField text_add_user_mdp,
                                            TextField text_remove_user_username,
                                            TextField text_update_update_username,
                                            PasswordField text_update_update_user_new_mdp,
                                            TextField text_changer_role_nom_utilisateur,
                                            ComboBox<String> comboBox_choice_role,
                                            TextField en_thick_min,
                                            TextField en_thick_max,
                                            TextField ex_thick_min,
                                            TextField ex_thick_max,
                                            TextField en_tens_min,
                                            TextField en_tens_max,
                                            TextField ex_tens_min,
                                            TextField ex_tens_max,
                                            TextField diamater_min,
                                            TextField diamater_max,
                                            TextField roll_force_min,
                                            TextField roll_force_max,
                                            TextField forward_slip_min,
                                            TextField forward_slip_max,
                                            Button valider_add_user,
                                            Button valider_remove_user,
                                            Button valider_update_user,
                                            Button valider_change_roll,
                                            Button valider_change_constraints,
                                            Button repasser_fenetre_graphe) {

        this.username = username;
        this.password = password;

        this.text_add_user_username = text_add_user_username;
        this.text_add_user_mdp = text_add_user_mdp;
        this.text_remove_user_username = text_remove_user_username;
        this.text_update_update_username = text_update_update_username;
        this.text_update_update_user_new_mdp = text_update_update_user_new_mdp;
        this.text_changer_role_nom_utilisateur = text_changer_role_nom_utilisateur;
        this.comboBox_choice_role = comboBox_choice_role;

        this.en_thick_min = en_thick_min;
        this.en_thick_max = en_thick_max;
        this.ex_thick_min = ex_thick_min;
        this.ex_thick_max = ex_thick_max;
        this.en_tens_min = en_tens_min;
        this.en_tens_max = en_tens_max;
        this.ex_tens_min = ex_tens_min;
        this.ex_tens_max = ex_tens_max;
        this.diamater_min = diamater_min;
        this.diamater_max = diamater_max;
        this.roll_force_min = roll_force_min;
        this.roll_force_max = roll_force_max;
        this.forward_slip_min = forward_slip_min;
        this.forward_slip_max = forward_slip_max;

        this.valider_add_user = valider_add_user;
        this.valider_remove_user = valider_remove_user;
        this.valider_update_user = valider_update_user;
        this.valider_change_roll = valider_change_roll;
        this.valider_change_constraints = valider_change_constraints;
        this.repasser_fenetre_graphe = repasser_fenetre_graphe;

        this.utilisateurs = new Utilisateurs();
        this.fileFormats = new FileFormats();


        en_thick_min.appendText(String.valueOf(fileFormats.enThick_min));
        en_thick_max.appendText(String.valueOf(fileFormats.enThick_max));
        ex_thick_min.appendText(String.valueOf(fileFormats.exThick_min));
        ex_thick_max.appendText(String.valueOf(fileFormats.exThick_max));
        en_tens_min.appendText(String.valueOf(fileFormats.enTens_min));
        en_tens_max.appendText(String.valueOf(fileFormats.enTens_max));
        ex_tens_min.appendText(String.valueOf(fileFormats.exTens_min));
        ex_tens_max.appendText(String.valueOf(fileFormats.exTens_max));
        diamater_min.appendText(String.valueOf(fileFormats.daiameter_max));
        diamater_max.appendText(String.valueOf(fileFormats.daiameter_max));
        roll_force_min.appendText(String.valueOf(fileFormats.rollForce_min));
        roll_force_max.appendText(String.valueOf(fileFormats.rollForce_max));
        forward_slip_min.appendText(String.valueOf(fileFormats.fSlip_min));
        forward_slip_max.appendText(String.valueOf(fileFormats.fSlip_max));
    }

    @Override
    public void handle(ActionEvent event) {

        if (event.getSource() == valider_add_user) {

            String username = text_add_user_username.getText();
            String mdp = text_add_user_mdp.getText();

            if (utilisateurs.verifSameName(username)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Cet identifiant est déjà utilisé.");
                alert.showAndWait();
            }
            if(username.length() < 3 || mdp.length() < 5){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("L'identifiant ou le mot de passe est trop court.");
                alert.setContentText("Minimum identifiant : 3 charactères \n " +
                        "Minimum mot de passe : 5 charactères");
                alert.showAndWait();
            }
            else {
                String[] columns = {username, mdp, "Worker"};
                utilisateurs.insert(columns);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Opération réussie");
                alert.setHeaderText(null);
                alert.setContentText("L'utilisateur a été créé.");
                alert.showAndWait();
            }

            text_add_user_username.clear();
            text_add_user_mdp.clear();
        }

        if (event.getSource() == valider_remove_user) {

            String username = text_remove_user_username.getText();

            if (utilisateurs.verifSameName(username)) {

                Alert confirmationBox = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationBox.setTitle("Confirmation");
                confirmationBox.setHeaderText("Êtes-vous sûr de vouloir effectuer cette action?");
                confirmationBox.setContentText("Cette action ne peut pas être annulée.");

                ButtonType confirmButton = new ButtonType("Confirmer");
                ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                confirmationBox.getButtonTypes().setAll(confirmButton, cancelButton);

                Optional<ButtonType> result = confirmationBox.showAndWait();

                if (result.get() == confirmButton) {

                    utilisateurs.delete(username);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Opération réussie");
                    alert.setHeaderText(null);
                    alert.setContentText("L'utilisateur a été supprimé.");
                    alert.showAndWait();
                } else {
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Cet utilisateur n'existe pas.");
                alert.showAndWait();
            }

            text_remove_user_username.clear();
        }

        if (event.getSource() == valider_update_user) {

            String nom = text_update_update_username.getText();
            String mdp = text_update_update_user_new_mdp.getText();

            if (utilisateurs.verifSameName(nom)) {
                utilisateurs.changeMDP(nom, mdp);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Opération réussie");
                alert.setHeaderText(null);
                alert.setContentText("Le mot de passe a été modifié.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Cet utilisateur n'existe pas.");
                alert.showAndWait();
            }

            text_update_update_username.clear();
            text_update_update_user_new_mdp.clear();
        }

        if (event.getSource() == valider_change_roll) {

            String nom = text_changer_role_nom_utilisateur.getText();
            String role_choisi = comboBox_choice_role.getSelectionModel().getSelectedItem();

            if (utilisateurs.verifSameName(nom)) {
                if ("Process Engineer".equals(role_choisi)) {
                    role_choisi = "Process_engineer";
                }
                utilisateurs.changeRole(nom, role_choisi);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Opération réussie");
                alert.setHeaderText(null);
                alert.setContentText("Le rôle a été modifié.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Cet utilisateur n'existe pas.");
                alert.showAndWait();
            }
            text_changer_role_nom_utilisateur.clear();
        }

        if (event.getSource() == repasser_fenetre_graphe) {

            Stage stage = (Stage) repasser_fenetre_graphe.getScene().getWindow();
            stage.close();

            FenetreGraphe fenetreGraphe = new FenetreGraphe(username, password);
            fenetreGraphe.start(stage);

        }


        if (event.getSource() == valider_change_constraints) {
            String new_en_thick_min = en_thick_min.getText();
            fileFormats.enThick_min = Double.parseDouble(new_en_thick_min);

            String new_en_thick_max = en_thick_max.getText();
            fileFormats.enThick_max = Double.parseDouble(new_en_thick_max);

            String new_ex_thick_min = ex_thick_min.getText();
            fileFormats.exThick_min = Double.parseDouble(new_ex_thick_min);

            String new_ex_thick_max = ex_thick_max.getText();
            fileFormats.exThick_max = Double.parseDouble(new_ex_thick_max);

            String new_en_tens_min = en_tens_min.getText();
            fileFormats.enTens_min = Double.parseDouble(new_en_tens_min);

            String new_en_tens_max = en_tens_max.getText();
            fileFormats.enTens_max = Double.parseDouble(new_en_tens_max);

            String new_ex_tens_min = ex_tens_min.getText();
            fileFormats.exTens_min = Double.parseDouble(new_ex_tens_min);

            String new_ex_tens_max = ex_tens_max.getText();
            fileFormats.exTens_max = Double.parseDouble(new_ex_tens_max);

            String new_diamater_min = diamater_min.getText();
            fileFormats.daiameter_min = Double.parseDouble(new_diamater_min);

            String new_diamater_max = diamater_max.getText();
            fileFormats.daiameter_max = Double.parseDouble(new_diamater_max);

            String new_roll_force_min = roll_force_min.getText();
            fileFormats.rollForce_min = Double.parseDouble(new_roll_force_min);

            String new_roll_force_max = roll_force_max.getText();
            fileFormats.rollForce_max = Double.parseDouble(new_roll_force_max);

            String new_forward_slip_min = forward_slip_min.getText();
            fileFormats.fSlip_min = Double.parseDouble(new_forward_slip_min);

            String new_forward_slip_max = forward_slip_max.getText();
            fileFormats.fSlip_max = Double.parseDouble(new_forward_slip_max);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opération réussie");
            alert.setHeaderText(null);
            alert.setContentText("Les nouvelles contraintes ont été prises en compte.");
            alert.showAndWait();

        }


    }
}



