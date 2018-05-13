package gui_handling;

import app.Feld;
import app.I18n;
import app.Nutzer;
import automaten.ErntAutomat;
import automaten.GiessAutomat;
import automaten.Saenautomat;
import dao.LesenUndSchreibenLernen;
import gui.TabbedPane;
import javafx.scene.control.Alert;
import pflanzen.PflanzenArten;

import java.util.List;

public class TabbedPane_automatenHandling
{

   public void erntenButton(List<String> ausgewaelt)
   {
      ErntAutomat erntAutomat = new ErntAutomat();
      for( Feld feld : Nutzer.aktuellerNutzer.getNutzerFelder() )
      {
         if( ausgewaelt.contains(feld.getName()) )
         {
            erntAutomat.arbeiten(feld);
         }
      }
   }

   public void geißenButton(List<String> ausgewaelt)
   {
      GiessAutomat giessAutomat = new GiessAutomat();
      for( Feld feld : Nutzer.aktuellerNutzer.getNutzerFelder() )
      {
         if( ausgewaelt.contains(feld.getName()) )
         {
            giessAutomat.arbeiten(feld);
         }
      }
   }

   public void saeenButton(List<String> ausgewaelt)
   {
      Saenautomat saenautomat = new Saenautomat();
      for( Feld feld : Nutzer.aktuellerNutzer.getNutzerFelder() )
      {
         if( ausgewaelt.contains(feld.getName()) )
         {
            saenautomat.arbeiten(feld);
         }
      }
   }

   public void allesButton(List<String> ausgewaelt)
   {
      Saenautomat saenautomat = new Saenautomat();
      GiessAutomat giessAutomat = new GiessAutomat();
      ErntAutomat erntAutomat = new ErntAutomat();
      for( Feld feld : Nutzer.aktuellerNutzer.getNutzerFelder() )
      {
         if( ausgewaelt.contains(feld.getName()) )
         {
            saenautomat.arbeiten(feld);
            giessAutomat.arbeiten(feld);
            erntAutomat.arbeiten(feld);
            //todo fragen: muss die nächste zeile oder reicht beim programm beenden?
            LesenUndSchreibenLernen.felderSchreiben();
         }
      }
   }

   public void neuButton(String name, PflanzenArten pflanzenArten, int pflanzenAnz)
   {
      boolean nameSchonVergeben = false;
      for (Feld feld : Nutzer.aktuellerNutzer.getNutzerFelder()) {
         if (feld.getName().equals(name)){
            nameSchonVergeben = true;
         }
      }
      if (!nameSchonVergeben){
         PflanzenArten pflanzenArten1 = PflanzenArten.Mais;
         if( pflanzenArten.equals(I18n.getText("wheat")) )
         {
            pflanzenArten1 = PflanzenArten.Weizen;
         }
         Feld feld = new Feld(name, pflanzenArten1, pflanzenAnz, Nutzer.aktuellerNutzer.getName());
         TabbedPane.listAutomaten.getItems().add(feld.getName());
         TabbedPane.listContent.getItems().add(feld.getName());
         TabbedPane.listUebersicht.getItems().add(feld.getName());
      }
      else {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setHeaderText(null);
         alert.setContentText(I18n.getText("otherFieldName"));
         alert.showAndWait();
      }
   }

   public void loeschenButton(List<String> ausgewaelt)
   {
      Nutzer.aktuellerNutzer.getNutzerFelder().removeAll(ausgewaelt);
      TabbedPane.listAutomaten.getItems().removeAll(ausgewaelt);
      TabbedPane.listContent.getItems().removeAll(ausgewaelt);
      TabbedPane.listUebersicht.getItems().removeAll(ausgewaelt);
   }
}
