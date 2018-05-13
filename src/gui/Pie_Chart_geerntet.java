package gui;

import app.I18n;
import app.Nutzer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;

public class Pie_Chart_geerntet
{

   public static PieChart.Data maisData = new PieChart.Data(
         I18n.getText("sweet_corn") + " " + Nutzer.aktuellerNutzer.getMaisGeerntet(), Nutzer.aktuellerNutzer.getMaisGeerntet());
   public static PieChart.Data weizenData = new PieChart.Data(
         I18n.getText("wheat") + " " + Nutzer.aktuellerNutzer.getWeizenGeerntet(), Nutzer.aktuellerNutzer.getWeizenGeerntet());
   public static ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
         maisData,
         weizenData
   );

   public Node pieChart_geertntet()
   {
      final PieChart pieChart_Pflanzenarten = new PieChart(pieChartData);
      pieChart_Pflanzenarten.setTitle(I18n.getText("Total_harvest"));
      pieChart_Pflanzenarten.setMaxSize(240, 240);
      pieChart_Pflanzenarten.setMinSize(240, 240);
      pieChart_Pflanzenarten.setLabelLineLength(8);
      pieChart_Pflanzenarten.setLabelsVisible(true);
      pieChart_Pflanzenarten.setLegendSide(Side.LEFT);

      return pieChart_Pflanzenarten;
   }
}
