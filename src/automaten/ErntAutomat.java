package automaten;

import java.util.ArrayList;

import app.Feld;
import pflanzen.FeldPflanzen;

public class ErntAutomat implements Automaten
{
   @Override
   public void arbeiten(Feld feld)
   {
      erten(feld);
   }

   // remove Pflanze, wenn Pflanze größer als im Enum
   private void erten(Feld feld)
   {
      new Thread(() ->
      {
         ArrayList<FeldPflanzen> zuErnten = new ArrayList<>();
         for( FeldPflanzen pflanze : feld.getPflanzenliste() )
         {
            if( pflanze.getHoehe() > pflanze.getPflanzenArten().getErntehöhe() )
            {
               zuErnten.add(pflanze);
            }
         }
         feld.getPflanzenliste().removeAll(zuErnten);
      }).start();
   }
}
