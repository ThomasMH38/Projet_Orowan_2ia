package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class ControleurFenetreGraphe implements EventHandler<ActionEvent> {

    String username;
    String password;
    Button mode_process_engineer;
    Utilisateurs utilisateurs;
    Button valider;
    GridPane gridPane;
    Button deconnexion;

    LineChart<Number, Number> friction_coefficient_list;

    LineChart<Number, Number> roll_speed_list;
    LineChart<Number, Number> sigma_list;
    ListView listViewCoefFrictions;


    public ControleurFenetreGraphe(String username, String password, Button mode_process_engineer,
                                   Button valider, GridPane gridPane,
                                   LineChart<Number, Number> friction_coefficient_list,
                                   LineChart<Number, Number> roll_speed_list,
                                   LineChart<Number, Number> sigma_list, Button deconnexion,ListView listViewCoefFrictions){

        this.username = username;
        this.password = password;
        this.mode_process_engineer = mode_process_engineer;
        this.utilisateurs = new Utilisateurs();
        this.valider = valider;
        this.gridPane = gridPane;
        this.friction_coefficient_list = friction_coefficient_list;
        this.roll_speed_list = roll_speed_list;
        this.sigma_list = sigma_list;
        this.deconnexion = deconnexion;
        this.listViewCoefFrictions = listViewCoefFrictions;

    }

    private void addCourbeCoefFriction(LineChart<Number, Number> lineChart, String functionName) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(functionName);

        try {
            Statement stmt = utilisateurs.dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_coef_friction, coef_friction FROM Coefficient_friction");
            while (rs.next()) {
                int id = rs.getInt("id_coef_friction");
                double coef = rs.getDouble("coef_friction");
                double x = id * 0.2;
                series.getData().add(new XYChart.Data<>(x, coef));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        yAxis.setAutoRanging(false);

        yAxis.setLowerBound(0.08);
        yAxis.setUpperBound(0.25);

        lineChart.getData().clear();
        lineChart.getData().add(series);
    }

    private void addRollSpeed(LineChart<Number, Number> lineChart, String functionName) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(functionName);

        try {
            Statement stmt = utilisateurs.dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Cas, Rolling_Torque FROM CSV_output");
            while (rs.next()) {
                int id = rs.getInt("Cas");
                double rolling_torque = rs.getDouble("Rolling_Torque");
                double x = id * 0.2;
                series.getData().add(new XYChart.Data<>(x, rolling_torque));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        yAxis.setAutoRanging(false);

        yAxis.setLowerBound(90000);
        yAxis.setUpperBound(123000);

        lineChart.setHorizontalGridLinesVisible(false); // désactiver les lignes de la grille horizontale

        lineChart.getData().clear();
        lineChart.getData().add(series);
    }

    private void addSigma(LineChart<Number, Number> lineChart, String functionName) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(functionName);

        try {
            Statement stmt = utilisateurs.dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Cas, Sigma_Moy FROM CSV_output");
            while (rs.next()) {
                int id = rs.getInt("Cas");
                double sigma_moy = rs.getDouble("Sigma_Moy");
                double x = id * 0.2;
                series.getData().add(new XYChart.Data<>(x, sigma_moy));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        yAxis.setAutoRanging(false);

        yAxis.setLowerBound(140);
        yAxis.setUpperBound(210);

        lineChart.getData().clear();
        lineChart.getData().add(series);
    }

    private void addVallistViewCoefFrictions(ListView listViewCoefFrictions){
        try{Statement stmt = utilisateurs.dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_coef_friction, coef_friction FROM Coefficient_friction");
            ObservableList<String> items = FXCollections.observableArrayList();
            while (rs.next()) {
                int id = rs.getInt("id_coef_friction");
                double value = rs.getDouble("coef_friction");
                items.add("Time : " + String.format("%.3f", id * 0.2) + " Coef Friction : " + String.format("%.3f", value));
            }
            rs.close();
            stmt.close();
            listViewCoefFrictions.setItems(items);
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handle(ActionEvent actionEvent) {

        if (actionEvent.getSource() == mode_process_engineer) {

            String role = utilisateurs.getRole(username, password);
            if (role.equals("Process_engineer")) {

                Stage stage = (Stage) mode_process_engineer.getScene().getWindow();
                stage.close();


                FenetreProcessEngineer fenetreProcessEngineer = new FenetreProcessEngineer(username, password);
                fenetreProcessEngineer.start(stage);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText(null);
                alert.setContentText("Vous n'avez pas les accès");
                alert.showAndWait();
            }
        }
        if (actionEvent.getSource() == valider) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Êtes-vous sûr de lancer le calcul ?");
            alert.setContentText("Sélectionnez OK pour confirmer, ou Cancel pour stopper l'action.");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    System.out.println("Action confirmé");

                    // On affiche la courbe de coefficient de friction

                    addCourbeCoefFriction(friction_coefficient_list, "Coefficient de friction");
                    addRollSpeed(roll_speed_list, "Roll speed");
                    addSigma(sigma_list, "Sigma");
                    addVallistViewCoefFrictions(listViewCoefFrictions);
                    gridPane.add(friction_coefficient_list, 3, 3);
                    gridPane.add(roll_speed_list,3,4);
                    gridPane.add(sigma_list,3,5);

                }
            });
        }
        if (actionEvent.getSource() == deconnexion){

            Alert confirmationBox = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationBox.setTitle("Confirmation");
            confirmationBox.setHeaderText("Êtes-vous sûr de vouloir quitter la session ?");
            confirmationBox.setContentText("Cliquez sur Confirmer pour valider la déconnexion.");

            ButtonType confirmButton = new ButtonType("Confirmer");
            ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmationBox.getButtonTypes().setAll(confirmButton, cancelButton);

            Optional<ButtonType> result = confirmationBox.showAndWait();

            if (result.get() == confirmButton) {

                Stage stage = (Stage) deconnexion.getScene().getWindow();
                stage.close();


                FenetreLogin fenetreLogin = new FenetreLogin();
                fenetreLogin.start(stage);

            }
            else {}
        }
    }
}
