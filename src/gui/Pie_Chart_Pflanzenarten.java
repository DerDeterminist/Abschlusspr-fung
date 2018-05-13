package gui;

import app.I18n;
import app.Nutzer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;

public class Pie_Chart_Pflanzenarten
{

   double maisProzent = ((double) TabbedPane.getDavonMeis() / (double) Nutzer.aktuellerNutzer.getNutzerFelder().size()) * 100d;
   double weizenProzent = ((double) TabbedPane.getDavonWeizen() / (double) Nutzer.aktuellerNutzer.getNutzerFelder().size()) * 100d;
   public PieChart.Data maisData = new PieChart.Data(I18n.getText("sweet_corn") + maisProzent + "%", TabbedPane.getDavonMeis());
   public PieChart.Data weizenData = new PieChart.Data(I18n.getText("wheat") + weizenProzent + "%", TabbedPane.getDavonWeizen());

   public Node pieChart_pflanzenarten()
   {
      ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            maisData,
            weizenData
      );


      final PieChart pieChart_Pflanzenarten = new PieChart(pieChartData);
      pieChart_Pflanzenarten.setTitle(I18n.getText("Distribution_of_field_types"));
      pieChart_Pflanzenarten.setMaxSize(240, 240);
      pieChart_Pflanzenarten.setMinSize(240, 240);
      pieChart_Pflanzenarten.setLabelLineLength(8);
      pieChart_Pflanzenarten.setLabelsVisible(true);
      pieChart_Pflanzenarten.setLegendSide(Side.LEFT);

      return pieChart_Pflanzenarten;
   }
}
