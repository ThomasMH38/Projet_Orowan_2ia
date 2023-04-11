package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Fenetre1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créer les composants de la fenêtre
        Label label1 = new Label("Fenêtre1");
        Label label2 = new Label("Stand XXX");
        Label label3 = new Label("Calcul Time:");
        TextField textField1 = new TextField();
        Button button1 = new Button("Valider");
        Label label4 = new Label("Résultats:");
        Label label5 = new Label("Time elapsed");
        Label label6 = new Label("Friction coefficient");
        LineChart<Number, Number> lineChart1 = createLineChart("Friction coefficient", "Time");
        LineChart<Number, Number> lineChart2 = createLineChart("Roll speed", "Time");
        LineChart<Number, Number> lineChart3 = createLineChart("Sigma", "Time");

        // Ajouter les données aux courbes
        addDataToLineChart(lineChart1, "Cosinus");
        addDataToLineChart(lineChart2, "Cosinus");
        addDataToLineChart(lineChart3, "Cosinus");

        // Créer la mise en page de la fenêtre
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.add(label1, 0, 0);
        gridPane.add(label2, 2, 0);
        gridPane.add(label3, 0, 1);
        gridPane.add(textField1, 1, 1);
        gridPane.add(button1, 2, 1);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(label4, createTable(), lineChart1, lineChart2, lineChart3);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().addAll(gridPane, vBox);

        Scene scene = new Scene(hBox, 800, 600);

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
