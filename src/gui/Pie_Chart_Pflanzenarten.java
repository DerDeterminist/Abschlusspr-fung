package gui;

import java.awt.Label;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;

public class Pie_Chart_Pflanzenarten
{

   public Node pieChart_pflanzenarten()
   {
      PieChart.Data maisData = new PieChart.Data("Mais", TabbedPane.getDavonMeis());
      PieChart.Data weizenData = new PieChart.Data("Weizen", TabbedPane.getDavonWeizen());

      ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            maisData,
            weizenData
      );


      final PieChart pieChart_Pflanzenarten = new PieChart(pieChartData);
      pieChart_Pflanzenarten.setTitle("Verteilung der Feldarten");
      pieChart_Pflanzenarten.setMaxSize(200, 200);
      pieChart_Pflanzenarten.setMinSize(200, 200);
      pieChart_Pflanzenarten.setLabelLineLength(10);
      pieChart_Pflanzenarten.setLabelsVisible(true);
      pieChart_Pflanzenarten.setLegendSide(Side.LEFT);

      maisData.setName(maisData.getName() + " " + maisData.getPieValue() + " %");
      weizenData.setName(weizenData.getName() + " " + weizenData.getPieValue() + " %");

      // todo zeigt den Prozentwert beim howen
      //Label caption = new Label("");
      //for( PieChart.Data data : pieChartData )
      //{
      //   data.getNode().addEventHandler(
      //         MouseEvent.MOUSE_ENTERED_TARGET,
      //         new EventHandler<MouseEvent>()
      //         {
      //            @Override
      //            public void handle(MouseEvent e)
      //            {
      //               //pieChart_Pflanzenarten.setTranslateX(e.getSceneX());
      //               //pieChart_Pflanzenarten.setTranslateY(e.getSceneX());
      //               pieChart_Pflanzenarten.getData().get()(String.valueOf(data.getPieValue()) + "%");
      //            }
      //         }
      //   );
      //}

      return pieChart_Pflanzenarten;
   }
}
