package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FenetreGraphe extends Application {

    private String username;
    private String password;

    public FenetreGraphe(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public void start(Stage primaryStage) {
        // Créer les composants de la fenêtre
        Label fenetre1 = new Label("Fenêtre1");
        Label stand_xxx = new Label("Stand XXX");
        Label calcul_time = new Label("Calcul Time:");
        TextField textField1 = new TextField();
        Button valider = new Button("Valider");
        Label resultats = new Label("Résultats:");
        Label time_elapsed = new Label("Time elapsed");
        Label friction_coefficient = new Label("Friction coefficient");
        Button mode_process_engineer = new Button("Mode process engineer");
        LineChart<Number, Number> friction_coefficient_list = createLineChart("Friction coefficient", "Time");
        LineChart<Number, Number> roll_speed_list = createLineChart("Roll speed", "Time");
        LineChart<Number, Number> sigma_list = createLineChart("Sigma", "Time");

        // Ajouter les données aux courbes
        addDataToLineChart(friction_coefficient_list, "Cosinus");
        addDataToLineChart(roll_speed_list, "Cosinus");
        addDataToLineChart(sigma_list, "Cosinus");

        // Créer la mise en page de la fenêtre
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.add(fenetre1, 3, 0);
        gridPane.add(stand_xxx, 4, 0);
        gridPane.add(calcul_time, 0, 1);
        gridPane.add(textField1, 1, 1);
        gridPane.add(valider, 2, 1);
        gridPane.add(createTable(), 1, 2);
        gridPane.add(friction_coefficient_list, 3, 3);
        gridPane.add(roll_speed_list, 3, 4);
        gridPane.add(sigma_list, 3, 5);
        gridPane.add(mode_process_engineer, 4, 1);

        valider.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Do you confirm to launch the calcul ?");
            alert.setContentText("Click OK to confirm, or Cancel to stop the action.");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    System.out.println("Action confirmed");
                    // Code pour lancer le calcul ici
                }
            });
        });

        /*
        mode_process_engineer.setOnAction(e -> {
            // Vérification de la condition 1
            boolean condition1 = // Code pour vérifier que l'utilisateur est le process engineer ;
            if (condition1) {
                // Action1
                action1();
            } else {
                // Vérification de la condition 2
                boolean condition2 = // Code pour vérifier que l'utilisateur est le worker;
                if (condition2) {


                    // Affichage d'un message d'erreur
                    Alert alerte = new Alert(Alert.AlertType.ERROR);
                    alerte.setTitle("Erreur");
                    alerte.setHeaderText(null);
                    alerte.setContentText("Vous n'avez pas les droits.");
                    alerte.showAndWait();
                }
            }
        }
    );
         */





        /*
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(friction_coefficient_list, roll_speed_list, sigma_list);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().addAll(gridPane, vBox);
        */
        Scene scene = new Scene(gridPane, 800, 600);

        primaryStage.setTitle("Fenêtre1");
        primaryStage.setScene(scene);
        primaryStage.show();
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

    // Ajouter des données au graphique en ligne
    private void addDataToLineChart(LineChart<Number, Number> lineChart, String functionName) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(functionName);

        for (double x = 0; x <= 10; x += 0.1) {
            double y = Math.cos(x);
            series.getData().add(new XYChart.Data<>(x, y));
        }

        lineChart.getData().add(series);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
