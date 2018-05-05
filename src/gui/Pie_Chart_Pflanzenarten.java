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

   double maisProzent = ((double) TabbedPane.getDavonMeis()/(double) TabbedPane.getFelder_des_Nutzers().size())*100d;
   double weizenProzent = ((double) TabbedPane.getDavonWeizen()/(double) TabbedPane.getFelder_des_Nutzers().size())*100d;
   public PieChart.Data maisData = new PieChart.Data("Mais "+maisProzent+"%", TabbedPane.getDavonMeis());
   public PieChart.Data weizenData = new PieChart.Data("Weizen "+weizenProzent+"%", TabbedPane.getDavonWeizen());

   public Node pieChart_pflanzenarten()
   {
      ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            maisData,
            weizenData
      );


      final PieChart pieChart_Pflanzenarten = new PieChart(pieChartData);
      pieChart_Pflanzenarten.setTitle("Verteilung der Feldarten");
      pieChart_Pflanzenarten.setMaxSize(240, 240);
      pieChart_Pflanzenarten.setMinSize(240, 240);
      pieChart_Pflanzenarten.setLabelLineLength(8);
      pieChart_Pflanzenarten.setLabelsVisible(true);
      pieChart_Pflanzenarten.setLegendSide(Side.LEFT);

      return pieChart_Pflanzenarten;
   }
}
