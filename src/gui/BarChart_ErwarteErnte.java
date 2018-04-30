package gui;

import app.Feld;
import app.Util;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import pflanzen.FeldPflanzen;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class BarChart_ErwarteErnte {

    public Node BarChart_ErwarteErnte(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        xAxis.setLabel("Felder");
        xAxis.setTickLabelFont(Util.ueberschriftFont);
        xAxis.setTickLabelFill(Color.BLACK);
        //todo label yAxis übernimmt die werte nicht
        yAxis.setLabel("Erwartete Ernte");
        yAxis.setTickLabelFont(Util.ueberschriftFont);
        yAxis.setTickLabelFill(Color.BLACK);

//         TabbedPane.getDruchschnitlicheHoehe();
//        for (String key : (HashMap)TabbedPane.getDruchschnitlicheHoehe()) {
//
//        }
//
//        for (KeyEvent feld : ) {
//
//            XYChart.Series series = new XYChart.Series();
//            series.getData().add(new XYChart.Data(pflanze.getName(),pflanze.getHoehe()));
//            bc.getData().add(series);
//
//        }
        bc.setBarGap(8);
        bc.setCategoryGap(8);
        bc.setLegendVisible(false);
        bc.setLayoutX(23);

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(
                new KeyFrame(Duration.millis(500),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                for (XYChart.Series<String, Number> series : bc.getData()) {
                                    for (XYChart.Data<String, Number> data : series.getData()) {
                                        //todo richtige daten
                                        data.setYValue(Math.random() * 100);
                                    }
                                }
                            }
                        }
                ));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.setAutoReverse(true);
        tl.play();

        return bc;
    }

}
