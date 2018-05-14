package gui;

import java.util.ArrayList;

import app.Feld;
import app.I18n;
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


public class BarChart_PflanzenHoehe
{

   public Node BarCartPfanenHoehe(String name)
   {
      final CategoryAxis xAxis = new CategoryAxis();
      final NumberAxis yAxis = new NumberAxis();
      final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
      xAxis.setLabel(I18n.getText("plants"));
      xAxis.setTickLabelFont(Util.ueberschriftFont);
      xAxis.setTickLabelFill(Color.BLACK);
      yAxis.setLabel(I18n.getText("height"));
      yAxis.setTickLabelFont(Util.ueberschriftFont);
      yAxis.setTickLabelFill(Color.BLACK);

      // Daten für alle Felder des angemeldeten Nutzers
      for( FeldPflanzen pflanze : Util.getFeldByName(name).getPflanzenliste() )
      {
         XYChart.Series series = new XYChart.Series();
         series.getData().add(new XYChart.Data(pflanze.getName(), pflanze.getHoehe()));
         bc.getData().add(series);
      }
      bc.setBarGap(8);
      bc.setCategoryGap(8);
      bc.setLegendVisible(false);
      bc.setLayoutX(23);

      // macht BarChart flexibel
      Timeline tl = new Timeline();
      tl.getKeyFrames().add(
            new KeyFrame(
                  Duration.millis(400),
                  new EventHandler<ActionEvent>()
                  {
                     @Override
                     public void handle(ActionEvent actionEvent)
                     {
                        int couter = 0;
                        for( XYChart.Series<String, Number> series : bc.getData() )
                        {
                           for( XYChart.Data<String, Number> data : series.getData() )
                           {
                              Feld feldByName = Util.getFeldByName(name);
                              if( feldByName == null )
                              {
                                 System.out.println("Alaram");
                              }
                              ArrayList<FeldPflanzen> pflanzenliste = feldByName.getPflanzenliste();
                              if( couter < pflanzenliste.size() )
                              {
                                 data.setYValue(pflanzenliste.get(couter++).getHoehe());
                              }
                           }
                        }
                        if( couter != 0 )
                        {
                           bc.getData().remove(couter, bc.getData().size());
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
