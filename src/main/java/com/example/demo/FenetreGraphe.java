package com.example.demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FenetreGraphe extends Application {

    private String username;
    private String password;

    Utilisateurs utilisateurs;

    public FenetreGraphe(String username, String password){
        this.username = username;
        this.password = password;
        this.utilisateurs = new Utilisateurs();
    }

    @Override
    public void start(Stage primaryStage) {
        // Créer les composants de la fenêtre
        Label fenetre1 = new Label("Fenêtre1");
        fenetre1.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold;");
        Label calcul_time = new Label("Calcul Time:");
        TextField textField1 = new TextField();
        Button valider = new Button("Valider");
        Label resultats = new Label("Résultats:");
        Label time_elapsed = new Label("Time elapsed");
        Label friction_coefficient = new Label("Friction coefficient");
        Button mode_process_engineer = new Button("Mode process engineer");
        LineChart<Number, Number> roll_speed_list = createLineChart("Time", "Roll speed");
        LineChart<Number, Number> sigma_list = createLineChart("Time", "Sigma");
        LineChart<Number, Number> friction_coefficient_list = createLineChart("Time", "Friction coefficient");
        Button deconnexion = new Button("Deconnexion");
        GridPane createTable = createTable();
        ListView<String> listViewCoefFrictions = new ListView<String>();


        ComboBox<String> comboBox_select_stand = new ComboBox<>(FXCollections.observableArrayList("Stand F2", "Stand F3"));
        // Configurer le choix déroulant
        comboBox_select_stand.setPromptText("Select a stand");
        //comboBox_select_stand.getSelectionModel().selectFirst();

        // Ajouter les données aux courbes
        //addDataToLineChart(roll_speed_list, "Cosinus");
        //addDataToLineChart(sigma_list, "Cosinus");
        //addDataToLineChart(friction_coefficient_list,"Coefficient de friction");

        // Créer la mise en page de la fenêtre
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        gridPane.add(fenetre1, 3, 0);
        gridPane.add(comboBox_select_stand, 4, 0);
        gridPane.add(calcul_time, 0, 1);
        gridPane.add(textField1, 1, 1);
        gridPane.add(valider, 2, 1);
        gridPane.add(listViewCoefFrictions, 1, 3);
        gridPane.add(roll_speed_list, 3, 4);
        gridPane.add(friction_coefficient_list, 3, 3);
        gridPane.add(sigma_list, 3, 5);
        gridPane.add(mode_process_engineer, 4, 1);
        gridPane.add(deconnexion,0,0);

        Scene scene = new Scene(gridPane, 800, 600);

        primaryStage.setTitle("Fenêtre1");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        ControleurFenetreGraphe controleurFenetreGraphe =
                new ControleurFenetreGraphe(username, password, mode_process_engineer, valider, gridPane,
                        friction_coefficient_list,roll_speed_list,sigma_list, deconnexion,
                        listViewCoefFrictions);

        mode_process_engineer.setOnAction(controleurFenetreGraphe);
        valider.setOnAction(controleurFenetreGraphe);
        deconnexion.setOnAction(controleurFenetreGraphe);
    }

    // Créer un tableau de deux colonnes
    private GridPane createTable() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label label1 = new Label("Time elapsed");
        Label label2 = new Label("Friction coefficient");
        gridPane.add(label1, 0, 0);
        gridPane.add(label2, 1, 0);

        return gridPane;
    }

    // Créer un graphique en ligne
    private LineChart<Number, Number> createLineChart(String xAxisLabel, String yAxisLabel) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(xAxisLabel);
        yAxis.setLabel(yAxisLabel);

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);

        return lineChart;
    }
}
