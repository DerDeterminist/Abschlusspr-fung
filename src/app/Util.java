package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import pflanzen.FeldPflanzen;
import pflanzen.PflanzenArten;

public class Util
{

   // Schriftarten
   public static Font ueberschriftFont = new Font("Arial", 18);
   public static Font textFont = new Font("Arial", 15);
   public static Font hinweisFont = new Font("Arial", 10);

   // Feldname -> Feld
   public static Feld getFeldByName(String name)
   {
      Feld dasEineFeld = null;
      for( Feld feld : Nutzer.aktuellerNutzer.getNutzerFelder() )
      {
         if( feld.getName().equals(name) )
         {
            dasEineFeld = feld;
         }
      }
      return dasEineFeld;
   }

   // Observable PflanzenListe
   public static ObservableList<FeldPflanzen> getFeldpflanzenObservable(Feld feld)
   {
      ObservableList<FeldPflanzen> rueckgabe = FXCollections.observableArrayList();
      rueckgabe.addAll(feld.getPflanzenliste());
      return rueckgabe;
   }

   // Observable PflanzenartenListe
   public static ObservableList<String> getPflanzenartenObservable()
   {
      ObservableList<String> options = FXCollections.observableArrayList();
      options.addAll(I18n.getText("sweet_corn"), I18n.getText("wheat"));
      return options;
   }

   // Pfanzenarart Name -> Pflanzenart
   public static PflanzenArten StringToPflanzenart(String name)
   {
      if( name.equals(PflanzenArten.Mais.name()) )
      {
         return PflanzenArten.Mais;
      }
      else
      {
         return PflanzenArten.Weizen;
      }
   }
}
