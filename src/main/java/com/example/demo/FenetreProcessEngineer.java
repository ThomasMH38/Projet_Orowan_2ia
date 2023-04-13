package com.example.demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FenetreProcessEngineer extends Application{

    public void start(Stage primaryStage) {
        // Créer les composants de la fenêtre
        // Label fenetre1 = new Label("Fenêtre1");
        // TextField textField1 = new TextField();
        // Button valider = new Button("Valider");

        Label label_fenetre_process_engineer = new Label("Fenetre process engineer");
        Label label_add_user = new Label("Add user");
        Label label_add_user_username = new Label("Username");
        Label label_add_user_password = new Label("Password");
        Label label_remove_user = new Label("Remove user");
        Label label_remove_user_username = new Label("Username");
        Label label_remove_user_password = new Label("Password");
        Label label_update_user = new Label("Change role");
        Label label_update_user_old_username = new Label("Old username");
        Label label_update_user_old_password = new Label("Old password");
        Label label_update_user_new_username = new Label("New username");
        Label label_update_user_new_password = new Label("New password");
        Label label_change_roll = new Label("Change password");
        Label label_change_roll_username = new Label("Username");
        Label label_change_roll_new_roll = new Label("New role");
        Label label_settings = new Label("Settings");

        Label label_min = new Label("Min");
        Label label_max = new Label("Max");
        Label label_entry_thickness = new Label("Entry thickness");
        Label label_exit_thickness = new Label("Exit thickness");
        Label label_entry_tension = new Label("Entry tension");
        Label label_exit_tension = new Label("Exit tension");
        Label label_diameter = new Label("Diameter");
        Label label_roll_force = new Label("Roll Force");
        Label label_forward_slip = new Label("Forward slip");





        TextField text_add_user_username = new TextField();
        PasswordField text_add_user_mdp = new PasswordField();
        TextField text_remove_user_username = new TextField();
        PasswordField text_remove_user_mdp = new PasswordField();
        TextField text_update_update_user_old_username = new TextField();
        TextField text_update_update_user_new_username = new TextField();
        PasswordField text_update_update_user_old_mdp = new PasswordField();
        PasswordField text_update_update_user_new_mdp = new PasswordField();
        TextField text_changer_role_nom_utilisateur = new TextField();

        Button valider_add_user = new Button("Valider");
        Button valider_remove_user = new Button("Valider");
        Button valider_update_user = new Button("Valider");
        Button valider_change_roll = new Button("Valider");

        ControleurFenetreProcessEngineer controleurFenetreProcessEngineer =
                new ControleurFenetreProcessEngineer(text_add_user_username, text_add_user_mdp,
                        text_remove_user_username, text_remove_user_mdp,
                        text_update_update_user_old_username, text_update_update_user_new_username,
                        text_update_update_user_old_mdp, text_update_update_user_new_mdp,
                        text_changer_role_nom_utilisateur, valider_add_user, valider_remove_user,
                        valider_update_user, valider_change_roll);



        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList("worker", "process engineer"));
        // Configurer le choix déroulant
        comboBox.setPromptText("Selectionner un role");
        comboBox.getSelectionModel().selectFirst();


        TextField en_thick_min = new TextField();
        TextField en_thick_max = new TextField();
        TextField ex_thick_min = new TextField();
        TextField ex_thick_max = new TextField();
        TextField en_tens_min = new TextField();
        TextField en_tens_max = new TextField();
        TextField ex_tens_min = new TextField();
        TextField ex_tens_max = new TextField();
        TextField diamater_min = new TextField();
        TextField diamater_max = new TextField();
        TextField roll_force_min = new TextField();
        TextField roll_force_max = new TextField();
        TextField forward_slip_min = new TextField();
        TextField forward_slip_max = new TextField();




        Button repasser_fenetre1 = new Button("Back");

        Button disable_stand = new Button("Disable stand");
        Button enable_stand = new Button("Enable stand");

        Button valider_entry_thickness_min = new Button("Valider");



        Button valider_exit_thickness_min = new Button("Valider");
        Button valider_entry_tension_min = new Button("Valider");
        Button valider_exit_tension_min = new Button("Valider");
        Button valider_diameter_min = new Button("Valider");
        Button valider_roll_force_min = new Button("Valider");
        Button valider_forward_slip_min = new Button("Valider");

        Button valider_entry_thickness_max = new Button("Valider");
        Button valider_exit_thickness_max = new Button("Valider");
        Button valider_entry_tension_max = new Button("Valider");
        Button valider_exit_tension_max = new Button("Valider");
        Button valider_diameter_max = new Button("Valider");
        Button valider_roll_force_max = new Button("Valider");
        Button valider_forward_slip_max = new Button("Valider");



        GridPane gridPane = new GridPane();
        //gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        gridPane.add(label_fenetre_process_engineer, 3, 0);
        gridPane.add(repasser_fenetre1, 0, 0);

        gridPane.add(label_add_user, 0, 1);
        gridPane.add(label_add_user_username, 1, 1);
        gridPane.add(text_add_user_username, 2, 1);
        gridPane.add(label_add_user_password, 3, 1);
        gridPane.add(text_add_user_mdp, 4, 1);
        gridPane.add(valider_add_user, 5, 1);

        gridPane.add(label_remove_user, 0, 2);
        gridPane.add(label_remove_user_username, 1, 2);
        gridPane.add(text_remove_user_username, 2, 2);
        gridPane.add(label_remove_user_password, 3, 2);
        gridPane.add(text_remove_user_mdp, 4, 2);
        gridPane.add(valider_remove_user, 5, 2);

        gridPane.add(label_update_user, 0, 3);
        gridPane.add(label_update_user_old_username, 1, 3);
        gridPane.add(text_update_update_user_old_username, 2, 3);
        gridPane.add(label_update_user_old_password, 3, 3);
        gridPane.add(text_update_update_user_old_mdp, 4, 3);
        gridPane.add(label_update_user_new_username, 5, 3);
        gridPane.add(text_update_update_user_new_username, 6, 3);
        gridPane.add(label_update_user_new_password, 7, 3);
        gridPane.add(text_update_update_user_new_mdp, 8, 3);
        gridPane.add(valider_update_user, 9, 3);


        gridPane.add(label_change_roll, 0, 4);
        gridPane.add(label_change_roll_username, 1, 4);
        gridPane.add(text_changer_role_nom_utilisateur, 2, 4);
        gridPane.add(label_change_roll_new_roll, 3, 4);
        gridPane.add(comboBox, 4, 4);
        gridPane.add(valider_change_roll, 5, 4);

        gridPane.add(label_settings, 0, 5);

        gridPane.add(disable_stand, 0, 6);
        gridPane.add(enable_stand, 2, 6);

        gridPane.add(label_min, 1, 7);
        gridPane.add(label_max, 3, 7);



        gridPane.add(label_entry_thickness, 0, 8);
        gridPane.add(label_exit_thickness, 0, 9);
        gridPane.add(label_entry_tension, 0, 10);
        gridPane.add(label_exit_tension, 0, 11);
        gridPane.add(label_diameter, 0, 12);
        gridPane.add(label_roll_force, 0, 13);
        gridPane.add(label_forward_slip, 0, 14);

        gridPane.add(en_thick_min, 1, 8);
        gridPane.add(en_thick_max, 3, 8);
        gridPane.add(ex_thick_min, 1, 9);
        gridPane.add(ex_thick_max, 3, 9);
        gridPane.add(en_tens_min, 1, 10);
        gridPane.add(en_tens_max, 3, 10);
        gridPane.add(ex_tens_min, 1, 11);
        gridPane.add(ex_tens_max, 3, 11);
        gridPane.add(diamater_min, 1, 12);
        gridPane.add(diamater_max, 3, 12);
        gridPane.add(roll_force_min, 1, 13);
        gridPane.add(roll_force_max, 3, 13);
        gridPane.add(forward_slip_min, 1, 14);
        gridPane.add(forward_slip_max, 3, 14);



        gridPane.add(valider_entry_thickness_min, 2, 8);
        gridPane.add(valider_entry_thickness_max, 4, 8);

        gridPane.add(valider_exit_thickness_min, 2, 9);
        gridPane.add(valider_entry_tension_min, 2, 10);
        gridPane.add(valider_exit_tension_min, 2, 11);
        gridPane.add(valider_diameter_min, 2, 12);
        gridPane.add(valider_roll_force_min, 2, 13);
        gridPane.add(valider_forward_slip_min, 2, 14);

        gridPane.add(valider_exit_thickness_max, 4, 9);
        gridPane.add(valider_entry_tension_max, 4, 10);
        gridPane.add(valider_exit_tension_max, 4, 11);
        gridPane.add(valider_diameter_max, 4, 12);
        gridPane.add(valider_roll_force_max, 4, 13);
        gridPane.add(valider_forward_slip_max, 4, 14);




        Scene scene = new Scene(gridPane, 800, 600);

        primaryStage.setTitle("Fenetre_process_engineer");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
