package automaten;

import java.util.ArrayList;

import app.Feld;
import gui.Pie_Chart_geerntet;
import pflanzen.FeldPflanzen;
import pflanzen.PflanzenArten;

public class ErntAutomat implements Automaten
{
   @Override
   public void arbeiten(Feld feld)
   {
      erten(feld);
   }

   // remove Pflanze, wenn Pflanze größer als im Enum
   private synchronized void erten (Feld feld)
   {
      new Thread(() ->
      {
         ArrayList<FeldPflanzen> zuErnten = new ArrayList<>();
         for( FeldPflanzen pflanze : feld.getPflanzenliste() )
         {
            if( pflanze.getHoehe() > pflanze.getPflanzenArten().getErntehoehe() )
            {
               zuErnten.add(pflanze);
            }
         }
         feld.getPflanzenliste().removeAll(zuErnten);
         if( feld.getPflanzenArten() == PflanzenArten.Weizen )
         {
            Pie_Chart_geerntet.weizenData.setPieValue(Pie_Chart_geerntet.weizenData.getPieValue() + zuErnten.size());
         }
         if( feld.getPflanzenArten() == PflanzenArten.Mais )
         {
            Pie_Chart_geerntet.maisData.setPieValue(Pie_Chart_geerntet.maisData.getPieValue() + zuErnten.size());
         }
      }).start();
   }
}